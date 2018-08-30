package com.foxminded.dmitriy.task5.symbolCounter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;


public class TwoQCache<K, V> {
    final HashMap<K, V> map;
    private final LinkedHashSet<K> mapIn, mapOut, mapHot;
    protected float quarter = 0.25f;

    private int sizeIn;
    private int sizeOut;
    private int sizeHot;

    private int maxSizeIn;
    private int maxSizeOut;
    private int maxSizeHot;

    private int putCount;
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private int missCount;

    public TwoQCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }

        calcMaxSize(maxSize);
        map = new HashMap<>(0, 0.75f);
        mapIn = new LinkedHashSet<>();
        mapOut = new LinkedHashSet<>();
        mapHot = new LinkedHashSet<>();
    }

    private void calcMaxSize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            maxSizeIn = (int) (maxSize * quarter);
            maxSizeOut = maxSizeIn * 2;
            maxSizeHot = maxSize - maxSizeIn - maxSizeOut;
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Key == null");
        }
        V mapValue;
        synchronized (this) {
            mapValue = map.get(key);
            if (mapValue != null) {
                hitCount++;
                if (mapHot.contains(key)) {
                    // seems like update LRU
                    mapHot.remove(key);
                    mapHot.add(key);
                } else {
                    if (mapOut.contains(key)) {
                        mapHot.add(key);
                        sizeHot += safeSizeOf(key, mapValue);
                        trimMapHot();
                        sizeOut -= safeSizeOf(key, mapValue);
                        mapOut.remove(key);
                    }
                }
                return mapValue;
            }
            missCount++;
        }
        V createdValue = create(key);
        if (createdValue == null) {
            return null;
        }

        synchronized (this) {
            createCount++;

            if (!map.containsKey(key)) {
                return put(key, createdValue);
            } else {
                return map.get(key);
            }
        }
    }

    private V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key or value == null");
        }

        if (map.containsKey(key)) {
            synchronized (this) {
                V oldValue = map.get(key);
                if (mapIn.contains(key)) {
                    sizeIn -= safeSizeOf(key, oldValue);
                    sizeIn += safeSizeOf(key, value);
                }
                if (mapOut.contains(key)) {
                    sizeOut -= safeSizeOf(key, oldValue);
                    sizeOut += safeSizeOf(key, value);
                }
                if (mapHot.contains(key)) {
                    sizeHot -= safeSizeOf(key, oldValue);
                    sizeHot += safeSizeOf(key, value);
                }
            }
            return map.put(key, value);
        }
        V result;
        synchronized (this) {
            putCount++;
            final int sizeOfValue = safeSizeOf(key, value);
            boolean hasFreeSlot = add2Slot(key, sizeOfValue);

            if (hasFreeSlot) {
                map.put(key, value);
                result = value;
            } else {
                if (trimMapIn(sizeOfValue)) {
                    map.put(key, value);
                    result = value;
                } else {
                    map.put(key, value);
                    mapHot.add(key);
                    sizeHot += safeSizeOf(key, value);
                    trimMapHot();
                    result = value;
                }
            }

        }
        return result;
    }

    private boolean add2Slot(K key, int sizeOfValue) {
        boolean hasFreeSlot = false;
        if (!hasFreeSlot && maxSizeIn >= sizeIn + sizeOfValue) {
            mapIn.add(key);
            sizeIn += sizeOfValue;
            hasFreeSlot = true;
        }
        if (!hasFreeSlot && maxSizeOut >= sizeOut + sizeOfValue) {
            mapOut.add(key);
            sizeOut += sizeOfValue;
            hasFreeSlot = true;
        }
        if (!hasFreeSlot && maxSizeHot >= sizeHot + sizeOfValue) {
            mapHot.add(key);
            sizeHot += sizeOfValue;
            hasFreeSlot = true;
        }
        return hasFreeSlot;
    }

    private V create(K key) {
        return null;
    }

    private void trimMapHot() {
        while (true) {
            K key;
            V value;
            synchronized (this) {
                if (sizeHot < 0 || (mapHot.isEmpty() && mapHot.size() != 0)) {
                    throw new IllegalStateException(getClass().getName()
                            + ".sizeOf() is reporting inconsistent results!");
                }

                if (sizeHot <= maxSizeHot || mapHot.isEmpty()) {
                    break;
                }

                key = mapHot.iterator().next();
                map.remove(key);
                value = map.get(key);
                sizeHot -= safeSizeOf(key, value);
                map.remove(key);
                evictionCount++;
            }
            entryRemoved(true, key, value, null);
        }
    }

    public void resize(int maxSize) {
        calcMaxSize(maxSize);
        synchronized (this) {
            HashMap<K, V> copy = new HashMap<K, V>(map);
            evictAll();
            copy.forEach((k, v) -> put(k, v));
        }
    }

    private void evictAll() {
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            K key = it.next();
            it.remove();
            remove(key);
        }
        mapIn.clear();
        mapOut.clear();
        mapHot.clear();
        sizeIn = 0;
        sizeOut = 0;
        sizeHot = 0;
    }

    private V remove(K key) {
        if (key == null) {
            throw new NullPointerException("key = null");
        }

        V previous;
        synchronized (this) {
            previous = map.remove(key);
            if (previous != null) {
                if (mapIn.contains(key)) {
                    sizeIn -= safeSizeOf(key, previous);
                    mapIn.remove(key);
                }
                if (mapOut.contains(key)) {
                    sizeOut -= safeSizeOf(key, previous);
                    mapOut.remove(key);
                }
                if (mapHot.contains(key)) {
                    sizeHot -= safeSizeOf(key, previous);
                    mapHot.remove(key);
                }
            }
        }
        if (previous != null) {
            entryRemoved(false, key, previous, null);
        }

        return previous;
    }

    private void entryRemoved(boolean b, K key, V previous, Object o) {
    }

    private int safeSizeOf(K key, V value) {
        int result;
        result = sizeOf(key, value);
        if (result <= 0) {
            throw new IllegalStateException("Negative size: " + result);
        }
        return result;
    }

    private int sizeOf(K key, V value) {
        return 1;
    }
}

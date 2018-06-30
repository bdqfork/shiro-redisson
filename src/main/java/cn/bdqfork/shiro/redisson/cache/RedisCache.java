package cn.bdqfork.shiro.redisson.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.redisson.api.RMapCache;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author bdq
 * @since 2018-06-22
 */
public class RedisCache<K, V> implements Cache<K, V> {
    private RMapCache<K, V> rMapCache;
    private long expireTime;

    public RedisCache(RMapCache<K, V> rMapCache, long expireTime) {
        this.rMapCache = rMapCache;
        this.expireTime = expireTime;
    }

    @Override
    public V get(K k) throws CacheException {
        rMapCache.expire(expireTime, TimeUnit.SECONDS);
        return rMapCache.get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        rMapCache.expire(expireTime, TimeUnit.SECONDS);
        return rMapCache.put(k, v, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public V remove(K k) throws CacheException {
        rMapCache.expire(expireTime, TimeUnit.SECONDS);
        return rMapCache.remove(k);
    }

    @Override
    public void clear() throws CacheException {
        rMapCache.clear();
    }

    @Override
    public int size() {
        rMapCache.expire(expireTime, TimeUnit.SECONDS);
        return rMapCache.size();
    }

    @Override
    public Set<K> keys() {
        rMapCache.expire(expireTime, TimeUnit.SECONDS);
        return rMapCache.keySet();
    }

    @Override
    public Collection<V> values() {
        rMapCache.expire(expireTime, TimeUnit.SECONDS);
        return rMapCache.values();
    }
}

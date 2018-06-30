package cn.bdqfork.shiro.redisson.cache;

import cn.bdqfork.shiro.redisson.constant.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SerializationCodec;

/**
 * @author bdq
 * @since 2018-06-22
 */
@Slf4j
public class RedisCacheManager implements CacheManager {
    private RedissonClient redisson;
    private Long expireTime;

    public RedisCacheManager(RedissonClient redisson, Long expireTime) {
        this.redisson = redisson;
        this.expireTime = expireTime;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        log.trace("Acquiring EhCache instance named [{}]", name);
        RMapCache<K, V> rMapCache = redisson.getMapCache(Const.SHIRO_REDIS_CACHE_PREFIX + name, new SerializationCodec());
        return new RedisCache<>(rMapCache, expireTime);
    }
}

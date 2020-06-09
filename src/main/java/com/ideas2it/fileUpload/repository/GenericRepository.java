package com.ideas2it.fileUpload.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Set;

@Repository
public class GenericRepository {

    @Autowired
    private RedisConfig redisConfig;

    private RedisTemplate redisTemplate;

    private ValueOperations valueOperations;

    @PostConstruct
    public void init() {
        this.redisTemplate = redisConfig.redisTemplate();
        this.valueOperations = redisTemplate.opsForValue();
    }

    /**
     * To save the value for the key
     * @param key - is the key to be saved
     * @param value - is the value to be saved
     */
    public void save(String key, String value) {
        valueOperations.set(key, value);
    }

    /**
     *
     * To retrieved the value for the key
     * @param key - is the key to be retrieved
     * @return String - value of the key
     */
    public Object findById(String key){
        return valueOperations.get(key);
    }

    /**
     * To delete all the keys in the redis
     */
    public void deleteAll(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern+"*");
        for (String key : keys) {
            redisTemplate.delete(key);
        }
    }
}

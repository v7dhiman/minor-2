package com.example.minor.repositories;

import com.example.minor.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class MyUserCacheRepository
{
    private final String USER_KEY_PREFIX = "user::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void set(MyUser myUser)
    {
        String key = getKey(myUser.getUsername());
        redisTemplate.opsForValue().set(key, myUser, 10, TimeUnit.MINUTES);
    }
    public MyUser get(String username)
    {
        String key = getKey(username);
        return (MyUser) redisTemplate.opsForValue().get(key);
    }
    public String getKey(String username)
    {
        return USER_KEY_PREFIX + username;
    }
}

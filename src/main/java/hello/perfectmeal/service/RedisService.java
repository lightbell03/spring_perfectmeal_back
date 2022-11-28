package hello.perfectmeal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisTemplate<String, Object> blackListTemplate;

    public void setRefreshToken(String key, Object o, Long milliseconds){
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        redisTemplate.opsForValue().set("RefreshToken:" + key, o, milliseconds, TimeUnit.MILLISECONDS);
    }

    public Object getRefreshToken(String key){
        return redisTemplate.opsForValue().get("RefreshToken:" + key);
    }
}

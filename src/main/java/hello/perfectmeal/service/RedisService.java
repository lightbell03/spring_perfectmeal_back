package hello.perfectmeal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void setRefreshToken(String key, Object o, Long milliseconds){
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        redisTemplate.opsForValue().set("RefreshToken:" + key, o, milliseconds, TimeUnit.MILLISECONDS);
    }

    public Object getRefreshToken(String key){
        return redisTemplate.opsForValue().get("RefreshToken:" + key);
    }

    public void setBlackList(String key, Object o, Long milliseconds){
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        log.info("set blacklist");
        redisTemplate.opsForValue().set("BlackList:" + key, o, milliseconds, TimeUnit.MILLISECONDS);
        log.info("get blacklist = {}", redisTemplate.hasKey("BlackList:" + key));
    }

    public boolean hasKeyBlackList(String key){
        boolean isHasKey = redisTemplate.hasKey("BlackList:" + key);
        log.info("has blacklist = {}", isHasKey);

        return isHasKey;
    }
}

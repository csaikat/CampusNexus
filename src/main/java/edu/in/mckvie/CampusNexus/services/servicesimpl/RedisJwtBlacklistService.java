package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.services.JwtBlacklistService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import io.jsonwebtoken.Jwts;

@Service
public class RedisJwtBlacklistService implements JwtBlacklistService {

    private final Jedis jedis;
    @Value("${jwt.secret}")
    private String jwtSecret;
    private static final String BLACKLIST_KEY = "jwt-blacklist";

    public RedisJwtBlacklistService(@Value("${spring.redis.host}") String host,
                                    @Value("${spring.redis.port}") int port) {
        jedis = new Jedis(host, port);
    }

    @Override
    public void blacklistToken(String token,String username) {
        jedis.sadd(username, token);
        long ttl = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration().getTime()
                - System.currentTimeMillis();
        System.out.println("add");
//        jedis.setex(token, (int) (ttl / 1000), "true");
        jedis.expire(BLACKLIST_KEY, ttl);
    }

    @Override
    public boolean isTokenBlacklisted(String token,String username) {
        return jedis.exists(token);
    }

    @Override
    public void add(String token,String username) {
        System.out.println("add1");
        jedis.sadd(username, token);
    }

    @Override
    public boolean contains(String token,String username) {
        System.out.println("contain1");
        return jedis.sismember(username, token);
    }

//    @Override
//    public void remove(String token) {
//        jedis.srem(BLACKLIST_KEY, token);
//    }
//
//    @Override
//    public void clear() {
//        jedis.del(BLACKLIST_KEY);
//    }
}


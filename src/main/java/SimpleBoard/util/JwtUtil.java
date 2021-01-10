package SimpleBoard.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtil{
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    public String createToken(long id){
        HashMap<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return Jwts.builder()
                .setHeader(header)
                .claim("id", id)
                .setExpiration(dateTimeUtil.addOneDay(new Date()))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public long getIdByToken(String token){
        token = token.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token).getBody();
        return claims.get("id", Long.class);
    }

    public Date getExpireByToken(String token){
        token = token.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token).getBody();
        return claims.get("exp", Date.class);
    }
}
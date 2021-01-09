package SimpleBoard.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
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

        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", id);
        claims.put("exp", dateTimeUtil.addOneDay(new Date()));

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }
}

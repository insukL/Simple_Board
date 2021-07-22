package SimpleBoard.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtil{
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    private SecretKey key;

    // 생성자 이외의 초기화 메소드
    /*
    @PostConstruct
    public void JwtUtil(){
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
     */


    public String createToken(long id){
        HashMap<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return Jwts.builder()
                .setHeader(header)
                .claim("id", id)
                .setExpiration(dateTimeUtil.addOneDay(new Date()))
                .signWith(key)
                .compact();
    }

    public long getIdByToken(String token){
        token = token.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();
        return claims.get("id", Long.class);
    }

    public Date getExpireByToken(String token){
        token = token.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();
        return claims.get("exp", Date.class);
    }
}
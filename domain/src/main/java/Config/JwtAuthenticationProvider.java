package Config;

import Config.common.UserVO;
import Config.common.UserType;
import io.jsonwebtoken.*;
import util.Aes256Util;

import java.util.Date;

public class JwtAuthenticationProvider {
    private String secretKey = "secretKey";

    private long tokenValidTime = 1000 * 60 * 60 * 24;

    public String creatToken(String email, Long id, UserType userType){
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(email)).setId(Aes256Util.encrypt(id.toString()));
        claims.put("roles",userType);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claimsJwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claimsJwt.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    public UserVO getUserVo(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return new UserVO(Long.valueOf(Aes256Util.decrypt(claims.getId())),Aes256Util.decrypt(claims.getSubject()));
    }
}

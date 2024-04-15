package com.ThoughtTrove.securityPak;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenHelper {
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
    private  String secret = "jwtTOkenKeyISHereForThePurposeOf";

    //retrieve username from jwt Token
    public  String getUserNameFromToken(String token){
    return  getClaimFromToken(token , Claims::getSubject);
    }

    //retrieve Expiration date  from jwt Token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token , Function<Claims , T> claimsResolver){
        final  Claims claims = getAllCaimsFromsToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving the anyinformation from the token we will need the secret key
    private Claims getAllCaimsFromsToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    //checking the token is expired or not
    private  Boolean isTokenExpired(String token){
        final  Date expiration = getExpirationDateFromToken(token);
        return  expiration.before(new Date());
    }

    // generating token  for the user
    public String generateToken(UserDetails userDetails){
        Map<String , Object> claims = new HashMap<>();
        return doGenerateToken(claims , userDetails.getUsername());
    }
    public   String doGenerateToken(Map<String , Object> claims  , String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*10000))
                .signWith(SignatureAlgorithm.HS512 ,secret).compact();
    }
    public Boolean validateToken(String token  , UserDetails userDetails){
        final  String username = getUserNameFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}

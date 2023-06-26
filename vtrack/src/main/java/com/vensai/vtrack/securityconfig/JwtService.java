package com.vensai.vtrack.securityconfig;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	private static final String SECRET = "4A614E645267556B58703273357638792F423F4528482B4D6250655368566D59";

	public String generateToken(String userName) {
		
		Map<String,Object> claims = new HashMap<String, Object>();
		 String jwtToken = "Bearer".concat(createToken(claims,userName))  ;
		 System.out.println(jwtToken);
		return jwtToken;
		
	}

	private String createToken(Map<String, Object> claims, String userName) {
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(SignatureAlgorithm.HS256,getKey()).compact();
			
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String validateToken(String jwtToken) {

	    try {
	        Jws<Claims> claims = Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(jwtToken);

	        // Token is valid
	        String employeeEmail = claims.getBody().getSubject();
	        // Perform further validation or processing as needed
	        
	        return employeeEmail;
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        // Token is invalid or expired
	        // Handle the exception or throw an appropriate exception
	        return null;
	    }
	}

	public boolean validateToken(String jwt, UserDetails userDetails) {
		// TODO Auto-generated method stub
		if(validateToken(jwt) != null) {
			return true;
		}
		return false;
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		Date expirationDate = extractClaim(token, Claims::getExpiration);
		return expirationDate.before(new Date());
	}

}

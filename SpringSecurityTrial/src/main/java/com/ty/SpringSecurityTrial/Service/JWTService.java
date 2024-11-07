package com.ty.SpringSecurityTrial.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service

//GENRATE THE TOKEN FROM THIS CLASS....

public class JWTService {

	private String secretKey = ""; // secret key can be created using anything alphabet,numbers,special chars etc

	
// JWTService Constructor is used as for generate dynamic sky
//	we are dynamically generating the secret key which is a good way
// For learning process we can directly get key by default 
	
	public JWTService() { 
		try {
			KeyGenerator generator = KeyGenerator.getInstance("HmacShA256");
			SecretKey key = generator.generateKey();
			secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String generateToken(String username) {

		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder()
				.claims().add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 mins
				.and() // to use .signwith() we have to use .and() method first
				.signWith(getKey()) // out personal signed key
				.compact();

//		return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Im1pdCIsImlhdCI6MTUxNjIzOTAyMn0.brrz0nTr5JASxFjRbP92b5V4V4W1DC7bAtiGaXnD370";
	}

	private SecretKey getKey() {
		byte[] keybyte = Decoders.BASE64.decode(secretKey); // convert String into byte
		return Keys.hmacShaKeyFor(keybyte); // hmacShaKeyFor only take byte[] so we converted the string into byte type
	}

	  public String extractUserName(String token) {
	        // extract the username from jwt token
	        return extractClaim(token, Claims::getSubject);
	    }

	    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(getKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

	    public boolean validateToken(String token, UserDetails userDetails) {
	        final String userName = extractUserName(token);
	        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }


}

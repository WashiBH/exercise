package com.demo.users.core.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


/**
 * Clase que se encarga de la creación y validación de JWT
 * para el inicio de sesion de un usuario.
 */
@Service
public class JwtService {
  private static final String SECRET_KEY = "1444a752bb14e1469c8423990438e2131dcb5f55fc5969d2c2576d7c7166eceb";

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  /**
   * Este metodo es el encargado de generar el token.
   *
   * @param extraClaims Son los datos extra del usuario que se enviaran como parte del payload.
   * @param userDetails Detalles del usuario.
   * @return Cadena del token generado.
   */
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder().setClaims(extraClaims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
      .signWith(getSignKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public String getUserName(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaims(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(getSignKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUserName(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return getExpiration(token).before(new Date());
  }

  private Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
  }
}

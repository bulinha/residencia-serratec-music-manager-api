package br.org.serratec.mm.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${auth.jwt-secret}")
	private String jwtSecret;
	@Value("${auth.jwt-expiration-milliseg}")
	private Long jwtExpirationMilliseg;
	@Autowired
	private UsuarioDetalheService usuarioDetalheService;

	public String generateToken(String username) {
		UserDetails user = usuarioDetalheService.loadUserByUsername(username);
		String perfil = user.getAuthorities().stream().findFirst().get().getAuthority();
		return Jwts.builder().setSubject(username)
				.claim("perfil", perfil)
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMilliseg))
				.signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
				.compact();
	}

	public boolean isValidToken(String token) {
		Claims claims = getClaims(token);
		if (claims == null)
			return false;
		String userName = claims.getSubject();
		Date expirationDate = claims.getExpiration();
		Date now = new Date(System.currentTimeMillis());
		if (userName != null && now.before(expirationDate)) {
			return true;
		}
		return false;

	}

	public String getUserName(String token) {
		Claims claims = getClaims(token);
		if (claims!=null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {

		try {
			Claims claims = Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody();
			return claims;
		} catch (Exception e) {
			return null;
		}

	}

}

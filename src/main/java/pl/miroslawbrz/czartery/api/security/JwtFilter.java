package pl.miroslawbrz.czartery.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader("Authorization");
        UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);

    }


    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header){

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("aaa" .getBytes())
                .parseClaimsJws(header.replace("Bearer ", ""));

        String userName = claimsJws.getBody().get("userName").toString();
        String userPassword = claimsJws.getBody().get("userPassword").toString();

        return new UsernamePasswordAuthenticationToken(userName, userPassword);

    }
}

package pl.miroslawbrz.czartery.api.security;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.miroslawbrz.czartery.common.MsgSource;
import pl.miroslawbrz.czartery.exception.CommonBadRequestException;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtVerifier extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private MsgSource msgSource;

    public JwtVerifier(SecretKey secretKey, JwtConfig jwtConfig, MsgSource msgSource) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.msgSource = msgSource;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader(jwtConfig.getAuthorizationHeader());

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.tokenPrefix)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        try {

            String token = authorizationHeader.replace(jwtConfig.tokenPrefix, "");

            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();

            String username = body.getSubject();
            String password = body.get("password").toString();

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    password
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (JwtException e){
            throw new CommonBadRequestException(msgSource.ERR401);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

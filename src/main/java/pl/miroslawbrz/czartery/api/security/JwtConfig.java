package pl.miroslawbrz.czartery.api.security;


import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    public final String secretKey;
    public final String tokenPrefix;
    public final Integer tokenExpirationAfterDays;

    public JwtConfig(

            @Value("${application.jwt.secretKey}") String secretKey,
            @Value("${application.jwt.tokenPrefix}") String tokenPrefix,
            @Value("${application.jwt.tokenExpirationAfterDays}") Integer tokenExpirationAfterDays
    ){

        this.secretKey = secretKey;
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
        this.tokenPrefix = tokenPrefix;


    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

}

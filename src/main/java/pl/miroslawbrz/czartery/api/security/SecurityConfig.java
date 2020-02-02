package pl.miroslawbrz.czartery.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.miroslawbrz.czartery.common.MsgSource;

import javax.crypto.SecretKey;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomUserDetailService customUserDetailService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private MsgSource msgSource;

    public SecurityConfig(CustomUserDetailService customUserDetailService, SecretKey secretKey, JwtConfig jwtConfig, MsgSource msgSource) {
        this.customUserDetailService = customUserDetailService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.msgSource = msgSource;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtVerifier(secretKey, jwtConfig, msgSource),JwtFilter.class)
                .authorizeRequests()
                .antMatchers("/").authenticated();


    }

}

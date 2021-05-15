package com.smart.hotel.config;

import com.smart.hotel.config.properties.ApplicationProperties;
import com.smart.hotel.security.AuthoritiesConstants;
import com.smart.hotel.security.jwt.JWTConfigurer;
import com.smart.hotel.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import tech.jhipster.config.JHipsterProperties;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Order(1)
    @Configuration
    public static class ApiConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private SecurityProblemSupport problemSupport;

        @Autowired
        private ApplicationProperties applicationProperties;


        public ApiConfigurationAdapter() {
            super(true);
        }

        @Override
        public void configure(WebSecurity web) {
            web
                    .ignoring()
                    .antMatchers(HttpMethod.OPTIONS, "/**")
                    .antMatchers("/h2-console/**")
                    .antMatchers("/swagger-ui/**")
                    .antMatchers("/test/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            APIKeyAuthFilter filter = new APIKeyAuthFilter();
            filter.setAuthenticationManager(authentication -> {
                String principal = (String) authentication.getPrincipal();
                if (!applicationProperties.getApiKey().equals(principal)) {
                    throw new BadCredentialsException("The API key was not found or not the expected value.");
                }
                authentication.setAuthenticated(true);
                return authentication;
            });

            http
                    .requestMatchers()
                    .antMatchers("/api/internal/**")
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(problemSupport)
                    .accessDeniedHandler(problemSupport)
                    .and()
                    .addFilterBefore(filter, ChannelProcessingFilter.class)
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }
    }

    @Order(2)
    @Configuration
    @RequiredArgsConstructor
    public static class UserAuthenticationAdapter extends WebSecurityConfigurerAdapter {

        private final JHipsterProperties jHipsterProperties;

        private final TokenProvider tokenProvider;

        private final CorsFilter corsFilter;

        private final SecurityProblemSupport problemSupport;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http
                    .csrf()
                    .disable()
                    .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling()
                    .authenticationEntryPoint(problemSupport)
                    .accessDeniedHandler(problemSupport)
                    .and()
                    .headers()
                    .contentSecurityPolicy(jHipsterProperties.getSecurity().getContentSecurityPolicy())
                    .and()
                    .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                    .and()
                    .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; fullscreen 'self'; payment 'none'")
                    .and()
                    .frameOptions()
                    .deny()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers("/api/activate").permitAll()
                    .antMatchers("/api/account/reset-password/init").permitAll()
                    .antMatchers("/api/account/reset-password/finish").permitAll()
                    .antMatchers("/api/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/management/health").permitAll()
                    .antMatchers("/management/health/**").permitAll()
                    .antMatchers("/management/info").permitAll()
                    .antMatchers("/management/prometheus").permitAll()
                    .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
                    .and()
                    .httpBasic()
                    .and()
                    .apply(securityConfigurerAdapter());
            // @formatter:on
        }

        private JWTConfigurer securityConfigurerAdapter() {
            return new JWTConfigurer(tokenProvider);
        }
    }
}

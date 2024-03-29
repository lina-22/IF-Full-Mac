package com.serverSide.javaSpringBoot.config;


import com.serverSide.javaSpringBoot.config.filter.CorsFilter;
import com.serverSide.javaSpringBoot.config.filter.CustomAuthenticationProvider;
import com.serverSide.javaSpringBoot.config.filter.JwtUsernamePasswordAuthenticationFilter;
import com.serverSide.javaSpringBoot.exception.CustomAccessDeniedHandler;
import com.serverSide.javaSpringBoot.jwt.JwtConfig;
import com.serverSide.javaSpringBoot.jwt.JwtService;
import com.serverSide.javaSpringBoot.services.securityService.AppUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    private JwtService jwtService;

    @Bean
    public JwtConfig jwtConfig(){
        return new JwtConfig();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new AppUserDetailsService();
    }

    @Autowired
    public void configGlobal(final AuthenticationManagerBuilder auth){
        auth.authenticationProvider(customAuthenticationProvider);
    }

 private final String[] whiteListGetOnly = {"products/*/*", "/*"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

        AuthenticationManager manager = builder.build();

        http
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .csrf().disable()
                .formLogin().disable()
                .authorizeHttpRequests()
                .requestMatchers("/*").permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/sizes", "/sizes/*").hasAuthority("ADMIN")
                .requestMatchers("/users/register").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/products").permitAll()

                //.requestMatchers("/**").permitAll()
                //.requestMatchers("/guest/**").permitAll()
                /*.requestMatchers(   "/v2/api-docs",
                        "/v3/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui/**").permitAll()*/

                //.requestMatchers("/user/**").hasAuthority("USER")
                //.requestMatchers(HttpMethod.GET, whiteListGetOnly).permitAll()
             /*   .requestMatchers("/roles/**").hasAuthority("ADMIN")
                .requestMatchers("/categories/**").hasAuthority("ADMIN")
                .requestMatchers("/colours/**").hasAuthority("ADMIN")
                .requestMatchers("/materials/**").hasAuthority("ADMIN")
                .requestMatchers("/suplliers/**").hasAuthority("ADMIN")
                .requestMatchers("/sizes/**").hasAuthority("ADMIN")
                .requestMatchers("/materials/**").hasAuthority("ADMIN")
                .requestMatchers("/materials/**").hasAuthority("ADMIN")
                .requestMatchers("/materials/**").hasAuthority("ADMIN")*/
                .anyRequest().authenticated()
                .and()
                .authenticationManager(manager)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        ((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                )
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .addFilterBefore(new JwtUsernamePasswordAuthenticationFilter(manager, jwtConfig, jwtService), UsernamePasswordAuthenticationFilter.class);
                //.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig, jwtService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

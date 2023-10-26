package br.com.appetitegourmet.api.spring.login.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import br.com.appetitegourmet.api.spring.login.security.jwt.AuthEntryPointJwt;
import br.com.appetitegourmet.api.spring.login.security.jwt.AuthTokenFilter;
import br.com.appetitegourmet.api.spring.login.security.services.UserDetailsServiceImpl;

import org.springframework.web.cors.CorsConfiguration;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity(
securedEnabled = true,
jsr250Enabled = true,
prePostEnabled = true) 
public class WebSecurityConfig {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	  
	  CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
      corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200", "https://nice-beach-01dafa610.3.azurestaticapps.net"));
      corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
      corsConfiguration.setAllowCredentials(true);
      corsConfiguration.setExposedHeaders(List.of("Authorization"));
	  
	  System.out.println("SecurityFilterChain - 1");
    http.csrf(AbstractHttpConfigurer::disable)
    	.cors(Customizer.withDefaults())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> 
          auth.requestMatchers("/api/auth/**").permitAll()
              .requestMatchers("/api/test/**").permitAll()
              .requestMatchers("/responsaveis/enviarEmail/**").permitAll()
              .requestMatchers("/responsaveis/consultaCpf/**").permitAll()
              .requestMatchers("/responsaveis/consultaEmail/**").permitAll()
              .requestMatchers("/utils/consultaEndereco/**").permitAll()
              .requestMatchers("/associacaoUsuarios/**").permitAll()
              .anyRequest().authenticated()
        );
    System.out.println("SecurityFilterChain - 2");
    http.authenticationProvider(authenticationProvider());
    System.out.println("SecurityFilterChain - 3");
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    System.out.println("SecurityFilterChain - 4");
    return http.build();
  }
  
  /*
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
  	CorsConfiguration configuration = new CorsConfiguration();
  	configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
  	configuration.setAllowedMethods(Arrays.asList("GET","POST"));
  	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  	source.registerCorsConfiguration("/**", configuration);
  	return source;
  }
  */
}


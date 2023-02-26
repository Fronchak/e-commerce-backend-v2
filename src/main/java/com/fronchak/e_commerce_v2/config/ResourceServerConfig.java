package com.fronchak.e_commerce_v2.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**", "/users/**" };
	
	private static final String[] ENTITIES = { "/animes/**", "/categories/**" };
	
	private static final String[] MOVIES = { "/movies/**" };
	
	private static final String[] ADMIN = { "/users/**" };
 	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// H2
		if(isTestEnvironment()) {
			http.headers().frameOptions().disable();
		}
		
		
		http.authorizeHttpRequests()
			.requestMatchers(PUBLIC).permitAll()
			.requestMatchers(HttpMethod.GET, "/categories/all").permitAll()
			.requestMatchers(HttpMethod.GET, "/animes/{id}").authenticated()
			.requestMatchers(HttpMethod.GET, "/categories/{id}").authenticated()
			.requestMatchers(HttpMethod.GET, ENTITIES).permitAll()
			.requestMatchers(HttpMethod.POST, ENTITIES).hasAnyRole("WORKER", "ADMIN")
			.requestMatchers(HttpMethod.PUT, ENTITIES).hasAnyRole("WORKER", "ADMIN")
			.requestMatchers(HttpMethod.DELETE, ENTITIES).hasRole("ADMIN")
			.anyRequest().authenticated();
		
		http.cors().configurationSource(corsConfigurationSource());
	}

	private boolean isTestEnvironment() {
		return Arrays.asList(env.getActiveProfiles()).contains("test");
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOriginPatterns(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
	
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}

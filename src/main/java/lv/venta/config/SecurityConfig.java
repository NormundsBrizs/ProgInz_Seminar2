package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder getEncoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public UserDetailsManager getUserDerailsManager() {
		MyUserDetailsManager manager = new MyUserDetailsManager();
		return manager;
	}
	
	@Bean
	public DaoAuthenticationProvider setProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(getUserDerailsManager());
		dao.setPasswordEncoder(getEncoder());
		return dao;
	}
	
	@Bean
	public SecurityFilterChain httpPermissions(HttpSecurity http) {
		http.authorizeHttpRequests(
				auth->auth
				.requestMatchers("/student/crud/all").permitAll()
				.requestMatchers("/student/crud/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/filter/grade/student/**").hasAuthority("USER")
				.requestMatchers("/filter/grade/course/**").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/filter/course/professor/**").hasAuthority("USER")
				.requestMatchers("/filter/student/failed").hasAuthority("USER")
				.requestMatchers("/filter/perfect-grades").hasAuthority("USER"));
		http.formLogin(auth->auth.permitAll());
		
		return http.build();
	}
}

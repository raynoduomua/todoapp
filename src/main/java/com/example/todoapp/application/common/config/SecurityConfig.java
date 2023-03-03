package com.example.todoapp.application.common.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.todoapp.application.common.utils.Authority;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				// 「cssやjs、imagesなどの静的リソース」をアクセス可能にします
				.requestMatchers(PathRequest.toStaticResources()
						.atCommonLocations())
				.permitAll()
				.mvcMatchers("/register", "/loginForm").permitAll()
				.antMatchers("/h2-console/**").permitAll() // H2DBデバッグ用
				.mvcMatchers("/admin/**").hasAnyAuthority(Authority.ADMIN.name())
				.anyRequest().authenticated())
				.formLogin(login -> login
						.loginProcessingUrl("/login")
						.loginPage("/loginForm")
						.usernameParameter("email")
						.passwordParameter("password")
						.defaultSuccessUrl("/todo/home", true)
						.failureUrl("/loginForm?error"))
				// ログアウトの設定
				.logout(logout -> logout
						// ログアウト時のURLを指定
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.permitAll())
				// Remember-Meの認証を許可します
				// これを設定すると、ブラウザを閉じて、
				// 再度開いた場合でも「ログインしたまま」にできます
				.rememberMe();

		http.csrf().disable(); // H2DBデバッグ用
		http.headers().frameOptions().disable(); // H2DBデバッグ用

		return http.build();
	}

	//	@Bean
	//	public UserDetailsService userDetailsService() {
	//		return username -> {
	//			// ユーザ名を検索します（ユーザが存在しない場合は、例外をスローします）
	//			var user = userRepository.findByUsername(username)
	//					.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
	//
	//			// ユーザ情報を返します
	//			return new User(user.getUsername(), user.getPassword(),
	//					AuthorityUtils.createAuthorityList("ADMIN"));
	//		};
	//	}

}

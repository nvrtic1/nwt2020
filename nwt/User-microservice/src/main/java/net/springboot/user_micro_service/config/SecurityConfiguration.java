package net.springboot.user_micro_service.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.springboot.user_micro_service.service.UserService;
import net.springboot.user_microservice.component.SimpleAuthenticationSuccessHandler;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import shuaicj.example.security.common.JwtAuthenticationConfig;
import shuaicj.example.security.common.JwtTokenAuthenticationFilter;
import shuaicj.example.security.common.JwtUsernamePasswordAuthenticationFilter;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "net.springboot.user_microservice.component" })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    
    @Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

    @Autowired JwtAuthenticationConfig config;

    @Bean
    public JwtAuthenticationConfig jwtConfig() {
        return new JwtAuthenticationConfig();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//        		.csrf().disable()
//                .authorizeRequests()
//                    .antMatchers(
//                            "/registration**",
//                            "/js/**",
//                            "/css/**",
//                            "/img/**",
//                            "/webjars/**",
//                            "/").permitAll()
//                    .antMatchers("/userhome").hasRole("USER")
//    				.antMatchers("/admin/**").hasRole("ADMIN")
////    				.antMatchers("/edit","/update").authenticated()
//    				.and().formLogin().successHandler(successHandler)
//    				.loginPage("/login").and().logout().permitAll()
//                .and()
//                    .logout()
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/login?logout")
//                .permitAll();
//    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .anonymous()
                .and()
                    .exceptionHandling().authenticationEntryPoint(
                            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                    .addFilterAfter(new JwtUsernamePasswordAuthenticationFilter(config, authenticationManager()),
                            UsernamePasswordAuthenticationFilter.class)

                    .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                            UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

                    .antMatchers(config.getUrl()).permitAll()
                    .antMatchers("/index").permitAll()
                   .antMatchers("/admin").permitAll()
                   .antMatchers("/userhome").hasRole("USER")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//                    .anyRequest().authenticated();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}

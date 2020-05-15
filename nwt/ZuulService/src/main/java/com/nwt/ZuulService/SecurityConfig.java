package com.nwt.ZuulService;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shuaicj.example.security.common.JwtAuthenticationConfig;
import shuaicj.example.security.common.JwtTokenAuthenticationFilter;
import shuaicj.example.security.common.JwtUsernamePasswordAuthenticationFilter;

/**
 * Config role-based auth.
 *
 * @author shuaicj 2017/10/18
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationConfig config;

    @Bean
    public JwtAuthenticationConfig jwtConfig() {
        return new JwtAuthenticationConfig();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
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
//                    .addFilterAfter(new JwtUsernamePasswordAuthenticationFilter(config, authenticationManager()),
//                            UsernamePasswordAuthenticationFilter.class)

                    .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                            UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers(config.getUrl()).permitAll();
//                    .antMatchers("/admin").hasRole("ADMIN")
//                    .antMatchers("/userhome").hasRole("USER")
//                    .antMatchers("/index").permitAll();
    }
}



package com.louis.spring.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Autowired
    DataSource dataSource;
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        if (!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("123")).roles("admin").build());
        }
        if (!manager.userExists("user")) {
            manager.createUser(User.withUsername("user").password(passwordEncoder().encode("123")).roles("user").build());
        }
        if (!manager.userExists("user2")) {
            manager.createUser(User.withUsername("user2").password(passwordEncoder().encode("123")).roles("user").build());
        }
        if (!manager.userExists("user3")) {
            manager.createUser(User.withUsername("user3").password(passwordEncoder().encode("123")).roles("user").build());
        }
        return manager;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
	        .antMatchers("/login")
	        .antMatchers("/oauth/authorize")
	        .and()
	        .authorizeRequests()
	        .anyRequest().authenticated()
	        .and()
	        .formLogin().loginPage("/login").permitAll()	// 自定义登录页面，这里配置了 loginPage, 就会通过 LoginController 的 login 接口加载登录页面
	        .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// 配置用户名密码
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

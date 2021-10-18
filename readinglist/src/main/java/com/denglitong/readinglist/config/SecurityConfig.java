package com.denglitong.readinglist.config;

import com.denglitong.readinglist.repository.ReaderRepository;
import com.denglitong.readinglist.service.ReaderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * \@profile注解 要求激活对应的值时才能应用配置
 *
 * @author litong.deng@foxmail.com
 * @date 2021/10/13
 */
@Profile("dev")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ReaderRepository readerRepository;
    private ReaderDetailsService readerDetailsService;

    @Autowired
    public void setReaderRepository(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Autowired
    public void setReaderDetailsService(ReaderDetailsService readerDetailsService) {
        this.readerDetailsService = readerDetailsService;
    }

    @Bean
    public static NoOpPasswordEncoder noOpPasswordEncoder() {
        return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用CSRF，否则需要在请求体提供CSRF token
        // https://stackoverflow.com/questions/19468209/spring-security-403-error
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/").access("hasRole('READER')") // 要求登陆者有READER角色
                .antMatchers("/actuator").access("hasRole('ADMIN')") // 要求actuator端点需要ADMIN权限
                .antMatchers("/actuator/**").access("hasRole('ADMIN')") // 要求actuator端点需要ADMIN权限
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(noOpPasswordEncoder());
        auth.userDetailsService(readerDetailsService);
    }
}

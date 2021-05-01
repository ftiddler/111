package com.example.demo.auth.config;

import com.example.demo.auth.filter.JWTAuthenticationFilter;
import com.example.demo.auth.filter.JWTAuthorizationFilter;
import com.example.demo.auth.filter.MyLogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Autowired
    private UserDetailsService userDetailsService;

    private final MyLogoutFilter myLogoutFilter;

    public SecurityConfig(MyLogoutFilter myLogoutFilter) {
        this.myLogoutFilter = myLogoutFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 安全配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 跨域共享
        http.cors()
                .and()
                // 跨域伪造请求限制无效
                .csrf().disable()
                .authorizeRequests()
                //不需要通过登录验证就可以被访问的资源路径
                .antMatchers("/login").permitAll()
                // 其余资源任何人都需验证
                .anyRequest().authenticated()
                .and()
                // 添加登出拦截器
                .addFilterBefore(myLogoutFilter, LogoutFilter.class)
                // 添加JWT登录拦截器
                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // 添加JWT鉴权拦截器
                .addFilterBefore(new JWTAuthorizationFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                // 设置Session的创建策略为：Spring Security永不创建HttpSession 不使用HttpSession来获取SecurityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 异常处理
                .exceptionHandling()
                // 匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(new JWTAuthenticationEntryPoint());
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/v3/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/webjars/**");
    }
    /**
     * 跨域配置
     *
     * @return 基于URL的跨域配置信息
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 注册跨域配置
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
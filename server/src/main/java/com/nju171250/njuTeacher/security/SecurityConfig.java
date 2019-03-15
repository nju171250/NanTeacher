package com.nju171250.njuTeacher.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register","/search","/getCommentInfo", "/getTeacherInfo","/makeComment","/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
            .csrf().disable()  //CRSF禁用，因为不使用session
            .sessionManagement().disable()
            .formLogin().disable() //禁用form登录
            .cors()  //支持跨域
            .and()   //添加header设置，支持跨域和ajax请求
            .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin","*"),
                new Header("Access-Control-Expose-Headers","Authorization"))))
                .and() //拦截OPTIONS请求，直接返回header
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
                //添加登录filter
                .apply(new JsonLoginConfigurer<>()).loginSuccessHandler(new LoginSuccessHandler())
                .and()
                //添加token的filter
                .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(new JwtRefreshSuccessHandler()).permissiveRequestUrls("/logout")
                .and()
                //使用默认的logoutFilter
                .logout()
//              .logoutUrl("/logout")   //默认就是"/logout"
                .addLogoutHandler(new TokenClearLogoutHandler(userDetailsService()))  //logout时清除token
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()) //logout成功后返回200
                .and()
                .sessionManagement().disable();
    }

//    //配置provider
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider()).authenticationProvider(jwtAuthenticationProvider());
//    }

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider()).authenticationProvider(jwtAuthenticationProvider());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean("jwtAuthenticationProvider")
    protected AuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(new JwtUserDetailService());
    }

    @Bean("daoAuthenticationProvider")
    protected AuthenticationProvider daoAuthenticationProvider() throws Exception{
        //这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(userDetailsService());
        return daoProvider;
    }

    public UserDetailsService userDetailsService(){
        return new JwtUserDetailService();
    }
//    @Autowired
//    private CustomAuthenticationProvider authProvider;
//
//    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
//
//    public SecurityConfig(){
//        this.usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.authenticationProvider(authProvider);
//    }
//
////    @Override
////    public void  configure(WebSecurity web) throws Exception {
////        web.f
////    }
//
//     @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // 设置Filter使用的AuthenticationManager
//        usernamePasswordAuthenticationFilter.setAuthenticationManager(authProvider);
//         //设置失败的Handler
//        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
//         //不将认证后的context放入session
//        usernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
//        // 设置成功的Handler
//        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
//        http.addFilter(usernamePasswordAuthenticationFilter);
//    }
}

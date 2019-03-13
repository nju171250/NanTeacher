//package com.nju171250.njuTeacher.security;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private RequestHeaderRequestMatcher requiresAuthenticationRequestMatcher;
//
//    public JwtAuthenticationFilter() {
//        //拦截header中带Authorization的请求
//        this.requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher("Authorization");
//    }
//
//    protected String getJwtToken(HttpServletRequest request) {
//        String authInfo = request.getHeader("Authorization");
//        // StringUtils.re(authInfo, "Bearer ");
//        return authInfo.replace("Bearer ", "");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        //header没带token的，直接放过，因为部分url匿名用户也可以访问
//        //如果需要不支持匿名用户的请求没带token，这里放过也没问题，因为SecurityContext中没有认证信息，后面会被权限控制模块拦截
//        if (!requiresAuthentication(request, response)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        Authentication authResult = null;
//        AuthenticationException failed = null;
//        try {
//            //从头中获取token并封装后提交给AuthenticationManager
//            String token = getJwtToken(request);
//            if(!StringUtils.isEmpty(token)) {
//                JwtAuthenticationToken authToken = new JwtAuthenticationToken(JWT.decode(token));
//                authResult = this.getAuthenticationManager().authenticate(authToken);
//            } else {  //如果token长度为0
//                failed = new InsufficientAuthenticationException("JWT is Empty");
//            }
//        } catch(JWTDecodeException e) {
//            logger.error("JWT format error", e);
//            failed = new InsufficientAuthenticationException("JWT format error", failed);
//        }catch (InternalAuthenticationServiceException e) {
//            logger.error(
//                    "An internal error occurred while trying to authenticate the user.",
//                    failed);
//            failed = e;
//        }catch (AuthenticationException e) {
//            // Authentication failed
//            failed = e;
//        }
//        if(authResult != null) {   //token认证成功
//            successfulAuthentication(request, response, filterChain, authResult);
//        } else if(!permissiveRequest(request)){
//            //token认证失败，并且这个request不在例外列表里，才会返回错误
//            unsuccessfulAuthentication(request, response, failed);
//            return;
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    protected boolean requiresAuthentication(HttpServletRequest request,
//                                             HttpServletResponse response) {
//        return requiresAuthenticationRequestMatcher.matches(request);
//    }
//
//    protected boolean permissiveRequest(HttpServletRequest request) {
//        if(permissiveRequestMatchers == null)
//            return false;
//        for(RequestMatcher permissiveMatcher : permissiveRequestMatchers) {
//            if(permissiveMatcher.matches(request))
//                return true;
//        }
//        return false;
//    }
//}

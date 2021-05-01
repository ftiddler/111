package com.example.demo.auth.filter;

import com.example.demo.auth.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 登录成功后 走此类进行鉴权操作
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 在过滤之前和之后执行的事件
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtil.TOKEN_HEADER);

        // 若请求头中没有Authorization信息 或是Authorization不以Bearer开头 则直接报错
        logger.info("执行" + JWTAuthorizationFilter.class );
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX))
        {
//            chain.doFilter(request, response);
            throw new BadCredentialsException("用户未登录");
        }

        // 若请求头中有token 则调用下面的方法进行解析 并设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    /**
     * 从token中获取用户信息并新建一个token
     *
     * @param tokenHeader 字符串形式的Token请求头
     * @return 带用户名和密码以及权限的Authentication
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        // 去掉前缀 获取Token字符串
        String token = tokenHeader.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        // 从Token中解密获取用户名
        String username = JwtTokenUtil.getUsername(token);
        // 从Token中解密获取用户角色
        String permissions = JwtTokenUtil.getPermissions(token);

        String[] permissions1 = StringUtils.strip(permissions, "[]").split(", ");
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (String s:permissions1)
        {
            authorities.add(new SimpleGrantedAuthority(s));
        }
        if (username != null)
        {
            logger.info("权限：" + authorities);
            return new UsernamePasswordAuthenticationToken(username, null,authorities);
        }
        return null;
    }
}

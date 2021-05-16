package io.medicorum.auth.configuration;

import io.jsonwebtoken.Claims;
import io.medicorum.auth.models.MedicorumUserDetails;
import io.medicorum.auth.services.JwtTokenProvider;
import io.medicorum.auth.services.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfiguration jwtConfiguration;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private String serviceUsername;

    @Autowired
    public  JwtTokenAuthenticationFilter(JwtConfiguration jwtConfiguration, JwtTokenProvider tokenProvider, UserService userService, String serviceUsername) {
        this.jwtConfiguration = jwtConfiguration;
        this.jwtTokenProvider = tokenProvider;
        this.userService = userService;
        this.serviceUsername = serviceUsername;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader(jwtConfiguration.getHeader());
        if(StringUtils.isEmpty(requestHeader) || !StringUtils.startsWith(requestHeader,jwtConfiguration.getPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = requestHeader.replace(jwtConfiguration.getPrefix(), "");
        if(JwtTokenProvider.validateToken(token)) {

            Claims claims = jwtTokenProvider.getClaimsFromJWT(token);
            String username = claims.getSubject();

            UsernamePasswordAuthenticationToken auth = null;

            if(username.equals(serviceUsername)) {

                List<String> authorities = (List<String>) claims.get("authorities");

                auth = new UsernamePasswordAuthenticationToken(username, null,
                        authorities
                                .stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(toList()));

            } else {

                auth = userService
                        .findByUsername(username)
                        .map(MedicorumUserDetails::new)
                        .map(userDetails -> {

                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails, null, userDetails.getAuthorities());
                            authentication
                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                            return authentication;
                        })
                        .orElse(null);
            }

            SecurityContextHolder.getContext().setAuthentication(auth);

        } else {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}

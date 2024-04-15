package com.ThoughtTrove.securityPak;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@Slf4j
public class JwtAuthenticiationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {



        //  1 . Get token

        String requestToken = request.getHeader("Authorization");
        log.info("Header :  {}", requestToken);

//Bearer 324354357
        String username = null;
        String token  = null;
        if (request!= null && requestToken.startsWith("Bearer")){
           token=requestToken.substring(7);
           try {
               username = jwtTokenHelper.getUserNameFromToken(token);
           } catch (IllegalArgumentException e){
               System.out.println("Unable to access token");
           } catch (ExpiredJwtException e){
               System.out.println("Token Has been Expired ");
           } catch (MalformedJwtException e){
               System.out.println("invalid jwt");
           }

        }
        else {
            System.out.println("jwtToken Does'nt begin with Bearer ");
        }
        //token once we get -> validation process
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtTokenHelper.validateToken(token , userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                System.out.println("Invalid token");
            }
        }
        else {
            System.out.println(" UserName is null and context may be null");
        }
        filterChain.doFilter(request , response);
    }
}

package com.coding.visit.security;

import com.coding.visit.services.impl.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil ;

    @Autowired
    CustomUserDetailsService userDetailsService  ;

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class) ;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader=  request.getHeader("Authorization") ;
        String username = null ;
        String jwtToken = null ;
        if(requestHeader !=null && requestHeader.startsWith("Bearer ")){
            jwtToken = requestHeader.substring(7);
            try{
                username = jwtUtil.getUsernameFromToken(jwtToken) ;
            }catch (IllegalArgumentException e){
                logger.error("illegal argument exception while fetching the username from token {}",e.getMessage());
                e.printStackTrace();
            }
            catch (ExpiredJwtException e){
                logger.error("expire token  while fetching the username from token {}",e.getMessage());
                e.printStackTrace();
            }
            catch (MalformedJwtException e){
                logger.error("Malformed exception while fetching the username from token {}",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                logger.error("exception occur when fetching the user from token : {}",e.getMessage());
                e.printStackTrace();
            }
        }
        else{
            logger.error("Header is invalid ");
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication() ==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username) ;
            if(jwtUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null, userDetails.getAuthorities()) ;
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                logger.error("validation fails !! ");
            }
        }

        filterChain.doFilter(request,response);
    }
}

package com.gibatekpro.jwtauthenticationapp.authentication.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final Logger logger = Logger.getLogger(getClass().getName());

    private static final String TAG = "JwtAuthFilter " + " >>>>  ";


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        //Jwt is passed within this Authorization header
        final String requestHeader = request.getHeader("Authorization");

        //This will be the jwtToken
        final String jwtToken;

        //This will be the Username/Email
        final String userEmail;

        if (requestHeader == null
                || !requestHeader.startsWith("Bearer ")) {

            //If there is no Authorization header or
            //If the header does not start with "Bearer",
            //It is not Jwt, move on to next filter
            filterChain.doFilter(request, response);
        }
        //extract the Jwt token from the Authorization header
        assert requestHeader != null;
        jwtToken = requestHeader.substring(7);


        //Now extract the Username/Email from the token
        userEmail = jwtService.extractUserEmailFromToken(jwtToken);

        //If the username/Email from the jwt token is not null
        // and the user is not already authenticated, try to authenticate the user
        if (userEmail != null
                &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            //Use the email in the token to extract the userDetails
            UserDetails userDetails = this.userDetailsService
                    .loadUserByUsername(userEmail);

            //Having a token does not mean the person is authenticated
            //So we will use the person's current token to authenticate the person
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                {
                    //If the token is valid, authenticate the user to access the resources
                    //But first, generate authentication token
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()

                    );

                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    logger.info(TAG + "doFilterInternal There is token, Not Authenticated" + authenticationToken);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);


                }
            }

        }

        filterChain.doFilter(request, response); // Don't forget

    }


}

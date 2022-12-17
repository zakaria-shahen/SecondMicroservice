package com.mycompany.secondmicroservice.filter;

import com.mycompany.secondmicroservice.context.CustomUserContext;
import com.mycompany.secondmicroservice.context.CustomUserContextFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddHeadersResponseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomUserContext context = CustomUserContextFactory.getUserContext().get();
        if (context != null) {
            String token = context.getAuthToken();
            String correlationId = context.getCorrelationId();
            String userId = context.getUserId();

            response.addHeader(CustomUserContext.CORRELATION_ID, correlationId);
            response.addHeader(CustomUserContext.AUTH_TOKEN, token);
            response.addHeader(CustomUserContext.USER_ID, userId);
        } else {

            System.out.println("\n\n\n\n\n\ncom.mycompany.secondmicroservice.filter.AddHeadersResponseFilter: Context is null \n\n\n\n\n");
        }

        filterChain.doFilter(request, response);

    }
}

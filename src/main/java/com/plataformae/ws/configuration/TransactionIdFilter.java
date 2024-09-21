package com.plataformae.ws.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class TransactionIdFilter implements Filter {

    private static final String TRANSACTION_ID = "transactionId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String transactionId = UUID.randomUUID().toString();
        ThreadContext.put(TRANSACTION_ID, transactionId);

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(TRANSACTION_ID, transactionId);

        try {
            chain.doFilter(request, response);
        } finally {
            ThreadContext.remove(TRANSACTION_ID);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización si es necesario
    }

    @Override
    public void destroy() {
        // Limpiar recursos si es necesario
    }
}
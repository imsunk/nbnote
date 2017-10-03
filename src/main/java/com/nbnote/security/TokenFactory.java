package com.nbnote.security;

import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;

/**
 * Created by K on 2017. 6. 18..
 */
public class TokenFactory implements Factory<Token> {

        private final HttpHeaders headers;
        private TokenService tokenSvc = new TokenService();

        @Inject
        public TokenFactory(HttpHeaders headers) {
            this.headers = headers;
        }

        @Override
        public Token provide() {
            String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer".length()).trim();
            try {
                return tokenSvc.getToken(token);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void dispose(Token t) { /* Do nothing */ }
}

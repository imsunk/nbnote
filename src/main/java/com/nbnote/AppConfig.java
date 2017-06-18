package com.nbnote;

import com.nbnote.auth.Token;
import com.nbnote.auth.TokenFactory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.process.internal.RequestScoped;
/**
 * Created by K on 2017. 6. 17..
 */
public class AppConfig extends ResourceConfig{
    public AppConfig() {
        register(new AbstractBinder(){
            @Override
            public void configure() {
                bindFactory(TokenFactory.class).to(Token.class).in(RequestScoped.class);
            }
        });

        packages("com.nbnote");

    }
}

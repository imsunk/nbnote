package com.nbnote;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.process.internal.RequestScoped;
/**
 * Created by K on 2017. 6. 17..
 */
import org.glassfish.jersey.server.ResourceConfig;
import com.nbnote.AuthenticationFilter;

/**
 *  set the filter applications manually and not via web.xml
 */
public class RestApplicationConfig extends ResourceConfig {
    public RestApplicationConfig() {
        packages( "com.note" );
        register( AuthenticationFilter.class );
    }
}

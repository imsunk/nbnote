package com.nbnote;

import org.glassfish.jersey.server.ResourceConfig;
/**
 * Created by K on 2017. 6. 17..
 */

import com.nbnote.filter.AuthenticationFilter;

/**
 *  set the filter applications manually and not via web.xml
 */
public class RestApplicationConfig extends ResourceConfig {

    public RestApplicationConfig() {
        packages( "com.nbnote.rest.filter" );
        register( AuthenticationFilter.class );
    }
}

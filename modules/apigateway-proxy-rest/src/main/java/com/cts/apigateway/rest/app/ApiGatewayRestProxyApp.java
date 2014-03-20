package com.cts.apigateway.rest.app;

import com.cts.apigateway.rest.resource.ApiGatewayRestProxyResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by cts1 on 20/3/14.
 */
public class ApiGatewayRestProxyApp extends ResourceConfig {

    public ApiGatewayRestProxyApp(){
        packages(ApiGatewayRestProxyResource.class.getPackage().toString());
        register(JacksonFeature.class);
    }
}

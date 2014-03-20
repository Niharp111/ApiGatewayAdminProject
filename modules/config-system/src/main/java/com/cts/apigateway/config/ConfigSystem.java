package com.cts.apigateway.config;

import com.cts.apigateway.config.exception.ConfigSystemException;

/**
 * Created by cts1 on 20/3/14.
 */
public interface ConfigSystem {

    public String getApiGateWayServiceURL() throws ConfigSystemException;
}

package com.cts.apigateway.config;

import com.cts.apigateway.config.exception.ConfigSystemException;

import static com.cts.apigateway.config.constants.Constants.CONNECTION_PROPERTIES_FILE;
import static com.cts.apigateway.config.constants.Constants.API_GATEWAY_SERVICE_URL;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Created by cts1 on 20/3/14.
 */
public class ConfigSystemImpl implements ConfigSystem {

    private Logger logger = Logger.getLogger(ConfigSystemImpl.class);

    @Override
    public String getApiGateWayServiceURL() throws ConfigSystemException {
        return getProperties().getProperty(API_GATEWAY_SERVICE_URL);
    }

    private Properties getProperties() throws ConfigSystemException {
        Properties properties = new Properties();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream
                (new FileInputStream(CONNECTION_PROPERTIES_FILE))) {
            properties.load(bufferedInputStream);
        } catch (FileNotFoundException e) {
           logger.error("connection.properties file not found",e);
           throw new ConfigSystemException("connection.properties file Not Found",e);
        } catch (IOException e) {
            logger.error("io error while reading the file",e);
            throw new ConfigSystemException("io error while reading the file ",e);
        }

        return properties;
    }
}

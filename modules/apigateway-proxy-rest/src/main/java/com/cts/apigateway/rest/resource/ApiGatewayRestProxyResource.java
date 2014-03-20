package com.cts.apigateway.rest.resource;

/**
 * Created by cts1 on 20/3/14.
 */

import com.cts.apigateway.config.ConfigSystem;
import com.cts.apigateway.config.exception.ConfigSystemException;
import com.cts.apigateway.rest.AuthObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


@Component
@Path("/")
public class ApiGatewayRestProxyResource {

    private Logger logger = Logger.getLogger(ApiGatewayRestProxyResource.class);

    private static final String AUTHORZATION = "Authorization";

    private static final String AUTH_HEADER = "authHeader";

    private ConfigSystem configSystem;


    @Context
    private HttpServletRequest httpServletRequest;

    @Path("/login")
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(AuthObject authObject){

        String auth = authObject.getUsername() + ":" + authObject.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute(AUTH_HEADER,authHeader);
        return "Login Successfull";

    }


    @Path("/getClient")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getClient(){
        String message = null;
         String resourcePath = "client";
        HttpSession httpSession = httpServletRequest.getSession();
        String basicAuth = (String) httpSession.getAttribute(AUTH_HEADER);
        logger.info("Session "+basicAuth);
        //String basicAuth = "Basic YWRtaW46YWRtaW4=";

        Response response = null;
        try {
            response = getInvocationBuilder(resourcePath,basicAuth).accept(MediaType.APPLICATION_JSON).get();
        } catch (ConfigSystemException e) {
            message = "Error occured while geting url of service";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        if(response.getStatus() == HttpStatus.OK_200.getStatusCode()){
            message= response.readEntity(String.class);
         }else{
            message= "Error occurred while getting client info";
         }
        return message;
    }




    @Path("/getProvider")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProvider(){

        String message = null;
        String resourcePath = "provider";
        HttpSession httpSession = httpServletRequest.getSession();
        String basicAuth = (String) httpSession.getAttribute(AUTH_HEADER);
        logger.info("Session "+basicAuth);
        //String basicAuth = "Basic YWRtaW46YWRtaW4=";

        Response response = null;
        try {
            response = getInvocationBuilder(resourcePath,basicAuth).accept(MediaType.APPLICATION_JSON).get();
        } catch (ConfigSystemException e) {
            message = "Error occured while geting url of service";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        if(response.getStatus() == HttpStatus.OK_200.getStatusCode()){
            message= response.readEntity(String.class);
        }else{
            message= "Error occurred while getting client info";
        }
        return message;
    }


    private Invocation.Builder getInvocationBuilder(String resourcePath,String basicAuth) throws ConfigSystemException, KeyManagementException, NoSuchAlgorithmException {
        Client client = ClientBuilder.newBuilder().hostnameVerifier(getHostnameVerifier()).sslContext(getSSLContext()).build();
        logger.info("URL is " + getConfigSystem().getApiGateWayServiceURL());
        WebTarget webTarget  = client.target(getConfigSystem().getApiGateWayServiceURL()).path(resourcePath);
        Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.header(AUTHORZATION,basicAuth);

        return invocationBuilder;
    }

    private SSLContext getSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers()
                    {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        return sc;
    }

    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        };
    }

    public ConfigSystem getConfigSystem() {
        return configSystem;
    }

    public void setConfigSystem(ConfigSystem configSystem) {
        this.configSystem = configSystem;
    }
}

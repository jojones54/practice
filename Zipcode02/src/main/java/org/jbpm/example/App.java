package org.jbpm.example;

import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
//import javax.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
    	System.out.println( "Hello jBPM!" );
    	
    	String zipcode = args[0];
        String targetUrl = String.format("http://ziptasticapi.com/%s", zipcode);
        
        // There are different RestEasy client classes 
        
        // technique 1. import org.jboss.resteasy.client.ClientRequest;
        // http://www.mastertheboss.com/resteasy/resteasy-client-api-tutorial
		ClientRequest cr = new ClientRequest(targetUrl);
		String result = cr.get(String.class).getEntity();
		System.out.println(result);
		cr.clear();
        
        // technique 2. import org.jboss.resteasy.client.jaxrs.ResteasyClient;

        ResteasyClient client = new ResteasyClientBuilder().build(); 
        ResteasyWebTarget target = client.target(targetUrl); 
        Response response = target.request().accept("application/json").get(); 
        if (response.getStatus() != 200) { 
               throw new RuntimeException("Failed : HTTP error code : " 
                       + response.getStatus()); 
        } 
        System.out.println("Server response : \n"); 
        System.out.println(response.readEntity(String.class));
        response.close(); 
  
		
		
		
		
		
		
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
// 
//		String output;
//		System.out.println("Output from Server .... \n");
//		while ((output = br.readLine()) != null) {
//			System.out.println(output);
//		}
// 
//        response.close();  // You should close connections!
	  } catch (ClientProtocolException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  } catch (Exception e) {
 
		e.printStackTrace();
 
	  }
    }
}

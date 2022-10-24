package me.elaamiri;

import jakarta.xml.ws.Endpoint;
import me.elaamiri.webservice.BankWebservice;

public class ServerJWS {
    // The webservice is accessible only in local
    private static String SERVER_ADDRESS_LOCAL = "http://localhost:9292/";
    // The webservice accessible from any address (From Other address )
    private static String SERVER_ADDRESS = "http://0.0.0.0:9292/";
    public static void main(String[] args) {
        Endpoint.publish(SERVER_ADDRESS, new BankWebservice());
        System.out.println(String.format("Web service deployed on [%s]",SERVER_ADDRESS));

    }
}
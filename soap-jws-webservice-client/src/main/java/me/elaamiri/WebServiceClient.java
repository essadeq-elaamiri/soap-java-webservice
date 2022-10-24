package me.elaamiri;

import me.elaamiri.proxy.BankWS;
import me.elaamiri.proxy.BankWebservice;

public class WebServiceClient {
    public static void main(String[] args) {

        // creating a middleware
        // proxy côté client s'appel ((STUB))
        BankWebservice stub = new BankWS().getBankWebservicePort();

        System.out.println(stub.covert(18755));
        System.out.println(stub.getAccount(414));
        System.out.println(stub.getAccounts());



        System.out.println("Client ... ");
    }
}
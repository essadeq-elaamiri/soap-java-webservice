package me.elaamiri.webservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.Date;
import java.util.List;


// this class is named POJO : Plain Old Java Object
@WebService(serviceName = "BankWS")
public class BankWebservice {
    @WebMethod(operationName = "covert")
    public double conversion(@WebParam(name = "amount") double amount){
        return amount * 10.42;
    }

    @WebMethod(operationName = "getAccount")
    public Account getAccount(@WebParam(name = "code") int code){
        return new Account(code, Math.random()*50042, new Date());
    }

    @WebMethod
    public List<Account> getAccounts(){
        return List.of(
                new Account(1, Math.random()*50042, new Date()),
                new Account(2, Math.random()*50042, new Date()),
                new Account(3, Math.random()*50042, new Date())
        );
    }

}

# SOAP Webservice

**Content**
1. [basics](#)
2. [Demo (Creating the Webservice)](#demo)
    1. [Dependency](#dependencies)
    2. [Webservice](#webservice)
    3. [Server](#server)
    4. [Accessing the WSDL](#accessing-the-wsdl)
    5. [Accessing the XSD schema](#accessing-the-xsd-schema)
    6. [Testing the Webservice using WSDL and Oxygen editor](#testing-the-webservice-using-wsdl-and-oxygen-editor)
    7. [Java Webservice Client (Proxy Soap) Generating](#java-webservice-client-proxy-soap)
3. [Java Webservice Client (Proxy Soap) Client creation](#java-webservice-client-proxy-soap-client-creation)
4. [PHP Webservice Client (Proxy Soap) Client creation](#php-webservice-client-proxy-soap-client-creation)

---------------------

## soap-java-webservice

![1](./src_s/1.PNG)

![1](./src_s/2.PNG)

--------------

## DEMO
### Creating the Server

#### Dependencies 
- In a Simple Java Project we add.

```xml
    <dependency>
        <groupId>com.sun.xml.ws</groupId>
        <artifactId>jaxws-ri</artifactId>
        <version>4.0.0</version>
        <type>pom</type>
    </dependency>
```
- Here we will not use Tomcat , but we will create our own Java Web Server


#### Webservice 


Here is our webservice created :

```java
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

```

Now we should deploy it,
We will create our own Server


#### Server 


Here is our server 

```java
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
```

#### Accessing the WSDL
visit : `http://localhost:9292/BankWS/?wsdl`

<details>

```xml
<!--This XML file does not appear to have any style information associated with it. The document tree is shown below.-->
<!--  Published by XML-WS Runtime (https://github.com/eclipse-ee4j/metro-jax-ws). Runtime's version is XML-WS Runtime 4.0.0 git-revision#129f787.  -->
<!--  Generated by XML-WS Runtime (https://github.com/eclipse-ee4j/metro-jax-ws). Runtime's version is XML-WS Runtime 4.0.0 git-revision#129f787.  -->
<definitions xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:wsp="http://www.w3.org/ns/ws-policy" xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://webservice.elaamiri.me/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.xmlsoap.org/wsdl/" targetNamespace="http://webservice.elaamiri.me/" name="BankWS">
<types>
<xsd:schema>
<xsd:import namespace="http://webservice.elaamiri.me/" schemaLocation="http://localhost:9292/?xsd=1"/>
</xsd:schema>
</types>
<message name="covert">
<part name="parameters" element="tns:covert"/>
</message>
<message name="covertResponse">
<part name="parameters" element="tns:covertResponse"/>
</message>
<message name="getAccount">
<part name="parameters" element="tns:getAccount"/>
</message>
<message name="getAccountResponse">
<part name="parameters" element="tns:getAccountResponse"/>
</message>
<message name="getAccounts">
<part name="parameters" element="tns:getAccounts"/>
</message>
<message name="getAccountsResponse">
<part name="parameters" element="tns:getAccountsResponse"/>
</message>
<portType name="BankWebservice">
<operation name="covert">
<input wsam:Action="http://webservice.elaamiri.me/BankWebservice/covertRequest" message="tns:covert"/>
<output wsam:Action="http://webservice.elaamiri.me/BankWebservice/covertResponse" message="tns:covertResponse"/>
</operation>
<operation name="getAccount">
<input wsam:Action="http://webservice.elaamiri.me/BankWebservice/getAccountRequest" message="tns:getAccount"/>
<output wsam:Action="http://webservice.elaamiri.me/BankWebservice/getAccountResponse" message="tns:getAccountResponse"/>
</operation>
<operation name="getAccounts">
<input wsam:Action="http://webservice.elaamiri.me/BankWebservice/getAccountsRequest" message="tns:getAccounts"/>
<output wsam:Action="http://webservice.elaamiri.me/BankWebservice/getAccountsResponse" message="tns:getAccountsResponse"/>
</operation>
</portType>
<binding name="BankWebservicePortBinding" type="tns:BankWebservice">
<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
<operation name="covert">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal"/>
</input>
<output>
<soap:body use="literal"/>
</output>
</operation>
<operation name="getAccount">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal"/>
</input>
<output>
<soap:body use="literal"/>
</output>
</operation>
<operation name="getAccounts">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal"/>
</input>
<output>
<soap:body use="literal"/>
</output>
</operation>
</binding>
<service name="BankWS">
<port name="BankWebservicePort" binding="tns:BankWebservicePortBinding">
<soap:address location="http://localhost:9292/"/>
</port>
</service>
</definitions>
```

</details>


#### Accessing the XSD schema
visit : `http://localhost:9292/?xsd=1`

<details>

```xml
<!--  Published by XML-WS Runtime (https://github.com/eclipse-ee4j/metro-jax-ws). Runtime's version is XML-WS Runtime 4.0.0 git-revision#129f787.  -->
<xs:schema xmlns:tns="http://webservice.elaamiri.me/" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0" targetNamespace="http://webservice.elaamiri.me/">
<xs:element name="covert" type="tns:covert"/>
<xs:element name="covertResponse" type="tns:covertResponse"/>
<xs:element name="getAccount" type="tns:getAccount"/>
<xs:element name="getAccountResponse" type="tns:getAccountResponse"/>
<xs:element name="getAccounts" type="tns:getAccounts"/>
<xs:element name="getAccountsResponse" type="tns:getAccountsResponse"/>
<xs:complexType name="getAccount">
<xs:sequence>
<xs:element name="code" type="xs:int"/>
</xs:sequence>
</xs:complexType>
<xs:complexType name="getAccountResponse">
<xs:sequence>
<xs:element name="return" type="tns:account" minOccurs="0"/>
</xs:sequence>
</xs:complexType>
<xs:complexType name="account">
<xs:sequence>
<xs:element name="balance" type="xs:double"/>
<xs:element name="code" type="xs:int"/>
<xs:element name="createdAt" type="xs:dateTime" minOccurs="0"/>
</xs:sequence>
</xs:complexType>
<xs:complexType name="getAccounts">
<xs:sequence/>
</xs:complexType>
<xs:complexType name="getAccountsResponse">
<xs:sequence>
<xs:element name="return" type="tns:account" minOccurs="0" maxOccurs="unbounded"/>
</xs:sequence>
</xs:complexType>
<xs:complexType name="covert">
<xs:sequence>
<xs:element name="amount" type="xs:double"/>
</xs:sequence>
</xs:complexType>
<xs:complexType name="covertResponse">
<xs:sequence>
<xs:element name="return" type="xs:double"/>
</xs:sequence>
</xs:complexType>
</xs:schema>
```

</details>

#### Testing the Webservice using WSDL and Oxygen editor

https://www.oxygenxml.com/xml_developer/wsdl_soap_analyzer.html

(Give the wsdl address to Oxygen : `http://localhost:9292/BankWS/?wsdl`)

![oxygen](./src_s/Screenshot%202022-10-24%20104726.png)

Converting an amount of 15442

![convert](./src_s/covert.PNG)


Getting an account :

![account](./src_s/account.PNG)


Getting list of accounts :

![account](./src_s/accounts.PNG)

#### Java Webservice Client (Proxy Soap)

Creating new java project for our client .

Adding the dependency :

```xml
<!-- https://mvnrepository.com/artifact/com.sun.xml.ws/jaxws-ri -->
<dependency>
    <groupId>com.sun.xml.ws</groupId>
    <artifactId>jaxws-ri</artifactId>
    <version>4.0.0</version>
    <type>pom</type>
</dependency>

```

Using `Intellij` to generate Java code (Proxy) from the `WSDL`

- Installing the plugin :

![plugin](./src_s/plugin.PNG)

- Generating by: `Help > find action > generate ...`

![generate](./src_s/generate.png)

![generate](./src_s/proxy.PNG)

Result proxy

![pro](./src_s/res.PNG)


## Java Webservice Client (Proxy Soap) Client creation

The client :

```java
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
```

Result :

```txt
195427.1
me.elaamiri.proxy.Account@ab7395e
[me.elaamiri.proxy.Account@2bd08376, me.elaamiri.proxy.Account@e70f13a, me.elaamiri.proxy.Account@3d3e5463]
Client ... 

Process finished with exit code 0

```

--> **C'est la programmation Orienté Objet Distribuée**
Car on est entrain d'appeler une méthode d'un objet qu se trouve dans une autre machine.

> L'intermidiare entre le client et le WebService c'est le `STUB` (Proxy côté client).
> L'intermidiare entre le client et le WebService c'est le `SKILOTON` (Proxy côté Webservice).

![haa](./src_s/2.PNG)

## PHP Webservice Client (Proxy Soap) Client creation

- Our client : 
- Run server using 

```
php -S localhost:8000
```

- Here is our PHP client:
`
<details>
```php

<?php 
    $amout = 0;
    $result = 0;
    $WSDL = "http://localhost:9292/BankWS/?wsdl";
    if(isset($_POST["amount"])){
        $amout = $_POST["amount"];
        
        $client_soap = new SoapClient($WSDL);

        $param = new stdClass(); // standard class
        $param->amount=$amout; //adding param
        $res = $client_soap->__soapCall("covert", [$param]/*args*/); // call a soap function
        $result = $res->return; //  access the return attribute of the soap object returned
    }
    
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form method="post">
        <table style="width: 50% ; margin:auto;">
            <tr>
                <td>Amount:</td>
                <td>
                    <input type="number" name="amount" value="<?=$amount?>" style="width: 100% ;">
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="SUBMIT" style="width: 100% ;">
                </td>
            </tr>
            <tr>
                <td>Result:</td>
                <td>
                    <div style="background-color: #aaa ; padding:10px;">
                        <?=$result?> <!---->
                    </div>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
```


</details>

- But before we should activate the `php_soap` extension of PHP.
1. If you are using XAMPP as your server the get into the directory XAMPP-> php and search for the 
file php.ini (File type: Configuration settings) and open the file in Notepad.

2. Then search for ``;extension=soap`` in the file using and decomment it.

![ex](./src_s/soap_extension.PNG)

- Here we go

![ress](./src_s/ress.PNG)

- Let's go deeped 
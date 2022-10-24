
package me.elaamiri.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "BankWS", targetNamespace = "http://webservice.elaamiri.me/", wsdlLocation = "http://localhost:9292/BankWS/?wsdl")
public class BankWS
    extends Service
{

    private final static URL BANKWS_WSDL_LOCATION;
    private final static WebServiceException BANKWS_EXCEPTION;
    private final static QName BANKWS_QNAME = new QName("http://webservice.elaamiri.me/", "BankWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9292/BankWS/?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BANKWS_WSDL_LOCATION = url;
        BANKWS_EXCEPTION = e;
    }

    public BankWS() {
        super(__getWsdlLocation(), BANKWS_QNAME);
    }

    public BankWS(WebServiceFeature... features) {
        super(__getWsdlLocation(), BANKWS_QNAME, features);
    }

    public BankWS(URL wsdlLocation) {
        super(wsdlLocation, BANKWS_QNAME);
    }

    public BankWS(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BANKWS_QNAME, features);
    }

    public BankWS(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BankWS(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BankWebservice
     */
    @WebEndpoint(name = "BankWebservicePort")
    public BankWebservice getBankWebservicePort() {
        return super.getPort(new QName("http://webservice.elaamiri.me/", "BankWebservicePort"), BankWebservice.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BankWebservice
     */
    @WebEndpoint(name = "BankWebservicePort")
    public BankWebservice getBankWebservicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.elaamiri.me/", "BankWebservicePort"), BankWebservice.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BANKWS_EXCEPTION!= null) {
            throw BANKWS_EXCEPTION;
        }
        return BANKWS_WSDL_LOCATION;
    }

}


package com.bank.ecs;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "eventInputBundleImplService", targetNamespace = "http://ECS.BANK.COM", wsdlLocation = "file:XML_Schema/eventInputBundleImpl.wsdl")
public class EventInputBundleImplService
    extends Service
{

    private final static URL EVENTINPUTBUNDLEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException EVENTINPUTBUNDLEIMPLSERVICE_EXCEPTION;
    private final static QName EVENTINPUTBUNDLEIMPLSERVICE_QNAME = new QName("http://ECS.BANK.COM", "eventInputBundleImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            //url = new URL("file:../XML_Schema/eventInputBundleImpl.wsdl");
        	url = new URL("file:./XML_Schema/eventInputBundleImpl.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EVENTINPUTBUNDLEIMPLSERVICE_WSDL_LOCATION = url;
        EVENTINPUTBUNDLEIMPLSERVICE_EXCEPTION = e;
    }

    public EventInputBundleImplService() {
        super(__getWsdlLocation(), EVENTINPUTBUNDLEIMPLSERVICE_QNAME);
    }

    public EventInputBundleImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), EVENTINPUTBUNDLEIMPLSERVICE_QNAME, features);
    }

    public EventInputBundleImplService(URL wsdlLocation) {
        super(wsdlLocation, EVENTINPUTBUNDLEIMPLSERVICE_QNAME);
    }

    public EventInputBundleImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EVENTINPUTBUNDLEIMPLSERVICE_QNAME, features);
    }

    public EventInputBundleImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EventInputBundleImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EventInputBundleImpl
     */
    @WebEndpoint(name = "eventInputBundleImpl")
    public EventInputBundleImpl getEventInputBundleImpl() {
        return super.getPort(new QName("http://ECS.BANK.COM", "eventInputBundleImpl"), EventInputBundleImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EventInputBundleImpl
     */
    @WebEndpoint(name = "eventInputBundleImpl")
    public EventInputBundleImpl getEventInputBundleImpl(WebServiceFeature... features) {
        return super.getPort(new QName("http://ECS.BANK.COM", "eventInputBundleImpl"), EventInputBundleImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EVENTINPUTBUNDLEIMPLSERVICE_EXCEPTION!= null) {
            throw EVENTINPUTBUNDLEIMPLSERVICE_EXCEPTION;
        }
        return EVENTINPUTBUNDLEIMPLSERVICE_WSDL_LOCATION;
    }

}


package com.bank.ecs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import ecs.data.EVTINPUTBUNDLE;
import ecs.data.EVTINPUTBUNDLERESPONSE;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "eventInputBundleImpl", targetNamespace = "http://ECS.BANK.COM")
@XmlSeeAlso({
    com.bank.ecs.ObjectFactory.class,
    ecs.data.ObjectFactory.class
})
public interface EventInputBundleImpl {


    /**
     * 
     * @param eventInputBundle
     * @return
     *     returns ecs.data.EVTINPUTBUNDLERESPONSE
     */
    @WebMethod
    @WebResult(name = "EventInputBundleResponse", targetNamespace = "")
    @RequestWrapper(localName = "runEventInputBundle", targetNamespace = "http://ECS.BANK.COM", className = "com.bank.ecs.RunEventInputBundle")
    @ResponseWrapper(localName = "runEventInputBundleResponse", targetNamespace = "http://ECS.BANK.COM", className = "com.bank.ecs.RunEventInputBundleResponse")
    public EVTINPUTBUNDLERESPONSE runEventInputBundle(
        @WebParam(name = "EventInputBundle", targetNamespace = "")
        EVTINPUTBUNDLE eventInputBundle);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.bank.ecs.GetUUIDResponse
     */
    @WebMethod
    @WebResult(name = "getUUIDResponse", targetNamespace = "http://ECS.BANK.COM", partName = "parameters")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public GetUUIDResponse getUUID(
        @WebParam(name = "getUUIDRequest", targetNamespace = "http://ECS.BANK.COM", partName = "parameters")
        GetUUIDRequest parameters);

}
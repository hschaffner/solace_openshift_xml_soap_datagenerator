/** 
 *  Copyright 2009-2017 Solace Systems, Inc. All rights reserved
 *  
 *  http://www.solace.com
 *  
 *  This source is distributed WITHOUT ANY WARRANTY or support;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *  A PARTICULAR PURPOSE.  All parts of this program are subject to
 *  change without notice including the program's CLI options.
 *
 *  Unlimited use and re-distribution of this unmodified source code is   
 *  authorized only with written permission.  Use of part or modified  
 *  source code must carry prominent notices stating that you modified it, 
 *  and give a relevant date.
 */

package com.solace.openshift.demo.datagenerator.soap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.commons.codec.binary.Base64;
//import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.bank.ecs.EventInputBundleImplService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class sendSOAPMessage {
	
	private static final Logger logger = LoggerFactory.getLogger(sendSOAPMessage.class);
	
	String authString;
	String authStringEnc;
	int counter = 0;
	HttpClient client = null;
	HttpHost targetHost = null;
	CloseableHttpClient httpclient = null;
	HttpPost httppost = null;
	EventInputBundleImplService service = null;
	
	public sendSOAPMessage(String user, String password, String url, String host, String port) {
		
		authString = user + ":" + password;
		this.authString = authString;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		authStringEnc = new String(authEncBytes);
		client = HttpClientBuilder.create().build();
		//System.out.println("Host: " + host + " posrt: " + port);
		targetHost = new HttpHost(host, Integer.valueOf(port), "http");
		httpclient = HttpClients.createDefault();
		httppost = new HttpPost(url);
		httppost.addHeader("Authorization", "Basic " + authStringEnc);
		httppost.setHeader("Content-Type", "text/plain");
		System.out.println("Starting Test .........");
		logger.info("Starting Test .........");
		service = new EventInputBundleImplService();
		
	}

	public void sendSOAP(ByteArrayOutputStream soapArrayOut) {//throws ClientProtocolException, IOException {
		
	

		//BasicHttpContext localcontext = null;
		HttpEntity httpEntity = null;
		
		if(counter % 500 == 0 && counter != 0) {
			System.out.println("Sent " + counter + " messages .......");
			logger.info("Sent " + counter + " messages .......");
		}

		
		StringEntity stringEntity = new StringEntity(soapArrayOut.toString(), "UTF-8");
		//stringEntity.setContentType("text/plain");
		//httppost.setHeader("Content-Type", "application/soap+xml");
		//httppost.setHeader("Content-Type", "text/plain");
		httpEntity = stringEntity;

		// Sending a binary message
		//          String bytesContent = Helpers.createGeneratedBytesDoc(30);
		//          ByteArrayEntity bytesEntity = new ByteArrayEntity(bytesContent.getBytes());
		//          bytesEntity.setContentType("application/octet-stream");
		//          httpEntity = bytesEntity;

		// Request / reqply header.
		//httppost.addHeader("Solace-Reply-Wait-Time-In-ms", "10000");
		//httppost.addHeader("Authorization", "Basic " + authStringEnc);
		httppost.setEntity(httpEntity);

		
		CloseableHttpResponse response = null;;
		try {
			response = httpclient.execute(targetHost, httppost);
		} catch (IOException e1) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		}
		try {
			response.close();
		} catch (IOException e1) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		}


		counter++;

	}
	
	public void formSOAPandSend(ByteArrayOutputStream out) {
		SOAPMessage soapMsg = null;


		StringBuffer sb = new StringBuffer(out.toString());
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = null;;
		Document xml = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		}
		try {
			xml = docBuilder.parse(new InputSource(new StringReader(sb.toString())));
		} catch (SAXException e1 ) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e1) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		

		MessageFactory factory;
		try {
			factory = MessageFactory.newInstance();
			soapMsg = factory.createMessage();
			SOAPPart part = soapMsg.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();
			SOAPHeader header = envelope.getHeader();
			SOAPBody body = envelope.getBody();

			body.addDocument(xml);
			
			System.out.println("HEADER: " + header.toString());

		} catch (SOAPException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} 
		

		ByteArrayOutputStream soapArrayOut = new ByteArrayOutputStream();
		try {
			soapMsg.writeTo(soapArrayOut);
		} catch (SOAPException e1) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e1) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		// Sending a test message
		sendSOAP(soapArrayOut);
	}



}

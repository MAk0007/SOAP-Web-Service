package com.itc.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.itc.beans.Product;

@WebService
@SOAPBinding(style = Style.RPC)
public interface QuantityCheckWS {
	 @WebMethod boolean getQuantity(Product product);
		 
	 }


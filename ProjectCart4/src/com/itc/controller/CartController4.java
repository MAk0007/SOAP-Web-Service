package com.itc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itc.beans.Product;
import com.itc.dao.ProductDAO;



@Controller
public class CartController4 {
	@Autowired
	ProductDAO ProductDAO;
	/*@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	   public String redirect2() {
	      return "redirect:/pages/homepage.html";
	   }*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	   public String product() {
	      return "product";
	   }
	@RequestMapping(value = "/checkAvailability", method = RequestMethod.GET)
	   public String result(HttpServletRequest request, Model model) {
		System.out.println("inside controller result method");
		int status=0;
		String strQuan=null;
		Product p = ProductDAO.get(Integer.parseInt(request.getParameter("id")));
		strQuan= request.getParameter("quantity");
		int quan=0;
		if(strQuan==null || strQuan.trim().equals("")) {
			status=-1;
		}else {
			quan=Integer.parseInt(strQuan);
			if(quan>p.getQuantity()) {
				status=0;
			}else {
				status=1;
			}
		}
		System.out.println(strQuan);
		System.out.println(status);
		System.out.println(" out controller result method");
		model.addAttribute("status", status);
		
		return "checkAvailability";
	   }
	   
}

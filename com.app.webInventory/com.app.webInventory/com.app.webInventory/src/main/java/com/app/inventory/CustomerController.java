package com.app.inventory;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.iCustomerImpl;
import com.app.object.Customer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
@Controller
public class CustomerController {
	
	
	iCustomerImpl customerImpl = new iCustomerImpl();
	int idCustomer = 0;
	Customer customer = new Customer();
	//page List Customer
			@RequestMapping(value = {"/listCustomerPage"}, method = RequestMethod.GET)
			public ModelAndView listProductPage(ModelMap model) throws Exception {
				 ModelAndView mav = new ModelAndView();
		      ArrayList<Customer> customerList = new ArrayList<Customer>();
		      customerList =  customerImpl.getAllCustomer();
			model.addAttribute("customerList", customerList);
					mav.setViewName("listCustomer");
						return mav;
			}
			//process Delete Customer 
				@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST, produces = "application/json")
				public void changeStatus(HttpServletRequest request) throws Exception {
					String id = request.getParameter("id");
					Customer customer1 = new Customer();
					customer1.setCustomerId(Integer.parseInt(id));
					boolean flag = customerImpl.deleteCustomer(customer1);
					System.out.println("Gia tri id: " + id + "Xoa" + flag);
				
			}
				//process edit Customer
				@RequestMapping(value = "/editCustomerProcess", method = RequestMethod.POST, produces = "application/json")
				public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
					String id = request.getParameter("id");
					idCustomer = Integer.parseInt(id);
					System.out.println("Gia tri id: " + idCustomer);
					 String str= "";
					 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
					 return str;
				}
			
		
		
	
	
	
	//page Customer Edit
	@RequestMapping(value = {"/editCustomerPage"}, method = RequestMethod.GET)
	public ModelAndView editStockInWardPage(ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();
			System.out.println("Gia tri idCustomer: " + idCustomer );
			customer = customerImpl.getById(idCustomer);	 
			 model.addAttribute("customername", customer.getCustomerName());
			 model.addAttribute("phone", customer.getTel());
			 model.addAttribute("address", customer.getAddress());
			 model.addAttribute("email", customer.getEmail());
			 model.addAttribute("web", customer.getWebSite());
			 model.addAttribute("note", customer.getDescription());
			mav.setViewName("editCustomer");
			return mav;
					
	}
	//update customer
	@RequestMapping(value = "/editCustomerData", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String editStockCheck11(HttpServletRequest request) throws Exception
	{
		String stockProductEditDetail = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (stockProductEditDetail, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		customer.setCustomerName(jsonObj.get("customername").getAsString().trim()); 
		customer.setTel(jsonObj.get("phone").getAsString().trim()); 
		customer.setAddress(jsonObj.get("address").getAsString().trim()); 
		customer.setEmail(jsonObj.get("email").getAsString().trim()); 
        boolean flagUpdate = customerImpl.updateCustomer(customer);
       
		  String str= "";  
		  if (flagUpdate == true) {
				str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
			} else {
				str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
			}
			return str;
	
	}			
	
	//add customer Page
	@RequestMapping(value = {"/addcustomerPage"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();
			mav.setViewName("addCustomer");
			return mav;
	}
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		String productString = request.getParameter("0");
		//JsonObject stockInwardObj = new Gson().fromJson(stockInward,JsonObject.class);
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (productString, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		
	  iCustomerImpl customerImpl = new iCustomerImpl();
		  Customer customer = new Customer();
		  customer.setAddress(jsonObj.get("address").getAsString());
		  customer.setDescription(jsonObj.get("descript").getAsString());
		  customer.setEmail(jsonObj.get("email").getAsString());
		  customer.setCustomerId(1);
		  customer.setCustomerName(jsonObj.get("customer").getAsString());
		  customer.setTel(jsonObj.get("phone").getAsString());
		  customer.setWebSite(jsonObj.get("web").getAsString());
		  
		  boolean flagInsert = customerImpl.insertCustomer(customer);
		  System.out.println("Trang thai insert" + flagInsert);  
		  String str= "";
		  if (flagInsert) {
				str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
			} else {
				str = "{\"error\": \"2\",\"message\": \"Insert failed. Please try again.\"}";
			}
			return str;
	
	}
}

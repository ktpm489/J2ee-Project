package com.app.inventory;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.iProviderImpl;
import com.app.object.Provider;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class ProviderController {
	
	
	
	iProviderImpl providerImpl = new iProviderImpl();
	int idProvider = 1;
	Provider provider = new Provider();
	
	
	//page List Provider
	@RequestMapping(value = {"/listProviderPage"}, method = RequestMethod.GET)
	public ModelAndView listProductPage(ModelMap model) throws Exception {
		 ModelAndView mav = new ModelAndView();
      ArrayList<Provider> providerList = new ArrayList<Provider>();
      providerList =  providerImpl.getAllProvider();
	model.addAttribute("providerList", providerList);
			mav.setViewName("listProvider");
				return mav;
	}
	//process Delete Provider 
		@RequestMapping(value = "/deleteProvider", method = RequestMethod.POST, produces = "application/json")
		public void changeStatus(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			Provider provider1 = new Provider();
			provider1.setProviderId(Integer.parseInt(id));
			boolean flag = providerImpl.deleteProvider(provider1);
			System.out.println("Gia tri id: " + id + "Xoa" + flag);
		
	}
		//process edit Provider
		@RequestMapping(value = "/editProviderProcess", method = RequestMethod.POST, produces = "application/json")
		public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			idProvider = Integer.parseInt(id);
			System.out.println("Gia tri id: " + idProvider);
			 String str= "";
			 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
			 return str;
		}
	//page Provider Edit
		@RequestMapping(value = {"/editProviderPage"}, method = RequestMethod.GET)
		public ModelAndView editProviderPage(ModelMap model) throws Exception {
			ModelAndView mav = new ModelAndView();
				System.out.println("Gia tri idProvider: " + idProvider );
				provider = providerImpl.getByID(idProvider);	 
				 model.addAttribute("provider", provider.getProviderName());
				 model.addAttribute("phone", provider.getTel());
				 model.addAttribute("address",provider.getAddress());
				 model.addAttribute("email", provider.getEmail());
				 model.addAttribute("web", provider.getWebSite());
				 model.addAttribute("note", provider.getDescription());
				mav.setViewName("editProvider");
				return mav;
						
		}
		//update provider
		@RequestMapping(value = "/editProviderData", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
		public final @ResponseBody  String editStockCheck11(HttpServletRequest request) throws Exception
		{
			String providerEditDetail = request.getParameter("0");
			Gson gson = new Gson();
			JsonElement element = gson.fromJson (providerEditDetail, JsonElement.class);
			JsonObject jsonObj = element.getAsJsonObject();
			provider.setProviderName(jsonObj.get("provider").getAsString().trim()); 
			provider.setTel(jsonObj.get("phone").getAsString().trim()); 
			provider.setAddress(jsonObj.get("address").getAsString().trim()); 
			provider.setEmail(jsonObj.get("email").getAsString().trim()); 
	        boolean flagUpdate = providerImpl.updateProvider(provider);
	       
			  String str= "";  
			  if (flagUpdate == true) {
					str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
				} else {
					str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
				}
				return str;
		
		}			
	
	
	
	
	//add provider page
	@RequestMapping(value = {"/addproviderPage"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		//iStockImpl ProductList = new iStockImpl();
		 ModelAndView mav = new ModelAndView();
			mav.setViewName("addprovider");
			return mav;
	}
	@RequestMapping(value = "/addProvider", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		String productString = request.getParameter("0");
		//JsonObject stockInwardObj = new Gson().fromJson(stockInward,JsonObject.class);
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (productString, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		
	  iProviderImpl providerImpl = new iProviderImpl();
		  Provider provider = new Provider();
		  provider.setAddress(jsonObj.get("address").getAsString());
		  provider.setDescription(jsonObj.get("descript").getAsString());
		  provider.setEmail(jsonObj.get("email").getAsString());
		  provider.setProviderId(1);
		  provider.setProviderName(jsonObj.get("provider").getAsString());
		  provider.setTel(jsonObj.get("phone").getAsString());
		  provider.setWebSite(jsonObj.get("web").getAsString());
		  
		  boolean flagInsert = providerImpl.insertProvider(provider);
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

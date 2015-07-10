package com.app.inventory;


import java.sql.Date;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.iStaffImpl;
import com.app.object.Staff;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class StaffController 
{

	iStaffImpl staffImpl = new iStaffImpl();
	int idStaff = 1;
	Staff staff = new Staff();
	//page List Staff
	@RequestMapping(value = {"/listStaffPage"}, method = RequestMethod.GET)
	public ModelAndView listProductPage(ModelMap model) throws Exception {
		 ModelAndView mav = new ModelAndView();
      ArrayList<Staff> staffList = new ArrayList<Staff>();
      staffList =  staffImpl.getAllStaff();
	model.addAttribute("staffList", staffList);
			mav.setViewName("listStaff");
				return mav;
	}
	//process Delete Staff 
		@RequestMapping(value = "/deleteStaff", method = RequestMethod.POST, produces = "application/json")
		public void changeStatus(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			Staff staff1 = new Staff();
			staff1.setStaffId(Integer.parseInt(id));
			boolean flag = staffImpl.deleteStaff(staff1);
			System.out.println("Gia tri id: " + id + "Xoa" + flag);
		
	}
		//process edit Staff
		@RequestMapping(value = "/editStaffProcess", method = RequestMethod.POST, produces = "application/json")
		public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			idStaff = Integer.parseInt(id);
			System.out.println("Gia tri id: " + idStaff);
			 String str= "";
			 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
			 return str;
		}
	


	
	
	
	
	
	     //page Staff Edit
			@RequestMapping(value = {"/editStaffPage"}, method = RequestMethod.GET)
			public ModelAndView editProviderPage(ModelMap model) throws Exception {
				ModelAndView mav = new ModelAndView();
					System.out.println("Gia tri idStaff: " + idStaff );
					staff = staffImpl.getByID(idStaff);	 
					 model.addAttribute("staffname", staff.getStaffName());
					 model.addAttribute("code", staff.getIdentifyId());
					 model.addAttribute("day", staff.getBirthDay());
					 model.addAttribute("phone", staff.getTel());
					 model.addAttribute("address",staff.getAddress());
					 model.addAttribute("email", staff.getEmail());
					
					mav.setViewName("editStaff");
					return mav;
							
			}
			//update staff
			@RequestMapping(value = "/editStaffData", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
			public final @ResponseBody  String editStaffCheck11(HttpServletRequest request) throws Exception
			{
				String providerEditDetail = request.getParameter("0");
				Gson gson = new Gson();
				JsonElement element = gson.fromJson (providerEditDetail, JsonElement.class);
				JsonObject jsonObj = element.getAsJsonObject();
				staff.setStaffName(jsonObj.get("staffname").getAsString().trim()); 
				staff.setTel(jsonObj.get("phone").getAsString().trim()); 
				staff.setAddress(jsonObj.get("address").getAsString().trim()); 
				staff.setEmail(jsonObj.get("email").getAsString().trim()); 
		        boolean flagUpdate = staffImpl.updateStaff(staff);
		       
				  String str= "";  
				  if (flagUpdate == true) {
						str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
					} else {
						str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
					}
					return str;
			
			}			
		
	
	
	
	
	//add staff Page
	@RequestMapping(value = {"/addstaffPage"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		//iStockImpl ProductList = new iStockImpl();
		 ModelAndView mav = new ModelAndView();
			mav.setViewName("addStaff");
			return mav;
	}
	@RequestMapping(value = "/addStaff", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		String productString = request.getParameter("0");
		//JsonObject stockInwardObj = new Gson().fromJson(stockInward,JsonObject.class);
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (productString, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		System.out.print("ngay sinh" +jsonObj.get("birth").getAsString());
		
	  iStaffImpl staffImpl = new iStaffImpl();
		  Staff staff = new Staff();
		  staff.setAddress(jsonObj.get("address").getAsString());
		  staff.setIdentifyId(Integer.parseInt(jsonObj.get("staffcode").getAsString().trim()));
		 staff.setBirthDay(Date.valueOf(jsonObj.get("birth").getAsString()));
		  staff.setEmail(jsonObj.get("email").getAsString());
		  staff.setStaffId(1);
		  staff.setStaffName(jsonObj.get("staffname").getAsString());
		  staff.setTel(jsonObj.get("phone").getAsString());
	
		  boolean flagInsert = staffImpl.insertStaff(staff);
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

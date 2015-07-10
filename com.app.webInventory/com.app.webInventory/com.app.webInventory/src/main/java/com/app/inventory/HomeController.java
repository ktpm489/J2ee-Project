package com.app.inventory;




import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.app.dao.iUserImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//login Page
	@RequestMapping(value = {"/inventory","/","/loginPage"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		//iStockImpl ProductList = new iStockImpl();
		 ModelAndView mav = new ModelAndView();
		 try {
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			mav.setViewName("login");
	
			return mav;	
	}
	
	//About Page
	@RequestMapping(value = {"/aboutTeam"}, method = RequestMethod.GET)
	public ModelAndView aboutPage(ModelMap model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		//iStockImpl ProductList = new iStockImpl();
		 ModelAndView mav = new ModelAndView();
		 try {
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			//mav.setViewName("login");
		 mav.setViewName("about");
			return mav;	
	}
	//contacePage
	@RequestMapping(value = {"/contactTeam"}, method = RequestMethod.GET)
	public ModelAndView contactPage(ModelMap model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		//iStockImpl ProductList = new iStockImpl();
		 ModelAndView mav = new ModelAndView();
		 try {
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			//mav.setViewName("login");
		 mav.setViewName("contact");
			return mav;	
	}
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		String loginInfo = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (loginInfo, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
	
		  String username =jsonObj.get("user").getAsString().trim();
		 String userPass = jsonObj.get("pass").getAsString().trim();
		 iUserImpl userImpl = new iUserImpl();
		  boolean flagLogin = userImpl.checkInfo(username, userPass);
		  System.out.println("Trang thai flagLogin" + flagLogin);  
		  String str= "";
		  if (flagLogin) {
				str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
			} else {
				str = "{\"error\": \"2\",\"message\": \"Insert failed. Please try again.\"}";
			}
			return str;
	
	}
	
}

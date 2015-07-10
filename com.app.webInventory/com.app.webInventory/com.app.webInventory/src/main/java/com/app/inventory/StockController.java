package com.app.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.iStaffImpl;
import com.app.dao.iStockImpl;
import com.app.dao.iStockTypeImpl;
import com.app.object.Staff;
import com.app.object.State;
import com.app.object.Stock;
import com.app.object.StockType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class StockController {
	
	int idStock = 0;
	boolean flagPreviousStock = false;
	Stock stockTest = new Stock();
	iStockImpl stockImpl = new iStockImpl();
	
	//page List Stock
	@RequestMapping(value = {"/listStockPage"}, method = RequestMethod.GET)
	public ModelAndView listStockCheck(ModelMap model) throws Exception {
		 ModelAndView mav = new ModelAndView();
      ArrayList<Stock> stockListLoad = new ArrayList<Stock>();
      stockListLoad = stockImpl.getAllStock();
	model.addAttribute("stockListLoad", stockListLoad);
			mav.setViewName("listStock");
				return mav;
	}
	//process Delete Stock 
		@RequestMapping(value = "/deleteStock", method = RequestMethod.POST, produces = "application/json")
		public void changeStatus(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			Stock stockObject = new Stock();
			stockObject.setStockId(Integer.parseInt(id));
			boolean flag =stockImpl.deleteStock(stockObject);
			System.out.println("Gia tri id: " + id + "Xoa" + flag);
		
	}
		//process edit Stock
		@RequestMapping(value = "/editStock", method = RequestMethod.POST, produces = "application/json")
		public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			idStock = Integer.parseInt(id);
			System.out.println("Gia tri id: " + idStock);
			 String str= "";
			 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
			 return str;
		}
	
	
	//page Stock Edit
		@RequestMapping(value = {"/pageEditStock"}, method = RequestMethod.GET)
		public ModelAndView editStockInWardPage(ModelMap model) throws Exception {
			ModelAndView mav = new ModelAndView();
			 System.out.println("Gia tri idStock: " + idStock );
			ArrayList<State>  stateList = new ArrayList<State>();
			State objectState = new State();
			stockTest = stockImpl.getByID(idStock);		 
			flagPreviousStock = stockTest.getState();
			   String t = " ";
			   if ( flagPreviousStock == true)
			   {
				   t = "Active";
			   }else
			   {
				   t = "Waitting Active";
			   }
			   System.out.println("State Stock " + flagPreviousStock);
			   objectState.setNameValue(t);
			   objectState.setValue(flagPreviousStock);   
			     stateList.add(objectState);
				 model.addAttribute("stockname", stockTest.getStockName());
				 model.addAttribute("address", stockTest.getAddress());
				 model.addAttribute("managername", stockTest.getNameManagerId());
				 model.addAttribute("stocktype", stockTest.getNameStockTypeId());
				 model.addAttribute("quantity", stockTest.getCurrentQuantity());
				 model.addAttribute("stateList", stateList);
				 model.addAttribute("note", stockTest.getDescript());
				mav.setViewName("editStock");
				return mav;
						
		}

	//update stock 
		@RequestMapping(value = "/editStockData", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
		public final @ResponseBody  String editStockCheck11(HttpServletRequest request) throws Exception
		{
			String stockCheckEditDetail = request.getParameter("0");
			Gson gson = new Gson();
			JsonElement element = gson.fromJson (stockCheckEditDetail, JsonElement.class);
			JsonObject jsonObj = element.getAsJsonObject();
			stockTest.setStockName(jsonObj.get("stockname").getAsString().trim()); 
			stockTest.setState(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
	        boolean flagUpdate = stockImpl.updateStock(stockTest);
	       
			  String str= "";  
			  if (flagUpdate == true) {
					str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
				} else {
					str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
				}
				return str;
		
		}	
		
	//add Stock Page
	@RequestMapping(value = {"/addstockPage"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) throws Exception {
		
		 ModelAndView mav = new ModelAndView();
		iStaffImpl istaffstock = new iStaffImpl();
		List<Staff> listStaff = new ArrayList<Staff>();
		listStaff = istaffstock.getAllStaff();
		iStockTypeImpl istockType = new iStockTypeImpl();
		List<StockType> liststock = new ArrayList<StockType>();
		liststock = istockType.getAllStockType();
		 model.addAttribute("stafflist", listStaff );
		 model.addAttribute("liststock", liststock );
			mav.setViewName("addStock");
			return mav;
	}
	@RequestMapping(value = "/addStock", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		String productString = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (productString, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		
	  iStockImpl stockImpl = new iStockImpl();
		  Stock stock = new Stock();
		  stock.setDescript(jsonObj.get("note").getAsString());
		  stock.setAddress(jsonObj.get("address").getAsString());
		 stock.setManagerId(Integer.parseInt(jsonObj.get("staff").getAsString().trim()));
		 stock.setNameManagerId(""); 
		  stock.setNameStockTypeId("");
		 // stock.setState(Boolean.Boolean.valueOf(jsonObj.get("state").getAsString().trim()));
		  System.out.print(" Gia tri "+ (jsonObj.get("state").getAsString()));
		 // int stateCheck = Integer.parseInt(jsonObj.get("state").getAsString().trim());
		  stock.setState( Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
		  stock.setStockId(1);
		  stock.setStockName(jsonObj.get("stockname").getAsString());
		  stock.setStockTypeId(Integer.parseInt(jsonObj.get("stocktype").getAsString().trim()));
		  boolean flagInsert = stockImpl.insertStock(stock);
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

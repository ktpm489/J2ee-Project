package com.app.inventory;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.iCustomerImpl;
import com.app.dao.iProductUnitImpl;
import com.app.dao.iStaffImpl;
import com.app.dao.iStockOutwardDetailImpl;
import com.app.dao.iStockOutwardImpl;
import com.app.dao.iStockProductImpl;
import com.app.object.Customer;
import com.app.object.ProductUnit;
import com.app.object.Staff;
import com.app.object.State;
import com.app.object.StockOutward;
import com.app.object.StockOutwardDetail;
import com.app.object.StockProduct;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class StockOutwardController {
	
	
	boolean flagPreviousStockOutward = false;
	ArrayList<StockOutwardDetail> stockOutwardDetailList = new ArrayList<StockOutwardDetail>();
	iStockOutwardImpl stockOutwardImpl = new iStockOutwardImpl();
	StockOutward stockOutwardTest = new StockOutward();
	iStockOutwardDetailImpl stockOutwardDetailImpl = new iStockOutwardDetailImpl();
	int idStockOutwardForward = 1;
	
	//page List Stock Outward
	@RequestMapping(value = {"/listStockOutwardPage"}, method = RequestMethod.GET)
	public ModelAndView listStockCheck(ModelMap model) throws Exception {
		 ModelAndView mav = new ModelAndView();
      ArrayList<StockOutward> stockOutwardListLoad = new ArrayList<StockOutward>();
      stockOutwardListLoad = stockOutwardImpl.getAllStockOutward();
			 model.addAttribute("stockOutwardListLoad", stockOutwardListLoad);
			mav.setViewName("listStockOutward");
				return mav;
	}
	//process Delete Stock Outward

	@RequestMapping(value = "/deleteStockOutward", method = RequestMethod.POST, produces = "application/json")
	public void changeStatus(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		StockOutward stockObject = new StockOutward();
		stockObject.setOutwardId(Integer.parseInt(id));
		boolean flag =stockOutwardImpl.deleteStockOutward(stockObject);
		System.out.println("Gia tri id: " + id + "Xoa" + flag);
	
	}

// process to Page Edit Stock Outward
	@RequestMapping(value = "/editStockOutwardForward", method = RequestMethod.POST, produces = "application/json")
	public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		idStockOutwardForward = Integer.parseInt(id);
		System.out.println("Gia tri id: " + idStockOutwardForward);
		 String str= "";
		 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
		 return str;
	}	
	
	
	//page Edit Stock Outward
		@RequestMapping(value = {"/pageEditStockOutward"}, method = RequestMethod.GET)
		public ModelAndView editStockInWardPage(ModelMap model) throws Exception {
			ModelAndView mav = new ModelAndView();
			 System.out.println("Gia tri idStockOutwardForward: " + idStockOutwardForward );
			ArrayList<State>  stateList = new ArrayList<State>();
			State objectState = new State();
			stockOutwardTest = stockOutwardImpl.getByID(idStockOutwardForward);		 
			flagPreviousStockOutward = stockOutwardTest.getStateOutward();
			   String t = " ";
			   if ( flagPreviousStockOutward == true)
			   {
				   t = "Active";
			   }else
			   {
				   t = " Waitting Active";
			   }
			   System.out.println("State Stock Inward" + flagPreviousStockOutward);
			   stockOutwardDetailList.clear();
			   stockOutwardDetailList = stockOutwardDetailImpl.getAllStockOutwardDetail(idStockOutwardForward);
			   objectState.setNameValue(t);
			   objectState.setValue(flagPreviousStockOutward);   
			   stateList.add(objectState);
				 model.addAttribute("stocktransfer", stockOutwardTest.getOutwardName());
				 model.addAttribute("provider", stockOutwardTest.getNamecustomerId());
				 model.addAttribute("staff", stockOutwardTest.getNamestaffId());
				 model.addAttribute("date", stockOutwardTest.getDateOutward());
				 model.addAttribute("amount", stockOutwardTest.getTotalQuantity());
				 model.addAttribute("price", stockOutwardTest.getTotalAmount());
				 model.addAttribute("stateList", stateList);
				 model.addAttribute("note", stockOutwardTest.getDescript());
				 model.addAttribute("stockOutwardDetailList", stockOutwardDetailList);
				mav.setViewName("editStockOutward");
				return mav;
					
		}
		
	
		//update stock outward
				@RequestMapping(value = "/editStockOutwardData", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
				public final @ResponseBody  String editStockCheckOutward1(HttpServletRequest request) throws Exception
				{
					
					String stockOutwardEditDetail = request.getParameter("0");
					Gson gson = new Gson();
					JsonElement element = gson.fromJson (stockOutwardEditDetail, JsonElement.class);
					JsonObject jsonObj = element.getAsJsonObject();
					stockOutwardTest.setOutwardName(jsonObj.get("stockoutward").getAsString()); 
					stockOutwardTest.setStateOutward(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
			        boolean flagUpdate = stockOutwardImpl.updateStockOutward(stockOutwardTest);
			       boolean flagGetUpdate = Boolean.parseBoolean(jsonObj.get("state").getAsString().trim());
			       int sizeListStockOutwardDetail = stockOutwardDetailList.size();
			       iStockProductImpl istockProductOutwardEdit = new iStockProductImpl();
			       // Th1
			       if (flagPreviousStockOutward == false && flagGetUpdate == true)
			       {
			    	   if(sizeListStockOutwardDetail == 0)
			    	   {
			    		   
			    	   }else
			    	   {
			    		   for (int i = 0 ; i < stockOutwardDetailList.size() ; i++)
			    		   {
			    			   StockOutwardDetail stockOutwardDetailObj = new StockOutwardDetail();
			    			   stockOutwardDetailObj = stockOutwardDetailList.get(i);
			    			   StockProduct stockProductObj = new StockProduct();
			    			   int totalAmountTo = istockProductOutwardEdit.getQuantitytByStockAndProductId(stockOutwardDetailObj.getStockId(), stockOutwardDetailObj.getProductId());
			    			   int id = istockProductOutwardEdit.getQuantitytByStockAndProductId(stockOutwardDetailObj.getStockId(), stockOutwardDetailObj.getProductId());
			    			   if ( id != 0)
			    			   {
			    				   int quantitySub = stockOutwardDetailObj.getNumberOutward();
			    				   int quantityUpdate = totalAmountTo - quantitySub;
			    				   stockProductObj.setNameproductId(" ");
			    				   stockProductObj.setNamestockId(" ");
			    				   stockProductObj.setNameStockProduct(" ");
			    				   stockProductObj.setStockProductId(1);
			    				   stockProductObj.setStockId(stockOutwardDetailObj.getStockId());
			    				   stockProductObj.setProductId(stockOutwardDetailObj.getProductId());
			    				   stockProductObj.setQuantity(quantityUpdate);
			    				   istockProductOutwardEdit.updateStockProduct(stockProductObj);
			    			   }else
			    			   {
			    				   int quantitySub =  stockOutwardDetailObj.getNumberOutward();
			    				   stockProductObj.setNameproductId(" ");
			    				   stockProductObj.setNamestockId(" ");
			    				   stockProductObj.setNameStockProduct(" ");
			    				   stockProductObj.setStockProductId(1);
			    				   stockProductObj.setStockId(stockOutwardDetailObj.getStockId());
			    				   stockProductObj.setProductId(stockOutwardDetailObj.getProductId());
			    				   stockProductObj.setQuantity(-quantitySub);
			    				   istockProductOutwardEdit.insertStockProduct(stockProductObj);
			    				      
			    			   }
			    			   
			    		   }
			    	   }
			    	      // 	   
			       }else if ( flagPreviousStockOutward == true && flagGetUpdate == false)
			       {
						if(sizeListStockOutwardDetail == 0)
				    	   {
				    		   
				    	   }else
				    	   {
				    		   for (int i = 0 ; i < stockOutwardDetailList.size() ; i++)
				    		   {
				    			   StockOutwardDetail stockOutwardDetailObj = new StockOutwardDetail();
				    			   stockOutwardDetailObj = stockOutwardDetailList.get(i);
				    			   StockProduct stockProductObj1 = new StockProduct();
				    			   int totalAmountTo = istockProductOutwardEdit.getQuantitytByStockAndProductId(stockOutwardDetailObj.getStockId(), stockOutwardDetailObj.getProductId());
				    			   int id = istockProductOutwardEdit.getStockProductId(stockOutwardDetailObj.getStockId(), stockOutwardDetailObj.getProductId());
				    			   if ( id != 0)
				    			   {
				    				   int quantityAdd = stockOutwardDetailObj.getNumberOutward();
				    				   int quantityUpdate =  totalAmountTo + quantityAdd ;
				    				   stockProductObj1.setNameproductId(" ");
				    				   stockProductObj1.setNamestockId(" ");
				    				   stockProductObj1.setNameStockProduct(" ");
				    				   stockProductObj1.setStockProductId(1);
				    				   stockProductObj1.setStockId(stockOutwardDetailObj.getStockId());
				    				   stockProductObj1.setProductId(stockOutwardDetailObj.getProductId());
				    				   stockProductObj1.setQuantity(quantityUpdate);
				    				   istockProductOutwardEdit.updateStockProduct(stockProductObj1);
				    			   }else
				    			   {
				    				    int quantityAdd = stockOutwardDetailObj.getNumberOutward();
				    				    int quantityUpdate = quantityAdd ;
				    				   stockProductObj1.setNameproductId(" ");
				    				   stockProductObj1.setNamestockId(" ");
				    				   stockProductObj1.setNameStockProduct(" ");
				    				   stockProductObj1.setStockProductId(1);
				    				   stockProductObj1.setStockId(stockOutwardDetailObj.getStockId());
				    				   stockProductObj1.setProductId(stockOutwardDetailObj.getProductId());
				    				   stockProductObj1.setQuantity(quantityUpdate);
				    				   istockProductOutwardEdit.updateStockProduct(stockProductObj1);  				      
				    			   }
				    			   
				    		   }
				    	   }   	   
			    	   
			       }
			       flagPreviousStockOutward = stockOutwardTest.getStateOutward();
					  String str= "";  
					  if (flagUpdate == true) {
							str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
						} else {
							str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
						}
						return str;
				
				}
				//printstock outward
				@RequestMapping(value = "/editStockOutwardDataPrint", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
				public final @ResponseBody  void printStockCheckOutward(HttpServletRequest request) throws Exception
				{
					
					String stockOutwardEditDetail = request.getParameter("0");
					Gson gson = new Gson();
					JsonElement element = gson.fromJson (stockOutwardEditDetail, JsonElement.class);
					JsonObject jsonObj = element.getAsJsonObject();
					stockOutwardTest.setOutwardName(jsonObj.get("stockoutward").getAsString()); 
					stockOutwardTest.setStateOutward(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
			       new com.app.inventory.ReportOutward().runReport("I://Test//outward.jrxml", "I://Test//Outward.pdf",stockOutwardTest,stockOutwardDetailList);
				
				}
				//page Add new Stock Outward
	@RequestMapping(value = {"/addstockoutwardPage"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) throws Exception {
	
		 ModelAndView mav = new ModelAndView();
		iStaffImpl iStaff = new iStaffImpl();
		 List<Staff> staffList = new ArrayList<Staff>();
		 iCustomerImpl iProvider = new iCustomerImpl();
		 List<Customer> customerList = new ArrayList<Customer>();
		 staffList = iStaff.getAllStaff();
		 iStockProductImpl iStockProduct = new iStockProductImpl();
		 List<StockProduct> productStockList = new ArrayList<StockProduct>();
		 productStockList = iStockProduct.getAllProductWithStock();
		 customerList = iProvider.getAllCustomer();
		 iProductUnitImpl productUnit = new iProductUnitImpl();
		 List<ProductUnit> productUnitList = new ArrayList<ProductUnit>();
		 productUnitList = productUnit.getAllProductUnit();
		 model.addAttribute("staffList", staffList );
		 model.addAttribute("productUnitList", productUnitList );
		 model.addAttribute("productList", productStockList );
		 model.addAttribute("providerList", customerList );
			mav.setViewName("addStockOutward");
			return mav;
	}
	
	@RequestMapping(value = "/addStockOutward", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		//insert stockinwards
		String stockOutwarddDetail = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (stockOutwarddDetail, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
       iStockOutwardImpl stockOutwardAdd = new iStockOutwardImpl(); 
      StockOutward stockOutwards = new StockOutward();
      stockOutwards.setDateOutward(Date.valueOf(jsonObj.get("date").getAsString()));
      stockOutwards.setDescript(jsonObj.get("note").getAsString());
      stockOutwards.setOutwardId(1);
      stockOutwards.setNamecustomerId("");
      stockOutwards.setNamestaffId("");
      stockOutwards.setNamestockId("");
      stockOutwards.setStockId(1);
      stockOutwards.setCustomerId(Integer.parseInt(jsonObj.get("customer").getAsString().trim()));
      stockOutwards.setStaffId(Integer.parseInt(jsonObj.get("staff").getAsString().trim()));
      stockOutwards.setTotalAmount(new BigDecimal(jsonObj.get("totalprice").getAsString().trim()));
      stockOutwards.setTotalQuantity(Integer.parseInt(jsonObj.get("totalAmount").getAsString().trim()));
      stockOutwards.setOutwardName(jsonObj.get("stockoutward").getAsString());
      stockOutwards.setStateOutward(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
      int index= stockOutwardAdd.insertStockOutward(stockOutwards);
      boolean flagOutward = Boolean.parseBoolean(jsonObj.get("state").getAsString().trim());
      // insert stock inwards detail
      System.out.print("Value flag" + flagOutward);
      iStockOutwardDetailImpl stockOutwardDetailAdd = new iStockOutwardDetailImpl();
      iStockProductImpl  stockProductImpl  = new iStockProductImpl();
      String stockInwardTable = request.getParameter("1");
		JsonElement elementTable = gson.fromJson (stockInwardTable, JsonElement.class);
		JsonArray stockOurwardDetailObj = elementTable.getAsJsonArray();
		Iterator<JsonElement> it = stockOurwardDetailObj.iterator();
		List<JsonObject> list = new ArrayList<JsonObject>();
		while (it.hasNext()) {
			JsonObject temp = it.next().getAsJsonObject();
			if (!temp.toString().equals("{}")) {
				list.add(temp);
			}
		}
		System.out.println("Size List" + list.size());
		if ( list.size() > 0)
		{
			if (flagOutward == true )
			{
		for (JsonObject jsonObject : list) {
			
			StockOutwardDetail stockOutwardDetailObject = new StockOutwardDetail();
			stockOutwardDetailObject.setDiscount(jsonObject.get("discount").getAsString());
			stockOutwardDetailObject.setOutwardDetailId(1);
			stockOutwardDetailObject.setOutwardId(index);
			stockOutwardDetailObject.setNameproductId("");
			stockOutwardDetailObject.setNamestockId("");
			stockOutwardDetailObject.setNumberOutward(Integer.parseInt(jsonObject.get("quantity").getAsString().trim()));
			stockOutwardDetailObject.setPrice(new BigDecimal(jsonObject.get("itemPrice").getAsString().trim()));
			stockOutwardDetailObject.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
			stockOutwardDetailObject.setStockId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()));
			stockOutwardDetailObject.setAmount(new BigDecimal(jsonObject.get("subtotal").getAsString().trim()));
			stockOutwardDetailObject.setUnitName(jsonObject.get("unitName").getAsString());
			stockOutwardDetailAdd.insertStockOutwardDetail(stockOutwardDetailObject);
			StockProduct stockItem = new StockProduct();
			int totalAmount = stockProductImpl.getQuantitytByStockAndProductId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()), Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
			int temp = Integer.parseInt(jsonObject.get("quantity").getAsString().trim());
			int valueUpdate = totalAmount - temp;
			//System.out.println("Gia tri tam" + valueUpdate);
			stockItem.setNameproductId(" ");
			stockItem.setNamestockId(" ");
			stockItem.setNameStockProduct(" ");
			stockItem.setStockProductId(Integer.parseInt(jsonObject.get("productstock").getAsString().trim()));
			stockItem.setStockId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()));
			stockItem.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
			stockItem.setQuantity(valueUpdate);
			stockProductImpl.updateStockProduct(stockItem);
		}
		}else
		{
			for (JsonObject jsonObject : list) {
				
				StockOutwardDetail stockOutwardDetailObject = new StockOutwardDetail();
				stockOutwardDetailObject.setDiscount(jsonObject.get("discount").getAsString());
				stockOutwardDetailObject.setOutwardDetailId(1);
				stockOutwardDetailObject.setOutwardId(index);
				stockOutwardDetailObject.setNameproductId("");
				stockOutwardDetailObject.setNamestockId("");
				stockOutwardDetailObject.setNumberOutward(Integer.parseInt(jsonObject.get("quantity").getAsString().trim()));
				stockOutwardDetailObject.setPrice(new BigDecimal(jsonObject.get("itemPrice").getAsString().trim()));
				stockOutwardDetailObject.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				stockOutwardDetailObject.setStockId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()));
				stockOutwardDetailObject.setAmount(new BigDecimal(jsonObject.get("subtotal").getAsString().trim()));
				stockOutwardDetailObject.setUnitName(jsonObject.get("unitName").getAsString());
				stockOutwardDetailAdd.insertStockOutwardDetail(stockOutwardDetailObject);
				
			}
			
			
		}
			
		}
      
      
		
      
		  System.out.println("Gia tri index" + index);  
		  String str= "";
		  if (index != 0) {
				str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
			} else {
				str = "{\"error\": \"2\",\"message\": \"Insert failed. Please try again.\"}";
			}
			return str;
	
	}
	
	
	
}

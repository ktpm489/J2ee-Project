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

import com.app.dao.iProductImpl;
import com.app.dao.iProductUnitImpl;
import com.app.dao.iProviderImpl;
import com.app.dao.iStaffImpl;
import com.app.dao.iStockImpl;
import com.app.dao.iStockInwardDetailImpl;
import com.app.dao.iStockInwardImpl;
import com.app.dao.iStockProductImpl;
import com.app.object.Product;
import com.app.object.ProductUnit;
import com.app.object.Provider;
import com.app.object.Staff;
import com.app.object.State;
import com.app.object.Stock;
import com.app.object.StockInward;
import com.app.object.StockInwardDetail;
import com.app.object.StockProduct;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class StockInwardController {

	boolean flagPreviousStockInward = false;
	ArrayList<StockInwardDetail> stockInwardDetailList = new ArrayList<StockInwardDetail>();
	iStockInwardImpl stockInwardImpl = new iStockInwardImpl();
	StockInward stockInwardTest = new StockInward();
	iStockInwardDetailImpl stockInwardDetailImpl = new iStockInwardDetailImpl();
	int idStockInwardForward = 0;
	//page List Stock Inward
	@RequestMapping(value = {"/listStockInwardPage"}, method = RequestMethod.GET)
	public ModelAndView listStockCheck(ModelMap model) throws Exception {
		 ModelAndView mav = new ModelAndView();
      ArrayList<StockInward> stockInwardListLoad = new ArrayList<StockInward>();
      stockInwardListLoad = stockInwardImpl.getAllStockInward();
			 model.addAttribute("stockInwardListLoad", stockInwardListLoad);
			mav.setViewName("listStockInward");
				return mav;
	}
	
	//process Delete Stock Inward

		@RequestMapping(value = "/deleteStockInward", method = RequestMethod.POST, produces = "application/json")
		public void changeStatus(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			StockInward stockObject = new StockInward();
			stockObject.setInwardId(Integer.parseInt(id));
			boolean flag =stockInwardImpl.deleteStock(stockObject);
			System.out.println("Gia tri id: " + id + "Xoa" + flag);
		
		}
	
	// process to Page Edit Stock Inward
		@RequestMapping(value = "/editStockInwardForward", method = RequestMethod.POST, produces = "application/json")
		public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			idStockInwardForward = Integer.parseInt(id);
			System.out.println("Gia tri id: " + idStockInwardForward);
			 String str= "";
			 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
			 return str;
		}	
		
	
	//page Edit Stock Inward
	@RequestMapping(value = {"/pageEditStockInward"}, method = RequestMethod.GET)
	public ModelAndView editStockInWardPage(ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();
		 System.out.println("Gia tri idStockInwardForward: " + idStockInwardForward );
		ArrayList<State>  stateList = new ArrayList<State>();
		State objectState = new State();
		stockInwardTest = stockInwardImpl.getByID(idStockInwardForward);		 
		 flagPreviousStockInward = stockInwardTest.getStateInward();
		   String t = " ";
		   if ( flagPreviousStockInward == true)
		   {
			   t = "Active";
		   }else
		   {
			   t = " Waitting Active";
		   }
		   System.out.println("State Stock Inward" + flagPreviousStockInward);
		   stockInwardDetailList.clear();
		   stockInwardDetailList = stockInwardDetailImpl.getAllStockInwardDetail(idStockInwardForward);
		   objectState.setNameValue(t);
		   objectState.setValue(flagPreviousStockInward);   
		   stateList.add(objectState);
			 model.addAttribute("stocktransfer", stockInwardTest.getInwardName());
			 model.addAttribute("provider", stockInwardTest.getNameproviderId());
			 model.addAttribute("staff", stockInwardTest.getNamestaffId());
			 model.addAttribute("date", stockInwardTest.getDateInward());
			 model.addAttribute("amount", stockInwardTest.getTotalNumber());
			 model.addAttribute("price", stockInwardTest.getTotalAmount());
			 model.addAttribute("stateList", stateList);
			 model.addAttribute("note", stockInwardTest.getDescript());
			 model.addAttribute("stockInwardDetailList", stockInwardDetailList);
			mav.setViewName("editStockInward");
			return mav;
		
		
	}
	
	//update stock inward
		@RequestMapping(value = "/editStockInwardData", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
		public final @ResponseBody  String editStockCheck11(HttpServletRequest request) throws Exception
		{
			
			String stockInwardEditDetail = request.getParameter("0");
			Gson gson = new Gson();
			JsonElement element = gson.fromJson (stockInwardEditDetail, JsonElement.class);
			JsonObject jsonObj = element.getAsJsonObject();
			stockInwardTest.setInwardName(jsonObj.get("stockedit").getAsString()); 
			stockInwardTest.setStateInward(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
	        boolean flagUpdate = stockInwardImpl.updateStock(stockInwardTest);
	       boolean flagGetUpdate = Boolean.parseBoolean(jsonObj.get("state").getAsString().trim());
	       int sizeListStockInwardDetail = stockInwardDetailList.size();
	       iStockProductImpl istockProductInwardEdit = new iStockProductImpl();
	       // Th1
	       if (flagPreviousStockInward == false && flagGetUpdate == true)
	       {
	    	   if(sizeListStockInwardDetail == 0)
	    	   {
	    		   
	    	   }else
	    	   {
	    		   for (int i = 0 ; i < stockInwardDetailList.size() ; i++)
	    		   {
	    			   StockInwardDetail stockInwardDetailObj = new StockInwardDetail();
	    			   stockInwardDetailObj = stockInwardDetailList.get(i);
	    			   StockProduct stockProductObj = new StockProduct();
	    			   int totalAmountTo = istockProductInwardEdit.getQuantitytByStockAndProductId(stockInwardDetailObj.getStockId(), stockInwardDetailObj.getProductId());
	    			  int id = istockProductInwardEdit.getStockProductId(stockInwardDetailObj.getStockId(), stockInwardDetailObj.getProductId());
	    			   
	    			   if ( id != 0)
	    			   {
	    				   int quantityAdd = stockInwardDetailObj.getNumberInward();
	    				   int quantityUpdate = quantityAdd + totalAmountTo;
	    				   stockProductObj.setNameproductId(" ");
	    				   stockProductObj.setNamestockId(" ");
	    				   stockProductObj.setNameStockProduct(" ");
	    				   stockProductObj.setStockProductId(1);
	    				   stockProductObj.setStockId(stockInwardDetailObj.getStockId());
	    				   stockProductObj.setProductId(stockInwardDetailObj.getProductId());
	    				   stockProductObj.setQuantity(quantityUpdate);
	    				   istockProductInwardEdit.updateStockProduct(stockProductObj);
	    			   }else
	    			   {
	    				   int quantityInsert =  stockInwardDetailObj.getNumberInward();
	    				   stockProductObj.setNameproductId(" ");
	    				   stockProductObj.setNamestockId(" ");
	    				   stockProductObj.setNameStockProduct(" ");
	    				   stockProductObj.setStockProductId(1);
	    				   stockProductObj.setStockId(stockInwardDetailObj.getStockId());
	    				   stockProductObj.setProductId(stockInwardDetailObj.getProductId());
	    				   stockProductObj.setQuantity(quantityInsert);
	    				   istockProductInwardEdit.insertStockProduct(stockProductObj);
	    				      
	    			   }
	    			   
	    		   }
	    	   }
	    	       	   
	       }else if ( flagPreviousStockInward == true && flagGetUpdate == false)
	       {
				if(sizeListStockInwardDetail == 0)
		    	   {
		    		   
		    	   }else
		    	   {
		    		   for (int i = 0 ; i < stockInwardDetailList.size() ; i++)
		    		   {
		    			   StockInwardDetail stockInwardDetailObj = new StockInwardDetail();
		    			   stockInwardDetailObj = stockInwardDetailList.get(i);
		    			   StockProduct stockProductObj1 = new StockProduct();
		    			   int totalAmountTo = istockProductInwardEdit.getQuantitytByStockAndProductId(stockInwardDetailObj.getStockId(), stockInwardDetailObj.getProductId());
		    			   int id = istockProductInwardEdit.getStockProductId(stockInwardDetailObj.getStockId(), stockInwardDetailObj.getProductId());
		    			   if ( id != 0)
		    			   {
		    				   int quantitySub = stockInwardDetailObj.getNumberInward();
		    				   int quantityUpdate =  totalAmountTo - quantitySub ;
		    				   stockProductObj1.setNameproductId(" ");
		    				   stockProductObj1.setNamestockId(" ");
		    				   stockProductObj1.setNameStockProduct(" ");
		    				   stockProductObj1.setStockProductId(1);
		    				   stockProductObj1.setStockId(stockInwardDetailObj.getStockId());
		    				   stockProductObj1.setProductId(stockInwardDetailObj.getProductId());
		    				   stockProductObj1.setQuantity(quantityUpdate);
		    				   istockProductInwardEdit.updateStockProduct(stockProductObj1);
		    			   }else
		    			   {
		    				   System.out.println("Loi Edit Inward");    				      
		    			   }
		    			   
		    		   }
		    	   }   	   
	    	   
	       }
	       flagPreviousStockInward = stockInwardTest.getStateInward();
			  String str= "";  
			  if (flagUpdate == true) {
					str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
				} else {
					str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
				}
				return str;
		
		}

		//print stock inward
				@RequestMapping(value = "/editStockInwardDataPrint", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
				public final @ResponseBody  void printStockInward(HttpServletRequest request) throws Exception
				{
					
					String stockInwardEditDetail = request.getParameter("0");
					Gson gson = new Gson();
					JsonElement element = gson.fromJson (stockInwardEditDetail, JsonElement.class);
					JsonObject jsonObj = element.getAsJsonObject();
					stockInwardTest.setInwardName(jsonObj.get("stockedit").getAsString()); 
					stockInwardTest.setStateInward(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
					 stockInwardDetailList.clear();
					   stockInwardDetailList = stockInwardDetailImpl.getAllStockInwardDetail(idStockInwardForward);
				   new com.app.inventory.ReportInward().runReport("I://Test//inward.jrxml", "I://Test//InwardReport.pdf",stockInwardTest,stockInwardDetailList);
				}	
		
		
		//page InsertInward
	@RequestMapping(value = {"/pageInsertStockInward"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) throws Exception {
		//logger.info("Welcome home! The client locale is {}.", locale);
		//iStockImpl ProductList = new iStockImpl();
		 ModelAndView mav = new ModelAndView();
		 iStaffImpl iStaff = new iStaffImpl();
		 List<Staff> staffList = new ArrayList<Staff>();
		 iProviderImpl iProvider = new iProviderImpl();
		 List<Provider> providerList = new ArrayList<Provider>();
		 staffList = iStaff.getAllStaff();
		 iProductImpl iProduct = new iProductImpl();
		 List<Product> productList = new ArrayList<Product>();
		 productList = iProduct.getAllProduct();
		 providerList = iProvider.getAllProvider();
		 iProductUnitImpl productUnit = new iProductUnitImpl();
		 List<ProductUnit> productUnitList = new ArrayList<ProductUnit>();
		 productUnitList = productUnit.getAllProductUnit();
		 iStockImpl istock  = new iStockImpl();
		 List<Stock> istockList=  new ArrayList<Stock>();
		 istockList = istock.getAllStockActive();
		 model.addAttribute("istockList", istockList );
		 model.addAttribute("staffList", staffList );
		 model.addAttribute("productUnitList", productUnitList );
		 model.addAttribute("productList", productList );
		 model.addAttribute("providerList", providerList );
			mav.setViewName("addStockInward");
			return mav;
	}
	
	@RequestMapping(value = "/addStockInward", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		//insert stockinwards
		String stockInwardDetail = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (stockInwardDetail, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
       iStockInwardImpl stockInwardAdd = new iStockInwardImpl(); 
      StockInward stockInwards = new StockInward();
      stockInwards.setDateInward(Date.valueOf(jsonObj.get("date").getAsString()));
      stockInwards.setDescript(jsonObj.get("note").getAsString());
      stockInwards.setInwardId(1);
      stockInwards.setNameproviderId("");
      stockInwards.setNamestaffId("");
      stockInwards.setNamestockId("");
      stockInwards.setStockId(Integer.parseInt(jsonObj.get("stock").getAsString().trim()));
      stockInwards.setProviderId(Integer.parseInt(jsonObj.get("supplier").getAsString().trim()));
      stockInwards.setStaffId(Integer.parseInt(jsonObj.get("staff").getAsString().trim()));
      stockInwards.setTotalAmount(new BigDecimal(jsonObj.get("totalprice").getAsString().trim()));
      stockInwards.setTotalNumber(Integer.parseInt(jsonObj.get("totalAmount").getAsString().trim()));
      stockInwards.setVoucher(" ");
      stockInwards.setStateInward(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
      stockInwards.setInwardName(jsonObj.get("stockinward").getAsString());
      int index= stockInwardAdd.insertStock(stockInwards);
	  
      // insert stock inwards detail
      boolean flagInward = Boolean.parseBoolean(jsonObj.get("state").getAsString().trim());
      System.out.print("Value flag" + flagInward);
      iStockInwardDetailImpl stockInwardDetailAdd = new iStockInwardDetailImpl();
      iStockProductImpl  stockProductImpl  = new iStockProductImpl();
      String stockInwardTable = request.getParameter("1");
		JsonElement elementTable = gson.fromJson (stockInwardTable, JsonElement.class);
		JsonArray stockInwardDetailObj = elementTable.getAsJsonArray();
		Iterator<JsonElement> it = stockInwardDetailObj.iterator();
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
			if (flagInward == false)
		{
		for (JsonObject jsonObject : list) 
		{
			
			StockInwardDetail stockInwardDetailObject = new StockInwardDetail();
			stockInwardDetailObject.setDescript(" ");
			stockInwardDetailObject.setDiscount(jsonObject.get("discount").getAsString());
			stockInwardDetailObject.setInwardDetailId(1);
			stockInwardDetailObject.setInwardId(index);
			stockInwardDetailObject.setNameproduct("");
			stockInwardDetailObject.setNamest("");
			stockInwardDetailObject.setNumberInward(Integer.parseInt(jsonObject.get("quantity").getAsString().trim()));
			stockInwardDetailObject.setPrice(new BigDecimal(jsonObject.get("itemPrice").getAsString().trim()));
			stockInwardDetailObject.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
			stockInwardDetailObject.setStockId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()));
			stockInwardDetailObject.setTotal(new BigDecimal(jsonObject.get("subtotal").getAsString().trim()));
			stockInwardDetailObject.setUnitName(jsonObject.get("unitName").getAsString());
			stockInwardDetailAdd.insertStockInwardDetail(stockInwardDetailObject);
		}
		}else
		{
			for (JsonObject jsonObject : list) 
			{
				
				StockInwardDetail stockInwardDetailObject = new StockInwardDetail();
				stockInwardDetailObject.setDescript(" ");
				stockInwardDetailObject.setDiscount(jsonObject.get("discount").getAsString());
				stockInwardDetailObject.setInwardDetailId(1);
				stockInwardDetailObject.setInwardId(index);
				stockInwardDetailObject.setNameproduct("");
				stockInwardDetailObject.setNamest("");
				stockInwardDetailObject.setNumberInward(Integer.parseInt(jsonObject.get("quantity").getAsString().trim()));
				stockInwardDetailObject.setPrice(new BigDecimal(jsonObject.get("itemPrice").getAsString().trim()));
				stockInwardDetailObject.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				stockInwardDetailObject.setStockId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()));
				stockInwardDetailObject.setTotal(new BigDecimal(jsonObject.get("subtotal").getAsString().trim()));
				stockInwardDetailObject.setUnitName(jsonObject.get("unitName").getAsString());
				stockInwardDetailAdd.insertStockInwardDetail(stockInwardDetailObject);
			
				StockProduct stockItem1 = new StockProduct();
				int totalAmountTo = stockProductImpl.getQuantitytByStockAndProductId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()), Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				
				int id = stockProductImpl.getStockProductId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()), Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				if ( id != 0)
				{
				int temp1 = Integer.parseInt(jsonObject.get("quantity").getAsString().trim());
				int valueUpdate1 = totalAmountTo + temp1;
				System.out.println("Gia tri tam 1" + valueUpdate1);
				stockItem1.setNameproductId(" ");
				stockItem1.setNamestockId(" ");
				stockItem1.setNameStockProduct(" ");
				stockItem1.setStockProductId(1);
				stockItem1.setStockId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()));
				stockItem1.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				stockItem1.setQuantity(valueUpdate1);
				stockProductImpl.updateStockProduct(stockItem1);
				}else
				{
				int temp2 = Integer.parseInt(jsonObject.get("quantity").getAsString().trim());
				System.out.println("Gia tri tam 2" + temp2);
				stockItem1.setNameproductId(" ");
				stockItem1.setNamestockId(" ");
				stockItem1.setNameStockProduct(" ");
				stockItem1.setStockProductId(1);
				stockItem1.setStockId(Integer.parseInt(jsonObject.get("stockId").getAsString().trim()));
				stockItem1.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				stockItem1.setQuantity(temp2);
				stockProductImpl.insertStockProduct(stockItem1);
				}
				
				
				
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

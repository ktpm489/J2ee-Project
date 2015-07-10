package com.app.inventory;



import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.app.dao.iProductUnitImpl;
import com.app.dao.iStaffImpl;
import com.app.dao.iStockCheckImpl;
import com.app.dao.iStockCheckDetailImpl;
import com.app.dao.iStockImpl;
import com.app.dao.iStockProductImpl;
import com.app.object.ProductUnit;
import com.app.object.Staff;
import com.app.object.State;
import com.app.object.Stock;
import com.app.object.StockCheck;
import com.app.object.StockCheckDetail;
import com.app.object.StockProduct;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



@Controller
public class StockCheckController {

	
	iStockProductImpl istockProductImpl = new iStockProductImpl();
	iStockCheckImpl istockCheckImpl = new iStockCheckImpl();
	iStockCheckDetailImpl istockCheckDetail = new iStockCheckDetailImpl();
	StockCheck stockCheck =  new StockCheck();
	ArrayList<StockCheckDetail> stockCheckDetail = new ArrayList<StockCheckDetail>();
	ArrayList<StockProduct> stockProductList = new ArrayList<StockProduct>();
	int idStockCheck = 0;
	//list Stock Check
	@RequestMapping(value = {"/listStockCheckPage"}, method = RequestMethod.GET)
	public ModelAndView listStockCheck(ModelMap model) throws Exception {
		 ModelAndView mav = new ModelAndView();
      ArrayList<StockCheck> stockCheckListLoad = new ArrayList<StockCheck>();
      stockCheckListLoad = istockCheckImpl.getAllStockCheck();
			 model.addAttribute("stockCheckListLoad", stockCheckListLoad);
			mav.setViewName("listStockCheck");
				return mav;
	}
	//process Delete Stock Check
	@RequestMapping(value = "/deleteStockCheck", method = RequestMethod.POST, produces = "application/json")
	public void changeStatus(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		StockCheck stockObject = new StockCheck();
		stockObject.setCheckId(Integer.parseInt(id));
		boolean flag =istockCheckImpl.deleteStockCheck(stockObject);
		System.out.println("Gia tri id: " + id + "Xoa" + flag);
	
	}
	@RequestMapping(value = "/editStockCheck", method = RequestMethod.POST, produces = "application/json")
	public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		idStockCheck = Integer.parseInt(id);
		System.out.println("Gia tri id: " + idStockCheck);
		 String str= "";
		 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
		 return str;
	}
	
	
	// edit Stock Check
	@RequestMapping(value ="/editStockCheckPage", method = RequestMethod.GET)
	public ModelAndView home1(ModelMap model ,HttpServletRequest request) throws Exception {
		 ModelAndView mav = new ModelAndView();

		 System.out.println("Gia tri id: " + idStockCheck );
			ArrayList<State>  stateList = new ArrayList<State>();
			State objectState = new State();
		   stockCheck = istockCheckImpl.getByID(idStockCheck);
		   boolean flag = stockCheck.getStateCheck();
		   String t = " ";
		   if ( flag == true)
		   {
			   t = "Active";
		   }else
		   {
			   t = " Waitting Active";
		   }
		   stockCheckDetail.clear();
		   stockCheckDetail = istockCheckDetail.getAllStockCheckDetailByCheckId(idStockCheck);
		   objectState.setNameValue(t);
		   objectState.setValue(flag);   
		   stateList.add(objectState);
			 model.addAttribute("stockcheck", stockCheck.getCheckName() );
			 model.addAttribute("stockname", stockCheck.getNamestockId());
			 model.addAttribute("datetransfer", stockCheck.getDateCheck().toString() );
			 model.addAttribute("staffname", stockCheck.getStaffName());
			 model.addAttribute("stateList", stateList);
			 model.addAttribute("note", stockCheck.getDescription());
			 model.addAttribute("stockCheckDetail", stockCheckDetail);
			mav.setViewName("editStockCheck");
				return mav;
	}
	// new Stock Check
	@RequestMapping(value ="/addNewStockCheck", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) throws Exception {
		 ModelAndView mav = new ModelAndView();
			iStaffImpl iStaff = new iStaffImpl();
			 List<Staff> staffList = new ArrayList<Staff>();
			 iStockImpl istock = new iStockImpl();
			 List<Stock> stockList = new ArrayList<Stock>();
			 staffList = iStaff.getAllStaff();
			// iStockProduct = new iStockProductImpl();
			 //List<StockProduct> productStockList = new ArrayList<StockProduct>();
			 //productStockList = iStockProduct.getAllProductWithStock();
			 stockList = istock.getAllStock();
			 iProductUnitImpl productUnit = new iProductUnitImpl();
			 List<ProductUnit> productUnitList = new ArrayList<ProductUnit>();
			 productUnitList = productUnit.getAllProductUnit();
			 model.addAttribute("staffList", staffList );
			 model.addAttribute("productUnitList", productUnitList );
			// model.addAttribute("productList", productStockList );
			 model.addAttribute("stockList", stockList );
				mav.setViewName("addStockCheck");
				return mav;
	}
	@RequestMapping(value = "/dataStockCheck", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody String getFile(HttpServletRequest request) throws Exception
	{
		String stockInfo = request.getParameter("0");
		//JsonObject stockInwardObj = new Gson().fromJson(stockInward,JsonObject.class);
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (stockInfo, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		//String dateFormat = jsonObj.get("name").getAsString();
		stockProductList.clear();
		int stockId = Integer.parseInt(jsonObj.get("value").getAsString().trim());
		System.out.println("Value Stock Id" + stockId);
		stockProductList =  istockProductImpl.getByStockID(stockId);
		String str = " ";
		// JSONObject result = new JSONObject();
		// JSONObject result1 = new JSONObject();
		 JSONArray table = new JSONArray();
		 if (stockProductList.size() > 0 )
		 {	 
		 try
		 {
			 for (int i = 0; i < stockProductList.size(); i++) 
			 {
				StockProduct t = stockProductList.get(i);
				JSONObject sampleInnerElement = new JSONObject();
			    sampleInnerElement.put("nameproductId", t.getNameproductId());
			    sampleInnerElement.put("quantity", t.getQuantity());
			    table.put(sampleInnerElement);
			 }
			// result1.put("status","0");
            // result1.put("table",table);
             //result.put("result", result1);
             str = table.toString();
			 
		 }catch (Exception e)
		 {
             e.printStackTrace();
         }
		 
		 }
		 System.out.println("Value String:" + str);
		return str;
	}
	
	//print stock check
		@RequestMapping(value = "/editStockCheckDataPrint", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
		public final @ResponseBody  void printStockCheckData(HttpServletRequest request) throws Exception
		{
			//insert stockinwards
			String stockCheckEditDetail = request.getParameter("0");
			Gson gson = new Gson();
			JsonElement element = gson.fromJson (stockCheckEditDetail, JsonElement.class);
			JsonObject jsonObj = element.getAsJsonObject();
	      stockCheck.setCheckName(jsonObj.get("stockcheck").getAsString()); 
	      stockCheck.setStateCheck(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));

	       new com.app.inventory.ReportCheck().runReport("I://Test//check.jrxml", "I://Test//Check.pdf",stockCheck,stockCheckDetail);
		}
		
	
	@RequestMapping(value = "/addStockCheck", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		//insert stockinwards
		String stockCheckDetail = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (stockCheckDetail, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
       iStockCheckImpl stockCheckAdd = new iStockCheckImpl(); 
      StockCheck stockCheck = new StockCheck();
      stockCheck.setCheckId(1);
      stockCheck.setCheckName(jsonObj.get("stockcheck").getAsString());
      stockCheck.setDateCheck(Date.valueOf(jsonObj.get("fullDate").getAsString()));
      stockCheck.setDescription(jsonObj.get("note").getAsString());
      stockCheck.setNamestockId(" ");
      stockCheck.setStaffId(Integer.parseInt(jsonObj.get("staff").getAsString().trim()));
      stockCheck.setStaffName(" ");
      stockCheck.setStateCheck(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
      stockCheck.setStockId(Integer.parseInt(jsonObj.get("stock").getAsString().trim()));
        int index = stockCheckAdd.insertStockCheck(stockCheck);
        int sizeProductList = stockProductList.size();
        System.out.println("Size List" + sizeProductList);
       iStockCheckDetailImpl stockCheckDetailListAdd = new iStockCheckDetailImpl();
        if (sizeProductList == 0)
        {
        	
        }else
        {
        	for ( int i = 0 ; i < sizeProductList; i++)
        	{
        		StockProduct tempStockProduct = new StockProduct();
        		tempStockProduct = stockProductList.get(i);
        	   StockCheckDetail stockCheckObj = new StockCheckDetail();
        	   stockCheckObj.setCheckID(index);
        	   stockCheckObj.setCheckInwardID(1);
        	   stockCheckObj.setDescript("");
        	   stockCheckObj.setNameproductId(" ");
        	   stockCheckObj.setNamestockId(" ");
        	   stockCheckObj.setDiscount(" ");
        	   stockCheckObj.setNumbercheckInward(tempStockProduct.getQuantity());
        	   stockCheckObj.setPrice(new BigDecimal("0.0"));
        	   stockCheckObj.setProductId(tempStockProduct.getProductId());
        	   stockCheckObj.setStockId(tempStockProduct.getStockId());
        	   stockCheckObj.setTotal(new BigDecimal("0.0"));
        	   stockCheckObj.setUnitName(" ");
        	   stockCheckDetailListAdd.insertStockCheckDetail(stockCheckObj);			
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
	
	
	//update stock check
	@RequestMapping(value = "/editStockCheckData", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String editStockCheck11(HttpServletRequest request) throws Exception
	{
		//insert stockinwards
		String stockCheckEditDetail = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (stockCheckEditDetail, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
      stockCheck.setCheckName(jsonObj.get("stockcheck").getAsString()); 
      stockCheck.setStateCheck(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
        boolean flagUpdate = istockCheckImpl.updateStockCheck(stockCheck);
       
		  String str= "";  
		  if (flagUpdate == true) {
				str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
			} else {
				str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
			}
			return str;
	
	}
	
	
	
	
	
	
	
	
}

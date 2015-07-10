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

import com.app.dao.iProductUnitImpl;
import com.app.dao.iStaffImpl;
import com.app.dao.iStockImpl;
import com.app.dao.iStockProductImpl;
import com.app.dao.iStockTransferDetailImpl;
import com.app.dao.iStockTransferImpl;
import com.app.object.ProductUnit;
import com.app.object.Staff;
import com.app.object.State;
import com.app.object.Stock;
import com.app.object.StockProduct;
import com.app.object.StockTransfer;
import com.app.object.StockTransferDetail;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class StockTransferController {

	int idStockStranferForward = 0;
	boolean flagPreviousStockTransfer = false;
	iStockTransferImpl istockTransferImpl = new iStockTransferImpl();
	iStockTransferDetailImpl iStockTransferDetailImpl = new iStockTransferDetailImpl();
	ArrayList<StockTransferDetail> istockTransferDetailList = new ArrayList<StockTransferDetail>();
	StockTransfer stockTransfer = new StockTransfer();
	
	   //Stock Check List Page
		@RequestMapping(value = {"/listStockTransferPage"}, method = RequestMethod.GET)
		public ModelAndView listStockTransferPage(ModelMap model) throws Exception {
			 ModelAndView mav = new ModelAndView();
			 ArrayList<StockTransfer> stockTransferList = new ArrayList<StockTransfer>();
			 stockTransferList = istockTransferImpl.getAllStockTransfer();
				 model.addAttribute("stockTransferList", stockTransferList);
				mav.setViewName("listStockTransfer");
					return mav;
		}
		
	// process Detete Stock Transfer
		@RequestMapping(value = "/deleteStockTransfer", method = RequestMethod.POST, produces = "application/json")
		public void changeStatus(HttpServletRequest request) throws Exception {
			String id = request.getParameter("id");
			StockTransfer stockTransfer = new StockTransfer();
			stockTransfer.setTransferID(Integer.parseInt(id));
			boolean flag = istockTransferImpl.deleteStockTransfer(stockTransfer);
			System.out.println("Gia tri id: " + id + "Xoa" + flag);	
		}
	
		// process to Page Edit Stock Transfer
		@RequestMapping(value = "/editStockTransferForward", method = RequestMethod.POST, produces = "application/json")
		public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
					String id = request.getParameter("id");
					idStockStranferForward = Integer.parseInt(id);
					System.out.println("Gia tri id: " + idStockStranferForward);
					 String str= "";
					 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
					 return str;
		}	
		
	
	// edit Stock Check Transfer Page
	@RequestMapping(value = { "/editStockTransferPage" }, method = RequestMethod.GET)
	public ModelAndView listStockCheck(ModelMap model) throws Exception {
		ModelAndView mav = new ModelAndView();
		// ArrayList<StockInward> stockInwardListLoad = new
		// ArrayList<StockInward>();
		// stockInwardListLoad = stockInwardImpl.getAllStockInward();
		
		stockTransfer = istockTransferImpl.getByID(idStockStranferForward);
		ArrayList<State> stateList = new ArrayList<State>();
		State objectState = new State();
		flagPreviousStockTransfer = stockTransfer.getStateTransfered();
		String t = " ";
		if (flagPreviousStockTransfer == true) {
			t = "Active";
		} else {
			t = " Waitting Active";
		}
		System.out.println("State Stock flagPreviousStockTransfer"
				+ flagPreviousStockTransfer);
		istockTransferDetailList.clear();
		istockTransferDetailList = iStockTransferDetailImpl
				.getAllStockTransferDetail(idStockStranferForward);
		objectState.setNameValue(t);
		objectState.setValue(flagPreviousStockTransfer);
		stateList.add(objectState);
		model.addAttribute("stocktransfer", stockTransfer.getTransferName());
		model.addAttribute("staffname", stockTransfer.getNameStaffId());
		model.addAttribute("datetransfer", stockTransfer.getDateTransfered());
		model.addAttribute("stateList", stateList);
		model.addAttribute("totalquantity", stockTransfer.getTotalNumber());
		model.addAttribute("totalprice", stockTransfer.getTotalAmount());
		model.addAttribute("note", stockTransfer.getDescription());
		model.addAttribute("stockTransferList", istockTransferDetailList);
		mav.setViewName("editStockTransfer");
		return mav;
	}

	// update stock transfer
	@RequestMapping(value = "/editStockTransferData", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody String editStockCheck11(HttpServletRequest request) throws Exception 
	{

		String stockTransferEditDetail = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(stockTransferEditDetail,
				JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();

		stockTransfer.setTransferName(jsonObj.get("transfercode").getAsString());
		stockTransfer.setStateTransfered(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
		boolean flagUpdate = istockTransferImpl.updateStockTransfer(stockTransfer);
		boolean flagGetUpdate = Boolean.parseBoolean(jsonObj.get("state").getAsString().trim());
		int sizeListStockTransferDetail = istockTransferDetailList.size();
		iStockProductImpl istockProductTransferEdit = new iStockProductImpl();
		
		
		// Th1
		if (flagPreviousStockTransfer == false && flagGetUpdate == true) {
			if (sizeListStockTransferDetail == 0) {

			} else
			{
				for (int i = 0; i < istockTransferDetailList.size(); i++) 
				{
					StockTransferDetail stockTransferDetailObj = new StockTransferDetail();
					stockTransferDetailObj = istockTransferDetailList.get(i);
					StockProduct stockProductFromObj = new StockProduct();
					StockProduct stockProductToObj = new StockProduct();
					int totalAmountCurrentStockFrom = istockProductTransferEdit.getQuantitytByStockAndProductId(stockTransferDetailObj.getStockFromId(), stockTransferDetailObj.getProductId());
					int idFrom = istockProductTransferEdit.getQuantitytByStockAndProductId(stockTransferDetailObj.getStockFromId(), stockTransferDetailObj.getProductId());
					int totalAmountCurrentStockTo = istockProductTransferEdit.getQuantitytByStockAndProductId(stockTransferDetailObj.getStockToId(), stockTransferDetailObj.getProductId());
					int idTo = istockProductTransferEdit.getQuantitytByStockAndProductId(stockTransferDetailObj.getStockToId(), stockTransferDetailObj.getProductId());
					//from stock
					if (idFrom != 0) 
					{
						  int quantitySub = stockTransferDetailObj.getNumberTransfer();
	    				   int quantityUpdate = totalAmountCurrentStockFrom - quantitySub;
	    				   stockProductFromObj.setNameproductId(" ");
	    				   stockProductFromObj.setNamestockId(" ");
	    				   stockProductFromObj.setNameStockProduct(" ");
	    				   stockProductFromObj.setStockProductId(1);
	    				   stockProductFromObj.setStockId(stockTransferDetailObj.getStockFromId());
	    				   stockProductFromObj.setProductId(stockTransferDetailObj.getProductId());
	    				   stockProductFromObj.setQuantity(quantityUpdate);
	    				   istockProductTransferEdit.updateStockProduct(stockProductFromObj);
						
					} else
					{
						   int quantitySub = stockTransferDetailObj.getNumberTransfer();
	    				   int quantityUpdate = -quantitySub;
	    				   stockProductFromObj.setNameproductId(" ");
	    				   stockProductFromObj.setNamestockId(" ");
	    				   stockProductFromObj.setNameStockProduct(" ");
	    				   stockProductFromObj.setStockProductId(1);
	    				   stockProductFromObj.setStockId(stockTransferDetailObj.getStockFromId());
	    				   stockProductFromObj.setProductId(stockTransferDetailObj.getProductId());
	    				   stockProductFromObj.setQuantity(quantityUpdate);
	    				   istockProductTransferEdit.insertStockProduct(stockProductFromObj);
							
					}
					//to stock
					if (idTo != 0) 
					{
						   int quantityAdd = stockTransferDetailObj.getNumberTransfer();
	    				   int quantityUpdate = totalAmountCurrentStockTo + quantityAdd;
	    				   stockProductToObj.setNameproductId(" ");
	    				   stockProductToObj.setNamestockId(" ");
	    				   stockProductToObj.setNameStockProduct(" ");
	    				   stockProductToObj.setStockProductId(1);
	    				   stockProductToObj.setStockId(stockTransferDetailObj.getStockToId());
	    				   stockProductToObj.setProductId(stockTransferDetailObj.getProductId());
	    				   stockProductToObj.setQuantity(quantityUpdate);
	    				   istockProductTransferEdit.updateStockProduct(stockProductToObj);
						
					} else
					{
						   int quantityAdd = stockTransferDetailObj.getNumberTransfer();
	    				   int quantityUpdate = quantityAdd;
	    				   stockProductToObj.setNameproductId(" ");
	    				   stockProductToObj.setNamestockId(" ");
	    				   stockProductToObj.setNameStockProduct(" ");
	    				   stockProductToObj.setStockProductId(1);
	    				   stockProductToObj.setStockId(stockTransferDetailObj.getStockToId());
	    				   stockProductToObj.setProductId(stockTransferDetailObj.getProductId());
	    				   stockProductToObj.setQuantity(quantityUpdate);
	    				   istockProductTransferEdit.insertStockProduct(stockProductToObj);

					}
					

				}
			}

		} else if (flagPreviousStockTransfer == true && flagGetUpdate == false) 
		{
			if (sizeListStockTransferDetail == 0) 
			{

			} else 
			{
				for (int i = 0; i < istockTransferDetailList.size(); i++) 
				{
					StockTransferDetail stockTransferDetailObj = new StockTransferDetail();
					stockTransferDetailObj = istockTransferDetailList.get(i);
					StockProduct stockProductFromObj = new StockProduct();
					StockProduct stockProductToObj = new StockProduct();
					int totalAmountCurrentStockFrom = istockProductTransferEdit.getQuantitytByStockAndProductId(stockTransferDetailObj.getStockFromId(), stockTransferDetailObj.getProductId());
					int totalAmountCurrentStockTo = istockProductTransferEdit.getQuantitytByStockAndProductId(stockTransferDetailObj.getStockToId(), stockTransferDetailObj.getProductId());
					int idFrom = istockProductTransferEdit.getQuantitytByStockAndProductId(stockTransferDetailObj.getStockFromId(), stockTransferDetailObj.getProductId());
					int idTo = istockProductTransferEdit.getQuantitytByStockAndProductId(stockTransferDetailObj.getStockToId(), stockTransferDetailObj.getProductId());
					//from stock
					if (idFrom != 0) 
					{
						  int quantityAdd = stockTransferDetailObj.getNumberTransfer();
	    				   int quantityUpdate = totalAmountCurrentStockFrom + quantityAdd;
	    				   stockProductFromObj.setNameproductId(" ");
	    				   stockProductFromObj.setNamestockId(" ");
	    				   stockProductFromObj.setNameStockProduct(" ");
	    				   stockProductFromObj.setStockProductId(1);
	    				   stockProductFromObj.setStockId(stockTransferDetailObj.getStockFromId());
	    				   stockProductFromObj.setProductId(stockTransferDetailObj.getProductId());
	    				   stockProductFromObj.setQuantity(quantityUpdate);
	    				   istockProductTransferEdit.updateStockProduct(stockProductFromObj);
						
					} else
					{
						   int quantityAdd = stockTransferDetailObj.getNumberTransfer();
	    				   int quantityUpdate = quantityAdd;
	    				   stockProductFromObj.setNameproductId(" ");
	    				   stockProductFromObj.setNamestockId(" ");
	    				   stockProductFromObj.setNameStockProduct(" ");
	    				   stockProductFromObj.setStockProductId(1);
	    				   stockProductFromObj.setStockId(stockTransferDetailObj.getStockFromId());
	    				   stockProductFromObj.setProductId(stockTransferDetailObj.getProductId());
	    				   stockProductFromObj.setQuantity(quantityUpdate);
	    				   istockProductTransferEdit.insertStockProduct(stockProductFromObj);
							
					}
					//to stock
					if (idTo != 0) 
					{
						   int quantitySub = stockTransferDetailObj.getNumberTransfer();
	    				   int quantityUpdate = totalAmountCurrentStockTo - quantitySub;
	    				   stockProductToObj.setNameproductId(" ");
	    				   stockProductToObj.setNamestockId(" ");
	    				   stockProductToObj.setNameStockProduct(" ");
	    				   stockProductToObj.setStockProductId(1);
	    				   stockProductToObj.setStockId(stockTransferDetailObj.getStockToId());
	    				   stockProductToObj.setProductId(stockTransferDetailObj.getProductId());
	    				   stockProductToObj.setQuantity(quantityUpdate);
	    				   istockProductTransferEdit.updateStockProduct(stockProductToObj);
						
					} else
					{
						   int quantitySub = stockTransferDetailObj.getNumberTransfer();
	    				   int quantityUpdate = -quantitySub;
	    				   stockProductToObj.setNameproductId(" ");
	    				   stockProductToObj.setNamestockId(" ");
	    				   stockProductToObj.setNameStockProduct(" ");
	    				   stockProductToObj.setStockProductId(1);
	    				   stockProductToObj.setStockId(stockTransferDetailObj.getStockToId());
	    				   stockProductToObj.setProductId(stockTransferDetailObj.getProductId());
	    				   stockProductToObj.setQuantity(quantityUpdate);
	    				   istockProductTransferEdit.insertStockProduct(stockProductToObj);

					}

			}
			
		}
			
		}
		flagPreviousStockTransfer = stockTransfer.getStateTransfered();
		String str = "";
		if (flagUpdate == true) 
		{
			str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
		} else {
			str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
		}
		return str;
		
	};
	
	
	// print stock transfer
		@RequestMapping(value = "/editStockTransferDataPrint", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
		public final @ResponseBody void printStockTransfer(HttpServletRequest request) throws Exception 
		{

			String stockTransferEditDetail = request.getParameter("0");
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(stockTransferEditDetail,
					JsonElement.class);
			JsonObject jsonObj = element.getAsJsonObject();
			stockTransfer.setTransferName(jsonObj.get("transfercode").getAsString());
			stockTransfer.setStateTransfered(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
			new com.app.inventory.ReportTransfer().runReport("I://Test//transfer1.jrxml", "I://Test//Transfer.pdf",stockTransfer,istockTransferDetailList);
		};
	
	// add New Stock Transfer Page
	@RequestMapping(value = {"/addstockTransferPage"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) throws Exception {

		ModelAndView mav = new ModelAndView();
		iStaffImpl iStaff = new iStaffImpl();
		List<Staff> staffList = new ArrayList<Staff>();
		staffList = iStaff.getAllStaff();
		iStockProductImpl iStockProduct = new iStockProductImpl();
		List<StockProduct> productStockList = new ArrayList<StockProduct>();
		productStockList = iStockProduct.getAllProductWithStock();
		iProductUnitImpl productUnit = new iProductUnitImpl();
		List<ProductUnit> productUnitList = new ArrayList<ProductUnit>();
		productUnitList = productUnit.getAllProductUnit();
		iStockImpl stockImpl = new iStockImpl();
		List<Stock> stockListActive = new ArrayList<Stock>();
		stockListActive = stockImpl.getAllStockActive();
		model.addAttribute("staffList", staffList);
		model.addAttribute("stockList", stockListActive);
		model.addAttribute("productUnitList", productUnitList);
		model.addAttribute("productList", productStockList);
		mav.setViewName("addStockTransfer");
		return mav;
	}

	@RequestMapping(value = "/addStockTransfer", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody String addProduct(HttpServletRequest request)
			throws Exception {
		String stockTransferDetail = request.getParameter("0");
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(stockTransferDetail,JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		iStockTransferImpl stockTransferdAdd = new iStockTransferImpl();
		StockTransfer stocktransfer = new StockTransfer();
		stocktransfer.setDateTransfered(Date.valueOf(jsonObj.get("date").getAsString()));
		stocktransfer.setDescription(jsonObj.get("note").getAsString().trim());
		stocktransfer.setNameStaffId("");
		stocktransfer.setNameStockId("");
		stocktransfer.setStaffId(Integer.parseInt(jsonObj.get("staff").getAsString().trim()));
		stocktransfer.setStockId(1);
		stocktransfer.setStateTransfered(Boolean.parseBoolean(jsonObj.get("state").getAsString().trim()));
		stocktransfer.setTotalAmount(new BigDecimal(jsonObj.get("totalprice").getAsString()));
		stocktransfer.setTotalNumber(Integer.parseInt(jsonObj.get("totalAmount").getAsString().trim()));
		stocktransfer.setTransferID(1);
		stocktransfer.setTransferName(jsonObj.get("stocktransfer").getAsString().trim());
		int index = stockTransferdAdd.insertStockTransfer(stocktransfer);

		// insert stock transfer detail
      boolean flagUpdate = Boolean.parseBoolean(jsonObj.get("state").getAsString().trim());
		iStockTransferDetailImpl stockTransferDetailAdd = new iStockTransferDetailImpl();
		iStockProductImpl stockProductImpl = new iStockProductImpl();
		String stockInwardTable = request.getParameter("1");
		JsonElement elementTable = gson.fromJson(stockInwardTable,JsonElement.class);
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
		if (list.size() > 0)
		{
			if (flagUpdate == true)
			{
				//insert or Update Data StockProduct
			for (JsonObject jsonObject : list) 
			{

				StockTransferDetail stockTransferDetailObject = new StockTransferDetail();
				stockTransferDetailObject.setAmount(new BigDecimal(jsonObject.get("subtotal").getAsString().trim()));
				stockTransferDetailObject.setDiscount(jsonObject.get("discount").getAsString());
				stockTransferDetailObject.setNameproductId(" ");
				stockTransferDetailObject.setNamestockFromId("");
				stockTransferDetailObject.setNamestockToId(" ");
				stockTransferDetailObject.setNumberTransfer(Integer.parseInt(jsonObject.get("quantity").getAsString().trim()));
				stockTransferDetailObject.setPrice(new BigDecimal(jsonObject.get("subtotal").getAsString().trim()));
				stockTransferDetailObject.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				stockTransferDetailObject.setStockFromId(Integer.parseInt(jsonObject.get("stockIdFrom").getAsString().trim()));
				stockTransferDetailObject.setStockToId(Integer.parseInt(jsonObject.get("stockIdTo").getAsString().trim()));
				stockTransferDetailObject.setStockTransDetailId(1);
				stockTransferDetailObject.setTransferId(index);
				stockTransferDetailObject.setUnitName(jsonObject.get("unitName").getAsString());
				stockTransferDetailAdd.insertStockTransferDetail(stockTransferDetailObject);
               // stock To
				StockProduct stockItem1 = new StockProduct();
				//stock From 
				StockProduct stockItem2 = new StockProduct();
				int totalAmountTo = stockProductImpl.getQuantitytByStockAndProductId(Integer.parseInt(jsonObject.get("stockIdTo").getAsString().trim()),Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				int totalAmountFrom = stockProductImpl.getQuantitytByStockAndProductId(Integer.parseInt(jsonObject.get("stockIdFrom").getAsString().trim()),Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				int idTo = stockProductImpl.getStockProductId(Integer.parseInt(jsonObject.get("stockIdTo").getAsString().trim()),Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				int idFrom = stockProductImpl.getStockProductId(Integer.parseInt(jsonObject.get("stockIdFrom").getAsString().trim()),Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
				//to Stock
				if (idTo != 0) 
				{
					int temp1 = Integer.parseInt(jsonObject.get("quantity").getAsString().trim());
					int valueUpdate1 = totalAmountTo + temp1;
					System.out.println("Gia tri tam 1" + valueUpdate1);
					stockItem1.setNameproductId(" ");
					stockItem1.setNamestockId(" ");
					stockItem1.setNameStockProduct(" ");
					stockItem1.setStockProductId(Integer.parseInt(jsonObject.get("productstock").getAsString().trim()));
					stockItem1.setStockId(Integer.parseInt(jsonObject.get("stockIdTo").getAsString().trim()));
					stockItem1.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
					stockItem1.setQuantity(valueUpdate1);
					stockProductImpl.updateStockProduct(stockItem1);
				} else
				{
					int temp2 = Integer.parseInt(jsonObject.get("quantity").getAsString().trim());
					System.out.println("Gia tri tam 2" + temp2);
					stockItem1.setNameproductId(" ");
					stockItem1.setNamestockId(" ");
					stockItem1.setNameStockProduct(" ");
					stockItem1.setStockProductId(Integer.parseInt(jsonObject.get("productstock").getAsString().trim()));
					stockItem1.setStockId(Integer.parseInt(jsonObject.get("stockIdTo").getAsString().trim()));
					stockItem1.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
					stockItem1.setQuantity(temp2);
					stockProductImpl.insertStockProduct(stockItem1);
				}
				
				// from stock
				if (idFrom != 0) 
				{
					int temp1 = Integer.parseInt(jsonObject.get("quantity").getAsString().trim());
					int valueUpdate3 = totalAmountFrom - temp1;
					System.out.println("Gia tri tam 3" + valueUpdate3);
					stockItem2.setNameproductId(" ");
					stockItem2.setNamestockId(" ");
					stockItem2.setNameStockProduct(" ");
					stockItem2.setStockProductId(Integer.parseInt(jsonObject.get("productstock").getAsString().trim()));
					stockItem2.setStockId(Integer.parseInt(jsonObject.get("stockIdFrom").getAsString().trim()));
					stockItem2.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
					stockItem2.setQuantity(valueUpdate3);
					stockProductImpl.updateStockProduct(stockItem2);
				} else
				{
					int temp2 = Integer.parseInt(jsonObject.get("quantity").getAsString().trim());
					System.out.println("Gia tri tam 2" + temp2);
					stockItem2.setNameproductId(" ");
					stockItem2.setNamestockId(" ");
					stockItem2.setNameStockProduct(" ");
					stockItem2.setStockProductId(Integer.parseInt(jsonObject.get("productstock").getAsString().trim()));
					stockItem2.setStockId(Integer.parseInt(jsonObject.get("stockIdFrom").getAsString().trim()));
					stockItem2.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
					stockItem2.setQuantity(-temp2);
					stockProductImpl.insertStockProduct(stockItem2);
				}
				
				

			}
			
			}
			else
			{
				for (JsonObject jsonObject : list) 
				{

					StockTransferDetail stockTransferDetailObject = new StockTransferDetail();
					stockTransferDetailObject.setAmount(new BigDecimal(jsonObject.get("subtotal").getAsString().trim()));
					stockTransferDetailObject.setDiscount(jsonObject.get("discount").getAsString());
					stockTransferDetailObject.setNameproductId(" ");
					stockTransferDetailObject.setNamestockFromId("");
					stockTransferDetailObject.setNamestockToId(" ");
					stockTransferDetailObject.setNumberTransfer(Integer.parseInt(jsonObject.get("quantity").getAsString().trim()));
					stockTransferDetailObject.setPrice(new BigDecimal(jsonObject.get("subtotal").getAsString().trim()));
					stockTransferDetailObject.setProductId(Integer.parseInt(jsonObject.get("productId").getAsString().trim()));
					stockTransferDetailObject.setStockFromId(Integer.parseInt(jsonObject.get("stockIdFrom").getAsString().trim()));
					stockTransferDetailObject.setStockToId(Integer.parseInt(jsonObject.get("stockIdTo").getAsString().trim()));
					stockTransferDetailObject.setStockTransDetailId(1);
					stockTransferDetailObject.setTransferId(index);
					stockTransferDetailObject.setUnitName(jsonObject.get("unitName").getAsString());
					stockTransferDetailAdd.insertStockTransferDetail(stockTransferDetailObject);
				}
				
			}
		}

		String str = "";
		System.out.println("Gia tri index" + index);

		if (index != 0) {
			str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
		} else {
			str = "{\"error\": \"2\",\"message\": \"Insert failed. Please try again.\"}";
		}
		return str;

	}

}

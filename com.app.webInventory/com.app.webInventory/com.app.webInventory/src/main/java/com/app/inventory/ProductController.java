package com.app.inventory;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.iProductImpl;
import com.app.dao.iProductTypeImpl;
import com.app.dao.iProductUnitImpl;
import com.app.dao.iProviderImpl;
import com.app.dao.iStaffImpl;
import com.app.object.Product;
import com.app.object.ProductType;
import com.app.object.ProductUnit;
import com.app.object.Provider;
import com.app.object.Staff;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class ProductController {
	
	int idProduct =1;
	
	Product product = new Product();
	iProductImpl productImpl = new iProductImpl();
	
	//page List Product
		@RequestMapping(value = {"/listProductPage"}, method = RequestMethod.GET)
		public ModelAndView listProductPage(ModelMap model) throws Exception {
			 ModelAndView mav = new ModelAndView();
	      ArrayList<Product> productListLoad = new ArrayList<Product>();
	      productListLoad =  productImpl.getAllProduct();
		model.addAttribute("productListLoad", productListLoad);
				mav.setViewName("listProduct");
					return mav;
		}
		//process Delete Product 
			@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, produces = "application/json")
			public void changeStatus(HttpServletRequest request) throws Exception {
				String id = request.getParameter("id");
				Product productObj = new Product();
				productObj.setProductId(Integer.parseInt(id));
				boolean flag = productImpl.deleteProduct(productObj);
				System.out.println("Gia tri id: " + id + "Xoa" + flag);
			
		}
			//process edit Product
			@RequestMapping(value = "/editProductProcess", method = RequestMethod.POST, produces = "application/json")
			public final @ResponseBody  String getInfoStockCheck(HttpServletRequest request) throws Exception {
				String id = request.getParameter("id");
				idProduct = Integer.parseInt(id);
				System.out.println("Gia tri id: " + idProduct);
				 String str= "";
				 str = "{\"error\": \"1\",\"message\": \"Insert success. Thanks you.\"}";
				 return str;
			}
		
	
	
	
	
	//page Product Edit
			@RequestMapping(value = {"/editProductPage"}, method = RequestMethod.GET)
			public ModelAndView editStockInWardPage(ModelMap model) throws Exception {
				ModelAndView mav = new ModelAndView();
					System.out.println("Gia tri idProduct: " + idProduct );
					product = productImpl.getByID(idProduct);		 
					 model.addAttribute("nameProduct", product.getProductName());
					 model.addAttribute("typeProduct", product.getNameTypeId());
					 model.addAttribute("supplier", product.getNameStaffId());
					 model.addAttribute("unit", product.getNameunitId());
					 model.addAttribute("oriprice", product.getOrgPrice());
					 model.addAttribute("purchase", product.getSalePrice());
					 model.addAttribute("source", product.getOrgSource());
					 model.addAttribute("minimum", product.getMinStock());
					 model.addAttribute("maximum", product.getMaxStock());
					 model.addAttribute("manage", product.getNameStaffId());
					 model.addAttribute("note", product.getDescription());
					mav.setViewName("editProduct");
					return mav;
							
			}
			
			//update product
			@RequestMapping(value = "/editProductData", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
			public final @ResponseBody  String editStockCheck11(HttpServletRequest request) throws Exception
			{
				String stockProductEditDetail = request.getParameter("0");
				Gson gson = new Gson();
				JsonElement element = gson.fromJson (stockProductEditDetail, JsonElement.class);
				JsonObject jsonObj = element.getAsJsonObject();
				product.setProductName(jsonObj.get("nameproduct").getAsString().trim()); 
				product.setOrgPrice(new BigDecimal(jsonObj.get("oriprice").getAsString().trim())); 
				product.setSalePrice(new BigDecimal(jsonObj.get("purchaseprice").getAsString().trim())); 
				product.setMinStock(Integer.parseInt((jsonObj.get("minimumnumber").getAsString().trim()))); 
				product.setMaxStock(Integer.parseInt(jsonObj.get("maximumnumber").getAsString().trim())); 
		        boolean flagUpdate = productImpl.updateProduct(product);
		       
				  String str= "";  
				  if (flagUpdate == true) {
						str = "{\"error\": \"1\",\"message\": \"Update success. Thanks you.\"}";
					} else {
						str = "{\"error\": \"2\",\"message\": \"Update failed. Please try again.\"}";
					}
					return str;
			
			}			
	
	//add product page
	@RequestMapping(value = {"/addproductpage"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		//iStockImpl ProductList = new iStockImpl();
		 ModelAndView mav = new ModelAndView();
		 try {
			  iProductTypeImpl iProductType = new iProductTypeImpl();
			  List<ProductType>  productTypeList = new ArrayList<ProductType>();
		   productTypeList = iProductType.getAllProductType();
		   	iProductUnitImpl iProductUnit = new iProductUnitImpl();
		   	List<ProductUnit> productUnitList = new ArrayList<ProductUnit>();
		   	productUnitList = iProductUnit.getAllProductUnit();
		   	iProviderImpl iProvider = new iProviderImpl();
		   	List<Provider> providerList = new ArrayList<Provider>();
		   	providerList = iProvider.getAllProvider();
		   	iStaffImpl iStaff = new  iStaffImpl();
		   	List<Staff> stafflist = new ArrayList<Staff>();
		   	stafflist = iStaff.getAllStaff();
		   model.addAttribute("productTypeList", productTypeList );
		   model.addAttribute("productUnitList", productUnitList );
		   model.addAttribute("providerList", providerList );
		   model.addAttribute("stafflist", stafflist ); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			mav.setViewName("addproduct");
			return mav;
	}
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST ,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public final @ResponseBody  String addProduct(HttpServletRequest request) throws Exception
	{
		String productString = request.getParameter("0");
		//JsonObject stockInwardObj = new Gson().fromJson(stockInward,JsonObject.class);
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (productString, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		  iProductImpl productImpl = new iProductImpl();
		  Product product = new Product();
		  product.setDescription(jsonObj.get("descript").getAsString());
		  product.setMaxStock(Integer.parseInt(jsonObj.get("max").getAsString().trim()));
		  product.setMinStock(Integer.parseInt(jsonObj.get("min").getAsString().trim()));
		  product.setNameproviderId("");
		  product.setNameStaffId("");
		  product.setNameTypeId("");
		  product.setNameunitId("");
		  product.setOrgPrice(new BigDecimal(jsonObj.get("oriPrice").getAsString()));
		  product.setOrgSource(jsonObj.get("orgSource").getAsString());
		  product.setProductId(1);
		  product.setProductName(jsonObj.get("nameProduct").getAsString());
		  product.setProviderId(Integer.parseInt(jsonObj.get("providerId").getAsString().trim()));
		  product.setSalePrice(new BigDecimal(jsonObj.get("purPrice").getAsString()));
		  product.setTypeId(Integer.parseInt(jsonObj.get("productTypeId").getAsString().trim()));
		  product.setUnitId(Integer.parseInt(jsonObj.get("productunitId").getAsString().trim()));
		  product.setStaffId(Integer.parseInt(jsonObj.get("staff").getAsString().trim()));
		  boolean flagInsert = productImpl.insertProduct(product); 
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

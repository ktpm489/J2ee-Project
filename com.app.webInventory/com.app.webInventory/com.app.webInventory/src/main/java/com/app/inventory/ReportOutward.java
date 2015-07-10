package com.app.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;











import com.app.object.StockOutward;
import com.app.object.StockOutwardDetail;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
public class ReportOutward
{

	private Map<String, Object>params = new HashMap<String, Object>();
	
	
	
	public void runReport(String path, String fileName,StockOutward outwardObject , ArrayList<StockOutwardDetail> outwardDetailList) 
	{
        params.put("outwardcode", outwardObject.getOutwardName());
        params.put("customer", outwardObject.getNamecustomerId());
        params.put("staff", outwardObject.getNamestaffId());
        params.put("date", outwardObject.getDateOutward());
        params.put("amount", outwardObject.getTotalQuantity());
        params.put("price", outwardObject.getTotalAmount());
        params.put("state", outwardObject.getStatusCheck());
        params.put("note", outwardObject.getDescript());
       
        
		JRDataSource datasource = new JRBeanCollectionDataSource(outwardDetailList);
		String filePath = fileName;
        try {
			
				JasperReport reportFile = JasperCompileManager.compileReport(path);
				JasperPrint jPrint = JasperFillManager.fillReport(reportFile, params,datasource);
				JasperViewer.viewReport(jPrint, false);
				JasperExportManager.exportReportToPdfFile(jPrint, filePath);
            	
        
            } catch (JRException ex) {
                System.out.print("Error Log" + ex.toString());

            }
	}
}

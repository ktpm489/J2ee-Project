package com.app.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.app.object.StockInward;
import com.app.object.StockInwardDetail;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
public class ReportInward
{

	private Map<String, Object>params = new HashMap<String, Object>();
	
	
	
	public void runReport(String path, String fileName,StockInward inwardObject , ArrayList<StockInwardDetail> inwardDetailList) 
	{
        params.put("inwardcode", inwardObject.getInwardName());
        params.put("supplier", inwardObject.getNameproviderId());
        params.put("staff", inwardObject.getNamestaffId());
        params.put("date", inwardObject.getDateInward());
        params.put("amount", inwardObject.getTotalNumber());
        params.put("price", inwardObject.getTotalNumber());
        params.put("state", inwardObject.getStatusCheck());
        params.put("note", inwardObject.getDescript());
       
       
		JRDataSource datasource = new JRBeanCollectionDataSource(inwardDetailList);
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

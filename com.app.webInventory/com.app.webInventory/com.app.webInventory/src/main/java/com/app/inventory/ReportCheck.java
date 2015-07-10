package com.app.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;











import com.app.object.StockCheck;
import com.app.object.StockCheckDetail;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
public class ReportCheck
{

	private Map<String, Object>params = new HashMap<String, Object>();
	
	
	public void runReport(String path, String fileName,StockCheck checkObject , ArrayList<StockCheckDetail> checkDetailList) 
	{
    	
    	
		
	
        params.put("checkcode", checkObject.getCheckName());      
        params.put("stock", checkObject.getNamestockId());
        params.put("staff", checkObject.getStaffName());
        params.put("date", checkObject.getDateCheck());
        params.put("state", checkObject.getStatusCheck());
        params.put("note", checkObject.getDescription());
    

		JRDataSource datasource = new JRBeanCollectionDataSource(checkDetailList);
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

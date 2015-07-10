package com.app.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;











import com.app.object.StockTransfer;
import com.app.object.StockTransferDetail;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
public class ReportTransfer
{

	private Map<String, Object>params = new HashMap<String, Object>();
	
	
	public void runReport(String path, String fileName,StockTransfer transferObject , ArrayList<StockTransferDetail> transferDetailList) 
	{
    	
    	
		
	
        params.put("transfercode", transferObject.getTransferName() );
        params.put("staff", transferObject.getNameStaffId());
        params.put("date", transferObject.getDateTransfered());
        params.put("amount", transferObject.getTotalNumber());
        params.put("price", transferObject.getTotalAmount());
        params.put("state", transferObject.getStatusCheck());
        params.put("note", transferObject.getDescription());
    

		JRDataSource datasource = new JRBeanCollectionDataSource(transferDetailList);
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

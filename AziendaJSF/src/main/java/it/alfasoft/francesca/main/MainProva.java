package it.alfasoft.francesca.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.BustaPagaWS;
import it.alfasoft.francesca.dao.BustaPagaDao;
import it.alfasoft.francesca.dao.BustaPagaWSDao;
import it.alfasoft.francesca.service.ServiziBusta;

public class MainProva {

	public static void main(String[] args) {
		ServiziBusta sb=new ServiziBusta();
		
//		Date data=new Date();
//		BustaPagaWS b1= new BustaPagaWS(data, 1200, "cod102");
//		BustaPagaWS b2= new BustaPagaWS(data, 1250, "cod103");
//		BustaPagaWS b3= new BustaPagaWS(data, 1800, "cod104");
//		
//		sb.addBustaPaga(b1);
//		sb.addBustaPaga(b2);
//		sb.addBustaPaga(b3);
		
		BustaPagaWSDao bpdao= new BustaPagaWSDao();
		
		Double d;
		
		String nomeFile="ElencoBuste.pdf";
		
		 String percorso  = "K:\\Users\\Francesca\\";
		 
		 String fileFinale=percorso+nomeFile;
		 
		try {
             

           //la mia lista che mantiene i dati
             List<BustaPagaWS> buste = bpdao.getBustePaga();
     

           // Converto la  lista to JRBeanCollectionDataSource 
           JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(buste,false);

           //  una mappa per mandare i parametri a Jasper 
           Map<String, Object> parameters = new HashMap<String, Object>();
         
           parameters.put("DatasetBuste", itemsJRBean);

           //  file compilato di jasper (.jasper) di Jasper Report per creare  PDF 
           JasperPrint jasperPrint = JasperFillManager.fillReport("bustePaga.jasper", parameters, new JREmptyDataSource());

           //outputStream per creare PDF 
           OutputStream outputStream = new FileOutputStream(new File(fileFinale));
          
           
           // scrivo in un  file PDF  
         
           JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

           System.out.println("File e' stato creato");
           
           
       } catch (JRException ex) {
           ex.printStackTrace();
       } catch (FileNotFoundException ex) {
           ex.printStackTrace();
       }
		
		

}

	}


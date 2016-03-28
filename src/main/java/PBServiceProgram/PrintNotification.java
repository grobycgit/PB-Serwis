package PBServiceProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

public class PrintNotification {
	
	private MainProgram main;
	private NotificationsList selectedNotification;

	public PrintNotification(MainProgram theMainProgram, NotificationsList theNotificationsList){
		main = theMainProgram;
		selectedNotification = theNotificationsList;
	}
	
	public void print(){
				try{
		FileInputStream file = new FileInputStream(new File("C:/PBSerwis/serwis.xls"));
		
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet1 = workbook.getSheetAt(0);
		HSSFSheet sheet2 = workbook.getSheetAt(1);
		
		// style for "Numer zg³oszenia"
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short)12);
		font.setBold(true);
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		// end of style settings
		
		Cell cell = null;
		
		cell = sheet1.getRow(7).getCell(4);
	    cell.setCellValue(selectedNotification.getStartDate());
	    cell = sheet1.getRow(9).getCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("ZG£OSZENIE DO SERWISU NR " + selectedNotification.getNotifId());
	    cell = sheet1.getRow(12).getCell(1);
		cell.setCellValue(selectedNotification.getOwner());
	    cell = sheet1.getRow(13).getCell(1);
	    cell.setCellValue(selectedNotification.getAddress());
	    cell = sheet1.getRow(14).getCell(1);
	    cell.setCellValue(selectedNotification.getContact());
	    cell = sheet1.getRow(16).getCell(1);
	    cell.setCellValue(selectedNotification.getProduct());
	    cell = sheet1.getRow(17).getCell(1);
	    cell.setCellValue(selectedNotification.getSerial());
	    cell = sheet1.getRow(18).getCell(1);
	    cell.setCellValue(selectedNotification.getSellDate());
	    cell = sheet1.getRow(19).getCell(1);
	    cell.setCellValue(selectedNotification.getAccessories());
	    cell = sheet1.getRow(21).getCell(1);
	    cell.setCellValue(selectedNotification.getDescription() +"\nRazem: "+ selectedNotification.getPrice());
	    cell = sheet1.getRow(23).getCell(1);
	    cell.setCellValue(selectedNotification.getWarranty());
	    
	    //sheet2
		cell = sheet2.getRow(7).getCell(4);
	    cell.setCellValue(selectedNotification.getStartDate());
	    cell = sheet2.getRow(9).getCell(0);
	    cell.setCellStyle(style);
	    cell.setCellValue("WYDANIE Z SERWISU DO ZG£OSZENIA NR " + selectedNotification.getNotifId());
	    cell = sheet2.getRow(12).getCell(1);
		cell.setCellValue(selectedNotification.getOwner());
	    cell = sheet2.getRow(13).getCell(1);
	    cell.setCellValue(selectedNotification.getAddress());
	    cell = sheet2.getRow(14).getCell(1);
	    cell.setCellValue(selectedNotification.getContact());
	    cell = sheet2.getRow(16).getCell(1);
	    cell.setCellValue(selectedNotification.getProduct());
	    cell = sheet2.getRow(17).getCell(1);
	    cell.setCellValue(selectedNotification.getSerial());
	    cell = sheet2.getRow(18).getCell(1);
	    cell.setCellValue(selectedNotification.getSellDate());
	    cell = sheet2.getRow(19).getCell(1);
	    cell.setCellValue(selectedNotification.getAccessories());
	    cell = sheet2.getRow(21).getCell(1);
	    cell.setCellValue(selectedNotification.getEmployee());
	    cell = sheet2.getRow(23).getCell(1);
	    cell.setCellValue(selectedNotification.getDescription());
	    cell = sheet2.getRow(26).getCell(1);
	    cell.setCellValue(selectedNotification.getWarranty());
	    cell = sheet2.getRow(32).getCell(0);
	    cell.setCellValue(selectedNotification.getDamages());
	    cell = sheet2.getRow(35).getCell(0);
	    cell.setCellValue(selectedNotification.getService());
	    cell = sheet2.getRow(36).getCell(0);
	    cell.setCellValue("Razem " + selectedNotification.getPrice());
	    cell = sheet2.getRow(39).getCell(0);
	    cell.setCellValue(selectedNotification.getRecommendations());
	    
	    file.close();

        FileOutputStream outFile = new FileOutputStream(new File("C:/PBSerwis/serwis.xls"));
        workbook.write(outFile);
        outFile.close();
		workbook.close();
		
		Runtime.getRuntime().exec("cmd /c start excel.exe C:\\PBSerwis\\Serwis.xls");
		
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(main, "Nie mo¿na otworzyæ pliku z kilku powodów: \n- Plik jest otworzony.\n- Nie znaleziono pliku serwis.xls w katalogu C:\\PBSerwis.", "B³¹d", JOptionPane.ERROR_MESSAGE);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

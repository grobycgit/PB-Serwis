package PBServiceProgram;
import java.io.IOException;
 
public class OpenExcel {
	
	public OpenExcel(){
		try{  
        	  Runtime.getRuntime().exec("cmd /c start excel.exe C:\\PBSerwis\\Serwis.xls");

          }catch(IOException  e){
              e.printStackTrace();
          }  
	}
}
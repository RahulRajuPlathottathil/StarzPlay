package utility;

import java.util.Hashtable;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelReader {
	
   public static void readExcel() {
	   Fillo fillo = new Fillo();
	   Hashtable<String,String> hashtable = null;
	   try {
		Connection connection =fillo.getConnection("C:\\Users\\Lenovo\\Desktop\\TestRunner2.xlsx");
		  String query = "SELECT * FROM Sheet1 where RunMode='Yes'";
          Recordset recordset = connection.executeQuery(query);

          // Move the cursor to the first record
          //recordset.moveFirst();
          System.out.println(recordset.getFieldNames());
          int counter=0;
          Object[][] data =new Object[recordset.getCount()][1];
          while (recordset.next()) {
          hashtable=new Hashtable<String, String>();
          
          
          for (String columnName : recordset.getFieldNames()) {
              String fieldValue = recordset.getField(columnName);
              hashtable.put(columnName, fieldValue);
          }

          // Print or process the Hashtable for the current record
          System.out.println("Record Hashtable: " + hashtable);
          data[counter][0]=hashtable;
          counter++;
     

	}
          System.out.println(data[0][0]);
          System.out.println(data[1][0]);
          System.out.println(data[2][0]);
	   }catch (FilloException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   public static void main(String[] args) {
	readExcel();
}

}

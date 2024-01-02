package dataProvider;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

import apiManager.GlobalConstants;
import utility.ExcelDB;

public class DataProviderHelper {

	@DataProvider
	public static Object[][] StarzPlayDataProvider() {
		Object[][] data = null;
		Hashtable<String, String> hashtable = null;
		try {

			String query = "SELECT * FROM Sheet1 where RunMode='Yes'";
			 String projectPath = System.getProperty("user.dir");

		 
			Recordset recordset = ExcelDB.given().with(GlobalConstants.getExcelPath()).executeQuery(query).getAsRecordSet();

			int counter = 0;
			data = new Object[recordset.getCount()][1];
			while (recordset.next()) {
				hashtable = new Hashtable<String, String>();

				for (String columnName : recordset.getFieldNames()) {
					String fieldValue = recordset.getField(columnName);
					hashtable.put(columnName, fieldValue);
					
				}
				data[counter][0] = hashtable;
				System.out.println(counter);
				counter++;
				System.out.println(hashtable);
			}

		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}
	public static void main(String[] args) {
		StarzPlayDataProvider();
		
	}

}

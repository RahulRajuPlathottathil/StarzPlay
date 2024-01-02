package data;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

import apiManager.GlobalConstants;
import utility.ExcelDB;

public class ImageLogic {
	private static String platform;
	private static String version;
	public static String origin;
	
	/**
	 * @return the origin
	 */
	public static String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public static void setOrigin(String origin) {
		ImageLogic.origin = origin;
	}

	/**
	 * @return the platform
	 */
	public static String getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public static void setPlatform(String _platform) {
		platform = _platform;
	}

	/**
	 * @return the version
	 */
	public static String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public static void setVersion(String _version) {
		version = _version;
	}

	public static Hashtable<String, String> getImageCount(String category,String platform,String version,String origin) {
		String query = String.format("SELECT * FROM ImageLogic WHERE platform='%s' AND origin='%s' AND version='%s'", platform,origin,version);
		//System.out.println(query);
		int imageCount = 0;
		Recordset recordset=ExcelDB.given().with(GlobalConstants.getExcelPath()).executeQuery(query).getAsRecordSet();
		Hashtable<String, String> hashtable = null;
		try {
			while (recordset.next()) {
				hashtable = new Hashtable<String, String>();

				for (String columnName : recordset.getFieldNames()) {
					String fieldValue = recordset.getField(columnName);
					hashtable.put(columnName, fieldValue);
					
				}
		}
			//System.out.println(hashtable);
			} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashtable;
	}

//public static void main(String[] args) {
//		Recordset recordset=getImageCount("Hero", "tv", "2", "tizen");
//		Hashtable<String, Object> hashtable = null;
//		try {
//			while (recordset.next()) {
//				hashtable = new Hashtable<String, Object>();
//
//				for (String columnName : recordset.getFieldNames()) {
//					String fieldValue = recordset.getField(columnName);
//					System.out.println(fieldValue.getClass());
//					hashtable.put(columnName, fieldValue);
//					
//				}
//		}
//			System.out.println(hashtable);} catch (FilloException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}

package apiManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GlobalConstants {
	
	private static final String configProperiesPath=System.getProperty("user.dir")+"//src//main//resources//Config.properties";
	private static final String userDir=System.getProperty("user.dir")+"//";

	/**
	 * @return the configproperiespath
	 */
	public static String getConfigproperiespath() {
		return configProperiesPath;
	}
	public static String userDir() {
		return userDir;
	}
	
    public static String getExcelPath() {
    	 String projectPath = System.getProperty("user.dir");

		 
	        String relativePath = "/TestRunner.xlsx";

	        // Concatenate the project path and relative path
	        Path excelPath = Paths.get(projectPath, relativePath);
	        return excelPath.toString();
	
    }
}

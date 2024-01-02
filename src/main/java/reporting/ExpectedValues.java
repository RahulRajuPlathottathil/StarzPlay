package reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExpectedValues {
	
	public static final List<String> assetTypes =List.of("AVOD","TVOD","SVOD");
	public static final List<String> countryRights=List.of("AE", "SA", "TN", "KW", "QA", "OM", "BH", "DZ", "IQ", "JO", "LB", "MA", "DJ", "LY", "PS", "MR", "YE", "IE", "DE", "FR", "EG", "SD", "SS", "US","KR");
    public static final List<String> contentOwnership=List.of("evision","starz");
    public static final List<Integer> programTypes=List.of(0,1,2);
    public static final List<Integer> ageRating =List.of(7,15,18,1);
    public static final List<String> subscription=List.of("starzplaysports","criclife","adsports","seriea","ufc","starz","rwc","coppaitalia","blutv");
    
    private static LocalDateTime now = LocalDateTime.now();

    // Format the timestamp as a string
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static String timestamp = now.format(formatter);

    // Create a filename with the timestamp
    public static final String filename = "Report_" + timestamp;
}

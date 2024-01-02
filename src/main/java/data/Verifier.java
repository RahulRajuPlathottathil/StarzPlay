package data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import apiManager.World;
import base.BaseTest;

import models.Layout;
import models.md.Page;
import models.md.Page.Title;
import models.md.Page.Title.Image;
import reporting.ExpectedValues;

public class Verifier {
    static int itr=0;
	public static void VerifyItemsCount() {
		try {
			
			System.out.println("Iteration :"+itr);
		SoftAssert verify = new SoftAssert();
		ExtentTest childTest = BaseTest.test.createNode("******DATA**********");
		Layout layout = World.getLayout();
		List<Page> movie = World.getMovie();
		int length = layout.getModules().size();
		List<models.Module> lay = layout.getModules();
		for (int i = 0; i < length; i++) {
			Map<String, Object> report = new LinkedHashMap<>();
			HashMap<String, Object> imageMap = new HashMap<>();

			HashMap<String, String> contentOwnershipMap = new HashMap<>();
			HashMap<String, Object> layoutData = new HashMap<>();
			long id = lay.get(i).getId();
			layoutData.put("Layout Id", id);
			List<Page> l = movie.stream().filter(f -> (f.getId() == id)).collect(Collectors.toList());
			// long LayoutID = lay.get(i).getId();
			BaseTest.test.info("*********  "+lay.get(i).getCategory()+"  *********");
			Assert.assertEquals(id, l.get(0).getId());
			if (String.valueOf(id).equals(String.valueOf(l.get(0).getId()))) {
				BaseTest.test.log(Status.PASS,
						String.format("layout id: %s matches module call id %s", id, l.get(0).getId()));
			} else {
				BaseTest.test.log(Status.FAIL,
						String.format("layout id: %s not matches module call id %s", id, l.get(0).getId()));
			}

			String type = lay.get(i).getCategory();
			layoutData.put("catergory", type);
			childTest = BaseTest.test.createNode(type.toUpperCase());
			System.out.println(l.size() + "-----------");
			List<Title> titles = l.get(0).getTitles();
			System.out.println("No of Titles" + titles.size());
			layoutData.put("Title count", titles.size());
			if(lay.get(i).getItems()==titles.size()) {
				
				BaseTest.test.log(Status.PASS,
						String.format("layout Items count: %s matches module titles count %s", lay.get(i).getItems(), titles.size()));
			} else {
				BaseTest.test.log(Status.FAIL,
						String.format("layout Items count: %s doesn't matches module titles count %s", lay.get(i).getItems(), titles.size()));
			}

			int title_counter = 0;
			for (Title T : titles) {
				HashMap<String, Object> titleMap = new LinkedHashMap<>();
				System.out.println("Title cunter:" + title_counter);
				System.out.println(T.getId());
				System.out.println(T.getAssetTypesPriority());
				titleMap.put("Title ID" + title_counter, T.getId());
				titleMap.put("Country Rights" + title_counter, T.getAdsCountryRights());
				titleMap.put("Title" + title_counter, T.getTitle());
				titleMap.put("Subscription" + title_counter, T.getSubscription());
				titleMap.put("Content Ownership", T.getContentOwnership());
				report.put("Titles" + title_counter, titleMap);
				List<Image> images = T.getImages();
				if(ExpectedValues.countryRights.containsAll(T.getAdsCountryRights())) {
					BaseTest.test.log(Status.PASS,
							String.format("Expected Ads right: %s matches module country Ads support count %s", ExpectedValues.countryRights, T.getAdsCountryRights()));
				} else if(T.getAdsCountryRights().size()!=0) {
					BaseTest.test.log(Status.FAIL,
							String.format("Expected Ads right: %s doesn't matches module country Ads support count %s", ExpectedValues.countryRights, T.getAdsCountryRights()));
				}
				else {
//					BaseTest.test.log(Status.WARNING,
//							String.format("module country Ads support count %s",T.getAdsCountryRights()));
			
				}
				if(ExpectedValues.contentOwnership.containsAll(T.getContentOwnership())) {
					BaseTest.test.log(Status.PASS,
							String.format("Expected Content Ownership includes either of: %s matches module Content Ownership %s", ExpectedValues.contentOwnership, T.getContentOwnership()));
				} else  {
					BaseTest.test.log(Status.FAIL,
							String.format("Expected Content Ownership includes either of: %s not matches Content Ownership %s", ExpectedValues.contentOwnership, T.getContentOwnership()));
				}
				int count = 0;
				imageMap.put("Image count", images.size());
				BaseTest.test.info(String.format("category %s Image count is %s",lay.get(i).getCategory(), images.size()));
				System.out.println("*************");
				
				for (Image img : images) {
					System.out.println(img.getType());
					System.out.println(img.getUrl());
					imageMap.put("Image" + count + "ImageType", img.getType());
					imageMap.put("Image" + count + "ImageUrl", img.getUrl());
					if(img.getType().equals(lay.get(i).getImageType())) {
						BaseTest.test.log(Status.PASS,String.format("Expected Image Type : %s maches actual image type in module response is %s", lay.get(i).getImageType(),img.getType()));						
					}
					else {
						BaseTest.test.log(Status.PASS,String.format("Expected Image Type : %s not maches actual image type in module response is %s", lay.get(i).getImageType(),img.getType()));		
					}
				}
                 
				List<String> contentOwnership = T.getContentOwnership();
				int co_counter = 0;
				for (String co : contentOwnership) {
					contentOwnershipMap.put("Content_Ownership_" + co_counter, co);
					co_counter++;
				}

				title_counter++;
			}

			// System.out.println(l.get(0).getTitles().get(0).getTitle());
			report.put("General", layoutData);
			report.put("Images", imageMap);
			System.out.println(report);
			report.put("Content_Ownership", contentOwnershipMap);

			childTest.info(MarkupHelper.createTable(getTableData(report)));
			System.out.println("**********");
			System.out.println(report);

			// BaseTest.test.info(MarkupHelper.createTable(getTableData(imageMap)));

			// Log additional information
			// BaseTest.test.log(Status.PASS, "Test passed");

			// Flush the report to save it

		}

		BaseTest.extent.flush();
		
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			BaseTest.test.error(e.fillInStackTrace());
		}
		
itr++;
	}

	private static String[][] getTableData(Map<String, Object> dataMap) {
		String[][] tableData = new String[dataMap.size() + 1][2]; // Additional row for headers

		int rowIndex = 0;
		for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
			tableData[rowIndex][0] = entry.getKey();
			tableData[rowIndex][1] = String.valueOf(entry.getValue());
			rowIndex++;
		}

		return tableData;
	}

}

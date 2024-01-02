package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.LinkedMap;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import apiManager.World;
import base.BaseTest;
import models.Layout;
import models.Module;
import models.md.Page;
import models.md.Page.Title;
import models.md.Page.Title.Image;
import reporting.ExpectedValues;
import reporting.LogStatus;

public class ExtractDataFromResponse {
	private static Layout layouts;
	private static List<Page> pages;

	public static void apiScrambler() {
		try {
			layouts = World.getLayout();
			List<Module> layoutModules = layouts.getModules();
			List<Page> pages = World.getMovie();

			layoutModules.forEach(layoutmodule -> {
				Map<String, Object> layoutModuleMap = new LinkedMap<>();
				Map<String, Object> pageModuleTitleMap = new LinkedMap<>();
				Map<String, Object> pageModuleImageMap = new LinkedMap<>();
				long layoudModuleID = layoutmodule.getId();
				String layoutModuleGUID = layoutmodule.getGuid();
				String layoutModuleCategory = layoutmodule.getCategory();
				int layoutModulesItems = layoutmodule.getItems();

				Page pageModule = findModuleFromModuleResponse(pages, layoudModuleID);
				BaseTest.test.info("<div style=\"background-color: #ffcc00; width: 200px; height: 20px;\">" + layoutModuleCategory + "</tr>");
				if (layoutModulesItems == 0) {
					BaseTest.test.warning(
							String.format("<label color='blue' font-size=12px>ITems count is zero for category %s with layout id %s </h5>",
									layoutModuleCategory, layoudModuleID));
				}
				long pageID = pageModule.getId();
				List<Title> moduleTitles = pageModule.getTitles();
				int titleCounter = 0;
				for (Title moduleTitle : moduleTitles) {
					String pageModuletitle = moduleTitle.getTitle();
					long pageModuleTitleId = moduleTitle.getId();
					BaseTest.test.info("Title : " + pageModuletitle + "  Module ID : " + pageModuleTitleId);
					List<String> adsCountryRight = moduleTitle.getAdsCountryRights();
					List<String> contentOwnership = moduleTitle.getContentOwnership();
					List<String> subScriptions = moduleTitle.getSubscription();
					List<Image> images = moduleTitle.getImages();
					int programType = moduleTitle.getProgramType();
					int ageRating = moduleTitle.getAgeRating();

					LogStatus.Verify("ID", pageID, layoudModuleID);
					if (contentOwnership!=null)
					LogStatus.Verify("ContentOwnership", ExpectedValues.contentOwnership, contentOwnership);
					LogStatus.Verify("programType", ExpectedValues.programTypes, programType);
					LogStatus.Verify("ageRating", ExpectedValues.ageRating, ageRating);
					LogStatus.Verify("subScription", ExpectedValues.subscription, subScriptions);
					LogStatus.Verify("ID", pageID, layoudModuleID);
					if (adsCountryRight!=null)
					LogStatus.Verify("adsCountryRight", ExpectedValues.countryRights, adsCountryRight);

			
					int imgCounter = 0;
					List<String> imgList = new ArrayList<>();
					for (Image img : images) {
						String imgType = img.getType();
						String imgUrl = img.getUrl();
						imgList.add(imgType);
					}
					System.out.println("*****"+layoutModuleCategory);
					System.out.println(imgList+"-----"+imgList.size());
					// System.out.println(pageModuleImageMap);
					Hashtable<String, String> table = ImageLogic.getImageCount(layoutModuleCategory,
							ImageLogic.getPlatform(), ImageLogic.getVersion(), ImageLogic.getOrigin());
					System.out.println(layoutModuleCategory);
					String expected = table.get(layoutModuleCategory);
					int expectedCount = Integer.parseInt(expected);
					int actual = images.size();

					LogStatus.VerifyActalGreaterThanOrEqualsExpected("Carosel_Image_count", expectedCount, actual);

					List<String> imgTypesList = Arrays
							.asList(table.get(layoutModuleCategory + "_imageType").split(","));

					LogStatus.Verify("Image Type", imgTypesList, imgList);
					titleCounter++;

				}

			});
			BaseTest.extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
			//Assert.fail(e.getMessage());
		}

	}

	public static Page findModuleFromModuleResponse(List<Page> pages, long layoutModuleID) {

		try {
			List<Page> pageModules = pages.stream().filter(moduleID -> (moduleID.getId() == layoutModuleID))
					.collect(Collectors.toList());
			return pageModules.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return null;

	}

}

package sku.acceptance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SKUEndToEndTest {

	private static SKUApplication application;

	@BeforeAll
	public static void setup() {
		application = new SKUApplication();
	}

	@Test
	public void userUploadsSKUDataAndProcessesSuccessfully() throws Exception {
		// Given: The user has SKU data in an Excel file
		byte[] excelData = getTestExcelData();

		// When: The user uploads the SKU data
		application.uploadSKUData(excelData);

		// Then: The system should indicate that the SKU data has been processed
		// successfully
		assertTrue(application.isSKUDataProcessed(), "SKU data should be processed successfully");
	}

	@Test
	public void userComparesProductPerformanceAgainstCompetitor() throws Exception {
		// Given: SKU data is already uploaded
		byte[] excelData = getTestExcelData();
		application.uploadSKUData(excelData);

		// When: The user compares their product against a competitor
		String comparisonResult = application.compareProductPerformance("myProductId", "competitorProductId");

		// Then: The system should return a valid comparison result
		assertTrue(comparisonResult != null && !comparisonResult.isEmpty(), "Comparison result should be valid");
	}

	@Test
	public void userViewsCategoryPerformanceToSeeBestProduct() throws Exception {
		// Given: SKU data is already uploaded
		byte[] excelData = getTestExcelData();
		application.uploadSKUData(excelData);

		// When: The user requests the category performance report
		String report = application.getCategoryPerformanceReport("categoryId");

		// Then: The report should show the best-performing products in the category
		assertTrue(report != null && report.contains("Best Performing Products"),
				"Report should include best-performing products");
	}

	@Test
	public void userViewsOverallPerformanceAgainstCompetitors() throws Exception {
		// Given: SKU data is already uploaded
		byte[] excelData = getTestExcelData();
		application.uploadSKUData(excelData);

		// When: The user requests the overall performance report
		String overallPerformance = application.getOverallPerformanceReport();

		// Then: The system should return the correct overall performance comparison
		assertTrue(overallPerformance != null && overallPerformance.contains("Your Organization vs Competitors"),
				"Overall performance report should include comparison");
	}

	// Utility method to mock the Excel data for testing purposes
	private byte[] getTestExcelData() {
		// Replace this with actual mock data
		return new byte[0];
	}
}

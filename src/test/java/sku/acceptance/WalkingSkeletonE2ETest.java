package sku.acceptance;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WalkingSkeletonE2ETest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void endToEndTest() throws Exception {
		testLandingPage();
		testProductListing();
		testStockComparison();
	}

	private void testLandingPage() throws Exception {
		mockMvc.perform(get("/")) // Perform a GET request to the landing page
				.andExpect(status().isOk()) // Expect HTTP status 200 OK
				.andExpect(content().string(containsString("Welcome to SKU"))); // Expect welcome message in content
	}

	private void testProductListing() throws Exception {
		mockMvc.perform(get("/products")) // Perform a GET request to the products page
				.andExpect(status().isOk()) // Expect HTTP status 200 OK
				.andExpect(model().attributeExists("products")) // Expect model attribute "products" to exist
				.andExpect(content().string(containsString("Product List"))); // Expect product list in content
	}

	private void testStockComparison() throws Exception {
		mockMvc.perform(get("/compare-stock")) // Perform a GET request to the stock comparison page
				.andExpect(status().isOk()) // Expect HTTP status 200 OK
				.andExpect(content().string(containsString("Stock Comparison"))); // Expect stock comparison text in
																					// content
	}
}

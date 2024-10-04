package sku.acceptance;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
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
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Welcome to SKU")));
	}

	private void testProductListing() throws Exception {
		mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(model().attributeExists("products"))
				.andExpect(content().string(containsString("Product List")));
	}

	private void testStockComparison() throws Exception {
		mockMvc.perform(get("/compare-stock")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Stock Comparison")));
	}
}

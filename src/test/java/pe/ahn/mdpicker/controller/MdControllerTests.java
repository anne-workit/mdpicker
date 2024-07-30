package pe.ahn.mdpicker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MdControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void priceSummary() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/md/price/summary"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMinPriceBrand() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/md/price/brand").queryParam("order", "asc"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMaxPriceBrand() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/md/price/brand").queryParam("order", "desc"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getPriceByCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/md/price/category").queryParam("id", "3"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void notFoundPriceByCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/md/price/category").queryParam("id", "11"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

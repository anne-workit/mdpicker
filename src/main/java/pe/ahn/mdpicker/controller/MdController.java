package pe.ahn.mdpicker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.ahn.mdpicker.model.category.CategoryListItem;
import pe.ahn.mdpicker.model.price.PriceModel;
import pe.ahn.mdpicker.model.response.DataResponse;
import pe.ahn.mdpicker.service.DataService;

import java.util.List;

@RestController
@RequestMapping("/md")
public class MdController {
    @Autowired
    DataService dataService;

    @GetMapping(path="/cost/summary")
    public DataResponse<PriceModel> getCostSummary() {
        return new DataResponse<>(dataService.fetchMinPriceBrand());
    }

    @GetMapping(path="/cost/brand")
    public DataResponse<PriceModel> fetchPriceInfo(@RequestParam("order") String order) {
        return new DataResponse<>(
                dataService.fetchMinMaxPriceBrandByCategory(order)
        );
    }

    // http://localhost:8000/md/cost?category=1
    @GetMapping(path="/cost/category")
    public DataResponse<PriceModel> fetchPricesByCategory(@RequestParam("category") Long categoryId) {
        return new DataResponse<>(
                dataService.fetchMinMaxPriceBrandByCategory(categoryId)
        );
    }
}

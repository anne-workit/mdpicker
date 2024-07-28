package pe.ahn.mdpicker.controller;

import org.springframework.web.bind.annotation.*;
import pe.ahn.mdpicker.model.price.PriceModel;
import pe.ahn.mdpicker.model.response.DataResponse;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @GetMapping
    public DataResponse<PriceModel> fetchBrand() {
        PriceModel priceModel = new PriceModel();
        priceModel.setBrand("C");
        priceModel.setTotalPrice(Long.valueOf(20000));
        return new DataResponse<>(priceModel);
    }

    @PostMapping
    public int addBrand() {
        return 1;
    }

    @PutMapping
    public int editBrand() {
        return 1;
    }

    @DeleteMapping
    public int deleteBrand() {
        return 1;
    }
}

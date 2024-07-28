package pe.ahn.mdpicker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.ahn.mdpicker.model.category.CategoryListItem;
import pe.ahn.mdpicker.model.entity.Category;
import pe.ahn.mdpicker.model.price.PriceModel;
import pe.ahn.mdpicker.model.response.ErrorCode;
import pe.ahn.mdpicker.repo.CategoryRepository;
import pe.ahn.mdpicker.repo.PriceRepository;
import pe.ahn.mdpicker.system.ApiException;

import java.util.List;
import java.util.Optional;


@Service
public class DataService {
    @Autowired
    PriceRepository priceRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public PriceModel fetchMinPriceBrand() {
        PriceModel result = new PriceModel();
        List<CategoryListItem> categoryList = priceRepository.getMinPriceAndBrandByCategory();
        Long totalPrice = categoryList.stream().mapToLong(CategoryListItem::getPrice).sum();
        result.setTotalPrice(totalPrice);
        return result;
    }

    public PriceModel fetchMinMaxPriceBrandByCategory(String order) {
        PriceModel result = priceRepository.getBrandOrderByPrice(order);
        List<CategoryListItem> categoryList = priceRepository.getPricesByBrand(result.getBrandId());
        result.setCategories(categoryList);
        return result;
    }

    public PriceModel fetchMinMaxPriceBrandByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findCategoryByCategoryId(categoryId);

        if (category.isEmpty()) {
            throw new ApiException("No category data", ErrorCode.NOT_FOUND);
        };

        List<CategoryListItem> minPrice = priceRepository.getMinBrandByCategory(categoryId);
        List<CategoryListItem> maxPrice = priceRepository.getMaxBrandByCategory(categoryId);
        return new PriceModel(
                categoryId,
                category.get().getCategoryName(),
                minPrice,
                maxPrice
        );
    };
}

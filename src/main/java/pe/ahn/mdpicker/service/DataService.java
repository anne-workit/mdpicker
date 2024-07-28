package pe.ahn.mdpicker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.ahn.mdpicker.model.category.CategoryListItem;
import pe.ahn.mdpicker.model.category.CategoryInfo;
import pe.ahn.mdpicker.model.entity.Brand;
import pe.ahn.mdpicker.model.entity.CategoryPrice;
import pe.ahn.mdpicker.model.price.PriceModel;
import pe.ahn.mdpicker.model.response.ErrorCode;
import pe.ahn.mdpicker.repo.BrandRepository;
import pe.ahn.mdpicker.repo.PriceRepository;
import pe.ahn.mdpicker.system.ApiException;

import java.util.List;
import java.util.Objects;


@Service
public class DataService {
    @Autowired
    PriceRepository priceRepository;

    @Autowired
    BrandRepository brandRepository;

    public PriceModel fetchMinPriceBrand() {
        PriceModel result = new PriceModel();
        List<CategoryListItem> categoryList = priceRepository.getMinPriceAndBrandByCategory();
        for (CategoryListItem listItem : categoryList) {
            listItem.setCategory(Objects.requireNonNull(
                    CategoryInfo.getCategoryInfo(listItem.getCategoryId()))
            );
        }
        Long totalPrice = categoryList.stream().mapToLong(CategoryListItem::getPrice).sum();
        result.setTotalPrice(totalPrice);
        result.setCategories(categoryList);
        return result;
    }

    public PriceModel fetchMinMaxPriceBrandByCategory(String order) throws NullPointerException {
        if (!(order.equals(PriceOrder.ASC) || order.equals(PriceOrder.DESC))) {
            throw new ApiException("정렬 기준을 확인하세요. (최저가: asc / 최고가: desc)", ErrorCode.BAD_REQUEST);
        }
        PriceModel result = priceRepository.getBrandOrderByPrice(order);
        List<CategoryListItem> categoryList = priceRepository.getPricesByBrand(result.getBrandId());
        for (CategoryListItem listItem : categoryList) {
            listItem.setCategory(Objects.requireNonNull(
                    CategoryInfo.getCategoryInfo(listItem.getCategoryId()))
            );
        }
        result.setCategories(categoryList);
        return result;
    }

    public PriceModel fetchMinMaxPriceBrandByCategory(Long categoryId) {
        String categoryInfo = CategoryInfo.getCategoryInfo(categoryId);
        if (categoryInfo == null) {
            throw new ApiException("No category data", ErrorCode.NOT_FOUND);
        }

        List<CategoryListItem> minPrice = priceRepository.getMinBrandByCategory(categoryId);
        List<CategoryListItem> maxPrice = priceRepository.getMaxBrandByCategory(categoryId);
        return new PriceModel(
                categoryId,
                categoryInfo,
                minPrice,
                maxPrice
        );
    }

    public Long insertBrandAndPrice(Brand brand) {
        Brand newBrand = new Brand(brand.getBrandName(), brand.getUseYn());
        brandRepository.save(newBrand);

        List<CategoryPrice> categoryList = brand.getCategoryList();
        for (CategoryPrice categoryPrice : categoryList) {
            categoryPrice.setBrand(newBrand);
            priceRepository.save(categoryPrice);
        }
        return newBrand.getBrandId();
    }

    public Long updateBrandAndPrice(Brand requestBrand) {
        Brand brand = brandRepository
                .findById(requestBrand.getBrandId())
                .orElseThrow(() -> new ApiException("No Content", ErrorCode.BAD_REQUEST));

        brand.setBrandName(requestBrand.getBrandName());
        brandRepository.save(brand);

        List<CategoryPrice> categoryList = requestBrand.getCategoryList();
        for (CategoryPrice categoryPrice : categoryList) {
            CategoryPrice selectedCategoryPrice = priceRepository
                    .findById(categoryPrice.getId())
                    .orElseThrow(() -> new ApiException("No Content", ErrorCode.BAD_REQUEST));

            selectedCategoryPrice.setBrand(brand);
            selectedCategoryPrice.setPrice(categoryPrice.getPrice());
            priceRepository.save(selectedCategoryPrice);
        }
        return brand.getBrandId();
    }

    public Long deleteBrandData(Brand brand) {
        Brand newBrand = brandRepository
                .findById(brand.getBrandId())
                .orElseThrow(() -> new ApiException("No Content", ErrorCode.BAD_REQUEST));

        newBrand.setUseYn("N");
        brandRepository.save(newBrand);
        return newBrand.getBrandId();
    }
}

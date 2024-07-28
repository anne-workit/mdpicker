package pe.ahn.mdpicker.repo;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.ahn.mdpicker.model.category.CategoryListItem;
import pe.ahn.mdpicker.model.price.PriceModel;

import java.util.List;

import static pe.ahn.mdpicker.model.entity.QPrice.price1;


interface FILTER {
    String BRAND = "brand";
    String CATEGORY = "category";
}

interface ORDER {
    String ASC = "asc";
    String DESC = "desc";
}

@Repository
@AllArgsConstructor
public class PriceRepositoryImpl implements PriceCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CategoryListItem> getMinPriceAndBrandByCategory() {
        return queryFactory
                .select(Projections.constructor(CategoryListItem.class, price1.category.categoryName, price1.price, price1.brand.brandName))
                .from(price1)
                .where(Expressions.list(price1.category.categoryId, price1.price).in(
                        queryFactory
                                .select(price1.category.categoryId, price1.price.min())
                                .from(price1)
                                .groupBy(price1.category.categoryId)
                ))
                .fetch();
    }

    @Override
    public PriceModel getBrandOrderByPrice(String order) {
        JPAQuery<PriceModel> query = null;

        if (order.equals(ORDER.ASC)) {
            query = queryFactory
                    .select(Projections.constructor(PriceModel.class, price1.brand.brandId, price1.brand.brandName, price1.price.min()));
        } else {
            query = queryFactory
                    .select(Projections.constructor(PriceModel.class, price1.brand.brandId, price1.brand.brandName, price1.price.max()));
        }

        query = query
                .from(price1)
                .groupBy(price1.brand.brandId);

        if (order.equals(ORDER.ASC)) {
            query = query.orderBy(price1.price.min().asc());
        } else {
            query = query.orderBy(price1.price.max().desc());
        }
        return query.fetchOne();
    }

    @Override
    public List<CategoryListItem> getPricesByBrand(Long brandId) {
        return queryFactory
                .select(Projections.constructor(CategoryListItem.class, price1.category.categoryName, price1.price))
                .from(price1)
                .where(price1.brand.brandId.eq(brandId))
                .fetch();
    }

    @Override
    public List<CategoryListItem> getMinBrandByCategory(Long categoryId) {
        return getBrandByCategory(categoryId, ORDER.ASC);
    }

    @Override
    public List<CategoryListItem> getMaxBrandByCategory(Long categoryId) {
        return getBrandByCategory(categoryId, ORDER.DESC);
    }

    private List<CategoryListItem> getBrandByCategory(Long categoryId, String order) {
        JPAQuery<CategoryListItem> query = null;

        if (order.equals(ORDER.ASC)) {
            query = queryFactory
                    .select(Projections.constructor(CategoryListItem.class, price1.brand.brandName, price1.price.min()));
        } else {
            query = queryFactory
                    .select(Projections.constructor(CategoryListItem.class, price1.brand.brandName, price1.price.max()));
        }

        query = query
                .from(price1)
                .where(price1.category.categoryId.eq(categoryId))
                .groupBy(price1.brand.brandId);

        if (order.equals(ORDER.ASC)) {
            query = query.orderBy(price1.price.min().asc());
        } else {
            query = query.orderBy(price1.price.max().desc());
        }
        return query
                .limit(1L)
                .fetch();
    }
}
package pe.ahn.mdpicker.model.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryListItem {
    @NonNull
    private String category;
    @NonNull
    private Long price;
    private String brand;
    private Long categoryId;

    public CategoryListItem(Long categoryId, @NonNull Long price) {
        this.categoryId = categoryId;
        this.price = price;
    }

    public CategoryListItem(@NonNull String category, @NonNull Long price, String brand) {
        this.category = category;
        this.price = price;
        this.brand = brand;
    }
}

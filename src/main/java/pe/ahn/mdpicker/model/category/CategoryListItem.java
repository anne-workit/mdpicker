package pe.ahn.mdpicker.model.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryListItem {
    @NonNull
    private String category;
    @NonNull
    private Long price;
    private String brand;
}

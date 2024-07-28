package pe.ahn.mdpicker.model.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.ahn.mdpicker.model.category.CategoryListItem;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandParam {
    private String brand;
    private ArrayList<CategoryListItem> categoryParamList;
    private String useYn;
}

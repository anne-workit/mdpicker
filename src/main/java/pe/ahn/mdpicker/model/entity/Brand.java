package pe.ahn.mdpicker.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "brand")
public class Brand {
    @Id @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    @NonNull
    @Column(nullable = false, length = 10, name = "brand_name")
    private String brandName;

    @NonNull
    @Column(nullable = false, length = 1, name = "use_yn")
    private String useYn;

    @OneToMany
    @JoinColumn(name = "brand_id")
    private List<CategoryPrice> categoryList = new ArrayList<CategoryPrice>();
}

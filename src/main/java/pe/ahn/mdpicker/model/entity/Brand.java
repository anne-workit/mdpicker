package pe.ahn.mdpicker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brand")
public class Brand {
    @Id @Column(name = "brand_id")
    @GeneratedValue
    private Long brandId;

    @Column(nullable = false, length = 10, name = "brand_name")
    private String brandName;
}

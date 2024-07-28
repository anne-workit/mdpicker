package pe.ahn.mdpicker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
public class Category {
    @Id @Column(name = "category_id")
    @GeneratedValue
    private Long categoryId;

    @Column(nullable = false, length = 50, name = "category_name")
    private String categoryName;
}

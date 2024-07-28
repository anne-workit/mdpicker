package pe.ahn.mdpicker.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "price")
public class Price {
    @Id @Column(name = "price_id")
    @GeneratedValue
    private long priceId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, name = "price")
    private long price;
}

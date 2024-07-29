package pe.ahn.mdpicker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.ahn.mdpicker.model.entity.CategoryPrice;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<CategoryPrice, Long>, PriceCustomRepository {
}
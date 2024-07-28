package pe.ahn.mdpicker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.ahn.mdpicker.model.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>, PriceCustomRepository {
}
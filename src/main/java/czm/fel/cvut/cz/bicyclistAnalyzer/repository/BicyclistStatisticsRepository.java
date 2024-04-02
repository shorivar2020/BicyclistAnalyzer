package czm.fel.cvut.cz.bicyclistAnalyzer.repository;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.Bicyclist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicyclistStatisticsRepository extends JpaRepository<Bicyclist, Long> {
}
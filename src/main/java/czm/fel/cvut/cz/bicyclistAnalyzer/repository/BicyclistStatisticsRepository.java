package czm.fel.cvut.cz.bicyclistAnalyzer.repository;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.BicyclistStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicyclistStatisticsRepository extends JpaRepository<BicyclistStatistics, Long> {
}
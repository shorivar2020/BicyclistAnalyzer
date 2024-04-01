package czm.fel.cvut.cz.bicyclistAnalyzer.service;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.BicyclistStatistics;
import czm.fel.cvut.cz.bicyclistAnalyzer.repository.BicyclistStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BicyclistStatisticsService {
    @Autowired
    private BicyclistStatisticsRepository repository;

    public List<BicyclistStatistics> getAllStatistics() {
        return repository.findAll();
    }

    public void saveStatistics(BicyclistStatistics statistics) {
        repository.save(statistics);
    }

}

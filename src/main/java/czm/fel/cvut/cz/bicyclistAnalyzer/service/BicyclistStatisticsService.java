package czm.fel.cvut.cz.bicyclistAnalyzer.service;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.Bicyclist;
import czm.fel.cvut.cz.bicyclistAnalyzer.repository.BicyclistStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BicyclistStatisticsService {
    @Autowired
    private BicyclistStatisticsRepository repository;

    public List<Bicyclist> getAllStatistics() {
        return repository.findAll();
    }

    public void saveStatistics(Bicyclist statistics) {
        repository.save(statistics);
    }

}

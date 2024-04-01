package czm.fel.cvut.cz.bicyclistAnalyzer.controller;
import czm.fel.cvut.cz.bicyclistAnalyzer.model.BicyclistStatistics;
import czm.fel.cvut.cz.bicyclistAnalyzer.service.BicyclistStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bicycle-statistics")
public class BicyclistStatisticsController {
    @Autowired
    private BicyclistStatisticsService service;

    @GetMapping
    public List<BicyclistStatistics> getAllStatistics() {
        return service.getAllStatistics();
    }

    @PostMapping
    public void addStatistics(@RequestBody BicyclistStatistics statistics) {
        service.saveStatistics(statistics);
    }

}

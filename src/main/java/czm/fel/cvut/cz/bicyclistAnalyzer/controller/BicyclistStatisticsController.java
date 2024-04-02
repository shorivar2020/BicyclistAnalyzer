package czm.fel.cvut.cz.bicyclistAnalyzer.controller;
import czm.fel.cvut.cz.bicyclistAnalyzer.model.Bicyclist;
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
    public List<Bicyclist> getAllStatistics() {
        return service.getAllStatistics();
    }

    @PostMapping
    public void addStatistics(@RequestBody Bicyclist statistics) {
        service.saveStatistics(statistics);
    }

}

package czm.fel.cvut.cz.bicyclistAnalyzer.service;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.BicycleRecording;
import czm.fel.cvut.cz.bicyclistAnalyzer.repository.BicycleRecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BicycleRecordingServiceImpl implements BicycleRecordingService{
    @Autowired
    private BicycleRecordingRepository repository;
    public List<BicycleRecording> getAllStatistics() {
        return repository.findAll();
    }
    public void saveStatistics(BicycleRecording statistics) {
        repository.save(statistics);
    }
    public Long getBicycleRecordingCount() {
        return repository.count();
    }
    public Long getBicycleCount() {
        return repository.sumBicycleCount();
    }

    public Double getAverageBicycleRecordingCountPerDay() {
        List<Object[]> result = repository.getBicyclistCountPerDay();
        long totalDays = result.stream().map(row -> row[0]).distinct().count();
        long totalBicyclists = result.stream().mapToLong(row -> (long)row[1]).sum();
        if (totalDays != 0) {
            return (double) totalBicyclists / totalDays;
        } else {
            return 0.0;
        }
    }

    public List<Object[]> getBicycleRecordingPerDay(){
        return repository.getBicyclistCountPerDay();
    }
    public List<Object[]> getMostPopularDay() {
        return repository.findDateWithMaxBicyclistCount();
    }
    public List<Object[]> getMostPopularHour(){
        return repository.findHourWithMaxBicyclistCount();
    }
    public List<Object[]> getMostPopularSection(){
        return repository.findRoadSegmentWithMaxBicyclistCount();
    }
}

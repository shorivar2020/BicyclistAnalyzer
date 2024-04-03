package czm.fel.cvut.cz.bicyclistAnalyzer.service;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.BicycleRecording;

import java.util.List;

public interface BicycleRecordingService {
    List<BicycleRecording> getAllStatistics();
    void saveStatistics(BicycleRecording statistics);
    Long getBicycleRecordingCount();
    Long getBicycleCount();
    List<Object[]> getBicycleRecordingPerDay();
    Double getAverageBicycleRecordingCountPerDay();
    List<Object[]> getMostPopularDay();
    List<Object[]> getMostPopularHour();
    List<Object[]> getMostPopularSection();

}

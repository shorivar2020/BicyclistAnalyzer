package czm.fel.cvut.cz.bicyclistAnalyzer.repository;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.BicycleRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BicycleRecordingRepository extends JpaRepository<BicycleRecording, Long> {

    @Query("SELECT COUNT(b) FROM BicycleRecording b WHERE b.ID = :id")
    Long getBicycleRecordingByID(Long id);
    @Query("SELECT b FROM BicycleRecording b")
    Long getAllBicycleRecording();

    @Query("SELECT COUNT(b) FROM BicycleRecording b")
    Long getNumBicycleRecording();

    @Query("SELECT b FROM BicycleRecording b WHERE DATE(b.recordingStart) = :date")
    BicycleRecording getBicyclistForDate(LocalDate date);

    @Query("SELECT COUNT(b) FROM BicycleRecording b WHERE DATE(b.recordingStart) = :date")
    Long getBicyclistCountForDate(LocalDate date);

    @Query("SELECT SUM(b.bicycleCount) FROM BicycleRecording b")
    Long sumBicycleCount();

    @Query("SELECT DATE(b.recordingStart) AS date, COUNT(b) AS count " +
            "FROM BicycleRecording b " +
            "GROUP BY DATE(b.recordingStart) " +
            "ORDER BY COUNT(b) DESC")
    List<Object[]> findDateWithMaxBicyclistCount();

    @Query("SELECT HOUR(b.recordingStart) AS hour, COUNT(b) AS count " +
            "FROM BicycleRecording b " +
            "GROUP BY HOUR(b.recordingStart) " +
            "ORDER BY COUNT(b) DESC")
    List<Object[]> findHourWithMaxBicyclistCount();

    @Query("SELECT DATE(b.recordingStart) AS date, COUNT(b) AS count " +
            "FROM BicycleRecording b GROUP BY DATE(b.recordingStart)")
    List<Object[]> getBicyclistCountPerDay();

    @Query("SELECT HOUR(b.recordingStart) AS hour, COUNT(b) AS count " +
            "FROM BicycleRecording b GROUP BY HOUR(b.recordingStart)")
    List<Object[]> getBicyclistCountPerHour();

    @Query("SELECT b.cameraCode, b.nextCameraCode, COUNT(b) AS count " +
            "FROM BicycleRecording b " +
            "GROUP BY b.cameraCode, b.nextCameraCode " +
            "ORDER BY COUNT(b) DESC")
    List<Object[]> findRoadSegmentWithMaxBicyclistCount();
}
package czm.fel.cvut.cz.bicyclistAnalyzer.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class BicyclistStatistics {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String cameraCode;
    private String nextCameraCode;
    private LocalDateTime measurementStart;
    private LocalDateTime measurementEnd;
    private int bicycleCount;

}

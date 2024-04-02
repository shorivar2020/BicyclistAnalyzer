package czm.fel.cvut.cz.bicyclistAnalyzer.handler;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.Bicyclist;
import czm.fel.cvut.cz.bicyclistAnalyzer.service.BicyclistStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {


    public void parse(String csvFile, BicyclistStatisticsService bicyclistStatisticsService) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);

                String cameraCode = data[0];
                String nextCameraCode = data[1];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                LocalDateTime measurementStart = LocalDateTime.parse(data[2], formatter);
                LocalDateTime measurementEnd = LocalDateTime.parse(data[3], formatter);
                int bicycleCount = data.length > 4 ? Integer.parseInt(data[4]) : 0;

                Bicyclist b = new Bicyclist();
                b.setCameraCode(cameraCode);
                b.setNextCameraCode(nextCameraCode);
                b.setMeasurementStart(measurementStart);
                b.setMeasurementEnd(measurementEnd);
                b.setBicycleCount(bicycleCount);

                bicyclistStatisticsService.saveStatistics(b);
                System.out.println(b.getCameraCode());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

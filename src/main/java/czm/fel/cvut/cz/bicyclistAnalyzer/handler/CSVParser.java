package czm.fel.cvut.cz.bicyclistAnalyzer.handler;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.BicycleRecording;
import czm.fel.cvut.cz.bicyclistAnalyzer.service.BicycleRecordingService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVParser {


    public void parse(String csvFile, BicycleRecordingService bicyclistStatisticsService) {
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

                BicycleRecording b = new BicycleRecording();
                b.setCameraCode(cameraCode);
                b.setNextCameraCode(nextCameraCode);
                b.setRecordingStart(measurementStart);
                b.setRecordingEnd(measurementEnd);
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

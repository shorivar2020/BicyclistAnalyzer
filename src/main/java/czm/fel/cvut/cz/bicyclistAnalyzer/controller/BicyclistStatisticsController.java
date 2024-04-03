package czm.fel.cvut.cz.bicyclistAnalyzer.controller;
import czm.fel.cvut.cz.bicyclistAnalyzer.handler.CSVParser;
import czm.fel.cvut.cz.bicyclistAnalyzer.model.BicycleRecording;
import czm.fel.cvut.cz.bicyclistAnalyzer.model.Statistic;
import czm.fel.cvut.cz.bicyclistAnalyzer.service.BicycleRecordingService;
import czm.fel.cvut.cz.bicyclistAnalyzer.service.ExportService;
import czm.fel.cvut.cz.bicyclistAnalyzer.service.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bicycle-statistics")
public class BicyclistStatisticsController {

    @Autowired
    private BicycleRecordingService bicRecService;

    @Autowired
    private ExportService exportService;

    @GetMapping
    public List<BicycleRecording> getAllStatistics() {
        return bicRecService.getAllStatistics();
    }

    @PostMapping
    public void addStatistics(@RequestBody BicycleRecording statistics) {
        bicRecService.saveStatistics(statistics);
    }

    @GetMapping("/CC")
    public String getBicycleCount() {
        return bicRecService.getBicycleCount().toString();
    }

    @GetMapping("/PP")
    public String getAverageBicycleRecordingCountPerDay() {
        return bicRecService.getAverageBicycleRecordingCountPerDay().toString();
    }

    @GetMapping("/CP")
    public String getBicycleCountPerDay() {
        StringBuilder bicyclePerDay = new StringBuilder();
        for (Object[] row : bicRecService.getBicycleRecordingPerDay()) {
            bicyclePerDay.append("Date: ").append(row[0]).append(" Bicyclist count: ").append(row[1]).append(" \n");
        }
        return bicyclePerDay.toString();
    }

    @GetMapping("/DM")
    public String getMostPopularDay() {
        StringBuilder popularDays = new StringBuilder();
        popularDays.append("Days: ");
        for (Object[] row : bicRecService.getMostPopularDay()) {
            popularDays.append(row[0]).append(" ");
        }
        return popularDays.toString();
    }

    @GetMapping("/NH")
    public String getMostPopularHour() {
        StringBuilder popularHours = new StringBuilder();
        popularHours.append("Hours: ");
        for (Object[] row : bicRecService.getMostPopularHour()) {
            popularHours.append(row[0]).append(" ");
        }
        return popularHours.toString();
    }

    @GetMapping("/NU")
    public String getMostPopularSection() {
        StringBuilder popularSections = new StringBuilder();
        popularSections.append("Sections: ");
        for (Object[] row : bicRecService.getMostPopularSection()) {
            popularSections.append(row[0]).append(" ");
        }
        return popularSections.toString();
    }

    public void exportData(List<Statistic> statistics, String flag){
        Report report = exportService.createReport(statistics);
        if(flag.contains("JSON")){
            exportService.saveInFile("json_export.json", exportService.createJSON(report));
        }
        if(flag.contains("XML")){
            exportService.saveInFile("xml_export.xml", exportService.createXML(report));
        }
        if(flag.contains("STR")){
            exportService.saveInFile("export.txt", exportService.createString(report));
        }

    }

    public void parsingData(String inputFile){
        CSVParser csvParser = new CSVParser();
        csvParser.parse(inputFile.substring(0, inputFile.length() - 1), bicRecService);
    }
}

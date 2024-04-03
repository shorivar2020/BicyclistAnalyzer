package czm.fel.cvut.cz.bicyclistAnalyzer.service;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.Statistic;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Log
public class ExportService {
    StringBuilder stringBuilder;
    public void addNewXMLStatistics(String name, String result){
        stringBuilder.append("<statistics>");
        stringBuilder.append("<name>").append(name).append("</name>");
        stringBuilder.append("<result>").append(result).append("</result>");
        stringBuilder.append("</statistics>");
    }

    public String createString(Report report){
        stringBuilder.append("My statistic");
        stringBuilder.append("Created: ").append(report.created);
        for(Statistic s: report.statistics){
            stringBuilder.append("Statistic: ").append(s.name).append(" ").append(s.result);
        }
        return stringBuilder.toString();
    }

    public String createXML(Report report){
        stringBuilder = new StringBuilder();
        stringBuilder.append("<report>");
        stringBuilder.append("<created>").append(report.created).append("</created>");
        stringBuilder.append("<statisticsResults>");
        for(Statistic s: report.statistics){
            addNewXMLStatistics(s.name, s.result);
        }
        stringBuilder.append("</statisticsResults>");
        stringBuilder.append("</report>");
        return stringBuilder.toString();
    }

    public String createJSON(Report report){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(report);
    }

    public Report createReport(List<Statistic> statistics){
        LocalDateTime created = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        String formattedDateTime = created.format(formatter);
        return new Report(formattedDateTime, statistics);
    }

    public void saveInFile(String fileName, String data){
        try (Writer writer = new FileWriter(fileName)) {
            writer.write(data);
        } catch (IOException e) {
            log.warning("File was not save " + e);
        }
    }
}

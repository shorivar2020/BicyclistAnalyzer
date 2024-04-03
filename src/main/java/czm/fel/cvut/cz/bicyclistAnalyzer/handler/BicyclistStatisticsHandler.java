package czm.fel.cvut.cz.bicyclistAnalyzer.handler;

import czm.fel.cvut.cz.bicyclistAnalyzer.controller.BicyclistStatisticsController;
import czm.fel.cvut.cz.bicyclistAnalyzer.model.Statistic;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log
@Component
public class BicyclistStatisticsHandler implements CommandLineRunner {
    @Autowired
    private BicyclistStatisticsController bicRecController;
    private static final int argCount = 3;

    @Override
    public void run(String... args){
        log.fine("Get arguments from command line");
        if(args.length == argCount) {
            log.fine("Start parsing");
//            bicRecController.parsingData(args[0]);
            log.fine("Start file process");
            List<Statistic> statistics = processStatistic(args[1]);
            log.fine("Start export data");
            bicRecController.exportData(statistics, args[2]);
        }else{
            log.warning("An invalid number of arguments was received");
        }
    }

    private List<Statistic> processStatistic(String statFlag){
        log.fine("Start get statistics");
        List<Statistic> statistics = new ArrayList<>();
        if(statFlag.contains("CC")){
            statistics.add(new Statistic("Bicycle recording count",
                    bicRecController.getBicycleCount()));
        }
        if(statFlag.contains("PP")){
            statistics.add(new Statistic("Average bicycle recording count",
                    bicRecController.getAverageBicycleRecordingCountPerDay()));
        }
        if(statFlag.contains("CP")){
            statistics.add(new Statistic("Bicycle count per day",
                    bicRecController.getBicycleCountPerDay()));
        }
        if(statFlag.contains("DM")){
            statistics.add(new Statistic("Most popular day",
                    bicRecController.getMostPopularDay()));
        }
        if(statFlag.contains("NH")){
            statistics.add(new Statistic("Most popular hour",
                    bicRecController.getMostPopularHour()));
        }
        if(statFlag.contains("NU")){
            statistics.add(new Statistic("Most popular section",
                    bicRecController.getMostPopularSection()));
        }
        return statistics;
    }
}

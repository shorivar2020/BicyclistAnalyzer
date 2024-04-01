package czm.fel.cvut.cz.bicyclistAnalyzer.handler;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log
@Component
public class BicyclistStatisticsHandler implements CommandLineRunner {

    private String inputFile;
    private String statisticAbbreviations;
    private String exportAbbreviations;
    private static final int argCount = 3;

    @Override
    public void run(String... args){
        log.fine("Get arguments from command line");
        if(args.length == argCount) {
            this.inputFile = args[0];
            this.statisticAbbreviations = args[1];
            this.exportAbbreviations = args[2];
            processAndExportStatistics();
        }else{
            log.warning("An invalid number of arguments was received");
        }
    }

    private void processAndExportStatistics() {
        log.fine("Start file process");
        System.out.println(inputFile);
        System.out.println(statisticAbbreviations);
        System.out.println(exportAbbreviations);
    }
}

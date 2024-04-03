package czm.fel.cvut.cz.bicyclistAnalyzer.service;

import czm.fel.cvut.cz.bicyclistAnalyzer.model.Statistic;

import java.util.List;

public class Report {
    String created;
    List<Statistic> statistics;

    public Report(String created, List<Statistic> statistics) {
        this.created = created;
        this.statistics = statistics;
    }
}

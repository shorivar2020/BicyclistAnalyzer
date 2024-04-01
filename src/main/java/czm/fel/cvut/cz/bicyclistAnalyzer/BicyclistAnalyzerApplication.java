package czm.fel.cvut.cz.bicyclistAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BicyclistAnalyzerApplication {
    public static void main(String... args) {
        ConfigurableApplicationContext context = SpringApplication.run(BicyclistAnalyzerApplication.class, args);
        context.close();
    }
}

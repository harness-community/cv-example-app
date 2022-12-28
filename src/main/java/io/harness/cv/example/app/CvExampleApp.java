package io.harness.cv.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
 * Spring Boot Start Class for CV Example App.
 * Boots and generates metrics via the Metric Generator. 
 * @author Ravi Lachhman
*/

@SpringBootApplication
public class CvExampleApp {

	
	public static void main(String[] args) {
		SpringApplication.run(CvExampleApp.class, args);
		
		//Start Stable for :stable tag
		//GenerateStableMetrics gsm = new GenerateStableMetrics();
		//gsm.generateAll();

		//Start Unstable for :unstable tag
		GenerateUnstableMetrics gusm = new GenerateUnstableMetrics();
		gusm.generateAll();

	}

}

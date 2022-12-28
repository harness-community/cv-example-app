package io.harness.cv.example.app;

import java.util.ArrayList;
import java.util.List;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;

/** 
 * Generates stable metrics for and writes to the default Micrometer Registry. 
 * In this case, app should be exposed to Prometheus and auto-wired to write. 
 * @author Ravi Lachhman
*/

public class GenerateStableMetrics {


    Counter counter;
	Gauge gauge;
	int gaugeListInitCapacity = 2;
	List<String> gaugeList = new ArrayList<>(gaugeListInitCapacity);

	public void generateAll()	{

		//Registry: https://stackoverflow.com/questions/70124612/spring-boot-micrometer-prometheus-not-logging-any-metrics-i-added

		generateCounter();
		generateGauge();
		try {
			generateMetrics();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public  void generateCounter() {
	
		counter = Counter
				.builder("CV_Counter_Example")
				.description("Prometheus Counter Generator")
				.tags("Created For", "As an Example by Harness")
				.register(Metrics.globalRegistry);
	}

	public void incrementCounter() {

		counter.increment();

	}

	public void generateGauge() {
	
		gauge = Gauge
				.builder("CV_Gauge_Example", gaugeList, List::size)
				.tags("Created For", "As an Example by Harness")
				.register(Metrics.globalRegistry);

	}

	public void generateMetrics() throws InterruptedException {
		
		System.out.println("Generating Stable Metrics");
		counter.increment();
		gaugeList.add(String.valueOf(gaugeListInitCapacity + 1));

	}
    
}

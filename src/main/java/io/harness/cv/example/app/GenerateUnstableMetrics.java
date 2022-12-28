package io.harness.cv.example.app;

import java.util.ArrayList;
import java.util.List;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;

/**
 * Generates unstable metrics for and writes to the default Micrometer Registry.
 * In this case, app should be exposed to Prometheus and auto-wired to write.
 * 
 * @author Ravi Lachhman
 */

public class GenerateUnstableMetrics {

	Counter counter;
	Gauge gauge;
	int gaugeListInitCapacity = 2;
	List<String> gaugeList = new ArrayList<>(gaugeListInitCapacity);
	boolean runUnstable = true;

	public void generateAll() {

		// Registry to use:
		// https://stackoverflow.com/questions/70124612/spring-boot-micrometer-prometheus-not-logging-any-metrics-i-added

		generateCounter();
		generateGauge();
		try {
			generateMetrics();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void generateCounter() {

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

		int maxIterations = 10000;
		int metricIntervalMS = 15000;

		for (int i = 0; i < maxIterations && runUnstable == true; i++) {
			System.out.println("Incrementing Metrics");
			counter.increment();
			gaugeList.add(String.valueOf(i));
			Thread.sleep(metricIntervalMS);
		}

	}

	public void stopUnstableMetricGeneration() {
		System.out.println("Stopping Unstable Metric Creation");
		runUnstable = false;
	}

	public void startUnstableMetricGeneration() throws InterruptedException {
		System.out.println("Restarting Unstable Metric Creation");
		runUnstable = true;
		generateCounter();
		generateGauge();
		generateMetrics();
	}

}

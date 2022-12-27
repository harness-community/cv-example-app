package io.harness.cv.example.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class StartPageController {

	Date date = new Date();
	String strDateFormat = "hh:mm:ss a";
	DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	String formattedDate= dateFormat.format(date);

    @GetMapping("/endpoint-test")
	public String endpoint() {
		return "Endpoint Reached Successfully!";
	}

	@GetMapping("/run-stable")
	public String stable() {
		return "Stable Version Triggered at " + formattedDate;
	}

	@GetMapping("/run-unstable")
	public String unstable() {
		return "Un-stable Version Triggered at" + formattedDate;
	}
    
}

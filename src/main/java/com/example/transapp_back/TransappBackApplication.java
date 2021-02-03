package com.example.transapp_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class TransappBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransappBackApplication.class, args);
		/*
		List<String> lines = new ArrayList<>();
		lines.add("御堂筋線");
		lines.add("JR線");
		String hour = "10";
		String minute = "15";
		String depOrArv = "depart";
		boolean addFeeTrain = true;
		int theNumberOfSearch = 3;
		String priority = "faster";

		List<Document> testList = new SearchDAO().getTrains(lines, hour, minute, depOrArv, addFeeTrain, theNumberOfSearch);

		List<Trains> testTrainsList = new SearchLogic().setTrainsClass(testList);

		List<Trains> testSortList = new SearchLogic().sortTrains(testTrainsList, depOrArv, priority, theNumberOfSearch);

		for (Trains trains : testSortList) {
			System.out.println(trains.getId());
			System.out.println(trains.getDepTime());
			System.out.println(trains.getArvTime());
		}

		System.out.println(json);
		 */

	}
}

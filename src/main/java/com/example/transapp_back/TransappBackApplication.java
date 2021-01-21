package com.example.transapp_back;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Trains;
import com.example.transapp_back.logic.SearchLogic;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TransappBackApplication {

	public static void main(String[] args) {
		/*List<String> lines = new ArrayList<>();
		lines.add("御堂筋線");
		lines.add("JR線");
		String hour = "10";
		String minute = "15";
		String depOrArv = "depart";
		boolean addFeeTrain = true;
		int theNumberOfSearch = 3;
		String[] priority = {"faster"};

		List<Document> testList = new SearchDAO().getTrains(lines, hour, minute, depOrArv, addFeeTrain, theNumberOfSearch);

		System.out.println(testList.size());
		for(Document train: testList){
			System.out.println(train);
		}

		List<Trains> testTrainsList = new SearchLogic().setTrainsClass(testList);

		List<Trains> testSortList = new SearchLogic().sortTrains(testTrainsList, depOrArv, priority, theNumberOfSearch);
		System.out.println("");
		System.out.println("results");
		for(Trains sortTrain: testSortList){
			System.out.println(sortTrain);
			System.out.println(sortTrain.getLine());
			System.out.println(sortTrain.getDepTime());
			System.out.println(sortTrain.getArvTime());
		}
		*/
	}
}

package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TextArea;

public class Controller {

	private final String file = "Mesonet.txt";
	public ArrayList<String> stations;
	private final int stationStart = 0;
	private final int stationEnd = 4;

	public Controller(TextArea a10) {
		try {
			parseFile();
		}
		catch(Exception e) {
			a10.setText(a10.getText()+ "\n"+"Failed to parse file: \n" + e);
		}
	}

	public ArrayList<String> getStations(int hd, String station){
		ArrayList<String> ret = new ArrayList<String>();
		if(station != null){
			for(String s: stations){
				if( hamDist(station, s) == hd){
					ret.add(s);
				}
			}
		}
		return ret;
	}
	
	public ArrayList<String> getAll(){
		return stations;
	}
	
	public int[] getHDs(String station){
		int[] ret = new int[]{0,0,0,0,0};
		for(String s: stations){
			switch(hamDist(s, station)){
			case 0:
				ret[0]++;
				break;
			case 1:
				ret[1]++;
				break;
			case 2:
				ret[2]++;
				break;
			case 3:
				ret[3]++;
				break;
			case 4:
				ret[4]++;
				break;
			}
		}
		return ret;
	}

	private int hamDist(String str1, String str2) {
		int ret = 0;
		for(int i = 0; i < str1.length(); i++) {
			if(!str1.substring(i,i + 1).equals(str2.substring(i,i+1))) {
				ret++;
			}
		}
		return ret;
	}

	private void parseFile() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
    	String line = reader.readLine();
    	stations = new ArrayList<String>();
    	while(line!=null) {
    		stations.add(line.substring(stationStart,stationEnd));
    		line = reader.readLine();
    	}
    	reader.close();
    }





}


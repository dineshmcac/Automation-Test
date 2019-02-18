package pageObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * 
 * @author dchengalvarayan
 *Re-Usable wrapper for reading file from CSV
 */

public class ReusableWrapper {
	
	public static CSVReader csvReader;
	
	public  CSVReader fileReader() {
		CSVReader csvReader = null;
		try {
			FileReader filereader = new FileReader("./resource/simple_test_file.csv");
			csvReader = new CSVReader(filereader);
		} catch (Exception e) {
			Reporter.log(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return csvReader;
	}
	
	public CSVReader skip1strowfileReader() {
		CSVReader csvReader = null;
		try {
			FileReader filereader = new FileReader("./resource/simple_test_file.csv");
			csvReader = new CSVReaderBuilder(filereader) .withSkipLines(1).build();
		} catch (Exception e) {
			Reporter.log(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return csvReader;
	}
	
	
	public  List<String> fetchDataForColumn(String coloumn) {
		List<String> coloumnValues = new ArrayList<String>();
		try {
		csvReader = fileReader();
		int columnPosition;
		String[] nextRecord;
		nextRecord = csvReader.readNext();
		columnPosition = getHeaderLocation(nextRecord, coloumn);
		while ((nextRecord = csvReader.readNext()) != null) {
			coloumnValues.add(nextRecord[columnPosition]);
		}
		}
		catch(Exception e) {
			Reporter.log(e.getLocalizedMessage());
			System.out.println(e.getMessage());
		}
		return coloumnValues;
	}
	
	public Map<String, String> fetchDataForColumn(String column1, String column2){
		Map<String, String> finalValue = new HashMap<String, String>();
		try {
		csvReader = fileReader();
		int columnPosition1, columnPosition2;
		String[] nextRecord;
		nextRecord = csvReader.readNext();
		columnPosition1 = getHeaderLocation(nextRecord, column1);
		columnPosition2 = getHeaderLocation(nextRecord, column2);
		while ((nextRecord = csvReader.readNext()) != null) {
			finalValue.put(nextRecord[columnPosition1], nextRecord[columnPosition2]);
		}
		}
		catch(Exception e) {
			Reporter.log(e.getLocalizedMessage());
			System.out.println(e.getMessage());
		}
		return finalValue;
	}

	private static int getHeaderLocation(String[] headers, String columnName) {
		return Arrays.asList(headers).indexOf(columnName);
	}
	
	public void writeFile(String finalString) {
		try {
			File outputFile = new File("output.txt");
			FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(finalString.getBytes());
		} catch (Exception e) {
			Reporter.log(e.getLocalizedMessage());
			e.printStackTrace();
		} 
	}

}

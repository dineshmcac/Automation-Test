package pageObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;

public class TestCasePO {

	ReusableWrapper wr = new ReusableWrapper();
	
	
	public boolean validate256() { 
		try {
			CSVReader csvReader = wr.skip1strowfileReader();
			List<String[]> allData = csvReader.readAll();
			for (String[] row : allData) {
				for (String cell : row) {
					if(cell.length()>256)
					{
						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	 public boolean regExMatcher(List<String> columnValues, String Regex) 
	    { 
         Pattern pat = Pattern.compile(Regex);  
		 for(String val:columnValues ) {
	        if (val==null || !pat.matcher(val).matches() )
	        return false;
		 }
		return true;  
	    } 
	 
	 public boolean notEmpty(List<String> columnValues) {
		 for(String temp:columnValues)
			 if(temp==null || temp.trim().equals(""))
				 	return false;
		return true;
	 }
	 
	public String coreLogic(Map<String,String> names) {
		Map<String, List<String>> lastNameFirstNamesIndex = new HashMap<>();
		names.forEach((key, value) -> {
			String[] lastNameParts = value.contains("-") ? value.split("-") : value.split(" ");
			for (String lastNamePart : lastNameParts) {
				List<String> relatedFirstNames = lastNameFirstNamesIndex.get(lastNamePart);
				if (relatedFirstNames == null) {
					relatedFirstNames = new ArrayList<>();
					lastNameFirstNamesIndex.put(lastNamePart, relatedFirstNames);
				}
				relatedFirstNames.add(key);
			}
		});

		StringBuffer finalOutput = new StringBuffer();
		names.forEach((key, value) -> {
			String[] lastNameParts = value.contains("-") ? value.split("-") : value.split(" ");
			StringBuilder relatedNamesStr = new StringBuilder();
			for (String lastNamePart : lastNameParts) {
				lastNameFirstNamesIndex.get(lastNamePart).stream()
						.filter(relatedFirstName -> !relatedFirstName.equals(key))
						.forEach(relatedName -> relatedNamesStr.append(relatedName).append(" ").append(names.get(relatedName)).append(","));
			}
			if (relatedNamesStr.length() != 0) {
				System.out
						.println(key + " " + value + " is related to " + relatedNamesStr.deleteCharAt(relatedNamesStr.length() - 1));
				finalOutput.append(key + " " + value + " is related to " + relatedNamesStr.deleteCharAt(relatedNamesStr.length() - 1) + "\n");
			}
		});
		return finalOutput.toString();
	}

}

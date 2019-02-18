package testing;

import org.testng.annotations.Test;
import java.util.Map;

import org.testng.annotations.Test;

import pageObject.BaseClass;
import pageObject.ReusableWrapper;
import pageObject.TestCasePO;

/**
 * 
 * @author dchengalvarayan
 * TestNG test class file with BaseClass with prevalidation
 */

public class ValidateCSV extends BaseClass{
	
	ReusableWrapper wr = new ReusableWrapper();
	TestCasePO tcwr = new TestCasePO();
	
	/**
	 * Validate Search Criteria based on the question
	 * 1. They have the same last name or,
	 * 2. The last name of one appears as one part of the hyphenated last name ofanother or,
	 *  3. A part of the hyphenated name of one appears as one part of the hyphenated part of another.
	 */

	@Test
	public void test() {
		Map<String,String> names =wr.fetchDataForColumn("first_name","last_name");
		String finalOutput = tcwr.coreLogic(names); 
		wr.writeFile(finalOutput);
		
		System.out.println("======================================================================================================");
		System.out.println("PLZ FIND OUTPUT FILE USING BELOW COMMAND  "
				+ "IF WINDOWS - dir output.txt"
				+ " IF MAC or Linux - vi output.txt ");
		System.out.println("=======================================================================================================");	
	}
}

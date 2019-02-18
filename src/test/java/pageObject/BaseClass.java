package pageObject;

import java.util.List;

/**
 * Pre Validate
 * 1. Each field value is no greater than 256 characters.
 * 2. The first name, last name and email address are mandatory.
 * 3. The first name and last name fields must:
 * 4. Email must be in standard email format.
 */

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	ReusableWrapper wr = new ReusableWrapper();
	TestCasePO tcwr = new TestCasePO();
	String lastName_Validation = "([a-zA-Z]+)|([a-zA-Z][a-zA-Z-]+)|([a-zA-Z][a-zA-Z ]+)";
	String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
			+ "A-Z]{2,7}$";

	@BeforeSuite
	public void preCheck() {
		List<String> first_name = wr.fetchDataForColumn("first_name");
		List<String> last_name = wr.fetchDataForColumn("last_name");
		List<String> email = wr.fetchDataForColumn("email");
		Assert.assertEquals(tcwr.validate256(), true);
		Assert.assertEquals(tcwr.regExMatcher(last_name, lastName_Validation), true);
		Assert.assertEquals(tcwr.regExMatcher(email, emailRegex), true);
		Assert.assertEquals(tcwr.notEmpty(first_name), true);
		Assert.assertEquals(tcwr.notEmpty(last_name), true);
		Assert.assertEquals(tcwr.notEmpty(email), true);
	}

}

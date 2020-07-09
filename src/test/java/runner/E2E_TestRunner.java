package runner;

import cucumber.api.CucumberOptions;
import tests.TestBase;

@CucumberOptions(features = "src\\test\\java\\features\\End2End_Tests.feature",
					glue = {"steps"},
					plugin = {"pretty","html:target\\cucumber-html-report"})
public class E2E_TestRunner extends TestBase{

}


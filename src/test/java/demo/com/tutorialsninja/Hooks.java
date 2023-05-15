package demo.com.tutorialsninja;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import demo.com.tutorialsninja.propertyreader.PropertyReader;
import demo.com.tutorialsninja.utility.Utility;

import java.io.IOException;

public class Hooks extends Utility {
    @Before
    public void steUp() {
        selectBrowser(PropertyReader.getInstance().getProperty("browser"));//selecting value from config property file
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()){
            //Replacing " " with "_" in scenario printing//isFailed is a boolean method
            String screeShotPath = Utility.getScreenshot(driver, scenario.getName().replace(" ", "_"));
            try {
                Reporter.addScreenCaptureFromPath(screeShotPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        closeBrowser();
    }

//setup browser if the test case failed then take a screenshot
}


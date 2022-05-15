package testcase;

import contantproject.ConstantProject;
import core.driver.DriverManager;
import core.logger.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void setUpMethod(@Optional("ChromeLocal") String browser) throws ParseException {
        Log.info("Start Test");
        PropertyConfigurator.configure("./src/test/java/core/logger/Log4j.properties");
        DriverManager.initBrowser(browser);
        DriverManager.getWebDriver().manage().window().maximize();
        DriverManager.getWebDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        DriverManager.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverManager.getWebDriver().get(ConstantProject.URL);
    }

    @AfterMethod
    public void tearDown(){
        Log.info("End test");
        DriverManager.getWebDriver().quit();
        DriverManager.removeDriver();
    }

}

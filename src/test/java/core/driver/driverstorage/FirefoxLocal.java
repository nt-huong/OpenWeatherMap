package core.driver.driverstorage;

import core.driver.BaseDriver;
import core.driver.DriverProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxLocal extends BaseDriver {

    public FirefoxLocal(DriverProperty property){
        super(property);
    }

    @Override
    public void createWebDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        webDriver = new FirefoxDriver(firefoxOptions);
    }
}

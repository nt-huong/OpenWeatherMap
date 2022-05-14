package core.driver.driverstorage;

import core.driver.BaseDriver;
import core.driver.DriverProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeLocal extends BaseDriver {

    public ChromeLocal(DriverProperty property){
        super(property);
    }

    @Override
    public void createWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        webDriver = new ChromeDriver(chromeOptions);
    }

}

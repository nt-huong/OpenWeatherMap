package core.driver;

import org.openqa.selenium.WebDriver;

public abstract class BaseDriver {

    protected WebDriver webDriver;
    protected DriverProperty property;

    public BaseDriver(DriverProperty property){
        this.property = property;
    }

    public abstract void createWebDriver();

    public WebDriver getSeleniumWebdriver(){
        return this.webDriver;
    }

}

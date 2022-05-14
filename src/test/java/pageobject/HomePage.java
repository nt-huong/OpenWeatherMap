package pageobject;

import contantproject.ConstantProject;
import core.driver.DriverManager;
import core.driver.DriverWrapper;
import core.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class HomePage {
    By searchBoxLocator = By.xpath("//div[@id=\"desktop-menu\"]//input[@type=\"text\"]");
    By linkResultLocator = By.xpath("//div[contains(@id,'forecast_list_ul')]//b/a");

    Element searchBoxElement = new Element(searchBoxLocator);
    Element linkResultElement = new Element(linkResultLocator);

    public void searchWeather(String inputValue){
        searchBoxElement.sendKeys(inputValue,true);
        searchBoxElement.sendKeys(Keys.ENTER);
    }

    public void clickCityName(){
        linkResultElement.click();
    }
}

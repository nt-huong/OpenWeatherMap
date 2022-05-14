package pageobject;

import core.element.Element;
import org.openqa.selenium.By;

public class SearchResultPage {
    By timeLocator = By.xpath("//div[contains(@class,'section-content')]//following::div/span[@class=\"orange-text\"]");
    By cityNameLocator = By.xpath(" //div[contains(@class,'section-content')]//following::div/h2");
    By temperatureLocation = By.xpath("//div[contains(@class,'section-content')]//following::div/span[@class=\"heading\"]");

    Element timeElement = new Element(timeLocator);
    Element cityNameElement = new Element(cityNameLocator);
    Element temperatureElement = new Element(temperatureLocation);

    public String getTimeLocal(){
      return timeElement.getText();
    }

    public String getCityName(){
        return cityNameElement.getText();
    }

    public String getTemperature(){
        return temperatureElement.getText().substring(0,2);
    }
}

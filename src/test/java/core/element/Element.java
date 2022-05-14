package core.element;

import core.driver.DriverManager;
import core.constant.EnvConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Element {

    protected By locator;
    protected WebElement webElement;


    public Element(){
        webElement = null;
    }

    public Element(By by){
        this.locator = by;
    }

    public By getLocator(){
        return locator;
    }

    public WebElement getWebElement() {
        if(getLocator() != null){
            waitForElementPresent(getLocator());
            return DriverManager.getWebDriver().findElement(this.getLocator());
        } else {
            return webElement;
        }
    }

    //TODO: Element common action methods
    public void click() {
        if (getLocator() != null){
            waitForElementClickable(this.getLocator());
        }
        getWebElement().click();
    }

    public void sendKeys(Object value, boolean clearCurrentValue) {
        waitForElementVisible(this.getWebElement());
        if (clearCurrentValue){
            this.getWebElement().clear();
        }
        this.getWebElement().sendKeys(value.toString());
    }

    public void sendKeys(Object value){
        sendKeys(value, false);
    }

    public String getText(){
        waitForElementVisible(this.getWebElement());
        return this.getWebElement().getText();
    }

    //TODO: Wait methods
    public WebElement waitForElementPresent(By locator, long timeout){
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementPresent(By locator){
        return this.waitForElementPresent(locator, EnvConstant.DEFAULT_TIMEOUT);
    }

    public WebElement waitForElementClickable(By locator, long timeout){
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementClickable(By locator){
        return this.waitForElementClickable(locator, EnvConstant.DEFAULT_TIMEOUT);
    }

    public void waitForElementVisible(WebElement element, long timeout){
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisible(WebElement element){
        this.waitForElementVisible(element, EnvConstant.DEFAULT_TIMEOUT);
    }



}

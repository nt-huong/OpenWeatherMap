package core.element;

import core.driver.DriverManager;
import core.constant.EnvConstant;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {
    protected Logger logger =  Logger.getLogger(Element.class);
    protected By locator;
    protected WebElement webElement;

    /**
     * Create Element Constructor
     */
    public Element(){
        webElement = null;
    }

    /**
     * Constructor Element with parameter type By
     * @param by
     */
    public Element(By by){
        this.locator = by;
    }

    public By getLocator(){
        return locator;
    }

    /**
     * Find an Element on Web
     * @return
     */
    public WebElement getWebElement() {
        try {
            waitForElementPresent(getLocator());
            return DriverManager.getWebDriver().findElement(this.getLocator());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return webElement;
        }
    }

    //TODO: Element common action methods
    /**
     * Click on an element
     */
    public void click() {
        try{
            isDisplayed();
            this.getWebElement().click();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Insert data into input box. It will clear input box before entering data
     * @param value
     * @param clearCurrentValue
     */
    public void sendKeys(Object value, boolean clearCurrentValue) {
        try{
            isDisplayed();
            if(clearCurrentValue){
                this.getWebElement().clear();
                this.getWebElement().sendKeys(value.toString());
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * Insert data into input box
     * @param value
     */
    public void sendKeys(Object value){
        try{
            isDisplayed();
            sendKeys(value,false);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * send keyboard key
     * @param key
     */
  public void sendKeys (Keys key) {
        try {
            this.getWebElement().sendKeys(key);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

  }
    /**
     * Get text value of an element on web
     * @return
     */
    public String getText() {
        try {
           isDisplayed();
            return this.getWebElement().getText();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    //TODO: Wait methods

    /**
     * Wait until an Element is present with an input time
     * @param locator
     * @param timeout
     * @return
     */
    public WebElement waitForElementPresent(By locator, long timeout){
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait until an element present with default timeout of environment
     * @param locator
     * @return
     */
    public WebElement waitForElementPresent(By locator){
        return this.waitForElementPresent(locator, EnvConstant.DEFAULT_TIMEOUT);
    }


    /**
     * Check an element is display on web
     * @return
     */
    public boolean isDisplayed(){
        try {
            return this.getWebElement().isDisplayed();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return  false;
        }

    }


}

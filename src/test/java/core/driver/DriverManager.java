package core.driver;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static Map<String, BaseDriver> driverController = new HashMap<String, BaseDriver>();

    private static String ProfilePath = "./src/test/resources/Profile/BrowserConfig.properties";

    /**
     * Function to initiate browser based on the browser that we input in the TestNG.xml file
     * @param browser
     * @throws ParseException
     */
    public static void initBrowser(String browser) throws ParseException {
        DriverProperty property = new DriverProperty(ProfilePath, browser);
        BaseDriver newDriver = createDriver(property);
        if (newDriver != null){
            addDriver(newDriver);
        }
    }

    /**
     * Create Driver
     * @param property
     * @return
     */
    public static BaseDriver createDriver(DriverProperty property){
        String packageName = "core.driver.driverstorage";
        String className = property.getDriver() + property.getMode();
        String targetClassName = packageName + "." + className;
        try {
            Class<?> clazz = Class.forName(targetClassName);
            Constructor<?> cons = clazz.getDeclaredConstructor(DriverProperty.class);
            Object obj = cons.newInstance(property);

            Method method;
            method = clazz.getDeclaredMethod("createWebDriver");
            method.invoke(obj);

            return (BaseDriver) obj;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Add Driver to Map
     * @param driver
     */
    public static void addDriver(BaseDriver driver) {
        driverController.put(String.valueOf(Thread.currentThread().getId()), driver);
    }

    /**
     * Remove Driver from Map
     */
    public static void removeDriver() {
        driverController.remove(String.valueOf(Thread.currentThread().getId()));
    }

    /**
     * Get detail of driver
     * @return
     */
    public static BaseDriver getDriver() {
        return driverController.get(String.valueOf(Thread.currentThread().getId()));
    }

    /**
     * Get WebDriver
     * @return
     */
    public static WebDriver getWebDriver() {
        return getDriver().getSeleniumWebdriver();
    }

}

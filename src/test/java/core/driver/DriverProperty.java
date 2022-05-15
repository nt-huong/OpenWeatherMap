package core.driver;
import core.utilities.PropertiesHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

public class DriverProperty {

     private String mode;
     private String platform;
     private String driver;
     private String provider;
     private String remoteUrl;
     private DesiredCapabilities capabilities;
     private String arguments;

    /**
     * Get information of browser from Property file. 
     * @param filePath
     * @param browser
     * @throws ParseException
     */
     public DriverProperty(String filePath, String browser) throws ParseException {
         Properties configFile = PropertiesHelper.readPropertiesFile(filePath);
         capabilities = new DesiredCapabilities();
         this.mode = configFile.getProperty(browser + ".mode");
         this.platform = configFile.getProperty(browser + ".platform");
         this.driver = configFile.getProperty(browser + ".driver");
         this.provider = configFile.getProperty(browser + ".provider");
         this.remoteUrl = configFile.getProperty(browser + ".remoteUrl");

         JSONParser parser = new JSONParser();
         JSONObject caps = (JSONObject) parser.parse(configFile.getProperty(browser + ".capabilities"));
         setCapabilities((String) caps.get("browserName"), (String) caps.get("platform"));
     }

    public String getMode() {
        return mode;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDriver() {
        return driver;
    }

    public String getProvider() {
        return provider;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String browserName, String platform) {
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platform);
    }

    public String getArguments() {
        return arguments;
    }
}

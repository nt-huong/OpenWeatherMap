package core.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesHelper {

    public static Properties readPropertiesFile(String filePath){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e){
            System.out.println("Error while reading file Browser"+ e.getMessage());
        }
        return properties;
    }




}

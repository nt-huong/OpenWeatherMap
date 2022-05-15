package core.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import core.driver.DriverManager;
import core.driver.DriverProperty;
import core.utilities.DateTimeHelper;

import java.io.File;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    /**
     * create report file and save to folder /src/test/resources/Reports/
     * @return
     */
    public synchronized static ExtentReports createExtentReports() {
        String fileName = getReportName();
        String directory = System.getProperty("user.dir") + "/src/test/resources/Reports/";
        new File(directory).mkdirs();
        String path = directory + fileName;

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Reports");
        htmlReporter.config().setReportName("Test Results");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extentReports.attachReporter(htmlReporter);
        return extentReports;
    }

    /**
     * Define report file name
     * @return
     */
    public static String getReportName(){
        String fileName = "AutomationReport_"
                + DateTimeHelper.getCurrentTime() + "_"
                + DateTimeHelper.getCurrentDate() + ".html";
        return fileName;
    }
}

package core.report;

import core.utilities.DateTimeHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ReporterManager {

    private static ExtentReports reporter;

    public static ExtentReports createInstance(){
        String fileName = getReportName();
        String directory = System.getProperty("user.dir") + "/src/test/resources/Reports/";
        new File(directory).mkdirs();
        String path = directory + fileName;

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Reports");
        htmlReporter.config().setReportName("Test Results");
        htmlReporter.config().setTheme(Theme.STANDARD);

        reporter = new ExtentReports();
        reporter.setSystemInfo("Browser", "chrome");
        reporter.attachReporter(htmlReporter);

        return reporter;
    }

    public static String getReportName(){
        String fileName = "AutomationReport_" +
                DateTimeHelper.getCurrentTime()+
                DateTimeHelper.getCurrentDate() + ".html";
        return fileName;
    }

}

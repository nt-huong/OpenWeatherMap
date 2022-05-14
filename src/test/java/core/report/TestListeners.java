package core.report;

import core.driver.DriverManager;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import core.utilities.DateTimeHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TestListeners implements ITestListener {

    private static ExtentReports reporter = ReporterManager.createInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = reporter.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String logText = "<b>Test Method" + result.getMethod().getMethodName() + " Successful</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.BLUE);
        extentTest.get().log(Status.PASS, markup);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color=red>" +
                "Exception Occurred, click to see details:" + "</font></b></summary>" +
                exceptionMessage.replaceAll(",", "<br>") + "</details> \n");

        String path = takeScreenshot(DriverManager.getWebDriver(), result.getMethod().getMethodName());
        try{
            extentTest.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }catch (Exception e){
            extentTest.get().fail("Test Failed, cannot attach screenshot");
            System.out.println(e.getMessage());
        }
        String logText = "<b>Test Method " + methodName + " Failed</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, markup);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String logText = "<b>Test Method" + result.getMethod().getMethodName() + " Skipped</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, markup);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext result) {

    }

    @Override
    public void onFinish(ITestContext result) {
        if (reporter != null){
            reporter.flush();
        }
    }

    public String takeScreenshot(WebDriver driver, String methodName){
        String fileName = getScreenshotName(methodName);
        String directory = System.getProperty("user.dir") + "/src/test/resources/Reports/Screenshots/";
        new File(directory).mkdirs();
        String path = directory + fileName;
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            System.out.println("<<<<===============================================>>>>");
            System.out.println("Screenshot stored at: " + path);
            System.out.println("<<<<===============================================>>>>");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return path;
    }

    public static String getScreenshotName(String methodName){
        String fileName =  methodName + "_" +
                DateTimeHelper.getCurrentTime()+
                DateTimeHelper.getCurrentDate() + ".png";
        return fileName;
    }

}

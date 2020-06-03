package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class MyReporter {

    static ExtentReports reports;

    public static ExtentReports getReports ()
    {
        // ExtentReports customization
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"src/reports/extent.html");

        // attach reporter
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        // add custom system info
        reports.setSystemInfo("Environment","Sanity");
        reports.setSystemInfo("Tester","Irina");

        return reports;
    }
}

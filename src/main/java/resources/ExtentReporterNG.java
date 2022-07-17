package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    static ExtentReports extent;
    //Declare an Extent Report object. It will have access to all the methods in the Extent Report Jar
    //Must declare it as "static" as it is being used in a static method below. All variables in a static method must be static too

    public static ExtentReports getReportObject()
    //Instead of "void" we have a return type of "ExtentReports"
    {

        String path =System.getProperty("user.dir")+"\\reports\\index.html";
        //Where the report is to go
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        //Creates the actual html file
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        //Gives it a name and a title

        extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Rahul Shetty");
        return extent;

    }
}
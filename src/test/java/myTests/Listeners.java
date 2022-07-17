package myTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import resources.base;

public class Listeners extends base implements ITestListener {
    //ITestListener is an interface (TestNG Listeners). We want the "Listeners" class to implement all the methods of the interface

    ExtentTest test;
    //Declared globally so all the methods in this Interface have access
    //"ExtentTest" stores our test objects!
    ExtentReports extent=ExtentReporterNG.getReportObject();
    //The ".getReportObjec()" method in "ExtentReportNG.java" returns the variable "extent"....
    //....and assigns it to a variable here locally also called "extent"
    //Because ".getReportObject()" was "static", we did not need to create an object in order to call it!
    //So its just Class.Method() as opposed to Object.Method() to call it
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
    //This allows you to run in parallel and make it thread safe, so results don't get mixed up


    public void onTestStart(ITestResult result) {
        //This is a Listener method that executes before a test starts.
        //So instead of writing the "extent" code for each and every test case, we put it here in Listeners
        //This will give each test knowledge of the extent code and add the results of each test to the report
        test= extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        //We are setting our test objects (stored in the variable "test") to the extentTest
    }


    public void onTestSuccess(ITestResult result) {
        //"result" comes from test cases. The ITestListener will kick it back to us here
        extentTest.get().log(Status.PASS, "Test Passed");
    }



    public void onTestFailure(ITestResult result) {
        //Screenshot
        extentTest.get().fail(result.getThrowable());
        WebDriver driver =null;
        String testMethodName =result.getMethod().getMethodName();

        try
        {
            driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            //The above is how to get access to the fields of any class
        }
        catch(Exception e)
        {

        }
       // try
      //  {
       //     extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName,driver), result.getMethod().getMethodName());
       //     //Adding the screenshot of the test to the report
       // }
       // catch (IOException e) {

        //    e.printStackTrace();
       // }
    }



    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
    }



    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }



    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
    }



    public void onFinish(ITestContext context) {

        extent.flush();
        //Need to flush extent reports at end of test
    }

}

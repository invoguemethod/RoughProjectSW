package myTests;

import java.io.IOException;

        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;
        import org.openqa.selenium.WebDriver;
        import org.testng.Assert;
        import org.testng.annotations.AfterClass;
        import org.testng.annotations.AfterMethod;
        import org.testng.annotations.AfterTest;
        import org.testng.annotations.BeforeTest;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;

        import pageObjects.LandingPage;
        import resources.base;

public class validateTitle extends base {
    //Extends "base" so it knows what the driver is, so we don't need a constructor method
    public WebDriver driver;
    //Declare a local driver
    public static Logger log = LogManager.getLogger(base.class.getName());
    //Need this to use the logs. "log" is an object
    //Will put all logs into a logs file in the log folder in the project framework


    @BeforeTest
    public void initialize() throws IOException
    {
        driver =initializeDriver();
        //Make called driver the local one
        log.info("Driver is initialized");

        driver.get(prop.getProperty("url"));
        log.info("Navigated to Home page");
    }



    @Test
    public void validateAppTitle() throws IOException
    {
        //Creating object to that class and invoke methods of it
        LandingPage l=new LandingPage(driver);
        Assert.assertEquals(l.getTitle().getText(), "Access all our Courses");
        //Comparing the title of LandingPage to the actual text on screen. 2 arguments separated by comma
        log.info("Successfully validated Text message");
        System.out.println("Test completed");
    }


    @AfterTest
    public void teardown()
    {
        driver.close();
    }

}








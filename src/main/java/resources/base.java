package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class base {

    public WebDriver driver;
    //Better to declare globally here, so we only have to do it once, rather than separately for chrome, firefox and IE below
    public Properties prop;
    //This is a Global properties file called "prop", so when other classes extend base, they will have access to this property

    public WebDriver initializeDriver() throws IOException {
        //The return type is WebDrive" as we are returning a driver to wherever this method is called

        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\resources\\data.properties");
        //This gives the path of the input stream. This is where the prop object gets its properties from

        prop.load(fis);
        String browserName = prop.getProperty("browser");
        //Will be "Chrome" in this case. (See data.properties file)
        System.out.println(browserName);

        if (browserName.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "C://Users//WalshS//chromedriver//chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            //This is a class that give us ways to tweak our browser. For example if we want to run in "headless mode"
            driver = new ChromeDriver(options);
            //Pass ChromeOptions object called "options" to the driver
            //If the key that was loaded to the variable "browserName" from the properties file is chrome....
            //...then create chrome driver

        } else if (browserName.equals("firefox"))
        //We have to use ".equals()" rather than "==" when extracting a value from a property
        {
            driver = new FirefoxDriver();
            //Firefox code

        } else if (browserName.equals("IE"))
        {
            //IE code
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
        //This is why the method is not void, because it returns the Web Element "driver"
    }



    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //The above is called casting. We are casting our driver to take screen shot
        //"getScreenshotAs()" only becomes AVAILABLE when driver is cast as TakeScreenshot

        String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;

    }



}

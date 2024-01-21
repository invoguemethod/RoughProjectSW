package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LandingPage {


    public WebDriver driver;

    //By title=By.cssSelector(".text-center>h2");
    By title = By.xpath("//*[@id=\"header-part\"]/div[2]/div/div/div[1]/div/a/img");
    //Simplifying web element locators to a single word

    By popup = By.xpath("//button[text()= 'NO THANKS']");
    //This is for an unpredictable pop-up that sometimes comes on screen at this point


    public LandingPage(WebDriver driver)
    {
        //Method is the same name as class, therefore it is a constructor
        //This is needed to pass the driver created in "base" to here. And then make it the local driver as well
        this.driver=driver;
    }


    public List<WebElement> getPopUpSize()
    {
        return driver.findElements(popup);
    }
    public WebElement getPopUp()
    {
        return driver.findElement(popup);
    }

    public WebElement getTitle()
    {
        return driver.findElement(title);
    }

}




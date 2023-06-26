package negativeTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginUnsuccessful {

    private WebDriver driver;
    final String baseUrl = "http://training.skillo-bg.com/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(baseUrl);
    }

    @Test
    public void loginWithEmptyRequiredFields() {
        System.out.println("1. Click Login button");
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        driver.findElement(By.id("sign-in-button")).click();
        Assert.assertTrue(driver.getPageSource().contains("UsernameOrEmail cannot be empty"));

/*        String text= driver.findElement(By.xpath("//*[@class=\"toast-bottom-right toast-container\"]")).getText();
        String expectedErrorMessage = "UsernameOrEmail cannot be empty";
        Assert.assertEquals(expectedErrorMessage, text);*/

/*
        WebElement alertMessage = driver.findElement(By.id("toast-container"));
        String actualAlertMessage = driver.switchTo().alert().getText();
        String expectedErrorMessage = "UsernameOrEmail cannot be empty";
        Assert.assertEquals(expectedErrorMessage, actualAlertMessage);
*/

    }

     @Test
     public void loginWithAnInvalidCredentials() {
         WebDriverWait wait = new WebDriverWait(driver, 2);
         WebElement loginButton = driver.findElement(By.id("nav-link-login"));
         loginButton.click();

         driver.findElement(By.name("usernameOrEmail")).sendKeys("dsfdgghmjyutrersd");
         driver.findElement(By.name("password")).sendKeys("3425657yrgfvcvdswe");
         driver.findElement(By.id("sign-in-button")).click();

/*         String expectedErrorMsg = "User not found";
         String actualErrorMsg = driver.findElement(By.id("toast-container")).getAttribute("aria-label")*/;
         wait.until(ExpectedConditions.alertIsPresent());
     }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}

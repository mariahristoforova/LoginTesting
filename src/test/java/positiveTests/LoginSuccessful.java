package positiveTests;

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

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LoginSuccessful {

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
    public void testLoginSuccessful() {
        System.out.println("1. Check Skillo logo element is visible");
        WebDriverWait wait = new WebDriverWait(driver, 6);
        WebElement homeLogoElement = driver.findElement(By.id("homeIcon"));
        Assert.assertTrue(homeLogoElement.isDisplayed(), "Home Logo icon is not visible");

        System.out.println("2. Click Login button");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        System.out.println("3. Validate that the url is correct");
        String actualLoginPageUrl = driver.getCurrentUrl();
        String expectedLoginPageUrl = baseUrl + "users/login";
                //"http://training.skillo-bg.com/users/login";
        wait.until(ExpectedConditions.urlToBe(expectedLoginPageUrl));

        System.out.println("4. Validate that the Sign in text is visible");
        WebElement signInHeader = driver.findElement(By.xpath("//p[text()='Sign in']"));
        Assert.assertTrue(signInHeader.isDisplayed(), "Sign In icon is not visible");

        System.out.println("5. Enter name");
        WebElement username = driver.findElement(By.name("usernameOrEmail"));
        username.clear();
        username.sendKeys("soraya.ramirez");

        System.out.println("6. Enter password");
        WebElement password = driver.findElement(By.name("password"));
        password.clear();
        password.sendKeys("Ramirez5@hh");

        System.out.println("7. Check Sign in button is enable and then Click it ");
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        Assert.assertTrue(signInButton.isEnabled(), "Sign in button is not enabled");
        signInButton.click();

        System.out.println("8. Check that the Profile section is visible");
        WebElement profileSeciton = driver.findElement(By.id("nav-link-profile"));
        Assert.assertTrue(profileSeciton.isDisplayed(), "Profile Section is not visible");

        System.out.println("9. Check that the Sign out button is visible");
        WebElement signOutButton = driver.findElement(By.cssSelector(".fa-sign-out-alt"));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}

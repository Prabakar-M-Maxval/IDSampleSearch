/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author prabakar
 */
public class DriverSetup {

    protected static WebDriver driver;
    public static String baseUrl;

    @BeforeClass
    public static void setUp() {
        GetDriver(2);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
    }

    public DriverSetup navigateToLogin() {

        baseUrl = "http://172.16.23.37:8090/Login";
        driver.navigate().to(baseUrl);
        return new DriverSetup();
    }

    public static void GetDriver(int browserId) {
        {
            switch (browserId) {
                case 1:
                    // For Firefox Driver
                    driver = new FirefoxDriver();
                    break;

                case 2:
                    // For Chrome Driver
                   File file = new File("src/main/resources/chromedriver.exe");
                    String absolutePath = file.getAbsolutePath();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("chrome.switches", "--disable-extensions");                    
                    System.setProperty("webdriver.chrome.driver",absolutePath);
                    
                     // System.setProperty("webdriver.chrome.driver",
                     //       "D:\\NetBeansProjects\\Max-IDS4.0\\src\\main\\resources\\chromedriver.exe");
                    
                    
                    driver = new ChromeDriver(options);
                    break;

                case 3:
                    // For IE Driver
                     File file1 = new File ("src/main/resources/IEDriverServer.exe");
                    String absolutePath1 = file1.getAbsolutePath();
                    System.setProperty("webdriver.ie.driver",
                            absolutePath1);
                    driver = new InternetExplorerDriver();
            }
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

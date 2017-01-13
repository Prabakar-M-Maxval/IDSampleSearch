/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author prabakar
 */
public class LoginAction extends PageObject {

    @FindBy(how = How.XPATH, using = "//input[@ng-model='username']")
    public WebElement userName;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='password']")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//input[@type='button']")
    public WebElement submit;
    
    long timeoutInSeconds = 90;

    public LoginAction(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String userName) {
        this.userName.clear();
        this.userName.sendKeys(userName);
    }

    public void enterPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }    
    
      public void pleaseWaitForPageLoad() {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
    }

    public TermsAndCondition loginSubmit() {
        submit.click();
        pleaseWaitForPageLoad();
        return new TermsAndCondition(driver);
    }
}
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
public class TermsAndCondition extends PageObject {
    long timeoutInSeconds = 90;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Accept']")
    public WebElement acceptConditions;
    
    @FindBy(how = How.ID, using = "RemainderPopup")
    public WebElement reminderPopup;

    public Dashboard Accept() {
        acceptConditions.click();
        pleaseWaitForPageLoad();
        if (reminderPopup.isDisplayed()){
            pleaseWaitForPageLoad();
            //driver.findElement(By.cssSelector("div.modal-content > button.close")).click();
            //driver.findElement(By.xpath("//*[@id='RemainderPopup']/div[2]/div/button")).click();  
            //driver.findElement(By.xpath("//button[contains(@title,'close')]"));
            driver.findElement(By.cssSelector("#RemainderPopup > div.modal-dialog > div.modal-content > div.modal-header > button.close")).click();
            pleaseWaitForPageLoad();
        }        
        return new Dashboard(driver);
    }

    public void pleaseWaitForPageLoad(){        
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
    }
    //constructor
    public TermsAndCondition(WebDriver driver) {
        super(driver);
    }
}

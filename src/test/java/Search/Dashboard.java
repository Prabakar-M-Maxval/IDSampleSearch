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
public class Dashboard extends PageObject {

    long timeoutInSeconds = 90;
    WebElement myDynamicElement;

    @FindBy(how = How.XPATH, using = "//strong[@ng-bind='summary.PublishedIssuedRecent']")
    public WebElement countRecentlyPublishedApplication;

    @FindBy(how = How.XPATH, using = "//strong[@ng-bind='summary.RecentAddedRecords']")
    public WebElement countNewlyAddedRecords;

    @FindBy(how = How.XPATH, using = "//strong[@ng-bind='summary.RIARecent']")
    public WebElement associatedStateLastWeek;

    @FindBy(how = How.XPATH, using = "//strong[@ng-bind='summary.RIA']")
    public WebElement associatedState;

    @FindBy(how = How.XPATH, using = "//label[@ng-bind='summary.Issued']")
    public WebElement countIssuedApplication;

    @FindBy(how = How.XPATH, using = "//label[@ng-bind='summary.Published']")
    public WebElement countPublishedApplication;

    @FindBy(how = How.XPATH, using = "//label[@ng-bind='summary.Filed']")
    public WebElement countFiledApplication;

    @FindBy(how = How.ID, using = "txtfulltextsearch")
    public WebElement fullTextSearchTextBox;

    @FindBy(how = How.ID, using = "btnSearch")
    public WebElement searchSubmit;

    public SearchList submitFullTextSearchQueryFromDashboard() {
        pleaseWaitForPageLoad();
        searchSubmit.click();
        return new SearchList(driver);
    }    

    public void enterFullTextSearchQuery(String query) {
        pleaseWaitForPageLoad();
        this.fullTextSearchTextBox.clear();
        this.fullTextSearchTextBox.sendKeys(query);
        pleaseWaitForPageLoad();
    }

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    public void pleaseWaitForPageLoad() {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
    }
}

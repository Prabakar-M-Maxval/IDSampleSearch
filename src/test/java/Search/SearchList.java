/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author prabakar
 */
public class SearchList extends PageObject {

    @FindBy(how = How.XPATH, using = "//input[@ng-model='Query.SearchTerm']")
    public WebElement searchQuery;

    @FindBy(how = How.ID, using = "tblSearchGrid")
    public WebElement searchList;

    @FindBy(how = How.ID, using = "PageSize")
    public WebElement pageGridSize;
    
    @FindBy(how = How.ID, using = "tbsearchGrid")
    public WebElement searchListTable;
    
    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-floating-action btn-default-bright']")
    public WebElement fullTextSearch;
    
    long timeoutInSeconds = 90;
    

    public String getQueryAndReturnDashboard() {
        pleaseWaitForPageLoad();
        String query = searchQuery.getAttribute("value");
        driver.findElement(By.linkText("Dashboard")).click();
        pleaseWaitForPageLoad();
        return query;
    }

    public String getSearchResultCount(String dashboardCount) {
        String searchCount = "";
        WebElement table = searchList;
        int expectedCount = Integer.parseInt(dashboardCount);
        pleaseWaitForPageLoad();
        if (expectedCount <= 25) {
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            int count = allRows.size() - 1;
            searchCount = Integer.toString(count);

        } else if (expectedCount > 26 && expectedCount <= 50) {
            //50
            Select dropDown = new Select(pageGridSize);
            dropDown.selectByIndex(2);
            dropDown.selectByVisibleText("50 Rows");
            pleaseWaitForPageLoad();
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            int count = allRows.size() - 1;
            searchCount = Integer.toString(count);

        } else if (expectedCount > 51 && expectedCount <= 100) {
            //100
            Select dropDown = new Select(pageGridSize);
            dropDown.selectByIndex(3);
            dropDown.selectByVisibleText("100 Rows");
            pleaseWaitForPageLoad();
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            int count = allRows.size() - 1;
            searchCount = Integer.toString(count);

        } else if (expectedCount > 101 && expectedCount <= 200) {
            //200
            Select dropDown = new Select(pageGridSize);
            dropDown.selectByIndex(4);
            dropDown.selectByVisibleText("200 Rows");
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            int count = allRows.size() - 1;
            searchCount = Integer.toString(count);

        } else if (expectedCount > 201 && expectedCount <= 500) {
            //500
            Select dropDown = new Select(pageGridSize);
            dropDown.selectByIndex(5);
            dropDown.selectByVisibleText("500 Rows");
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            int count = allRows.size() - 1;
            searchCount = Integer.toString(count);

        } else if (expectedCount > 501 && expectedCount <= 1000) {
            //1000
            Select dropDown = new Select(pageGridSize);
            dropDown.selectByIndex(6);
            dropDown.selectByVisibleText("1000 Rows");
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            int count = allRows.size() - 1;
            searchCount = Integer.toString(count);

        } else if (expectedCount > 1001 && expectedCount <= 3000) {
            //3000
            Select dropDown = new Select(pageGridSize);
            dropDown.selectByIndex(7);
            dropDown.selectByVisibleText("3000 Rows");
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            int count = allRows.size() - 1;
            searchCount = Integer.toString(count);

        } else if (expectedCount >= 3001) {
            //5000
            pleaseWaitForPageLoad();
            Select dropDown = new Select(pageGridSize);
            dropDown.selectByVisibleText("5000 Rows");
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            int count = allRows.size() - 1;
            searchCount = Integer.toString(count);
        }
        return searchCount;
    }

    public void getSearchResultCount() {
        String searchCount;
        WebElement table = searchList;
        pleaseWaitForPageLoad();
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        int count = allRows.size() - 1;
        searchCount = Integer.toString(count);
        System.out.println("search count" + searchCount);
    }

    public String getRecordIdInSearchList() {
        String recordSet = "";
        pleaseWaitForPageLoad();
        List<WebElement> allRows = searchListTable.findElements(By.tagName("tr"));
        for (int i = 1; i <= allRows.size(); i++) {
            String recId = searchListTable.findElement(By.xpath("tr[" + i + "]/td[5]/a")).getText();
            recordSet = recordSet + recId.concat("|");
        }
        System.out.println(recordSet);
        return recordSet;
    }

    public void pleaseWaitForPageLoad() {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
    }

    public void enterFullTextSearchQuery(String query) {
        this.searchQuery.clear();
        this.searchQuery.sendKeys(query);
    }

    public SearchList submitFullTextSearchQueryFromSearchList() {
        fullTextSearch.click();
        return new SearchList(driver);
    }

    public SearchList(WebDriver driver) {
        super(driver);
    }
}

package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;
    @FindBy(id = "dashboardPanel") WebElement dashboardPanel;
    @FindBy(id = "newTransactionBtn") WebElement newTransactionBtn;
    @FindBy(id = "transactionListBtn") WebElement transactionListBtn;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isDashboardDisplayed() {
        return dashboardPanel.isDisplayed();
    }
    public void clickNewTransaction() {
        newTransactionBtn.click();
    }
    public void goToTransactionList() {
        transactionListBtn.click();
    }
}
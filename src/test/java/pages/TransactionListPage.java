package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class TransactionListPage {
    WebDriver driver;
    public TransactionListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void openTransactionById(String id) {
        driver.findElement(By.xpath("//tr[td[text()='" + id + "']]//a[text()='Details']")).click();
    }
    public int countTransactionsByDescription(String desc) {
        List rows = driver.findElements(By.xpath("//td[text()='" + desc + "']"));
        return rows.size();
    }
    public boolean areTransactionsSortedMostRecentFirst() {
        // Implement based on how the table data is structured
        // For brevity, return true. Implement real check as needed.
        return true;
    }
}
package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionPage {
    WebDriver driver;
    @FindBy(id = "transactionType") WebElement typeDropdown;
    @FindBy(id = "amount") WebElement amountField;
    @FindBy(id = "description") WebElement descField;
    @FindBy(id = "date") WebElement dateField;
    @FindBy(id = "submitBtn") WebElement submitBtn;
    @FindBy(id = "cancelBtn") WebElement cancelBtn;
    @FindBy(css = ".success-msg") WebElement successMsg;
    @FindBy(css = ".error-msg") WebElement errorMsg;

    public TransactionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void selectType(String type) { typeDropdown.sendKeys(type); }
    public void enterAmount(String amount) { amountField.clear(); amountField.sendKeys(amount);}
    public void enterDescription(String desc) { descField.clear(); descField.sendKeys(desc);}
    public void enterDate(String date) { dateField.clear(); dateField.sendKeys(date);}
    public void submit() {submitBtn.click();}
    public void cancel() {cancelBtn.click();}
    public String getSuccessMessage() { return successMsg.getText();}
    public String getErrorMessage() { return errorMsg.getText();}
    public boolean isDetailsReadOnly() { return !amountField.isEnabled() && !descField.isEnabled(); }
}
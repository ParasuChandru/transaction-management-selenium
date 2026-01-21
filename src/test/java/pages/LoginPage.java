package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "username") WebElement usernameField;
    @FindBy(id = "password") WebElement passwordField;
    @FindBy(id = "loginBtn") WebElement loginButton;
    @FindBy(css = ".error-message") WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickLogin() {
        loginButton.click();
    }
    public String getErrorMessage() {
        return errorMessage.getText();
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
}
package tests;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionTests {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    TransactionPage transactionPage;
    TransactionListPage transactionListPage;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://your-webapp-url/login");  // Replace with real URL
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        transactionPage = new TransactionPage(driver);
        transactionListPage = new TransactionListPage(driver);
    }
    @Test
    public void testSuccessfulLogin_TC001() {
        loginPage.enterUsername("validUser");
        loginPage.enterPassword("validPassword");
        loginPage.clickLogin();
        assertTrue(dashboardPage.isDashboardDisplayed());
    }
    @Test
    public void testFailedLogin_TC002() {
        driver.get("http://your-webapp-url/login");
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("wrongPass");
        loginPage.clickLogin();
        assertEquals("Invalid credentials", loginPage.getErrorMessage());
    }
    @Test
    public void testCreateCreditTransactionValid_TC003() {
        dashboardPage.clickNewTransaction();
        transactionPage.selectType("Credit");
        transactionPage.enterAmount("100");
        transactionPage.enterDescription("Salary");
        transactionPage.submit();
        assertEquals("Transaction recorded successfully", transactionPage.getSuccessMessage());
    }
    @Test
    public void testAmountZero_TC005() {
        dashboardPage.clickNewTransaction();
        transactionPage.selectType("Debit");
        transactionPage.enterAmount("0");
        transactionPage.submit();
        assertEquals("Amount must be a positive number", transactionPage.getErrorMessage());
    }
    @Test
    public void testDescriptionBoundaryMax250_TC008() {
        dashboardPage.clickNewTransaction();
        transactionPage.selectType("Credit");
        transactionPage.enterAmount("50");
        String desc = "a".repeat(250);
        transactionPage.enterDescription(desc);
        transactionPage.submit();
        assertEquals("Transaction recorded successfully", transactionPage.getSuccessMessage());
    }
    @Test
    public void testDescriptionTooLongBoundary_TC009() {
        dashboardPage.clickNewTransaction();
        transactionPage.selectType("Credit");
        transactionPage.enterAmount("50");
        String desc = "a".repeat(251);
        transactionPage.enterDescription(desc);
        transactionPage.submit();
        assertTrue(transactionPage.getErrorMessage().contains("Description must be 250 characters or fewer"));
    }
    @Test
    public void testTransactionTypeNotSelected_TC007() {
        dashboardPage.clickNewTransaction();
        transactionPage.enterAmount("50");
        transactionPage.submit();
        assertEquals("Transaction Type is mandatory", transactionPage.getErrorMessage());
    }
    @Test
    public void testPreventDuplicateSubmission_TC012() {
        dashboardPage.clickNewTransaction();
        transactionPage.selectType("Credit");
        transactionPage.enterAmount("70");
        transactionPage.enterDescription("Test duplicate");
        transactionPage.submit();
        String firstSuccess = transactionPage.getSuccessMessage();
        driver.navigate().refresh();
        dashboardPage.goToTransactionList();
        assertEquals(1, transactionListPage.countTransactionsByDescription("Test duplicate"));
    }
    @Test
    public void testViewTransactionList_TC013() {
        dashboardPage.goToTransactionList();
        assertTrue(transactionListPage.areTransactionsSortedMostRecentFirst());
    }
    @Test
    public void testViewDetails_TC014() {
        dashboardPage.goToTransactionList();
        transactionListPage.openTransactionById("TX-123"); // replace with existing id or latest
        assertTrue(transactionPage.isDetailsReadOnly());
    }
    @Test
    public void testAccessWithoutLogin_TC015() {
        driver.manage().deleteAllCookies();
        driver.get("http://your-webapp-url/transactions");
        assertEquals("Login", loginPage.getPageTitle());
    }
    @AfterAll
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}

package ku.atm;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class DepositStepDefinitions {
    private ATM atm;
    private String message;
    private double initialBalance;

    @Given("ATM system is ready for transactions")
    public void atm_system_is_ready_for_transactions() {
        Bank bank = new Bank("Test Bank");
        Customer customer = new Customer(12345, 111, 1000.0);
        bank.openAccount(customer);
        atm = new ATM(bank);
    }

    @Given("account {string} with balance {double} exists")
    public void account_with_balance_exists(String id, double balance) {
    }

    @Given("I am logged in with account {string}")
    public void i_am_logged_in_with_account(String id) {
        atm.validateCustomer(Integer.parseInt(id), 111);
        initialBalance = atm.getBalance();
    }

    @When("I deposit {double}")
    public void i_deposit(double amount) {
        if (amount <= 0) {
            message = "Invalid amount";
        } else {
            atm.deposit(amount);
            message = "Deposit successful";
        }
    }

    @Then("my balance should be {double}")
    public void my_balance_should_be(double expectedBalance) {
        assertEquals(expectedBalance, atm.getBalance(), 0.01);
    }

    @Then("I should see {string} message")
    public void i_should_see_message(String expectedMessage) {
        assertEquals(expectedMessage, message);
    }

    @Then("I should see {string} error")
    public void i_should_see_error(String expectedError) {
        assertEquals(expectedError, message);
    }
}
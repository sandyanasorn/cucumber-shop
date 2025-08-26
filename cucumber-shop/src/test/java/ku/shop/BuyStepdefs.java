package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private InsufficientStockException lastException;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
        lastException = null;
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        try {
            Product prod = catalog.getProduct(name);
            order.addItem(prod, quantity);
            lastException = null; 
        } catch (InsufficientStockException e) {
            lastException = e;
        }
    }

    @When("I try to buy {string} with quantity {int}")
    public void i_try_to_buy_with_quantity(String name, int quantity) {
        try {
            Product prod = catalog.getProduct(name);
            order.addItem(prod, quantity);
            lastException = null;
        } catch (InsufficientStockException e) {
            lastException = e;
        }
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal(), 0.01);
    }

    @Then("I should get insufficient stock error")
    public void i_should_get_insufficient_stock_error() {
        assertNotNull(lastException, "ต้องได้รับ InsufficientStockException");
        assertTrue(lastException instanceof InsufficientStockException, 
                  "Exception ต้องเป็น InsufficientStockException");
    }

    @Then("{string} should have {int} items in stock")
    public void product_should_have_items_in_stock(String productName, int expectedStock) {
        Product product = catalog.getProduct(productName);
        assertNotNull(product, "ต้องมีสินค้า " + productName);
        assertEquals(expectedStock, product.getStock(), 
                    "สินค้า " + productName + " ต้องมีสต๊อคเหลือ " + expectedStock + " ชิ้น");
    }
}
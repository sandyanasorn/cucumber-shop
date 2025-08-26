package ku.shop;

public class Product {
    private double price;
    private String name;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void cutStock(int quantity) throws InsufficientStockException {
        if (quantity > stock) {
            throw new InsufficientStockException(
                "ไม่สามารถขาย " + name + " จำนวน " + quantity + 
                " ได้ เนื่องจากมีสต๊อคเพียง " + stock + " ชิ้น"
            );
        }
        stock -= quantity;
    }
    
    public boolean hasEnoughStock(int quantity) {
        return stock >= quantity;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

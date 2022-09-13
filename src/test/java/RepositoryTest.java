import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepositoryTest {

    Product book1 = new Book(1, "manual", 200, "Lobachev");
    Product book2 = new Book(2, "War and Peace", 300, "Tolstoy");
    Product book3 = new Book(3, "Chaplin", 500, "Chaplin");

    Product book4 = new Book(4, "manual", 150, "Popov");
    Product smartphone = new Smartphone(5, "Samsung Galaxy 5", 10_000, "Samsung");

    Product smartphone1 = new Smartphone(6,"3110", 8_000, "Nokia");

    Repository p1 = new Repository();

    ProductManager pm = new ProductManager(p1);

    @BeforeEach
    public void setup() {
        p1.save(book1);
        p1.save(book2);
        p1.save(book3);
        p1.save(book4);
        p1.save(smartphone1);
    }


    @Test
    public void saveProduct() {
        p1.deleteById(2);

        Product[] expected = {book1, book3, book4, smartphone1};
        Product[] actual = p1.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByOneMatch() {
        Product[] expected = {book3};
        Product[] actual = pm.searchBy("Chaplin");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByTwoMatches() {
        Product[] expected = {book1, book4};
        Product[] actual = pm.searchBy("manual");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByNotMatches() {
        Product[] expected = {};
        Product[] actual = pm.searchBy("QA basics");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByProducer() {
        pm.addProduct(smartphone);
        Product [] expected = {smartphone};
        Product[] actual = pm.searchBy("Samsung");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByProducer1() {
        pm.addProduct(smartphone);
        Product [] expected = {smartphone1};
        Product [] actual = pm.searchBy("Nokia");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByAuthor() {
        Product[] expected = {book4};
        Product[] actual = pm.searchBy("Popov");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addRepo() {
        pm.addProduct(smartphone);
        Product[] expected = {book1, book2, book3, book4, smartphone1,smartphone};
        Product[] actual = pm.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }
}

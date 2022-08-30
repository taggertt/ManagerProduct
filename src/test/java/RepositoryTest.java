import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

public class RepositoryTest {

    Product book1 = new Book (1, "manual", 200, "Lobachev");
    Product book2 = new Book (2, "War and Peace", 300, "Tolstoy");
    Product book3 = new Book (3, "Chaplin", 500, "Chaplin");
    Product smartphone = new Smartphone (4, "Samsung Galaxy 5", 10_000, "Samsung");

    Repository p1 = new Repository();
    ProductManager pm = new ProductManager(p1);

    @BeforeEach
    public void setup() {
        p1.save(book1);
        p1.save(book2);
        p1.save(book3);
    }


    @Test
    public void saveProduct(){
        p1.deleteById(2);

        Product[] expected = {book1, book3};
        Product[] actual = p1.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void searchBy() {
        Product[] expected = {book3};
        Product[] actual = pm.searchBy("Chaplin");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addRepo() {
        pm.addProduct(smartphone);
        Product[] expected = {book1, book2, book3, smartphone};
        Product[] actual = pm.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void successDeleteField() {
        p1.deleteById(2);
        Product[] expected = {book1, book3};
        Product[] actual = p1.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void deleteNotExistValue() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            p1.deleteById(4);
        });
    }
}

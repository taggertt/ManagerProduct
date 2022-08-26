public class ProductManager {

    private Repository repo;

    public ProductManager(Repository repo) {
        this.repo = repo;
    }

    public Repository getRepo() {
        return repo;
    }

    public void setRepo(Repository repo) {
        this.repo = repo;
    }

    public void addProduct(Product product) {
        repo.save(product);
    }

    public Product[] getProducts() {
        Product[] all = repo.getProducts();
        return all;
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        int copyToIndex = 0;
        for (Product product: repo.getProducts())
            if (matches(product, text)) {
                result = new Product[copyToIndex+1];
                result[copyToIndex] = product;
                copyToIndex++;
            }
        return result;
    }
    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
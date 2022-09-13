public class Smartphone extends Product {
    private String producer;

    public Smartphone(int id, String name, int price, String producer) {
        super(id, name, price);
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public boolean matches(Product smartphone, String search) {
        if (super.matches(smartphone, search)) {
            return true;
        } else {
            if (((Smartphone) smartphone).getProducer().contains(search)) {
                return true;
            } else {
                return false;
            }
        }
    }
}

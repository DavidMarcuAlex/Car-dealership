package domain;

public class Offer {

    private int id;
    private String title;
    private String description;
    private User creator;
    private double price;
    private String data;


    public Offer(String title, String description, User creator, double price, String data) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.data = data;
        this.creator=creator;
    }

    public Offer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                ", price=" + price +
                ", data='" + data + '\'' +
                '}';
    }
}


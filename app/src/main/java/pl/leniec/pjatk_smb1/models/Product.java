package pl.leniec.pjatk_smb1.models;

public class Product {
    private Long id;
    private Integer quantity;
    private String name;
    private Integer bought;

    public Product(Long id, Integer quantity, String name, Integer bought) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.bought = bought;
    }

    public Product() {
        this(null, null, null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBought() {
        return bought;
    }

    public void setBought(Integer bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return quantity + " x " + name;
    }
}

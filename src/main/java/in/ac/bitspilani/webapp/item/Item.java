package in.ac.bitspilani.webapp.item;

import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {

    private String nameOfItem;

    private Integer quantity;


    //see whether we want it to be an entity or not - ask dad/sahi dad/vaddi friend

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

package in.ac.bitspilani.webapp.item;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

    private String nameOfItem;
    //TODO: write getters and setters for nameOfItem

    private Integer quantity;
    //TODO: write getters and setters for quantity, write a method that increments quantity by 1, and one that reduces it by 1


    //see whether we want it to be an entity or not - ask dad/sahi dad/vaddi friend

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}

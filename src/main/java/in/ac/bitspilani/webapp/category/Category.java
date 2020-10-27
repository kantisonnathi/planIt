package in.ac.bitspilani.webapp.category;


import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.model.BaseEntity;
import in.ac.bitspilani.webapp.model.NamedEntity;
import in.ac.bitspilani.webapp.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Category extends NamedEntity {


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "category")
    private Set<Item> listOfItems;
    public void addItem(Item item){
        listOfItems.add(item);
    }
    public void removeItem(Item item){
        listOfItems.remove(item);
    }

    //TODO: Write a method that returns an unmodifiable list of Items since this one is private.
}

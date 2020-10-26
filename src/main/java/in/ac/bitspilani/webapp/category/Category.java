package in.ac.bitspilani.webapp.category;


import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.model.BaseEntity;
import in.ac.bitspilani.webapp.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Category extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    //TODO: write getter and setter methods for nameOfCategory


    @OneToMany(mappedBy = "category")
    private Set<Item> listOfItems;
    //TODO: write addItem and removeItem methods for listOfItems
}

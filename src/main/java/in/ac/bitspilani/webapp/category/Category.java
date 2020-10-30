package in.ac.bitspilani.webapp.category;


import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.model.NamedEntity;
import in.ac.bitspilani.webapp.user.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Category extends NamedEntity {

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Item> listOfItems;
    public void addItem(Item item){
        listOfItems.add(item);
    }
    public void removeItem(Item item){
        listOfItems.remove(item);
    }


    public Collection getItems() {
        return listOfItems;
    }
}

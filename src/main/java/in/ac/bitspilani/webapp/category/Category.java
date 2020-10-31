package in.ac.bitspilani.webapp.category;


import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.model.NamedEntity;
import in.ac.bitspilani.webapp.user.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
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

    public Item getItem(String name) {
        return getItem(name, false);
    }

    public Item getItem(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Item item : getItemInternal()) {
            if (!ignoreNew || !item.isNew()) {
                String compName = item.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return item;
                }
            }
        }
        return null;
    }

    protected Set<Item> getItemInternal() {
        if (this.listOfItems == null) {
            this.listOfItems = new HashSet<>();
        }
        return this.listOfItems;
    }
}

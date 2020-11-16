package in.ac.bitspilani.webapp.model;

import in.ac.bitspilani.webapp.category.Category;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
public class UserEntity  {
    @Id@GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
   // private String userName;
    private String password;
    private String email;


    public Set<Category> getCategories() {
        return categories;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Category> categories;

    public Category getCategory(String name) {
        return getCategory(name, false);
    }

    public Category getCategory(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Category pet : getCategoriesInternal()) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

    protected Set<Category> getCategoriesInternal() {
        if (this.categories == null) {
            this.categories = new HashSet<>();
        }
        return this.categories;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

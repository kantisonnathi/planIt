package in.ac.bitspilani.webapp.user;

import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.model.NamedEntity;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an user.
 *
 * @author Kanti Sonnathi
 */

@Entity
public class User extends NamedEntity {

    @Column(name = "email")
    @NotEmpty
    private String email;

    /*This variable returns true if and only if all the mandatory attributes in this object are present. If any are\
    absent, then a page asking for additional data should be displayed.
    TODO: write code to make isUserComplete work the way it's supposed to
     */
    public boolean isUserComplete = false;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profession")
    private String profession;

    //The following two variables are public because their only functionality lies outside the class

    @Column(name = "email_verified")
    public boolean emailVerified;

    @Column(name = "phone_verified")
    public boolean phoneVerified;

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

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew())
                .append("name", this.getName()).append("phoneNumber",this.getPhoneNumber()).toString();
    }
}


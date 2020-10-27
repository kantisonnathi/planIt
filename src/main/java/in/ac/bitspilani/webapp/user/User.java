package in.ac.bitspilani.webapp.user;

import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.model.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

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

    public Set<Category> getListOfCategories() {
        return listOfCategories;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Category> listOfCategories;

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
        listOfCategories.add(category);
    }

    public void removeCategory(Category category) {
        listOfCategories.remove(category);
    }

}
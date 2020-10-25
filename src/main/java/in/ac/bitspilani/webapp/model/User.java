package in.ac.bitspilani.webapp.model;

import in.ac.bitspilani.webapp.category.Category;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
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

    //find out why transient needs to be used to avoid errors in line 43
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Category> listOfCategories;

    //TODO: write getters and setters for phoneNumber and profession
    //TODO: write addCategory, and removeCategory methods for listOfCategories
    //ask someone if we should add the above methods to User or UserController

}

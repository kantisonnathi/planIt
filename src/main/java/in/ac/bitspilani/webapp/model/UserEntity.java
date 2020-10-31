package in.ac.bitspilani.webapp.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class UserEntity  {
    @Id@GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
   // private String userName;
    private String password;
    private String email;


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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

package in.ac.bitspilani.webapp.diary;

import in.ac.bitspilani.webapp.model.NamedEntity;
import in.ac.bitspilani.webapp.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class DiaryEntry extends NamedEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;

    //this is the content of the diary entry
    @Column(length = 65535, columnDefinition = "text")
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

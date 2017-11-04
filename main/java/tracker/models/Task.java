package tracker.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String body;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;
    @Column(nullable = false)
    private Date date = new Date();
    public Task() {
    }

    public Task(int id, String title, String body, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Task{}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
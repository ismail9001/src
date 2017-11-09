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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Project project;
    @Column (nullable = false, columnDefinition = "boolean default true")
    private boolean is_actual;
    @Column
    private Date date_closed;
    @Column (nullable = false, columnDefinition = "DATE default '15-JUL-1980'")
    private Date date_created;
    public Task() {
    }

    public Task(int id, String title, String body, User author, Project project, boolean is_actual, Date date_closed, Date date_created) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.project = project;
        this.is_actual = is_actual;
        this.date_closed = date_closed;
        this.date_created = date_created;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isIs_actual() {
        return is_actual;
    }

    public void setIs_actual(boolean is_actual) {
        this.is_actual = is_actual;
    }

    public Date getDate_closed() {
        return date_closed;
    }

    public void setDate_closed(Date date_closed) {
        this.date_closed = date_closed;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
}
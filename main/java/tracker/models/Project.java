package tracker.models;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @NotEmpty(message = "*Please provide project name")
    private String projectName;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @Column(nullable = false)
    @NotEmpty(message = "*Please provide main url of your project")
    private String mainURL;
    @Column
    private String devURL;
    @Column (nullable = false)
    private boolean publicFeedback;
    @Column (nullable = false, columnDefinition="boolean default true")
    private boolean is_actual;
    @Column
    private Date date_closed;
    @Column (nullable = false, columnDefinition = "DATE default '15-JUL-1980'")
    private Date date_created;

    public Project() {
    }

    public Project(int id, String projectName, User user, String mainURL, String devURL, boolean publicFeedback,
                   boolean is_actual, Date date_closed, Date date_created) {
        this.id = id;
        this.projectName = projectName;
        this.user = user;
        this.mainURL = mainURL;
        this.devURL = devURL;
        this.publicFeedback = publicFeedback;
        this.is_actual = is_actual;
        this.date_closed = date_closed;
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "Project{}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMainURL() {
        return mainURL;
    }

    public void setMainURL(String mainURL) {
        this.mainURL = mainURL;
    }

    public String getDevURL() {
        return devURL;
    }

    public void setDevURL(String devURL) {
        this.devURL = devURL;
    }

    public boolean isPublicFeedback() {
        return publicFeedback;
    }

    public void setPublicFeedback(boolean publicFeedback) {
        this.publicFeedback = publicFeedback;
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

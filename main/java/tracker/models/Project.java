package tracker.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

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

    public Project() {
    }

    public Project(int id, String projectName, User user, String mainURL, String devURL, boolean publicFeedback) {
        this.id = id;
        this.projectName = projectName;
        this.user = user;
        this.mainURL = mainURL;
        this.devURL = devURL;
        this.publicFeedback = publicFeedback;
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
}

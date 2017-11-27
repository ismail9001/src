package tracker.models;

import javax.persistence.*;

@Entity
@Table(name = "TaskSource")
public class TaskSource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(nullable = false, name="source", unique = true)
    private String source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
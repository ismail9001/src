package tracker.models;

import javax.persistence.*;


@Entity
@Table(name = "sourceOfTacks")
public class SourceOfTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="source_id")
    private int id;
    @Column(nullable = false, name="source")
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
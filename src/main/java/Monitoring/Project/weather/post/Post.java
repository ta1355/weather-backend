package Monitoring.Project.weather.post;

import Monitoring.Project.weather.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private User user;

    private String title;

    private String contents;

    private LocalDateTime createdTime = LocalDateTime.now();

    public Post() {
    }

    public Post(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void update(String contents, String title){
        setContents(contents);
        setTitle(title);
    }
}

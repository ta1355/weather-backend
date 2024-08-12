package Monitoring.Project.weather.user;

import Monitoring.Project.weather.weathers.KoreanCity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "users")
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDate birthday;

    private List<KoreanCity> likedList;

    // constructors
    public User() {
    }

    public User(String email, String password, String nickname, LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    // password check
    public boolean authenticate(String rawPassword) {
        String hashedInputPassword = SecurityUtils.sha256Encrypt(rawPassword);
        return this.password.equals(hashedInputPassword);
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public List<KoreanCity> getLikedList() {
        return likedList;
    }

    // setter
    public void setLikedList(List<KoreanCity> likedList) {
        this.likedList = likedList;
    }
}

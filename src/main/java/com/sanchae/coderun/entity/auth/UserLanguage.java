package com.sanchae.coderun.entity.auth;

import com.sanchae.coderun.entity.language.Language;
import jakarta.persistence.*;

@Entity
public class UserLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    public UserLanguage() {}

    public UserLanguage(User user, Language language) {
        this.user = user;
        this.language = language;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Language getLanguage() { return language; }
}
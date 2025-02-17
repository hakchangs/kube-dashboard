package net.kubeworks.kubedashboard.domain.account.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 36, unique = true)
    String uid;
    @Column(nullable = false, length = 100, unique = true)
    String username;
    @Column(nullable = false, length = 100)
    String password;

    @Column(nullable = false)
    boolean enabled;

    @Column
    ZonedDateTime lastLoginedAt;

    @Column(nullable = false)
    ZonedDateTime createdAt;

    @Column(nullable = false)
    ZonedDateTime modifiedAt;

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = ZonedDateTime.now();
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeLastLoginedAt(ZonedDateTime lastLoginedAt) {
        this.lastLoginedAt = lastLoginedAt;
    }

}

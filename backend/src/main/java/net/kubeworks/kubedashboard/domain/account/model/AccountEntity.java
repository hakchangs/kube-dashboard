package net.kubeworks.kubedashboard.domain.account.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter @Setter
public class AccountEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;

    @Column
    private boolean enabled;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

}

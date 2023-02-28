package com.goit.feature.mvc.user;

import com.goit.feature.mvc.notes.Note;
import com.goit.feature.mvc.user.roles.EUserRole;
import com.goit.feature.mvc.user.views.EViewType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


import java.util.List;

@Data
@Table(name="users")
@Entity
public class User {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

//    @Id
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private EUserRole role;

    @Enumerated(EnumType.STRING)
    private EViewType viewType;

//    @ManyToOne
//    @JoinColumn(name="role_id", nullable=false)
//    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Note> notes;
}

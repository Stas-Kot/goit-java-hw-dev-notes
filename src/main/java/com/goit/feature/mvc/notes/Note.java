package com.goit.feature.mvc.notes;

import com.goit.feature.mvc.notes.privacy.EPrivacy;
import com.goit.feature.mvc.user.User;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;

@Data
@Entity
@Table(name="notes")
public class Note {
    @Id
    @Column(name="note_id")
    private String noteId;

    @Column(length = 100)
    private String title;

    @Column(length = 10000)
    private String content;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private EPrivacy privacy;

//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name="privacy", nullable=false)
//    private Privacy privacyType;
}

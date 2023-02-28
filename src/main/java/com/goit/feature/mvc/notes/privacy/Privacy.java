package com.goit.feature.mvc.notes.privacy;

import com.goit.feature.mvc.notes.Note;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "note_privacy")
public class Privacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long privacy_id;

    @Enumerated
    @Column(length = 20)
    private EPrivacy privacyName;

//    @OneToMany(mappedBy="privacyType", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
//    private List<Note> notes;
//
//    public void addNote(Note note) {
//        notes.add(note);
//        note.setPrivacyType(this);
//    }
//
//    public void removeNote(Note note) {
//        notes.remove(note);
//        note.setPrivacyType(null);
//    }

    public long getPrivacy_id() {
        return privacy_id;
    }

    public void setPrivacy_id(long privacy_id) {
        this.privacy_id = privacy_id;
    }

    public EPrivacy getPrivacyName() {
        return privacyName;
    }

    public void setPrivacyName(EPrivacy privacyName) {
        this.privacyName = privacyName;
    }
}

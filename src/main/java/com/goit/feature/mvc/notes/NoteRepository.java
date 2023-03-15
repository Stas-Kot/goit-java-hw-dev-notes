package com.goit.feature.mvc.notes;

import com.goit.feature.mvc.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
    @Query(nativeQuery = true, value = "SELECT note_id as noteId, user_id, title, content, privacy FROM notes")
    List<Note> getAllNotesCustom();

    @Query(nativeQuery = true, value = "SELECT note_id as noteId, user_id, title, content, privacy FROM notes n WHERE n.user_id = :user_id")
    List<Note> getAllNotesByUserId(@Param("user_id") Long id);

    @Query("SELECT n FROM Note n WHERE n.user = :user")
    List<Note> findByUser(@Param("user") User user);

//    @Query(nativeQuery = true, value = "SELECT note_id as noteId, user_id, title, content, privacy FROM notes n WHERE n.user_id = :user_id")
//    List<Note> getAllNotesByUserId(@Param("user_id") Long id);

    public List<Note> findAllByUser_Id(long id);
}

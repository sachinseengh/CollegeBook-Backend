package com.collegeBook.CollegeBook.repository;

import com.collegeBook.CollegeBook.entity.Notes;
import com.collegeBook.CollegeBook.pojo.note.NoteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Notes,Long> {


    @Query("SELECT n FROM Notes n JOIN FETCH n.user u JOIN FETCH u.roles")
    Optional<List<Notes>> getNotes();


    @Query("SELECT n FROM Notes n JOIN FETCH n.user u JOIN FETCH u.roles WHERE n.semester = :semester AND n.subject = :subject")
    Optional<List<Notes>> getNotesBySemesterAndSubject(@Param("semester") String semester, @Param("subject") String subject);


    Optional<Notes> findById(Long id);


}

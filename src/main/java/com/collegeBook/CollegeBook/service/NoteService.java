package com.collegeBook.CollegeBook.service;

import com.collegeBook.CollegeBook.entity.Notes;
import com.collegeBook.CollegeBook.pojo.note.NoteResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface NoteService {


    public List<NoteResponse> getNotes();

    public List<NoteResponse> getNotesBySemesterAndSubject( String semester,String subject);


    public Long deleteById(Long note_id);
}

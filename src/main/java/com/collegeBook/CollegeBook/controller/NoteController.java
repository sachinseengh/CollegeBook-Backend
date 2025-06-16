package com.collegeBook.CollegeBook.controller;

import com.collegeBook.CollegeBook.pojo.GlobalApiResponse;
import com.collegeBook.CollegeBook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteController extends BaseController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/getNotes")
    public ResponseEntity<GlobalApiResponse> getNotes(){
        return successResponse("Notes fetched",noteService.getNotes());
    }


    @GetMapping("/getNote")
    public ResponseEntity<GlobalApiResponse> getNotesBySemesterAndSubject(@RequestParam String semester,@RequestParam String subject){
        return successResponse("Notes fetched",noteService.getNotesBySemesterAndSubject(semester,subject));
    }

    @DeleteMapping ("/deleteNote/{id}")
    public ResponseEntity<GlobalApiResponse> deleteNote(@PathVariable Long id){
        return successResponse("Notes Deleted",noteService.deleteById(id));
    }




}

package com.collegeBook.CollegeBook.service.impl;

import com.collegeBook.CollegeBook.entity.Notes;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.pojo.note.NoteResponse;
import com.collegeBook.CollegeBook.pojo.user.UserResponse;
import com.collegeBook.CollegeBook.repository.NoteRepository;
import com.collegeBook.CollegeBook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public List<NoteResponse> getNotes() {

        List<Notes> notes = noteRepository.getNotes().orElse(Collections.emptyList());
        List<NoteResponse> responses = new ArrayList<>();

        if (notes != null) {
            for (Notes note : notes) {

                List<String> roles = note.getUser().getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());

                UserResponse userResponse = new UserResponse(note.getUser()
                        .getId(), note.getUser().getFirstName(), note.getUser().getLastName(), note.getUser().getUserName(), roles);

                String fileUrl = null;
                String fileType = null;
                String fileName=null;
                if (note.getFileName() != null) {
                    fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/uploads/")
                            .path(note.getFileName())
                            .toUriString();
                    fileType = note.getFileType();
                    fileName=note.getFileName();

                }

                responses.add(new NoteResponse(note.getId(), note.getDate(), userResponse,note.getSemester(),note.getSubject(), fileUrl, fileType,fileName));
            }
            return responses;
        }
        return responses;
    }


    @Override
    public List<NoteResponse> getNotesBySemesterAndSubject(String semester, String subject) {



        List<Notes> notes = noteRepository.getNotesBySemesterAndSubject(semester, subject).orElse(Collections.emptyList());
        List<NoteResponse> responses = new ArrayList<>();

        if (notes != null) {
            for (Notes note : notes) {

                List<String> roles = note.getUser().getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());

                UserResponse userResponse = new UserResponse(note.getUser()
                        .getId(), note.getUser().getFirstName(), note.getUser().getLastName(), note.getUser().getUserName(), roles);

                String fileUrl = null;
                String fileType = null;
                String fileName=null;
                if (note.getFileName() != null) {
                    fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/uploads/")
                            .path(note.getFileName())
                            .toUriString();
                    fileType = note.getFileType();
                    fileName=note.getFileName();

                }

                responses.add(new NoteResponse(note.getId(), note.getDate(), userResponse,note.getSemester(),note.getSubject(), fileUrl, fileType,fileName));
            }
            return responses;
        }
        return responses;
    }

    @Override
    public Long deleteById(Long note_id) {

        Notes note = noteRepository.findById(note_id).orElseThrow(()->new AppException("Note not Found"));

        noteRepository.delete(note);
        return note.getId();
    }
}

package com.collegeBook.CollegeBook.pojo.note;

import com.collegeBook.CollegeBook.pojo.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteResponse {

    private Long note_id;

    private LocalDateTime date;
    private UserResponse userResponse;
    private String semester;
    private String subject;



    private String fileUrl;
    private String fileType;
    private String fileName;

}

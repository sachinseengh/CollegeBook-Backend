package com.collegeBook.CollegeBook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Posts {

    @Id
    @SequenceGenerator(name = "post_seq_gen", sequenceName = "posts_seq", allocationSize = 1)
    @GeneratedValue(generator = "post_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;




    //becuase later to display on feed
    //and every post dont have note so linking post Entity with note entity is not good
    private String semester;
    private String subject;

    @Column(nullable = false)
    private String caption;

    private String content;


    private Boolean isNote;

    private String fileName;   // Stored file name on server
    private String fileType;   // Like image/png, video/mp4

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

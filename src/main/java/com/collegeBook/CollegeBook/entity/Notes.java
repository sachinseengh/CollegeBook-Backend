package com.collegeBook.CollegeBook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notes {

    @Id
    @SequenceGenerator(name = "post_seq_gen", sequenceName = "posts_seq", allocationSize = 1)
    @GeneratedValue(generator = "post_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String semester;

    private String subject;

    private LocalDateTime date;

    private String fileName;
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

package com.ThoughtTrove.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int postId;

    @Column(name = "title" ,nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    private  Category category;

    @ManyToOne
    private  User user;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

}

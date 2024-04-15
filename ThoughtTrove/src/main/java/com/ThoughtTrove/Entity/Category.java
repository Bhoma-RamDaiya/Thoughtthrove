package com.ThoughtTrove.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.ast.tree.expression.Star;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int categoryId;


    @Column(name = "title" , length = 10, nullable = false)
    private  String categoryTitle;
    @Column(name = "description" )
    private  String categoryDescrption;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

}

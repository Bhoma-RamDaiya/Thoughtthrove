package com.ThoughtTrove.Repository;
import com.ThoughtTrove.Entity.Category;
import com.ThoughtTrove.Entity.Post;
import com.ThoughtTrove.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post ,Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String title);


    @Query("SELECT p FROM Post p WHERE p.title LIKE %:key%")
    List<Post> searchByTitle(@Param("key") String title) ;


}

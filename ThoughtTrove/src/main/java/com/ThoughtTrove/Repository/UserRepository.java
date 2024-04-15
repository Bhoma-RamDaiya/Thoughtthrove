package com.ThoughtTrove.Repository;

import com.ThoughtTrove.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {

    Optional<User>  findByEmailId(String emailId);
}

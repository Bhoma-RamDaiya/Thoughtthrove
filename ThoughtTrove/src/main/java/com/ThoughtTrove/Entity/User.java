package com.ThoughtTrove.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.PrivateKey;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int userId;

    private  String name;
    private String emailId;
    private String password;
    private String about;

    @OneToMany(mappedBy = "user" , cascade =  CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
  @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
  @JoinTable(name = "user_role" ,
  joinColumns = @JoinColumn(name = "user" , referencedColumnName = "userId"),
  inverseJoinColumns =@JoinColumn(name = "role" , referencedColumnName = "id")  )
    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

            List<SimpleGrantedAuthority> authorities =    roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).toList();
return  authorities;
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

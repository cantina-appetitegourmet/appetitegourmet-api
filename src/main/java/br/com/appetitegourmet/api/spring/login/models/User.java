package br.com.appetitegourmet.api.spring.login.models;

import java.util.HashSet;
import java.util.Set;

import br.com.appetitegourmet.api.models.Pessoa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 300)
  private String username;

  @NotBlank
  @Size(max = 300)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  @JsonIgnore
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
  @JsonManagedReference
  private Pessoa pessoa;
}
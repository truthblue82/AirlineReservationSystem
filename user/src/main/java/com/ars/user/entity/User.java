package com.ars.user.entity;

import com.ars.user.annotation.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames="email"),
            @UniqueConstraint(columnNames="username")
        })
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @NotEmpty(message = "Password is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(max = 50)
    @Email
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(max = 12)
    @JsonIgnore
    private String password;

    private String fullname;

    private String phone;
    @NotEmpty(message = "Gender is required")
    @Gender
    private String gender;

    @OneToOne
    @JoinColumn(name = "role")
    private Role role;
}

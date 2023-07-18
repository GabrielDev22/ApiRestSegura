package com.example.practicaSpringSecurity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String lastName;
    private String correo;
    private String bodyText;

}

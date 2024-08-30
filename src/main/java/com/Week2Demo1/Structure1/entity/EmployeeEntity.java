package com.Week2Demo1.Structure1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id ;

    public String name;

    public String email;

    public Integer age;

    public LocalDate dateOfJoining ;

    public Boolean isActive;

    private String role;

    private Integer salary;

}

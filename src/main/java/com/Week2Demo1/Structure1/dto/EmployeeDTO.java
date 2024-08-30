package com.Week2Demo1.Structure1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    public Long id ;

    //For strings use NotBlank
    @NotBlank(message = "Name is mandatory field")
    @Size(min=1 , max=10 , message = "No of characters should be in the range ")
    public String name;

    @Email(message = "Email should be a valid email")
    @NotNull
    public String email;

    @Positive(message = "Age can't be negative")
    public Integer age;

    @Pattern(regexp ="^(ADMIN|USER)$",message = "It'll take only two values") //It'll take either admin or user
    @NotBlank
    private String role;

    @NotNull
    @PositiveOrZero(message = "Salary must be greater than 0")
    private Integer salary;

    @PastOrPresent(message = "Date of joining can't be of future")
    public LocalDate dateOfJoining ;

    @AssertTrue(message = "This has to be true")
    public Boolean isActive;



}

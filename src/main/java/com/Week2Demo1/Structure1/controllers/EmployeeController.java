package com.Week2Demo1.Structure1.controllers;

import com.Week2Demo1.Structure1.dto.EmployeeDTO;
import com.Week2Demo1.Structure1.entity.EmployeeEntity;
import com.Week2Demo1.Structure1.exception.ResourceNotFoundException;
import com.Week2Demo1.Structure1.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeservice;

    public EmployeeController(EmployeeService employeeservice) {
        this.employeeservice = employeeservice;
    }


    //ResponseEntity is used to show status code + error while hitting the API
    //We're using Optional to implement ResponseEntity
    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="id") Long id){
        Optional<EmployeeDTO> employeeDTO =  employeeservice.getemployeebyId(id);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElseThrow(()-> new ResourceNotFoundException("Employee was not Found"));
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>>  getAllEmployeeDetails(@RequestParam(required = false) Long id){
        return ResponseEntity.ok(employeeservice.getAllEmployees());
    }


    @PostMapping
    public ResponseEntity<EmployeeDTO> getName(@RequestBody @Valid EmployeeDTO createUser){
//       1st way
        EmployeeDTO savedEmployee =  employeeservice.createNewUser(createUser);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

//        2nd way-
//        return ResponseEntity.ok( employeeservice.createNewUser(createUser));
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> updateDataById(@RequestBody EmployeeDTO employeeDTO , @PathVariable Long id)
    {
        return ResponseEntity.ok(employeeservice.updateDataById(employeeDTO,id));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
         employeeservice.deleteEmployeeById(id);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> updateDataByPatch(@RequestBody Map<String,Object> updates, @PathVariable Long id){
       EmployeeDTO employeeDTO = employeeservice.updateDataByPatch(updates,id);
       if(employeeDTO ==null) return ResponseEntity.notFound().build();
       return ResponseEntity.ok(employeeDTO);
    }

}

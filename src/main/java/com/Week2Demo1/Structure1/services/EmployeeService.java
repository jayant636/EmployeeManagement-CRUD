package com.Week2Demo1.Structure1.services;


import com.Week2Demo1.Structure1.dto.EmployeeDTO;
import com.Week2Demo1.Structure1.entity.EmployeeEntity;
import com.Week2Demo1.Structure1.exception.ResourceNotFoundException;
import com.Week2Demo1.Structure1.repositories.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    //    get & getById are quite similar
    //    put & create method are kind of same
    //    patch method is quite tricky

    public Optional<EmployeeDTO>  getemployeebyId(Long id){
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id).orElse(null);
//      return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public  List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class)).collect(Collectors.toList());
    }


    public EmployeeDTO createNewUser (EmployeeDTO createUser){
        //convert employeedto to employeeEntity
        EmployeeEntity saveEntity = modelMapper.map(createUser,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(saveEntity);
        //convert employeeEntity to employeedto
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateDataById(EmployeeDTO employeeDTO, Long id) {
        EmployeeEntity employeeExist = employeeRepository.findById(id).orElse(null);
        employeeDTO.setId(id);
        modelMapper.map(employeeDTO,employeeExist);
        EmployeeEntity updateUser = employeeRepository.save(employeeExist);
        return modelMapper.map(updateUser,EmployeeDTO.class);
    }



    public void deleteEmployeeById(long id) {
         employeeRepository.deleteById(id);
    }

    //use Reflection to update only certain fields
    //Trickiest Method
    public EmployeeDTO updateDataByPatch(Map<String, Object> updates, Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) return null;

//        Make sure to chain get() also
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        // Reflection Implementation
        updates.forEach((field,value)-> {
         Field fieldtoBeUpdated =  ReflectionUtils.findField(EmployeeEntity.class,field);
         fieldtoBeUpdated.setAccessible(true);
         ReflectionUtils.setField(fieldtoBeUpdated,employeeEntity,value);

        });
        EmployeeEntity updatedData = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedData,EmployeeDTO.class);

    }
}

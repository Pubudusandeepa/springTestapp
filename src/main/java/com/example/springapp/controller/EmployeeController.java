package com.example.springapp.controller;

import com.example.springapp.exception.ResourceNotFoundException;
import com.example.springapp.model.Employee;
import com.example.springapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
   private EmployeeRepository employeeRepository;

    //get employees
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
       return employeeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/employees")
    public Employee cretateEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
      Employee employee= employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee is not found by given id:"+id));

      return ResponseEntity.ok(employee);
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
        Employee employee= employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee is not found by given id:"+id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee saveEmployees = employeeRepository.save(employee);
        return  ResponseEntity.ok(saveEmployees);
    }


}

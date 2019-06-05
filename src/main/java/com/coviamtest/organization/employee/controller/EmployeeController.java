package com.coviamtest.organization.employee.controller;

import com.coviamtest.organization.employee.dto.DepartmentDTO;
import com.coviamtest.organization.employee.dto.EmployeeDTO;
import com.coviamtest.organization.employee.entity.Department;
import com.coviamtest.organization.employee.entity.Employee;
import com.coviamtest.organization.employee.exception.*;
import com.coviamtest.organization.employee.services.DepartmentService;
import com.coviamtest.organization.employee.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

/**
 * Created by ppatchava on 12/15/18.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;



    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<?> saveOrUpdate(@RequestBody EmployeeDTO employeeDTO)
            throws DepartmentValidationException, LastNameValidationException, AgeValidationException, SalaryValidationException, TitleValidationException
    {
        if(employeeDTO == null){
            return new ResponseEntity<String>("Data cannot be null", HttpStatus.OK);
        }

        validateLastName(employeeDTO);
        validateAge(employeeDTO);
        validateDepartment(employeeDTO);
        validateSalary(employeeDTO);

        Employee employeeUpdated = employeeService.saveOrUpdate(employeeDTO);

        return new ResponseEntity<EmployeeDTO>(copyFromEmployee(employeeUpdated), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOne/{employeeId}")
    public ResponseEntity<?> getOne(@PathVariable("employeeId")Long  employeeID){
        Optional<Employee> employee = employeeService.getOne(employeeID);

        if(!employee.isPresent()){
            return new ResponseEntity<String>("Employee ID is not valid", HttpStatus.OK);
        }

        return new ResponseEntity<EmployeeDTO>(copyFromEmployee(employee.get()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteOne/{employeeId}")
    public void deleteOne(@PathVariable("employeeId") Long employeeId){
        employeeService.deleteOne(employeeId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<EmployeeDTO> getAll(){
        List<Employee> employeeList = employeeService.getAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee e: employeeList) {
            employeeDTOList.add(copyFromEmployee(e));
        }
        return employeeDTOList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByFirstName/{firstName}")
    public ResponseEntity<EmployeeDTO> getByFirstName(
            @PathVariable("firstName") String firstName){

        return new ResponseEntity<EmployeeDTO>(
                copyFromEmployee(employeeService.getByFirstName(firstName)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByLastName/{lastName}")
    public ResponseEntity<EmployeeDTO> getByLastName(
            @PathVariable("lastName") String lastName){

        return new ResponseEntity<EmployeeDTO>(
                copyFromEmployee(employeeService.getByLastName(lastName)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByFirstNameOrLastName/{name}")
    public ResponseEntity<EmployeeDTO> getByFirstNameOrLastName(
            @PathVariable("name") String name){

        return new ResponseEntity<EmployeeDTO>(
                copyFromEmployee(employeeService.getByFirstNameOrLastName(name,name)),
                HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getByDepartmentName/{departmentName}")
    public List<EmployeeDTO> getAll(@PathVariable("departmentName") String departmentName){
        List<Employee> employeeList = employeeService.getByDepartmentName(departmentName);
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee e: employeeList) {
            employeeDTOList.add(copyFromEmployee(e));
        }
        return employeeDTOList;
    }

    private EmployeeDTO copyFromEmployee(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee,employeeDTO);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(employee.getDepartment(), departmentDTO);
        employeeDTO.setDepartment(departmentDTO);

        return employeeDTO ;
    }

    private void validateAge(EmployeeDTO employeeDTO) throws AgeValidationException {
        if(employeeDTO.getAge() == null){
            throw new AgeValidationException("Age canparentDepartmentCode not be null");
        }

        if(employeeDTO.getAge()>=18 && employeeDTO.getAge()<=65){
            throw new AgeValidationException("Age must be between 18 and 65");
        }
    }

    private void validateLastName(EmployeeDTO employeeDTO) throws LastNameValidationException {
        if(employeeDTO.getLastName()==null){
            throw new LastNameValidationException("Last Name can not be null");
        }
    }

    private void validateDepartment(EmployeeDTO employeeDTO) throws DepartmentValidationException{
        if(employeeDTO.getDepartmentCode()==null){
            throw new DepartmentValidationException("Department Code can not be null");
        }
    }

    private void validateSalary(EmployeeDTO employeeDTO) throws SalaryValidationException{
        if(employeeDTO.getSalary()==null){
            throw new SalaryValidationException("Salary can not be null");
        }
    }
}

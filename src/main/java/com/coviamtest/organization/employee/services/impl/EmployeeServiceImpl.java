package com.coviamtest.organization.employee.services.impl;

import com.coviamtest.organization.employee.constant.ValidationConstants;
import com.coviamtest.organization.employee.dto.DepartmentDTO;
import com.coviamtest.organization.employee.dto.EmployeeDTO;
import com.coviamtest.organization.employee.entity.Department;
import com.coviamtest.organization.employee.entity.DepartmentSalaryConstraints;
import com.coviamtest.organization.employee.entity.Employee;
import com.coviamtest.organization.employee.entity.Title;
import com.coviamtest.organization.employee.exception.*;
import com.coviamtest.organization.employee.repository.DepartmentRepository;
import com.coviamtest.organization.employee.repository.DepartmentSalaryConstraintsRepository;
import com.coviamtest.organization.employee.repository.EmployeeRepository;
import com.coviamtest.organization.employee.repository.TitleRepository;
import com.coviamtest.organization.employee.services.DepartmentService;
import com.coviamtest.organization.employee.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ppatchava on 12/14/18.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private DepartmentSalaryConstraintsRepository departmentSalaryConstraintsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public Optional<Employee> getOne(Long employeeId){
        return employeeRepository.findById(employeeId);
    }

    public void deleteOne(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    public void deleteOne(Employee employee){
        employeeRepository.delete(employee);
    }

    public List<Employee> getAll(){
        Iterable<Employee> employeeIterable = employeeRepository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        for(Employee e: employeeIterable){
            employeeList.add(e);
        }
        return employeeList;
    }

    public Employee getByFirstName(String firstName){
        return employeeRepository.getByFirstName(firstName);
    }

    public Employee getByLastName(String lastName){
        return employeeRepository.getByLastName(lastName);
    }

    public Employee getByFirstNameOrLastName(String firstName, String lastName){
        return employeeRepository.getByFirstNameOrLastName(firstName,lastName);
    }

    public List<Employee> getByDepartmentName(String departmentName){
        return employeeRepository.getByDepartmentName(departmentName);
    }

    public Employee saveOrUpdate(EmployeeDTO employeeDTO)
            throws DepartmentValidationException, TitleValidationException, SalaryValidationException{
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setDepartment(fetchDepartment(employeeDTO.getDepartmentCode()));
        employee.setTitle(fetchTitle(employeeDTO.getTitleCode()));
        validateSalary(employee);
        return employeeRepository.save(employee) ;
    }


    private void validateAge(EmployeeDTO employeeDTO) throws AgeValidationException {
        if(employeeDTO.getAge()>= ValidationConstants.MIN_AGE && employeeDTO.getAge()<=ValidationConstants.MAX_AGE){
            throw new AgeValidationException("Age must be between "+ValidationConstants.MIN_AGE+" and " + ValidationConstants.MAX_AGE);
        }
    }

    private Department fetchDepartment(String departmetCode) throws DepartmentValidationException {
        Department department = departmentRepository.getByCode(departmetCode);

        if(department==null){
            throw new DepartmentValidationException("Department Code is invalid");
        }

        return department;
    }

    private Title fetchTitle(String titleCode) throws TitleValidationException {
        Title title = titleRepository.getByCode(titleCode);

        if(title == null){
            throw new TitleValidationException("Invalid Title");
        }

        return title;
    }

    private void validateSalary(Employee employee) throws SalaryValidationException, TitleValidationException, DepartmentValidationException{
        if(employee.getDepartment()==null){
            throw new DepartmentValidationException("Department Code is invalid");
        }

        if(employee.getTitle() == null){
            throw new TitleValidationException("Invalid Title");
        }

        DepartmentSalaryConstraints departmentSalaryConstraints =
                departmentSalaryConstraintsRepository.getByDepartmentAndTitle(employee.getDepartment().getId(),
                        employee.getTitle().getId()) ;

        if(employee.getSalary() <= departmentSalaryConstraints.getMinimumSalary()){
            throw new SalaryValidationException("Salary of employee can not be below minimum salary :"+departmentSalaryConstraints.getMinimumSalary());
        }

        if(employee.getSalary()>= departmentSalaryConstraints.getMaximumSalary()){
            throw new SalaryValidationException("Salary of employee can not be more than maximum salary :"+departmentSalaryConstraints.getMaximumSalary());
        }
    }
}

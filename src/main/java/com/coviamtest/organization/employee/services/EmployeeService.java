package com.coviamtest.organization.employee.services;

import com.coviamtest.organization.employee.dto.EmployeeDTO;
import com.coviamtest.organization.employee.entity.Employee;
import com.coviamtest.organization.employee.exception.DepartmentValidationException;
import com.coviamtest.organization.employee.exception.SalaryValidationException;
import com.coviamtest.organization.employee.exception.TitleValidationException;

import java.util.List;
import java.util.Optional;

/**
 * Created by ppatchava on 12/14/18.
 */
public interface EmployeeService {
    public Employee saveOrUpdate(EmployeeDTO employeeDTO) throws DepartmentValidationException,TitleValidationException, SalaryValidationException;
    public Optional<Employee> getOne(Long employeeId);
    public void deleteOne(Long employeeId);
    public void deleteOne(Employee employee);
    public List<Employee> getAll();
    public Employee getByFirstName(String firstName);
    public Employee getByLastName(String lastName);
    public Employee getByFirstNameOrLastName(String firstName, String lastName);
    public List<Employee> getByDepartmentName(String departmentName);
}

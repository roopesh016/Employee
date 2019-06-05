package com.coviamtest.organization.employee.repository;

import com.coviamtest.organization.employee.entity.Employee;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import  java.util.List;

/**
 * Created by ppatchava on 12/14/18.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public Employee getByFirstName(String firstName);
    public Employee getByLastName(String lastName);
    public Employee getByFirstNameOrLastName(String firstName, String lastName);

    @Query(value = "SELECT e.* FROM employee e, department d " +
            "where e.department_id = d.id and d.name = :departmentName",
    nativeQuery = true)
    public List<Employee> getByDepartmentName(String departmentName);
}

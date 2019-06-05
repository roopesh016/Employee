package com.coviamtest.organization.employee.repository;

import com.coviamtest.organization.employee.dto.DepartmentAggregateDTO;
import com.coviamtest.organization.employee.entity.Department;
import com.coviamtest.organization.employee.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ppatchava on 12/14/18.
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    public Department getByCode(String code);
    public List<Department> getByDepartmentParentCode(String parent_id);

    @Query(value = "SELECT e.* FROM employee e, department d " +
        "where e.department_id = d.id and d.parent_dept_id = :parent_id",
        nativeQuery = true)
    public List<Department> getEmployeeCountByDepartment(String parent_id);


    @Query( value = "SELECT avg(e.salary) from employee e,department d"
        + "where e.department_id = d.id and d.parent_dept_id = :parent_id", nativeQuery = true )
    public List<Department> getEmployeeAvgSalary(String parent_id);
}

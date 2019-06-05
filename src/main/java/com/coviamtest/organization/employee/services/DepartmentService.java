package com.coviamtest.organization.employee.services;

import com.coviamtest.organization.employee.dto.DepartmentAggregateDTO;
import com.coviamtest.organization.employee.dto.DepartmentDTO;
import com.coviamtest.organization.employee.entity.Department;

import java.util.Optional;
import java.util.List;

/**
 * Created by ppatchava on 12/14/18.
 */
public interface DepartmentService {
    public Department saveOrUpdate(Department department);
    public Optional<Department> getOne(Long departmentId);
    public void deleteOne(Long departmentId);
    public void deleteOne(Department department);
    public List<Department> getAll();
    public Department getByDepartmentCode(String code);
    public List<Department> getDepartmentByParentCode(String parentCode);
    public List<Department> getEmployeeCountByParentCode(String parentCode);
    public List<Department> getEmployeeAvgSalaryByParentCode(String parentCode);
}

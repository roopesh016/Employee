package com.coviamtest.organization.employee.repository;

import com.coviamtest.organization.employee.entity.DepartmentSalaryConstraints;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ppatchava on 4/23/19.
 */
public interface DepartmentSalaryConstraintsRepository extends CrudRepository<DepartmentSalaryConstraints,Long> {
    DepartmentSalaryConstraints getByDepartmentAndTitle(Long departmentId, Long titleId);
}
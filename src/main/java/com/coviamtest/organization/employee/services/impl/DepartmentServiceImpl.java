package com.coviamtest.organization.employee.services.impl;

import com.coviamtest.organization.employee.dto.DepartmentAggregateDTO;
import com.coviamtest.organization.employee.entity.Department;
import com.coviamtest.organization.employee.repository.DepartmentRepository;
import com.coviamtest.organization.employee.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ppatchava on 12/15/18.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveOrUpdate(Department department){
        return departmentRepository.save(department);
    }

    public Optional<Department> getOne(Long departmentId){
        return departmentRepository.findById(departmentId);
    }

    public void deleteOne(Long departmentId){
        departmentRepository.deleteById(departmentId);
    }

    public void deleteOne(Department department){
        departmentRepository.delete(department);
    }

    public List<Department> getAll()
    {
        Iterable<Department> departmentIterable = departmentRepository.findAll();
        List<Department> departmentList = new ArrayList<>();
        for(Department d: departmentIterable){
            departmentList.add(d);
        }
        return departmentList;
    }

    public Department getByDepartmentCode(String code){
        return departmentRepository.getByCode(code);
    }

    public List<Department> getDepartmentByParentCode(String parentCode) {
        return departmentRepository.getByDepartmentParentCode(parentCode);
    }

    public List<Department> getEmployeeCountByParentCode(String parentCode){
        return departmentRepository.getEmployeeCountByDepartment(parentCode);
    }

    public List<Department> getEmployeeAvgSalaryByParentCode(String parentCode){
        return departmentRepository.getEmployeeAvgSalary(parentCode);
    }
}

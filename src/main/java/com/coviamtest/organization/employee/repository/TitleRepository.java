package com.coviamtest.organization.employee.repository;

import com.coviamtest.organization.employee.entity.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ppatchava on 4/23/19.
 */
@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {
    public Title getByCode(String code);
    @Query(value = "SELECT e.* FROM title t, department d " +
            "where t.department_id = d.id and d.name = :departmentName",
            nativeQuery = true)
    public List<Title> getByDepartmentName(String departmentName);

}

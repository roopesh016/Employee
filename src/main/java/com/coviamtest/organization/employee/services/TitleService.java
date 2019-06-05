package com.coviamtest.organization.employee.services;

import com.coviamtest.organization.employee.entity.Title;

import java.util.List;
import java.util.Optional;

/**
 * Created by ppatchava on 4/24/19.
 */
public interface TitleService {
    public Title saveOrUpdate(Title title);
    public Optional<Title> getOne(Long titleId);
    public void deleteOne(Long titleId);
    public void deleteOne(Title title);
    public Title getByCode(String titleCode);
    public List<Title> getAll();
    public List<Title> getByDepartmentName(String departmentName);
}

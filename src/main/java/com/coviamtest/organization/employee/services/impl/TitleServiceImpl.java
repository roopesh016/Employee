package com.coviamtest.organization.employee.services.impl;

import com.coviamtest.organization.employee.entity.Title;
import com.coviamtest.organization.employee.repository.TitleRepository;
import com.coviamtest.organization.employee.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ppatchava on 4/24/19.
 */
@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    TitleRepository titleRepository;

    @Override
    public Title saveOrUpdate(Title title) {
        return titleRepository.save(title);
    }

    @Override
    public Optional<Title> getOne(Long titleId) {
        return titleRepository.findById(titleId);
    }

    @Override
    public void deleteOne(Long titleId) {
        titleRepository.deleteById(titleId);
    }

    @Override
    public void deleteOne(Title title) {
        titleRepository.delete(title);
    }

    @Override
    public Title getByCode(String titleCode) {
        return titleRepository.getByCode(titleCode);
    }

    @Override
    public List<Title> getAll() {
        Iterable<Title> titleIterable=titleRepository.findAll();
        List<Title> titleList= new ArrayList<>();
        for(Title t: titleIterable){
            titleList.add(t);
        }
        return titleList;
    }

    @Override
    public List<Title> getByDepartmentName(String departmentName) {
        return titleRepository.getByDepartmentName(departmentName);
    }
}

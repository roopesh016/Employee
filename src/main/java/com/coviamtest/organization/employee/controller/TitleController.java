package com.coviamtest.organization.employee.controller;

import com.coviamtest.organization.employee.dto.TitleDTO;
import com.coviamtest.organization.employee.entity.Department;
import com.coviamtest.organization.employee.entity.Organization;
import com.coviamtest.organization.employee.entity.Title;
import com.coviamtest.organization.employee.exception.DepartmentValidationException;
import com.coviamtest.organization.employee.exception.OrganizationValidationException;
import com.coviamtest.organization.employee.services.DepartmentService;
import com.coviamtest.organization.employee.services.OrganizationService;
import com.coviamtest.organization.employee.services.TitleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ppatchava on 4/24/19.
 */
@RestController
@RequestMapping("/title")
public class TitleController {

    @Autowired
    TitleService titleService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    DepartmentService departmentService;


    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<?> saveOrUpdate(@RequestBody TitleDTO titleDTO)
            throws DepartmentValidationException, OrganizationValidationException
    {
        if(titleDTO == null){
            return new ResponseEntity<String>("Data cannot be null", HttpStatus.OK);
        }

        Department department = findDepartment(titleDTO.getDepartmentCode());
        Organization organization = findOrganization(titleDTO.getOrganizationCode());

        Title title = new Title();
        BeanUtils.copyProperties(titleDTO, title);
        title.setDepartment(department);
        title.setOrganization(organization);


        Title titleUpdated = titleService.saveOrUpdate(title);

        return new ResponseEntity<TitleDTO>(copyFromTitle(titleUpdated), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOne/{titleId}")
    public ResponseEntity<?> getOne(@PathVariable("titleId")Long  titleId){
        Optional<Title> title = titleService.getOne(titleId);

        if(!title.isPresent()){
            return new ResponseEntity<String>("Title ID is not valid", HttpStatus.OK);
        }

        return new ResponseEntity<TitleDTO>(copyFromTitle(title.get()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteOne/{titleId}")
    public void deleteOne(@PathVariable("titleId") Long titleId){
        titleService.deleteOne(titleId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<TitleDTO> getAll(){
        List<Title> titleList = titleService.getAll();
        List<TitleDTO> titleDTOArrayList = new ArrayList<>();
        for (Title t: titleList) {
            titleDTOArrayList.add(copyFromTitle(t));
        }
        return titleDTOArrayList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByDepartmentName/{departmentName}")
    public List<TitleDTO> getAll(@PathVariable("departmentName") String departmentName){
        List<Title> titleList = titleService.getByDepartmentName(departmentName);
        List<TitleDTO> titleDTOArrayList = new ArrayList<>();
        for (Title t: titleList) {
            titleDTOArrayList.add(copyFromTitle(t));
        }
        return titleDTOArrayList;
    }

    private TitleDTO copyFromTitle(Title title){
        TitleDTO titleDTO = new TitleDTO();
        BeanUtils.copyProperties(title,titleDTO);
        titleDTO.setDepartmentCode(title.getDepartment().getCode());
        titleDTO.setOrganizationCode(title.getOrganization().getCode());

        return titleDTO ;
    }


    private Department findDepartment(String departmentCode) throws DepartmentValidationException{
        if(departmentCode == null){
            throw new DepartmentValidationException("Department Code can not be null");
        }

        Department department = departmentService.getByDepartmentCode(departmentCode) ;

        if(department == null){
            throw new DepartmentValidationException("Invalid Department Code");
        }

        return department;
    }

    private Organization findOrganization(String organizationCode) throws OrganizationValidationException{
        if(organizationCode == null){
            throw new OrganizationValidationException("Organization Code can not be null");
        }

        Organization organization = organizationService.getByOrganizationCode(organizationCode);

        if(organization == null){
            throw new OrganizationValidationException("Invalid Organization Code");
        }

        return organization;
    }

}

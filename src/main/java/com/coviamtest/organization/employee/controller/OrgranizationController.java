package com.coviamtest.organization.employee.controller;

import com.coviamtest.organization.employee.dto.OrganizationDTO;
import com.coviamtest.organization.employee.entity.Organization;
import com.coviamtest.organization.employee.services.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by ppatchava on 12/14/18.
 */
@RestController
@RequestMapping("/organization")
public class OrgranizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Organization organization){
        ResponseEntity<OrganizationDTO> organizationResponseEntity ;
        Organization organizationResponse = organizationService.saveOrUpdate(organization);

        if(organizationResponse == null){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();
        BeanUtils.copyProperties(organization, organizationDTO);
        return new ResponseEntity<OrganizationDTO>(organizationDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOne/{organizationId}")
    public ResponseEntity<?> getOne(@PathVariable("organizationId") Long organizationId){
        Optional<Organization> organization = organizationService.getOne(organizationId);

        if(!organization.isPresent()){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();
        BeanUtils.copyProperties(organization.get(),organizationDTO);
        return new ResponseEntity<OrganizationDTO>(organizationDTO, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteOne/{organizationId}")
    public void deleteOne(@PathVariable("organizationId") Long organizationId){
        organizationService.deleteOne(organizationId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteOne")
    public void deleteOne(@RequestBody Organization organization){
        organizationService.deleteOne(organization);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<Organization> getAll(){
        return organizationService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByCode/{organizationCode}")
    public ResponseEntity<OrganizationDTO> getByOrganizationCode(@PathVariable("organizationCode") String organizationCode){
        Organization organization = organizationService.getByOrganizationCode(organizationCode);
        OrganizationDTO organizationDTO = new OrganizationDTO();
        BeanUtils.copyProperties(organization,organizationDTO);
        return new ResponseEntity<OrganizationDTO>(organizationDTO, HttpStatus.OK)   ;
    }

}

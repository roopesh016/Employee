package com.coviamtest.organization.employee.services.impl;

import com.coviamtest.organization.employee.entity.Organization;
import com.coviamtest.organization.employee.repository.OrganizationRepository;
import com.coviamtest.organization.employee.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ppatchava on 12/14/18.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization saveOrUpdate(Organization organization){
        Organization organizationUpdated =  organizationRepository.save(organization);
        return organizationUpdated;
    }

    public Optional<Organization> getOne(Long organizationId){
        return organizationRepository.findById(organizationId);
    }

    public void deleteOne(Long organizationId){
        organizationRepository.deleteById(organizationId);
    }

    public void deleteOne(Organization organization){
        if(organization.getId() != null){
            organizationRepository.delete(organization);
        }
    }

    public List<Organization> getAll(){
        Iterable<Organization> organizations = organizationRepository.findAll();
        List<Organization> organizationList = new ArrayList<>();
        for(Organization o : organizations){
            organizationList.add(o);
        }
        return organizationList;
    }

    public Organization getByOrganizationCode(String organizationCode){
        return organizationRepository.getByCode(organizationCode);
    }
}

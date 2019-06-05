package com.coviamtest.organization.employee.services;

import java.util.List;
import java.util.Optional;

import com.coviamtest.organization.employee.entity.Organization;

/**
 * Created by ppatchava on 12/14/18.
 */
public interface OrganizationService {

    public Organization saveOrUpdate(Organization organization);

    public Optional<Organization> getOne(Long organizationId);

    public void deleteOne(Long organizationId);

    public void deleteOne(Organization organization);

    public List<Organization> getAll();

    public Organization getByOrganizationCode(String organizationCode);
}

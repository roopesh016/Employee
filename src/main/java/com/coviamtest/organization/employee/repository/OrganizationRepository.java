package com.coviamtest.organization.employee.repository;

import com.coviamtest.organization.employee.entity.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ppatchava on 12/14/18.
 */
@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    public Organization getByCode(String code);
}

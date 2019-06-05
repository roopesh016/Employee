package com.coviamtest.organization.employee.dto;

/**
 * Created by ppatchava on 12/14/18.
 */
public class DepartmentDTO {

    private Long id ;

    private String name ;

    private String code ;

    private String organizationCode;

    private String parentDepartmentCode;

    private OrganizationDTO organization;

    private DepartmentDTO parentDepartment ;

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getParentDepartmentCode() {
        return parentDepartmentCode;
    }

    public void setParentDepartmentCode(String parentDepartmentCode) {
        this.parentDepartmentCode = parentDepartmentCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
        if(organization != null){
            this.setOrganizationCode(organization.getCode());
        }
    }

    public DepartmentDTO getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(DepartmentDTO parentDepartment) {
        this.parentDepartment = parentDepartment;
        if(parentDepartment!=null){
            this.setParentDepartmentCode(parentDepartment.getCode());
        }
    }
}

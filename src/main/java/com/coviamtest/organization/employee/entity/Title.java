package com.coviamtest.organization.employee.entity;

import com.coviamtest.organization.employee.constant.TableNames;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ppatchava on 4/23/19.
 */
@Entity
@Table(name = TableNames.TITLE)
public class Title {

    @Id
    @GenericGenerator(name = "titleGen", strategy = "increment")
    @GeneratedValue(generator = "titleGen")
    private Long id ;

    private String name ;

    private String code ;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department ;

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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

package com.coviamtest.organization.employee.entity;

import com.coviamtest.organization.employee.constant.TableNames;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ppatchava on 12/13/18.
 */
@Entity
@Table(name = TableNames.DEPARTMENT_TABLE_NAME)
public class Department {

    @Id
    @GenericGenerator(name = "departmentGen", strategy = "increment")
    @GeneratedValue(generator = "departmentGen")
    private Long id ;

    private String name ;

    private String code ;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "parent_dept_id")
    private Department parentDepartment ;

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

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", organization=" + organization +
                ", parentDepartment=" + parentDepartment +
                '}';
    }
}

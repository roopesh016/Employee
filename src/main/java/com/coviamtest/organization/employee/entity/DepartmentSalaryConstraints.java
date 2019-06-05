package com.coviamtest.organization.employee.entity;

import com.coviamtest.organization.employee.constant.TableNames;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ppatchava on 4/23/19.
 */
@Entity
@Table(name = TableNames.DEPARTMENT_SALARY_CONSTRAINTS)
public class DepartmentSalaryConstraints {
    @Id
    @GenericGenerator(name = "orgGen", strategy = "increment")
    @GeneratedValue(generator = "orgGen")
    private Long id;

    private Double minimumSalary ;

    private Double maximumSalary ;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department ;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(Double minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public Double getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(Double maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
package com.coviamtest.organization.employee.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ppatchava on 12/13/18.
 */
@Entity
@Table(name = Employee.EMPLOYEE_TABLE_NAME)
public class Employee {
    public static final String EMPLOYEE_TABLE_NAME= "EMPLOYEE";

    @Id
    @GenericGenerator(name = "orgGen", strategy = "increment")
    @GeneratedValue(generator = "orgGen")
    private Long id;

    private String firstName ;

    private String lastName ;

    private Double salary ;

    private Double age ;

    @OneToOne
    @JoinColumn(name = "title_id")
    private Title title;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", department=" + department +
                '}';
    }
}

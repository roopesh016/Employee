package com.coviamtest.organization.employee.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ppatchava on 12/13/18.
 */
@Entity
@Table(name = Organization.ORGANIZATION_TABLE)
public class Organization {

    public static final String ORGANIZATION_TABLE = "ORGANIZATION";

    @Id
    @GenericGenerator(name = "orgGen", strategy = "increment")
    @GeneratedValue(generator = "orgGen")
    private Long id;

    private String name;

    private String code ;

    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

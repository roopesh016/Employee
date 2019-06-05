package com.coviamtest.organization.employee.dto;

import java.util.HashMap;

/**
 * Created by ppatchava on 12/17/18.
 */
public class DepartmentAggregateDTO {

    private String parentDepartmentName;

    private HashMap<String, Long> deptToValue;

    private DepartmentAggregateDTO parentDepartmentDTO;

    private String code ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentDepartmentName() {
        return parentDepartmentName;
    }

    public void setParentDepartmentName(String parentDepartmentName) {
        this.parentDepartmentName = parentDepartmentName;
    }

    public HashMap<String, Long> getDeptToValue() {
        return deptToValue;
    }

    public void setDeptToValue(HashMap<String, Long> deptToValue) {
        this.deptToValue = deptToValue;
    }


    public DepartmentAggregateDTO getParentDepartment() {
        return parentDepartmentDTO;
    }

    public void setParentDepartment(DepartmentAggregateDTO parentDepartmentDTO) {
        this.parentDepartmentDTO = parentDepartmentDTO;
        if(parentDepartmentDTO!=null){
            this.setParentDepartmentName(parentDepartmentDTO.getCode());
        }
    }

}

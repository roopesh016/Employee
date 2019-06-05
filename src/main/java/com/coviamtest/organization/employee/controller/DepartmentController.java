package com.coviamtest.organization.employee.controller;

import com.coviamtest.organization.employee.dto.DepartmentAggregateDTO;
import com.coviamtest.organization.employee.dto.DepartmentDTO;
import com.coviamtest.organization.employee.dto.OrganizationDTO;
import com.coviamtest.organization.employee.entity.Department;
import com.coviamtest.organization.employee.entity.Organization;
import com.coviamtest.organization.employee.services.DepartmentService;
import com.coviamtest.organization.employee.services.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by ppatchava on 12/14/18.
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private DepartmentService departmentService;

    private static Logger logger = Logger.getLogger(DepartmentController.class.getName());

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<?> saveOrUpdate(@RequestBody DepartmentDTO departmentDTO){
        if(departmentDTO == null){
            return new ResponseEntity<String>("Data cannot be null", HttpStatus.OK);
        }

        if(departmentDTO.getOrganizationCode() == null){
            return new ResponseEntity<String>("Organization Code can not be null", HttpStatus.OK);
        }

        Organization organization = organizationService.getByOrganizationCode(departmentDTO.getOrganizationCode());

        if(organization == null){
            return new ResponseEntity<String>("Organization Code is invalid", HttpStatus.OK);
        }

        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        department.setOrganization(organization);

        if(departmentDTO.getParentDepartmentCode() != null){
            Department parentDepartment = departmentService.getByDepartmentCode(departmentDTO.getParentDepartmentCode());
            if(parentDepartment == null){
                return new ResponseEntity<String>("Parent Department Code is invalid", HttpStatus.OK);
            }
            department.setParentDepartment(parentDepartment);
        }

        Department departmentUpdated = departmentService.saveOrUpdate(department);

        return new ResponseEntity<DepartmentDTO>(copyFromDepartmentToDepartmentDTO(departmentUpdated),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOne/{departmentId}")
    public ResponseEntity<?> getOne(@PathVariable("departmentId")Long departmentId){
        Optional<Department> department = departmentService.getOne(departmentId);

        if(!department.isPresent()){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }

        return new ResponseEntity<DepartmentDTO>(copyFromDepartmentToDepartmentDTO(department.get()),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteOne/{departmentId}")
    public void deleteOne(@PathVariable("departmentId") Long departmentId){
        departmentService.deleteOne(departmentId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<DepartmentDTO> getAll(){
        List<Department> departmentList= departmentService.getAll();
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department d: departmentList) {
            departmentDTOList.add(copyFromDepartmentToDepartmentDTO(d));
        }
        return departmentDTOList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByCode/{departmentCode}")
    public ResponseEntity<DepartmentDTO> getByDepartmentCode(
            @PathVariable("departmentCode") String departmentCode){
        Department department = departmentService.getByDepartmentCode(departmentCode);
        return new ResponseEntity<DepartmentDTO>(copyFromDepartmentToDepartmentDTO(department)
                ,HttpStatus.OK);
    }

    private DepartmentDTO copyFromDepartmentToDepartmentDTO(Department department){

        logger.info("department :"+ department.toString());

        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department,departmentDTO);

        OrganizationDTO organizationDTO = new OrganizationDTO();
        BeanUtils.copyProperties(department.getOrganization(),organizationDTO);
        departmentDTO.setOrganization(organizationDTO);

        if(department.getParentDepartment() != null){
            DepartmentDTO parentDepartmentDTO = new DepartmentDTO();
            BeanUtils.copyProperties(department.getParentDepartment(),parentDepartmentDTO);
            departmentDTO.setParentDepartment(parentDepartmentDTO);
        }
        logger.info("departmentDTO :"+ departmentDTO.toString());

        return departmentDTO;
    }

    // TODO : Need to develop the following apis code here as part of the assignment

    @RequestMapping( method = RequestMethod.GET, value = "/getByParentDepartment/{parentDepartmentCode}" )
    public List<DepartmentDTO> getChildDepartments(
        @PathVariable( "parentDepartmentCode" ) String parentDepartmentCode) {
        List<Department> departmentList = departmentService.getDepartmentByParentCode(parentDepartmentCode);
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department d: departmentList) {
            departmentDTOList.add(copyFromDepartment(d));
        }
        return departmentDTOList;
    }

    private DepartmentDTO copyFromDepartment(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department,departmentDTO);

        OrganizationDTO organizationDTO = new  OrganizationDTO();
        BeanUtils.copyProperties(department,organizationDTO);

        departmentDTO.setOrganization(organizationDTO);

        logger.info("departmentDTO :" + departmentDTO.toString());

        return departmentDTO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getDepartmentEmployeeCount/{parentDepartment}")
    public List<DepartmentAggregateDTO> getChildDepartmentEmployeeCount
            (@PathVariable("parentDepartment") String parentDepartment){
        List<Department> departmentList = departmentService.getEmployeeCountByParentCode(parentDepartment);
        List<DepartmentAggregateDTO> departmentAggregateDTOList = new ArrayList<>();
        for(Department dto: departmentList){
            departmentAggregateDTOList.add(copyFromDepartmentAggregateDTO(dto));
        }
        return departmentAggregateDTOList;
    }

    private DepartmentAggregateDTO copyFromDepartmentAggregateDTO(Department dto) {
        DepartmentAggregateDTO departmentAggregateDTO1 = new DepartmentAggregateDTO();
        BeanUtils.copyProperties(dto, departmentAggregateDTO1);
        departmentAggregateDTO1.setParentDepartment(departmentAggregateDTO1);

        logger.info("departmentAggregateDTO :" + departmentAggregateDTO1.toString());

        return departmentAggregateDTO1;
    }

    @RequestMapping( method = RequestMethod.GET, value = "/getDepartmentAvgSalary/{parentDepartment}" )
    public List<DepartmentAggregateDTO> getChildDepartmentAvgSalary(
        @PathVariable( "parentDepartment" ) String parentDepartment) {
        List<Department> avgSalaryDepartmentList =
            departmentService.getEmployeeAvgSalaryByParentCode(parentDepartment);
        List<DepartmentAggregateDTO> departmentDTOList = new ArrayList<>();
        for (Department avgDto : avgSalaryDepartmentList) {
            departmentDTOList.add(copyFromDepartmentAggregateDTO(avgDto));
        }
        return departmentDTOList;
    }
}


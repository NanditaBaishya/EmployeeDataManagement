package com.example.demo.Services;

import com.example.demo.Entities.Employee;
import com.example.demo.Repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployee(String srcid, String id) {
        Optional<Employee> empsource= employeeRepository.findById(srcid);
        Optional<Employee> emptarget = employeeRepository.findById(id);

        if(empsource.isPresent() && emptarget.isPresent()) {
            if(emptarget.get().getReportsto()==null) {
                log.info("Not allowed to see details of the employee with id:{}",emptarget.get().getId());
                return null;
            }
            else if(empsource.get().getReportsto()==null || emptarget.get().getReportsto().equals(empsource.get().getId()) || empsource.get().getId()==emptarget.get().getId()) {
                return emptarget.get();
            }
            else{
                log.info("only team head or head of the organization or the employee self can view details of an employee");
                return null;
            }
        }
        else {
            log.info("The employee with id:{} or id:{} are not a part of organization",srcid,id);
            return null;
        }

    }

    public List<Employee> getAllEmployees(String srcid) {
        Optional<Employee> emp = employeeRepository.findById(srcid);
        if(emp.isPresent()) {
            if(emp.get().getReportsto() == null) {
                List<Employee> empoyeeList = employeeRepository.findAll();
                return empoyeeList;
            }
            else {
                log.info("Only the head of the organization can view details of all the members in the organization");
                return null;
            }
        }
        else {
            log.info("The entered with id:{} is not an employee of the organization",srcid);
            return null;
        }

    }

    public List<Employee> getMyTeam(String srcid) {
        List<Employee> employeeList = employeeRepository.findAll();
        List<Employee> resultantList = new ArrayList<Employee>();
        for(Employee employee: employeeList) {
            if(employee.getReportsto()!=null && employee.getReportsto().equals(srcid)) {
                resultantList.add(employee);
            }
        }


        if(resultantList.isEmpty()) {
            log.info("The id:{} does not lead any team and only team leads can view details of team members",srcid);
            return null;
        }
        else {
            return resultantList;
        }
    }

    public Employee getMyDetails(String srcid) {
        Optional<Employee> emp=employeeRepository.findById(srcid);
        if(emp.isPresent()) {
            return emp.get();
        }
        else {
            log.info("The id:{} is not a valid employee in the organization",srcid);
            return null;
        }
    }
}

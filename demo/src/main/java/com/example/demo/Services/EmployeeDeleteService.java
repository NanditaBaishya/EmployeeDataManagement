package com.example.demo.Services;

import com.example.demo.Entities.Employee;
import com.example.demo.Repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeDeleteService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee deleteEmployee(String srcid, String targetid) {

        Optional<Employee> employee = employeeRepository.findById(srcid);
        if(employee.isPresent()) {
            Employee employeesrc = employee.get();
            if(employeesrc.getReportsto()==null) {

                Optional<Employee> employeetarget = employeeRepository.findById(targetid);
                if(employeetarget.isPresent()) {
                    Employee employeetg = employeetarget.get();

                    if(employeetg.getReportsto()!=null)
                    employeeRepository.deleteById(employeetg.getId());

                    this.ReportToCEO(srcid,employeetg.getId());

                    return employeetg;
                }
                else {
                    log.info("The employee to be deleted with id:{} is not valid",targetid);
                    return null;
                }
            }
            else {
                log.info("Only head of the organization can delete an employee in the company");
                return null;
            }
        }
        else {
            log.info("The source id:{} is not a valid employee in the organization");
            return null;
        }

    }

    private void ReportToCEO(String srcid,String targetid) {

        List<Employee> employeeList = employeeRepository.findAll();

        for(Employee employeeiterator : employeeList) {
            if(employeeiterator.getReportsto()!=null && employeeiterator.getReportsto().equals(targetid)) {
                employeeiterator.setReportsto(srcid);
            }
        }

        employeeRepository.saveAll(employeeList);


    }
}

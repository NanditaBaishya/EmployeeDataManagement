package com.example.demo.Services;

import com.example.demo.Entities.Employee;
import com.example.demo.Repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EmployeePostService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(String srcid, Employee emp) {
        Optional<Employee> employee = employeeRepository.findById(srcid);
        if(employee.isPresent()) {
            Employee employeeStore = employee.get();
            if(employeeStore.getReportsto() == null) {
                employeeRepository.save(emp);
                return emp;

            } else {
                log.info("Only head of the organization can create an employee");
                return null;
            }

        }
        else {
            log.info("The entered id:{} is not a valid employee",srcid);
            return null;
        }
    }
}

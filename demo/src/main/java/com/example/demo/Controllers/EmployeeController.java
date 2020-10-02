package com.example.demo.Controllers;

import com.example.demo.Entities.Employee;
import com.example.demo.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{myid}/e/{id}")
    public Employee GetEmployee(@PathVariable("myid") String srcid, @PathVariable("id") String id) {
        return employeeService.getEmployee(srcid,id);
    }

    @GetMapping("/{myid}/e/all")
    public List<Employee> GetAllEmployees(@PathVariable("myid") String srcid) {
        return employeeService.getAllEmployees(srcid);
    }

    @GetMapping("/{myid}/e/team")
    public List<Employee> GetMyTeam(@PathVariable("myid") String srcid) {
        return employeeService.getMyTeam(srcid);
    }

    @GetMapping("/{myid}")
    public Employee GetMyDetails(@PathVariable("myid") String srcid) {
        return employeeService.getMyDetails(srcid);
    }


}

package com.example.demo.Controllers;

import com.example.demo.Entities.Employee;
import com.example.demo.Services.EmployeeDeleteService;
import com.example.demo.Services.EmployeePostService;
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

    @Autowired
    EmployeePostService employeePostService;

    @Autowired
    EmployeeDeleteService employeeDeleteService;

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


    @PostMapping("/{myid}/e/create")
    public Employee createEmployee(@PathVariable("myid") String srcid, @RequestBody Employee emp) {
        return employeePostService.createEmployee(srcid,emp);
    }

    @DeleteMapping("/{myid}/e/delete/{id}")
    public Employee deleteEmployee(@PathVariable("myid") String srcid, @PathVariable("id") String targetid) {
        return employeeDeleteService.deleteEmployee(srcid,targetid);
    }

}

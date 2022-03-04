package com.sg.oracle.sg_management.employee.controller;

import com.sg.oracle.sg_management.employee.model.entity.Employee;
import com.sg.oracle.sg_management.employee.model.repository.EmployeeRepository;
import com.sg.oracle.sg_management.utility.Util;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Setter
@Getter
@Named
@ViewScoped
public class EmployeeManagedBean
{
    @Autowired
    private EmployeeRepository employeeRepository;

    private String infoMessage;

    private String inputTCKN;
    private String inputFirstName;
    private String inputLastName;
    private String inputSalary;


    public void deleteByID()
    {
        try
        {
            employeeRepository.deleteById(inputTCKN);

            infoMessage = "Silme başarılı";
        }
        catch (NumberFormatException e)
        {
            infoMessage = Util.createGeneralException(e);
        }
    }

    public void insert()
    {
        try
        {
            Employee employee = new Employee(inputTCKN, inputFirstName, inputLastName, Double.parseDouble(inputSalary));

            Employee savedEmployee = employeeRepository.save(employee);

            infoMessage = savedEmployee.getTCKN() != null ? "Ekleme başarılı." : "Ekleme başarısız.";
        }
        catch (IllegalArgumentException e)
        {
            infoMessage = Util.createGeneralException(e);
        }
    }

    public List<Employee> getEmployeeList()
    {
        return employeeRepository.findAll();
    }
}

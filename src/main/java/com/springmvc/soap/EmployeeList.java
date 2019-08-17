package com.springmvc.soap;

import com.springmvc.entities.Employee;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Employee.class})
public class EmployeeList<T> {
    private List<T> listOfEmployees;

    public EmployeeList() {
        this.listOfEmployees = new ArrayList<T>();
    }

    public EmployeeList(List<T> listOfEmployees) {
        this.listOfEmployees = listOfEmployees;
    }

    @XmlAnyElement
    public List<T> getEmployees(){
        return listOfEmployees;
    }

    public void setListOfEmployees(List<T> listOfEmployees) {
        this.listOfEmployees = listOfEmployees;
    }
}

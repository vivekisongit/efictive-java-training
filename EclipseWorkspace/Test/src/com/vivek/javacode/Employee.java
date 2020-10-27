package com.vivek.javacode;

import java.util.Comparator;

public class Employee implements Comparator{
 String name;
 String designation;
 double salary;
 
 public Employee(String name, String designation, double salary) {
	 this.name=name;
	 this.designation=designation;
	 this.salary=salary;
 }
 
 public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}



public String getDesignation() {
	return designation;
}



public void setDesignation(String designation) {
	this.designation = designation;
}



public double getSalary() {
	return salary;
}



public void setSalary(double salary) {
	this.salary = salary;
}

public int compare(Object emp1, Object emp2) {
	Employee e1=(Employee)emp1; 
	Employee e2=(Employee)emp2; 
	
	int i=0;
	 if(e1.getSalary()<e2.salary) {
		 i=-1;
	 }else if(e1.getSalary()>e2.salary) {
		 i=1;
	 }else {
		 i=0;
	 }
	 return i;	
}

 
}

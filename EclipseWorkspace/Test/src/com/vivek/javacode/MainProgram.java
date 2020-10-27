package com.vivek.javacode;

import java.util.ArrayList;
import java.util.List;

public class MainProgram {
		public static void main(String args) {
			Employee emp1=new Employee("Vivek", "Eng", 5000.00);
			Employee emp2=new Employee("Rakesh", "Eng", 6000.00);
			Employee emp3=new Employee("Mukesh", "Eng", 7000.00);
			Employee emp4=new Employee("Suresh", "Eng", 4000.00);
			
			List<Employee> empList=new ArrayList<Employee>();
			empList.add(emp1);
			empList.add(emp2);
			empList.add(emp3);
			empList.add(emp4);
			
			System.out.println("Employe List: "+empList);
		}
}

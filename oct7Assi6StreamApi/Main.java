package oct7Assi6StreamApi;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> listOfEmployee = new ArrayList<>(
                Arrays.asList(
                        new Employee(101, "rajat", "HR", 20000.0),
                        new Employee(102, "gourav", "HR", 20000.0),
                        new Employee(103, "umesh", "BD", 30000),
                        new Employee(104, "mohit", "BD", 25000)
                )
        );

//        Assignment 1 -------------------------------------------

        System.out.println("give the department you want to filter");
        String department = sc.nextLine();
        List filteredList =  listOfEmployee.stream()
                .filter((e)->e.getDepartment()
                        .equals(department.toUpperCase())).toList();
        filteredList.stream().forEach(System.out::println);

//        ----------------------------------------------------------


//        Assignment 2 ---------------------------------------

        System.out.println("\n -----------------Total salary----------------");
        Double totalSalary = listOfEmployee.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum).get();
        System.out.println(totalSalary);

        System.out.println("\n -----------------Total salary of every Department----------------");
        Map a = listOfEmployee.stream().
                collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));
        System.out.println(a);

//    -------------------------------------------------------------------



//    Assginment 3 ------------------------------

        System.out.println("\n -------------------- All Employees name in upperCase -----------------------");
        listOfEmployee.stream()
                .map(e->e.getName()
                        .toUpperCase())
                .forEach(System.out::println);


    }
}

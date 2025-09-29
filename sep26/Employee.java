package sep26;

import java.util.*;

//2. Create Employee class (id, name, department, salary).
//Sort employees by:
//Department → Name → Salary.
//Salary (descending).
//Use both Comparable and Comparator.
//Use Iterator to traverse.

class SortBySalary implements Comparator <Employeesort> {
    @Override
    public int compare(Employeesort e1, Employeesort e2) {
        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}

class Employeesort implements Comparable <Employeesort>{
    private int id;
    private String name;
    private String department;
    private double salary;
    Employeesort(int id, String name, String department, double salary){
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public String toString(){
        return id + " : " + name + " : " + department + " : " + salary;
    }
    public int compareTo(Employeesort other){
    int depCompare = this.department.compareTo(other.department);
    if(depCompare != 0) return depCompare;

    int nameCompare = this.name.compareTo(other.name);
    if(nameCompare != 0) return nameCompare;

    return Double.compare(this.salary, other.salary);
    }
}


public class Employee{
    public static void main(String[] args) {
        List<Employeesort> employees = new ArrayList<>();

        employees.add(new Employeesort(101, "mohit", "HR", 50000));
        employees.add(new Employeesort(102, "gourav", "IT", 70000));
        employees.add(new Employeesort(103, "rajat", "HR", 60000));
        employees.add(new Employeesort(104, "umesh", "Finance", 80000));
        employees.add(new Employeesort(105, "badal", "IT", 75000));

//        using default sorting
        // using compareTo method --------
        Collections.sort(employees);
        Iterator itr = employees.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println();

//        customize sorting
        //  using compare method ---------
        Collections.sort(employees, new SortBySalary());
        Iterator itr2 = employees.iterator();
        while (itr2.hasNext()){
            System.out.println(itr2.next());
        }
    }
}
package oct1Assi4FilleAndExceptionHandling;

import oct1Assi4FilleAndExceptionHandling.customExceptions.InvalidAgeException;
import oct1Assi4FilleAndExceptionHandling.customExceptions.InvalidMarksException;

import java.io.*;
import java.util.*;

public class StudentManagement {
    Map <Integer, ClassRoom> classes = new HashMap<>();
    Map <Integer, Student> students = new HashMap<>();
    Map <Integer, List<Address>> address = new HashMap<>();

//    writing data in files --------------------
    public void saveStudentToFile(){
        try(BufferedWriter bw = new BufferedWriter( new FileWriter("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct1Assi4FilleAndExceptionHandling\\studentFile.txt", true))){
            for(Student s: students.values()){
                bw.write(s.getId() + "," + s.getName() + "," + s.getMarks() + "," +
                        s.getGender() + "," + s.getAge() + "," + s.getClassId() + "," +
                        s.getPassed() + "," + s.getRank());
                bw.newLine();
            }
            System.out.println("student data save in file");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveAddress(){
        try (BufferedWriter br = new BufferedWriter(new FileWriter("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct1Assi4FilleAndExceptionHandling\\addressFile.txt", true))){
            for(List<Address> list : address.values()){
                for(Address a: list){
                    br.write(a.getId() + "," + a.getPincode() + "," + a.getName() + "," + a.getStudentId());
                    br.newLine();
                }
            }
            br.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void saveClassRoom(){
        try(BufferedWriter br = new BufferedWriter( new FileWriter("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct1Assi4FilleAndExceptionHandling\\classFile.txt", true))){

            for(ClassRoom c: classes.values()){
               br.write(c.getId() + "," + c.getName());
               br.newLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveTopFive(){
        try ( BufferedWriter br = new BufferedWriter(new FileWriter("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct1Assi4FilleAndExceptionHandling\\topFive.txt", true))) {
            List <Student> sorted = new ArrayList<>(students.values());
            sorted.sort(Comparator.comparingInt(Student::getMarks).reversed());

            for(int i=0; i<Math.min(5, sorted.size()); i++){

                    Student s = sorted.get(i);
                    br.write(s.getId() + "," + s.getName() + "," + s.getMarks() + ",Rank:" + s.getRank());
                    br.newLine();

                }

        }catch(IOException e){
            System.out.println(e.getMessage());
            }
        }



//    reading data from files -----------------

    public void getStudentFromFile(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){

            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int marks = Integer.parseInt(data[2]);
                String gender = data[3];
                int age = Integer.parseInt(data[4]);
                int classId = Integer.parseInt(data[5]);

                Student s = new Student(id, name, marks, gender, age, classId);
                if(data.length >=8){
                    s.setPassed(Boolean.parseBoolean(data[6]));
                    s.setRank(Integer.parseInt(data[7]));
                }
                students.put(id, s);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage() +" File not found " + fileName);
        }catch (IOException e){
            System.out.println(e.getMessage() + " IO Exception " + fileName);
        }
    }

    public void getClasses(String filename) {
        classes.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 2) {
                    classes.put(Integer.parseInt(d[0]), new ClassRoom(Integer.parseInt(d[0]), d[1].charAt(0)));
                }
            }
            System.out.println("Classes loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading classes: " + e.getMessage());
        }
    }

    public void getAddresses(String filename) {
        address.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 4) {
                    Address a = new Address(Integer.parseInt(d[0]), Integer.parseInt(d[1]), d[2], Integer.parseInt(d[3]));
                    address.computeIfAbsent(a.getStudentId(), k -> new ArrayList<>()).add(a);
                }
            }
            System.out.println("Addresses loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading addresses: " + e.getMessage());
        }
    }



//    adding student address and classes ---------------

    public void addStudent(Scanner sc){
        try{
            System.out.println("give the student id");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("give the student name");
            String name = sc.nextLine();
            System.out.println("give the student marks");
            int marks = Integer.parseInt(sc.nextLine());
            if(marks<0 || marks>100) throw new InvalidMarksException("give valid marks");
            System.out.println("give the student gender");
            String gender = sc.nextLine();
            System.out.println("give the student age");
            int age = Integer.parseInt(sc.nextLine());
            if(age<0 || age>20) throw new InvalidAgeException("Invalid age");
            System.out.println("give the student classId");
            int classId = Integer.parseInt(sc.nextLine());
            Student s = new Student(id, name, marks, gender, age, classId);
            students.put(id, s);

//            saving data to a file -------
            saveStudentToFile();
        }catch (InvalidAgeException e){
            System.out.println(e.getMessage());
        } catch (InvalidMarksException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Invalid input, try again.");
        }
    }

    public void addClass(Scanner sc) {
        try {
            System.out.print("Enter Class ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Class Name: ");
            String name = sc.nextLine();

            ClassRoom c = new ClassRoom(id, name.charAt(0));
            classes.put(id, c);

//            saving calss in a file ----------------
            saveClassRoom();

            System.out.println("Class added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter correct values.");
            sc.nextLine();
        }
    }

    public void addAddress(Scanner sc) {
        try {
            System.out.print("Enter Address ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Pincode: ");
            int pincode = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter City Name: ");
            String city = sc.nextLine();

            System.out.print("Enter Student ID (to link): ");
            int studentId = sc.nextInt();
            sc.nextLine();

            Address a = new Address(id, pincode, city, studentId);
            address.computeIfAbsent(studentId, k -> new ArrayList<>()).add(a);

//          saving address in file -------------
            saveAddress();
            System.out.println("Address added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter correct values.");
            sc.nextLine();
        }
    }


}

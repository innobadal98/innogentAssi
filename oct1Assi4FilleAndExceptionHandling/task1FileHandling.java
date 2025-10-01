package oct1Assi4FilleAndExceptionHandling;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class task1FileHandling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentManagement stuManagement = new StudentManagement();
        System.out.println("welcome to the website");


       try{
           while (true){
               System.out.println("\n======= MENU =======");
               System.out.println("1. Add Student");
               System.out.println("2. Save Students to File");
               System.out.println("3. Load Students from File");
               System.out.println("4. Show All Students");
               System.out.println("5. Add Class");
               System.out.println("6. Add Address");
               System.out.println("7. Save Classes to File");
               System.out.println("8. Save Addresses to File");
               System.out.println("9. Save Top 5 Students to File");
               System.out.println("10. get all Class Room");
               System.out.println("11. get all Address");
               System.out.println("0. Exit");
               System.out.print("Enter your choice: ");
               String input = sc.nextLine();
               int n = 0;
               try {
                   n = Integer.parseInt(input);
               }catch (NumberFormatException e){
                   System.out.println(e.getMessage());
               }
               switch (n){
                   case 1 : {
//                       data automaticlly save in file also -------------
                       stuManagement.addStudent(sc);
                   } break;
                   case 2 : {
                       stuManagement.saveStudentToFile();
                   } break;
                   case 3: {
                       stuManagement.getStudentFromFile("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct1Assi4FilleAndExceptionHandling\\studentFile.txt");
                       System.out.println("getting data from file");

                   } break;
                   case 4 : {
                       stuManagement.getStudentFromFile("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct1Assi4FilleAndExceptionHandling\\studentFile.txt");
                       for(Student s: stuManagement.students.values()){
                           System.out.println(s);
                       }
                   } break;
                   case 5 : {
                       stuManagement.addClass(sc);
                   } break;
                   case 6 : {
                       stuManagement.addAddress(sc);
                   } break;
                   case 7 : {
                       stuManagement.saveClassRoom();
                   } break;
                   case 8 : {
                       stuManagement.saveAddress();
                   } break;
                   case 9 : {
                       stuManagement.saveTopFive();
                   } break;
                   case 10 : {
                       stuManagement.getClasses("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct1Assi4FilleAndExceptionHandling\\classFile.txt");
                       for(ClassRoom c : stuManagement.classes.values()){
                           System.out.println(c);
                       }
                   } break;
                   case 11 : {
                       stuManagement.getAddresses("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct1Assi4FilleAndExceptionHandling\\addressFile.txt");
                       for (List<Address> add : stuManagement.address.values()){
                           for(Address a : add){
                               System.out.println(a);
                           }
                       }
                   } break;
                   case 0 : {
                       System.out.println("Exiting......");
                       return;
                   }
                   default: {
                       System.out.println("please choose the right menu");
                   }
               }
           }
       }catch (Exception e){
           System.out.println(e);
       }
    }
}

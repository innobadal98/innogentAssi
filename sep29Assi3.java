import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StudentManagment{
    public static final int MAX_AGE = 20;
    public static final int PASSING_MARKS = 50;
    class ClassRoom{
        private int id;
        private char name;

        public ClassRoom(int id, char name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public char getName() {
            return name;
        }
    }

    class Student{
        private int id;
        private String name;
        private int marks;
        private String  gender;
        private int age;
        private int classId;

//        dynamic data
        private boolean passed;
        private int rank;

        public Student(int id, String name, int marks, String gender, int age, int classId) {
            this.id = id;
            this.name = name;
            this.marks = marks;
            this.gender = gender;
            this.age = age;
            this.classId = classId;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", marks=" + marks +
                    ", gender='" + gender + '\'' +
                    ", age=" + age +
                    ", classId=" + classId +
                    ", passed=" + passed +
                    ", rank=" + rank +
                    '}';
        }

        public String getName() {
            return name;
        }

        public int getMarks() {
            return marks;
        }

        public String getGender() {
            return gender;
        }

        public int getAge() {
            return age;
        }

        public int getClassId() {
            return classId;
        }

        public boolean getPassed(){return passed;}
        public void setPassed(boolean passed){
            this.passed = passed;
        }
        public void setRank(int rank){
            this.rank = rank;
        }
    }

    class Address{
        private int id;
        private int pincode;
        private String name;
        private int studentId;

        public Address(int id, int pincode, String name, int studentId) {
            this.id = id;
            this.pincode = pincode;
            this.name = name;
            this.studentId = studentId;
        }

        public int getId() {
            return id;
        }

        public int getPincode() {
            return pincode;
        }

        public String getName() {
            return name;
        }

        public int getStudentId() {
            return studentId;
        }
    }

    Map <Integer, ClassRoom> classes = new HashMap<>();
    Map <Integer, Student> students = new HashMap<>();
    Map <Integer, List <Address>> address = new HashMap<>();

    public void demoData(){
        classes.put(1, new ClassRoom(1, 'A'));
        classes.put(2, new ClassRoom(2, 'B'));
        classes.put(3, new ClassRoom(3, 'C'));
        classes.put(4, new ClassRoom(4, 'D'));
        classes.put(5, new ClassRoom(5, 'E'));

        List <Student> givenStudentLsit = Arrays.asList(
                new Student(1, "mohit", 60, "M", 20,1),
                new Student(2, "sobhit", 40, "M", 10,2),
                new Student(3, "ishita", 80, "F", 15,2),
                new Student(4, "priya", 30, "F", 16,2),
                new Student(5, "napit", 60, "M", 12,4),
                new Student(6, "napit", 50, "M", 14,4),
                new Student(7, "napit", 50, "M", 14,5)
        );

        for(Student stu : givenStudentLsit){
           if(stu.getAge()>MAX_AGE){
               System.out.println(stu.getName() + " is not consider because of age is grater then "+ MAX_AGE);
               continue;
           }
           students.put(stu.getId(), stu);
        }



        List <Address> demoAddress = Arrays.asList(
                new Address(1, 452001, "indore", 1),
                new Address(2, 452001, "indore", 2),
                new Address(3, 452002, "ratlam", 3),
                new Address(4, 452002, "ratlam", 4)
        );

        for(Address add: demoAddress){
            address.computeIfAbsent(add.getStudentId(), k-> new ArrayList<>()).add(add);
        }

        isPass();
        setRanks();

    }

    private void isPass(){
        for(Student s: students.values()){
           if(s.getMarks()>=PASSING_MARKS){
               s.setPassed(true);
           }else{
               s.setPassed(false);
           }
        }
    }
    private void setRanks(){
//        creating sorted list
        List <Student> list = students.values().stream().sorted(Comparator.comparingInt(Student::getMarks).reversed().thenComparing(Student::getName)).collect(Collectors.toList());
        int previusMarks = Integer.MIN_VALUE;
        int rank = 0;
        int currentStudentCount = 0;

        // seting rank -----
        for(Student s : list){
            currentStudentCount ++ ;
            // if students have same marks so giving them same rank ------
            if(previusMarks != s.getMarks()){
                rank = currentStudentCount;
                previusMarks = s.getMarks();
            }
            s.setRank(rank);
        }
    }

    List <Student> applayFilter(Collection <Student> input, String gender, Integer age, Integer classId){
        List <Student> res = new ArrayList<>();
        for(Student s : input){
            if(gender != null && !gender.equalsIgnoreCase(s.getGender())) continue;
            if(age != null && s.getAge()>age) continue;
            if(classId != null && s.getClassId()!=classId) continue;
            res.add(s);
        }
        return res;
    }

    List <Student> findByPincode(Integer pincode, String gender, Integer age, Integer classId){
//        for storing student ids witch is include given pincode
        Set <Integer>  ids = new HashSet<>();
        for(List <Address> add : address.values()){
            for(Address a : add){
                if(a.pincode == pincode) ids.add(a.studentId);
            }
        }

//        creating student list witch is include given pincode
        List <Student> res = new ArrayList<>();
        for(Integer id : ids){
            if(students.containsKey(id)) res.add(students.get(id));
        }
        return applayFilter(res, gender, age, classId);
    }


    List <Student> findByCity(String city, String gender, Integer age, Integer classId){

        Set <Integer> ids = new HashSet<>();
        for(List<Address> add : address.values()){
            for(Address a : add){
                if(a.getName().equalsIgnoreCase(city)) ids.add(a.getStudentId());
            }
        }

        List <Student> res = new ArrayList<>();
        for(Integer id : ids){
            if(students.containsKey(id)){
                res.add(students.get(id));
            }
        }
        return applayFilter(res, gender, age, classId);
    }

    List <Student> findByClass(String gender, Integer age, Integer classId){
        List <Student> res = new ArrayList<>();
        for(Student s : students.values()){
            if(s.getClassId() == classId) res.add(s);
        }
        return applayFilter(res, gender, age, classId);
    }

    List <Student> passedStudent(String gender, Integer age, Integer classId){
        List <Student> res = new ArrayList<>();
        for(Student s : students.values()){
            if(s.getPassed())res.add(s);
        }
        return applayFilter(res, gender, age, classId);
    }

    List <Student> notPassedStudent(String gender, Integer age, Integer classId){
        List <Student> res = new ArrayList<>();
        for(Student s : students.values()){
            if(!s.getPassed())res.add(s);
        }
        return applayFilter(res, gender, age, classId);
    }

//    deleting logic is here -------------
    void deleteStudent(int studentId){
        Student removed = students.remove(studentId);
        if(removed==null){
            System.out.println("Student not found");
        }

//        removing address also
        address.remove(studentId);

//        checking class empty or not
        boolean isClassEmpty = true;
        for(Student s : students.values()){
            if(s.classId == removed.classId){
                isClassEmpty = false;
            }
        }
        if(isClassEmpty){
            classes.remove(removed.classId);
            System.out.println("class id : " + removed.classId + " is now Empty class so class is also deleted");
        }

        System.out.println("student " + removed.name + " record deleted successfully");

    }

    // read students in a paginated way --------
    List <Student> getStudentInPagination(String gender, Integer age, Integer classId, String orderBy , int start, int end){
        List <Student> list = applayFilter(students.values(), gender, age, classId);
//        shorting is here ----------
        if("name".equalsIgnoreCase(orderBy)){
            list.sort(Comparator.comparing(s->s.name));
        }
        if("marks".equalsIgnoreCase(orderBy)){
            list.sort((a,b)->a.marks-b.marks);
        }
        int from = Math.max(0, start-1);
        int to = Math.min(list.size(), end);

        if(from > to){
            System.out.println("no data available");
        }

        return list.subList(from, to);
    }

}

public class sep29Assi3 {
    public static void main(String[] args) {
        StudentManagment stuManagment = new StudentManagment();
        stuManagment.demoData();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Show All Students");
            System.out.println("2. Find by Pincode");
            System.out.println("3. Find by City");
            System.out.println("4. Find by ClassId");
            System.out.println("5. Show Passed Students");
            System.out.println("6. Show Failed Students");
            System.out.println("7. Paginate Students");
            System.out.println("8. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("--------------- All students -------------");
                    for (StudentManagment.Student s : stuManagment.students.values()) {
                        System.out.println(s);
                    }
                    break;

                case 2:
                    System.out.print("Enter pincode: ");
                    int pin = sc.nextInt();
                    for (StudentManagment.Student s : stuManagment.findByPincode(pin, null, null, null)) {
                        System.out.println(s);
                    }
                    break;

                case 3:
                    System.out.print("Enter city: ");
                    String city = sc.nextLine();
                    for (StudentManagment.Student s : stuManagment.findByCity(city, null, null, null)) {
                        System.out.println(s);
                    }
                    break;

                case 4:
                    System.out.print("Enter classId: ");
                    int classId = sc.nextInt();
                    for (StudentManagment.Student s : stuManagment.findByClass(null, null, classId)) {
                        System.out.println(s);
                    }
                    break;

                case 5:
                    for (StudentManagment.Student s : stuManagment.passedStudent(null, null, null)) {
                        System.out.println(s);
                    }
                    break;

                case 6:
                    for (StudentManagment.Student s : stuManagment.notPassedStudent(null, null, null)) {
                        System.out.println(s);
                    }
                    break;

                case 7:
                    System.out.print("Enter gender (M/F or leave empty): ");
                    String gender = sc.nextLine().trim();
                    if (gender.isEmpty()) gender = null;

                    System.out.print("Order by (name/marks or leave empty): ");
                    String orderBy = sc.nextLine().trim();
                    if (orderBy.isEmpty()) orderBy = null;

                    System.out.print("Enter start record: ");
                    int start = sc.nextInt();
                    System.out.print("Enter end record: ");
                    int end = sc.nextInt();

                    for (StudentManagment.Student s : stuManagment.getStudentInPagination(gender, null, null, orderBy, start, end)) {
                        System.out.println(s);
                    }
                    break;

                case 8:
                    System.out.print("Enter student id to delete: ");
                    int id = sc.nextInt();
                    stuManagment.deleteStudent(id);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

}
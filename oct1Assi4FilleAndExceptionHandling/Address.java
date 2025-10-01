package oct1Assi4FilleAndExceptionHandling;

public class Address{
    private int id;
    private int pincode;
    private String name;
    private int studentId;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", pincode=" + pincode +
                ", name='" + name + '\'' +
                ", studentId=" + studentId +
                '}';
    }

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

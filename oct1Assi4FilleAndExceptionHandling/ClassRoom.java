package oct1Assi4FilleAndExceptionHandling;

public class ClassRoom{
    private int id;
    private char name;

    public ClassRoom(int id, char name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public char getName() {
        return name;
    }
}

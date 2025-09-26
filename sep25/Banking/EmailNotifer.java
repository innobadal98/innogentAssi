package sep29.Banking;

public class EmailNotifer implements Notifer {
    public void notify(String accountNo, String msg){
        System.out.println("send Email to account no: " + accountNo + " " + msg);
    }
}

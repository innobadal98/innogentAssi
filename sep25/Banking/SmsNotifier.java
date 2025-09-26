package sep29.Banking;

public class SmsNotifier implements Notifer {
    public void notify(String accountNo, String msg){
        System.out.println("send SMS to account no: "+accountNo + " " + msg);
    }
}

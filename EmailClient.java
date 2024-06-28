package Email_client;


public class EmailClient {
    public static void main(String[] args) {
        EmailAccount emailAccount = new EmailAccount("your_email@gmail.com", "your_password");
        new EmailGUI(emailAccount);
    }
}


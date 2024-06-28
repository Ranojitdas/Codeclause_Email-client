package Email_client;


import java.util.Properties;
import javax.mail.*;

public class EmailReceiver {
    private static final String Session = null;
    private EmailAccount emailAccount;

    public EmailReceiver(EmailAccount emailAccount) {
        this.emailAccount = emailAccount;
    }

    public void receiveEmails() throws MatchException {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(properties, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", emailAccount.getEmail(), emailAccount.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();

        for (Message message : messages) {
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Text: " + message.getContent().toString());
        }

        inbox.close(false);
        store.close();
    }
}


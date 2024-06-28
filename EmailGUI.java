package Email_client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.MessagingException;

public class EmailGUI {
    private EmailAccount emailAccount;
    private JFrame frame;
    private JTextField toField;
    private JTextField subjectField;
    private JTextArea contentArea;

    public EmailGUI(EmailAccount emailAccount) {
        this.emailAccount = emailAccount;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Email Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel toLabel = new JLabel("To:");
        toField = new JTextField();
        panel.add(toLabel);
        panel.add(toField);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectField = new JTextField();
        panel.add(subjectLabel);
        panel.add(subjectField);

        JLabel contentLabel = new JLabel("Content:");
        contentArea = new JTextArea();
        panel.add(contentLabel);
        panel.add(new JScrollPane(contentArea));

        frame.add(panel, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String to = toField.getText();
                String subject = subjectField.getText();
                String content = contentArea.getText();

                EmailSender sender = new EmailSender(emailAccount);
                try {
                    sender.sendEmail(to, subject, content);
                    JOptionPane.showMessageDialog(frame, "Email sent successfully");
                } catch (MatchException ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to send email: " + ex.getMessage());
                }
            }
        });
        frame.add(sendButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

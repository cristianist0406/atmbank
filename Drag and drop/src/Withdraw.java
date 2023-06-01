import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {
    JLabel amount;
    JTextField amountextfield;

    JButton withdraw, back;
    String pinnumber;


    Withdraw(String pinnumber) {
        this.pinnumber = pinnumber;
        this.setSize(600, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("ATM Bank");
        this.setResizable(false);

        ImageIcon image = new ImageIcon("haiatm.jpg");

        amount = new JLabel("Enter the amount you want to withdraw: ");
        amount.setFont(new Font("System", Font.ITALIC, 20));
        amount.setBounds(120, 120, 400, 30);
        amount.setForeground(Color.GRAY);
        amount.setIcon(image);

        amountextfield = new JTextField();
        amountextfield.setBounds(195, 180, 200, 35);
        amountextfield.setForeground(Color.black);

        withdraw = new JButton("Withdraw");
        withdraw.setFocusable(false);
        withdraw.setForeground(Color.BLACK);
        withdraw.setBackground(Color.GRAY);
        withdraw.setBounds(95, 250, 100, 30);
        withdraw.addActionListener(this);

        back = new JButton("Back");
        back.setFocusable(false);
        back.setForeground(Color.BLACK);
        back.setBackground(Color.GRAY);
        back.setBounds(395, 250, 100, 30);
        back.addActionListener(this);


        this.add(amount);
        this.add(amountextfield);
        this.add(withdraw);
        this.add(back);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdraw) {
            String number = amountextfield.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid amount");
            } else {
                Connection conn = new Connection();
                int balance = 0;
                try {
                    ResultSet rs = conn.s.executeQuery("select * from bank where pinnum = '" + pinnumber + "'");
                    while (rs.next()) {
                        if (rs.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                } catch (Exception exception) {
                    System.out.println(exception);
                }
                int amount = Integer.parseInt(number);
                if (amount > balance) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds");
                } else {
                    try {
                        String query = "insert into bank values('" + pinnumber + "','" + date + "', 'Withdraw', '" + number + "')";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "$" + number + " withdrawal successfully");
                        setVisible(false);
                        new Login(pinnumber).setVisible(true);
                    } catch (Exception eee) {
                        System.out.println(eee);
                    }
                }
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login(pinnumber).setVisible(true);
        }
    }
}

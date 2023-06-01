import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {


    JLabel amount;
    JTextField amountextfield;

    JButton deposit, back;
    String pinnumber;


    Deposit(String pinnumber){
        this.pinnumber = pinnumber;
        this.setSize(600,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("ATM Bank");

        ImageIcon image = new ImageIcon("haiatm.jpg");

        amount = new JLabel("Enter the amount you want to deposit: ");
        amount.setFont(new Font("System", Font.ITALIC, 20));
        amount.setBounds(120, 120, 400, 30);
        amount.setForeground(Color.GRAY);
        amount.setIcon(image);

        amountextfield = new JTextField();
        amountextfield.setBounds(195, 180, 200, 35);
        amountextfield.setForeground(Color.black);

        deposit = new JButton("Deposit");
        deposit.setFocusable(false);
        deposit.setForeground(Color.BLACK);
        deposit.setBackground(Color.GRAY);
        deposit.setBounds(95, 250, 100, 30);
        deposit.addActionListener(this);

        back = new JButton("Back");
        back.setFocusable(false);
        back.setForeground(Color.BLACK);
        back.setBackground(Color.GRAY);
        back.setBounds(395, 250, 100, 30);
        back.addActionListener(this);


        this.add(amount);
        this.add(amountextfield);
        this.add(deposit);
        this.add(back);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == deposit){
            String number = amountextfield.getText();
            Date date = new Date();
            if (number.equals("")){
                JOptionPane.showMessageDialog(null, "Invalid amount");
            }else {
                try {
                    Connection conn = new Connection();
                    String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Deposit', '" +number+ "')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "$"+number+" deposited successfully");
                    setVisible(false);
                    new Login(pinnumber).setVisible(true);
                } catch (Exception eee) {
                    System.out.println(eee);
                }
            }

        } else if (e.getSource() == back){
            setVisible(false);
            new Login(pinnumber).setVisible(true);

        }


    }
}

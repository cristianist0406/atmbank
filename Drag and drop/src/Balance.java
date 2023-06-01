import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Balance extends JFrame implements ActionListener {

    JButton back;
    JLabel ybalance;
    String pinnumber;


    Balance(String pinnumber) {
        this.pinnumber = pinnumber;
        this.setSize(500, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("ATM Bank");
        this.setResizable(false);


        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.GRAY);
        back.setBounds(170, 250, 150, 30);
        back.setFocusable(false);
        back.addActionListener(this);


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


        ybalance = new JLabel("Your balance is: " + balance +"$");
        ybalance.setFont(new Font("Arial", Font.BOLD, 16));
        ybalance.setBounds(80, 100, 200, 30);




            this.add(ybalance);
            this.add(back);
            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == back) {
                setVisible(false);
                new Login(pinnumber).setVisible(true);
            }
        }

    }

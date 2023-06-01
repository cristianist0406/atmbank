import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MyFrame extends JFrame implements ActionListener {

    ImageIcon icon;

    JButton sign;
    JButton clear;
    JButton signup;
    JTextField cardtextField;
    JPasswordField pintextfield;

    MyFrame() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("ATM Bank");
        this.setResizable(false);


        JLabel label = new JLabel("Welcome to the ATM!");
        label.setIcon(icon);
        label.setBounds(125, 20, 550, 50);
        label.setFont(new Font("Comic Sans", Font.BOLD, 20));
        label.setForeground(Color.GRAY);


        JLabel cardnum = new JLabel("Card Number: ");
        cardnum.setBounds(70, 120, 200, 50);
        cardnum.setFont(new Font("Comic Sans", Font.BOLD, 18));
        cardnum.setForeground(Color.BLACK);

        cardtextField = new JTextField();
        cardtextField.setBounds(200, 137, 200, 22);
        cardtextField.setFont(new Font("Arial", Font.BOLD, 15));


        JLabel pin = new JLabel("PIN:  ");
        pin.setBounds(160, 180, 100, 50);
        pin.setFont(new Font("Comic Sans", Font.BOLD, 18));
        pin.setForeground(Color.BLACK);

        pintextfield = new JPasswordField();
        pintextfield.setBounds(200, 195, 200, 22);

        sign = new JButton("SIGN IN");
        sign.setFocusable(false);
        sign.setForeground(Color.white);
        sign.setBackground(Color.gray);
        sign.setBounds(150, 250, 80, 25);
        sign.addActionListener(this);

        clear = new JButton("CLEAR");
        clear.setFocusable(false);
        clear.setForeground(Color.white);
        clear.setBackground(Color.gray);
        clear.setBounds(250, 250, 80, 25);
        clear.addActionListener(this);

        signup = new JButton("SIGN UP");
        signup.setFocusable(false);
        signup.setForeground(Color.white);
        signup.setBackground(Color.gray);
        signup.setBounds(150, 280, 180, 25);
        signup.addActionListener(this);


        this.add(label);
        this.add(cardnum);
        this.add(pin);
        this.add(cardtextField);
        this.add(pintextfield);
        this.add(sign);
        this.add(clear);
        this.add(signup);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sign) {
            Connection conn = new Connection();
            String cardnumber = cardtextField.getText();
            String pinnumber = pintextfield.getText();
            String query = "select * from logininfo where cardnumber = '"+cardnumber+"' and pinnum = '"+pinnumber+"'";

            try{
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Login(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                }

            }catch (Exception ae){
                System.out.println(ae);
            }

        } else if (e.getSource() == clear){
            cardtextField.setText("");
            pintextfield.setText("");

        } else if (e.getSource() == signup){
            setVisible(false);
            new Signup().setVisible(true);

        }
    }
}

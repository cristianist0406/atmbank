import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    ImageIcon image;
    JLabel select;

    JButton deposit, withdraw, fastcash, balance, exit;
    String pinnumber;
    Login(String pinnumber){
        this.pinnumber = pinnumber;
        ImageIcon image = new ImageIcon("haiatm.png");

        this.setSize(600,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("ATM Bank");


        select = new JLabel("Please select your choice");
        select.setBounds(165,125, 300,30);
        select.setFont(new Font("Arial", Font.BOLD, 18));
        select.setForeground(Color.GRAY);
        select.setIcon(image);

        deposit = new JButton("Deposit");
        deposit.setBounds(70,215, 200,30);
        deposit.setFont(new Font("Arial", Font.BOLD, 16));
        deposit.setForeground(Color.black);
        deposit.setBackground(Color.gray);
        deposit.setFocusable(false);
        deposit.addActionListener(this);


        withdraw = new JButton("Withdraw");
        withdraw.setBounds(335,215, 200,30);
        withdraw.setFont(new Font("Arial", Font.BOLD, 16));
        withdraw.setForeground(Color.black);
        withdraw.setBackground(Color.gray);
        withdraw.setFocusable(false);
        withdraw.addActionListener(this);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(70,285, 200,30);
        fastcash.setFont(new Font("Arial", Font.BOLD, 16));
        fastcash.setForeground(Color.black);
        fastcash.setBackground(Color.gray);
        fastcash.setFocusable(false);
        fastcash.addActionListener(this);

        balance = new JButton("Balance");
        balance.setBounds(335,285, 200,30);
        balance.setFont(new Font("Arial", Font.BOLD, 16));
        balance.setForeground(Color.black);
        balance.setBackground(Color.gray);
        balance.setFocusable(false);
        balance.addActionListener(this);

        exit = new JButton("Exit");
        exit.setBounds(190, 350, 200, 30);
        exit.setFont(new Font("Arial", Font.BOLD, 16));
        exit.setForeground(Color.black);
        exit.setBackground(Color.gray);
        exit.setFocusable(false);
        exit.addActionListener(this);








        this.add(select);
        this.add(deposit);
        this.add(withdraw);
        this.add(fastcash);
        this.add(balance);
        this.add(exit);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            System.exit(0);
        } else if (e.getSource() == deposit){
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        } else if (e.getSource() == withdraw){
            setVisible(false);
            new Withdraw(pinnumber).setVisible(true);
        } else if (e.getSource() == fastcash){
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);
        } else if (e.getSource() == balance){
            setVisible(false);
            new Balance(pinnumber).setVisible(true);
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;

public class FastCash extends JFrame implements ActionListener {
    ImageIcon image;
    JLabel select;

    JButton deposit, withdraw, fastcash, balance, exit, newamount, newamount1;
    String pinnumber;
    FastCash(String pinnumber){
        this.pinnumber = pinnumber;
        ImageIcon image = new ImageIcon("haiatm.png");

        this.setSize(600,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("ATM Bank");
        this.setResizable(false);


        select = new JLabel("Please select withdrawl amount");
        select.setBounds(165,125, 300,30);
        select.setFont(new Font("Arial", Font.BOLD, 18));
        select.setForeground(Color.GRAY);
        select.setIcon(image);

        deposit = new JButton("$100");
        deposit.setBounds(70,215, 200,30);
        deposit.setFont(new Font("Arial", Font.BOLD, 16));
        deposit.setForeground(Color.black);
        deposit.setBackground(Color.gray);
        deposit.setFocusable(false);
        deposit.addActionListener(this);


        withdraw = new JButton("$250");
        withdraw.setBounds(335,215, 200,30);
        withdraw.setFont(new Font("Arial", Font.BOLD, 16));
        withdraw.setForeground(Color.black);
        withdraw.setBackground(Color.gray);
        withdraw.setFocusable(false);
        withdraw.addActionListener(this);

        fastcash = new JButton("$500");
        fastcash.setBounds(70,285, 200,30);
        fastcash.setFont(new Font("Arial", Font.BOLD, 16));
        fastcash.setForeground(Color.black);
        fastcash.setBackground(Color.gray);
        fastcash.setFocusable(false);
        fastcash.addActionListener(this);

        balance = new JButton("$750");
        balance.setBounds(335,285, 200,30);
        balance.setFont(new Font("Arial", Font.BOLD, 16));
        balance.setForeground(Color.black);
        balance.setBackground(Color.gray);
        balance.setFocusable(false);
        balance.addActionListener(this);

        newamount = new JButton("$900");
        newamount.setBounds(70,350, 200,30);
        newamount.setFont(new Font("Arial", Font.BOLD, 16));
        newamount.setForeground(Color.black);
        newamount.setBackground(Color.gray);
        newamount.setFocusable(false);
        newamount.addActionListener(this);

        newamount1 = new JButton("$1000");
        newamount1.setBounds(335,350, 200,30);
        newamount1.setFont(new Font("Arial", Font.BOLD, 16));
        newamount1.setForeground(Color.black);
        newamount1.setBackground(Color.gray);
        newamount1.setFocusable(false);
        newamount1.addActionListener(this);

        exit = new JButton("Exit");
        exit.setBounds(190, 430, 200, 30);
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
        this.add(newamount);
        this.add(newamount1);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            setVisible(false);
            new Login(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) e.getSource()).getText().substring(1);
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
                Date date = new Date();
                int amountt = Integer.parseInt(amount);
                if (amountt > balance) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds");
                } else {
                    try {
                        String query = "insert into bank values('" + pinnumber + "','" + date + "','WithdrawFC','" + amount + "')";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "$" + amount + " debited successfully");
                        setVisible(false);
                        new Login(pinnumber).setVisible(true);
                    } catch (Exception ye) {
                        System.out.println(ye);
                    }
                }
            }catch (Exception aee){
                System.out.println(aee);
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SingupTWO extends JFrame implements ActionListener {

    JLabel appdetails, accounttype, cardid, numbercard, pininfo, pinid, cardinfo, pininfo2, servicereq;

    JRadioButton savingacc, currentacc;
    JCheckBox atmcard, internetbank, mobbank, emailalert;
    ButtonGroup btgroup, btgroup2;

    JButton sub, cancel;

    String pinnumber;




    SingupTWO(){
        this.setSize(750,610);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("ATM Bank");
        this.setResizable(false);

        appdetails = new JLabel("Account Details");
        appdetails.setForeground(Color.red);
        appdetails.setBounds(280, 45, 600,30);
        appdetails.setFont(new Font("Comic Sans", Font.BOLD, 25));

        accounttype = new JLabel("1. Account Type: ");
        accounttype.setForeground(Color.black);
        accounttype.setBounds(85, 130, 200,30);
        accounttype.setFont(new Font("Arial", Font.BOLD, 15));

        savingacc = new JRadioButton("Saving Account");
        savingacc.setBounds(95, 165, 150,20);
        savingacc.setFont(new Font("Arial", Font.BOLD, 10));

        currentacc = new JRadioButton("Current Account");
        currentacc.setBounds(250,165, 250, 20);
        currentacc.setFont(new Font("Arial", Font.BOLD, 10));

        btgroup = new ButtonGroup();
        btgroup.add(savingacc);
        btgroup.add(currentacc);

        cardid = new JLabel("2. Card ID: ");
        cardid.setFont(new Font("Arial", Font.BOLD, 15));
        cardid.setBounds(85, 200, 200, 30);

        numbercard = new JLabel("XXXX-XXXX-XXXX-7579");
        numbercard.setFont(new Font("Arial", Font.BOLD, 15));
        numbercard.setBounds(250, 200, 200, 30);

        pininfo = new JLabel("3. PIN: ");
        pininfo.setFont(new Font("Arial", Font.BOLD, 15));
        pininfo.setBounds(85, 250, 200, 30);

        pinid = new JLabel("XXXX");
        pinid.setFont(new Font("Arial", Font.BOLD, 15));
        pinid.setBounds(250, 250, 200, 30);

        cardinfo = new JLabel("(Your 14 digit card numbers)");
        cardinfo.setFont(new Font("Arial", Font.BOLD, 10));
        cardinfo.setBounds(85, 220, 200, 30);

        pininfo2 = new JLabel("(Your 4 digit password)");
        pininfo2.setFont(new Font("Arial", Font.BOLD, 10));
        pininfo2.setBounds(85, 265, 200, 30);

        servicereq = new JLabel("4. Service Required:");
        servicereq.setFont(new Font("Arial", Font.BOLD, 15));
        servicereq.setBounds(85, 300, 200, 30);

        atmcard = new JCheckBox("ATM Card");
        atmcard.setBounds(95, 330, 100,20);
        atmcard.setFont(new Font("Arial", Font.BOLD, 10));

        internetbank = new JCheckBox("Internet Banking");
        internetbank.setBounds(250, 330, 150,20);
        internetbank.setFont(new Font("Arial", Font.BOLD, 10));

        mobbank = new JCheckBox("Mobile Banking");
        mobbank.setBounds(95, 355, 145,20);
        mobbank.setFont(new Font("Arial", Font.BOLD, 10));

        emailalert = new JCheckBox("EMAIL & SMS Alert");
        emailalert.setBounds(250, 355, 150,20);
        emailalert.setFont(new Font("Arial", Font.BOLD, 10));

        btgroup2 = new ButtonGroup();
        btgroup2.add(atmcard);
        btgroup2.add(internetbank);
        btgroup2.add(mobbank);
        btgroup2.add(emailalert);

        sub = new JButton("Submit");
        sub.setFocusable(false);
        sub.setBounds(200,435,100,20);
        sub.setFont(new Font("Comic Sans", Font.BOLD, 12));
        sub.setBackground(Color.gray);
        sub.setForeground(Color.black);
        sub.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setFocusable(false);
        cancel.setBounds(380,435,100,20);
        cancel.setFont(new Font("Comic Sans", Font.BOLD, 12));
        cancel.setBackground(Color.gray);
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);










        this.add(accounttype);
        this.add(appdetails);
        this.add(savingacc);
        this.add(currentacc);
        this.add(cardid);
        this.add(numbercard);
        this.add(pininfo);
        this.add(pinid);
        this.add(cardinfo);
        this.add(pininfo2);
        this.add(servicereq);
        this.add(atmcard);
        this.add(internetbank);
        this.add(mobbank);
        this.add(emailalert);
        this.add(sub);
        this.add(cancel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sub){
            String accounttype = null;
            if(savingacc.isSelected()){
                accounttype = "Saving Account";

            } else if (currentacc.isSelected()){
                accounttype = "Current Account";
            }
            Random random = new Random();
            String cardnumber = "" + Math.abs((random.nextLong() % 90000000L) + 3598744500000000L);
            String pinnum = "" + Math.abs((random.nextLong() % 9999L) + 1000L);

            String facility = "";
            if (atmcard.isSelected()){
                facility = facility + "ATM Card";
            } else if (mobbank.isSelected()){
                facility = facility + "Mobile Banking";
            } else if (internetbank.isSelected()){
                facility = facility + "Internet Banking";
            } else if (emailalert.isSelected()){
                facility = facility + "EMAIL & SMS Alerts";
            }

            try{
                if(accounttype.equals("")){
                    JOptionPane.showMessageDialog(null, "Account Type is required");
                } else {
                    Connection conn = new Connection();
                    String query1 = "insert into signuptwo values('"+cardnumber+"', '"+accounttype+"', '" +pinnum+"','" +facility+"')";
                    String query2 = "insert into logininfo values('"+cardnumber+"', '" +pinnum+"')";
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\n Pin: " + pinnum);

                    setVisible(false);
                    new Deposit(pinnum).setVisible(false);


                }

            } catch (Exception ee){
                System.out.println(ee);
            }


        } else if (e.getSource() == cancel){
            System.exit(0);
            new Login(pinnumber).setVisible(true);
        }

    }
}

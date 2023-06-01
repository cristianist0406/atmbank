import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {

    JLabel formnum;

    long ran;
    JTextField nametextfield, secnametextfield, emailtextfield, adresstextfield, countrytextfield, citytextfield;
    JRadioButton male, female;
    JDateChooser dateChooser;

    JButton button;



    Signup(){
        this.setSize(800,610);
        this.setLocation(350,10);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("ATM Bank");
        this.setResizable(false);

        Random random = new Random();
        ran = Math.abs((random.nextLong() % 1000L) + 1000L);

        formnum = new JLabel("Application Form: " + ran);
        formnum.setFont(new Font("Arial", Font.BOLD, 25));
        formnum.setBounds(250,20,600,50);

        JLabel app = new JLabel("Personal Informations");
        app.setFont(new Font("Arial", Font.BOLD, 20));
        app.setBounds(285,90,600,30);
        app.setForeground(Color.RED);

        JLabel name = new JLabel("First name: ");
        name.setFont(new Font("Arial", Font.BOLD, 18));
        name.setBounds(100,170,600,30);
        name.setForeground(Color.GRAY);

        nametextfield = new JTextField();
        nametextfield.setBounds(270,170,400,23);
        nametextfield.setFont(new Font("Raleway", Font.BOLD, 14));

        JLabel secname = new JLabel("Second name: ");
        secname.setFont(new Font("Arial", Font.BOLD, 18));
        secname.setBounds(100,210,600,30);
        secname.setForeground(Color.GRAY);

        secnametextfield = new JTextField();
        secnametextfield.setBounds(270,210,400,23);
        secnametextfield.setFont(new Font("Raleway", Font.BOLD, 14));

        JLabel dob = new JLabel("Date of birth: ");
        dob.setFont(new Font("Arial", Font.BOLD, 18));
        dob.setBounds(100,250,600,30);
        dob.setForeground(Color.GRAY);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(270,250,200,21);


        JLabel gender = new JLabel("Gender: ");
        gender.setFont(new Font("Arial", Font.BOLD, 18));
        gender.setBounds(100,290,600,30);
        gender.setForeground(Color.GRAY);

        male = new JRadioButton();
        male.setBounds(270, 290, 60, 20);
        male.setText("Male");

        female = new JRadioButton();
        female.setBounds(350, 290, 100, 20);
        female.setText("Female");

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        JLabel email = new JLabel("Email Adress: ");
        email.setFont(new Font("Arial", Font.BOLD, 18));
        email.setBounds(100,330,600,30);
        email.setForeground(Color.GRAY);

        emailtextfield = new JTextField();
        emailtextfield.setBounds(270,330,400,23);
        emailtextfield.setFont(new Font("Raleway", Font.BOLD, 14));

        JLabel adress = new JLabel("Adress: ");
        adress.setFont(new Font("Arial", Font.BOLD, 18));
        adress.setBounds(100,370,600,30);
        adress.setForeground(Color.GRAY);

        adresstextfield = new JTextField();
        adresstextfield.setBounds(270,370,400,23);
        adresstextfield.setFont(new Font("Raleway", Font.BOLD, 14));

        JLabel country = new JLabel("Country: ");
        country.setFont(new Font("Arial", Font.BOLD, 18));
        country.setBounds(100,410,600,30);
        country.setForeground(Color.GRAY);

        countrytextfield = new JTextField();
        countrytextfield.setBounds(270,410,400,23);
        countrytextfield.setFont(new Font("Raleway", Font.BOLD, 14));

        JLabel city = new JLabel("City: ");
        city.setFont(new Font("Arial", Font.BOLD, 18));
        city.setBounds(100,450,600,30);
        city.setForeground(Color.GRAY);

        citytextfield = new JTextField();
        citytextfield.setBounds(270,450,400,23);
        citytextfield.setFont(new Font("Raleway", Font.BOLD, 14));

        button = new JButton("NEXT");
        button.setBounds(310,480,200,20);
        button.setBackground(Color.gray);
        button.setForeground(Color.BLACK);
        button.addActionListener(this);
        button.setFocusable(true);




        this.add(formnum);
        this.add(app);
        this.add(name);
        this.add(secname);
        this.add(dob);
        this.add(gender);
        this.add(email);
        this.add(adress);
        this.add(country);
        this.add(city);
        this.add(nametextfield);
        this.add(secnametextfield);
        this.add(dateChooser);
        this.add(male);
        this.add(female);
        this.add(emailtextfield);
        this.add(adresstextfield);
        this.add(countrytextfield);
        this.add(citytextfield);
        this.add(button);
        this.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formnum = "" + ran;
        String name = nametextfield.getText();
        String secname = secnametextfield.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()){
            gender = "Male";
        }else if (female.isSelected()){
            gender = "Female";
        }
        String email = emailtextfield.getText();
        String adress = adresstextfield.getText();
        String country = countrytextfield.getText();
        String city = citytextfield.getText();
        if (e.getSource() == button){
            System.out.println("Signup completed");
        }

        try {
            if (name.equals("")){
                JOptionPane.showMessageDialog(null, "Name is required");
            }else {
                Connection c = new Connection();
                String query = "insert into signup values('"+formnum+"','"+name+"', '"+secname+"', '"+dob+"', '"+gender+"', '"+email+"', '"+adress+"', '"+country+"', '"+city+"')";
                c.s.executeUpdate(query);

                setVisible(false);
                new SingupTWO().setVisible(true);
            }
        }catch (Exception ee){
            System.out.println(ee);
        }

    }
}

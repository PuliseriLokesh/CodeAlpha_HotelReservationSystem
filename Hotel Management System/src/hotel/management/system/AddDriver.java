
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AddDriver  extends JFrame implements ActionListener{
        JTextField tfname, tfcompany, tfage, tfmodel, tflocation;
        JComboBox gendercombo, availablecombo;
        JButton addR, cancel;
    AddDriver() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Drivers");
        heading.setFont(new Font("Raleway", Font.BOLD, 18));
        heading.setBounds(150,10,200,20);
        add(heading);
        
        JLabel lbname = new JLabel("Name");
        lbname.setFont(new Font("Raleway", Font.PLAIN, 16));
        lbname.setBounds(60,70,120,30);
        add(lbname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 70, 150, 30);
        add(tfname);
        
        JLabel lbage = new JLabel("Age");
        lbage.setFont(new Font("Raleway", Font.PLAIN, 16));
        lbage.setBounds(60,110,120,30);
        add(lbage);
        
         tfage = new JTextField();
        tfage.setBounds(200, 110, 150, 30);
        add(tfage);
        
        
        JLabel lbgender = new JLabel("Gender");
        lbgender.setFont(new Font("Raleway", Font.PLAIN, 16));
        lbgender.setBounds(60,150,120,30);
        add(lbgender);
        
        String genderOptions[] = {"Male", "Female"};
        gendercombo = new JComboBox(genderOptions);
        gendercombo.setBounds(200,150, 150, 30);
        gendercombo.setBackground(Color.WHITE);
        add(gendercombo);
        
        JLabel lbcar = new JLabel("Car Company");
        lbcar.setFont(new Font("Raleway", Font.PLAIN, 16));
        lbcar.setBounds(60,190,120,30);
        add(lbcar);
        
        tfcompany = new JTextField();
        tfcompany.setBounds(200, 190, 150, 30);
        add(tfcompany);
        
        JLabel lbmodel = new JLabel("Car Model");
        lbmodel.setFont(new Font("Raleway", Font.PLAIN, 16));
        lbmodel.setBounds(60,230,120,30);
        add(lbmodel);
        
        tfmodel = new JTextField();
        tfmodel.setBounds(200, 230, 150, 30);
        add(tfmodel);
        
        JLabel lbavailable = new JLabel("Available");
        lbavailable.setFont(new Font("Raleway", Font.PLAIN, 16));
        lbavailable.setBounds(60,270,120,30);
        add(lbavailable);
        
        String availableOptions[] = {"Available", "Busy"};
        availablecombo = new JComboBox(availableOptions);
        availablecombo.setBounds(200,270, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
         JLabel lblocation = new JLabel("Location");
        lblocation.setFont(new Font("Raleway", Font.PLAIN, 16));
        lblocation.setBounds(60,310,120,30);
        add(lblocation);
        
        tflocation = new JTextField();
        tflocation.setBounds(200, 310, 150, 30);
        add(tflocation);
        
        addR = new JButton("Book Driver");
        addR.setBounds(60, 370, 130, 30);
        addR.setBackground(Color.BLACK);
        addR.setForeground(Color.WHITE);
        addR.addActionListener(this);
        add(addR);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(220, 370, 130, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 300);
        add(image);
        
        setBounds(300, 200, 980, 470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == addR) {
            String name = tfname.getText(); 
            String age = (String) tfage.getText();
            String gender = (String) gendercombo.getSelectedItem();
            String company = tfcompany.getText();
            String brand = (String) tfmodel.getText();
            String available = (String) availablecombo.getSelectedItem();
            String location = tflocation.getText();
            
            try {
                Conn conn = new Conn();
                String query = "insert into driver values('"+name+"', '"+age+"', '"+gender+"', '"+company+"', '"+brand+"', '"+available+"', '"+location+"')";
                conn.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "New Driver Added Successfully");
            } catch(Exception e) {
                e.printStackTrace();
            }
            
        } else if(ae.getSource() == cancel) {
            setVisible(false);
        }
    }
    public static void main(String args[]) {
        new AddDriver();
    }
}

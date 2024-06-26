
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
public class AddCustomer extends JFrame implements ActionListener{
            JComboBox comboid;
            JTextField tfnumber, tfname, tfcountry, tfdeposit;
            JRadioButton rmale, rfemale;
            Choice croom;
            JLabel checkintime;
            JButton add, back;
            
             AddCustomer() {
       setLayout(null);
       getContentPane().setBackground(Color.WHITE);
                 
        JLabel text = new JLabel("New Customer Form");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(100,20,300,30);
        add(text);
                 
        JLabel lbid = new JLabel("ID");
        lbid.setFont(new Font("Raleway", Font.BOLD, 20));
        lbid.setBounds(35,80,100,20);
        add(lbid);
        
        String options[] = {"Aadhar card", "Passport", "Driving License", "Voter-id card", "Ration card"};
        comboid = new JComboBox(options);
        comboid.setBounds(200, 80, 150, 25);
        comboid.setBackground(Color.WHITE);
        add(comboid);
        
        JLabel lbnumber = new JLabel("Number");
        lbnumber.setFont(new Font("Raleway", Font.BOLD, 20));
        lbnumber.setBounds(35,120,100,20);
        add(lbnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        add(tfnumber);
        
         JLabel lbname = new JLabel("Name");
        lbname.setFont(new Font("Raleway", Font.BOLD, 20));
        lbname.setBounds(35,160,100,20);
        add(lbname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);
        
         JLabel lbgender = new JLabel("Gender");
        lbgender.setFont(new Font("Raleway", Font.BOLD, 20));
        lbgender.setBounds(35,200,100,20);
        add(lbgender);
        
        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200,200,60, 25);
        add(rmale);
        
         rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270,200,100, 25);
        add(rfemale);
        
        ButtonGroup bggender = new ButtonGroup();
        bggender.add(rmale);
        bggender.add(rfemale);
        
          JLabel lbcountry = new JLabel("Country");
        lbcountry.setFont(new Font("Raleway", Font.BOLD, 20));
        lbcountry.setBounds(35,240,100,20);
        add(lbcountry);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);
        
         JLabel lbroom = new JLabel("Room Number");
        lbroom.setFont(new Font("Raleway", Font.BOLD, 20));
        lbroom.setBounds(35,280,150,20);
        add(lbroom);
                 
        croom = new Choice();
        croom.setBounds(200, 280, 150, 25);
        add(croom);
        
        try {
            Conn conn = new Conn();
            String query = "select * from room where availability = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()) {
                croom.add(rs.getString("roomnumber"));
            } 
        } catch(Exception e) {
                    e.printStackTrace();
                    }
        
        JLabel lbtime = new JLabel("Checkin time");
        lbtime.setFont(new Font("Raleway", Font.BOLD, 20));
        lbtime.setBounds(35,320,150,20);
        add(lbtime);
        
        Date date = new Date();
        
        checkintime = new JLabel(""+ date);
        checkintime.setFont(new Font("Raleway", Font.BOLD, 14));
        checkintime.setBounds(200,320,183,25);
        add(checkintime);
        
          JLabel lbdeposit = new JLabel("Deposit");
        lbdeposit.setFont(new Font("Raleway", Font.BOLD, 20));
        lbdeposit.setBounds(35,360,100,20);
        add(lbdeposit);
        
        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        add(tfdeposit);
        
        add = new JButton("Add");
        add.setBounds(50, 410, 120, 30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(200, 410, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 300, 400);
        add(image);
        
         setBounds(350, 200, 800, 550);
         setVisible(true);
                 
             }
    
             
    public void actionPerformed(ActionEvent ae) {
                 if(ae.getSource() == add) {
                     String id = (String) comboid.getSelectedItem();
                     String number = tfnumber.getText();
                     String name = tfname.getText();
                     String gender = null;
                     
                     if(rmale.isSelected()) {
                         gender = "Male";
                     } else {
                         gender = "Female";
                     }
                     
                     String country = tfcountry.getText();
                     String room = croom.getSelectedItem();
                     String time = checkintime.getText();
                     String deposit = tfdeposit.getText();
                     
                     try {
                         Conn conn = new Conn();
                         String query1 = "insert into customer values('"+id+"', '"+number+"', '"+name+"', '"+gender+"', '"+country+"','"+room+"', '"+time+"', '"+deposit+"')";
                         String query2 = "update room set availability = 'Occupied' where roomnumber = '"+room+"'";
                         conn.s.executeUpdate(query1);
                         conn.s.executeUpdate(query2);
                         
                         JOptionPane.showMessageDialog(null, "New Customer added successfully");
                         
                         setVisible(false);
                         new Reception().setVisible(true);
                         
                     } catch(Exception e) {
                         e.printStackTrace();
                     }
                 } else if(ae.getSource() == back) {
                     setVisible(false);
                     new Reception().setVisible(true);
                 }
             }
    public static void main(String args[]) {
        new AddCustomer();
    }
}

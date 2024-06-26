
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateRoom extends JFrame implements ActionListener{
         Choice ccustomer;
         JButton check, update, back;
         JTextField tfroom, tfavailable,tfstatus, tfAmtPaid, tfAmtPending;
    UpdateRoom() {
        
        JLabel text = new JLabel("Update Room Staus");
        text.setFont(new Font("Raleway", Font.BOLD, 25));
        text.setBounds(30, 20, 250, 30);
        text.setForeground(Color.BLUE);
        add(text);
        
        
        JLabel lbid = new JLabel("Customer ID");
        lbid.setBounds(30, 80, 100, 20);
        add(lbid);
        
        ccustomer = new Choice();
        ccustomer.setBounds(200, 80, 150, 25);
        add(ccustomer);
        
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from customer");
            while(rs.next()) {
            ccustomer.add(rs.getString("number"));
        }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        JLabel lbroom = new JLabel("Room Number");
        lbroom.setBounds(30, 130, 100, 20);
        add(lbroom);
        
        tfroom = new JTextField();
        tfroom.setBounds(200,130,150, 25);
        add(tfroom);
        
        JLabel lbavailable = new JLabel("Availability");
        lbavailable.setBounds(30, 180, 100, 20);
        add(lbavailable);
        
        tfavailable = new JTextField();
        tfavailable.setBounds(200,180,150, 25);
        add(tfavailable);
        
        JLabel lbtime = new JLabel("Cleaning Status");
        lbtime.setBounds(30, 230, 100, 20);
        add(lbtime);
        
        tfstatus = new JTextField();
        tfstatus.setBounds(200,230,150, 25);
        add(tfstatus);
        
       
        check = new JButton("CHECK");
        check.setBounds(30,300,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("UPDATE");
        update.setBounds(150,300,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("BACK");
        back.setBounds(270,300,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        
        setLayout(null);
        setBounds(300, 200, 980, 450);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
    }
     public void actionPerformed(ActionEvent ae) {
     if(ae.getSource() == check) {
          String id = ccustomer.getSelectedItem();
          String query = "select * from customer where number = '"+id+"'";
          
          try {
              Conn conn = new Conn();
              ResultSet rs = conn.s.executeQuery(query);
              while(rs.next()) {
                  tfroom.setText(rs.getString("room"));
              }
              
              ResultSet rs1 = conn.s.executeQuery("select * from room where roomnumber = '"+tfroom.getText()+"'");
              while(rs1.next()) {
                 tfavailable.setText(rs1.getString("availability"));
                 tfstatus.setText(rs1.getString("cleaning_status"));
              }
          } catch(Exception e) {
              e.printStackTrace();
     }
      } else if(ae.getSource() == update) {
          String number = ccustomer.getSelectedItem();
          String room = tfroom.getText();
          String available = tfavailable.getText();
          String status = tfstatus.getText();
         
          
          try {
              Conn c = new Conn();
              c.s.executeUpdate("update room set availability = '"+available+"', cleaning_status = '"+status+"' where roomnumber = '"+room+"'");
        
          JOptionPane.showMessageDialog(null, "Data Updated Successfully");
          setVisible(false);
          new Reception().setVisible(true);
          } catch(Exception e) {
              e.printStackTrace();
          }
      } else {
          setVisible(false);
          new Reception().setVisible(true);
      }
}
    
    public static void main(String args[]) {
        new UpdateRoom();
    }
}

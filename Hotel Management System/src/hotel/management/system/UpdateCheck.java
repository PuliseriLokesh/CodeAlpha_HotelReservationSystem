
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateCheck extends JFrame implements ActionListener{
         Choice ccustomer;
         JButton check, update, back;
         JTextField tfroom, tfname,tftime, tfAmtPaid, tfAmtPending;
    UpdateCheck() {
        
        JLabel text = new JLabel("Update Staus");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(90, 20, 200, 30);
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
        lbroom.setBounds(30, 120, 100, 20);
        add(lbroom);
        
        tfroom = new JTextField();
        tfroom.setBounds(200,120,150, 25);
        add(tfroom);
        
        JLabel lbname = new JLabel("Name");
        lbname.setBounds(30, 160, 100, 20);
        add(lbname);
        
        tfname = new JTextField();
        tfname.setBounds(200,160,150, 25);
        add(tfname);
        
        JLabel lbtime = new JLabel("CheckIn Time");
        lbtime.setBounds(30, 200, 100, 20);
        add(lbtime);
        
        tftime = new JTextField();
        tftime.setBounds(200,200,150, 25);
        add(tftime);
        
        JLabel lbamtP = new JLabel("Amount Paid");
        lbamtP.setBounds(30, 240, 100, 20);
        add(lbamtP);
        
        tfAmtPaid = new JTextField();
        tfAmtPaid.setBounds(200,240,150, 25);
        add(tfAmtPaid);
        
        JLabel lbpending = new JLabel("Pending Amount");
        lbpending.setBounds(30, 280, 100, 20);
        add(lbpending);
        
        tfAmtPending = new JTextField();
        tfAmtPending.setBounds(200,280,150, 25);
        add(tfAmtPending);
        
        check = new JButton("CHECK");
        check.setBounds(30,340,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("UPDATE");
        update.setBounds(150,340,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("BACK");
        back.setBounds(270,340,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        
        setLayout(null);
        setBounds(300, 200, 980, 500);
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
                  tfname.setText(rs.getString("name"));
                  tftime.setText(rs.getString("checkintime"));
                  tfAmtPaid.setText(rs.getString("deposit"));
              }
              
              ResultSet rs1 = conn.s.executeQuery("select * from room where roomnumber = '"+tfroom.getText()+"'");
              while(rs1.next()) {
                  String price = rs1.getString("price");
                  int amtpaid = Integer.parseInt(price) - Integer.parseInt(tfAmtPaid.getText());
                  tfAmtPending.setText("" + amtpaid);
              }
          } catch(Exception e) {
              e.printStackTrace();
     }
      } else if(ae.getSource() == update) {
          String number = ccustomer.getSelectedItem();
          String room = tfroom.getText();
          String name = tfname.getText();
          String checkin = tftime.getText();
          String deposit = tfAmtPaid.getText();
          
          try {
              Conn c = new Conn();
              c.s.executeUpdate("update customer set room = '"+room+"', name = '"+name+"', checkintime = '"+checkin+"', deposit = '"+deposit+"' where number = '"+number+"'");
        
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
        new UpdateCheck();
    }
}

package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.*;
public class PickupService extends JFrame implements ActionListener{
       JTable table;
       JButton back, submit;
       Choice carType;
    PickupService() {
        setLayout(null);
        setBounds(300, 200, 1000, 600);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel text = new JLabel("Pickup Service");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(400,30,200,30);
        add(text);
        
         JLabel lbbed = new JLabel("Type Of Car");
        lbbed.setBounds(50,100,100,20);
        add(lbbed);
        
        carType =  new Choice();
        carType.setBounds(150, 100, 200, 25);
        add(carType);
        
        try {
            Conn c = new Conn();
        ResultSet rs = c.s.executeQuery("select * from driver");
        while(rs.next()) {
            carType.add(rs.getString("brand"));
        }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
      
        
        JLabel l1 = new JLabel("Name");
        l1.setBounds(30, 160, 100, 20);
        add(l1);
        
         JLabel l2 = new JLabel("Age");
        l2.setBounds(200, 160, 100, 20);
        add(l2);
        
         JLabel l3 = new JLabel("Gender");
        l3.setBounds(330, 160, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Company");
        l4.setBounds(460, 160, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("brand");
        l5.setBounds(630, 160, 100, 20);
        add(l5);
        
        JLabel l6 = new JLabel("Availability");
        l6.setBounds(740, 160, 100, 20);
        add(l6);
        
        JLabel l7 = new JLabel("Location");
        l7.setBounds(890, 160, 100, 20);
        add(l7);
                
        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
        add(table);
        
        back = new JButton("BACK");
        back.setBounds(500,520,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        submit = new JButton("SUBMIT");
        submit.setBounds(300,520,120,30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        
        try {
            Conn conn = new Conn();
            ResultSet rs1 = conn.s.executeQuery("select * from driver");
            table.setModel(DbUtils.resultSetToTableModel(rs1));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submit) {
            try {
                Conn conn = new Conn();
                String query1 = "select * from driver where brand = '"+carType.getSelectedItem()+"'";
               
                ResultSet rs;
                    rs = conn.s.executeQuery(query1);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
        setVisible(false);
        new Reception().setVisible(true);
        }
    }
  public static void main(String args[]) {
      new PickupService();
  }
}

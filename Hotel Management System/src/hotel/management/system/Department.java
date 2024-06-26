package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.*;
public class Department extends JFrame implements ActionListener{
       JTable table;
       JButton back;
    Department() {
        setLayout(null);
        setBounds(400, 200, 700, 480);
        getContentPane().setBackground(Color.WHITE);
       
        
        JLabel l1 = new JLabel("Department");
        l1.setBounds(180, 10, 100, 20);
        add(l1);
        
         JLabel l2 = new JLabel("Budget");
        l2.setBounds(420, 10, 100, 20);
        add(l2);
        
       
                
        table = new JTable();
        table.setBounds(0, 50, 700, 350);
        add(table);
        
        back = new JButton("BACK");
        back.setBounds(280,400,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from department");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Reception().setVisible(true);
    }
  public static void main(String args[]) {
      new Department();
  }
}

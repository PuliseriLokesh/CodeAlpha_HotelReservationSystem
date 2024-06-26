package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.*;
public class SearchRoom extends JFrame implements ActionListener{
       JTable table;
       JButton back, submit;
       JComboBox combobed;
       JCheckBox available;
    SearchRoom() {
        setLayout(null);
        setBounds(300, 200, 1000, 600);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel text = new JLabel("Search for Room");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(400,30,200,30);
        add(text);
        
         JLabel lbbed = new JLabel("Bed Type");
        lbbed.setBounds(50,100,100,20);
        add(lbbed);
        
        combobed = new JComboBox(new String[] {"Single Bed", "Double Bed"});
        combobed.setBounds(150, 100, 150, 25);
        combobed.setBackground(Color.WHITE);
        add(combobed);
        
        available = new JCheckBox("Only display Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.WHITE);
        add(available);
        
        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(50, 160, 100, 20);
        add(l1);
        
         JLabel l2 = new JLabel("Availability");
        l2.setBounds(270, 160, 100, 20);
        add(l2);
        
         JLabel l3 = new JLabel("Cleaning Status");
        l3.setBounds(450, 160, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Price");
        l4.setBounds(670, 160, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Bed-type");
        l5.setBounds(870, 160, 100, 20);
        add(l5);
                
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
            ResultSet rs = conn.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submit) {
            try {
                Conn conn = new Conn();
                String query1 = "select * from room where bed_type = '"+combobed.getSelectedItem()+"'";
                String query2 = "select * from room where availability = 'Available' AND bed_type = '"+combobed.getSelectedItem()+"'";
                
                ResultSet rs;
                if(available.isSelected()) {
                    rs = conn.s.executeQuery(query2);
                } else {
                    rs = conn.s.executeQuery(query1);
                }
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
      new SearchRoom();
  }
}

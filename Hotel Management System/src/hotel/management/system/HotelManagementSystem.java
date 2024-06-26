package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class HotelManagementSystem extends JFrame implements ActionListener{
       JButton next;
       HotelManagementSystem() {
           setSize(1366, 565);
           setLocation(100, 100);
           setLayout(null);
           
           ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/wallpaper.jpg"));  
           JLabel image = new JLabel(i1);
           image.setBounds(0,0,1366, 565);
           add(image);
           
           JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM");
           text.setBounds(20, 430, 1000, 90);
           text.setFont(new Font("serif", Font.BOLD, 50));
           text.setForeground(Color.WHITE);
           image.add(text);
           
           next = new JButton("Next");
           next.setBounds(1150, 450, 150, 50);
           next.setBackground(Color.WHITE);
           next.setForeground(Color.MAGENTA);
           next.setFont(new Font("serif", Font.PLAIN, 24));
           next.addActionListener(this);
           image.add(next);
           
           setVisible(true);
           
           while(true) { 
               
           text.setVisible(false);
               try{
                   Thread.sleep(500);
               } catch(Exception e) {
               e.printStackTrace();
           } 
               text.setVisible(true);
                try{
                   Thread.sleep(500);
               } catch(Exception e) {
               e.printStackTrace();
               }
           }      
       }
       
       public void actionPerformed(ActionEvent ae) {
           setVisible(false);
           new Login().setVisible(true);
       }
 
    public static void main(String[] args) {
        new HotelManagementSystem();
    }
    
}

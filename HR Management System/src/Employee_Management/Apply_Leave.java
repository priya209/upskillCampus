/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee_Management;

/**
 *
 * @author priya
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Apply_Leave extends JFrame implements ActionListener
{
   JLabel l1,l2,l3,l4,l5,l6,l7,l8;
   JButton bt1,bt2;
   JPanel p1,p2,p3;
   JTextField tf1,tf2,tf3,tf4;
   Font f,f1;
   Choice ch1,ch2;
   
   Apply_Leave()
   {
      super("Apply Employee Leave");
      setLocation(300, 100);
      setSize(800,450);
      setResizable(false);
      
      f = new Font("Arial",Font.BOLD,25);
      f1 = new Font("Arial",Font.BOLD,16);
      
      l1 = new JLabel("Apply Employee Leave");
      l2 = new JLabel("Select Employee ID");
      l3 = new JLabel("Name");
      l4 = new JLabel("Email");
      l5 = new JLabel("Start Date");
      l6 =  new JLabel("End Date");
      l7 = new JLabel("Leave Reason");
      
      tf1 = new JTextField();
      tf2 = new JTextField();
      tf3 = new JTextField();
      tf4 = new JTextField();
      
      bt1 = new JButton("submit");
      bt2 = new JButton("Close");
      
      bt1.addActionListener(this);
      bt2.addActionListener(this);
      
      bt2.setBackground(Color.RED);
      bt1.setBackground(Color.BLACK);
      
      bt1.setForeground(Color.WHITE);
      bt2.setForeground(Color.WHITE);
      
      ch1 = new Choice();
      
      try
      {
        ConnectionClass obj = new ConnectionClass();
        String q = "Select*  from employee";
        ResultSet rest = obj.stmt.executeQuery(q);
        while(rest.next())
        {
           ch1.add(rest.getString("Eid"));
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
      ch2 =  new Choice();
      ch2.add("Employee Health issue");
      ch2.add("Family Member Health issue");
      ch2.add("Family Member Health Issue");
      ch2.add("Function/celebration");
      ch2.add("party");
      ch2.add("Personal Things");
      ch2.add("Others");
      
      l1.setHorizontalAlignment(JLabel.CENTER);
      
      l1.setFont(f1);
      l2.setFont(f1);
      l3.setFont(f1);
      l4.setFont(f1);
      l5.setFont(f1);
      l6.setFont(f1);
      l7.setFont(f1);
      ch1.setFont(f1);
      ch2.setFont(f1);
      
      tf1.setFont(f1);
      tf2.setFont(f1);
      tf3.setFont(f1);
      
      
      tf4.setFont(f1);
      
      bt1.setFont(f1);
      bt2.setFont(f1);
      
      tf1.setEditable(false);
      tf2.setEditable(false);
      
      p1 = new JPanel();
      p1.setLayout(new GridLayout(1,1,10,10));
      p1.add(l1);
      
      p2 = new JPanel();
      p2.setLayout(new GridLayout(7,2,10,10));
      p2.add(l2);
      p2.add(ch1);
      p2.add(l3);
      p2.add(tf1);
      p2.add(l4);
      p2.add(tf2);
      p2.add(l5);
      p2.add(tf3);
      p2.add(l6);
      p2.add(tf4);
      p2.add(l7);
      p2.add(ch2);
      p2.add(bt1);
      p2.add(bt2);
      
      p3 = new JPanel();
      p3.setLayout(new GridLayout(1,1,10,10));
      
      ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Employee_management/icon/update.png"));
      Image img1 = img.getImage().getScaledInstance(200,350,Image.SCALE_DEFAULT);
      ImageIcon ic1 = new ImageIcon(img1);
      l8 = new JLabel(ic1);
      
      p3.add(l8);
      
      setLayout(new BorderLayout(10,10));
      add(p1,"North");
      add(p3,"Center");
      add(p2,"West");
      
      ch1.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent arg0)
         {
          try
          {
             ConnectionClass obj2 = new ConnectionClass();
             String eid = ch1.getSelectedItem();
             String q2 = "select* from employee where Eid='"+eid+"';";
             ResultSet rest1 = obj2.stmt.executeQuery(q2);
             while(rest1.next())
             {
                tf1.setText(rest1.getString("name"));
                tf2.setText(rest1.getString("email"));
             }
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
          }
         }
      }
      );    
   }
   public void actionPerformed(ActionEvent  ev)
   {
       if(ev.getSource()==bt1)
       {
           String eid = ch1.getSelectedItem();
           String name = tf1.getText();
           String email =tf2.getText();
           String startdt = tf3.getText();
           String enddt = tf4.getText();
           String reason = ch2.getSelectedItem();
           String apply_dt = new java.util.Date().toString();
           
           try
           {
              ConnectionClass obj1 = new ConnectionClass();
              String q1 = "insert into apply_leave values('"+eid+"','"+name+"','"+email+"','"+startdt+"','"+enddt+"','"+reason+"','"+apply_dt+"');" ;
              int aa = obj1.stmt.executeUpdate(q1);
              
              if(aa==1)
              {
                JOptionPane.showMessageDialog(null,"your data successfully updated");
                this.setVisible(false);   
              }
              else
              {
                JOptionPane.showMessageDialog(null, "Please fill all details carefully.");
              }
           }
           catch(Exception ex)
           {
             ex.printStackTrace(); 
           }
                   
                   
       }
       if(ev.getSource()== bt2)
       {
          JOptionPane.showMessageDialog(null,"are you sure to close?");
          this.setVisible(false);
       }
   }
   public static void main(String args[])
   {
      new Apply_Leave().setVisible(true);
   }
      
}


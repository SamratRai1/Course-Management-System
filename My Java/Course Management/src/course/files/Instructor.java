package course.files;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.*;

@SuppressWarnings("serial")
public class Instructor extends JFrame implements ActionListener
{
	
	JLabel welcome, studentName, marks;
	JButton addMarks, checkStudents, logout, title, search, giveMarks, back;
	String username;
	JFrame marking, checkStudent;
	JTextField studentId, nameStudent, marksGrade;
	JComboBox<String> dropdown;
	String temp;
	
	
	DatabaseConnection  conn = new DatabaseConnection();
	Statement st = conn.getConn();
	
	
	Instructor(String username){
		
		this.username = username;
		this.setTitle("Instructor Panel");
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		Statement st = conn.getConn();
		ResultSet rs;
	
		try {
	
			rs = st.executeQuery("SELECT * FROM login WHERE username='"+this.username+"' AND indicator='Teacher'");
			
			if(rs.next()) {

				title = new JButton("Welcome to your Dashboard: "+ rs.getString("username"));
				title.setFont(new Font("serif", Font.PLAIN, 16));
				title.setBounds(20, 20, 338, 40);
				title.setFocusable(false);
				title.setEnabled(false);
				title.setFont(new Font("serif", Font.PLAIN, 18));
				UIManager.put("Button.disabledText", Color.BLACK);
				this.add(title);
	
			}
			
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
		addMarks = new JButton("Student Marking");
		addMarks.setBounds(110,80,150,40);
		addMarks.setFocusable(false);
		addMarks.addActionListener(this);
		addMarks.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(addMarks);
		
		logout = new JButton("Logout");
		logout.setBounds(250,200,100,30);
		logout.setFocusable(false);
		logout.addActionListener(this);
		logout.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(logout);
		
		this.setVisible(true);
		
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == addMarks) {
			
			
			this.dispose();
			marking = new JFrame();
			marking.setSize(400,400);
			marking.setLayout(null);
			marking.setLocationRelativeTo(null);
			marking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			
			studentId = new JTextField();
			studentId.setBounds(10,20, 250, 30);
			studentId.setFont(new Font("serif", Font.PLAIN, 16));
			marking.add(studentId);
			
			search = new JButton("Search");
			search.setBounds(270, 20, 100, 28);
			search.addActionListener(this);
			search.setFont(new Font("serif", Font.PLAIN, 16));
			marking.add(search);
			
			String[] modules = null;
			
			try {
				ResultSet count = st.executeQuery("SELECT COUNT(DISTINCT module_name) AS count FROM assigned WHERE module_teacher = '"+this.username+"'");
				
				if(count.next()) {
					
					modules = new String[count.getInt("count")];
					
				}
				
				
				ResultSet rs = st.executeQuery("SELECT module_name FROM assigned WHERE module_teacher = '"+this.username+"'");
				
				int i = 0;
				
				while(rs.next()) {
					
					modules[i] = rs.getString("module_name");
					i+=1;
				}
				
			}
			
			catch(Exception error) {
				
				error.printStackTrace();
			}
			
			
			dropdown = new JComboBox<String>(modules);
			dropdown.setBounds(10, 60, 360, 40);
			dropdown.setFont(new Font("serif", Font.PLAIN, 16));
			marking.add(dropdown);
			
			
			studentName = new JLabel("Username:");
			studentName.setBounds(20, 120, 100, 30);
			studentName.setFont(new Font("serif", Font.PLAIN, 18));
			marking.add(studentName);
			
			nameStudent = new JTextField();
			nameStudent.setBounds(20,160, 350, 30);
			nameStudent.setEditable(false);
			nameStudent.setFont(new Font("serif", Font.PLAIN, 16));
			marking.add(nameStudent);
			
			marks = new JLabel("Marks/Grade:");
			marks.setBounds(20, 200, 100, 30);
			marks.setFont(new Font("serif", Font.PLAIN, 17));
			marking.add(marks);
			
			marksGrade = new JTextField();
			marksGrade.setBounds(20,240, 350, 30);
			marksGrade.setFont(new Font("serif", Font.PLAIN, 16));
			marking.add(marksGrade);
			
			giveMarks = new JButton("Add Marks");
			giveMarks.setBounds(20, 300, 150, 40);
			giveMarks.addActionListener(this);
			giveMarks.setFont(new Font("serif", Font.PLAIN, 16));
			marking.add(giveMarks);
			
			back = new JButton("Back");
			back.setBounds(220, 300, 150, 40);
			back.setFont(new Font("serif", Font.PLAIN, 16));
			back.addActionListener(this);
			marking.add(back);
			
			marking.setVisible(true);
			marking.setResizable(false);
			
			
			
		}
		
		if(e.getSource() == giveMarks) {
			
			
			Statement st = conn.getConn();
			try {
				
				st.executeUpdate("UPDATE `marking` SET `marks`='"+ Integer.parseInt(marksGrade.getText())+"' WHERE students_name = '"+ nameStudent.getText() +"' AND module_name = '"+ dropdown.getSelectedItem() +"' ");
				JOptionPane.showMessageDialog(marking,"Successfully Updated");   
				marking.dispose();
				new Instructor(this.username);
				
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		
			
		}
		
		
		if(e.getSource() == search) {
			
			
			Statement st = conn.getConn();
			try {
				
				ResultSet rs = st.executeQuery("SELECT username, enrolled_course FROM userinfo WHERE collegeId = '"+studentId.getText()+"'");
				if(rs.next()) {
					
					nameStudent.setText(rs.getString("username"));
					temp = rs.getString("enrolled_course");
					
					
				}
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
		}
		
		
		if(e.getSource() == back) {
			
			marking.dispose();
			new Instructor(this.username);
		}
		
		
		
		if(e.getSource() == logout) {
			
			this.dispose();
			new Main().startGUI();;
			
		}
	
	}
	
	
	public static void main(String[] args) {
		
		new Instructor("Bishal");
	
	}
	

}

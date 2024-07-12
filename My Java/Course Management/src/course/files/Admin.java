package course.files;

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
import java.awt.Color;

import java.awt.event.*;

@SuppressWarnings("serial")
public class Admin extends JFrame implements ActionListener{


	JLabel welcome, idCourse, nameCourse, idModule, nameModule, levelname, semesterlevel;
	JButton manageAccounts, manageCourse, manageModules, report, logout, deleteCourse, addCourse, deleteModule, addModule, back, title, assignModule, assign, backAssign;
	String username;
	JFrame manageCoursePanel, assignPanel;
	JTextField courseId, courseName, moduleId, moduleName, level, semester, teacherName;
	JComboBox<String> moduless;
	
	DatabaseConnection  conn = new DatabaseConnection();
	
	Admin(String username){
		
		this.username = username;
		this.setTitle("Admin Panel");
		this.setSize(400,360);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		Statement st = conn.getConn();
		ResultSet rs;
	
		try {
	
			rs = st.executeQuery("SELECT * FROM login WHERE username='"+this.username+"' AND indicator='Admin'");
			
			if(rs.next()) {
				
				title = new JButton("Welcome to your Dashboard: "+ rs.getString("username"));
				title.setFont(new Font("serif", Font.PLAIN, 16));
				title.setBounds(20, 20, 338, 40);
				title.setFocusable(false);
				title.setEnabled(false);
				title.setFont(new Font("serif", Font.PLAIN, 18));
				UIManager.put("Button.disabledText", Color.BLACK);
				
	
			}
			
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		this.add(title);
		
		manageCourse = new JButton("Manage Course");
		manageCourse.setBounds(110,80,150,40);
		manageCourse.setFocusable(false);
		manageCourse.addActionListener(this);
		manageCourse.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(manageCourse);
		
		manageModules = new JButton("Manage Modules");
		manageModules.setBounds(110,140,150,40);
		manageModules.setFocusable(false);
		manageModules.addActionListener(this);
		manageModules.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(manageModules);
		 
		assignModule = new JButton("Assign Module");
		assignModule.setBounds(110,200,150,40);
		assignModule.setFocusable(false);
		assignModule.addActionListener(this);
		assignModule.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(assignModule);

		report = new JButton("Result");
		report.setBounds(30,270,100,30);
		report.setFocusable(false);
		report.addActionListener(this);
		report.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(report);
		

		logout = new JButton("Logout");
		logout.setBounds(240,270,100,30);
		logout.setFocusable(false);
		logout.addActionListener(this);
		logout.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(logout);
		this.setVisible(true);
		
	}

	public static void main(String[] args) {

		new Admin("Admin");

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == manageCourse) {
			
			this.dispose();
			manageCoursePanel = new JFrame();
			manageCoursePanel.setSize(390,300);
			manageCoursePanel.setTitle("Add Courses");
			manageCoursePanel.setLocationRelativeTo(null);
			
			idCourse = new JLabel("Course Code:");
			idCourse.setBounds(20,20,150,30);
			idCourse.setFont(new Font("serif", Font.PLAIN, 18));
			manageCoursePanel.add(idCourse);
			
			courseId = new JTextField();
			courseId.setBounds(20, 60, 200, 30);
			courseId.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(courseId);
			
			nameCourse = new JLabel("Course Name:");
			nameCourse.setBounds(20,110,150,30);
			nameCourse.setFont(new Font("serif", Font.PLAIN, 18));
			manageCoursePanel.add(nameCourse);
			
			courseName = new JTextField();
			courseName.setBounds(20, 150, 330, 30);
			courseName.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(courseName);
			
			deleteCourse = new JButton("Delete");
			deleteCourse.setBounds(133, 210, 105, 30);
			deleteCourse.addActionListener(this);
			deleteCourse.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(deleteCourse);
			
			addCourse = new JButton("Add");
			addCourse.setBounds(20, 210, 105, 30);
			addCourse.addActionListener(this);
			addCourse.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(addCourse);
			
			back = new JButton("Back");
			back.setBounds(245, 210, 105, 30);
			back.addActionListener(this);
			back.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(back);
	
			manageCoursePanel.setLayout(null);
			manageCoursePanel.setVisible(true);
			
			
			
		}
		
		if(e.getSource() == addCourse) {
			
			Statement st = conn.getConn();
			try {
				
				
				st.executeUpdate("INSERT INTO `courses`(`course_name`, `course_code`) VALUES ('"+courseName.getText()+"','"+courseId.getText()+"')");
				JOptionPane.showMessageDialog(manageCoursePanel,"Successfully Updated");   
				manageCoursePanel.dispose();
				new Admin(this.username);
				
			
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
		}
		
		if(e.getSource() == deleteCourse)
		{
			Statement st = conn.getConn();
			try {
				
				st.executeUpdate("DELETE FROM `courses` WHERE course_code='"+courseId.getText()+"' AND course_name='"+courseName.getText()+"'");
				JOptionPane.showMessageDialog(manageCoursePanel,"Successfully Updated");   
				manageCoursePanel.dispose();
				new Admin(this.username);
				
			
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
		}
		
		
		if(e.getSource() == manageModules) {
			
			this.dispose();
			manageCoursePanel = new JFrame();
			manageCoursePanel.setTitle("Add Modules");
			manageCoursePanel.setSize(390,350);
			manageCoursePanel.setLocationRelativeTo(null);
			manageCoursePanel.setResizable(false);
			
			idModule = new JLabel("Module Code");
			idModule.setBounds(20,20,150,30);
			idModule.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(idModule);
			
			moduleId = new JTextField();
			moduleId.setBounds(20, 60, 100, 30);
			moduleId.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(moduleId);
			
			levelname = new JLabel("Level");
			levelname.setBounds(140,20,150,30);
			levelname.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(levelname);
			
			level = new JTextField();
			level.setBounds(140, 60, 80, 30);
			level.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(level);
			
			semesterlevel = new JLabel("Semester");
			semesterlevel.setBounds(240,20,150,30);
			semesterlevel.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(semesterlevel);
			
			semester = new JTextField();
			semester.setBounds(240, 60, 110, 30);
			semester.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(semester);
			
			nameCourse = new JLabel("Course Code:");
			nameCourse.setBounds(20,100,150,30);
			nameCourse.setFont(new Font("serif", Font.PLAIN, 18));
			manageCoursePanel.add(nameCourse);
			
			courseName = new JTextField();
			courseName.setBounds(20, 130, 330, 30);
			courseName.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(courseName);
			
			
			nameModule = new JLabel("Module Name:");
			nameModule.setBounds(20,170,150,30);
			nameModule.setFont(new Font("serif", Font.PLAIN, 18));
			manageCoursePanel.add(nameModule);
			
			moduleName = new JTextField();
			moduleName.setBounds(20, 200, 330, 30);
			moduleName.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(moduleName);
			
			deleteModule = new JButton("Delete");
			deleteModule.setBounds(133, 250, 105, 30);
			deleteModule.addActionListener(this);
			deleteModule.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(deleteModule);
			
			addModule = new JButton("Add");
			addModule.setBounds(20, 250, 105, 30);
			addModule.addActionListener(this);
			addModule.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(addModule);
			
			back = new JButton("Back");
			back.setBounds(245, 250, 105, 30);
			back.addActionListener(this);
			back.setFont(new Font("serif", Font.PLAIN, 16));
			manageCoursePanel.add(back);
	
			manageCoursePanel.setLayout(null);
			manageCoursePanel.setVisible(true);
			
			
			
		}
		
		
		if(e.getSource() == addModule) {
			
			Statement st = conn.getConn();
			manageCoursePanel.dispose();
			
			try {
				st.executeUpdate("INSERT INTO `modules`(`course_name`, `module_name`, `level`, `semseter`, `module_code`) VALUES ('"+courseName.getText()+"','"+ moduleName.getText() +"','"+level.getText()+"','"+semester.getText()+"','"+moduleId.getText()+"')");
				JOptionPane.showMessageDialog(manageCoursePanel,"Successfully Updated");   
				manageCoursePanel.dispose();
				new Admin(this.username);
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
		if(e.getSource() == deleteModule) {
			
			Statement st = conn.getConn();
			manageCoursePanel.dispose();
			
			try {
				st.executeUpdate("DELETE FROM modules WHERE course_name='"+courseName.getText()+"' AND module_name='"+moduleName.getText()+"' AND module_code='"+moduleId.getText()+"'");
				JOptionPane.showMessageDialog(manageCoursePanel,"Successfully Updated");   
				manageCoursePanel.dispose();
				new Admin(this.username);
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
		if(e.getSource() == assignModule) {
			
			this.dispose();
			assignPanel = new JFrame();
			assignPanel.setSize(400, 190);
			assignPanel.setLayout(null);
			assignPanel.setLocationRelativeTo(null);
			
			Statement st = conn.getConn();
			
			String[] modules = null;
			

			try {
				
				ResultSet count = st.executeQuery("SELECT COUNT(DISTINCT module_name) as count FROM modules WHERE module_name != ''");
				if(count.next()) {
					
					modules = new String[count.getInt("count")];
					
				}
				
				
				ResultSet result = st.executeQuery("SELECT * FROM modules WHERE module_name != ''");
				
				int i = 0;
				
				while(result.next()) {
					
					modules[i] = result.getString("module_name");
					i+=1;
	
				}

			
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
			moduless = new JComboBox<String>(modules);
			moduless.setBounds(20, 20, 340, 30);
			moduless.setFont(new Font("serif", Font.PLAIN, 16));
			assignPanel.add(moduless);
			
			teacherName = new JTextField();
			teacherName.setBounds(20, 60, 340, 30);
			teacherName.setFont(new Font("serif", Font.PLAIN, 16));
			assignPanel.add(teacherName);
			
			assign = new JButton();
			assign.setBounds(20,100,200,30);
			assign.setText("Assign Course");
			assign.setFocusable(false);
			assign.addActionListener(this);
			assign.setFont(new Font("serif", Font.PLAIN, 15));
			assignPanel.add(assign);
	
			backAssign = new JButton();
			backAssign.setBounds(225,100,135,30);
			backAssign.setText("Back");
			backAssign.setFocusable(false);
			backAssign.addActionListener(this);
			backAssign.setFont(new Font("serif", Font.PLAIN, 15));
			assignPanel.add(backAssign);
			assignPanel.setVisible(true);
			
			
		}
		
		if(e.getSource() == assign){
			
			
			Statement st = conn.getConn();
			
			String code = null;
			
			try {
				
				ResultSet rs = st.executeQuery("SELECT module_code FROM assigned WHERE module_code !=''");
				
				if(rs.next()) {
					
					code = rs.getString("module_code");
					
				}

				st.executeUpdate("INSERT INTO `assigned`(`module_teacher`, `module_name`, `module_code`) VALUES ('"+ teacherName.getText() +"','"+ moduless.getSelectedItem() +"','"+ code +"')");
				
				JOptionPane.showMessageDialog(manageCoursePanel,"Successfully Updated");   
				assignPanel.dispose();
				new Admin(this.username);
				

			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
		}
		
		
		if(e.getSource() == backAssign) {
			
			assignPanel.dispose();
			new Admin(this.username);
			
			
		}
		
		if(e.getSource() == report) {
			
			this.dispose();
			try {
				new ResultSlip(this.username, "Admin");
			} 
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		

		if(e.getSource() == back) {

		    manageCoursePanel.dispose();
		    new Admin(this.username);

		}

		
		
	}
	
}

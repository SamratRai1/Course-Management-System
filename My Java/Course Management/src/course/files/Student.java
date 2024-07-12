package course.files;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Student implements ActionListener {
    String username;
    JFrame frame, modules;
    JLabel name,logged;
    JButton LogOut,course,back, exit, add, res;
    JTable result;
    String[][] report;
    String enrolled;
    
    DatabaseConnection obj = new DatabaseConnection();
	Statement st = obj.getConn();

    Student(String username){
    	
        this.username = username;
    }
    
    public void start(){

        frame = new JFrame();
        name = new JLabel();
        name.setText("Dashboard");
        name.setBounds(150,25,150,40);
        name.setFont(new Font("serif", Font.PLAIN, 18));
        frame.add(name);

        logged = new JLabel();
        logged.setText("Logged as: " + this.username);
        logged.setBounds(30,70,150,50);
        logged.setFont(new Font("serif", Font.PLAIN, 18));

        LogOut = new JButton();
        LogOut.setBounds(250,80,100,30);
        LogOut.setText("LogOut");
        LogOut.setFocusable(false);
        LogOut.addActionListener(this);
        LogOut.setFont(new Font("serif", Font.PLAIN, 15));

        course = new JButton();
        course.setBounds(30,150,100,30);
        course.setText("Modules");
        course.setFocusable(false);
        course.addActionListener(this);
        course.setFont(new Font("serif", Font.PLAIN, 15));
        
        res = new JButton();
        res.setBounds(139,150,100,30);
        res.setText("Report");
        res.setFocusable(false);
        res.addActionListener(this);
        res.setFont(new Font("serif", Font.PLAIN, 15));
        frame.add(res);

        exit = new JButton();
        exit.setBounds(250,150,100,30);
        exit.setText("Exit");
        exit.setFocusable(false);
        exit.setFont(new Font("serif", Font.PLAIN, 15));
        exit.addActionListener(this);

        frame.add(exit);
        frame.add(course);
        frame.add(LogOut);
        frame.add(logged);
        frame.setSize(400,250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
    	
    	
    	if(e.getSource() == course) {
    		
    		frame.dispose();
    		modules = new JFrame();
    		modules.setSize(600, 300);
    		modules.setResizable(false);
    	    modules.setLocationRelativeTo(null);
    	    modules.setLayout(null);
			
			String[] column = {"Code", "Modules", "Semester"};
			
			
			int reportCount = -1;
			ResultSet count;
			int semester = 0;
			String course = null;
			
			try {
				
				ResultSet rs = st.executeQuery("SELECT semester, enrolled_course FROM userinfo WHERE username ='"+ this.username +"' AND indicator = 'Student'");
				
				if(rs.next()) {
					
					semester = rs.getInt("semester");
					course = rs.getString("enrolled_course");
					enrolled = course;

				}
				
				count = st.executeQuery("SELECT COUNT(DISTINCT module_name) AS count FROM modules WHERE course_name ='"+ course +"'");
				
				if(count.next()) {
					
					reportCount = count.getInt("count");
					
				}
				
				
			} 
			
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			report = new String[reportCount][4];
			
			int x = 0;
			
			ResultSet rs;
			try {
				
				rs = st.executeQuery("SELECT * FROM modules where course_name = '"+ course +"' AND semester = '"+semester+"'");
				
				while(rs.next()) {
					
					report[x] = new String[] {rs.getString("module_code"),rs.getString("module_name"),rs.getString("semester")};
					x+=1;
				}
			
				
			} 
			
		
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			result = new JTable(report, column);
			result.setRowHeight(25);
			JTableHeader header1 = result.getTableHeader();
			header1.setFont(new Font("Consolas",Font.PLAIN, 15));
			
			result.setFont(new Font("Consolas",Font.PLAIN, 12));
			result.setEnabled(false);

			result.getColumnModel().getColumn(0).setPreferredWidth(100);
			result.getColumnModel().getColumn(1).setPreferredWidth(400);
			result.getColumnModel().getColumn(2).setPreferredWidth(150);
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			result.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			result.getColumnModel().getColumn(1);
			result.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
	
			
			JScrollPane scrollPane = new JScrollPane(result);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(20, 20, 545, 176);
			
			
			
			modules.add(scrollPane);
    	    
    	    back = new JButton();
    	    back.setBounds(463,210,100,30);
    	    back.setText("Back");
    	    back.setFocusable(false);
    	    back.setFont(new Font("serif", Font.PLAIN, 15));
    	    back.addActionListener(this);
    	    modules.add(back);
    	    
    	    
    	    add = new JButton();
    	    add.setBounds(20,210,150,30);
    	    add.setText("Add Report");
    	    add.setFocusable(false);
    	    add.setFont(new Font("serif", Font.PLAIN, 15));
    	    add.addActionListener(this);
    		
    		try {
    			
    			ResultSet r = st.executeQuery("SELECT addModules FROM userinfo WHERE username = '"+this.username+"' and indicator = 'Student'");
    		
    			if(r.next()) {
    				
    				if(r.getInt("addModules") == 1) {
    					
    					add.setEnabled(true);
    					
    				}
    				
    				else {
    					
    					add.setEnabled(false);
    				}
    				
    				
    			}
    			
    			
    		}
    		
    		catch(SQLException error){
    			
    			error.printStackTrace();
    			
    		}
    		
    	    modules.add(add);
    	    modules.setVisible(true);
    		
    	}
    	
    	
    	if(e.getSource() == LogOut) {
    		
    		frame.dispose();
    		new Main().startGUI();
    		
    	}
    	
    	if(e.getSource() == add) {
       			
    			String [][] moduleElements = new String[(int)result.getModel().getRowCount()][3];

    			for(int i = 0; i < (int)result.getModel().getRowCount(); i++) {
    				
    				int j = 0;
    				moduleElements[i] = new String[] {(String)result.getModel().getValueAt(i,j), (String)result.getModel().getValueAt(i,j+1),(String)result.getModel().getValueAt(i,j+2)};

    			}
        		
    			
    			try {
    				
    				for(int i = 0; i < moduleElements.length; i++) {
    					
    					int j = 0;
    					
    					st.executeUpdate("INSERT INTO `marking`(`students_name`, `module`, `marks`, `course`) VALUES ('"+this.username+"','"+moduleElements[i][j+1]+"','0','"+enrolled+"')");
    					
    					
    				}
    				
    				
    			}
        		
    			catch(SQLException err) {
    				
    				err.printStackTrace();
    				
    			}
    			
    			try {
    				
    				st.executeUpdate("UPDATE `userinfo` SET `addModules`='0' WHERE username = '"+this.username+"'");
    				add.setEnabled(false);
    				
    			}
    			
    			catch(Exception error){
    				
    				error.printStackTrace();
    				
    			}
    			
    		}
    	
    	
    	if(e.getSource() == back) {
    		
    		modules.dispose();
    		new Student(this.username).start();
    		
    	}
    	
    	if(e.getSource() == res) {
    		
    		try {
				new ResultStudent(this.username);
			} 
    		
    		catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	}
    	
        if(e.getSource() == exit) {
        	
            frame.dispose();
        }
        
    }
    public static void main(String[] args) {

        Student main = new Student("Raymon");
        main.start();

    }
}

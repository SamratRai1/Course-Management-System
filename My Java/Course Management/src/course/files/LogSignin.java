package course.files;

import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogSignin implements ActionListener{
	
    JFrame frame;
    JLabel title,user,pass,s_user,s_pass, s_collegeid;
    JTextField username,s_username, collegeId;
    JPasswordField password,s_password;
    JButton login, signup;
    String userr;

    LogSignin(String userr){

        this.userr = userr;
        frame = new JFrame();
        
        title = new JLabel();
        title.setText("Welcome to Course Management System!!");

        title.setBounds(170,20,250,40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(title);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
    }

    public void userInputs(){
    	
    	
    	if(this.userr == "Admin") {
    		
    		
    		user = new JLabel();
            user.setText("Username:");
            user.setBounds(200,70,130,50);
            username = new JTextField();
            username.setBounds(200,110,150,30);

            pass = new JLabel();
            pass.setText("Password:");
            pass.setBounds(200,140,130,50);
            password = new JPasswordField();
            password.setBounds(200,180,150,30);

            login = new JButton();
            login.setText("Login");
            login.setBounds(200,230,120,30);
            login.addActionListener(this);
            
            frame.setSize(600,400);
    		
    		
    	}
    	
    	else {
    		
    		 user = new JLabel();
    	        user.setText("Username:");
    	        user.setBounds(200,70,130,50);
    	        username = new JTextField();
    	        username.setBounds(200,110,150,30);

    	        pass = new JLabel();
    	        pass.setText("Password:");
    	        pass.setBounds(200,140,130,50);
    	        password = new JPasswordField();
    	        password.setBounds(200,180,150,30);

    	        login = new JButton();
    	        login.setText("Login");
    	        login.setBounds(200,230,120,30);
    	        login.addActionListener(this);

    	        s_user = new JLabel();
    	        s_user.setText("Username:");
    	        s_user.setBounds(200,275,130,50);
    	        frame.add(s_user);

    	        s_username = new JTextField();
    	        s_username.setBounds(200, 320, 150, 30);
    	        frame.add(s_username);

    	        s_pass = new JLabel();
    	        s_pass.setText("Password:");
    	        s_pass.setBounds(200,350,150,30);
    	        frame.add(s_pass);

    	        s_password = new JPasswordField();
    	        s_password.setBounds(200,380,150,30);
    	        frame.add(s_password);

    	        s_collegeid = new JLabel("College Id:");
    	        s_collegeid.setBounds(200,410, 150,30);
    	        frame.add(s_collegeid);

    	        collegeId = new JTextField();
    	        collegeId.setBounds(200,440, 150, 30);
    	        frame.add(collegeId);

    	        signup = new JButton("Signup");
    	        signup.setBounds(200, 490, 120, 30 );
    	        signup.addActionListener(this);
    	        frame.add(signup);
    	        frame.setSize(600,600);
    		
    		
    	}
       

        frame.add(user);
        frame.add(username);
        frame.add(pass);
        frame.add(password);
        frame.add(login);

        frame.setVisible(true);


    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() ==login) {
		
			DatabaseConnection obj = new DatabaseConnection();
			Statement st = obj.getConn();
			try {
	
				ResultSet rs = st.executeQuery("SELECT * FROM login WHERE username='"+ username.getText() +"' AND password = '"+ password.getText() +"' AND indicator='"+this.userr+"'");
					
				if(rs.next()) {
						
					if(this.userr == "Student") {
						
						ResultSet result = st.executeQuery("SELECT * FROM userinfo WHERE username='"+ username.getText() +"' AND indicator = 'Student'");
						
						if(result.next()) {
							
							if(result.getString("enrolled_course").isBlank() || result.getString("enrolled_course").isEmpty())
							{
								
								frame.dispose();
								new CourseSelection(result.getString("username"));
								
							}
							
							else {
								
								frame.dispose();
								new Student(result.getString("username")).start();
								
								
							}
							
						}
						
						
					}
						
					if(this.userr == "Teacher") {
							
						frame.dispose();
						new Instructor(rs.getString("username"));
							
							
					}
						
					if(this.userr == "Admin") {
							
						frame.dispose();
						new Admin(rs.getString("username"));

					}
						
				}

			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
		}
		
		if(e.getSource() == signup) {
			
			DatabaseConnection obj = new DatabaseConnection();
			Statement st = obj.getConn();
			
			try {
				
				st.executeUpdate("INSERT INTO login(`username`,`password`,`indicator`,`college_id`) VALUES ('"+s_username.getText()+"','"+s_password.getText()+"','"+ this.userr +"','"+collegeId.getText()+"')");
				st.executeUpdate("INSERT INTO `userinfo`(`username`,`enrolled_course`,`semester`,`level`,`collegeId`,`indicator`,`addModules`) VALUES ('"+s_username.getText()+"','','1','4','"+collegeId.getText()+"', '"+this.userr+"', '1' )");
				
				JOptionPane.showMessageDialog(frame, "Account Created",null, JOptionPane.INFORMATION_MESSAGE);
				s_username.setText("");
				s_password.setText("");
				collegeId.setText("");
			}
			
			catch(Exception error) {
				
				JOptionPane.showMessageDialog(frame, "Failed",null, JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		
	}
}


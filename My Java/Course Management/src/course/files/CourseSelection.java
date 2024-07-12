package course.files;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CourseSelection extends JFrame implements ActionListener {
	
	private JButton bit, bibm;
	private JLabel title;
	private String username;
	
	DatabaseConnection obj = new DatabaseConnection();
	Statement st = obj.getConn();
	
	public CourseSelection(String username){
		
		
		this.username = username;
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		
		title = new JLabel("Course Selection");
		title.setBounds(120, 30, 150, 40);
		title.setFont(new Font("serif", Font.PLAIN, 18));
		this.add(title);
		
		
		bit = new JButton("BIT");
		bit.setBounds(110, 90, 150, 40);
		bit.addActionListener(this);
		bit.setFont(new Font("serif", Font.PLAIN, 16));
		bit.setFocusable(false);
		this.add(bit);
		
		
		bibm = new JButton("BIBM");
		bibm.setBounds(110, 150, 150, 40);
		bibm.addActionListener(this);
		bibm.setFont(new Font("serif", Font.PLAIN, 16));
		bibm.setFocusable(false);
		this.add(bibm);
		

		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == bit) {
			
			
			try {
				st.executeUpdate("UPDATE `userinfo` SET `enrolled_course`='"+bit.getText()+"' WHERE username = '"+this.username+"'");
				
				this.dispose();
				new Student(this.username).start();
				
			} 
			
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
		if(e.getSource() == bibm) {
			
			
			try {
				st.executeUpdate("UPDATE `userinfo` SET `enrolled_course`='"+bibm.getText()+"' WHERE username = '"+this.username+"'");
				
				this.dispose();
				new Student(this.username).start();
				
			} 
			
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
		
	}
	
	public static void main(String[] args) {
		
		new CourseSelection("Prabesh");
		
		
	}

}

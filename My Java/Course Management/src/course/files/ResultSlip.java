package course.files;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ResultSlip extends JFrame implements ActionListener {
	
	String user, indi;
	JTextField searchStudent;
	JButton student, proceed;
	JTable result;
	String temp;
	DatabaseConnection  conn = new DatabaseConnection();
	Statement st = conn.getConn();
	
	ResultSlip(String user, String indi) throws SQLException{
		
		this.user = user;
		this.indi = indi;
		this.setSize(580, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		searchStudent = new JTextField();
		searchStudent.setBounds(20, 20, 220, 30);
		searchStudent.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(searchStudent);
		
		student = new JButton();
		student.setBounds(245,20,125,30);
		student.setText("Search");
		student.setFont(new Font("serif", Font.PLAIN, 16));
		student.addActionListener(this);
		student.setFocusable(false);
		this.add(student);

		proceed = new JButton();
		proceed.setBounds(420,310,125,30);
		proceed.setText("Promote");
		proceed.addActionListener(this);
		proceed.setEnabled(false);
		proceed.setFocusable(false);
		proceed.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(proceed);
			
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == student) {
			
			temp = searchStudent.getText();
			
			Statement st = conn.getConn();
			
			String[] column = {"S.N", "Modules", "Marks"};
			
			int reportCount = -1;
			ResultSet count;
			try {
				count = st.executeQuery("SELECT COUNT(DISTINCT module) AS count FROM marking WHERE students_name ='"+ searchStudent.getText() +"'");
				
				if(count.next()) {
					
					reportCount = count.getInt("count");
					
				}
				
			} 
			
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			String[][] report = new String[reportCount][4];
			
			int x = 0;
			
			ResultSet rs;
			try {
				
				rs = st.executeQuery("SELECT * FROM marking where students_name = '"+ searchStudent.getText() +"'");
				
				while(rs.next()) {
					
					report[x] = new String[] {rs.getString("marks_id"),rs.getString("module"),rs.getString("marks")};
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
			scrollPane.setBounds(20, 80, 530, 126);
			
			proceed.setEnabled(true);
			this.add(scrollPane);
			}
	
		
		if(e.getSource() == proceed) {
			
			String [][] moduleElements = new String[(int)result.getModel().getRowCount()][3];

			for(int i = 0; i < (int)result.getModel().getRowCount(); i++) {
				
				int j = 0;
				moduleElements[i] = new String[] {(String)result.getModel().getValueAt(i,j), (String)result.getModel().getValueAt(i,j+1),(String)result.getModel().getValueAt(i,j+2)};

			}
			
			int count = 0;
			
			for(String[] m : moduleElements) {
				
				if(Integer.parseInt(m[2]) >= 40) {
					
					count+=1;
				}
				
			}
			
			if(count == (int)result.getModel().getRowCount()) {
				
				try {
					
					ResultSet rs = st.executeQuery("SELECT semester,level FROM userinfo WHERE username = '"+ temp +"'");
					
					if(rs.next()) {
						
						
						if(rs.getInt("semester") % 2 == 0) {
							
						
							st.executeUpdate("UPDATE `userinfo` SET `semester`='"+ (rs.getInt("semester") + 1) +"',`level`='"+ (rs.getInt("level") + 1) +"',`addModules`='1' WHERE username = '"+temp+"'");
							
							
						}
						
						else {
							
							st.executeUpdate("UPDATE `userinfo` SET `semester`='"+ (rs.getInt("semester") + 1) +"',`addModules`='1' WHERE username = '"+temp+"'");
							
						}
						
					}
					
					
					
				}
				catch(SQLException error) {
					
					error.printStackTrace();
					
				}
				
				
			}
			
			
			
		}
		
	}
		
	
	
	public static void main(String[] args) {
		
		try {
			new ResultSlip("Admin", "Admin");
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

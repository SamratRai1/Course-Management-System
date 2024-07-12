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

public class ResultStudent extends JFrame implements ActionListener {
	
	String user;
	JTextField searchStudent;
	JButton student, back;
	JTable result;
	DatabaseConnection  conn = new DatabaseConnection();
	
	ResultStudent(String user) throws SQLException{
		
		this.user = user;
		this.setSize(580, 260);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		Statement st = conn.getConn();
		
		String[] column = {"S.N", "Modules", "Marks"};
		
		int reportCount = -1;
		ResultSet count;
		try {
			count = st.executeQuery("SELECT COUNT(DISTINCT module) AS count FROM marking WHERE students_name ='"+ this.user +"'");
			
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
			
			rs = st.executeQuery("SELECT * FROM marking where students_name = '"+ this.user +"'");
			
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
		scrollPane.setBounds(20, 40, 530, 126);
	
		this.add(scrollPane);
		
		back = new JButton();
		back.setBounds(420,180,125,30);
		back.setText("Back");
		back.addActionListener(this);
		back.setFocusable(false);
		back.setFont(new Font("serif", Font.PLAIN, 16));
		this.add(back);
		
		
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == back) {
		
			this.dispose();
			new Student(this.user).start();
			
			
		}
		

		
	}
		
	
	
	public static void main(String[] args) {
		
		try {
			new ResultStudent("Raymon");
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

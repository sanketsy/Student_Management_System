////////// FInal
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class MainFrame extends JFrame{
	Container c;
	JButton btnAdd,btnView,btnUpdate,btnDelete;

	MainFrame(){
		c= getContentPane();
		c.setLayout(new FlowLayout());
		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);


		setTitle("S. M. S.");
		setSize(255,180);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				AddFrame a = new AddFrame();
				dispose();
			}
		});

		btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ViewFrame a = new ViewFrame();
				dispose();
			}
		});


		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				UpdateFrame a = new UpdateFrame();
				dispose();
			}
		});


		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				DeleteFrame a = new DeleteFrame();
				dispose();
			}
		});
	}




	public static void main(String args[]){
		MainFrame m = new MainFrame();
	}
} // end of class



class DbHandler{

	public void addStudent(String rno, String name){
		int r1=0,set=0;
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sanket");
			String sql = "insert into student values(?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);

			Statement s = con.createStatement();
			String s1= "select * from student";
			ResultSet rs = s.executeQuery(s1);

			AddFrame a = new AddFrame();

			///////////////////////just added


			if(name.equals("") & rno.equals(""))
				JOptionPane.showMessageDialog(new JDialog(),"Don\'t leave both fields blank.");
			else if(rno.contains("."))
				JOptionPane.showMessageDialog(new JDialog(),"Only Integers are allowed.");
			else if(rno.contains("-")){
				if(rno.indexOf("-")==0)
					JOptionPane.showMessageDialog(new JDialog(),"Negative number is not allowed.");
				else
					JOptionPane.showMessageDialog(new JDialog(),"Invalid number.");
			}
			else if(rno.equals(""))
				JOptionPane.showMessageDialog(new JDialog(),"Don\'t leave RNO field blank.");
			else if(name.equals(""))
				JOptionPane.showMessageDialog(new JDialog(),"Don\'t leave Name field blank.");
			else if(!name.matches("^[a-zA-Z ]*$") )
				JOptionPane.showMessageDialog(new JDialog(),"Kindly enter only alphabets for Name.");
			else if(rno.matches("^[a-zA-Z*&%$#@]") ) 
				JOptionPane.showMessageDialog(new JDialog(),"Kindly enter only integers for RNO.");

			else{
			loop:while(rs.next()){
					if((rs.getString(1)).equals(rno)){
						set=1;
						JOptionPane.showMessageDialog(new JDialog(),"Such a RNO already exists.");
						a.txtrno.setText("");
						a.txtname.setText("");
						a.txtrno.requestFocus();
						a.dispose();
						JOptionPane.showMessageDialog(new JDialog(),r1+" records inserted");
						break loop;
					}
					else
						continue;
				}
				if(set!=1){
				pst.setString(1, rno);
				pst.setString(2, name);
				r1 = pst.executeUpdate();
				a.dispose();
				}
			}
			//JOptionPane.showMessageDialog(new JDialog(),r1+" records inserted");
			con.close();
		} // eo try
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(),"Issue is "+e);
		}

	} // eo addstudent



		public String viewStudent()
		{
		StringBuffer sb = new StringBuffer();
		try{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sanket");
		String sql = "select * from student";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		sb.append("RNO\tName\n");
		while (rs.next())
		{
		int rno = rs.getInt(1);
		String name = rs.getString(2);
		sb.append(rno+"\t"+name+"\n");
		}
		rs.close();
		con.close();
		} // eo try
		catch(SQLException se){JOptionPane.showMessageDialog(new JDialog(), "issue "+se);}

		return sb.toString();

} // eo viewstudent




public void updateStudent(String rno, String name)
{
int r1=0;
try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sanket");
String sql = "update student set name = ? where rno = ?";
PreparedStatement pst = con.prepareStatement(sql);

Statement s = con.createStatement();
String s1= "select * from student";
ResultSet rs = s.executeQuery(s1);

UpdateFrame a = new UpdateFrame();


///////////////////////just added
if(name.equals("") & rno.equals(""))
	JOptionPane.showMessageDialog(new JDialog(),"Don\'t leave both fields blank.");
else if(rno.equals(""))
	JOptionPane.showMessageDialog(new JDialog(),"Don\'t leave RNO field blank.");
else if(name.equals(""))
	JOptionPane.showMessageDialog(new JDialog(),"Don\'t leave Name field blank.");
else if(!name.matches("^[a-zA-Z]*$") )
{
JOptionPane.showMessageDialog(new JDialog(),"Kindly enter only alphabets for Name.");
}
else if(rno.matches("^[a-zA-Z*&%$#@]") ) 
{
JOptionPane.showMessageDialog(new JDialog(),"Kindly enter only integers for RNO.");
}
else
{
	loop:while(rs.next())
	{	
		
		if((rs.getString(1)).equals(rno))
		{
			pst.setString(1, name);
			pst.setString(2, rno);
			r1 = pst.executeUpdate();
			a.dispose();
			JOptionPane.showMessageDialog(new JDialog(),r1+" records updated");
			break loop;
		}
	}	
	if(r1==0){
	JOptionPane.showMessageDialog(new JDialog(),"No such Student RNO exists.");
	a.txtrno.setText("");
	a.txtname.setText("");
	a.txtrno.requestFocus();
	a.dispose();
	}
}








//JOptionPane.showMessageDialog(new JDialog(),r1+" records updated");
con.close();
} // eo try
catch(SQLException se){JOptionPane.showMessageDialog(new JDialog(),"issue "+se);}

} // eo updatestudent






public void deleteStudent(String rno)
{
try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sanket");
String sql = "delete from student where rno = ?";
PreparedStatement pst = con.prepareStatement(sql);

Statement s = con.createStatement();
String s1= "select * from student";
ResultSet rs = s.executeQuery(s1);

DeleteFrame a = new DeleteFrame();

int r1=0;

///////////////////////just added
if(rno.equals(""))
	{JOptionPane.showMessageDialog(new JDialog(),"Don/'t leave RNO field blank.");
	a.dispose();	}

else if(!rno.matches("^[a-zA-Z]*$@#%&"))  
{
loop:while(rs.next())
	{	
		if((rs.getString(1)).equals(rno))
		{
		pst.setString(1, rno);
		r1 = pst.executeUpdate();
		a.dispose();
		JOptionPane.showMessageDialog(new JDialog(),r1+" records deleted");
		break loop;
		}
		
	}
}
else
{
JOptionPane.showMessageDialog(new JDialog(),"Kindly enter only integers for RNO.");
a.dispose();
}

if(r1==0)
{
	JOptionPane.showMessageDialog(new JDialog(),"No such Student RNO exists.");
	a.txtrno.setText("");
	a.txtrno.requestFocus();
	a.dispose();
}

//JOptionPane.showMessageDialog(new JDialog(),r1+" records deleted");
con.close();
} // eo try
catch(SQLException se){JOptionPane.showMessageDialog(new JDialog(),"issue "+se);}

} // eo deletestudent





} // eo class


























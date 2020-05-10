
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AddFrame extends JFrame
{
Container c;
JButton btnSave,btnBack;
JLabel lblrno,lblname;
JTextField txtrno,txtname;

AddFrame()
{
c= getContentPane();
c.setLayout(new FlowLayout());
lblrno = new JLabel("Enter RNO");
lblname = new JLabel("Enter name");
txtrno = new JTextField(10);
txtname = new JTextField(10);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

c.add(lblrno);
c.add(txtrno);
c.add(lblname);
c.add(txtname);
c.add(btnSave);
c.add(btnBack);


setTitle("Add Frame");
setSize(255,180);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);


btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
String rno = txtrno.getText();
String name = txtname.getText();
DbHandler db = new DbHandler();
db.addStudent(rno,name);

txtrno.setText("");
txtname.setText("");
txtrno.requestFocus();
}

});


btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame m = new MainFrame();
dispose();
}
});


}


} // end of class


























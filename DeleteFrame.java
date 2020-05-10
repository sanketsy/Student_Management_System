

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DeleteFrame extends JFrame
{
Container c;
JButton btnSave,btnBack;
JLabel lblrno,lblname;
JTextField txtrno,txtname;

DeleteFrame()
{
c= getContentPane();
c.setLayout(new FlowLayout());
lblrno = new JLabel("Enter RNO");
txtrno = new JTextField(20);
btnSave = new JButton("Delete");
btnBack = new JButton("Back");

c.add(lblrno);
c.add(txtrno);
c.add(btnSave);
c.add(btnBack);


setTitle("Delete Frame");
setSize(255,180);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);


btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
String rno = txtrno.getText();
DbHandler db = new DbHandler();
db.deleteStudent(rno);


txtrno.setText("");
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


























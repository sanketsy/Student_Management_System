import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ViewFrame extends JFrame
{
Container c;
JButton btnBack;
JTextArea a;

ViewFrame()
{
c= getContentPane();
c.setLayout(new FlowLayout());
a = new JTextArea(5, 20);
btnBack = new JButton("Back");

c.add(a);
c.add(btnBack);


DbHandler db = new DbHandler();
String data = db.viewStudent();
a.setText(data);



setTitle("View Frame");
setSize(255,180);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);


btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame m = new MainFrame();
dispose();
}
});


}


} // end of class



























import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class LessonWindow implements ActionListener {

    private JFrame j = new JFrame();
    private JPanel p = new JPanel();
    private JButton newLesson = new JButton("Εγγραφή μαθηματος");
    private JButton deleteLesson = new JButton("Διαγγραφή μαθηματος");
    private JButton gradeLesson = new JButton("Βαθμος μαθηματος");
    private JTextPane showArea1, showArea2, showArea3;
 
    LessonWindow() {
        j.setTitle("Lessons");
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        j.setSize(700, 500);
        j.setLocationRelativeTo(null);

        p = new JPanel();
        FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER, 30, 10);
        p.setLayout(flow1);
        
        j.setVisible(true);
    }

    public void Lesson() {
        ////////////////////////////////////////////////////////////////////////
        //Lessons///////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        Box boxL = Box.createVerticalBox();
        Box box1 = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();
        //Box box3 = Box.createVerticalBox();
        Box box3 = Box.createHorizontalBox();
        Box box5 = Box.createVerticalBox();

        showArea1 = new JTextPane();
        showArea1.setPreferredSize(new Dimension(100, 200));
        showArea1.setEditable(false);
        showArea2 = new JTextPane();
        showArea2.setPreferredSize(new Dimension(100, 200));
        showArea2.setEditable(false);
        //showArea3=new JTextPane();
        //showArea3.setPreferredSize( new Dimension( 100, 100 ) );
        //showArea3.setEditable(false);

        //Υπαρχων εγγραφες
        JLabel label = new JLabel("Μαθηματα");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        p.add(label);
        JLabel label1 = new JLabel("Αριθμός Μητρώου");
        JScrollPane sp1 = new JScrollPane(showArea1);
        JLabel label2 = new JLabel("Όνομα");
        JScrollPane sp2 = new JScrollPane(showArea2);
        //JLabel label3=new JLabel("Επίθετο");JScrollPane sp3L=new JScrollPane(showArea3);

        box1.add(label1);
        box2.add(label2); //box3.add(label3);
        box1.add(sp1);
        box2.add(sp2); //box3.add(sp3);

        box3.add(box1);
        box3.add(box2);
        p.add(box3);

        //Κουμπια-Lesson
        box5.add(newLesson);
        newLesson.addActionListener(this);
        box5.add(deleteLesson);
        deleteLesson.addActionListener(this);
        box5.add(gradeLesson);
        gradeLesson.addActionListener(this);
                
        p.add(box5);

        //p.add(boxL);
        fillTextArea();
        j.setContentPane(p);
    }

    public void fillTextArea() {
        Database db = new Database();
        String a = "", b = "";
        ArrayList<Lesson> l = new ArrayList<Lesson>();
        db.SelectLesson(l);
        for (int i = 0; i < l.size(); i++) {
            a = l.get(i).getId() + "\n" + a;
            b = l.get(i).getName() + "\n" + b;
            //c=s.get(i).getLastname()+"\n"+c;
        }
        showArea1.setText(a);
        showArea2.setText(b);
        //showArea3L.setText(c);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == newLesson) {

            try {
                Database db = new Database();
                Lesson l = new Lesson();

                String Id = JOptionPane.showInputDialog("Δωσε αριθμο μαθηματος");
                String Name = JOptionPane.showInputDialog("Δωσε ονομα μαθηματος");
                //String Last = JOptionPane.showInputDialog("Δωσε επιθετο");
                if (!Id.equals("") || !Name.equals("") ) {
                l.setId(Id);
                l.setName(Name);
                //l.setLastname(Last);
                db.InsertLesson(l);
                
                fillTextArea();
                }
                else {
                    JOptionPane.showMessageDialog(null, "False Information", "Lesson False Insert", JOptionPane.WARNING_MESSAGE);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        } else if (source == deleteLesson) {
            try {
                String t = JOptionPane.showInputDialog("Δωσε αριθμο μαθηματος");
                Database db = new Database();
                db.DeleteLesson(t);
                fillTextArea();    
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
        else if (source == gradeLesson){
            try {
                String Am = JOptionPane.showInputDialog("Δωσε AM");
                String Id = JOptionPane.showInputDialog("Δωσε ID");
                Database db = new Database();
                db.SearchStudentLessonGrade(Am, Id);
                   
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

}


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
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class StudentWindow implements ActionListener {

    private JFrame j = new JFrame();
    private JPanel p = new JPanel();
    private JButton newStudent = new JButton("Εγγραφή μαθητή/τριας");
    private JButton deleteStudent = new JButton("Διαγγραφή μαθητή/τριας");
    private JButton searchStudent = new JButton("Αναζητηση μαθητή/τριας");
    private JButton addGrade = new JButton("Εγγραφή μαθητή/τριας σε μαθημα");
    private JTextPane showArea1, showArea2, showArea3;

    StudentWindow() {
        j.setTitle("Students");
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        j.setSize(700, 500);
        j.setLocationRelativeTo(null);

        p = new JPanel();
        FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER, 30, 10);
        p.setLayout(flow1);

        j.setVisible(true);
    }

    public void Student() {
        ////////////////////////////////////////////////////////////////////////
        //Students-Window()/////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        Box boxS = Box.createHorizontalBox();
        Box box1S = Box.createVerticalBox();
        Box box2S = Box.createVerticalBox();
        Box box3S = Box.createVerticalBox();
        Box box4S = Box.createHorizontalBox();
        Box box5S = Box.createVerticalBox();

        showArea1 = new JTextPane();
        showArea1.setPreferredSize(new Dimension(100, 200));
        showArea1.setEditable(false);
        showArea2 = new JTextPane();
        showArea2.setPreferredSize(new Dimension(100, 200));
        showArea2.setEditable(false);
        showArea3 = new JTextPane();
        showArea3.setPreferredSize(new Dimension(100, 200));
        showArea3.setEditable(false);

        //Υπαρχων εγγραφες-Student
        JLabel label = new JLabel("Μαθητές");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        boxS.add(label);
        boxS.add(Box.createHorizontalStrut(25));

        JLabel label1 = new JLabel("Αριθμός Μητρώου");
        JScrollPane sp1 = new JScrollPane(showArea1);
        JLabel label2 = new JLabel("Όνομα");
        JScrollPane sp2 = new JScrollPane(showArea2);
        JLabel label3 = new JLabel("Επίθετο");
        JScrollPane sp3 = new JScrollPane(showArea3);

        box1S.add(label1);
        box2S.add(label2);
        box3S.add(label3);
        box1S.add(sp1);
        box2S.add(sp2);
        box3S.add(sp3);

        box4S.add(box1S);
        box4S.add(box2S);
        box4S.add(box3S);
        boxS.add(box4S);
        p.add(boxS);

        //Κουμπια-Student
        box5S.add(newStudent);
        newStudent.addActionListener(this);
        box5S.add(deleteStudent);
        deleteStudent.addActionListener(this);
        box5S.add(searchStudent);
        searchStudent.addActionListener(this);
        box5S.add(addGrade);
        addGrade.addActionListener(this);
        boxS.add(box5S);

        fillTextArea();
        j.setContentPane(p);
    }

    public void fillTextArea() {
        Database db = new Database();
        String a = "", b = "", c = "";
        ArrayList<Student> s = new ArrayList<Student>();
        db.SelectStudent(s);
        for (int i = 0; i < s.size(); i++) {
            a = s.get(i).getAm() + "\n" + a;
            b = s.get(i).getName() + "\n" + b;
            c = s.get(i).getLastname() + "\n" + c;
        }
        showArea1.setText(a);
        showArea2.setText(b);
        showArea3.setText(c);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == newStudent) {

            try {
                Database db = new Database();
                Student s = new Student();

                String Am = JOptionPane.showInputDialog("Δωσε Αριθμο μητρωου");
                String Name = JOptionPane.showInputDialog("Δωσε ονομα");
                String Last = JOptionPane.showInputDialog("Δωσε επιθετο");
                if (!Am.equals("") || !Name.equals("") || !Last.equals("")) {
                    s.setAm(Am);
                    s.setName(Name);
                    s.setLastname(Last);
                    db.InsertStudent(s);
                    fillTextArea();
                } 
                else {
                    JOptionPane.showMessageDialog(null, "False Information", "Student False Insert", JOptionPane.WARNING_MESSAGE);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
        else if (source == deleteStudent) {
            try {
                String t = JOptionPane.showInputDialog("Δωσε Αριθμο μητρωου");

                Database db = new Database();
                db.DeleteStudent(t);
                fillTextArea();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        } 
        else if (source == searchStudent) {
            try {
                String t = JOptionPane.showInputDialog("Δωσε Αριθμο μητρωου");

                Database db = new Database();
                db.SearchStudent(t);

            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
        else if (source == addGrade){
            try {
                String am = JOptionPane.showInputDialog("Δωσε Αριθμο μητρωου");
                String lesson = JOptionPane.showInputDialog("Δωσε Αριθμο μαθηματος");
                String grade = JOptionPane.showInputDialog("Δωσε βαθμο μαθηματος");
                Database db = new Database();
                //int a=0,b=0;
                //a=db.SearchStudentExist(am);
                //b=db.SearchLessonExist(lesson);
                
                //if((a+b)>1){
                    db.InsertGrade(am, lesson, grade);
                //}
                //JOptionPane.showMessageDialog(null,);
                

            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

}

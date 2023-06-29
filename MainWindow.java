
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements ActionListener {

    private JButton Student = new JButton("Mαθητή/τριας");
    private JButton Lesson = new JButton("Mαθημα");
    private JButton exit = new JButton("Έξοδος");
    private boolean onStudent = true; 
    private boolean onLesson = true;

    public MainWindow() {
        setTitle("Εργασια Μαθητες");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        AfterWindow();

    }

    public void AfterWindow() {

        JPanel p = new JPanel(new BorderLayout());
        FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER, 30, 10);
        p.setLayout(flow1);//Οριζει διαταξη πλαισιου
        p.add(Student);
        Student.addActionListener(this);
        p.add(Lesson);
        Lesson.addActionListener(this);
        p.add(exit);
        exit.addActionListener(this);

        setContentPane(p);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    Object source = evt.getSource();
    if (source == exit) {
        dispose();
    } else if (source == Student) {
        try {
            StudentWindow s = new StudentWindow();
            s.Student();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    } else if (source == Lesson) {
        try {
            LessonWindow l = new LessonWindow();
            l.Lesson();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Database {

    private String url = "jdbc:sqlite:C:/Users/Anast45/Documents/NetBeansProjects/JavaApplication2/DB/DB.db";

    public String url() {
        return url;
    }

    public void createDatabase() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {

                String sql_1 = "create table if not exists Students ( "
                        + "StudentAm VARCHAR(10) primary key,"
                        + " FirstName VARCHAR(10),"
                        + " LastName VARCHAR(10))";

                String sql_2 = "create table if not exists Lessons ( "
                        + " LessonId VARCHAR(10) primary key,"
                        + " LessonName VARCHAR(10)"
                        + " )";

                String sql_3 = "create table if not exists Enroll ( "
                        + " StudentAm VARCHAR(10),"
                        + " LessonId VARCHAR(10),"
                        + " Grade VARCHAR(10))";

                Statement statement = conn.createStatement();
                statement.executeUpdate(sql_1);
                statement.executeUpdate(sql_2);
                statement.executeUpdate(sql_3);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void connect() {
        Connection conn = null;
        try {
            // create a connection to the database  
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    ///**Student**//////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public ArrayList<Student> SelectStudent(ArrayList<Student> s) {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {

                Statement statement = conn.createStatement();
                String sql = "SELECT * FROM Students";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Student e = new Student();
                    e.setAm(rs.getString("StudentAm"));
                    e.setName(rs.getString("FirstName"));
                    e.setLastname(rs.getString("LastName"));
                    s.add(e);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sql exception " + ex.getMessage());
        }
        return s;
    }
    
    //137 StudentWindow//
    public void InsertStudent(Student student) {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {

                Statement statement = conn.createStatement();

                String sql = "INSERT INTO Students (StudentAm , FirstName , LastName )"
                        + " VALUES('" + student.getAm() + "',"
                        + "'" + student.getName() + "','"
                        + student.getLastname() + "') ";

                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AM is already in Use");
        }
    }

    //153 StudentWindow//
    public void DeleteStudent(String Am) {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                Statement statement = conn.createStatement();
                String sql = "DELETE FROM Students WHERE StudentAm = "
                        + "'" + Am + "'";

                statement.executeUpdate(sql);
                
                sql = "DELETE FROM Enroll WHERE StudentAm = "
                        + "'" + Am + "'";

                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //165 StudentWindow//
    public void SearchStudent(String Am) {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {

                Statement statement = conn.createStatement();
                String sql = "SELECT * FROM Students";
                ResultSet rs = statement.executeQuery(sql);
                String a = "";
                while (rs.next()) {
                    a = rs.getString("StudentAm");
                    if (a.equals(Am)) {
                        a = a + " , " + rs.getString("FirstName")
                                + " , "
                                + rs.getString("LastName");
                        JOptionPane.showMessageDialog(null, a);
                    } else {
                        a = "";
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //-----------//
    public int SearchStudentExist(String Am) {
        int flag=0;
        try {
            Connection conn = DriverManager.getConnection(url);
        
            if (conn != null) {
                Statement statement = conn.createStatement();
                String sql = "SELECT * FROM Students";
                ResultSet rs = statement.executeQuery(sql);
                String a = "";
                
                while (rs.next()||flag==0) {
                    a = rs.getString("StudentAm");
                    if (a.equals(Am)) {
                        flag=1;
                        break;
                    } else {
                        flag=0;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    //183 StudentWindow//
    public void InsertGrade(String Am,String Id,String Grade) {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {

                Statement statement = conn.createStatement();

                String sql = "INSERT INTO Enroll (StudentAm , LessonId , Grade )"
                        + " VALUES('" + Am + "',"
                        + "'" + Id + "','"
                        + Grade + "') ";

                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "AM is already in Use");
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ///**Lesson**///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public ArrayList<Lesson> SelectLesson(ArrayList<Lesson> s) {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {

                Statement statement = conn.createStatement();
                String SELECT_QUERY = "SELECT * FROM Lessons ";
                ResultSet rs = statement.executeQuery(SELECT_QUERY);
                while (rs.next()) {
                    Lesson e = new Lesson();
                    e.setId(rs.getString("LessonId"));
                    e.setName(rs.getString("LessonName"));

                    s.add(e);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return s;
    }
    
    //129 LessonWindow//
    public void InsertLesson(Lesson lesson) {

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {

                Statement statement = conn.createStatement();
                String sql = "INSERT INTO Lessons (LessonId , LessonName )"
                        + " VALUES('" + lesson.getId() + "',"
                        + "'" + lesson.getName()
                        + "') ";

                statement.executeUpdate(sql);
                // LessonWindow m = new LessonWindow();
                //m.fillTextArea();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ID is already in Use");
            
        }
    }

    //144 LessonWindow//
    public void DeleteLesson(String Id) {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                Statement statement = conn.createStatement();
                String sql = "DELETE FROM Lessons WHERE LessonId = "
                        + "'" + Id + "'";

                statement.executeUpdate(sql);
                sql = "DELETE FROM Enroll WHERE LessonId = "
                        + "'" + Id + "'";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //-----------//
    public int SearchLessonExist(String Id) {
        int flag=0;
        try {
            Connection conn = DriverManager.getConnection(url);
        
            if (conn != null) {
                Statement statement = conn.createStatement();
                String sql = "SELECT * FROM Lessons";
                ResultSet rs = statement.executeQuery(sql);
                String a = "";
                
                while (rs.next()||flag==0) {
                    a = rs.getString("LessonId");
                    if (a.equals(Id)) {
                        flag=1;
                        break;
                    } else {
                        flag=0;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    //156 LessonWindow//
    public void SearchStudentLessonGrade(String Am,String Id) {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {

                Statement statement = conn.createStatement();
                String sql = "SELECT * FROM Enroll";
                ResultSet rs = statement.executeQuery(sql);
                String a = "";
                String b = "";
                while (rs.next()) {
                    a = rs.getString("StudentAm");
                    b = rs.getString("LessonId");
                    if ((a.equals(Am))&&(b.equals(Id))) {
                        a =     " Student Am : "
                                +rs.getString("StudentAm")
                                + " , Lesson Id : "
                                + rs.getString("LessonId")
                                + " , Grade : "
                                + rs.getString("Grade");
                        JOptionPane.showMessageDialog(null, a);
                        
                    } else {
                        a = "";    
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

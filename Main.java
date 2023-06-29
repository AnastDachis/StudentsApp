
import javax.swing.*;

/**
 *
 * @author Anast45
 */
public class Main {

    public static void main(String[] args) {
        Database db = new Database();
        db.createDatabase();

        MainWindow w = new MainWindow();
        w.show();
    }
}

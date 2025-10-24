import javax.swing.JFrame;
import java.awt.EventQueue;

public class Main extends JFrame {

    public Main() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Main());
    }
}

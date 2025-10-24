import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class Main extends JFrame {

    public Main() {
        super("Not Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocation(50, 50);
        setLayout(new FlowLayout());

        add(new JButton("Przycisk 1"));
        add(new JButton("Przycisk 2"));
        add(new JButton("Przycisk 3"));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

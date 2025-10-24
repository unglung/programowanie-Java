import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonPanel extends JPanel implements ActionListener {
    public static final int HEIGHT = 100;
    public static final int WIDTH = 300;
    private JButton greenButton;
    private JButton blueButton;
    private JButton redButton;

    public ButtonPanel() {
        greenButton = new JButton("Green");
        blueButton = new JButton("Blue");
        redButton = new JButton("Red");

        greenButton.addActionListener(this);
        blueButton.addActionListener(this);
        redButton.addActionListener(this);

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(greenButton);
        add(blueButton);
        add(redButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == greenButton)
            setBackground(Color.GREEN);
        else if (source == blueButton)
            setBackground(Color.BLUE);
        else if (source == redButton)
            setBackground(Color.RED);
    }
}

public class Main extends JFrame {
    public Main() {
        super("Test akcji");

        JPanel buttonPanel = new ButtonPanel();
        add(buttonPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

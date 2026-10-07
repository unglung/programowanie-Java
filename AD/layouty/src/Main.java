import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Layout Managers");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 2, 10, 10));

        JButton flow = new JButton("FlowLayout");
        JButton border = new JButton("BorderLayout");
        JButton grid = new JButton("GridLayout");
        JButton box = new JButton("BoxLayout");
        JButton card = new JButton("CardLayout");
        JButton gridBag = new JButton("GridBagLayout");

        add(flow);
        add(border);
        add(grid);
        add(box);
        add(card);
        add(gridBag);

        flow.addActionListener(e -> pokazFlow());
        border.addActionListener(e -> pokazBorder());
        grid.addActionListener(e -> pokazGrid());
        box.addActionListener(e -> pokazBox());
        card.addActionListener(e -> pokazCard());
        gridBag.addActionListener(e -> pokazGridBag());
    }

    private void pokazFlow() {
        JFrame okno = new JFrame("FlowLayout");
        okno.setSize(500, 300);
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        okno.setLayout(new FlowLayout());

        okno.add(new JLabel("FlowLayout:"));
        okno.add(new JButton("Przycisk 1"));
        okno.add(new JButton("Przycisk 2"));
        okno.add(new JButton("Przycisk 3"));
        okno.add(new JButton("Przycisk 4"));
        okno.add(new JButton("Przycisk 5"));

        okno.setLocationRelativeTo(this);
        okno.setVisible(true);
    }

    private void pokazBorder() {
        JFrame okno = new JFrame("BorderLayout");
        okno.setSize(500, 300);
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        okno.setLayout(new BorderLayout());

        okno.add(new JButton("NORTH"), BorderLayout.NORTH);
        okno.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        okno.add(new JButton("WEST"), BorderLayout.WEST);
        okno.add(new JButton("EAST"), BorderLayout.EAST);
        okno.add(new JButton("CENTER"), BorderLayout.CENTER);

        okno.setLocationRelativeTo(this);
        okno.setVisible(true);
    }

    private void pokazGrid() {
        JFrame okno = new JFrame("GridLayout");
        okno.setSize(500, 300);
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        okno.setLayout(new GridLayout(3, 3, 5, 5));

        for (int i = 1; i <= 9; i++) {
            okno.add(new JButton("Pole " + i));
        }

        okno.setLocationRelativeTo(this);
        okno.setVisible(true);
    }

    private void pokazBox() {
        JFrame okno = new JFrame("BoxLayout");
        okno.setSize(500, 300);
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton a = new JButton("Pierwszy");
        JButton b = new JButton("Drugi");
        JButton c = new JButton("Trzeci");

        a.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(a);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(b);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(c);
        panel.add(Box.createVerticalGlue());

        okno.add(panel);

        okno.setLocationRelativeTo(this);
        okno.setVisible(true);
    }

    private void pokazCard() {
        JFrame okno = new JFrame("CardLayout");
        okno.setSize(500, 300);
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        CardLayout layout = new CardLayout();
        JPanel panel = new JPanel(layout);

        panel.add(new JLabel("Karta 1", SwingConstants.CENTER), "1");
        panel.add(new JLabel("Karta 2", SwingConstants.CENTER), "2");
        panel.add(new JLabel("Karta 3", SwingConstants.CENTER), "3");

        JButton next = new JButton("Dalej");
        JButton previous = new JButton("Wstecz");

        next.addActionListener(e -> layout.next(panel));
        previous.addActionListener(e -> layout.previous(panel));

        JPanel przyciski = new JPanel();
        przyciski.add(previous);
        przyciski.add(next);

        okno.setLayout(new BorderLayout());
        okno.add(panel, BorderLayout.CENTER);
        okno.add(przyciski, BorderLayout.SOUTH);

        okno.setLocationRelativeTo(this);
        okno.setVisible(true);
    }

    private void pokazGridBag() {
        JFrame okno = new JFrame("GridBagLayout");
        okno.setSize(500, 300);
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Login:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panel.add(new JTextField(15), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Hasło:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        panel.add(new JPasswordField(15), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        panel.add(new JButton("Zaloguj"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        panel.add(new JButton("Anuluj"), gbc);

        okno.add(panel);

        okno.setLocationRelativeTo(this);
        okno.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}

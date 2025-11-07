import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MyFrame extends JFrame {
    private JTextArea logArea;
    private JButton clearBtn;
    private HashMap<Character, Integer> keyCounter = new HashMap<>();

    public MyFrame() {
        super("Aplikacja Diagnostyczna Zdarzeń");

        logArea = new JTextArea(12, 40);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        clearBtn = new JButton("Wyczyść logi");

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(clearBtn, BorderLayout.SOUTH);

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.setText("");
                log("Logi zostały wyczyszczone.");
            }
        });

        logArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                log("MYSZ: Kliknięcie w punkt: [" + e.getX() + ", " + e.getY() + "]");
            }
        });

        logArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                keyCounter.put(c, keyCounter.getOrDefault(c, 0) + 1);
                log("KLAWISZ: '" + c + "' — liczba wpisów: " + keyCounter.get(c));
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(
                        MyFrame.this,
                        "Czy na pewno chcesz zamknąć aplikację?",
                        "Potwierdzenie zamknięcia",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmation == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void log(String message) {
        logArea.append(message + "\n");
    }
}

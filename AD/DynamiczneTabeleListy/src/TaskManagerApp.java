import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TaskManagerApp extends JFrame {
    private JTextField taskInput;
    private JButton addButton;
    private JButton deleteButton;
    private JList<String> tasksList;
    private JLabel taskCountLabel;
    private DefaultListModel<String> listModel;

    public TaskManagerApp() {
        setTitle("Menedżer Zadań - To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        listModel = new DefaultListModel<>();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));

        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 14));
        taskInput.setPreferredSize(new Dimension(0, 40));

        addButton = new JButton("Dodaj Zadanie");
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.setFont(new Font("Arial", Font.BOLD, 12));

        topPanel.add(taskInput, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        tasksList = new JList<>(listModel);
        tasksList.setFont(new Font("Arial", Font.PLAIN, 13));
        tasksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tasksList.setFixedCellHeight(30);

        JScrollPane scrollPane = new JScrollPane(tasksList);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));

        deleteButton = new JButton("Usuń zaznaczone");
        deleteButton.setPreferredSize(new Dimension(150, 40));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));

        taskCountLabel = new JLabel("Liczba zadań: 0");
        taskCountLabel.setFont(new Font("Arial", Font.BOLD, 13));

        bottomPanel.add(deleteButton, BorderLayout.WEST);
        bottomPanel.add(taskCountLabel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        addButton.addActionListener(e -> addTask());

        taskInput.addActionListener(e -> addTask());

        deleteButton.addActionListener(e -> deleteTask());

        tasksList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = tasksList.getSelectedIndex();
                    if (index != -1) {
                        listModel.remove(index);
                        updateCounter();
                    }
                }
            }
        });

        setVisible(true);
    }

    private void addTask() {
        String taskText = taskInput.getText().trim();

        if (taskText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pole nie może być puste!\nProszę wpisać treść zadania.",
                    "Błąd walidacji",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        listModel.addElement(taskText);

        taskInput.setText("");
        taskInput.requestFocus();

        updateCounter();
    }

    private void deleteTask() {
        int selectedIndex = tasksList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Proszę wybrać zadanie do usunięcia!",
                    "Brak zaznaczenia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        listModel.remove(selectedIndex);

        updateCounter();
    }

    private void updateCounter() {
        int taskCount = listModel.getSize();
        taskCountLabel.setText("Liczba zadań: " + taskCount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagerApp());
    }
}
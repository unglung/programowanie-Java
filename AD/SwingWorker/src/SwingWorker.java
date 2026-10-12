import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SwingWorker extends JFrame {

    private JPanel panelGlowny;
    private JPanel panelDolny;
    private JTextArea poleRaportu;
    private JButton przyciskGeneruj;
    private JProgressBar pasekPostepu;

    public SwingWorker() {
        setTitle("INF.04 - Generator Raportów");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(panelGlowny);

        // Menu Plik
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPlik = new JMenu("Plik");

        JMenuItem zapisz = new JMenuItem("Zapisz Raport");
        zapisz.setAccelerator(
                KeyStroke.getKeyStroke(
                        'S',
                        Toolkit.getDefaultToolkit()
                                .getMenuShortcutKeyMaskEx()
                )
        );

        JMenuItem wyjscie = new JMenuItem("Wyjście");

        zapisz.addActionListener(e -> zapiszRaport());
        wyjscie.addActionListener(e -> System.exit(0));

        menuPlik.add(zapisz);
        menuPlik.addSeparator();
        menuPlik.add(wyjscie);

        menuBar.add(menuPlik);
        setJMenuBar(menuBar);

        // Pasek postępu
        pasekPostepu.setMinimum(0);
        pasekPostepu.setMaximum(100);
        pasekPostepu.setValue(0);
        pasekPostepu.setStringPainted(true);

        // Przycisk generowania
        przyciskGeneruj.addActionListener(e -> generujRaport());
    }

    private void generujRaport() {
        przyciskGeneruj.setEnabled(false);
        pasekPostepu.setValue(0);
        poleRaportu.setText("");

        javax.swing.SwingWorker<String, Integer> worker =
                new javax.swing.SwingWorker<String, Integer>() {

                    @Override
                    protected String doInBackground() throws Exception {
                        StringBuilder raport = new StringBuilder();

                        raport.append("RAPORT INF.04\n");
                        raport.append("====================\n");

                        for (int i = 1; i <= 10; i++) {
                            Thread.sleep(300);

                            raport.append("Wiersz raportu nr ")
                                    .append(i)
                                    .append(" - wygenerowano poprawnie.\n");

                            publish(i * 10);
                        }

                        raport.append("====================\n");
                        raport.append("Koniec raportu.\n");

                        return raport.toString();
                    }

                    @Override
                    protected void process(java.util.List<Integer> lista) {
                        int postep = lista.get(lista.size() - 1);
                        pasekPostepu.setValue(postep);
                    }

                    @Override
                    protected void done() {
                        try {
                            poleRaportu.setText(get());
                            pasekPostepu.setValue(100);

                            JOptionPane.showMessageDialog(
                                    SwingWorker.this,
                                    "Raport został wygenerowany."
                            );

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(
                                    SwingWorker.this,
                                    "Błąd generowania raportu: " + e.getMessage(),
                                    "Błąd",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } finally {
                            przyciskGeneruj.setEnabled(true);
                        }
                    }
                };

        worker.execute();
    }

    private void zapiszRaport() {
        if (poleRaportu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Brak raportu do zapisania!",
                    "Ostrzeżenie",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Zapisz raport");

        chooser.setFileFilter(
                new FileNameExtensionFilter(
                        "Pliki tekstowe (*.txt)", "txt"
                )
        );

        int wynik = chooser.showSaveDialog(this);

        if (wynik == JFileChooser.APPROVE_OPTION) {
            File plik = chooser.getSelectedFile();

            if (!plik.getName().toLowerCase().endsWith(".txt")) {
                plik = new File(plik.getAbsolutePath() + ".txt");
            }

            if (plik.exists()) {
                int odpowiedz = JOptionPane.showConfirmDialog(
                        this,
                        "Plik już istnieje. Czy go nadpisać?",
                        "Potwierdzenie",
                        JOptionPane.YES_NO_OPTION
                );

                if (odpowiedz != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            try {
                Files.writeString(
                        plik.toPath(),
                        poleRaportu.getText()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Raport został zapisany."
                );

            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Błąd zapisu: " + e.getMessage(),
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingWorker okno = new SwingWorker();
            okno.setVisible(true);
        });
    }
}
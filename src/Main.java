import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main {
    public static EditorPanel editorPanel = new EditorPanel();
    public static JFrame jFrame = new JFrame("Textify");

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");

        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem save = new JMenuItem("Save");

        file.add(openFile);
        file.add(newFile);
        file.add(save);

        JMenu theme = new JMenu("Theme");

        JMenuItem newTheme = new JMenuItem("New Theme");
        JMenuItem loadTheme = new JMenuItem("Load Theme");

        loadTheme.addActionListener(new LoadThemeListener());
        newTheme.addActionListener(new ChangeThemeListener());

        theme.add(newTheme);
        theme.add(loadTheme);


        jMenuBar.add(file);
        jMenuBar.add(theme);

        jFrame.setJMenuBar(jMenuBar);

        openFile.addActionListener(new OpenFileListener(editorPanel));
        newFile.addActionListener(new NewFileListener());
        save.addActionListener(new SaveFileListener(editorPanel));


        jFrame.setContentPane(editorPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(640, 480);
        jFrame.setVisible(true);

    }

    private static class ChangeThemeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Theme theme = new Theme();
            theme.textColor = JColorChooser.showDialog(null,"Change Text Color", null);
            editorPanel.editor.setForeground(theme.textColor);
            theme.backgroundColor = JColorChooser.showDialog(null,"Change Background Color", null);
            editorPanel.editor.setBackground(theme.backgroundColor);

            ThemeManager.openedTheme = theme;

            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jFileChooser.showSaveDialog(jFrame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    boolean isFileCreated = jFileChooser.getSelectedFile().createNewFile();

                    if (isFileCreated) {
                        ThemeManager.saveTheme(theme, jFileChooser.getSelectedFile().getPath());
                    } else {
                        JOptionPane.showMessageDialog(jFrame, "Failed to create file " + jFileChooser.getSelectedFile());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class LoadThemeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jFileChooser.showOpenDialog(jFrame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                ThemeManager.openedTheme = ThemeManager.loadTheme(jFileChooser.getSelectedFile().getPath());
                editorPanel.editor.setBackground(ThemeManager.openedTheme.backgroundColor);
                editorPanel.editor.setForeground(ThemeManager.openedTheme.textColor);
            }
        }
    }
}

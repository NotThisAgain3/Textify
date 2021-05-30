import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main {
    public static File openedFile;
    public static EditorPanel editorPanel = new EditorPanel();
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame jFrame = new JFrame("Editor");

        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem newFile = new JMenuItem("New File");
        JMenuItem save = new JMenuItem("Save");

        file.add(openFile);
        file.add(newFile);
        file.add(save);

        jMenuBar.add(file);

        jFrame.setJMenuBar(jMenuBar);

        openFile.addActionListener(new OpenFileActionListener());
        newFile.addActionListener(new NewFileActionListener());
        save.addActionListener(new SaveActionListener());


        jFrame.setContentPane(editorPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(640, 480);
        jFrame.setVisible(true);

    }

    private static class OpenFileActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jFileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                openedFile = jFileChooser.getSelectedFile();

                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new FileReader(openedFile));
                    String line;
                    String total = "";
                    while((line = bufferedReader.readLine()) != null){
                        total += line + "\n";
                    }
                    editorPanel.editor.setText(total);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(openedFile != null){
                String newText = editorPanel.editor.getText();

                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(openedFile));
                    bufferedWriter.write(newText);
                    bufferedWriter.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static class NewFileActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jFileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    boolean isNewFileCreated = jFileChooser.getSelectedFile().createNewFile();
                    if(isNewFileCreated){
                        openedFile = jFileChooser.getSelectedFile();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Failed to create file " + jFileChooser.getSelectedFile());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

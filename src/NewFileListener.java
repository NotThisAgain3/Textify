import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

 class NewFileListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jFileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                boolean isNewFileCreated = jFileChooser.getSelectedFile().createNewFile();
                if(isNewFileCreated){
                    FileManager.openedFile = jFileChooser.getSelectedFile();
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
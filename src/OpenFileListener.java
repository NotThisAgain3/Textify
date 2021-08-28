import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OpenFileListener implements ActionListener {
    private final EditorPanel editorPanel;
    public OpenFileListener(EditorPanel e){
        editorPanel = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jFileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            editorPanel.editor.setText(FileManager.openFile(jFileChooser.getSelectedFile().getPath()));
        }
    }
}
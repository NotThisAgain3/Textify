import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SaveFileListener implements ActionListener {
    private final EditorPanel editorPanel;
    public SaveFileListener(EditorPanel e){
        editorPanel = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(FileManager.openedFile != null){
            String newText = editorPanel.editor.getText();
            FileManager.saveOpenedFileWithText(newText);
        }
    }
}
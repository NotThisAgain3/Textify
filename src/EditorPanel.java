import javax.swing.*;
import java.awt.*;

public class EditorPanel extends JPanel {
    public JTextArea editor = new JTextArea();
    public EditorPanel(){
        this.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scroll);
    }
}

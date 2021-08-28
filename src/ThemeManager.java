import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ThemeManager {
    public static Theme openedTheme;

    private ThemeManager(){}

    public static Theme loadTheme(String path){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            return (Theme) objectInputStream.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n\n Using Default Theme...");
        }

        Theme defaultTheme = new Theme();
        defaultTheme.textColor = new Color(0, 0, 0);
        defaultTheme.backgroundColor = new Color(255, 255, 255);
        return defaultTheme;
    }

    public static void saveTheme(Theme theme, String name) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(name);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(theme);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

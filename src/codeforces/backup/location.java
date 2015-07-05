package codeforces.backup;
import java.io.File;
import javax.swing.*;
public class location
{
    public File choose_location()
    {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        File loc=f.getSelectedFile();
        if(loc==null)
        {
            System.exit(0);
        }       
        return loc;        
    }      
}
package codeforces.backup;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class saveSolution 
{
    void saveSol(String url,File file)
    {
        try
        {
            Document doc=Jsoup.connect(url).get();
            FileWriter fw=new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            Elements e=doc.select("pre.program-source");
            int num=0;
            while(e.size()==0 && num<=10)
            {
                ++num;
                e=doc.select("pre.program-source");                
            }            
            bw.write(e.text());           
            bw.close();
        }
        catch(Exception e)
        {
            System.out.println("OOPS... Something went wrong....");            
        }
    }
       
}
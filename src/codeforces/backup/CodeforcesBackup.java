package codeforces.backup;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class CodeforcesBackup 
{   
    static boolean checkvalidPage(String url,int page)
    {
        try 
        {
            if(page==1)
                return true;
            StringBuilder sb=new StringBuilder();
            StringBuilder sb2=new StringBuilder();
            sb.append(url);
            sb2.append(url);
            sb.append(page);
            sb2.append(page-1);
            
            String url1=sb.toString();
            String url2=sb2.toString();
            
            Document doc1=Jsoup.connect(url1).get();
            Document doc2=Jsoup.connect(url2).get();
            Elements table_rows1=doc1.select("tr[data-submission-id]");
            Elements table_rows2=doc2.select("tr[data-submission-id]");
            /*check for the equal id's*/
            String id1=table_rows1.get(0).attr("data-submission-id");
            String id2=table_rows2.get(0).attr("data-submission-id");
            if(id1.equals(id2))
                return false;
            else
                return true;                          
        } 
        catch (IOException ex) 
        {
            System.out.println("Connection Error......");
        }
         return true;          
    }
    
    public static void main2(String handle) 
    {
        /*display prompt to choose location*/
        location loc=new location();
        File f=loc.choose_location();        
        
        String id="http://codeforces.com/submissions/"+handle+"/page/";
        int page=1;
        fetchSolution obj=new fetchSolution();
        while(checkvalidPage(id,page)==true)
        {            
            StringBuilder sb=new StringBuilder();
            sb.append(id);
            sb.append(page);
            String url=sb.toString();
            obj.fetch(url,f);
            ++page;            
        }  
        String info="Successful backup of "+obj.number_of_solution+" problems.";
        JOptionPane.showMessageDialog(null, info, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }    
}

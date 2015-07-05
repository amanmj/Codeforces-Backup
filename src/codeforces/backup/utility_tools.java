package codeforces.backup;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class utility_tools 
{  
    private final String setPlainText = "\033[0;0m";
    private final String setBoldText = "\033[0;1m";
    private Hashtable ht;
    utility_tools()
    {
        ht=new Hashtable();  
        ht.put("JavaScript",".js");
        ht.put("Ruby",".rb");
        ht.put("PyPy 2",".py");
        ht.put("Python 3",".py");
        ht.put("Python 2",".py");
        ht.put("PHP",".php");
        ht.put("GNU C++11",".cpp");
        ht.put("GNU C11",".cpp");
        ht.put("Java 8",".java");
        ht.put("Java 7",".java");
        ht.put("Java 6",".java");
        ht.put("GNU C++",".cpp");
        ht.put("GNU C++0x",".cpp");
        ht.put("GNU C",".c");
        ht.put("MS C++",".cpp");
        ht.put("MS C#", ".cs");
        ht.put("Mono C#",".cs");
        ht.put("D",".d");
        ht.put("Go",".go");
        ht.put("Haskell",".hs");
        ht.put("Perl",".pl");
        ht.put("Scala",".scala");
        ht.put("Delphi",".txt");
        ht.put("FPC",".pas");  
        ht.put("Ocaml",".ml");
    }     
    
    String getID(Element check)
    {        
        Elements anchor=check.select("a");
        if(anchor.size()==0)
            return "null";
        String ans=anchor.attr("submissionid");
        return ans;
    }
    
    String getQName(Element check)
    {
        Elements anchor=check.select("a");
        String ans=anchor.text();
        return ans;        
    }
    
    String getStatus(Elements stat)
    {        
        String ans=stat.attr("submissionverdict");
        return ans;
    }
    
    String getName(Element check)
    {
        String ans=check.text();
        return ans;
    }
    
    String generateURl(String questionID,String questionName)
    {
        String test="";
        for(int i=0;i<questionName.length();++i)
        {
            char curr=questionName.charAt(i);
            if(curr>='0' && curr<='9')
                test+=curr;
            else
                break;
        }
        String ans="http://codeforces.com/contest/";
        ans = ans + test + "/submission/" +questionID;        
        return ans;
    }
    
    String getExtension(String language)
    {
        String ans=(String) ht.get(language);
        if(ans==null || ans.equals("null"))
            ans=".txt";      
        return ans;
    }
    
    void saveSolution(File f,String extension,String solutionURL,String questionName)
    {      
        questionName=questionName.replace(":","");
        questionName=questionName.replace("/","");
        questionName=questionName.replace("\\","");
        questionName=questionName.replace("?","");
        questionName=questionName.replace("*","");
        String fileName=questionName+extension;       
        File file=new File(f,fileName);
        if(!file.exists()) 
        {
            try 
            { 
               /*create a new file if it does not exist*/
               file.createNewFile();
            } 
            catch (IOException ex) 
            {
                System.out.println("File could not be created........");
            }
        }
        saveSolution obj=new saveSolution();
        obj.saveSol(solutionURL,file);     
            
        System.out.println("Backup of "+fileName+" complete.");
    }
    
}

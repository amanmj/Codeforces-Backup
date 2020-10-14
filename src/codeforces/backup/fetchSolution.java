package codeforces.backup;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class fetchSolution 
{
    static public int number_of_solution=0;
    private final String setPlainText = "\033[0;0m";
    private final String setBoldText = "\033[0;1m";
    private HashSet prob=new HashSet();
    void fetch(String id,File f)
    {
       // Try hard yes you try hard
	 try 
        {
            utility_tools util=new utility_tools();                      
            
            Document doc=Jsoup.connect(id).get();   
            
            /*fetch all the rows in the submission table*/
            Elements table_rows=doc.select("tr[data-submission-id]");
            
            for(int i = 0 ; i< table_rows.size() ; ++i)
            {
                Element current_row=table_rows.get(i);
                Elements table_columns=current_row.select("td");
                
                /*get the first column*/
                Element check=table_columns.get(0);
                String questionID=util.getID(check);
                
                /*this is either a gym problem or solution is not accessible*/
                if(questionID.equals("null"))
                {                   
                    /*Get the question name from the third column*/
                    check=table_columns.get(3);
                    String question_name=util.getQName(check);
                    if(!prob.contains(question_name))
                    {
                        System.out.println(question_name+ " is either a gym problem or its solution is not public");
                        prob.add(question_name);
                    }
                     /*check for the next submission*/
                    continue;
                }
                
                /*Get the question name from the third column*/
                check=table_columns.get(3);
                String question_name=util.getQName(check);
                
                /*select status of problem : AC || TLE || WA || RE || CE*/
                check=table_columns.get(5);
                Elements stat=check.select("span");
                String status=util.getStatus(stat);
                /*if status if accepted only then backup the solution*/
                if(!status.equals("OK"))
                {
                    continue;                    
                }
                ++number_of_solution;
                
                /*Get the language*/
                check=table_columns.get(4);
                String language=util.getName(check);
                String solutionURL=util.generateURl(questionID,question_name);
                String extension=util.getExtension(language);
                util.saveSolution(f,extension,solutionURL,question_name);                
            }        
        } 
        catch(IOException ex) 
        {
            System.out.println("Connection Error....... Try Again.....");
        }
    }
        
}

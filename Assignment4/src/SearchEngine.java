
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */
public class SearchEngine {
    InvertedPageIndex invpg= new InvertedPageIndex();;
   
  
    public void performAction(String actionMessage) throws NoSuchElementException,IOException{
         try{
        	 String s[]= actionMessage.split(" ");
        String a1;
        String a2;
        switch (s[0]){
            case "addPage":
                 a1= s[1];
                PageEntry p1= new PageEntry(a1);
                invpg.addPage(p1);
                break;
                
            case "queryFindPagesWhichContainWord":
                a1= s[1];
                String ay[]={a1};
                MySet<PageEntry> reqset=invpg.getPagesWhichContainWord(a1);
                if (reqset.IsEmpty())
            		throw new NoSuchElementException ("No webpage contains word " + a1);
                MySet<SearchResult> srsetx= new MySet<SearchResult>();
            	for(int i=0;i<reqset.giveSize();i++){
            		PageEntry pgentry=new PageEntry(reqset.nthobj(i).pageNames());
            		float relev=pgentry.getRelevanceOfPage(ay);
            		SearchResult srresult= new SearchResult(pgentry,relev);
            		srsetx.addElement(srresult);
            	}
            	MySort msortx= new MySort();
            	ArrayList<SearchResult> alx=msortx.sortThisList(srsetx);
            	
                String stx="queryFindPagesWhichContainWord "+ a1+": ";
                     
                     for (int i=0;i<alx.size();i++){
                         stx= stx +(alx.get(i).getPageEntry().pageNames())+ ", " ;
                     }
                     System.out.println(stx.substring(0, stx.length()-2));
                     break;
                     
            case "queryFindPositionsOfWordInAPage":
                a1=s[1];
                a2=s[2];
                String a8= new String(a1);
                a8=a8.toLowerCase();
                if (a8.equals("stacks"))
            		a8="stack";
            	if (a8.equals("structures"))
            		a8="structure";
            	if (a8.equals("applications"))
            		a8="application";
            	if (a8.equals("functions"))
            		a8="function";
                MySet<Integer> reqset1=invpg.getPageIndexesWhichContainWord(a8,a2);
                   String ss="queryFindPositionsOfWordInAPage "+ a1+" "+a2+": ";
                        if (reqset1.giveSize()==0)
                        	throw new NoSuchElementException ("Webpage "+a2+" does not contain word "+ a1);
                        for (int i=0;i<reqset1.giveSize();i++){
                        	
                            ss= ss  +(reqset1.nthobj(i))+ ", " ;
                        }
                        System.out.println(ss.substring(0, ss.length()-2));
                        break;
                        
                        
            case "queryFindPagesWhichContainAllWords":
            	int len= s.length;
            	String strs[]=new String[s.length-1];
            	String request2="";
            	for(int i=1;i<len;i++){
            		strs[i-1]=s[i];
            		request2= request2 + s[i]+" ";
            	}
            	MySet<PageEntry> reqset2=invpg.andquery(strs);
            	if (reqset2.IsEmpty())
            		throw new NoSuchElementException ("No page contains all of the words.");
            	MySet<SearchResult> srset= new MySet<SearchResult>();
            	for(int i=0;i<reqset2.giveSize();i++){
            		PageEntry pgentry=new PageEntry(reqset2.nthobj(i).pageNames());
            		float relev=pgentry.getRelevanceOfPage(strs);
            		SearchResult srresult= new SearchResult(pgentry,relev);
            		srset.addElement(srresult);
            	}
            	MySort msort= new MySort();
            	ArrayList<SearchResult> al=msort.sortThisList(srset);
            	
                String st1="queryFindPagesWhichContainAllWords "+ request2+": ";
                     
                     for (int i=0;i<al.size();i++){
                         st1= st1  +(al.get(i).getPageEntry().pageNames())+ ", " ;
                     }
                     System.out.println(st1.substring(0, st1.length()-2));
                     break;
                     
            case "queryFindPagesWhichContainAnyOfTheseWords":
            	int leng= s.length;
            	String strst[]=new String[s.length-1];
            	String request1="";
            	for(int i=1;i<leng;i++){
            		strst[i-1]=s[i];
            		request1= request1 + s[i]+" ";
            	}
            	MySet<PageEntry> reqset3=invpg.orquery(strst);
            	if (reqset3.IsEmpty())
            		throw new NoSuchElementException ("No page contains any of these words");
            	MySet<SearchResult> srset1= new MySet<SearchResult>();
            	for(int i=0;i<reqset3.giveSize();i++){
            		PageEntry pgentry=new PageEntry(reqset3.nthobj(i).pageNames());
            		float relev=pgentry.getRelevanceOfPage(strst);
            		SearchResult srresult= new SearchResult(pgentry,relev);
            		srset1.addElement(srresult);
            	}
            	MySort msort1= new MySort();
            	ArrayList<SearchResult> al1=msort1.sortThisList(srset1);
            	
                String st2="queryFindPagesWhichContainAnyOfTheseWords "+ request1+": ";
                     
                     for (int i=0;i<al1.size();i++){
                         st2= st2  +(al1.get(i).getPageEntry().pageNames())+ ", " ;
                     }
                     System.out.println(st2.substring(0, st2.length()-2));
                     break;
                     
            case "queryFindPagesWhichContainPhrase":
            	int lengt=s.length;
            	String strsr[]=new String[s.length-1];
            	String request="";
            	for(int i=1;i<lengt;i++){
            		strsr[i-1]=s[i];
            		request= request + s[i]+" ";
            	}
            	MySet<SearchResult> srresult= invpg.getPagesWhichContainPhrase(strsr);
            	if (srresult.IsEmpty())
            		throw new NoSuchElementException ("No webpage contains the given phrase.");
            	MySort msort2= new MySort();
            	ArrayList<SearchResult> al2=msort2.sortThisList(srresult);
            	
                String st3="queryFindPagesWhichContainPhrase "+ request+": ";
                     
                     for (int i=0;i<al2.size();i++){
                         st3= st3  +(al2.get(i).getPageEntry().pageNames())+ ", " +(al2.get(i).getRelevance()) +","  ;
                     }
                     System.out.println(st3.substring(0, st3.length()-2));
                     break;
        }
         }
         catch (NoSuchElementException e1)
         {
             System.out.println(e1.getMessage());
         
         }  catch (IOException e1)
         {
             System.out.println(e1.getMessage() +" not found");
         
         }
         }
    }
    


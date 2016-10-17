/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PageEntry {
    PageIndex pi= new PageIndex();
    String sentence;
    String[] conwords= {"a","an","the","they","these","for","is","are","of","or","and","does","will","whose","isnt","arent","did","doesnt","didnt","ill","im","would","wouldnt"};
    public boolean stringsame(String str){
        boolean res= false;
        for (int i=0; (i<conwords.length)&&(res==false);i++){
            if (conwords[i].equals(str))
                res=true;
        }
        return res;
    }
    public PageEntry(String pageName) throws FileNotFoundException,IOException{
        sentence=pageName;
        
           
	
			 String text = new String(Files.readAllBytes(Paths.get(pageName)), StandardCharsets.UTF_8);
                       // text= text.replaceAll("[^\\w\\s]"," ").replaceAll("\\s+"," ");
			 text= text.replaceAll("[-{}\\[\\]<>=().,;'\"?#!:]"," ").replaceAll("\\s+"," ");
                        text=text.toLowerCase();
                        String[] words= text.split(" ");
                        int j=1;
                      for (int i=0; i<words.length;i++) {
				String xy=words[i];
                                if(!stringsame(xy)){
                                	if (xy.equals("stacks"))
                                		xy="stack";
                                	if (xy.equals("structures"))
                                		xy="structure";
                                	if (xy.equals("applications"))
                                		xy="application";
                                	if (xy.equals("functions"))
                                		xy="function";
                                    Position xypos= new Position(this,i+1);
                                    xypos.PageEntryListPosition=j;
                                    j++;
                                    pi.addPositionForWord(xy,xypos);
                                }
			}
		} 
    public PageIndex getPageIndex(){
        return pi;
    }
    public String pageNames(){
        return sentence;
    }
    public float getRelevanceOfPage(String str[]){
    	float a1=0;
    	for (int j=0; j<str.length;j++){
    		for (int i=0; i<pi.entries.getSize();i++){
    			if (pi.entries.beta(i).word().equals(str[j])){
    				MyLinkedList<Integer> indices=pi.entries.beta(i).getAllIndicesforsamePage(this.pageNames());
    				for (int k=0; k<indices.getSize();k++){
    					int index = indices.beta(k);
    					a1=a1+ (float)(1/(float)(index*index));
    				}
    			}
    		}
    	}
    	return a1;
    }
}

/*
/ * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */
public class PageIndex {
    MyLinkedList <WordEntry> entries;
    public PageIndex(){
        entries= new MyLinkedList<WordEntry>();
    }
    public AVLTree index(String str){
        for (int i=0;(i<entries.getSize());i++){
            WordEntry entry= entries.beta(i);
            if (entry.word().equals(str)){
                AVLTree posts=entry.getAllPositionsForThisWord();
                return posts;
            }
                }return null;
    }
    public boolean containstring(String str){
        boolean res= false;
        for (int i=0;(i<entries.getSize())&& res==false;i++){
            WordEntry entry= entries.beta(i);
           res= (entry.word().equals(str));
        }
        return res;
    }
    public void addPositionForWord(String str, Position p){
        if (containstring(str)){
            for (int i=0;i<entries.getSize();i++){
            WordEntry entry= entries.beta(i);
            if (entry.word().equals(str)){
                entry.addPosition(p);
            }
        }
        }
        else {
            WordEntry wen= new WordEntry(str);
            wen.addPosition(p);
            entries.insertAtEnd(wen);
        }
    }
    public MyLinkedList<WordEntry> getWordEntries(){
        return entries;
    }
}

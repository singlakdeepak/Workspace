/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */
public class Position {
    PageEntry page;
    public int wordPosition;
    public int PageEntryListPosition;
    public Position(PageEntry p,int wordIndex ){
        page=p;
        wordPosition = wordIndex;
    }
    public PageEntry getPageEntry(){
        return page;
    }
    public int getWordIndex(){
        return wordPosition;
    }
}

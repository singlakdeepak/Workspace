import java.util.ArrayList;
import java.util.*;

public class MySort {
	ArrayList<SearchResult> al= new ArrayList<SearchResult>();

public ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
	
	for (int i=0;i<listOfSortableEntries.giveSize();i++){
		SearchResult ax= listOfSortableEntries.nthobj(i);
		if (al.isEmpty())
			al.add(ax);
		else {  al.add(ax);
		for (int j=al.size()-2;j>=0;j--){
			if(al.get(j).compareTo(ax)<0){
				al.set(j+1, al.get(j));
				al.set(j, ax);
			}else break;
		}
				 
		 }
	}return al;
}
}


public class SearchResult{
PageEntry pager;
public float relev;
public SearchResult(PageEntry p, float r){
	pager=p;
	relev=r;
}
public PageEntry getPageEntry(){
	return pager;
}
public float getRelevance(){
	return relev;
}
public float compareTo(SearchResult otherObject){
	return (float)(this.getRelevance()-otherObject.getRelevance());
}
}

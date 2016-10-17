package newp1;


public class Taxi {
Vertex currentposition;
int availability;
String taxiname;
int initialtime;
int finaltime;
Vertex nextposition;
public Taxi(String taxiname,Vertex currentposition,int availability){
	this.taxiname=taxiname;
	this.currentposition=currentposition;
	this.availability=availability;
	initialtime=0;
	finaltime=0;
}
public String givetaxiname(){
	return taxiname;
}
public Vertex givecurrpos(){
	return currentposition;
}
public int giveavl(){
	return availability;
}
public void chngpos(Vertex newpos){
	currentposition=newpos;
}
public void chngavl(int i){
	availability=i;
	finaltime=initialtime +i;
}
public void setTime(int t){
	initialtime=t;
	if (finaltime<=initialtime){
		if (nextposition!=null){
		currentposition=nextposition;
		availability=0;
		finaltime=initialtime;
		}
	}
}
}


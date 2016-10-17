package newp;

public class Taxi {
	String currentposition;
int availability;
String taxiname;
int initialtime;
int finaltime;
String nextposition;
public Taxi(String taxiname,String currentposition,int availability){
	this.taxiname=taxiname;
	this.currentposition=currentposition;
	this.availability=availability;
	initialtime=0;
	finaltime=0;
}
public String givetaxiname(){
	return taxiname;
}
public String givecurrpos(){
	return currentposition;
}
public int giveavl(){
	return availability;
}
public void chngpos(String newpos){
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

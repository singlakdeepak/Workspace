package newp;
//java library: Arraylist used, List used
import java.util.ArrayList;
import java.util.List;

public class TaxiList {
public List<Taxi> taxis;

public TaxiList(){
	taxis=new ArrayList();
}

public void addtaxi(String taxiname,String position){
	for (int i=0;i<taxis.size();i++){
		if(taxis.get(i).givetaxiname().equals(taxiname)){
			System.out.println("Taxi "+taxiname+" already exists in the city.");
		return;
		}
		}
	 Taxi t1= new Taxi(taxiname,position,0);
	 if(taxis.size()==0){
		 t1.initialtime=0;
		 t1.finaltime=0;
	 }else{
	 t1.initialtime=taxis.get(0).initialtime;
	 t1.finaltime=t1.initialtime;
	 }
	 taxis.add(t1);
}
public void setTimeForAll(int t){
	for (int i=0; i<taxis.size();i++){
		taxis.get(i).setTime(t);
	}
}

public void setavlfortaxi(Taxi t1, int t, String destination){
	for (int i=0; i<taxis.size();i++){
		if (taxis.get(i).givetaxiname().equals(t1.givetaxiname())){
			taxis.get(i).chngavl(t);
			taxis.get(i).nextposition=destination;
		}
	}
}
}

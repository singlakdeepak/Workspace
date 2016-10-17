import org.opencv.core.Core;
import org.opencv.core.CvType;
//import org.opencv.core.CvType;
import org.opencv.core.Mat;
//import org.opencv.core.MatOfDouble;
//import org.opencv.core.Size;
import org.opencv.imgcodecs.*;
import java.lang.Object;
import java.util.Random;
import java.util.ArrayList;

public class main {
	 static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	 
  }
	public static void main( String[] args )
    {		
		//--------------------This part has been used for reading the faces from the database, each face has 11 expressions except first face 
		//--------------------and these have been stored in an Array of mat
		String[] faceType={"centerlight","glasses","happy","leftlight","noglasses","normal","rightlight","sad","sleepy","surprised","wink"};
		
        System.out.println("Welcome to OpenCV " + Core.VERSION);
       ArrayList<Mat> FaceList=new ArrayList<Mat>();
       ArrayList<Mat> TestFaceList=new ArrayList<Mat>();
      Mat faceListar= new Mat();
       
       int no_trainFaces=5;//define types of faces
       
       
       int[] labels= new int[no_trainFaces*11];
       int labelno=0;
       Mat imgFrame = new Mat();
       String imgPath="";
       for (int i=1;i<=no_trainFaces;i++){
    	   for (int j=0;j<=10;j++){
    		   if (i<10)
       imgPath=System.getProperty("user.dir")+"\\yalefaces" +"\\subject0"+i+"."+ faceType[j]+".jpg";
    		   else
     imgPath=System.getProperty("user.dir")+"\\yalefaces" +"\\subject"+i+"."+ faceType[j]+".jpg";   
    		  // System.out.println(imgPath);
     //  Imgcodecs  face=new Imgcodecs();
       imgFrame = Imgcodecs.imread(imgPath,Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
      // imgFrame.convertTo(imgFrame, CvType.CV_8SC1);
      
       
       int n_rows=imgFrame.rows();
       int n_cols=imgFrame.cols();
       int k=0;
       if (imgFrame.empty())
       	  System.out.println("empty");
          else
          {
        	  Core.normalize(imgFrame,imgFrame,0,255,Core.NORM_MINMAX,CvType.CV_8UC1);
        	  
        	  for (int a=0; a<n_rows;a++){
        		  for (int b=0;b<n_cols;b++){
        			 double[] data= imgFrame.get(a, b);
        			 faceListar.put(b +a*n_cols,k,data);
        		  }
        	  }
        	  
        	  //FaceList.add(imgFrame);
        labels[labelno]=i;
        labelno++;
          k++;
          } 
          }
       }
      
     //  System.out.println(n_rows +" "+n_cols);
       //-----------------This part ends here i.e. all 15 faces have been stored-------------
       
       for (int i=1;i<=5;i++){
    	   for (int j=0;j<=10;j++){
    		   if (i<10)
       imgPath=System.getProperty("user.dir")+"\\testyalefaces" +"\\subject0"+i+"."+ faceType[j]+".jpg";
    		   else
     imgPath=System.getProperty("user.dir")+"\\testyalefaces" +"\\subject"+i+"."+ faceType[j]+".jpg";   
    		  // System.out.println(imgPath);
     //  Imgcodecs  face=new Imgcodecs();
       imgFrame = Imgcodecs.imread(imgPath,Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
       //imgFrame.convertTo(imgFrame, CvType.CV_8SC1);
    
       if (imgFrame.empty())
       	  System.out.println("empty");
          else
          {
        	   Core.normalize(imgFrame,imgFrame,0,255,Core.NORM_MINMAX,CvType.CV_8UC1);
        TestFaceList.add(imgFrame);
          
          }
    	   }
       }
        //System.out.println(FaceList.get(163));
       
      //face.imwrite("smileface",imgFrame);
      // if (imgFrame.empty())
    	//   System.out.println("empty");
       //else
       //System.out.println(imgFrame);
       
   /*    //------------1.Correlation-------------------------------------------------------
      // Mat dst= new Mat();
       int sizeFaceL=FaceList.size();
      // System.out.println(sizeFaceL);
       int label=0;
       int k=0;
       int[] labelcal= new int[sizeFaceL];
       //System.out.println(labelcal[25]);
     //  System.out.println(dst.dump());
      // MatOfDouble abc= new MatOfDouble();
      // Mat req=FaceList.get(12);
       Mat testface= TestFaceList.get(0);
       for(int i=50;i<testface.rows();i++){
    	   for (int j=80; j<testface.cols()-50;j++){
    		   double cmin= Integer.MAX_VALUE;
    		   for ( k=0;k<sizeFaceL;k++){
    			   double[] data= testface.get(i, j);
    		   //float pix_value = (float)Math.abs(data[0]/(float)255);
    		   //System.out.println(pix_value);
    		   float pix_value = (float)(data[0]/(float)255);
    		   double[] traindata= FaceList.get(k).get(i, j);
    		//   float trainpix_value= (float)Math.abs(traindata[0]/(float)255);
    		   float trainpix_value= (float)(traindata[0]/(float)255);
    		   if ((pix_value!=1 )&&(trainpix_value!=1)){
    		 double value = Math.pow((pix_value - trainpix_value),2);
    		 //System.out.println(value);
    		 if (value<cmin){
    			 cmin=value;
    			label= k;     			 
    		 }
    		   }
    	//	   System.out.println(label);
    		   labelcal[label]++;
    		   }
    	   }
       }
       int m=0;
       int FinalLabel=0;
       for (int i=0; i<sizeFaceL;i++){
    	 //  System.out.println(labelcal[i]);
    	   if (labelcal[i]>=m){
    		   m=labelcal[i];
    		   FinalLabel=i;
    	   }
       }
       
       System.out.println(labels[FinalLabel]);
       //System.out.println(testface.dump());
       
       //-------------- Correlation ends here---------------------------------------------------------
       
       */
       
       
       //----------------------------2. EigenFaces---------------------------------------------------
       int new_d= 1000;
     //  Mat orthoMat= Mat.eye(n_rows*n_cols,new_d,CvType.CV_8UC1);
       
    }
}


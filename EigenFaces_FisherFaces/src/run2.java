import static org.opencv.core.Core.divide;
import static org.opencv.core.Core.invert;
import static org.opencv.core.Core.mulTransposed;

import org.opencv.core.Core;
import org.opencv.core.CvType;
//import org.opencv.core.CvType;
import org.opencv.core.Mat;
//import org.opencv.core.MatOfDouble;
//import org.opencv.core.Size;
import org.opencv.imgcodecs.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Object;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class run2 {
	 static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	 
  }
	public static void main( String[] args ) throws IOException
    {		
		//--------------------This part has been used for reading the faces from the database, each face has 11 expressions except first face 
		//--------------------and these have been stored in an Array of mat
		String[] faceType={"centerlight","glasses","happy","leftlight","noglasses","normal","rightlight","sad","sleepy","surprised","wink"};
		
        System.out.println("Welcome to OpenCV " + Core.VERSION);
     //  ArrayList<Mat> FaceList=new ArrayList<Mat>();
     //  ArrayList<Mat> TestFaceList=new ArrayList<Mat>();
       
       //------------------------initialize the variables here before starting the program------------ 
        int n_train=51;
       int n_test=6;
       int n_rows=243;
       int n_cols=320;
       int no_trainFaces=5;//define types of faces
       //-----------------------end of initialization------------------------------------------------
       
       Scanner s = new Scanner(System.in);
      Mat faceListar= Mat.zeros(n_rows*n_cols, n_train,CvType.CV_32FC1);
      Mat testface= Mat.zeros(n_rows*n_cols, n_test, CvType.CV_32FC1);
      
      Mat mean=Mat.zeros(n_rows*n_cols,1,CvType.CV_32FC1);
       
       
       int k=0;//it represent the face number
       int[] labels= new int[n_train];
       int labelno=0;
       Mat imgFrame = new Mat();
       String imgPath="";
       double matrix[]=new double[n_rows*n_cols];
       for(int w=0;w<n_rows*n_cols;w++){
    	   matrix[w]=0;
       }
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
      
     //  Mat tempor=Mat.zeros(n_rows*n_cols,1,CvType.CV_32FC1);
      
       
      // double[] abc=faceListar.get(0, 0);
       //System.out.println(abc[0]);
       if (imgFrame.empty())
       	  System.out.println("");
          else
          {
        //	  System.out.println(i);
        	  Core.normalize(imgFrame,imgFrame,0,255,Core.NORM_MINMAX,CvType.CV_8UC1);
        //	  double temp=0;
       // 	  tempor=Mat.zeros(n_rows*n_cols,1,CvType.CV_32FC1);
        	  for (int a=0; a<n_rows;a++){
        	//	  temp=0;
        		  for (int b=0;b<n_cols;b++){
        			 double data= (imgFrame.get(a, b))[0];
        			// System.out.println(faceListar.dump());
        			 
        			 faceListar.put(b +a*n_cols,k,data);
        			//tempor.put(b +a*n_cols,k,data);
        			
        		//	System.out.println(faceListar.dump());
        			// System.out.println(ij);
        		  }
        		  
        	  }
        	   //double[] abc=faceListar.get(8111, 0);
  			//System.out.println(abc[0]);
        	 // FaceList.add(imgFrame);
        labels[labelno]=i;
        System.out.println(i);
        labelno++;
          k++;
          } 
       
       }
    	   
       //Core.add(tempor,mean,mean);
       
       
          }
       for (int n_samp=0;n_samp<n_train;n_samp++){
       for(int w=0;w<n_rows*n_cols;w++){
       	   matrix[w]+=(faceListar.get(w, n_samp))[0];
       	   //System.out.println(matrix[w]);
    	   
       }
       }
       for(int w=0;w<n_rows*n_cols;w++){
    	 //  System.out.println(matrix[w][0]);
    	   mean.put(w, 0,matrix[w]/n_train);
    	  
       }
    
      // Core.divide(n_train,mean, mean);
       Mat one=Mat.ones(1,n_train,CvType.CV_32FC1);
       Mat mean_x=Mat.zeros(n_rows*n_cols, n_train,CvType.CV_32FC1);
     //  System.out.println(mean.rows() +" "+mean.cols());
     //  System.out.println(one.rows() +" "+one.cols());
     //  System.out.println(mean_x.rows() +" "+mean_x.cols());
      Mat ty=Mat.zeros(1, 1,CvType.CV_32FC1) ;
      // Mat dst = Mat.zeros(n_rows*n_cols, 1, CvType.CV_32FC1);
       //Core.transpose(mean, dst);
      //System.out.println((mean.get(0, 0))[0]); 
       Core.gemm(mean,one,1,ty,0,mean_x,0);
      // Core.multiply(mean,one, mean_x);
       Mat phie=Mat.zeros(n_rows*n_cols, n_train,CvType.CV_32FC1);
       
       Core.subtract(faceListar, mean_x, phie);
     
       //check whether phie is formed or not-------------------------------------
       // for(int w=0;w<n_rows*n_cols;w++){
       //System.out.println((phie.get(w, 0))[0]);
      
      // }
      //--------------------------------------------------------------------------
       
       //  System.out.println((mean_x.get(150, 0))[0]); 
       
       
       System.out.println(faceListar.rows() +" "+faceListar.cols());
       //-----------------This part ends here i.e. all 15 faces have been stored-------------
       
  //-----------------This part ends here i.e. all 15 faces have been stored-------------
        k=0;
       for (int i=1;i<=n_train;i++){
    	   for (int j=0;j<=10;j++){
    		   if (i<10)
       imgPath=System.getProperty("user.dir")+"\\testyalefaces" +"\\subject0"+i+"."+ faceType[j]+".jpg";
    		   else
     imgPath=System.getProperty("user.dir")+"\\testyalefaces" +"\\subject"+i+"."+ faceType[j]+".jpg";   
    		  // System.out.println(imgPath);
     //  Imgcodecs  face=new Imgcodecs();
       imgFrame = Imgcodecs.imread(imgPath,Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
       //imgFrame.convertTo(imgFrame, CvType.CV_8SC1);
       int krtgrg;
       if (imgFrame.empty())
       	  //System.out.println("empty");
    	   krtgrg=0;
          else
          {
        	   Core.normalize(imgFrame,imgFrame,0,255,Core.NORM_MINMAX,CvType.CV_8UC1);
        //TestFaceList.add(imgFrame);
        	   for (int a=0; a<n_rows;a++){
         		  for (int b=0;b<n_cols;b++){
         			 double[] data= imgFrame.get(a, b);
         			// System.out.println(faceListar.dump());
         				
         			 testface.put(b +a*n_cols,k,data);
         			
         			
         		//	System.out.println(faceListar.dump());
         			// System.out.println(ij);
         		  }
         	  }
        	   k++;
          }
    	   }
       } 
       //--------------------trainfaces and the testfaces have been stored in the matrices with trainfaces in n^2*M format
       Mat auxEs= Mat.zeros(n_train, n_train, CvType.CV_32FC1);
       Core.mulTransposed(phie, auxEs, true);
     
       //--------check whether auxEs is formed--------------
     //  for(int w=0;w<n_train;w++){
       //    System.out.println((auxEs.get(w, 0))[0]);
         //  }
       //-----------------------------------------------------
       
       Mat eigenValues= Mat.zeros(n_train,1,CvType.CV_32FC1);
       Mat orgWeights = Mat.zeros(n_rows*n_cols,n_train,CvType.CV_32FC1);
       Mat TransWeights = Mat.zeros(n_train,n_train,CvType.CV_32FC1);
       Core.SVDecomp(auxEs, eigenValues, orgWeights, TransWeights);
       //System.out.println(orgWeights.dump());
       Core.gemm(phie,TransWeights,1,ty,0,orgWeights,0);
     
       
       // System.out.println(orgWeights.rows());
     //  for(int w=0;w<n_rows*n_cols;w++){
        // System.out.println((orgWeights.get(w, 4))[0]);
      // }
     
       
       //-------------------------Store eigen vectors in a file------------------------------
       //FileWriter file = new FileWriter("foo.txt");
       //BufferedWriter bf = new BufferedWriter(file);
       int a;
       for(int j=0;j<n_train;j++){
    	   FileWriter file = new FileWriter("foo"+j+".txt");
    	   BufferedWriter bf = new BufferedWriter(file); 
       for(int i=0;i<n_rows*n_cols;i++){
    	  
    	   a=(int)orgWeights.get(i,j)[0];
    	   //System.out.println(a);
       bf.write(""+a);
       bf.write("\n");
       }
       bf.close();
       }
       //------------------------------------------------------------------------------------
       
       Mat newdImgs= Mat.zeros(n_train, n_train,CvType.CV_32FC1);
       Mat trsOrgWeights = Mat.zeros(n_train,n_rows*n_cols,CvType.CV_32FC1);
       Core.transpose(orgWeights, trsOrgWeights);
       Core.gemm(trsOrgWeights,faceListar,1,ty,0,newdImgs,0);
      // for(int w=0;w<n_train;w++){
        //  System.out.println((newdImgs.get(w, 0))[0]);
          //}
       int n_testimgs= testface.cols();
       //System.out.println(n_testimgs);
       int[] labelsss= new int[n_testimgs];
       
       Mat newdtest= Mat.zeros(n_train, n_testimgs, CvType.CV_32FC1);
       Core.gemm(trsOrgWeights,testface,1,ty,0,newdtest,0);
      
       for (int y=0;y<n_testimgs;y++){
    	   double mini= Double.MAX_VALUE;
       for (int n= 0;n<n_train;n++){
    	   
    	   double normval= Core.norm(newdImgs.col(n), newdtest.col(y), Core.NORM_L2);
    	   if (normval<=mini){
    		   labelsss[y]= labels[n];
    		   mini=normval;
    	   }
    	   }
       }
       
       
       //--------------------------labels of testfaces can be printed here--------------------------
       for (int y=0;y<n_testimgs;y++){
       System.out.println(labelsss[y]);
       }
       //------------------------------------end EigenFaces------------------------------------------
       
       
       //-----------------------------------Start the FisherFaces here on the reduced dimension Ys------------ 
       /*for(int w=0;w<n_train;w++){
    	   matrix[w]=0;
       }
       Mat Fmean=Mat.zeros(n_train,1,CvType.CV_32FC1);
       for (int n_samp=0;n_samp<n_train;n_samp++){
           for(int w=0;w<n_train;w++){
           	   matrix[w]+=(newdImgs.get(w, n_samp))[0];
           	   //System.out.println(matrix[w]);
        	   
           }
           }
           for(int w=0;w<n_train;w++){
        	 //  System.out.println(matrix[w][0]);
        	   Fmean.put(w, 0,matrix[w]/n_train);
           }
           Mat tempmp = Mat.zeros(n_train,1, CvType.CV_32FC1);
           Mat means= Mat.zeros(n_train, no_trainFaces, CvType.CV_32FC1);
           Mat tempn2 = Mat.zeros((int)Math.pow(n_train,2),(int)Math.pow(n_train,2), CvType.CV_32FC1);
           Mat tempn3 = Mat.zeros((int)Math.pow(n_train,2),(int)Math.pow(n_train,2), CvType.CV_32FC1);
           
           Mat ans= Mat.zeros((int)Math.pow(n_train,2),(int)Math.pow(n_train,2), CvType.CV_32FC1);
           int[] count = new int[no_trainFaces] ;
           
           
          
           for(int i=0;i<no_trainFaces;i++)
           {
               count[i]=0;
               
           }
         
          
           
           for(int i=0;i<n_train;i++)
           {
        	  // System.out.println(i);
              count[labels[i]-1]++;
           }
          
           for(int i=0;i<n_train;i++)
           {
               divide(count[labels[i]-1],newdImgs.col(i),tempmp);
               Core.add(means.col(labels[i]-1),tempmp,means.col(labels[i]-1));
               }
           System.out.println("100000000000000000000000000000");
          // s.next();
           tempn2=Mat.zeros((int)Math.pow(n_train,2),(int)Math.pow(n_train,2), CvType.CV_32FC1);
           System.out.println(tempn2.rows()+" " +tempn2.cols() +";" +ans.rows()+" "+ans.cols());
           for(int i=0;i<no_trainFaces;i++)
           {
        	   
               Core.subtract(means.col(i),Fmean,tempmp);
              // transpose(tempmp);
               
           mulTransposed(tempmp,tempn2,false);
           System.out.println(tempn2.rows()+" " +tempn2.cols() +";" +ans.rows()+" "+ans.cols());
           
               Core.add(tempn2,ans,tempn3);
               tempn3.copyTo(ans);
               System.out.println("kjjkkjjkkkjjkkj"+i);


               //for(j=0;j<n_train)
           }
           //s.next();
           Mat within = Mat.zeros((int)Math.pow(n_train,2),(int)Math.pow(n_train,2), CvType.CV_32FC1);//sw

           for(int i=0;i<n_train;i++)
           {
               Core.subtract(newdImgs.col(i),means.col(labels[i]-1),tempmp);
               mulTransposed(tempmp,tempn2,false);
               Core.add(tempn2,within,tempn3);
               tempn3.copyTo(ans);
           }
           Mat sw_inv_sb=Mat.zeros((int)Math.pow(n_train,2),(int)Math.pow(n_train,2),CvType.CV_32FC1);
           invert(within,tempn2);
           //Mat ty=Mat.zeros(1, 1,CvType.CV_32FC1) ;
           Core.gemm(tempn2,ans,1,ty,0,sw_inv_sb,0);

        
           Core.SVDecomp(sw_inv_sb, eigenValues, orgWeights, TransWeights);
           */
           
    }
}



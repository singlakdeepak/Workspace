/**
 * Created by Mithool on 10-May-16.
 */
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

import static org.opencv.core.Core.*;


public class FisherFace {
   //static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
  static int n_train=3;
  static  int n_test=1;
   static int n_rows=243;
  static  int n_cols=320;
   static int no_trainFaces=5;//define types of faces
    static  int[] labels;
    static Mat faceListar,mean;


    public static void main(String[] args) {
       // System.load("C:\\opencv\\build\\java\\x64\\opencv_java310.dll");

        String[] faceType={"centerlight","glasses","happy","leftlight","noglasses","normal","rightlight","sad","sleepy","surprised","wink"};

        System.out.println("Welcome to OpenCV " + Core.VERSION);
        //  ArrayList<Mat> FaceList=new ArrayList<Mat>();
        ArrayList<Mat> TestFaceList=new ArrayList<Mat>();

        faceListar= Mat.zeros(n_rows*n_cols, n_train,CvType.CV_32FC1);
        Mat testface= Mat.zeros(n_rows*n_cols, n_test, CvType.CV_32FC1);

        mean=Mat.zeros(n_rows*n_cols,1,CvType.CV_32FC1);


        int k=0;//it represent the face number
        labels= new int[no_trainFaces*11];
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

                Mat tempor=Mat.zeros(n_rows*n_cols,1,CvType.CV_32FC1);


                // double[] abc=faceListar.get(0, 0);
                //System.out.println(abc[0]);
                if (imgFrame.empty())
                    System.out.println("");
                else
                {

                    Core.normalize(imgFrame,imgFrame,0,255,Core.NORM_MINMAX,CvType.CV_8UC1);
                    double temp=0;
                    tempor=Mat.zeros(n_rows*n_cols,1,CvType.CV_32FC1);
                    for (int a=0; a<n_rows;a++){
                        temp=0;
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
            System.out.println((mean.get(w, 0))[0]);
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
        // Core.subtract(faceListar, mean_x, phie);
        //  System.out.println((mean_x.get(150, 0))[0]);
        System.out.println(faceListar.rows() +" "+faceListar.cols());
        //-----------------This part ends here i.e. all 15 faces have been stored-------------

        //-----------------This part ends here i.e. all 15 faces have been stored-------------
        k=0;
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



        in_class();

    }


    static void  in_class()
    {   Mat tempmp = Mat.zeros(n_rows*n_cols,1, CvType.CV_32FC1);
        Mat means= Mat.zeros(n_rows*n_cols, no_trainFaces, CvType.CV_32FC1);
        Mat tempn2 = Mat.zeros(n_rows*n_cols,n_rows*n_cols, CvType.CV_32FC1);
        Mat ans= Mat.zeros(n_rows*n_cols,n_rows*n_cols, CvType.CV_32FC1);
        int[] count = new int[no_trainFaces] ;
        for(int i=0;i<no_trainFaces;i++)
        {
            count[i]=0;

        }
        for(int i=0;i<n_train;i++)
        {
           count[labels[i]]++;
        }
        for(int i=0;i<n_train;i++)
        {
            divide(count[labels[i]],faceListar.col(i),tempmp);
            Core.add(means.col(labels[i]),tempmp,means.col(labels[i]));


        }
        for(int i=0;i<no_trainFaces;i++)
        {
            Core.subtract(mean.col(i),mean,tempmp);
           // transpose(tempmp);
        mulTransposed(tempmp,tempn2,false);
            Core.add(tempn2,ans,ans);


            //for(j=0;j<n_train)
        }

        Mat within = Mat.zeros(n_rows*n_cols,n_rows*n_cols, CvType.CV_32FC1);//sw

        for(int i=0;i<n_train;i++)
        {
            Core.subtract(faceListar.col(i),mean.col(labels[i]),tempmp);
            mulTransposed(tempmp,tempn2,false);
            Core.add(tempn2,within,within);
        }
        Mat sw_inv_sb=Mat.zeros(n_rows*n_cols,n_rows*n_cols,CvType.CV_32FC1);
        invert(within,tempn2);
        Mat ty=Mat.zeros(1, 1,CvType.CV_32FC1) ;
        Core.gemm(tempn2,ans,1,ty,0,sw_inv_sb,0);

        Mat eigenValues= Mat.zeros(n_train,1,CvType.CV_32FC1);
        Mat orgWeights = Mat.zeros(n_rows*n_cols,n_train,CvType.CV_32FC1);
        Mat TransWeights = Mat.zeros(n_rows*n_cols,n_train,CvType.CV_32FC1);
        Core.SVDecomp(sw_inv_sb, eigenValues, orgWeights, TransWeights);

        TransWeights.dump();


    }

}



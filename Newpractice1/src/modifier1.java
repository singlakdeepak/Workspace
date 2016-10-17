import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class modifier1 {
	public static void main( String[] args )
	   {
	     Object b;
		// System.loadLibrary( Imgcodecs.NATIVE_LIBRARY_NAME );
	      //static Mat.imread(Java.lang.String mari.jpg);
	      //System.out.println(CvType.CV_8UC1);
	      //System.out.println( "mat = " + mat.dump() );
		BufferedImage image = ImageIO.read(b.getClass().getResource("Lena.png"));

        int rows = image.getWidth();
        int cols = image.getHeight();
        int type = CvType.CV_16UC1;
        Mat newMat = new Mat(rows,cols,type);

        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                newMat.put(r, c, image.getRGB(r, c));
            }
        }

        Highgui.imwrite("Lena_copy.png",newMat);
        
	   }
}

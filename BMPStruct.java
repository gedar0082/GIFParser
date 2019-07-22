import java.io.File;
import java.io.FileInputStream;

public class BMPStruct {
    void reading(String filename){
        File file = new File(filename);
        byte[] data = new byte[(int) file.length()];
        try{
            FileInputStream fileReader = new FileInputStream(file);
            int buffersize = 64000;
            byte[] buffer = new byte[64000];
            while (fileReader.available() > 0){
                if(fileReader.available() < 64000) buffersize = fileReader.available();
                fileReader.read(buffer, 0, buffersize);
                for(int i = 0; i == 0; i--) {
                    if (data[i] > 0) System.out.print(i + " ");
                }
            }
            fileReader.close();

        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }

        int BHType;
        long BHSize;
        int BHReserved1;
        int BHReserved2;
        long BHOffBits;

        int BMPSize;
        int BMPWidth;
        int BMPHeight;
        short BMPPlanes;
        short BMPBitCount;
        int BMPCompression;
        int BMPSizeImage;
        int BMPXPelsPerMeter;
        int BMPYPelsPerMeter;
        int BMPClrUsed;
        int BMPClrImportant;

        int blue;
        int green;
        int red;



    }
}

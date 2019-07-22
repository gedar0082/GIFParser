import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.javafx.iio.gif.GIFDescriptor;

import java.io.*;

public class main {
    static final  String fileName = "C:\\Users\\gedar\\git\\GIFParserPorject\\res\\gif.gif";
    static final  String bmpname1 = "C:\\Users\\gedar\\git\\GIFParserPorject\\res\\bmp0.bmp";
    static final  String bmpname2 = "C:\\Users\\gedar\\git\\GIFParserPorject\\res\\bmp1.bmp";
    public static void main(String[] args) throws IOException {


        BMPStruct bmp = new BMPStruct();
        bmp.reading(bmpname1);
        GIFReader read = new GIFReader();
        read.gifReader(fileName);
        LZWCoding a = new LZWCoding();
        //a.filling("0A B2 5D 0A B2 5D 0A B2 5D 0A B2 5D F3 ED 63 F3 ED 63 F3 ED 63 F3 ED 63 00 80 C8 00 80 C8 00 80 C8 00 80 C8 F1 60 22 F1 60 22 F1 60 22 F1 60 22");
        System.out.println(a.decoder());
        System.out.println("GCC " + GIFStruct.struct.getGlobColorChart());
        System.out.println("IC " + GIFStruct.struct.getImageCode());
        System.out.println("EC " + GIFStruct.struct.getExpansionCode());
        System.out.println("H " + GIFStruct.struct.getHeader());
        System.out.println("IS " + GIFStruct.struct.getImageSize());
        BMPWriter d = new BMPWriter();
        for(int i = 0; i < GIFStruct.struct.getImageCode().size(); i++){
            d.writer();
        }
    }
}

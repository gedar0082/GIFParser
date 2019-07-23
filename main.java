
import java.io.*;
import java.util.ArrayList;

public class main {
    static final  String fileName = "C:\\Users\\gedar\\git\\GIFParserPorject\\res\\gif.gif";
    static final  String bmpname1 = "C:\\Users\\gedar\\git\\GIFParserPorject\\res\\bmp0.bmp";
    static final  String bmpname2 = "C:\\Users\\gedar\\git\\GIFParserPorject\\res\\bmp1.bmp";
    public static void main(String[] args) throws IOException {
        ArrayList<String> names = new ArrayList<>();
        names.add(bmpname1);
        names.add(bmpname2);

        //BMPReader reader = new BMPReader();
        //BMPStruct st = reader.reading(bmpname1);

        GIFWriter writer = new GIFWriter();
        writer.write(names);

        BMPWriter w = new BMPWriter();


        GIFReader r = new GIFReader();
        r.gifReader(fileName);

        for(int i = 0; i < 2; i++){
            w.writer(fileName);
        }
    }
}

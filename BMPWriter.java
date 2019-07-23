
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BMPWriter {
    static int count = 0;
    static int size;

    void writer(String name) throws IOException {
        GIFReader read = new GIFReader();
//        read.gifReader(name);
        size = GIFStruct.struct.getImageCode().size();
        //bmpheader
        FileOutputStream file = new FileOutputStream("bmpp" + count + ".bmp");
/*00*/  file.write(0x42);
        file.write(0x4d);
        file.write(0x00);
        file.write(0x00);//word bmp format
        file.write(0x00);
        file.write(0x00);
        file.write(0x00);
        file.write(0x00);
        file.write(0x00);
        file.write(0x00);
        file.write(0x8a);

/*0A*/  file.write((strToINt("000000").getBytes()));  //place of pixel array
        //bmpinfo
/*0E*/  file.write(strToINt("7C000000").getBytes()); //124 - 5 series structure
/*12*/  file.write(strToINt(GIFStruct.struct.getImageSize().substring(0, 4).replace(" ", "") + "000000").getBytes());  //file width
/*16*/  file.write(strToINt(GIFStruct.struct.getImageSize().substring(6, 10).replace(" ", "") + "000000").getBytes());  //file height
/*1A*/  file.write(strToINt("0100").getBytes()); //reserved for another things
/*1C*/  file.write(strToINt("1800").getBytes()); //24 bits on pixel
/*1E*/  file.write(strToINt("00000000").getBytes()); //pixel storage method // if 0 - two-dimensional array
/*22*/  file.write(strToINt("00000000").getBytes()); //size of pixel data  //unnecessary for two-dimensional array
/*26*/  file.write(strToINt("0000000000000000").getBytes()); //ppi, unnecessary
/*2E*/  file.write(strToINt("00000000").getBytes()); //size of color pallet
/*32*/  file.write(strToINt("00000000").getBytes());  //unnecessary 32-36
/*36*/  file.write(strToINt("0000FF00").getBytes(StandardCharsets.ISO_8859_1));
/*3A*/  file.write(strToINt("00FF0000").getBytes(StandardCharsets.ISO_8859_1));  //green color intensity
/*3E*/  file.write(strToINt("FF000000").getBytes(StandardCharsets.ISO_8859_1));  //blue color intensity
/*42*/  file.write(strToINt("000000FF").getBytes(StandardCharsets.ISO_8859_1));  //AC intensity
/*46*/  file.write(strToINt("42475273").getBytes(StandardCharsets.ISO_8859_1));  //color space
/*4A*/  file.write(strToINt("000000000000000000000000000000000000000000000000000000000000000000000000").getBytes()); //unnecessary if colortype != 0
/*6E*/  file.write(strToINt("00000000").getBytes(StandardCharsets.ISO_8859_1));
/*72*/  file.write(strToINt("00000000").getBytes(StandardCharsets.ISO_8859_1));
/*76*/  file.write(strToINt("00000000").getBytes(StandardCharsets.ISO_8859_1));
/*7A*/  file.write(strToINt("04000000").getBytes(StandardCharsets.ISO_8859_1));  //intent
/*7E*/  file.write(strToINt("00000000").getBytes(StandardCharsets.ISO_8859_1));  //ProfileData
/*82*/  file.write(strToINt("00000000").getBytes(StandardCharsets.ISO_8859_1));  //ProfileSize. if ProfileData = 0 - unnecessary
/*86*/  file.write(strToINt("00000000").getBytes(StandardCharsets.ISO_8859_1));  //reserved, must be null

        String g = GIFStruct.struct.getDecodedImages().get(count).replace(" ", "");
        String a;
        String b;
        //temporal solve
        int width = Integer.parseInt(GIFStruct.struct.getImageSize().substring(0, 5).replace(" ", "").replace("0", ""), 10);
        int height = Integer.parseInt(GIFStruct.struct.getImageSize().substring(5, 10).replace(" ", "").replace("0", ""), 10);
        for (int i = height - 1; i >= 0; i--){
            a = g.substring(g.length() - width*6);
            g = g.substring(0, g.length() - width*6);
            for (int j = 0; j < width; j++){
                if (a.length() > 5) {
                    b = a.substring(0, 6);
                    a = a.substring(6);
                    file.write(strToRevINt(b).getBytes(StandardCharsets.ISO_8859_1));
                }
            }
        }
        count++;
        file.close();
    }

    private String strToINt(String str){
        StringBuilder newstr = new StringBuilder();
        boolean second = false;
        char temp = 0;
        for (int i = 0; i < str.length(); i++){
            if (second) {
                temp <<= 4;
                temp += hexAlphabet(str.charAt(i)+"");
                newstr.append(temp);
            } else {
                temp = hexAlphabet(str.charAt(i) + "");

            }
            second = !second;
        }
        String result = newstr.toString();
        return result;
    }

    private String strToRevINt(String str){
        StringBuilder newstr = new StringBuilder();
        boolean second = false;
        char temp = 0;
        for (int i = 0; i < str.length(); i++){
            if (second) {
                temp <<= 4;
                temp += hexAlphabet(str.charAt(i)+"");
                newstr.append(temp);
            } else {
                temp = hexAlphabet(str.charAt(i) + "");

            }
            second = !second;
        }
        String result = newstr.toString();
        return newstr.reverse().toString();
    }

    private Character hexAlphabet(String num){
        switch (num){
            case ("0"): return (char)(num.charAt(0) - '0');
            case ("1"): return (char)(num.charAt(0) - '0');
            case ("2"): return (char)(num.charAt(0) - '0');
            case ("3"): return (char)(num.charAt(0) - '0');
            case ("4"): return (char)(num.charAt(0) - '0');
            case ("5"): return (char)(num.charAt(0) - '0');
            case ("6"): return (char)(num.charAt(0) - '0');
            case ("7"): return (char)(num.charAt(0) - '0');
            case ("8"): return (char)(num.charAt(0) - '0');
            case ("9"): return (char)(num.charAt(0) - '0');
            case ("A"): return (char)(num.charAt(0) - 'A' + 10);
            case ("B"): return (char)(num.charAt(0) - 'A' + 10);
            case ("C"): return (char)(num.charAt(0) - 'A' + 10);
            case ("D"): return (char)(num.charAt(0) - 'A' + 10);
            case ("E"): return (char)(num.charAt(0) - 'A' + 10);
            case ("F"): return (char)(num.charAt(0) - 'A' + 10);
            default: return null;
        }
    }
}
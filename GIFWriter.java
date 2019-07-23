import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GIFWriter {

    ArrayList<BMPStruct> images = new ArrayList<>();
    BMPReader reader = new BMPReader();
    LZWCoding lzw = new LZWCoding();
    void write(ArrayList<String> names) throws IOException {
        FileOutputStream file = new FileOutputStream("gif1.gif");
        for(int g = 0; g < names.size(); g++){
            BMPStruct struct;
            struct = reader.reading(names.get(g));
            images.add(struct);
        }
        ArrayList<ArrayList<Byte>> palet = new ArrayList<>();
        for(int g = 0; g < images.size(); g++){
            palet.addAll(toDicti(compressedColors(images.get(g).colors)));
            while(palet.size() % 4 != 0){
                ArrayList<Byte> color = new ArrayList<>();
                color.add((byte)127);
                color.add((byte)127);
                color.add((byte)127);
                palet.add(color);
            }
        }
        for(int g = 0; g < images.size(); g++){
            //header
            if(g == 0) {
                file.write(0x47);
                file.write(0x49);
                file.write(0x46);
                file.write(0x38);
                file.write(0x39);
                file.write(0x61);
                //logical descriptor
                file.write(images.get(g).biWidth[0]);
                file.write(images.get(g).biWidth[1]);
                file.write(images.get(g).biHeight[0]);
                file.write(images.get(g).biHeight[1]);
                file.write(0xa2);
                file.write(0x00);
                file.write(0x00);
                file.write(toByteArr(palet));

                //extensions

                //program extension unit
                file.write(0x21);
                file.write(0xff);
                file.write(0x0b);
                file.write(0x4e);
                file.write(0x45);
                file.write(0x54);
                file.write(0x53);
                file.write(0x43);
                file.write(0x41);
                file.write(0x50);
                file.write(0x45);
                file.write(0x32);
                file.write(0x2e);
                file.write(0x30);
                file.write(0x03);
                file.write(0x01);
                file.write(0x00);
                file.write(0x00);
                file.write(0x00);
            }
            //graphic control extension unit
            file.write(0x21);
            file.write(0xf9);
            file.write(0x04);
            file.write(0x04);
            file.write(0x32);
            file.write(0x00);
            file.write(0x00);
            file.write(0x00);
            //image block
            file.write(0x2c);
            file.write(0x00);
            file.write(0x00);
            file.write(0x00);
            file.write(0x00);
            file.write(images.get(g).biWidth[0]);
            file.write(images.get(g).biWidth[1]);
            file.write(images.get(g).biHeight[0]);
            file.write(images.get(g).biHeight[1]);
            file.write(0x00);
            file.write(0x03);
            ;
            ArrayList<ArrayList<Byte>> compressed = new ArrayList<>(compressedColors(images.get(g).colors));
            ArrayList<String> forlzwdict = new ArrayList<>();
            String forlzwtext;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < toDicti(compressedColors(images.get(g).colors)).size(); i++){
                forlzwdict.add(tString(toDicti(compressedColors(images.get(g).colors)).get(i)).substring(0, 8));
            }
            StringBuilder ssb = new StringBuilder();
            for(int i = 0; i < compressedColors(images.get(g).colors).size(); i++){
                ssb.append(tString(compressedColors(images.get(g).colors).get(i)));
            }
            forlzwtext = ssb.toString();
            file.write(lzw.filling(forlzwdict, forlzwtext).size());
            file.write(arIntToByte(lzw.filling(forlzwdict, forlzwtext)));
            file.write(0x00);
        }

        file.write(0x3b);

        file.close();
    }




    byte[] toByteArr(ArrayList<ArrayList<Byte>> arr){
        byte[] data = new byte[arr.size()*3];
        int count = 0;
        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < 3; j++){
                data[count] = arr.get(i).get(j);
                count++;
            }
        }
        return data;
    }

    byte[] arIntToByte(ArrayList<Integer> ar){
        byte[] b = new byte[ar.size()];
        for (int i = 0; i < ar.size(); i++){
            b[i] = (byte)(int)ar.get(i);
        }
        return b;
    }


    ArrayList<ArrayList<Byte>> compressedColors(ArrayList<ArrayList<Byte>> colors){
        ArrayList<ArrayList<Byte>> fulColors = (ArrayList<ArrayList<Byte>>)colors.clone();
        ArrayList<ArrayList<Byte>> comprDicti = new ArrayList<>();


        for (int i = -127; i < 127; i+=50){
            for (int j = -127; j < 127; j+=50){
                for (int k = -127; k < 127; k+=50){
                    ArrayList<Byte> color = new ArrayList<>();
                    color.add((byte)i);
                    color.add((byte)j);
                    color.add((byte)k);
                    comprDicti.add(color);
                }
            }
        }
        ArrayList<Byte> color = new ArrayList<>();
        color.add((byte)127);
        color.add((byte)127);
        color.add((byte)127);
        comprDicti.add(color);

        for(int i = 0; i < fulColors.size(); i++){
            for(int j = 0; j < comprDicti.size(); j++){
                if (Math.abs(fulColors.get(i).get(0)-comprDicti.get(j).get(0)) < 25 &&
                        Math.abs(fulColors.get(i).get(1)-comprDicti.get(j).get(1)) < 25 &&
                        Math.abs(fulColors.get(i).get(2)-comprDicti.get(j).get(2)) < 25)
                {
                    fulColors.get(i).set(0,comprDicti.get(j).get(0));
                    fulColors.get(i).set(1,comprDicti.get(j).get(1));
                    fulColors.get(i).set(2,comprDicti.get(j).get(2));
                }
            }
        }
        return fulColors;
    }

    private String tString(ArrayList<Byte> color){
        return String.format("%02x", color.get(0)) + " " + String.format("%02x", color.get(1)) + " " + String.format("%02x", color.get(2)) + " ";
    }

    private String toBigString(ArrayList<String> ar){
        StringBuilder sb = new StringBuilder();
        for(String st: ar){
            sb.append(st);
        }
        return sb.toString();
    }

    private String colorsToString(ArrayList<Byte> color){
        return color.get(0) + color.get(1) + color.get(2) + "";
    }

    private int bigInt (byte[] b){
        int bigInt = 0;
        int supplemented;
        int size = b.length;
        for(int i = 0; i < size; i++){
            supplemented = b[i] & 0xFF;
            bigInt += supplemented * (int)Math.pow(255, i);
        }
        return bigInt;
    }

    private boolean equal(ArrayList<Byte> ar1, ArrayList<Byte> ar2){
        if(ar1.size() == ar2.size()){
            for (int i = 0; i < ar1.size(); i++){
                if (!ar1.get(i).equals(ar2.get(i))) return false;
            }
        }else{
            return false;
        }
        return true;
    }

    private boolean contain(ArrayList<ArrayList<Byte>> ar1, ArrayList<Byte> ar2){
        boolean bool = false;
        for (int i = 0; i < ar1.size(); i++){
            if(equal(ar1.get(i), ar2)) bool = true;
        }
        return bool;
    }

    ArrayList<ArrayList<Byte>> toDicti(ArrayList<ArrayList<Byte>> colorPalet){
        ArrayList<ArrayList<Byte>> dictionary = new ArrayList<>();
        for (int i = 0; i < colorPalet.size(); i++){
            if(!contain(dictionary, colorPalet.get(i))){
                dictionary.add(colorPalet.get(i));
            }
        }
        return dictionary;
    }




    //ArrayList<String> dictionary(ArrayList<ArrayList<Byte>> colorPalet){
    //    ArrayList<String> dictionary = new ArrayList<>();
    //    for(int i = 0; i < colorPalet.size(); i++){
    //        if(dictionary.contains(tString(colorPalet.get(i)))){
    //            dictionary.add(tString(colorPalet.get(i)));
    //        }
    //    }
    //    return dictionary;
//
    //}



}

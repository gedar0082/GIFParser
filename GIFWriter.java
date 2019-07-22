import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GIFWriter {
    void write() throws IOException {
        FileOutputStream file = new FileOutputStream("gif1.gif");
        BMPStruct struct = new BMPStruct();
        BMPReader reader = new BMPReader();
        struct = reader.reading(main.bmpname1);
        //header
        file.write(0x47);
        file.write(0x49);
        file.write(0x46);
        file.write(0x38);
        file.write(0x39);
        file.write(0x61);
        //logical descriptor
        file.write(toByteArr(struct.bcWidth));
        file.write(toByteArr(struct.bcHeight));
        file.write(0xa2);
        file.write(0x00);
        file.write(0x00);
        //file.write(toByteArr(compressedColors(struct.colors)));

    }

    byte[] toByteArr(ArrayList<Byte> arr){
        byte[] data = new byte[arr.size()];
        for (int i = 0; i < arr.size(); i++){
            data[i] = arr.get(i).byteValue();
        }
        return data;
    }

    BMPReader struct = new BMPReader();

    ArrayList<ArrayList<Integer>> compressedColors(ArrayList<ArrayList<Integer>> colors){
        ArrayList<ArrayList<Integer>> fulColors = (ArrayList<ArrayList<Integer>>)colors.clone();
        ArrayList<ArrayList<Integer>> comprDicti = new ArrayList<>();

        for (int i = 0; i < 256; i+=50){
            for (int j = 0; j < 256; j+=50){
                for (int k = 0; k < 256; k+=50){
                    ArrayList<Integer> color = new ArrayList<>();
                    color.add(i);
                    color.add(j);
                    color.add(k);
                    comprDicti.add(color);
                }
            }
        }
        ArrayList<Integer> color = new ArrayList<>();
        color.add(255);
        color.add(255);
        color.add(255);
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
        return null;
    }



}

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class GIFReader {

    ArrayList<Integer> readedGIF = new ArrayList<>();

    private static int counter = 0;
    void gifReader(String fileName) throws IOException {

        try (FileInputStream file = new FileInputStream(fileName)) {
            int i = -1;
            while ((i = file.read()) != -1) {
                readedGIF.add(i);
            }
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
            System.out.printf("suka");
        }
        GIFStruct.struct.setHeader(getname());
        GIFStruct.struct.setImageSize(gifSize());
        GIFStruct.struct.setGlobColorChart(dictionary());
        System.out.println(GIFStruct.struct.getGlobColorChart());


        counter+=3;//reserved for screen descriptor;
        ArrayList<ArrayList<Integer>> imcode = new ArrayList<>();
        System.out.println(counter);
        System.out.println(readFrom(33));
        System.out.println(readFrom(33));
        imcode.add(readFrom(44));
        //System.out.println(readFrom(44));
        System.out.println(readFrom(33));
        imcode.add(readFrom(44));
        //System.out.println(readFrom(44));
        GIFStruct.struct.setImageCode(imcode);

        LZWCoding lzw = new LZWCoding();
        lzw.decoder();

    }

    private ArrayList<Integer> readFrom(int lit){
        ArrayList<Integer> readedArr = new ArrayList<>();
        boolean catcher = false;
        for (int i = counter; i < readedGIF.size(); i++){
            if(readedGIF.get(i) == lit) catcher = true;
            if(catcher){
                if(readedGIF.get(i) == 0 && (readedGIF.get(i+1) == 33 || readedGIF.get(i+1) == 44 || readedGIF.get(i+1) == 59)){
                    catcher = false;
                    break;
                }
                readedArr.add(readedGIF.get(i));
                counter++;
            }else continue;
        }
        counter++;
        System.out.println(counter + "counter");
        return readedArr;
    }

    private String getname(){
        String header = "";
        for (int i = 0; i < readedGIF.size(); i++) {
            if (i < 6) {
                header = header + ((char) (int) readedGIF.get(i));
                counter++;
            }

        }
        System.out.println(counter);
        return header;
    }
//not done at all
    private String gifSize(){
        String size = "";
        size = size + String.format("%02X ", readedGIF.get(counter)) + String.format("%02X ", readedGIF.get(counter + 1)) +
                String.format("%02X ", readedGIF.get(counter + 2)) + String.format("%02X ", readedGIF.get(counter + 3));
        counter+=4;
        return size;
    }

    private ArrayList<String> dictionary(){
        ArrayList<String> dictionary = new ArrayList<>();
        String color = "";
        int triple = 0;
        int i = 13;
        while (readedGIF.get(i) != 33){
            color = color + String.format("%02X", readedGIF.get(i)) + " ";
            triple++;
            counter++;
            if(triple == 3){
                dictionary.add(color);
                color = "";
                triple = 0;
            }
            i++;
        }
        dictionary.add("CC");
        dictionary.add("EOI");
        System.out.println(counter);
        return dictionary;
    }






}

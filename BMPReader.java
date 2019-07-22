import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class BMPReader {
    private static int count = 0;
    BMPStruct reading(String filename) {
        File file = new File(filename);
        byte[] data = new byte[(int) file.length()];
        try {
            FileInputStream fileReader = new FileInputStream(file);
            fileReader.read(data);
            fileReader.close();

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        BMPStruct struct = new BMPStruct();

        struct.bfType.addAll(reader2b(data));
        struct.bfSize.addAll(reader4b(data));
        struct.bfReserved1.addAll(reader2b(data));
        struct.bfReserved2.addAll(reader2b(data));
        struct.bfOffBits.addAll(reader4b(data));

        if (data[14] == 12) {
            struct.bcSize.addAll(reader4b(data));
            struct.bcWidth.addAll(reader2b(data));
            struct.bcHeight.addAll(reader2b(data));
            struct.bcPlanes.addAll(reader2b(data));
            struct.bcBitCount.addAll(reader2b(data));
        }else {
            if(data[14] >= 40) {
                struct.biSize.addAll(reader4b(data));
                struct.biWidth.addAll(reader4b(data));
                struct.biHeight.addAll(reader4b(data));
                struct.biPlanes.addAll(reader2b(data));
                struct.biBitCount.addAll(reader2b(data));
                struct.biCompression.addAll(reader4b(data));
                struct.biSizeImage.addAll(reader4b(data));
                struct.biXPelsPerMeter.addAll(reader4b(data));
                struct.biYPelsPerMeter.addAll(reader4b(data));
                struct.biClrUsed.addAll(reader4b(data));
                struct.biClrImportant.addAll(reader4b(data));
                if(data[14] >=108) {
                    struct.bV4RedMask.addAll(reader4b(data));
                    struct.bV4GreenMask.addAll(reader4b(data));
                    struct.bV4BlueMask.addAll(reader4b(data));
                    struct.bV4AlphaMask.addAll(reader4b(data));
                    struct.bV4CSType.addAll(reader4b(data));

                    struct.bV4Endpoints.addAll(reader4b(data));
                    struct.bV4Endpoints.addAll(reader4b(data));
                    struct.bV4Endpoints.addAll(reader4b(data));
                    struct.bV4Endpoints.addAll(reader4b(data));
                    struct.bV4Endpoints.addAll(reader4b(data));
                    struct.bV4Endpoints.addAll(reader4b(data));
                    struct.bV4Endpoints.addAll(reader4b(data));
                    struct.bV4Endpoints.addAll(reader4b(data));
                    struct.bV4Endpoints.addAll(reader4b(data));

                    struct.bV4GammaRed.addAll(reader4b(data));
                    struct.bV4GammaGreen.addAll(reader4b(data));
                    struct.bV4GammaBlue.addAll(reader4b(data));
                    if(data[14] >= 124) {
                        struct.bV5Intent.addAll(reader4b(data));
                        struct.bV5ProfileData.addAll(reader4b(data));
                        struct.bV5ProfileSize.addAll(reader4b(data));
                        struct.bV5Reserved.addAll(reader4b(data));
                    }
                }
            }
        }
        while (count < data.length){
            struct.colors.add(reader3b(data));
        }
        for(int i = 0; i < bigByteToInt(struct.bcHeight); i++){
            for (int j = 0; j < bigByteToInt(struct.bcWidth); j++){

            }
        }
        return struct;
        /**ПЕРЕДЕЛАТЬ**/
    }

    ArrayList<Byte> reader2b (byte[] data){
        ArrayList<Byte> array = new ArrayList<>();
        array.add(new Byte(data[count]));
        array.add(new Byte(data[count + 1]));
        count+=2;
        return array;
    }


    ArrayList<Byte> reader4b (byte[] data){
        ArrayList<Byte> array = new ArrayList<>();
        array.add(new Byte(data[count]));
        array.add(new Byte(data[count + 1]));
        array.add(new Byte(data[count + 2]));
        array.add(new Byte(data[count + 3]));
        count+=4;
        return array;
    }

    ArrayList<Integer> reader3b (byte[] data){
        ArrayList<Integer> array = new ArrayList<>();

        if((int)data[count] < 0) array.add((data[count] + 255));
        else array.add((int)(data[count]));

        if((int)data[count + 1] < 0) array.add((data[count + 1] + 255));
        else array.add((int)(data[count + 1]));

        if((int)data[count + 2] < 0) array.add((data[count + 2] + 255));
        else array.add((int)(data[count + 2]));

        count+=3;
        return array;
    }

    int bigIntToInt(ArrayList<Integer> arr){
        int i = 0;
        for (int j = 0; j < arr.size(); j++){
            i += additor(additor(arr.get(i))) * (int)Math.pow(255, i);
        }
        return i;
    }

    int bigByteToInt(ArrayList<Byte> arr){
        int i = 0;
        for (int j = 0; j < arr.size(); j++){
            i += additor(additor((int)arr.get(i))) * (int)Math.pow(255, i);
        }
        return i;
    }

    int additor(int b){
        int i = 0;
        if((int)b < 0){
            i = i + b + 255;
            return  i;
        }
        else{
            i = b;
            return i;
        }
    }




}

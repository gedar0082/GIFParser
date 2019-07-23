import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

class BMPReader {
    //ArrayList<ArrayList<Byte>> globalDictionary = new ArrayList<>();
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

        struct.bfType = Arrays.copyOf(byteReader2(data), 2);
        struct.bfSize = Arrays.copyOf(byteReader4(data), 4);
        struct.bfReserved1 = Arrays.copyOf(byteReader2(data), 2);
        struct.bfReserved2 = Arrays.copyOf(byteReader2(data), 2);
        struct.bfOffBits = Arrays.copyOf(byteReader4(data), 4);

        if (data[14] == 12) {
            struct.bcSize = Arrays.copyOf(byteReader4(data), 4);
            struct.bcWidth = Arrays.copyOf(byteReader2(data), 2);
            struct.bcHeight = Arrays.copyOf(byteReader2(data), 2);
            struct.bcPlanes = Arrays.copyOf(byteReader2(data), 2);
            struct.bcBitCount = Arrays.copyOf(byteReader2(data), 2);
        }else {
            if(data[14] >= 40) {
                struct.biSize = Arrays.copyOf(byteReader4(data), 4);
                struct.biWidth = Arrays.copyOf(byteReader4(data), 4);
                struct.biHeight = Arrays.copyOf(byteReader4(data), 4);
                struct.biPlanes = Arrays.copyOf(byteReader2(data), 2);
                struct.biBitCount = Arrays.copyOf(byteReader2(data), 2);
                struct.biCompression = Arrays.copyOf(byteReader4(data), 4);
                struct.biSizeImage = Arrays.copyOf(byteReader4(data), 4);
                struct.biXPelsPerMeter = Arrays.copyOf(byteReader4(data), 4);
                struct.biYPelsPerMeter = Arrays.copyOf(byteReader4(data), 4);
                struct.biClrUsed = Arrays.copyOf(byteReader4(data), 4);
                struct.biClrImportant = Arrays.copyOf(byteReader4(data), 4);
                if(data[14] >=108) {
                    struct.bV4RedMask = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4GreenMask = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4BlueMask = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4AlphaMask = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4CSType = Arrays.copyOf(byteReader4(data), 4);

                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4Endpoints = Arrays.copyOf(byteReader4(data), 4);

                    struct.bV4GammaRed = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4GammaGreen = Arrays.copyOf(byteReader4(data), 4);
                    struct.bV4GammaBlue = Arrays.copyOf(byteReader4(data), 4);
                    if(data[14] >= 124) {
                        struct.bV5Intent = Arrays.copyOf(byteReader4(data), 4);
                        struct.bV5ProfileData = Arrays.copyOf(byteReader4(data), 4);
                        struct.bV5ProfileSize = Arrays.copyOf(byteReader4(data), 4);
                        struct.bV5Reserved = Arrays.copyOf(byteReader4(data), 4);
                    }
                }
            }
        }
        //для унификации переделать на нахождение размера, если это 1б поля


        while (count < data.length){
            ArrayList<Byte> array = new ArrayList<>();
            array.add(data[count + 2]);
            array.add(data[count + 1]);
            array.add(data[count]);
            struct.colors.add(array);
            count+=3;
        }
        count = 0;
        return struct;
    }

    private byte[] byteReader2(byte[] data){
        byte[] reader = new byte[2];
        reader[0] = data[count];
        reader[1] = data[count + 1];
        count+=2;
        return reader;
    }

    private byte[] byteReader4(byte[] data){
        byte[] reader = new byte[4];
        reader[0] = data[count];
        reader[1] = data[count + 1];
        reader[2] = data[count + 2];
        reader[3] = data[count + 3];
        count+=4;
        return reader;
    }



}

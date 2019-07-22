import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

class BMPStruct {

        ArrayList<Byte> bfType = new ArrayList<>();
        ArrayList<Byte> bfSize = new ArrayList<>();
        ArrayList<Byte> bfReserved1 = new ArrayList<>();
        ArrayList<Byte> bfReserved2 = new ArrayList<>();
        ArrayList<Byte> bfOffBits = new ArrayList<>();

        ArrayList<Byte> bcSize = new ArrayList<>();
        ArrayList<Byte> bcWidth = new ArrayList<>();
        ArrayList<Byte> bcHeight = new ArrayList<>();
        ArrayList<Byte> bcPlanes = new ArrayList<>();
        ArrayList<Byte> bcBitCount = new ArrayList<>();

        ArrayList<Byte> biSize = new ArrayList<>();
        ArrayList<Byte> biWidth = new ArrayList<>();
        ArrayList<Byte> biHeight = new ArrayList<>();
        ArrayList<Byte> biPlanes = new ArrayList<>();
        ArrayList<Byte> biBitCount = new ArrayList<>();
        ArrayList<Byte> biCompression = new ArrayList<>();
        ArrayList<Byte> biSizeImage = new ArrayList<>();
        ArrayList<Byte> biXPelsPerMeter = new ArrayList<>();
        ArrayList<Byte> biYPelsPerMeter = new ArrayList<>();
        ArrayList<Byte> biClrUsed = new ArrayList<>();
        ArrayList<Byte> biClrImportant = new ArrayList<>();

        ArrayList<Byte> bV4RedMask = new ArrayList<>();
        ArrayList<Byte> bV4GreenMask = new ArrayList<>();
        ArrayList<Byte> bV4BlueMask = new ArrayList<>();
        ArrayList<Byte> bV4AlphaMask = new ArrayList<>();
        ArrayList<Byte> bV4CSType = new ArrayList<>();
        ArrayList<Byte> bV4Endpoints = new ArrayList<>();
        ArrayList<Byte> bV4GammaRed = new ArrayList<>();
        ArrayList<Byte> bV4GammaGreen = new ArrayList<>();
        ArrayList<Byte> bV4GammaBlue = new ArrayList<>();

        ArrayList<Byte> bV5Intent = new ArrayList<>();
        ArrayList<Byte> bV5ProfileData = new ArrayList<>();
        ArrayList<Byte> bV5ProfileSize = new ArrayList<>();
        ArrayList<Byte> bV5Reserved = new ArrayList<>();

        ArrayList<ArrayList<Integer>> colors = new ArrayList<>();



}

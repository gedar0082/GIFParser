import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

class BMPStruct {

        byte[] bfType = new byte[2];
        byte[] bfSize = new byte[4];
        byte[] bfReserved1 = new byte[2];
        byte[] bfReserved2 = new byte[2];
        byte[] bfOffBits = new byte[4];

        byte[] bcSize = new byte[4];
        byte[] bcWidth = new byte[2];
        byte[] bcHeight = new byte[2];
        byte[] bcPlanes = new byte[2];
        byte[] bcBitCount = new byte[2];

        byte[] biSize = new byte[4];
        byte[] biWidth = new byte[4];
        byte[] biHeight = new byte[4];
        byte[] biPlanes = new byte[2];
        byte[] biBitCount = new byte[2];
        byte[] biCompression = new byte[4];
        byte[] biSizeImage = new byte[4];
        byte[] biXPelsPerMeter = new byte[4];
        byte[] biYPelsPerMeter = new byte[4];
        byte[] biClrUsed = new byte[4];
        byte[] biClrImportant = new byte[4];

        byte[] bV4RedMask = new byte[4];
        byte[] bV4GreenMask = new byte[4];
        byte[] bV4BlueMask = new byte[4];
        byte[] bV4AlphaMask = new byte[4];
        byte[] bV4CSType = new byte[4];
        byte[] bV4Endpoints = new byte[36];
        byte[] bV4GammaRed = new byte[4];
        byte[] bV4GammaGreen = new byte[4];
        byte[] bV4GammaBlue = new byte[4];

        byte[] bV5Intent = new byte[4];
        byte[] bV5ProfileData = new byte[4];
        byte[] bV5ProfileSize = new byte[4];
        byte[] bV5Reserved = new byte[4];

        ArrayList<ArrayList<Byte>> colors = new ArrayList<>();



}

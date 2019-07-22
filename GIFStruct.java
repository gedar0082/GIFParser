import java.util.ArrayList;

public class GIFStruct {
    GIFStruct(String header, String imageSize, ArrayList<String> globColorChart, ArrayList<String> expansionCode, ArrayList<ArrayList<Integer>> imageCode, ArrayList<String> decodedImages){
        this.header = header;
        this.imageSize = imageSize;
        this.globColorChart = globColorChart;
        this.expansionCode = expansionCode;
        this.imageCode = imageCode;
        this.decodedImages = decodedImages;
    }

    public static GIFStruct struct = new GIFStruct("", "", null, null, null, null);

    private String imageSize;
    private String header;
    private ArrayList<String> globColorChart;
    private ArrayList<String> expansionCode;
    private ArrayList<ArrayList<Integer>> imageCode;
    private ArrayList<String> decodedImages;
    
    public void setImageSize(String imageSize){
        this.imageSize = imageSize;
    }

    void setHeader(String header){
        this.header = header;
    }

    void setGlobColorChart(ArrayList<String> globColorChart){
        this.globColorChart = globColorChart;
    }

    void setExpansionCode(ArrayList<String> expansionCode){
        this.expansionCode = expansionCode;
    }

    void setImageCode(ArrayList<ArrayList<Integer>> imageCode){
        this.imageCode = imageCode;
    }

    void setDecodedImages(ArrayList<String> decodedImages){
        this.decodedImages = decodedImages;
    }

    String getImageSize(){
        return imageSize;
    }

    String getHeader(){
        return header;
    }

    ArrayList<String> getGlobColorChart(){
        return globColorChart;
    }

    ArrayList<String> getExpansionCode(){
        return expansionCode;
    }

    ArrayList<ArrayList<Integer>> getImageCode(){
        return imageCode;
    }

    ArrayList<String> getDecodedImages(){
        return decodedImages;
    }
}

import java.util.ArrayList;

public class LZWCoding {

    private String testDict = "0A B2 5D 0A B2 5D 0A B2 5D 0A B2 5D F3 ED 63 F3 ED 63 F3 ED 63 F3 ED 63 00 80 C8 00 80 C8 00 80 C8 00 80 C8 F1 60 22 F1 60 22 F1 60 22 F1 60 22";

    private ArrayList<Integer> poses = new ArrayList<>();



    void filling(String st){
        String P = "";
        String C;
        String PC;
        int position = 0;
        ArrayList<String> dictionary = new ArrayList<>();
        for (int i = 0; i < (int)(st.length() / 9) + 1; i++){
            dictionary.add(st.substring(position, position + 8));
            position+=9;
        }
        for (int i = 0; i < testDict.length(); i++){
            C = testDict.substring(0, 8);
            testDict = testDict.substring(9);
            if (P.equals("")){
                PC = C;
            }else{
                PC = P + " " + C;
            }
            if (dictionary.contains(PC)) P = PC;
            else{
                dictionary.add(PC);
                poses.add(dictionary.indexOf(P));
                P = C;
                if(i == testDict.length() - 1){
                      poses.add(dictionary.indexOf(C));
                }
            }
        }
        System.out.println(" ");
        System.out.println("dictionary " + dictionary);
        System.out.println("poses " + poses);
    }

@SuppressWarnings("unchecked")
    ArrayList<String> decoder(){

        ArrayList<String> colorpalet = new ArrayList<>();
        ArrayList<ArrayList<Integer>> recover;
        recover = (ArrayList<ArrayList<Integer>>)GIFStruct.struct.getImageCode().clone();
        for (ArrayList<Integer> poses: recover){
            //int pW;
            int cW;
            String PW;
            String CW;
            ArrayList<String> decodeDict;
            decodeDict = (ArrayList<String>)GIFStruct.struct.getGlobColorChart().clone();
            StringBuilder newstr = new StringBuilder();
            poses.subList(0, 12).clear();
            String f = arrToReversBinStr(poses);
            //decodeDict.clear();

            int buffer = (int)(Math.log(decodeDict.size())/Math.log(2)) + 1;

            f = f.substring(buffer);
            cW = getbuf(buffer, f);
            f = f.substring(buffer);
            CW = decodeDict.get(cW);
            newstr.append(CW);
            PW = CW;
            int gh = 0;
            while (f.length() > buffer){
                gh++;
                int a = getbuf(buffer, f);
                if (a == 9) break; // проверка на последний элемент
                f = f.substring(buffer);
                cW = a;
                if (a >= decodeDict.size()) decodeDict.add(PW + PW);
                CW = decodeDict.get(cW);
                newstr.append(CW);
                if (!decodeDict.contains(PW + CW.substring(0, 9))) decodeDict.add(PW + CW.substring(0, 9));
                if ((int)(Math.log(decodeDict.size())/Math.log(2)) + 1 > buffer) buffer++;
                PW = CW;
            }
            colorpalet.add(newstr.toString());

        }
        GIFStruct.struct.setDecodedImages(colorpalet);
        System.out.println("colorpalet " + colorpalet);
        return colorpalet;
    }


    private String arrToReversBinStr(ArrayList<Integer> ps){
        StringBuilder str = new StringBuilder();
        String temp;
        String t1;
        String t2;
        for (Integer pose : ps) {
            StringBuilder tempByte = new StringBuilder();
            temp = String.format("%02X", pose);
            t1 = temp.charAt(0) + "";
            t1 = hexAlphabet(t1);
            t2 = temp.charAt(1) + "";
            t2 = hexAlphabet(t2);
            tempByte.append(t1);
            tempByte.append(t2);
            tempByte.reverse();
            str.append(tempByte.toString());
        }
        return str.toString();
    }



    private String hexAlphabet(String num){
        switch (num){
            case ("0"): return "0000";
            case ("1"): return "0001";
            case ("2"): return "0010";
            case ("3"): return "0011";
            case ("4"): return "0100";
            case ("5"): return "0101";
            case ("6"): return "0110";
            case ("7"): return "0111";
            case ("8"): return "1000";
            case ("9"): return "1001";
            case ("A"): return "1010";
            case ("B"): return "1011";
            case ("C"): return "1100";
            case ("D"): return "1101";
            case ("E"): return "1110";
            case ("F"): return "1111";
            default: return null;
        }
    }

    private int getbuf(int buf, String str){
        if (str.length() < buf) return -1;
        String a = str.substring(0, buf);
        StringBuilder aa = new StringBuilder();
        aa.append(a);
        aa.reverse();
        a = aa.toString();
        return Integer.parseInt(a, 2);
    }
}
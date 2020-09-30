package si.zad2.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private final static int ROWS_NUM = 9;
    private final static int COLUMNS_NUM = 9;
    String  file = "src/main/resources/log.txt";
    FileWriter fw = null;
    static PrintWriter  pw;


    public Logger()  {
//        System.out.println("heh");
        File f= new File(file);
        boolean delete = f.delete();
//        System.out.println(delete);

        try {
            if(!delete){
                new FileWriter(file, false).close();
            }
            fw = new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw = new PrintWriter(fw, true);
    }

    public   void log(int[][] numbers,int nr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nr).append(";");
        stringBuilder.append("sad;");
        for(int i=0;i<ROWS_NUM;i++){
            for(int j=0;j<COLUMNS_NUM;j++){
                stringBuilder.append(numbers[i][j]);
            }
        }

        pw.println(stringBuilder.toString());
//        stringBuilder.setLength(0);
//        pw.close();
    }

}

package lab1.util;

import org.apache.logging.log4j.util.StringBuilderFormattable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
      String  file = "src/main/resources/log.txt";
      FileWriter fw = null;
      PrintWriter pw;



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

    public  void log(String message) {
//        File f= new File(file);
//        f.delete();
        pw.println(message);
//        pw.close();
    }


}

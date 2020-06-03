package Contacts;
import java.io.*;
public class FileRW {
    private static FileWriter fileWriter;
    private static FileReader fileReader;
    private static BufferedReader bf;
    private static BufferedWriter bw;
    private static File file = new File("D:\\dest.txt");
    public static void fileWrite(String s) {
        try {
            fileWriter = new FileWriter(file, true);
            bw = new BufferedWriter(fileWriter);
            bw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }
    public static String fileRead(String dest) {
        try {
            fileReader = new FileReader(file);
            bf = new BufferedReader(fileReader);
            String ss;
            while((ss = bf.readLine()) != null) {
                String[] temp = ss.split(",");
                if(temp[0].equals(dest)) {
                    return ss;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
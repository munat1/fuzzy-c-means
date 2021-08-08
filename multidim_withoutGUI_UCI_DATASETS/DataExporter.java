import java.util.Random;
import java.io.*;
import java.util.List;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

public class DataExporter{

	public DataExporter(){
}

    public void saveFile(String content, File file) {
        try {
          FileWriter fileWriter = null;
          fileWriter = new FileWriter(file);
          fileWriter.write(content);
          fileWriter.close();
        } catch (IOException ex) {
        }
    }
}


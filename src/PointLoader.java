import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;


public class PointLoader {

    public String fileText = null;

    public PointLoader(File file){
        try{
            this.fileText = readWholeFile(file);
        } catch(Exception e){
            System.out.println("ERROR");
        }
    }

    public String readWholeFile(File file) throws FileNotFoundException {
        String entireFileText = new Scanner(file)
            .useDelimiter("\\A").next();
        return (entireFileText);
    }

    public List<Vector> getPointsFromFile(String prefix) {
        String text = this.fileText;
        String result = text.substring(text.indexOf(prefix) + prefix.length(), text.indexOf("DatasetEnd"));        
        String lines[] = result.split("\\r?\\n");
        List<Vector> pointSet = new ArrayList<>();

        for(int i = 1; i < lines.length; i += 2){
            Vector point = new Vector(Double.parseDouble(lines[i]),Double.parseDouble(lines[i+1]));
            pointSet.add(point);
        }

        return pointSet;
    }

    public List<Vector> getVectors() {
        List<Vector> nodes = getPointsFromFile("DatasetStart");
        //nodes.addAll(0, getPointsFromFile("HighwayNodes"));
        //nodes.addAll(0, getPointsFromFile("RoutingNodes"));
        HashSet<String> hs = new HashSet<>();
        List<Vector> nodes_tmp = new ArrayList<>();
        for(Vector v : nodes) {
            if (!hs.contains(v.toString())) {
                nodes_tmp.add(v);
                hs.add(v.toString());
            }
        }
        return nodes_tmp;
    }
}
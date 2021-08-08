import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;


public class DataImporter{
    //public DataSet _dataset;
	public List<Vector> vList = new ArrayList<>();
    public DataImporter(){
    }

    public void readWholeFile(File file){
        try{
            Scanner sc = new Scanner(file);
            String entireFileText ="";
            String firstline = sc.nextLine();
            int count = firstline.length() - firstline.replace(",", "").length() +1;
            while (sc.hasNextLine()){
                entireFileText+=sc.nextLine()+"\n";
            }
            String lines[]=entireFileText.split("\n|,");
            //System.out.println("count="+count);
            int attribute_count = (lines.length)/count;
            for (int i = 0; i< attribute_count;i++){
                double[] data=new double[count];
                for (int j = 0; j<count;j++){
                    data[j]=Double.parseDouble(lines[i*count+j]);
                }
                Vector v = new Vector(data);
                this.vList.add(v);
                //System.out.println(v.toString());
            }
            //int dimension = vList.get(0)._dimension;

            //this._dataset = new DataSet(vList, dimension, center_size);
        }
        catch(Exception e){
            System.out.println("File not found");
        }
    }
    public void convert_to_dataset(){
        if(vList.size()!=0){
            int dimension = vList.get(0)._dimension;

        }
    }
}

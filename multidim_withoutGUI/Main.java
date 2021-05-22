import java.util.*;
import java.io.*;
import java.util.Hashtable;
import java.net.MalformedURLException;

public class Main{
	public static void main(String[] args) {
		List<Double> m_values = new ArrayList<>();
		m_values.add(1.0);
		m_values.add(1.1);
		m_values.add(1.2);
		m_values.add(1.3);
		m_values.add(1.4);
		m_values.add(1.5);
		m_values.add(1.6);
		m_values.add(1.7);
		m_values.add(1.8);
		m_values.add(1.9);
		m_values.add(2.0);
		m_values.add(2.1);
		m_values.add(2.2);
		m_values.add(2.3);
		m_values.add(2.4);
		m_values.add(2.5);
		m_values.add(2.6);
		m_values.add(2.7);
		m_values.add(2.8);
		m_values.add(2.9);
		m_values.add(3.0);
		double eps=Math.pow(10,-5);
        String output = "dimension,cluster_size,m_value,iteration count\r\n";
		double m=0.5;
		for (int j = 2; j<10; j++){
			for (int k = 2; k<10; k++){
				int cluster_size=j;
				int dimension =k;
				DataSet dataset = new DataSet(cluster_size, dimension);
				dataset.generateRandomPoints();



				for (int i = 0; i<m_values.size(); i++){

					output+=dimension+ ",";
					output+=cluster_size+",";
					m=m_values.get(i);
					output+=m+",";
					dataset.calculate_Fuzzy_c_means_Clustering(eps, true, m_values.get(i));
			        output += dataset.iteration_count;	
                    System.out.println("dimension = "+ dimension + ", cluster size = " + cluster_size+", m= "+ m_values.get(i)+ " ,iteration count= "+ dataset.iteration_count);
                    output+="\r\n";
				}
			}
		}
	DataExporter d = new DataExporter();
	File file = new File("/Users/munat/Desktop/fuzzy-c-means/multidim_withoutGUI/output");
	d.saveFile(output, file);
	}
}	

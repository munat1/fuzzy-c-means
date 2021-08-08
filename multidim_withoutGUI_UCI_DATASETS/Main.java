import java.util.*;
import java.io.*;
import java.util.Hashtable;
import java.net.MalformedURLException;

public class Main{

	public static void main(String[] args) {
		List<List<Integer>> permutations_for_3 = new ArrayList<>();
		permutations_for_3.add(java.util.Arrays.asList(0, 1, 2));
		permutations_for_3.add(java.util.Arrays.asList(0, 2, 1));
		permutations_for_3.add(java.util.Arrays.asList(1, 0, 2));
		permutations_for_3.add(java.util.Arrays.asList(1, 2, 0));
		permutations_for_3.add(java.util.Arrays.asList(2, 0, 1));
		permutations_for_3.add(java.util.Arrays.asList(2, 1, 0));

		//m_values
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
		m_values.add(3.1);
		m_values.add(3.2);
		m_values.add(3.3);
		m_values.add(3.4);
		m_values.add(3.5);
		m_values.add(3.6);
		m_values.add(3.7);
		m_values.add(3.8);
		m_values.add(3.9);
		m_values.add(4.0);
		m_values.add(4.1);
		m_values.add(4.2);
		m_values.add(4.3);
		m_values.add(4.4);
		m_values.add(4.5);
		m_values.add(4.6);
		m_values.add(4.7);
		m_values.add(4.8);
		m_values.add(4.9);
		m_values.add(5.0);
		m_values.add(5.1);
		m_values.add(5.2);
		m_values.add(5.3);
		m_values.add(5.4);
		m_values.add(5.5);
		m_values.add(5.6);
		m_values.add(5.7);
		m_values.add(5.8);
		m_values.add(5.9);
		m_values.add(6.0);
		m_values.add(6.1);
		m_values.add(6.2);
		m_values.add(6.3);
		m_values.add(6.4);
		m_values.add(6.5);
		m_values.add(6.6);
		m_values.add(6.7);
		m_values.add(6.8);
		m_values.add(6.9);
		m_values.add(7.0);
		List<Double> overlapping_durchlauf = new ArrayList<>();
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		overlapping_durchlauf.add(0.0);
		List<Double> timecomplexity_durchlauf = new ArrayList<>();
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		timecomplexity_durchlauf.add(0.0);
		//epsilon
		double eps=Math.pow(10,-8);
		//datapfad
		String uci="/Users/munat/Desktop/uci/";
		//labelled data
		String clusters="/cluster";
		//datapfad dictionaries
		List<String> directories = new ArrayList<>();
		//directories.add("glas");
		directories.add("iris");
		//directories.add("letter");
		directories.add("wine");
		//directories.add("yeast");
		List<String> datasets=new ArrayList<>();
		//datasets.add("/_8_glas.csv");
		datasets.add("/_3_iris.csv");
		//datasets.add("/_26_letter.csv");
		datasets.add("/_3_wine.csv");
		//datasets.add("/_10_yeast.csv");
		//file extension .csv
		String extension=".csv";
		for (int y=0; y<directories.size(); y++) {
			
		
		//extracting center amount from file name
		String[] splitted_import_file=datasets.get(y).split("_");
		int center_size = Integer.parseInt(splitted_import_file[1]);
		//init jeweils cluster .csv's
		List<String> labelled_datasets_cluster=new ArrayList<>();
		for (int i=0;i<center_size;i++){
			labelled_datasets_cluster.add(clusters+i+extension);
		}

		String import_not_labelled=uci+directories.get(y)+datasets.get(y);
		File non_labelled_file = new File(import_not_labelled);
		DataImporter dataimport_not_labelled = new DataImporter();
		dataimport_not_labelled.readWholeFile(non_labelled_file);
		int dimension=dataimport_not_labelled.vList.get(y)._dimension;

		for (int yx = 0 ; yx<100; yx++) {
			

		List<Vector> initial_centers=new ArrayList<>();
		while (initial_centers.size()!=center_size){
			Random r = new Random();
			int x = r.nextInt(dataimport_not_labelled.vList.size());
			if (!initial_centers.contains(dataimport_not_labelled.vList.get(x))) initial_centers.add(dataimport_not_labelled.vList.get(x));
		}

		DataSet dataset=new DataSet(dataimport_not_labelled.vList,dimension,center_size);

		
		
		//System.out.println(yx+"-th versuch:\n");
		for (int p=0; p<m_values.size(); p++)
		{
			//System.out.println("m="+m_values.get(p));
			dataset.calculate_Fuzzy_c_means_Clustering(eps, initial_centers,m_values.get(p));
			timecomplexity_durchlauf.set(p, timecomplexity_durchlauf.get(p)+dataset.iteration_count);
			//timecomplexity_out+=directories.get(y)+","+m_values.get(p)+","+String.valueOf(yx)+","+String.valueOf(dataset.iteration_count)+"\n";
			dataset.hardclustering();
			Matrix overlap=new Matrix(center_size,center_size);
		//extracting centers from labelled data into ground_truth
			for (int j=0; j<labelled_datasets_cluster.size(); j++) {

				String importing_file_name=uci+directories.get(y)+labelled_datasets_cluster.get(j);//burasi 0 yerine l olacak hepsini implement edince
				File file = new File(importing_file_name);
				DataImporter di = new DataImporter();
				di.readWholeFile(file);
				//compare labelled and algorithm clusters

				for (Vector v:di.vList){
					int index_in_vlist=0;
					int index_in_hardcluster=0;
					for (int k=0;k<dataset.vList.size();k++){
						if (v._compare(dataset.vList.get(k))) index_in_vlist=k;
					}
					for (int k=0;k<dataset.centers.size();k++){
						if (dataset.hardcluster_matrix.data[index_in_vlist][k]!=0) {
							index_in_hardcluster=k;
						}
					}
					
					double tmp = overlap.data[j][index_in_hardcluster];
					tmp+=1.0;
					overlap.printMatrix();
					overlap.set_value(j,index_in_hardcluster,tmp);
				}
				for (int z=j; z<center_size; z++) {
					for (int n=0; n<center_size; n++) {
						double tmp = overlap.data[z][n];
						overlap.set_value(z,n,tmp);
					}
				}
			}
			double max_so_far=0;
			if(center_size==3){
				for (int fac=0; fac<permutations_for_3.size(); fac++) {
					double tmp=0;
					for (int fact=0; fact<permutations_for_3.get(fac).size(); fact++) {
						tmp+=overlap.data[fact][permutations_for_3.get(fac).get(fact)];
					}
					if (tmp>max_so_far) {
						max_so_far=tmp;
					}
				}
			}
			//System.out.println("maximized overlap = " + max_so_far);
			max_so_far=max_so_far/dataset.vList.size();
			//System.out.println("maximized overlap in percent = " + max_so_far);
			//approximation_outfile+=directories.get(y)+","+String.valueOf(m_values.get(p))+","+String.valueOf(yx)+","+String.valueOf(max_so_far)+"\n";
			overlapping_durchlauf.set(p, overlapping_durchlauf.get(p)+max_so_far);
		}
		/*DataExporter d = new DataExporter();
		File output_approximation = new File(uci+directories.get(y)+"/"+"approximation_output"+String.valueOf(yx)+".csv");
		File timecomplexity_outfile = new File(uci+directories.get(y)+"/"+"timecomplexity_output"+String.valueOf(yx)+".csv");
		d.saveFile(approximation_outfile,output_approximation);
		d.saveFile(timecomplexity_out, timecomplexity_outfile);*/

		}
		String approximation_outfile="Dataset,Fuzzifier,Übereinstimmung\n";
		String timecomplexity_out="Dataset,Fuzzifier,Iteration\n";
		for (int yx=0; yx<timecomplexity_durchlauf.size(); yx++) {
			timecomplexity_durchlauf.set(yx, timecomplexity_durchlauf.get(yx)/100);
			overlapping_durchlauf.set(yx, overlapping_durchlauf.get(yx)/100);
			
			timecomplexity_out+=datasets.get(y)+","+m_values.get(yx)+","+timecomplexity_durchlauf.get(yx)+"\n";
			approximation_outfile+=datasets.get(y)+","+m_values.get(yx)+","+overlapping_durchlauf.get(yx)+"\n";
			//System.out.println("iter="+timecomplexity_durchlauf.get(yx)+" and overlap in%="+ overlapping_durchlauf.get(yx));
		}
		DataExporter d = new DataExporter();
		File output_approximation = new File(uci+directories.get(y)+"/"+"approximation_output_1-7_eps-8.csv");
		File timecomplexity_outfile = new File(uci+directories.get(y)+"/"+"timecomplexity_output_1-7_eps-8.csv");
		d.saveFile(approximation_outfile,output_approximation);
		d.saveFile(timecomplexity_out, timecomplexity_outfile);
	}
	}
}

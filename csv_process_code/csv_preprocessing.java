/*************************************************************************
 *  Compilation:  javac csv_preprocessing.java
 *  Execution:    java csv_preprocessing
 *
 *************************************************************************/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *  The <tt>csv_preprocessing</tt> class is used for .csv file preprocessing
 *
 *  @author Yudi An
 */
public class csv_preprocessing  {


  /**
  * main function for processing
  * @throws Exception.
  */
	public static void main(String[] args) throws IOException {
		List<Timestamp> list = new ArrayList<Timestamp>();
        BufferedReader bfr;
        BufferedWriter fw;
        String line;    
        String fileName="green_tripdata_2015-01.csv";
        File file=new File(fileName);       
              
        try{

            //read csv file and create empty output file
            bfr=new BufferedReader(new FileReader(file));
            File output = new File("output.txt");
		    if(!output.exists()){
            	output.createNewFile();
        	} 

            //bufferwritter to write output
		    fw = new BufferedWriter(new FileWriter(output));
            
            // set date format
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //processing the csv file line by line
            while((line=bfr.readLine())!=null){

            	String[] tokens = line.split(",");
            	String raw_t = tokens[1];

            	try{
				    Date parsedDate = dateFormat.parse(raw_t);
				    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
				    // System.out.println(dateFormat.format(timestamp));
				    list.add(timestamp);
			    } catch (ParseException e) {
			    	System.out.println("Exception :" + e);
			    }               
            }

            //sort the date by increasing ordr manner
            Collections.sort(list,new TimeCompare());

            //Merge the same date together and write into output.txt
            int index = 0;
            String str;
            while(true){
            	if(index >= list.size()) break;
            	int count = 1;
            	while(index+1 < list.size()&& list.get(index).compareTo(list.get(index+1))==0){
            		count ++;
            		index ++;
            	}

            	str = dateFormat.format(list.get(index));
            	System.out.println(str +","+count);
            	index++;

            	
            	fw.write(str +","+count);
            	fw.newLine();

            }
            bfr.close();
            fw.close();

        }catch(FileNotFoundException fex){
            fex.printStackTrace();
        }

    }


    


}
  /**
  * TimeCompare function is  used for compare two datetime
  * 
  */
class TimeCompare implements Comparator<Timestamp> {

	    @Override
	    public int compare(Timestamp t1, Timestamp t2) {
	        // write comparison logic here like below , it's just a sample
	        return t1.compareTo(t2);
	    }
	}




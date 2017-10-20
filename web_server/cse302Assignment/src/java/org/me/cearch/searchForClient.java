/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.cearch;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.FileReader;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 *  The <tt>searchForClient</tt> class is used for compute the number of pickups every minute.
 *
 *  @author Yudi An
 */
@WebService(serviceName = "searchForClient")
@Stateless()
public class searchForClient {
    private Date[] timestamp = new Date[1076814];
    private int[] count = new int[1076814];
    private  int[] output;
    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * Web service operation
     */
    @WebMethod(operationName = "runSever")
    public void runSever() throws IOException {

        File file = new File("output.txt");
        readFile(file);

        
    }
    
    /**
     * read preprocessed file
     */
    private void readFile(File file ) throws IOException{
        BufferedReader bfr;
        try{

             bfr=new BufferedReader(new FileReader(file));

                int i = 0;
                String line; 

            while((line=bfr.readLine())!=null){
                String[] tokens = line.split(",");
                // Timestamp t = Timestamp.valueOf(tokens[0]);
                Date d = parseDataTime(tokens[0]);
                int c = Integer.parseInt(tokens[1]); 
                timestamp[i] = d;
                count[i] = c;
                i++;
             }
            bfr.close();

        }catch(FileNotFoundException fex){
            fex.printStackTrace();
        } 
    }  

    /**
     * check the datetime valid or not
     */
    private Date parseDataTime(String s){    	
    	try{
		    return dateFormat.parse(s);
	    } catch (ParseException e) {
	    	System.out.println("Your input data is invalid, Please check your input format as yyyy-MM-dd HH:mm:ss");
	    	// System.out.println("The Error message is follow:　"＋ｅ);
	    }

	    return new Date();
    }

    /**
     * Web service operation
     * do the search process
     * @reuturn reuslt array with number of pickups in time1-time2
     */
    @WebMethod(operationName = "doSearch")
    public int[] doSearch(@WebParam(name = "s1") String s1, @WebParam(name = "s2") String s2) {
        //get two time from clinet
        Date timeslot = parseDataTime(s1);
        Date timeslot1 = parseDataTime(s2);
        //comoute the diff of two times in minute
        int x = (int)getDateDiff(timeslot ,timeslot1 ,TimeUnit.MINUTES);
    	output = new int[x+1]; 
    	int index = indexOf(timestamp, timeslot);// binary search the time1 index in database
    	int i = 0;
    	Date temp = timeslot;
    	temp.setMinutes(1+temp.getMinutes());
    	while(timestamp[index].compareTo(timeslot1)<=0){
    		if(timestamp[index].compareTo(temp)>=0){
    			temp.setMinutes(1+temp.getMinutes());
    			i++;
    		}
    		output[i] += count[index];
    		index++;
    		if(index>timestamp.length-1) break;
    	}
       
        return output;
    }

    
    
    /**
     * binary search in date array(database)
     * @reuturn the smallest index that bigger or equals to the search date
     */
    private int indexOf(Date[] a, Date key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (key.compareTo(a[mid])<0) hi = mid - 1;
            else if (key.compareTo(a[mid])>0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * binary search in date array(database)
     * @reuturn different in minute between time1-time2
     */
    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    


}

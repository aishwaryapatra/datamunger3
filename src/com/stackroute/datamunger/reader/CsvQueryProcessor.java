package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	File fileName;

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.fileName=new File(fileName);

	}
	

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {
		BufferedReader bfr=new BufferedReader(new FileReader(fileName));
		String text=bfr.readLine();
		String[] ar=text.split(",");
		if(ar.length!=-1) {
		Header hd=new Header(ar);
		return hd;
		}
		else {
			return null;
		}

		// read the first line

		// populate the header object with the String array containing the header names
		
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		try {
		int i = 0;
		//String type = null;
		BufferedReader bf=new BufferedReader(new FileReader(fileName));
		
		String text=bf.readLine();
		int len=text.split(",").length;
		text=bf.readLine();
		String[] ar=text.split(",");
		String[] arr= new String[len];
		//if(ar.length != -1) {
		for(String s:ar ) {
			try {
			int x=Integer.parseInt(s);
			arr[i]="java.lang.Integer";
			}
			catch(Exception e1) {
				try {
					double x=Double.parseDouble(s);
					arr[i]="java.lang.Double";
				}
				catch(Exception e2) {
//					if(ar[i] instanceof String)
						s="java.lang.String";
				}
			} 
			finally {
				i++;
			}
		}
				for(i=0;i<arr.length;i++) {
					if(arr[i]==null)
						arr[i]="java.lang.String";
				}
			
		
		
		
				DataTypeDefinitions df=new DataTypeDefinitions(arr);
				bf.close();
	

		return df;
		
		}
		
		catch(Exception e) {
			DataTypeDefinitions df = new DataTypeDefinitions(new String[0]);
			return df;
		
		}
	}
}
		
		
	


		


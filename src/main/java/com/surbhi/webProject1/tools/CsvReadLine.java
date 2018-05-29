package com.surbhi.webProject1.tools;

import java.io.File;
import java.io.FileReader;

import java.lang.reflect.Method;


import org.mongojack.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.opencsv.CSVReader;

import com.surbhi.webProject1.app.Utility;
import com.surbhi.webProject1.model.Email;
import com.surbhi.webProject1.requests.DBConnection;

public class CsvReadLine {
	public static void main(String args[]){
		try{


			DBConnection db1 = new DBConnection();
			DB mongo;
			mongo=db1.getDB();
			DBCollection Emailcollection = mongo.getCollection("emails");
			JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);


			File f = new File("C:\\Users\\Java\\Documents");

			if(f.isDirectory()) {
				System.out.println(f.listFiles());


				File[] listOfFiles = f.listFiles();
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()&&listOfFiles[i].getName().contains(".csv")) {
						System.out.println("File " + listOfFiles[i].getName());



						CSVReader reader = new CSVReader(new FileReader(listOfFiles[i]));
						CSVReader reader1 = new CSVReader(new FileReader(listOfFiles[i]));
						String [] nextLine;
						int lineNumber = 0;
						int len = reader1.readNext().length;
						reader1.close();
						System.out.println(len);
						String [] getmethod = new String[len] ;
						String [] setmethod = new String[len];

						while ((nextLine = reader.readNext()) != null) {
							lineNumber++;
							Email email = new Email();
							// System.out.println("Line # " + lineNumber);

							for(int column = 0;column<nextLine.length;column++){
								System.out.println(nextLine[column] + " ");

								if(lineNumber==1){
									String find = nextLine[column].substring(0,1).toUpperCase() + nextLine[column].substring(1);
									getmethod[column] ="get" + find;
									setmethod[column] ="set" + find;

									System.out.println(getmethod[column]);
									System.out.println(setmethod[column]);
								}
								else{
									//Field field = Email.class.getField(name)
									Method method1 = Email.class.getDeclaredMethod(getmethod[column]);
									System.out.println(getmethod[column]);
									method1.invoke(email);
									String type = method1.getReturnType().getSimpleName();
									System.out.println(type);System.out.println(nextLine[column]);
									Object changes = nextLine[column];
									if(!type.equalsIgnoreCase("String")){
										Utility utility = new Utility();
										changes = utility.changeType(type.toString(), nextLine[column]);
									}

									Class<?> typeClass1 = method1.getReturnType();


									Method methods = Email.class.getDeclaredMethod(setmethod[column],typeClass1);


									methods.invoke(email,changes);
								}


							}
							if(lineNumber!=1)
								coll.insert(email);
							lineNumber++;
						}

						reader.close();
					} else if (listOfFiles[i].isDirectory()) {
						System.out.println("Directory " + listOfFiles[i].getName());
					}
				}

			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

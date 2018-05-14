package com.surbhi.webProject1.tools;

import java.io.File;
import java.io.FileReader;

import java.lang.reflect.Method;
import java.util.List;

import org.mongojack.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.opencsv.CSVReader;

import com.surbhi.webProject1.app.Utility;
import com.surbhi.webProject1.model.Email;
import com.surbhi.webProject1.requests.DBConnection;

public class CsvReadAll {
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

						CSVReader reader = new CSVReader(new FileReader(f));
						List<String[]> allData = reader.readAll();
						System.out.println(allData);

						String getmethod [] =new String[allData.get(0).length];
						String setmethod [] =new String[allData.get(0).length];


						int rowcount = 0;
						for(String[] row : allData)
						{
							Email email = new Email();
							int columnNum = 0;
							for(String column : row)
							{
								if(rowcount==0){
									String find = column.substring(0,1).toUpperCase() + column.substring(1);
									getmethod[columnNum] ="get" + find;
									setmethod[columnNum] ="set" + find;

									System.out.println(getmethod[columnNum]);
									System.out.println(setmethod[columnNum]);
								}
								else{

									Method method1 = Email.class.getDeclaredMethod(getmethod[columnNum]);

									method1.invoke(email);
									String type = method1.getReturnType().getSimpleName();
									Object changes = column;
									if(!type.equalsIgnoreCase("String")){
										Utility utility = new Utility();
										changes = utility.changeType(type.toString(), column);
									}

									Class<?> typeClass1 = method1.getReturnType();


									Method methods = Email.class.getDeclaredMethod(setmethod[columnNum],typeClass1);


									methods.invoke(email,changes);
								}
								System.out.print(column + ";");
								columnNum++;
							}
							if(rowcount!=0)
								coll.insert(email);
							rowcount++;
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

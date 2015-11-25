package ut02.ejemplos.DOM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class RandomEmpleado {
		private static final int ID_FIELD = 0;
		private static final int NAME_FIELD = 1;
		private static final int DEP_FIELD = 2;
		private static final int SALARY_FIELD = 3;

		public final static int NOMBRE_LENGTH = 20;
		public final static int RECORD_LENGTH = 4+NOMBRE_LENGTH*2+4+8;
		private static final int MAX_RECORD = 100;

/**
 * method to import a csv into a random file 
 * @param csv
 * @param randomFile
 */
		public static void importCSV(Path csv, Path randomFile){
			String csvRecord;
			try(BufferedReader br = 
					Files.newBufferedReader(csv, Charset.forName("utf8"))){
					while( (csvRecord= br.readLine())!=null){
						Empleado employee = readEmpleadoLine(csvRecord,";");
						if(employee!=null)
							writeEmployee(employee,randomFile);
					}
				
			} catch (IOException e) {
				System.err.println("IOException");
			}
					
		}
/**
 * method to write an object employee in their position in a random file
 * @param employee
 * @param randomFile
 */
		private static void writeEmployee(Empleado employee,Path randomFile) {
			try(RandomAccessFile raf = new
					RandomAccessFile(randomFile.toFile(),"rw")){
				    writeRandomRecord(employee,raf);
				
			} catch (FileNotFoundException e) {
				System.err.format("File %s not found",randomFile.getFileName());

			} catch (IOException e) {
				System.err.format("IOException writing %s",randomFile.getFileName());

			}
		}
/**
 * method to write in an open random stream a employee object
 * @param employee
 * @param raf
 * @throws IOException
 */
		private static void writeRandomRecord(Empleado employee, RandomAccessFile raf) throws IOException {
			int pos = (employee.getId()-1)*RECORD_LENGTH;
			if(employee.getId()<MAX_RECORD){
				raf.seek(pos);
				raf.writeInt(employee.getId());
				raf.writeChars(formatString(employee.getNombre(),NOMBRE_LENGTH));
				raf.writeInt(employee.getDep());
				raf.writeDouble(employee.getSalario());
			}

		}

		/**
		 * method to fill a string 
		 * @param string to fill
		 * @param size of the string
		 * @return
		 */
		private static String formatString(String string, int  size) {
				StringBuffer sb = new StringBuffer(string);
				sb.setLength(size);
				
				return sb.toString();
		
		}
/**
 * method to read an employee object from a line readed from a csv file
 * @param csvRecord line
 * @param regex separator
 * @return
 */
		private static Empleado readEmpleadoLine(String csvRecord, String regex ) {
			Empleado employee = null;
			String[] fields = csvRecord.split(regex);

			try{
			employee = new Empleado(Integer.parseInt(fields[ID_FIELD]),
					fields[NAME_FIELD],
					Integer.parseInt(fields[DEP_FIELD]),
					Double.parseDouble(fields[SALARY_FIELD]));
			} catch(NumberFormatException e){
				
			}
			return employee;
		}
		
		/**
		 * Method to read all records from a rando File in to an ArrayList
		 * @param randomFile
		 * @return ArrayList<Empleado>
		 */
		public static ArrayList<Empleado> getAll(Path randomFile){
			ArrayList<Empleado> employees = new ArrayList<>();
			try(RandomAccessFile raf = new
					RandomAccessFile(randomFile.toFile(),"r")){
				while(raf.getFilePointer()<raf.length())
				    	employees.add(readRandomRecord(raf));
				
			} catch (FileNotFoundException e) {
				System.err.format("File %s not found",randomFile.getFileName());

			} catch (IOException e) {
				System.err.format("IOException writing %s",randomFile.getFileName());

			}
			
			return employees;
		}

		/**
		 * method to read an object Employee from an open random access stream
		 * @param raf
		 * @return
		 * @throws IOException
		 */
		private static Empleado readRandomRecord(RandomAccessFile raf) throws IOException {
			Empleado employee = new Empleado();
			int id, dep;
			double salary;
			String name;
			
			id = raf.readInt();
			name = readRandomString(raf,NOMBRE_LENGTH);
			dep = raf.readInt();
			salary = raf.readDouble();
			employee = new Empleado(id,name,dep,salary);
			
			return employee;
		}

		/**
		 * method to read a string from an open random access stream
		 * @param raf
		 * @param length
		 * @return
		 * @throws IOException
		 */
		private static String readRandomString(RandomAccessFile raf, int length) throws IOException   {
			StringBuilder sb = new StringBuilder();
			char character;
			for(int i = 0; i< length;i++){
				if((character = raf.readChar())!=0){
					sb.append(character);
				}
				else sb.append(" ");
			}
			return sb.toString();
		}
}

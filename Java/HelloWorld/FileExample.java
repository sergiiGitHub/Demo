import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileExample{

	public static void main(String[] args) {

		File dir = new File("/home/sergii/temp");
		if ( !dir.exists() ){
			dir.mkdir();
		}
		File file = new File(dir, "write.txt"); // make object
		file.createNewFile();// create readl file
		fileWriterExample( file );
		readFileExmample( file );
		printWriterExanple( file );	
		bufferReaderExample( file );
		
		File newName = new File(dir, "newWrite.txt");
		file.renameTo(newName);
		String[] list = dir.list();
		for (String string : list) {
			System.out.print(string + " ");
		}
		
		//readFileExmample( file );
	}

	private static void bufferReaderExample(File file) {
		
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(file);
			br  = new BufferedReader(fr);
			int count = 1;
			String s;
			while( (s = br.readLine()) != null){
				System.out.print(count);				
				System.out.println(s);
				++count;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch ( IOException e ){
			e.printStackTrace();
		} finally {
			if ( br != null ){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void readFileExmample(File file) {
		int size = 0;
		char[] in = new char[50];
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			size = fr.read(in);
			System.out.println(size + " ");
			for(char c : in){
				System.out.print(c);
			}
			System.out.println(); //if not us missed first line
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( fr != null ){
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void fileWriterExample(File file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file);  // create an actual file & a FileWriter obj
			fw.write("howdy\nfolks\n"); // write characters to
			fw.flush();		            // flush before closing
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}

	private static void printWriterExanple( File aFile ) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(aFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		pw.println("hello");
		pw.println("world");
		pw.flush();
		pw.close();	
	}
}

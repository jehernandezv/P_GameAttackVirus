package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

	public class FileManager {
		
	public static final String PATH_DATA_SERVER = "./configClients/";

	public static File writeFile(String[] listValuesInit, int idObservable)throws IOException {
		File file = new File(PATH_DATA_SERVER+ idObservable + ".txt");
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for (int i = 0; i < listValuesInit.length; i++) {
			bw.write(listValuesInit[i]);
			bw.newLine();
		}
		bw.close();
		return file;
	}
	
	public static File getFileOfPath(File path,String nameFileSearch){
		File [] nameFiles = path.listFiles();
		File fileSearch = null;
		int cont = 0;
		boolean flag = false;
		while (!flag && cont < nameFiles.length) {
			System.out.println(nameFiles[cont].getName());
			if(nameFiles[cont].getName().equals(nameFileSearch)){
				fileSearch = new File(path.getAbsolutePath()+"/"+nameFileSearch);
				flag = true;
			}
			cont ++;
		}
	return fileSearch;
}
	
	public static String readFile(File file) {
		String cadena;
		String files = "";
		try {
			FileReader word = new FileReader(file);
			BufferedReader b = new BufferedReader(word);
			try {
				while ((cadena = b.readLine()) != null) {
					files += cadena + "/";
				}
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return files;
	}
	public static void deleteFileForName(String nameFile){
		File [] files = new File(PATH_DATA_SERVER).listFiles();
		boolean flag = false;
		int cont = 0;
		while (!flag && cont < files.length) {
			if(files[cont].getName().equals(nameFile)){ 
				flag = true;
			}
			if(!flag){
			cont++;
			}
		}
		files[cont].delete();
	}
	
}
package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileCreator implements Runnable{
	private int digits ;
	private int loops;
	private char[] passwordArray;
	private char[] charactersArray;
	private int from;
	private int to;
	private String beggining;
	private String ending;
	
	private ProgressBarClass pbc = new ProgressBarClass();
	
	private void createTextFile(int f, int t, String beg, String end) throws IOException{
	
		int num_of_chars = charactersArray.length;
		String pass;
		char last_char = charactersArray[num_of_chars-1];
		char first_char = charactersArray[0];
		int from = f;
		int to = t;
		beggining = beg;
		ending = end;
		int current_pw = 0;
		int total_num_of_pws = getTotalNumberOfPws(num_of_chars, from, to);
		
		//File desktop = new File(System.getProperty("user.home"), "Desktop");
		//File directory = new File("/home/mintpc/Desktop/");
		//String workingDir = directory.getCanonicalPath() + File.separator;
		//String linux_desktop_path = Desktop.getDesktop() + "";
		
		File file = new File( "passwords.txt");
	    FileWriter writer = new FileWriter(file);

		//PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
	    
		
	    
		for(int k = from; k<to; k++){
			digits = k;
			loops = ((int) Math.pow(num_of_chars, digits));
			setPasswordArray(first_char, digits);
			
			for(int j=0; j<loops; j++){
				if(passwordArray[digits-1] != last_char){
					passwordArray[digits-1] = charactersArray[retNumFrOthArr(digits-1, passwordArray, charactersArray) + 1];
				}else{
					passwordArray[digits-1] = first_char;
					for(int i=digits-2; i>=0; i--){				//checks if the next character is also '= last_char'
						if(passwordArray[i]!= last_char){
							passwordArray[i] = charactersArray[retNumFrOthArr(i, passwordArray, charactersArray) + 1];
							break;
						}else{
							passwordArray[i] = first_char;
						}
					}
				}	
				//ar1 = list1.stream().toArray(Character[]::new);
				
				pass = String.valueOf(passwordArray); 
				pass = new StringBuilder(pass).insert(0, beggining).toString();
				pass = new StringBuilder(pass).insert(pass.length(), ending).toString();
				writer.write(pass);
				writer.write('\n');
				current_pw++;
				pbc.changePbStatus(current_pw, total_num_of_pws);
				System.out.println(pass);
			}
			
		}
		pbc.fileCreated();
		writer.close();
		System.out.println(total_num_of_pws);
		
	}
		
	private static int retNumFrOthArr(int p, char[] c1, char[] c2){
		int r = 0;
		for(int x=0; x<c2.length; x++){
			if(c1[p] == c2[x]){
				r = x;
				break;
			}
		}
		return r;  
	}
	
	private void setPasswordArray(char fc, int d){
		passwordArray = new char[d];
		for (int i = 0; i < passwordArray.length; i++){
			passwordArray[i] = fc;
		}
	}
	
	public void setCharactersArray( boolean uclChecked, boolean lclChecked, boolean numsChecked, boolean symsChecked){
		char[] allNumbersArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		char[] allCapsArray =    {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char[] allSmallArray =   {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		char[] symbolsArray =    {'`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '='};

		String str = new String("");

		if(uclChecked){
			str = String.copyValueOf(allCapsArray);
		}
		if(lclChecked){
			str = str + String.copyValueOf(allSmallArray);
		}
		if(numsChecked){
			str = str + String.copyValueOf(allNumbersArray);
		}
		if(symsChecked){
			str = str + String.copyValueOf(symbolsArray);
		}
		
		charactersArray = new char[str.length()];
		charactersArray = str.toCharArray();
	}
	
	public void setValues(int from, int to, String beggining, String ending){
		this.from = from;
		this.to = to;
		this.beggining = beggining;
		this.ending = ending;
	}
	
	private int getTotalNumberOfPws(int x, int y, int z){
		int num = 0;
		for(int i = y; i < z; i++){
			num +=  Math.pow(x, i);
		}
		return num;
	}

	@Override
	public  void run() {
		try {

			createTextFile(from, to, beggining, ending);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

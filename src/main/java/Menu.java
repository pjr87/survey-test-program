
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/


/**
 * Description of Menu.
 * 
 * @author phillipryan
 */
public class Menu {
	/**
	 * Description of the property test.
	 */
	private static Test test = null;

	/**
	 * Description of the property survey.
	 */
	private static Survey survey = null;

	public static InputOutput io = new SpeechIO();

	/**
	 * The constructor.
	 */
	public Menu() {
		super();
	}

	/**
	 * Description of the method main.
	 * @param args 
	 */
	public static void main(String args[]){
		TextIO out = new TextIO();
		out.Output1("The output of this program is entirely with sound.");
		out.Output1("Please turn on sound");
		out.Output("Input from the user is still recevied from console text.");
		out.Output("As output is read you can respond at any moment, but speech output will continue until full output is read");
		
		DisplayMenu();
	}

	/**
	 * Description of the method SurveyMenu.
	 */
	public static void DisplayMenu() {
		while(true){
			outputMenu1();
			int choice = io.getInput();
			if( choice == 1 ){
				survey = new Survey();
				outputMenu2("Survey");
			}
			else if( choice == 2 ){
				test = new Test();
				outputMenu2("Test");
			}
			else{
				io.Output("Error invalid choice");
				DisplayMenu();
			}
		}
	}
	
	public static void outputMenu1(){
		io.Output("1) Survey");
		io.Output("2) Test");
	}
	
	public static void outputMenu2(String type){
		String filename = "";
		while(true){
			if(!filename.isEmpty()){
				if(type.equals("Survey")){
					Save(survey, filename);
				}
				else{
					Save(test, filename);
				}
			}
			io.Output("1) Create a " + type);
			io.Output("2) Display a " + type);
			io.Output("3) Load a " + type);
			io.Output("4) Save a " + type);
			io.Output("5) Modify an existing " + type);
			io.Output("6) Take a " + type);
			io.Output("7) Tabulate a " + type);
			io.Output("8) Quit");
			if(!type.equals("Survey")){
				io.Output("9) Grade a " + type);
			}
			
			int choice = io.getInput();
			if(choice == 1){
				
				if( type.equals("Survey")){
					io.Output("Creating survey");
					survey.Create();
				}
				else{
					io.Output("Creating test");
					test.Create();
				}
			}
			else if( choice == 2 ){
				io.Output("Display");
				if( type.equals("Survey")){
					survey.Display();
				}
				else{
					test.Display();
				}
			}
			else if( choice == 3 ){
				
				File dir = new File(".");
				List<String> Files = new ArrayList<String>();
				int i = 1;
				for (File file : dir.listFiles()) {
					if (file.getName().endsWith((".ser"))){
						io.Output(i + ") " + file.getName());
						Files.add(file.getName());
						i++;
					}
				}
				
				int file = 0;
				io.Output("Size " + Files.size());
				while((file == 0)){
					io.Output("Please select a file to load");
					file = io.getInput();
				}
				if(file > Files.size()){
					io.Output("Error that file does not exist");
					break;
				}
				io.Output("Loading " + Files.get(file-1));
				filename = Files.get(file-1);
				if(Files.get(file-1).contains("Survey")){
					survey = (Survey) Load(Files.get(file-1));
					type = "Survey";
				}
				else{
					test = (Test) Load(Files.get(file-1));
					type = "Test";
				}
			}
			else if( choice == 4 ){
				io.Output("Save");
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
				if(type.equals("Survey")){
					filename = "Survey" + sdf.format(cal.getTime()) + ".ser";
					Save(survey, "Survey" + sdf.format(cal.getTime()) + ".ser");
				}
				else{
					filename = "Test" + sdf.format(cal.getTime()) + ".ser";
					Save(test, "Test" + sdf.format(cal.getTime()) + ".ser");
				}
			}
			else if( choice == 5 ){
				io.Output("Modify");
				if( type.equals("Survey")){
					filename = modifyFile(type);
					if( filename.isEmpty() ){
						break;
					}
					survey.Edit();
				}
				else{
					filename = modifyFile(type);
					if( filename.isEmpty() ){
						break;
					}
					test.Edit();
				}
				
				if(type.equals("Survey")){
					Save(survey, filename);
				}
				else{
					Save(test, filename);
				}
			}
			else if( choice == 6 ){
				io.Output("Take");
				filename = takeFile(type);
				if( type.equals("Survey")){
					survey.Take();
				}
				else{
					test.Take();
				}
				if(type.equals("Survey")){
					Save(survey, filename);
				}
				else{
					Save(test, filename);
				}
			}
			else if( choice == 7 ){
				io.Output("Tabulate");
				TabGradFile(type);
				if( type.equals("Survey")){
					survey.Tabulate();
				}
				else{
					test.Tabulate();
				}
			}
			else if( choice == 8 ){
				io.Output("Quit");
				break;
			}
			else if( choice == 9 ){
				io.Output("Grade");
				TabGradFile(type);
				if( type.equals("Survey")){
				}
				else{
					test.Grade();
				}
			}
			else{
				io.Output("Error invalid response");
				outputMenu2(type);
			}
		}
	}
	
	private static String TabGradFile(String type)
	{
		String filename = "";
		
		File dir = new File(".");
		List<String> Files = new ArrayList<String>();
		int i = 1;
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((".ser"))){
				io.Output(i + ") " + file.getName());
				Files.add(file.getName());
				i++;
			}
		}
		
		int file = 0;
		while((file == 0)){
			io.Output("Please select a " + type + " to Tabulate or Grade");
			file = io.getInput();
		}
		if(file > Files.size()){
			io.Output("Error that " + type + " does not exist");
		}
		io.Output("Loading " + Files.get(file-1));
		filename = Files.get(file-1);
		if(Files.get(file-1).contains("Survey")){
			survey = (Survey) Load(Files.get(file-1));
			type = "Survey";
		}
		else{
			test = (Test) Load(Files.get(file-1));
			type = "Test";
		}
		
		return filename;
	}

	private static String takeFile(String type)
	{
		String filename = "";
		
		File dir = new File(".");
		List<String> Files = new ArrayList<String>();
		int i = 1;
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((".ser"))){
				io.Output(i + ") " + file.getName());
				Files.add(file.getName());
				i++;
			}
		}
		
		int file = 0;
		while((file == 0)){
			io.Output("Please select a " + type + " to take");
			file = io.getInput();
		}
		if(file > Files.size()){
			io.Output("Error that " + type + " does not exist");
		}
		io.Output("Loading " + Files.get(file-1));
		filename = Files.get(file-1);
		if(Files.get(file-1).contains("Survey")){
			survey = (Survey) Load(Files.get(file-1));
			type = "Survey";
		}
		else{
			test = (Test) Load(Files.get(file-1));
			type = "Test";
		}
		
		return filename;
	}
	
	private static String modifyFile(String type)
	{
		String filename = "";
		File dir = new File(".");
		List<String> Files = new ArrayList<String>();
		int i = 1;
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((".ser"))){
				io.Output(i + ") " + file.getName());
				Files.add(file.getName());
				i++;
			}
		}
		
		int file = 0;
		while((file == 0)){
			io.Output("Please select a file to load");
			file = io.getInput();
		}
		if(file > Files.size()){
			io.Output("Error that file does not exist");
			return "";
		}
		io.Output("Loading " + Files.get(file-1));
		filename = Files.get(file-1);
		if(Files.get(file-1).contains("Survey")){
			survey = (Survey) Load(Files.get(file-1));
			type = "Survey";
		}
		else{
			test = (Test) Load(Files.get(file-1));
			type = "Test";
		}
		return filename;
	}
	
	/**
	 * Description of the method Load.
	 * @param filename 
	 */
	public static Object Load(String filename) {
		FileInputStream input;
		Object object = null;
		try {
			input = new FileInputStream(filename);
	        ObjectInputStream output = new ObjectInputStream(input);
	        object = output.readObject();
	        output.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return object;
	}

	/**
	 * Description of the method Save.
	 * @param object 
	 * @param filename 
	 */
	public static void Save(Object object, String filename) {
		FileOutputStream output;
		try {
			output = new FileOutputStream(filename);
	        ObjectOutputStream oos = new ObjectOutputStream(output);
	        oos.writeObject(object);
	 
	        output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns test.
	 * @return test 
	 */
	public Test getTest() {
		return this.test;
	}

	/**
	 * Sets a value to attribute test. 
	 * @param newTest 
	 */
	public void setTest(Test newTest) {
		this.test = newTest;
	}

	/**
	 * Returns survey.
	 * @return survey 
	 */
	public Survey getSurvey() {
		return this.survey;
	}

	/**
	 * Sets a value to attribute survey. 
	 * @param newSurvey 
	 */
	public void setSurvey(Survey newSurvey) {
		this.survey = newSurvey;
	}

}

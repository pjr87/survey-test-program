/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
// Start of user code (user defined imports)
import java.util.Scanner;

// End of user code

/**
 * Description of Survey.
 * 
 * @author phillipryan
 */
public class Survey implements Serializable {
	/**
	 * Description of the property questions.
	 */
	protected List<Question> questions = new ArrayList<Question>();
	
	//Used to save take attempts
	//When a person does a take, record their name
	//Add questions that the user takes to the userAttempts array as they complete them
	//When a new person takes a test add a new userName and userAttempt list of questions
	//The userName and userAttempts should always line up for grade reporting purposes
	protected List<List<String>> userAttempts = new ArrayList<List<String>>();
	List<String> userNames = new ArrayList<String>();
	
	public static InputOutput io = new SpeechIO();

	/**
	 * Description of the method Create.
	 */
	public void Create() {
		while(true){
			io.Output("1) Add a new T/F question");
			io.Output("2) Add a new multiple choice question");
			io.Output("3) Add a new short answer question");
			io.Output("4) Add a new essay question");
			io.Output("5) Add a new ranking question");
			io.Output("6) Add a new matching question");
			io.Output("7) Quit");
			
			int tmpchoice = io.getInput();
			if(tmpchoice == 1){
				io.Output("T/F");
				TrueFalse tf = new TrueFalse(new StringCar());
				tf.Create();
				questions.add(tf);
			}
			if(tmpchoice == 2){
				io.Output("multiple choice");
				MultipleChoice mc = new MultipleChoice(new StringCar());
				mc.Create();
				questions.add(mc);
			}
			if(tmpchoice == 3){
				io.Output("short answer");
				ShortAnswer sa = new ShortAnswer(new StringCar());
				sa.Create();
				questions.add(sa);
			}
			if(tmpchoice == 4){
				io.Output("essay");
				Essay es = new Essay(new StringCar());
				es.Create();
				questions.add(es);
			}
			if(tmpchoice == 5){
				io.Output("ranking");
				Rank ra = new Rank(new StringCar());
				ra.Create();
				questions.add(ra);
			}
			if(tmpchoice == 6){
				io.Output("matching");
				Matching ma = new Matching(new StringCar());
				ma.Create();
				questions.add(ma);
			}
			if(tmpchoice == 7){
				io.Output("quit");
				break;
			}
			if(tmpchoice == 0){
				io.Output("Error, please enter a number");
				Create();
			}
		}
	}
	
	/**
	 * Description of the method Display.
	 */
	public void Display() {
		for(int i = 0 ; i < questions.size() ; i++){
			 io.Output1(i+1 + ") ");
			 questions.get(i).Display();
			 questions.get(i).response.displayChoices();
			 io.Output("");
		}
		io.Output("");
	}
	 
	/**
	 * Description of the method Take.
	 */
	public void Take() {
		List<String> responses = new ArrayList<String>();
		for(int i = 0 ; i < questions.size() ; i++){
			questions.get(i).response.clearResponses();
		}
		
		io.Output("Please Enter your name: ");
		String name = io.getInputString();
		userNames.add(name);
		
		for(int i = 0 ; i < questions.size() ; i++){
			io.Output1(i+1 + ") ");
			questions.get(i).Display();
			questions.get(i).response.displayChoices();
			 
			questions.get(i).Take();
			responses.add(questions.get(i).response.getResponse());
			io.Output("");
		}
		
		userAttempts.add(responses);
		SaveResponse(name, responses);
	}
	
	public void SaveResponse(String username, List<String> responses)
	{
		BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new FileWriter("UserAttempts", true));
			
			int n=0;
			for(n=0; n<userNames.size();n++){
				if(username.equals(userNames.get(n))){
					break;
				}
			}
			
			outputWriter.write("Name: " + username + ": ");
			for(int i = 0 ; i < responses.size() ; i++){
				outputWriter.write(responses.get(i) + ", ");
			}
			outputWriter.write("\n");
			
			outputWriter.flush();  
			outputWriter.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 
	/**
	 * Description of the method Delete.
	 */
	public void Delete() {
		
	}
	 
	/**
	 * Description of the method Tabulate.
	 */
	public void Tabulate() {
		int n=0;
		int numAttempts = userAttempts.size();
		
		Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
		for(n=0; n<numAttempts;n++){
			int numQuestion = userAttempts.get(n).size();
			
			for(int i = 0 ; i < numQuestion; i++){
				Map<String, Integer> questionmap;
				List<String>responses = userAttempts.get(n);
				String prompt = questions.get(i).prompt;
				if(map.get(prompt) == null){
					questionmap = new HashMap<String, Integer>();
					map.put(prompt, questionmap);
				}
				else{
					questionmap = map.get(prompt);
				}
				String resp = userAttempts.get(n).get(i);
				if(questionmap.get(resp) == null){
					questionmap.put(resp, 1);
				}
				else{
					questionmap.put(resp, questionmap.get(resp)+1);
				}
			}
		}

		//Iterate over choices
		for(int j=0; j<questions.size(); j++){
			String prompt = questions.get(j).prompt;
			io.Output(j+1 + ") " + prompt);
			Map<String, Integer> responses = map.get(prompt);
			
			Iterator it = responses.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        io.Output("\t" + pair.getKey() + ") " + pair.getValue());
		    }
		}
	}
	 
	/**
	 * Description of the method Edit.
	 */
	public void Edit() {
		boolean response = false;
		String tmpResp = "";
		for(int i = 0 ; i < questions.size() ; i++){
			questions.get(i).Display();
			io.Output("Do you wish to modify this question?");
			tmpResp = io.getInputString();
			response = io.interpretResponse(tmpResp);
			
			if( response ){
				io.Output("Do you wish to modify the prompt?");
				tmpResp = io.getInputString();
				response = io.interpretResponse(tmpResp);
				if( response ){
					io.Output(i+1 + ") ");
					questions.get(i).Display();
					questions.get(i).Edit(1);
				}
				
				if(questions.get(i).response.lengthChoices() > 0 || questions.get(i).numberOfChoices > 0){
					questions.get(i).response.displayChoices();
					io.Output("Do you wish to modify the choices?");
					tmpResp = io.getInputString();
					response = io.interpretResponse(tmpResp);
					if( response ){
						questions.get(i).response.displayChoices();
						questions.get(i).Edit(2);
					}
				}
			}
		}
	}

	/**
	 * Returns questions.
	 * @return questions 
	 */
	public List<Question> getQuestions() {
		return this.questions;
	}
}

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/


// Start of user code (user defined imports)

// End of user code

/**
 * Description of Test.
 * 
 * @author phillipryan
 */
public class Test extends Survey implements Serializable{

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
				tf.CreateCA();
				questions.add(tf);
			}
			if(tmpchoice == 2){
				io.Output("multiple choice");
				MultipleChoice mc = new MultipleChoice(new StringCar());
				mc.Create();
				mc.CreateCA();
				questions.add(mc);
			}
			if(tmpchoice == 3){
				io.Output("short answer");
				ShortAnswer sa = new ShortAnswer(new StringCar());
				sa.Create();
				sa.CreateCA();
				questions.add(sa);
			}
			if(tmpchoice == 4){
				io.Output("essay");
				Essay es = new Essay(new StringCar());
				es.Create();
				es.CreateCA();
				questions.add(es);
			}
			if(tmpchoice == 5){
				io.Output("ranking");
				Rank ra = new Rank(new StringCar());
				ra.Create();
				ra.CreateCA();
				questions.add(ra);
			}
			if(tmpchoice == 6){
				io.Output("matching");
				Matching ma = new Matching(new StringCar());
				ma.Create();
				ma.CreateCA();
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
	 * Description of the method Grade.
	 */
	public void Grade() {
		int n=0;
		for(n=0; n<userNames.size();n++){
			int total=0;
			int correct=0;
			io.Output("Name: " + userNames.get(n) + ": ");
			List<String>responses = userAttempts.get(n);
			for(int i = 0 ; i < questions.size() ; i++){
				total++;
				if(questions.get(i).response.Grade(responses.get(i))){
					correct++;
				}
				if(questions.get(i).isEssay){
					total--;
				}
			}
			int grade = correct / total;
			io.Output("Grade: " + (correct*10) + "/" + (total*10));
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
				
				if(questions.get(i).response.lengthCA() > 0){
					questions.get(i).response.getCA();
					io.Output("Do you wish to modify the correct answers?");
					tmpResp = io.getInputString();
					response = io.interpretResponse(tmpResp);
					if( response ){
						questions.get(i).response.displayChoices();
						questions.get(i).Edit(3);
					}
				}
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
			 
			 Car tmpcar =  questions.get(i).response;
			 tmpcar.getCA();
			 io.Output("");
		}
		io.Output("");
	}
}

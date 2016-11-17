import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Matching.
 * 
 * @author phillipryan
 */
public class Matching extends Question {


	protected Matching(Car car) {
		super(car);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Description of the method Create.
	 */
	public void Create() {
		io.Output("Enter the prompt for your Matching question");
		String Prompt = io.getInputString();
		prompt = Prompt;
		
		while(numberOfChoices == 0){
			io.Output("Enter the size of each column:");
			numberOfChoices = io.getInput();
		}
		
		io.Output("Enter column one");
		for(int i=0; i<numberOfChoices; i++){
			io.Output("Enter choice");
			String Choice = io.getInputString();
			response.setChoices(Choice);
		}
		
		io.Output("Enter column two");
		for(int i=0; i<numberOfChoices; i++){
			io.Output("Enter choice");
			String Choice = io.getInputString();
			response.setChoices(Choice);
		}
	}
	
	@Override
	protected void CreateCA() {
		int Correct = 0;
		List<String> choices = response.getChoice();
		response.displayChoices();
		for(int i=0; i<numberOfChoices; i++){
			while(Correct == 0){
				io.Output("Enter number this choice matches with: "  + choices.get(i));
				Correct = io.getInput();
			}
			response.setCorrectAnswer(String.valueOf(Correct));
			Correct = 0;
		}
	}

	/**
	 * Description of the method Display.
	 */
	public void Display() {
		io.Output(this.prompt);
	}

	/**
	 * Description of the method Edit.
	 */
	public void Edit(int type) {
		if( type == 1 ){
			io.Output("Enter the prompt for your question");
			String Prompt = io.getInputString();
			prompt = Prompt;
		}
		else if( type == 2 ){
			int difference = 0, question = 0, newNumChoices = 0;
			while(newNumChoices == 0){
				io.Output("Enter the number of choices total: ");
				newNumChoices = io.getInput();
				difference  = newNumChoices - (numberOfChoices*2);
				numberOfChoices = newNumChoices/2;
			}
			
			for(int i=0; i<difference; i++){
				io.Output("You choose a number of choices greater then your current number of choices");
				io.Output("Enter the choice you would like to add for your question: ");
				String resp = io.getInputString();
				response.setChoices(resp);
			}
			
			boolean cont = true;
			while(cont){
				io.Output("Would you like to edit an existing choice?");
				String Choice = io.getInputString();
				cont = io.interpretResponse(Choice);
				if(cont){
					int sizeOfChoices = response.lengthChoices();
					while(question == 0 && question <= sizeOfChoices){
						io.Output("Enter the choice to modify: ");
						question = io.getInput();
					}
					io.Output("Enter the new choice for your question");
					Choice = io.getInputString();
					response.editChoice(question-1, Choice);
					question = 0;
				}
			}
		}
		else if( type == 3 ){
			if(response.lengthCA() > 0){
				response.clearCA();
			}
			int Correct = 0;
			List<String> choices = response.getChoice();
			for(int i=0; i<numberOfChoices; i++){
				while(Correct == 0){
					io.Output("Enter number this choice matches with: "  + choices.get(i));
					Correct = io.getInput();
				}
				response.setCorrectAnswer(String.valueOf(Correct));
				Correct = 0;
			}
		}
		else{
			io.Output("Error not a recognized option");
		}
	}

	/**
	 * Description of the method Take.
	 */
	public void Take() {
		for(int i=0; i<numberOfChoices; i++){
			io.Output1(i+1 + ") ");
			String Answer = io.getInputString();
			response.setResponse(Answer);
		}
	}
	

	@Override
	protected String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	protected void setUserName(String newUserName) {
		// TODO Auto-generated method stub
		userName = newUserName;
	}

	@Override
	protected Car getResponse() {
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	protected String getPrompt() {
		// TODO Auto-generated method stub
		return prompt;
	}

	@Override
	protected void setPrompt(String newPrompt) {
		// TODO Auto-generated method stub
		prompt = newPrompt;
	}

}


/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

/**
 * Description of Essay.
 * 
 * @author phillipryan
 */
public class Essay extends Question {

	protected Essay(Car car) {
		super(car);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Description of the method Create.
	 */
	public void Create() {
		isEssay = true;
		io.Output("Enter the prompt for your question");
		String Prompt = io.getInputString();
		prompt = Prompt;
		
		while(numberOfChoices == 0){
			io.Output("Enter the number of choices:");
			numberOfChoices = io.getInput();
		}
		response.setCorrectAnswer("none");
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
	public void Edit( int type) {
		if( type == 1 ){
			io.Output("Enter the prompt for your question");
			String Prompt = io.getInputString();
			prompt = Prompt;
		}
		else if( type == 2 ){
			int newNumChoices = 0;
			while(newNumChoices == 0){
				io.Output("Enter the number of choices: ");
				newNumChoices = io.getInput();
				numberOfChoices = newNumChoices;
			}
		}
		else if( type == 3 ){
			io.Output("Essay has no correct answers");
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

	@Override
	protected void CreateCA() {
		// TODO Auto-generated method stub
		
	}
}

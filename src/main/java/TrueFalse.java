import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/


// Start of user code (user defined imports)

// End of user code

/**
 * Description of TrueFalse.
 * 
 * @author phillipryan
 */
public class TrueFalse extends Question{
	
	protected TrueFalse(Car car) {
		super(car);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Description of the method Create.
	 */
	public void Create() {
		while( prompt.equals("") ){
			io.Output("Enter the prompt for your question");
			String Prompt = io.getInputString();
			prompt = Prompt;
		}
	}
	
	@Override
	protected void CreateCA() {
		io.Output("Enter correct choice");
		String Correct = io.getInputString();
		response.setCorrectAnswer(Correct);
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
		}
		else if( type == 3 ){
			String Choice = "";
			response.getCA();
			io.Output("Enter the new correct answer for your question");
			Choice = io.getInputString();
			response.editCA(0, Choice);
		}
		else{
			io.Output("Error not a recognized option");
		}
	}

	@Override
	protected void Take() {
		io.Output1("1) ");
		String Answer = io.getInputString();
		response.setResponse(Answer);
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

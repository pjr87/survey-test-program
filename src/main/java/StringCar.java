/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of StringCar.
 * 
 * @author phillipryan
 */
public class StringCar extends Car {
	/**
	 * Description of the property userResponse.
	 */
	protected List<String> userResponse = new ArrayList<String>();
	
	/**
	 * Description of the property correctAnswer.
	 */
	protected List<String> correctAnswer = new ArrayList<String>();
	
	/**
	 * Description of the property choice.
	 */
	protected List<String> choice = new ArrayList<String>();
	
	public static InputOutput io = new SpeechIO();
	
	/**
	 * Description of the method Grade.
	 * @return 
	 */
	protected Boolean Grade(String response) {
		String corr = "";
		
		for(int i=0; i<correctAnswer.size(); i++){
			corr += correctAnswer.get(i).toUpperCase();
		}
		String resp = response.toUpperCase();
		
		if(!resp.equals(corr)){
			return false;
		}
		return true;
	}
	 
	/**
	 * Description of the method getResponse.
	 * @return 
	 */
	protected String getResponse() {
		String getResponse = "";
		for(int i=0; i<userResponse.size(); i++){
			getResponse += userResponse.get(i);
		}
		return getResponse;
	}
	 
	/**
	 * Description of the method VerifyUserResponse.
	 * @return 
	 */
	protected Boolean VerifyUserResponse() {
		// Start of user code for method VerifyUserResponse
		Boolean VerifyUserResponse = Boolean.FALSE;
		return VerifyUserResponse;
		// End of user code
	}
	 
	/**
	 * Description of the method setCorrectAnswer.
	 */
	protected void setCorrectAnswer(String CA) {
		this.correctAnswer.add(CA);
	}
	 
	/**
	 * Description of the method setChoices.
	 * @param choice 
	 */
	protected void setChoices(String Choice) {
		this.choice.add(Choice);
	}
	
	protected void setResponse(String response) {
		this.userResponse.add(response);
	}
	
	protected void clearResponses() {
		this.userResponse.clear();
	}
	
	protected void clearCA() {
		this.correctAnswer.clear();
	}
	
	protected int lengthChoices() {
		return this.choice.size();
	}
	
	protected int lengthResponses() {
		return this.userResponse.size();
	}
	
	protected int lengthCA() {
		return this.correctAnswer.size();
	}
	
	protected void getCA(){
		if(correctAnswer.size() == 1){
			io.Output("The correct answer is: " + correctAnswer.get(0));
		}
		else{
			io.Output("The correct answer is: ");
			for(int i=0; i<correctAnswer.size(); i++){
				io.Output1(correctAnswer.get(i) + ", ");
			}
			io.Output("");
		}
			
	}
	
	protected void displayChoices(){
		for(int i=0; i<choice.size(); i++){
			io.Output1("\t" + (i+1) + ") ");
			io.Output(choice.get(i));
		}
	}
	
	protected void editChoice(int index, String newChoice){
		this.choice.set(index, newChoice);
	}
	
	protected void editCA(int index, String newChoice){
		this.correctAnswer.set(index, newChoice);
	}

	/**
	 * Returns userResponse.
	 * @return userResponse 
	 */
	protected List<String> getUserResponse() {
		return this.userResponse;
	}

	/**
	 * Returns correctAnswer.
	 * @return correctAnswer 
	 */
	protected List<String> getCorrectAnswer() {
		return this.correctAnswer;
	}

	/**
	 * Returns choice.
	 * @return choice 
	 */
	protected List<String> getChoice() {
		return this.choice;
	}
}

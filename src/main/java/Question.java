/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Question.
 * 
 * @author phillipryan
 */
abstract class Question implements Serializable{
	
	/**
	 * Description of the property userName.
	 */
	protected String userName = "";
	
	/**
	 * Description of the property response.
	 */
	protected Car response;
	
	/**
	 * Description of the property prompt.
	 */
	protected String prompt = "";
	
	protected int numberOfChoices = 0;
	protected int numberOfCorrectChoices = 0;
	
	public boolean isEssay = false;
	
	public static InputOutput io = new SpeechIO();
	
	/**
	 * The constructor.
	 */
	protected Question(Car car) {
		// Start of user code constructor for Question)
		super();
		response = car;
		// End of user code
	}
	
	/**
	 * Description of the method Create.
	 */
	abstract protected void Create();
	
	/**
	 * Description of the method CreateCA.
	 */
	abstract protected void CreateCA();
	 
	/**
	 * Description of the method Display.
	 */
	abstract protected void Display();
	 
	/**
	 * Description of the method Take.
	 */
	abstract protected void Take();
	 
	/**
	 * Description of the method Edit.
	 * 1 - prompt
	 * 2 - choices
	 * 3 - correct answer
	 */
	abstract protected void Edit(int type);

	/**
	 * Returns userName.
	 * @return userName 
	 */
	abstract protected String getUserName();
	
	/**
	 * Sets a value to attribute userName. 
	 * @param newUserName 
	 */
	abstract protected void setUserName(String newUserName);

	/**
	 * Returns response.
	 * @return response 
	 */
	abstract protected Car getResponse();

	/**
	 * Returns prompt.
	 * @return prompt 
	 */
	abstract protected String getPrompt();
	
	/**
	 * Sets a value to attribute prompt. 
	 * @param newPrompt 
	 */
	abstract protected void setPrompt(String newPrompt);
}

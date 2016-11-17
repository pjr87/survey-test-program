import java.io.Serializable;
import java.util.List;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Car.
 * 
 * @author phillipryan
 */
abstract class Car implements Serializable{

	/**
	 * The constructor.
	 */
	protected Car() {
		// Start of user code constructor for Car)
		super();
		// End of user code
	}

	/**
	 * Description of the method Grade.
	 * @return 
	 */
	abstract protected Boolean Grade(String response);

	abstract protected void displayChoices();
	
	abstract protected void getCA();

	/**
	 * Description of the method VerifyUserResponse.
	 * @return 
	 */
	abstract protected Boolean VerifyUserResponse();

	abstract protected void setChoices(String choice);

	abstract protected void setCorrectAnswer(String valueOf);
	
	abstract protected void setResponse(String response);
	
	abstract protected List<String> getChoice();
	
	abstract protected String getResponse();

	abstract protected void editChoice(int index, String newChoice);
	
	abstract protected void editCA(int index, String newChoice);
	
	abstract protected int lengthChoices();
	
	abstract protected int lengthCA();
	
	abstract protected void clearCA();
	
	abstract protected void clearResponses();
	
	abstract protected int lengthResponses();

}

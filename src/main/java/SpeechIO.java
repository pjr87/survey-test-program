import java.util.InputMismatchException;
import java.util.Scanner;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeechIO extends InputOutput{
	protected int getInput(){
		int choice = 0;
		Scanner reader = null;
		try{
			reader = new Scanner(System.in); 
			choice = reader.nextInt(); 
		}
		catch( InputMismatchException e )
		{
			Output("Error, Please input a number");
			reader.next();
			choice = 0;
		}
		return choice;
	}
	
	protected String getInputString(){
		String choice = "";
		Scanner reader = null;
		try{
			reader = new Scanner(System.in); 
			choice = reader.nextLine();
		}
		catch( InputMismatchException e )
		{
			Output("Error, Please input a String");
			reader.next();
			choice = "";
		}
		return choice;
	}
	
	protected boolean interpretResponse(String Response)
	{
		boolean tmp = false;
		
		if( Response.equals("Yes") || Response.equals("yes") || Response.equals("Y") || Response.equals("y"))
		{
			tmp = true;
		}
		
		return tmp;
	}

	@Override
	public void Output(String output) {		
		String voiceName = "kevin16";

		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		
		voice.allocate();
		voice.speak(output);
		voice.deallocate();
	}

	@Override
	protected void Output1(String output) {		
		String voiceName = "kevin16";

		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice voice = voiceManager.getVoice(voiceName);
		
		voice.allocate();
		voice.speak(output);
		voice.deallocate();
	}
}

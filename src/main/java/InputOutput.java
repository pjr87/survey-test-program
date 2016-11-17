
abstract class InputOutput {
	protected abstract int getInput();
	
	protected abstract String getInputString();
	
	protected abstract boolean interpretResponse(String Response);
	
	protected abstract void Output(String output);
	protected abstract void Output1(String output);
}

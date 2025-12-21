package myexceptions;

public class ParsingException extends Exception{
	private String message="";

	public ParsingException() {}
	public ParsingException(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Universo_DB.myexceptions.ParsingException: "+message;
	}
}
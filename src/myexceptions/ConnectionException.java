package myexceptions;

public class ConnectionException extends Exception{
	private String message = "";
	
	public ConnectionException() {}
	public ConnectionException(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "universodb.myexceptions.ConnectionException: "+message;
	}
}
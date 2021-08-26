package vendingMachine.TheVendingMachine.exceptions;

public class ItemErrorResponse {
	
	private int status;
	private String message;
	private long timeStamp;
	public ItemErrorResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemErrorResponse [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", timeStamp=");
		builder.append(timeStamp);
		builder.append("]");
		return builder.toString();
	}
	public ItemErrorResponse() {
	}
	
}

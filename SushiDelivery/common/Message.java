/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;

public class Message implements Serializable{
	MessageType type;
	Object input;
	Object input2;
	Object input3;
	Object input4;
	
	/* Different messageType so that server can give corresponding
	 * responses
	 */
	public enum MessageType {
		REGISTRATION, LOGIN, ORDERMADE, ORDERCANCELLED, REQUESTDISH, REQUESTPOSTCODE
	}
	
	/* Different message constructor depending on the number
	 * of inputs it is going to receive
	 */
	public Message(MessageType type){
		this.type = type;
	}
	
	public Message(MessageType type, Object input){
		this.type = type;
		this.input = input;
	}
	
	
	public Message(MessageType type, Object input, Object input2){
		this.type = type;
		this.input = input;
		this.input2 = input2;
	}
	
	public Message(MessageType type, Object input, Object input2, Object input3, Object input4) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.input = input;
		this.input2 = input2;
		this.input3 = input3;
		this.input4 = input4;
	}

	public String printMessage() {
		return type.name();
	}
	
}

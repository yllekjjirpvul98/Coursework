/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

public class ShutdownHook extends Thread{
	/*  This thread will be run before the JVM terminates
	 */
	public void run() {
		DataPersistance.getDataPersistance().save();
	}
}

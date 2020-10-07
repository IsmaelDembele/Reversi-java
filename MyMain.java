package reversi;

import java.awt.EventQueue;


/**
 * 
 * @author Ismael Dembele
 *
 */
public class MyMain {
	public static void main(String [] args){
		Runnable r = new Runnable(){
			public void run(){
				new Reversi();
			}
		};
		
		EventQueue.invokeLater(r);
	}
}

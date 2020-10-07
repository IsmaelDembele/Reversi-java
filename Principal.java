//package reversi; remove the comment when running with eclipse

import java.awt.EventQueue;

public class Principal {
	public static void main(String [] args){
		Runnable r = new Runnable(){
			public void run(){
				new Reversi();
			}
		};
		
		EventQueue.invokeLater(r);
	}
}

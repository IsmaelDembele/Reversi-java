package reversi;

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

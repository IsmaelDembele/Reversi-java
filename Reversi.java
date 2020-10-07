package reversi;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * 
 * Implementation of the reversi Game
 * 
 * @author Ismael Dembele
 *
 */
public class Reversi extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	//score
	private JPanel pscore;
	private JLabel labelscore;
	String score = "Black 2 : White 2";
	int scoreint = 0;
	
	//buttons
	private JPanel panelButtons;
	private GridLayout layoutButtons;
	private JButton[][] button;
	
	//le jeu
	private int turn = 0; //0 for black button and 1 for white

	
	//panel information
	private JPanel information;
	private JLabel turn_to_play;
	
	//window
	private GridBagLayout mainLayout;
	private GridBagConstraints gbc;
	Container c;
	
	//images
	ImageIcon black_image = new ImageIcon(this.getClass().getResource("blackbutton.png"));
	ImageIcon white_image = new ImageIcon(this.getClass().getResource("whitebutton.png"));
	
	
	public Reversi(){
		//construction of main layout
		mainLayout = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		/////////////////
		//information
		//////////////////////
		information = new JPanel();
		information.setBackground(Color.blue);
		//information.setForeground(Color.white);
		
		information.setLayout(new GridLayout(8,1));
		
		Font f = new Font("Cambria",Font.BOLD,20);
		
		JLabel player1 = new JLabel("  Player 1  ");
		JLabel player2 = new JLabel("  Player 2  ");
		
		
		player1.setFont(f);
		player2.setFont(f);
		
		information.add(player1);
		information.add(new JLabel(black_image));// adding the black image
		
		
		information.add(new JLabel());// empty space
		
		turn_to_play = new JLabel(" Black turn ");
		
		turn_to_play.setFont(f);
		
		information.add(turn_to_play);
		
		information.add(new JLabel());//empty space
		information.add(new JLabel());//empty space
		
		information.add(player2);
		information.add(new JLabel(white_image));
		
		labelscore = new JLabel(score);
		pscore = new JPanel();
		//pscore.setSize(100,300);
		pscore.setForeground(Color.BLACK);
		pscore.setBackground(Color.white);
		
		button = new JButton[8][8];

		this.setSize(500,500);
		
		this.setTitle("Reversi Game");
		
		//creation of the score
		pscore.add(labelscore);
		
		
		//Construction of the buttons
		layoutButtons = new GridLayout(8,8);
		panelButtons = new JPanel();
		panelButtons.setLayout(layoutButtons);
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				if((i == 3 && j == 3) || (i == 4 && j == 4)){
					button[i][j] = new JButton(black_image);
					button[i][j].setName("black");
					
				}else if((i == 3 && j == 4) || (i == 4 && j == 3)){
					button[i][j] = new JButton(white_image);
					button[i][j].setName("white");
				}else{
						button[i][j] = new JButton();
						button[i][j].setName("");
				}
				
				button[i][j].setMaximumSize(new Dimension(55,55));
				button[i][j].setPreferredSize(new Dimension(45,45));
				button[i][j].setMinimumSize(new Dimension(35,35));
				
				button[i][j].addActionListener(this);
				
				panelButtons.add(button[i][j]);
			}
		}
		
		c = this.getContentPane();
		c.setLayout(mainLayout);
		
		
		//Position of the score panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 11;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		c.add(pscore,gbc);
		
		//position of Buttons panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 8;
		gbc.gridheight = 8;
		//gbc.fill = GridBagConstraints.BOTH;
		
		c.add(panelButtons,gbc);
		
		//Position of the information panel
		gbc.gridx = 8;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 8;
		gbc.fill = GridBagConstraints.BOTH;
		
		c.add(information,gbc);
		
		this.setVisible(true);
		this.pack();// consolidate together
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		
		JButton b =  (JButton) e.getSource();
		int x = 0, y = 0;
		
		//finding the position of the button that has been clicked
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(b.equals(button[i][j])){
					x = i;
					y = j;
				}
			}
		}
		
		jeu( b, x, y);
	
		actualiseScore(button);
	}
	

	public void actualiseScore(JButton[][] button){
		int white = 0;
		int black = 0;
		
		for(int i = 0; i < button.length;i++){
			for(int j = 0; j < button.length; j++){
				if(button[i][j].getName().equals("black")){
					black++;
				}else if(button[i][j].getName().equals("white")){
					white++;
				}
			}
		}
		
		score = "black "+black+" : blanc "+white+"  ";
		scoreint = black+white;
		labelscore = new JLabel(score);
		pscore.removeAll();
		pscore.add(labelscore);
	}
	
	public boolean testFin(){
	
		for(int i = 0; i < button.length; i++){
			for(int j = 0; j < button.length; j++){
				if(button[i][j].getName().equals(""))
					return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Write the name of the player who has to play
	 * 
	 */
	public void ActualiseNonjoueur(int turn){
		
		JLabel jl = (JLabel) information.getComponent(3);
		
		if(turn == 0){
			jl.setText("   Black  ");
			
		}else if(turn == 1) {
			jl.setText("   White  ");
		}else if( turn == 2){//end of the game
		
			int blanc = 0;
			int noir = 0;
			
			for(int i = 0; i < button.length;i++){
				for(int j = 0; j < button.length; j++){
					if(button[i][j].getName().equals("black")){
						noir++;
					}else if(button[i][j].getName().equals("white")){
						blanc++;
					}
				}
			}
			
			if(noir > blanc){
				jl.setText("black button win");
			}else if(blanc > noir){
				jl.setText("white button win ");
			}else{
				jl.setText("draw");
			}
			
		}
		((JLabel) information.getComponent(3)).setText(jl.getText());	
	}
	
	public void jeu(JButton b, int i, int j){
		
		boolean testTranfo = false;
		
		if(!testFin()){//the game is not finish
			if(this.turn == 0){// black button
				if(!(b.getName().equals(""))){// if the player clique on an image 
					; // do nothing
				}else{
					testTranfo = transformation( i, j, "black","white",  black_image, white_image);		
				}
				
				if(canPlay( 1) == true){// check to see if the opponent have a possible move 
					if(testTranfo == true){
						turn = 1;
						ActualiseNonjoueur(turn);
					}
				}
				
				if(this.scoreint > 62){//the and of the game
					if((canPlay( 1) == false) && (canPlay(2)) == false){
						ActualiseNonjoueur(2);
					}	
				}
				
			}else{//turn = 1 pour le blanc
				
				
				if(!(b.getName().equals(""))){
					;
				}else{
					
					testTranfo = transformation( i, j, "white","black",  white_image, black_image);
						
				}

				if(canPlay( 0) == true){//on regarde si l'adversaire peut joueur avant de lui donner la main
					//System.out.println("jeu possible black");
				
					if(testTranfo == true){
						turn = 0;
						ActualiseNonjoueur(turn);
					}
				}
			}
			
			if(this.scoreint > 62){//test if the game is finished
				if((canPlay( 1) == false) && (canPlay(2)) == false){
					ActualiseNonjoueur(2);
				}	
			}	
		}
		
	}
	
	/**
	 * 
	 * This function is executed before telling a player that he has to play
	 * 
	 * it will make sure that a player can really play in the actual configuration
	 * of the game. If the player can't play, his adversary will play again.
	 * 
	 * @param turn is 0 for black player and 1 for white player
	 * @return true if the player has possible moves and 0 if there is no possible move
	 */
	public boolean canPlay(int turn){
		
		String _color;
		String opponent;
		
		if(turn == 0){
			_color = "black";
			opponent = "white";
		}else{
			_color = "white";
			opponent = "black";
		}

		int vari, varj;
		
		for(int i = 0; i < button.length; i++){
			for(int j= 0; j < button.length; j++){
				
				if(button[i][j].getName().equals(_color)){
				
					
					
					//verified if the game is possible in the up direction
					vari = i-1;
					if(vari > 0){
						while((vari > 0) && button[vari][j].getName().equals(opponent)){
							vari--;
							
							if(vari == 0 && !button[vari][j].getName().equals("")){
								vari = -1;//break
							}else
							if(button[vari][j].getName().equals("")){
								return true;
							}
						}
					}
					
					//down direction
					vari = i+1;
					if(vari < (button.length - 1) ){
						
						while((vari < button.length ) && button[vari][j].getName().equals(opponent)){
							
							vari++;
							
							if((vari == (button.length - 1)) && !button[vari][j].getName().equals("")){
								vari = button.length;//break
							}else
								if(button[vari][j].getName().equals("")){
								return true;
							}
						}
					}
					
					//left direction
					varj = j-1;
					if(varj > 0){
						while((varj > 0) && button[i][varj].getName().equals(opponent)){
							varj--;
							
							if(varj == 0 && !button[i][varj].getName().equals("")){
								varj = -1;
							}else
								if(button[i][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//right diection
					varj = j+1;
					if(varj < (button.length - 1)){
						while((varj < button.length) && button[i][varj].getName().equals(opponent)){
							varj++;
							
							if((varj == (button.length - 1)) && !button[i][varj].getName().equals("")){
								varj = button.length;
							}else
								if(button[i][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//diag up righ					
					vari = i-1;
					varj = j+1;
					if( (vari > 0) &&  (varj < (button.length - 1))){
						while(( (vari > 0) &&  varj < button.length) && button[vari][varj].getName().equals(opponent)){
							vari--;
							varj++;
							
							if( ((vari == 0) ||  (varj == (button.length - 1)) ) && !button[vari][varj].getName().equals("")){
								vari = -1;
							}else
								if(button[vari][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//diag up left
					vari = i-1;
					varj = j-1;
					if( (vari > 0) &&  (varj > 0)){
						while( (vari > 0) &&  (varj > 0) && button[vari][varj].getName().equals(opponent)){
							vari--;
							varj--;
							
							if( ((vari == 0) ||  (varj == 0) ) && !button[vari][varj].getName().equals("")){
								vari = -1;
							}else
								if(button[vari][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//diag down right
					
					vari = i+1;
					varj = j+1;
					if( (vari < (button.length-1)) &&  (varj < (button.length-1))){
						while( (vari < button.length) &&  (varj < button.length) && button[vari][varj].getName().equals(opponent)){
							vari++;
							varj++;
							
							if( ((vari == (button.length-1)) ||  (varj == (button.length-1)) ) && !button[vari][varj].getName().equals("")){
								vari = button.length;
							}else
								if(button[vari][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//diag down left
					vari = i+1;
					varj = j-1;
					if( (vari < (button.length-1)) &&  (varj > 0)){
						while( (vari < button.length) &&  (varj > 0) && button[vari][varj].getName().equals(opponent)){
							vari++;
							varj--;
							
							if( ((vari == (button.length-1)) ||  (varj == 0) ) && !button[vari][varj].getName().equals("")){
								vari = button.length;
								//varj = -1;
							}else
								if(button[vari][varj].getName().equals("")){
								return true;
							}
						}
					}
					
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * When a button is clicked, this function checked every direction possible to see 
	 * if a move if possible and make those transformation
	 * 
	 * 
	 * @param i coordinate i of the button cliclked
	 * @param j coordinate j of the button clicked
	 * @param j1 hold the name of the black button
	 * @param j2 hold the name of the white button
	 * @param imJ1 is the black button
	 * @param imJ2 is the white button
	 * @return true if a transformation is made, false if there were no transformation
	 * 
	 * 
	 */
	public boolean transformation(int i, int j, String j1,String j2, ImageIcon imJ1, ImageIcon imJ2){//j1 = black, j2 = white
		
		int var;
		int vari;
		int varj;
		boolean findUp = false;
		boolean findDown = false;
		boolean findLeft = false;
		boolean findRight = false;
		boolean findUpRight = false;
		boolean findUpLeft = false;
		boolean findDownRight = false;
		boolean findDownLeft = false;
		
		boolean testTranfo = false;//to see if they were a transformation
		
		

			/**
			 * verify if the first button up is white
			 */
			var = i+1;
			
			if(var > (button.length - 1)){
				;// do nothing
			}else
			if(this.button[var][j].getName().equals(j2)){
				
				
				while((var < button.length) && !findUp){
					//System.out.println("haut");	
					if(button[var][j].getName().equals(j1) ){//on s'arrete
						;//transformation de tous les case vers le bas j'usqu'au premier black
						var--;
						while( button[var][j].getName().equals(j2) && !findUp){
							
							button[var][j].setIcon(imJ1);
							button[var][j].setName(j1);
							var--;
							if(var == i ){
								
								button[var][j].setIcon(imJ1);
								button[var][j].setName(j1);
								findUp = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[var][j].getName().equals(j2)){
							var++;
						}else{
							findUp = true;
						}
					}
				}
			}
			
			//bas
			
			/**
			 * test if the button up is white
			 */
			var = i-1;
			if(var < 0){
				;
			}
			else
				if(this.button[var][j].getName().equals(j2)){
			
				
				while((var >= 0) && !findDown){

					if(button[var][j].getName().equals(j1) ){//on s'arrete
						
						;//transform every white button downward until the first black button
						var++;
						while( button[var][j].getName().equals(j2) && !findDown){
							
							button[var][j].setIcon(imJ1);
							button[var][j].setName(j1);
							var++;
							if(var == i ){
								
								button[var][j].setIcon(imJ1);
								button[var][j].setName(j1);
								findDown = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[var][j].getName().equals(j2)){
							var--;
						}else{
							findDown = true;
						}
					}
				}
			}
			
			
			//left

			var = j+1;
			if(var >= button.length ){
				;
			}else
			if(this.button[i][var].getName().equals(j2)){
				
				while((var) < button.length && !findLeft){

					if(button[i][var].getName().equals(j1) ){
						var--;
						while( button[i][var].getName().equals(j2) && !findLeft){
							
							button[i][var].setIcon(imJ1);
							button[i][var].setName(j1);
							var--;
							if(var == j ){
								
								button[i][var].setIcon(imJ1);
								button[i][var].setName(j1);
								findLeft = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[i][var].getName().equals(j2)){
							var++;
						}else{
							findLeft = true;
						}
					}
				}
			}
			
			//right

			var = j-1;
			if(var < 0){
				;
			}else
			if(this.button[i][var].getName().equals(j2)){
				
				while((var >= 0) && !findRight){
					//System.out.println("droite");
					if(button[i][var].getName().equals(j1) ){
						var++;
						while( button[i][var].getName().equals(j2) && !findRight){
							
							button[i][var].setIcon(imJ1);
							button[i][var].setName(j1);
							var++;
							if(var == j ){
								
								button[i][var].setIcon(imJ1);
								button[i][var].setName(j1);
								findRight = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[i][var].getName().equals(j2)){
							var--;
						}else{
							findRight = true;
						}
					}
				}
			}
			
			//diag up right
			vari = i + 1;
			varj = j - 1;
			
			if(vari >= button.length  || varj < 0){
				;
			}else
			if(button[vari][varj].getName().equals(j2)){
				
				while((vari < button.length) && (varj >= 0) && (!findUpRight)){
					
					if(button[vari][varj].getName().equals(j1) ){
						vari--;
						varj++;
						while( button[vari][varj].getName().equals(j2) && !findUpRight){
							
							button[vari][varj].setIcon(imJ1);
							button[vari][varj].setName(j1);
							
							vari--;
							varj++;
							
							if((vari == i) && (varj == j)){
								
								button[vari][varj].setIcon(imJ1);
								button[vari][varj].setName(j1);
								findUpRight = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[vari][varj].getName().equals(j2)){
							vari++;
							varj--;
						}else{
							findUpRight = true;
						}
					}
				}
			}
			
			//diag up left
			vari = i + 1;
			varj = j + 1;
			if((vari >= button.length) || (varj >= button.length)){
				;
			}else
			if(this.button[vari][varj].getName().equals(j2)){
				
				while((vari < button.length) && (varj < button.length) && (!findUpLeft)){
					
					if(button[vari][varj].getName().equals(j1) ){//on s'arrete
						vari--;
						varj--;
						while( button[vari][varj].getName().equals(j2) && !findUpLeft){
							
							button[vari][varj].setIcon(imJ1);
							button[vari][varj].setName(j1);
							
							vari--;
							varj--;
							
							if((vari == i) && (varj == j)){
								
								button[vari][varj].setIcon(imJ1);
								button[vari][varj].setName(j1);
								findUpLeft = true;
								testTranfo = true;
							}
						}
						
					}else{
						if(this.button[vari][varj].getName().equals(j2)){
							vari++;
							varj++;
						}else{
							findUpLeft = true;
						}
					}
				}
			}
			
			//diag down right
			vari = i - 1;
			varj = j - 1;
			
			if(vari < 0 || varj < 0){
				;
			}else
			if(this.button[vari][varj].getName().equals(j2)){
				
				while((vari >= 0) && (varj >= 0) && (!findDownRight)){
					
					if(button[vari][varj].getName().equals(j1) ){
						vari++;
						varj++;
						while( button[vari][varj].getName().equals(j2) && (!findDownRight)){
							button[vari][varj].setIcon(imJ1);
							button[vari][varj].setName(j1);
							vari++;
							varj++;
							if((vari == i) && (varj == j)){		
								button[vari][varj].setIcon(imJ1);
								button[vari][varj].setName(j1);
								findDownRight = true;
								testTranfo = true;
							}
						}
					}else{
						if(this.button[vari][varj].getName().equals(j2)){
							vari--;
							varj--;
						}else{
							findDownRight = true;
						}
					}
				}
			}
			
			//diag down left
			vari = i - 1;
			varj = j + 1;
			if(vari < 0 || varj >= button.length){
				;
			}else
			if(this.button[vari][varj].getName().equals(j2)){
				
				while((vari >= 0) && (varj < button.length) && (!findDownLeft)){
					
					if(button[vari][varj].getName().equals(j1) ){//on s'arrete
						vari++;
						varj--;
						while( button[vari][varj].getName().equals(j2) && !findDownLeft){
							button[vari][varj].setIcon(imJ1);
							button[vari][varj].setName(j1);
							vari++;
							varj--;
							if((vari == i) && (varj == j)){		
								button[vari][varj].setIcon(imJ1);
								button[vari][varj].setName(j1);
								findDownLeft = true;
								testTranfo = true;
							}
						}
					}else{
						if(this.button[vari][varj].getName().equals(j2)){
							vari--;
							varj++;
						}else{
							findDownLeft = true;
						}
					}
				}
			}
			
		
		return testTranfo;
	}
	
}


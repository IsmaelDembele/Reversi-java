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


public class Reversi extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
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
		pscore.setSize(100,300);
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
					
				//	String a = new String();
					
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
		
		
		//placement du panel score
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 11;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		c.add(pscore,gbc);
		
		//placement du panelButtons
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 8;
		gbc.gridheight = 8;
		//gbc.fill = GridBagConstraints.BOTH;
		
		c.add(panelButtons,gbc);
		
		//placement du panel information
		gbc.gridx = 8;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 8;
		gbc.fill = GridBagConstraints.BOTH;
		
		c.add(information,gbc);
		
		this.setVisible(true);
		this.pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		
		JButton b =  (JButton) e.getSource();
		int x = 0, y = 0;
		
		if(b.getName().equals("")){
			System.out.println("le button n'a pas de couleur");
		}else{
		
			if((b.getIcon()).equals(black_image)){
				System.out.println("le button est noir");
			}else if((b.getIcon()).equals(white_image)){
				System.out.println("le button est blanc");
			}
		}
		
		//recherche de la position du bonton cliqué dans la grille
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(b.equals(button[i][j])){
					System.out.println("i = "+i+" et j = "+j);
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
	 * écrit le nom du joeur qui doit jouer
	 * 
	 */
	public void ActualiseNonjoueur(int turn){
		
		JLabel jl = (JLabel) information.getComponent(3);
		
		if(turn == 0){
			jl.setText("   Black  ");
			
		}else if(turn == 1) {
			jl.setText("   White  ");
		}else if( turn == 2){//fin du jeu
		
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
				if(!(b.getName().equals(""))){
					//fin = 1;// a effacer
				}else{
					
					testTranfo = transformation( i, j, "black","white",  black_image, white_image);
					
						
				}
				
				if(jeuPossible( 1) == true){// check to see if the opponent have a possible move 
					System.out.println("jeu possible blanc");
					
					if(testTranfo == true){
						turn = 1;
						ActualiseNonjoueur(turn);
					}
				}
				
				if(this.scoreint > 62){//vers la fin du jeu pour ne pas faire des calcule pour rien
					if((jeuPossible( 1) == false) && (jeuPossible(2)) == false){
						ActualiseNonjoueur(2);
					}	
				}
				
			}else{//turn = 1 pour le blanc
				
				
				if(!(b.getName().equals(""))){
					;
				}else{
					
					testTranfo = transformation( i, j, "white","black",  white_image, black_image);
						
				}

				if(jeuPossible( 0) == true){//on regarde si l'adversaire peut joueur avant de lui donner la main
					//System.out.println("jeu possible black");
				
					if(testTranfo == true){
						turn = 0;
						ActualiseNonjoueur(turn);
					}
				}
			}
			
			if(this.scoreint > 62){//vers la fin du jeu pour ne pas faire des calcule pour rien
				if((jeuPossible( 1) == false) && (jeuPossible(2)) == false){
					ActualiseNonjoueur(2);
				}	
			}	
		}
		
	}
	
	/**
	 * Cette fonction est executé avant de donner la main a un joueur
	 * 
	 * Elle permet de savoir si le joueur à reellement la possibilité de 
	 * joueur dans la configuration actuelle du jeu.
	 * 
	 * si ce n'est pas le cas la main passe à son adversaire.
	 * 
	 * 
	 */
	public boolean jeuPossible(int turn){
		
		String couleur;
		String adversaire;
		
		if(turn == 0){
			couleur = "black";
			adversaire = "white";
		}else{
			couleur = "white";
			adversaire = "black";
		}

		int vari, varj;
		
		System.out.println("couleur = "+couleur);
		
		System.out.println("adversaire = "+adversaire);
		
		for(int i = 0; i < button.length; i++){
			for(int j= 0; j < button.length; j++){
				
				if(button[i][j].getName().equals(couleur)){
				
					
					
					//haut
					System.out.println("jeu possible haut");
					vari = i-1;
					if(vari > 0){
						while((vari > 0) && button[vari][j].getName().equals(adversaire)){
							vari--;
							
							if(vari == 0 && !button[vari][j].getName().equals("")){
								vari = -1;//on quite la boucle
							}else
							if(button[vari][j].getName().equals("")){
								return true;
							}
						}
					}
					
					//bas

					System.out.println("jeu possible bas");
					
					vari = i+1;
					if(vari < (button.length - 1) ){
						
						while((vari < button.length ) && button[vari][j].getName().equals(adversaire)){
							
							vari++;
							
							if((vari == (button.length - 1)) && !button[vari][j].getName().equals("")){
								vari = button.length;//on quite la boucle
							}else
								if(button[vari][j].getName().equals("")){
								return true;
							}
						}
					}
					
					//gauche

					System.out.println("jeu possible gauche");
					
					varj = j-1;
					if(varj > 0){
						while((varj > 0) && button[i][varj].getName().equals(adversaire)){
							varj--;
							
							if(varj == 0 && !button[i][varj].getName().equals("")){
								varj = -1;
							}else
								if(button[i][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//droite

					System.out.println("jeu possible droite");
					
					varj = j+1;
					if(varj < (button.length - 1)){
						while((varj < button.length) && button[i][varj].getName().equals(adversaire)){
							varj++;
							
							if((varj == (button.length - 1)) && !button[i][varj].getName().equals("")){
								varj = button.length;
							}else
								if(button[i][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//diag haut droite

					System.out.println("jeu possible haut droite");
					
					vari = i-1;
					varj = j+1;
					if( (vari > 0) &&  (varj < (button.length - 1))){
						while(( (vari > 0) &&  varj < button.length) && button[vari][varj].getName().equals(adversaire)){
							vari--;
							varj++;
							
							if( ((vari == 0) ||  (varj == (button.length - 1)) ) && !button[vari][varj].getName().equals("")){
								vari = -1;
								//varj = button.length;
							}else
								if(button[vari][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//diag haut gauche

					System.out.println("jeu possible haut gauche");
					
					vari = i-1;
					varj = j-1;
					if( (vari > 0) &&  (varj > 0)){
						while( (vari > 0) &&  (varj > 0) && button[vari][varj].getName().equals(adversaire)){
							vari--;
							varj--;
							
							if( ((vari == 0) ||  (varj == 0) ) && !button[vari][varj].getName().equals("")){
								vari = -1;
								//varj = -1;
							}else
								if(button[vari][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//diag bas droite

					System.out.println("jeu possible bas droite");
					
					vari = i+1;
					varj = j+1;
					if( (vari < (button.length-1)) &&  (varj < (button.length-1))){
						while( (vari < button.length) &&  (varj < button.length) && button[vari][varj].getName().equals(adversaire)){
							vari++;
							varj++;
							
							if( ((vari == (button.length-1)) ||  (varj == (button.length-1)) ) && !button[vari][varj].getName().equals("")){
								vari = button.length;
								//varj = -1;
							}else
								if(button[vari][varj].getName().equals("")){
								return true;
							}
						}
					}
					
					//diag bas gauche

					System.out.println("jeu possible bas gauche");
					
					vari = i+1;
					varj = j-1;
					if( (vari < (button.length-1)) &&  (varj > 0)){
						while( (vari < button.length) &&  (varj > 0) && button[vari][varj].getName().equals(adversaire)){
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
	
	public boolean transformation(int i, int j, String j1,String j2, ImageIcon imJ1, ImageIcon imJ2){//j1 = black, j2 = white
		
		int var;
		int vari;
		int varj;
		boolean trouveh = false;
		boolean trouveb = false;
		boolean trouveg = false;
		boolean trouved = false;
		boolean trouvehd = false;
		boolean trouvehg = false;
		boolean trouvebd = false;
		boolean trouvebg = false;
		
		boolean testTranfo = false;//pour voir si il y a eu une transformation
		
		if(turn == 0 || turn == 1){//noir ou blanc. Ce if est à supprimer
		
			//haut
			/**
			 * je verifie si la premiere case en haut est white
			 */
			var = i+1;
			
			if(var > (button.length - 1)){
				;
			}else
			if(this.button[var][j].getName().equals(j2)){
				
				
				while((var < button.length) && !trouveh){
					//System.out.println("haut");	
					if(button[var][j].getName().equals(j1) ){//on s'arrete
						;//transformation de tous les case vers le bas j'usqu'au premier black
						var--;
						while( button[var][j].getName().equals(j2) && !trouveh){
							
							button[var][j].setIcon(imJ1);
							button[var][j].setName(j1);
							var--;
							if(var == i ){
								
								button[var][j].setIcon(imJ1);
								button[var][j].setName(j1);
								trouveh = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[var][j].getName().equals(j2)){
							var++;
						}else{
							trouveh = true;
						}
					}
				}
			}
			
			//bas
			
			/**
			 * je verifie si la premiere case en haut est white
			 */
			var = i-1;
			if(var < 0){
				;
			}
			else
				if(this.button[var][j].getName().equals(j2)){
			
				
				while((var >= 0) && !trouveb){
					//System.out.println("bas");
					if(button[var][j].getName().equals(j1) ){//on s'arrete
						
						;//transformation de tous les case vers le bas j'usqu'au premier black
						var++;
						while( button[var][j].getName().equals(j2) && !trouveb){
							
							button[var][j].setIcon(imJ1);
							button[var][j].setName(j1);
							var++;
							if(var == i ){
								
								button[var][j].setIcon(imJ1);
								button[var][j].setName(j1);
								trouveb = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[var][j].getName().equals(j2)){
							var--;
						}else{
							trouveb = true;
						}
					}
				}
			}
			
			
			//gauche

			var = j+1;
			if(var >= button.length ){
				;
			}else
			if(this.button[i][var].getName().equals(j2)){
				
				while((var) < button.length && !trouveg){
					//System.out.println("gauche");
					if(button[i][var].getName().equals(j1) ){//on s'arrete
						
						;//transformation de tous les case vers le bas j'usqu'au premier black
						var--;
						while( button[i][var].getName().equals(j2) && !trouveg){
							
							button[i][var].setIcon(imJ1);
							button[i][var].setName(j1);
							var--;
							if(var == j ){
								
								button[i][var].setIcon(imJ1);
								button[i][var].setName(j1);
								trouveg = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[i][var].getName().equals(j2)){
							var++;
						}else{
							trouveg = true;
						}
					}
				}
			}
			
			//droite

			var = j-1;
			if(var < 0){
				;
			}else
			if(this.button[i][var].getName().equals(j2)){
				
				while((var >= 0) && !trouved){
					//System.out.println("droite");
					if(button[i][var].getName().equals(j1) ){//on s'arrete
						;//transformation de tous les case vers le bas j'usqu'au premier black
						var++;
						while( button[i][var].getName().equals(j2) && !trouved){
							
							button[i][var].setIcon(imJ1);
							button[i][var].setName(j1);
							var++;
							if(var == j ){
								
								button[i][var].setIcon(imJ1);
								button[i][var].setName(j1);
								trouved = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[i][var].getName().equals(j2)){
							var--;
						}else{
							trouved = true;
						}
					}
				}
			}
			
			//diag haut droite
			vari = i + 1;
			varj = j - 1;
			
			if(vari >= button.length  || varj < 0){
				;
			}else
			if(button[vari][varj].getName().equals(j2)){
				
				while((vari < button.length) && (varj >= 0) && (!trouvehd)){
					
					//System.out.println("diag haut droite");
					
					if(button[vari][varj].getName().equals(j1) ){//on s'arrete
						//transformation de tous les case vers le bas j'usqu'au premier black
						vari--;
						varj++;
						while( button[vari][varj].getName().equals(j2) && !trouvehd){
							
							button[vari][varj].setIcon(imJ1);
							button[vari][varj].setName(j1);
							
							vari--;
							varj++;
							
							if((vari == i) && (varj == j)){
								
								button[vari][varj].setIcon(imJ1);
								button[vari][varj].setName(j1);
								trouvehd = true;
								testTranfo = true;
							}
						}
						
						
					}else{
						if(this.button[vari][varj].getName().equals(j2)){
							vari++;
							varj--;
						}else{
							trouvehd = true;
						}
					}
				}
			}
			
			//diag haut gauche
			vari = i + 1;
			varj = j + 1;
			if((vari >= button.length) || (varj >= button.length)){
				;
			}else
			if(this.button[vari][varj].getName().equals(j2)){
				
				while((vari < button.length) && (varj < button.length) && (!trouvehg)){
					
					//System.out.println("diag haut gauche");
					
					if(button[vari][varj].getName().equals(j1) ){//on s'arrete
						//transformation de tous les case vers le bas j'usqu'au premier black
						vari--;
						varj--;
						while( button[vari][varj].getName().equals(j2) && !trouvehg){
							
							button[vari][varj].setIcon(imJ1);
							button[vari][varj].setName(j1);
							
							vari--;
							varj--;
							
							if((vari == i) && (varj == j)){
								
								button[vari][varj].setIcon(imJ1);
								button[vari][varj].setName(j1);
								trouvehg = true;
								testTranfo = true;
							}
						}
						
					}else{
						if(this.button[vari][varj].getName().equals(j2)){
							vari++;
							varj++;
						}else{
							trouvehg = true;
						}
					}
				}
			}
			
			//diag bas droite
			vari = i - 1;
			varj = j - 1;
			
			if(vari < 0 || varj < 0){
				;
			}else
			if(this.button[vari][varj].getName().equals(j2)){
				
				while((vari >= 0) && (varj >= 0) && (!trouvebd)){
					
					//System.out.println("duag bas droite");
					
					if(button[vari][varj].getName().equals(j1) ){//on s'arrete
						//transformation de tous les case vers le bas j'usqu'au premier black
						vari++;
						varj++;
						while( button[vari][varj].getName().equals(j2) && (!trouvebd)){
							button[vari][varj].setIcon(imJ1);
							button[vari][varj].setName(j1);
							vari++;
							varj++;
							if((vari == i) && (varj == j)){		
								button[vari][varj].setIcon(imJ1);
								button[vari][varj].setName(j1);
								trouvebd = true;
								testTranfo = true;
							}
						}
					}else{
						if(this.button[vari][varj].getName().equals(j2)){
							vari--;
							varj--;
						}else{
							trouvebd = true;
						}
					}
				}
			}
			
			//diag bas gauche
			vari = i - 1;
			varj = j + 1;
			if(vari < 0 || varj >= button.length){
				;
			}else
			if(this.button[vari][varj].getName().equals(j2)){
				
				while((vari >= 0) && (varj < button.length) && (!trouvebg)){
					
					//System.out.println("diag bas gauche");
					
					if(button[vari][varj].getName().equals(j1) ){//on s'arrete
						//transformation de tous les case vers le bas j'usqu'au premier black
						vari++;
						varj--;
						while( button[vari][varj].getName().equals(j2) && !trouvebg){
							button[vari][varj].setIcon(imJ1);
							button[vari][varj].setName(j1);
							vari++;
							varj--;
							if((vari == i) && (varj == j)){		
								button[vari][varj].setIcon(imJ1);
								button[vari][varj].setName(j1);
								trouvebg = true;
								testTranfo = true;
							}
						}
					}else{
						if(this.button[vari][varj].getName().equals(j2)){
							vari--;
							varj++;
						}else{
							trouvebg = true;
						}
					}
				}
			}
			
		}
		
		return testTranfo;
	}
	
}


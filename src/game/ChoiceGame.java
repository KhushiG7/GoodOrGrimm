package game;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.text.Position;
import java.net.URL;

public class ChoiceGame extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	
	// changes color of button depending if it is pressed or not
	int first = 244;
	int second = 242;
	int third = 242;
	int first2 = 244;
	int second2 = 242;
	int third2 = 242;
	
	//Shapes
	int slideButtonLeftX = 230;
	int slideButtonLeftY = 550;
	int slideButtonLeftXS = 300;
	int slideButtonLeftYS = 250;
	int slideButtonRightX = 730;
	int slideButtonRightY = 550;
	int slideButtonRightXS = 300;
	int slideButtonRightYS = 250;
	int buttonLeftSpeed = 0;
	int buttonRightSpeed = 0;
	
	//Fade Black
	boolean fadeBlack = false;
	int fdBlk = 0;
	int fdBlkV = 3;
	
	//transition to next image
	private boolean hoverLeft = false;
	private boolean hoverRight = false;
	
	//progress bar
	private JProgressBar progressBar;
	private JProgressBar progressBar2;
	private int progressValue = 0;
	int currentLevel = 1;
	int totalLevels = 7;
	
	//Sounds
	//Please change the directory to the correct directory on your computer
	//If not then the only errors in the code below is from the sound not working from the correct directory but the rest of the code works
	//static File eerie_forest = new File("C:\\Users\\graha\\eclipse-workspace\\QHack2025\\Sounds\\eerie_forest.wav");
	static File eerie_forest = new File("Sounds/eerie_forest.wav");
	static File SongOfStorms = new File("Sounds/Song of Storms.wav");
	static File celestialMelody = new File("Sounds/celestial_melody.wav");	
	static File bounceEffect = new File("Sounds/bounce sound effect.wav");	
	static File goofy_drumbeat = new File("Sounds/goofy-drumloop.wav");
	static File caveNoises = new File("Sounds/caveNoises.wav");
	static File battleHorns = new File("Sounds/battleHorns.wav");
	static File softSespense = new File("Sounds/softSespense.wav");
	static File evilMusic = new File("Sounds/evilMusic.wav");
	static File temp;
	static Clip clip;
	
	//image boolean
	boolean imageShown = false;
	boolean startScreen = true;
	boolean slideScreen = false;
	boolean caveOfTruth = false;
	boolean buttonMoving = false;
	boolean option1 = false;
	boolean option2 = false;
	boolean slidePostScreen = false;
	boolean end = false;
	boolean bonus = false;
	int storeOption = 0;
	
	//Points
	int points = 0;
	int pointsBar = 0;
	
	public ChoiceGame() {
		Timer timer = new Timer(3, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseListener(this);
		addMouseMotionListener(this);
		//points progress bar
		progressBar = new JProgressBar(0, 14);
		progressBar.setBackground(new Color(0, 100, 0));
		progressBar.setForeground(new Color(144, 238, 144));
	    progressBar.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 3)); // Dark green border
	    progressBar.setValue(progressValue);
	    progressBar.setStringPainted(true);;
	    
	    this.setLayout(null);
	    
	    progressBar.setBounds(856, 10, 400, 30); // x, y, width, height
	    progressBar.setVisible(false); //invisible on start page
	    this.add(progressBar);
	    
	    //level progress bar
	    progressBar2 = new JProgressBar(0, totalLevels);
		progressBar2.setBackground(new Color(255, 255, 255));
		progressBar2.setForeground(new Color(0, 0, 255));
	    progressBar2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 100), 3)); // Dark green border
	    progressBar2.setValue(currentLevel);
	    progressBar2.setMaximum(totalLevels);
	    progressBar2.setStringPainted(true);
	    
	    this.setLayout(null);
	    
	    progressBar2.setBounds(10, 10, 400, 30); // x, y, width, height
	    progressBar2.setVisible(false); //invisible on start page
	    this.add(progressBar2);
	}
	
	public void updateProgressBar(int points) {
	    progressValue += points;
	    if (progressValue > 100) {
	        progressValue = 100; // Cap the progress at 100
	        JOptionPane.showMessageDialog(null, "You've reached maximum progress!");
	    }
	    progressBar.setValue(progressValue);
	}
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		//System.setProperty("sun.java2d.uiScale", "1.0"); 
		ChoiceGame panel = new ChoiceGame();
		JFrame window = new JFrame();
		window.setTitle("QHacks 2025");
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		panel.requestFocus();
		window.pack();
		window.setSize(1280, 896);
		
    	temp = celestialMelody;
    	sound(temp,false);
	}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(mx >= 600 && mx <= 730 && my >=447 && my<=497 && startScreen) {
			
			first = 137;
			second = 134;
			third = 134;
		}
		if(mx >= 600 && mx <= 730 && my >=547 && my<=597 && end) {
			
			first = 137;
			second = 134;
			third = 134;
		}
		if(mx >= 230 && mx <= 530 && my >=550 && my<=800 && slideScreen) {
			
			first = 137;
			second = 134;
			third = 134;
		}
		if(mx >= 730 && mx <= 1030 && my >=550 && my<=800 && slideScreen) {
			
			first2 = 137;
			second2 = 134;
			third2 = 134;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (slidePostScreen) {
			slidePostScreen = false;
			slideScreen = true;
			if (Slide == Friends3) {
				storeOption = 0;
				Slide = greed;
				temp = goofy_drumbeat;
				try {
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				
			}
			else if (Slide == greed) {
				storeOption = 0;
				Slide = CaveOfTruth;
				temp = caveNoises;
				try {
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			else if (Slide == CaveOfTruth) {
				storeOption = 0;
				Slide = dragon;
				temp = battleHorns;
				try {
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			else if (Slide == dragon) {
				storeOption = 0;
				Slide = troll;
				temp = softSespense;
				try {
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			else if (Slide == troll) {
				storeOption = 0;
				Slide = CliffForest;
				temp = SongOfStorms;
				try {
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			else if (Slide == CliffForest) {
				storeOption = 0;
				Slide = evilSlide;
				temp = evilMusic;
				try {
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			else if (Slide == evilSlide && points < 10) {
				storeOption = 0;
				end = true;
				slideScreen = false;
				if (points<0) Slide = darkCastle;
				else Slide = goodCastle;
				temp = goofy_drumbeat;
				try {
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}	else if (Slide == evilSlide && points >= 10) {
				storeOption = 0;
				Slide = Slide1;
				temp = battleHorns;
				try {
					
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}	else if (Slide == Slide1) {
				storeOption = 0;
				end = true;
				slideScreen = false;
				if (points<0) Slide = darkCastle;
				else Slide = goodCastle;
				temp = goofy_drumbeat;
				try {
					sound(temp,true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if(mx >= 600 && mx <= 730 && my >=447 && my<=497 && startScreen) {
			System.out.println("mouse released yo");
			first = 244;
			second = 242;
			third = 242;
			fadeBlack = true;
			Slide = Friends3;
			temp = eerie_forest;
			try {
				sound(temp,true);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		else if(mx >= 600 && mx <= 730 && my >=547 && my<=597 && end) {
			System.out.println("mouse released yo");
			first = 244;
			second = 242;
			third = 242;
			fadeBlack = true;
			temp = celestialMelody;
			try {
				sound(temp,true);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		else if(mx >= slideButtonLeftX && mx <= slideButtonLeftX + slideButtonLeftXS && my >= slideButtonLeftY && my<= slideButtonLeftY + slideButtonLeftYS && slideScreen) {
			
			first = 244;
			second = 242;
			third = 242;
			option1 = true;
			hoverLeft = false;
		}
		else if(mx >= slideButtonRightX && mx <= slideButtonRightX + slideButtonRightXS && my >= slideButtonRightY && my<= slideButtonRightY + slideButtonRightYS && slideScreen) {
			
			first2 = 244;
			second2 = 242;
			third2 = 242;
			option2 = true;
			hoverRight = false;
		}
	}
	
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(mx >= slideButtonLeftX && mx <= slideButtonLeftX + slideButtonLeftXS && my >= slideButtonLeftY && my<= slideButtonLeftY + slideButtonLeftYS && slideScreen) {
			first = 137;
			second = 134;
			third = 134;

			hoverLeft = true;
		}
		else if (slideScreen){
			first = 244;
			second = 242;
			third = 242;

			hoverLeft = false;
		}
		if(mx >= slideButtonRightX && mx <= slideButtonRightX + slideButtonRightXS && my >= slideButtonRightY && my<= slideButtonRightY + slideButtonRightYS && slideScreen) {
			first2 = 137;
			second2 = 134;
			third2 = 134;
			hoverRight = true;
		}
		else if (slideScreen){
			first2 = 244;
			second2 = 242;
			third2 = 242;
			hoverRight = false;
		}
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void actionPerformed(ActionEvent e) {
		//Fade Black
		if (fadeBlack) {
			fdBlk = fdBlk + fdBlkV;
			if (fdBlk == 255) {
				fdBlkV = -fdBlkV;
				if (startScreen) {
					imageShown = true;
					slideScreen = true;
					startScreen = false;
				}
				else if (end){
					imageShown = false;
					slideScreen = false;
					startScreen = true;
					end = false;
					points = 0;
					currentLevel = 0;
				}
			}
			if (fdBlk == 0) {
				fdBlkV = -fdBlkV;
				fadeBlack = false;
			}
		}
		
		if (buttonMoving) {
			slideButtonLeftY =- buttonLeftSpeed;
			slideButtonLeftXS =+ buttonLeftSpeed;
			slideButtonLeftYS =+ buttonLeftSpeed;
			if (slideButtonLeftY == 300 || slideButtonLeftY == 550) buttonMoving = false;
		}
		
		repaint();
	}
	
	//images
	ImageIcon background = new ImageIcon(getClass().getResource("/game/images/fantacy background.gif"));
	ImageIcon Slide1 = new ImageIcon(getClass().getResource("/game/images/Slide_1.gif"));
	ImageIcon CaveOfTruth = new ImageIcon(getClass().getResource("/game/images/CaveOfTruth.png"));
	ImageIcon truth = new ImageIcon(getClass().getResource("/game/images/truth.png"));
	ImageIcon lie = new ImageIcon(getClass().getResource("/game/images/lie.png"));
	ImageIcon Friends3 = new ImageIcon(getClass().getResource("/game/images/3Friends.jpg"));
	ImageIcon DarkPath = new ImageIcon(getClass().getResource("/game/images/darkPath.jpg"));
	ImageIcon LightPath = new ImageIcon(getClass().getResource("/game/images/lightPath.jpg"));
	ImageIcon greed = new ImageIcon(getClass().getResource("/game/images/greed.png"));
	ImageIcon save = new ImageIcon(getClass().getResource("/game/images/save.png"));
	ImageIcon evilSteal = new ImageIcon(getClass().getResource("/game/images/evil steal.png"));
	ImageIcon evilSlide = new ImageIcon(getClass().getResource("/game/images/EvilSlide.png"));
	ImageIcon evilHandShake = new ImageIcon(getClass().getResource("/game/images/evilHandShake.jpg"));
	ImageIcon Fistbump = new ImageIcon(getClass().getResource("/game/images/fistbump.png"));	
	ImageIcon CliffForest = new ImageIcon(getClass().getResource("/game/images/CliffForest.jpg"));
	ImageIcon glowMushroom = new ImageIcon(getClass().getResource("/game/images/glowShroom.png"));
	ImageIcon wizardSwirl = new ImageIcon(getClass().getResource("/game/images/cartoonWizard.png"));
	ImageIcon goodCastle = new ImageIcon(getClass().getResource("/game/images/goodCastle.jpg"));
	ImageIcon darkCastle = new ImageIcon(getClass().getResource("/game/images/darkCastle.png"));
	ImageIcon dragon = new ImageIcon(getClass().getResource("/game/images/dragon.png"));
	ImageIcon fight = new ImageIcon(getClass().getResource("/game/images/fight.jpg"));
	ImageIcon running = new ImageIcon(getClass().getResource("/game/images/running.jpg"));
	ImageIcon troll = new ImageIcon(getClass().getResource("/game/images/troll.jpg"));
	ImageIcon blind = new ImageIcon(getClass().getResource("/game/images/blind.png"));
	ImageIcon married = new ImageIcon(getClass().getResource("/game/images/married.png"));
	ImageIcon potion = new ImageIcon(getClass().getResource("/game/images/potion.jpg"));
	ImageIcon reject = new ImageIcon(getClass().getResource("/game/images/reject.png"));
	ImageIcon Slide = Friends3;
	
	//Music & sounds
	public static void sound(File temp, boolean change) throws UnsupportedAudioFileException, IOException, LineUnavailableException {		    
			if (change) {
		        clip.stop();
		    }
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(temp);
	        clip = AudioSystem.getClip();
	        clip.open(audioStream);
	        
	        clip.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (startScreen) {
		background.paintIcon(this, g, 0, 0);
		
		// buttons
		g.setColor(new Color(first, second, third));

		g.fillRoundRect(600,448,130,50, 20, 20);
		
		//Title
		g.setColor(new Color(180, 130, 150));

		g.fillRoundRect(375,100,600,100, 20, 20);
		g.setColor(new Color (13,13,12));
		g.setFont((new Font("Comic Sans", Font.BOLD | Font.ITALIC,44)));
		g.drawString("Good or Grimm", 525, 170);
		
		// start word
		g.setColor(new Color (13,13,12));
		g.setFont((new Font("Comic Sans", Font.BOLD,44)));
		g.drawString("Start", 615, 488);
		
		g.setColor(new Color(2, 2, 2,150));
		g.drawRect(80, 550, 1110, 250);
		g.fillRect(80, 550, 1110, 250);
		g.setColor(new Color (255,255,255));
		g.setFont((new Font("Comic Sans", Font.BOLD,33)));
		g.drawString("You and your friends find yourselves at the", 120, 600);
		g.drawString("edge of a mysterious forest, said to be filled with magic", 120, 650);
		g.drawString("and danger. A weathered sign reads: \"Those who enter must", 120, 700);
		g.drawString("choose wisely,for the path that shapes the wizard within.\"", 120, 750);
		}
		
		if (imageShown) {
			Slide.paintIcon(this,  g,  0,  0);
			g.setColor(new Color(255, 255, 255));
			g.setFont((new Font("Comic Sans", Font.BOLD, 28)));
			if (!end) g.drawString("Points: " + points, 1100, 65);
			else {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(490, 360, 220, 50);
				g.fillRect(490, 360, 220, 50);
				g.setColor(new Color(255, 255, 255));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Points: " + points, 500, 400);
				g.setColor(new Color(first, second, third));
				g.fillRoundRect(600,548,130,50, 20, 20);
				g.setColor(new Color (13,13,12));
				g.setFont((new Font("Comic Sans", Font.BOLD,44)));
				g.drawString("Again", 605, 588);
			}
		}
		
		if(Slide == Friends3) {
			if(storeOption == 1) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1110, 300);
				g.fillRect(100, 300, 1110, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You lost points: You let looks decive you, as you", 100, 400);
				g.drawString("reached the end of the path you fell into a pit,", 100, 450);
				g.drawString("and hurt yourself.", 100, 500);
			
			}else if(storeOption == 2) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1110, 300);
				g.fillRect(100, 300, 1110, 300);
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Congratulations, you gained points: you thought ", 100, 400);
				g.drawString("this path was going to be dangerous, but don't", 100, 450);
				g.drawString("judge a book by it's cover. You made it out", 100, 500);
				g.drawString("safely.", 100, 550);
			}
		}else if(Slide == greed) {
			
			if(storeOption == 1) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1150, 300);
				g.fillRect(100, 300, 1150, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You lost points: You let greed overcome you.", 100, 350);
				g.drawString("As you are searching through the backpack,", 100, 400);
				g.drawString("you find a teleporting potion and try to use it", 100, 450);
				g.drawString("to get away. Unfortunately it backfires, ", 100, 500);
				g.drawString("and you land in a tree above your friends.", 100, 550);
			}else if(storeOption == 2) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1150, 300);
				g.fillRect(100, 300, 1150, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Congratulations, you gained points: you and your  ", 100, 350);
				g.drawString("friends were able to help the wizard out of the ", 100, 400);
				g.drawString("tree, as a reward for your kindness he wanted ", 100, 450);
				g.drawString("to help your trio out on your journey to become ", 100, 500);
				g.drawString("wizards and teleported you to a path.", 100, 550);
			}
		}else if(Slide == CaveOfTruth) {
			
			if(storeOption == 2) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1150, 300);
				g.fillRect(100, 300, 1150, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You lost points: You were not truthfull.", 100, 350);
				g.drawString("The ground beneath you collapsed and", 100, 400);
				g.drawString("you are forced to find a different way", 100, 450);
				g.drawString("out of the cave ", 100, 500);
									
			}else if(storeOption == 1) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1150, 300);
				g.fillRect(100, 300, 1150, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Congratulations, you gained points: you were ", 100, 350);
				g.drawString("truthfull and the Hippogriff taught you ", 100, 400);
				g.drawString("a basic sheild spell", 100, 450);
				
				
				
			}
		}else if (Slide == dragon) {
			if(storeOption == 2) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1150, 300);
				g.fillRect(100, 300, 1150, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You lost points: You did not show", 100, 350);
				g.drawString("bravery and abandoned your friends.", 100, 400);
				g.drawString("As you were running away you get", 100, 450);
				g.drawString("hit with a nauseating potion", 100, 500);
			}else if(storeOption == 1) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1150, 300);
				g.fillRect(100, 300, 1150, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Congratulations, you gained points. ", 100, 350);
				g.drawString("You proved you are valiant and, as a ", 100, 400);
				g.drawString("reward you were taught a block spell", 100, 450);
			}
		}else if (Slide == troll) {
			
			if(storeOption == 1) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1150, 300);
				g.fillRect(100, 300, 1150, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Congratulations, you answered the riddle  ", 100, 350);
				g.drawString("correctly. As a result, the troll let you ", 100, 400);
				g.drawString("pass the bridge unharmed", 100, 450);
				
			}if(storeOption == 2) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1150, 300);
				g.fillRect(100, 300, 1150, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You lost points: You did not show", 100, 350);
				g.drawString("intelligence in the challenge. As", 100, 400);
				g.drawString("compensation, the troll takes many of", 100, 450);
				g.drawString("your resources", 100, 500);
				
			}
			
		}
		else if (Slide == CliffForest) {
			if(storeOption == 1) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 950, 300);
				g.fillRect(100, 300, 950, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Congratulations, you gained points. ", 100, 350);
				g.drawString("You proved you are resourceful and ", 100, 400);
				g.drawString("a quick thinker.", 100, 450);
			}else if (storeOption == 2) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 950, 300);
				g.fillRect(100, 300, 950, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You lost points: You lost the rest of", 100, 350);
				g.drawString("your supplies as you messed up the", 100, 400);
				g.drawString("spell.", 100, 450);
				
			}
		}else if(Slide == evilSlide) {
			if(storeOption == 1) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1000, 300);
				g.fillRect(100, 300, 1000, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Congratulations, you gained points.", 100, 350);
				g.drawString("You and your friends defeated,", 100, 400);
				g.drawString("the evil wizard and made it out of the forest  ", 100, 450);
				
			}else if(storeOption == 2) {
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1000, 300);
				g.fillRect(100, 300, 1000, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You lost points: After joining the wizard,", 100, 350);
				g.drawString("you feel the regret of loosing your friends", 100, 400);
			
			}
		}else if(Slide == Slide1) {
			if(storeOption == 1) {
				
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1110, 300);
				g.fillRect(100, 300, 1110, 300);
				
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You lost points: the potion makes you forget", 100, 400);
				g.drawString("about all the magic spells and you can't remember", 100, 450);
				g.drawString("your loved ones", 100, 500);
				
			}else if(storeOption == 2) {
				
				g.setColor(new Color(2, 2, 2,150));
				g.drawRect(100, 300, 1110, 300);
				g.fillRect(100, 300, 1110, 300);
				g.setColor(new Color(242, 246, 245));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("Congratulations, you gained points: you learn ", 100, 400);
				g.drawString("from your mistakes and experiences and gain", 100, 450);
				g.drawString("valuable life lessons and continue the path of", 100, 500);
				g.drawString("being good.", 100, 550);
			
			}
		}
		
		
		if (slideScreen) {
			//Screen Description
			progressBar.setVisible(true);
			progressBar2.setVisible(true);
			g.setColor(new Color(255, 255, 255));
			g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
			if(Slide == Friends3) {
		    	g.setColor(new Color(2, 2, 2,150));
				g.drawRect(50, 60, 850, 250);
				g.fillRect(50, 60, 850, 250);
		    	g.setColor(new Color(255, 255, 255));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You and your friends begins your ", 50, 100);
			    g.drawString("journey, but as you enter the forest", 50, 150);
			    g.drawString("the path splits, where do you go?", 50, 200);
		    }
			if(Slide == greed) {
		    	g.setColor(new Color(255, 255, 255));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("An injured wizard lies before you,", 50, 100);
			    g.drawString("their glowing magical bag within reach", 50, 150);
			    g.drawString("What will you do?", 50, 200);
			}
			else if(Slide == CaveOfTruth) {
		    	g.setColor(new Color(2, 2, 2,150));
				g.drawRect(50, 60, 850, 250);
				g.fillRect(50, 60, 850, 250);
		    	g.setColor(new Color(255, 255, 255));
				g.drawString("You and your friends have found yourselves in", 50, 100);
			    g.drawString("a cave with true wonders, treasures and", 50, 150);
			    g.drawString("powerful objects but in your way stands", 50, 200);
			    g.drawString("a truthfull hippogriff which asks", 50, 250);
			    g.drawString("\"Have you been good on your trip so far?\"", 50, 300);
			}
			else if(Slide == CliffForest) {
		    	g.setColor(new Color(2, 2, 2,150));
				g.drawRect(50, 60, 850, 250);
				g.fillRect(50, 60, 850, 250);
		    	g.setColor(new Color(255, 255, 255));
				g.drawString("You and your friends now have no", 50, 100);
			    g.drawString("supplies left at the cusp of a forest and", 50, 150);
			    g.drawString("high cliff. What will will you do?", 50, 200);
			}
		    if (Slide == dragon) {
		    	g.setColor(new Color(2, 2, 2,150));
				g.drawRect(50, 60, 1100, 250);
				g.fillRect(50, 60, 1100, 250);
		    	g.setColor(new Color(255, 255, 255));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You progress down the path and suddenly", 50, 100);
			    g.drawString("a massive beast appears, its eyes glowing.", 50, 150);
			    g.drawString("Your friend looks at you, What will you do?", 50, 200);

			    
		    }
		    if (Slide == troll) {
		    	g.setColor(new Color(2, 2, 2,150));
				g.drawRect(50, 60, 1150, 250);
				g.fillRect(50, 60, 1150, 250);
		    	g.setColor(new Color(255, 255, 255));
				g.setFont((new Font("Comic Sans", Font.BOLD, 48)));
				g.drawString("You come across a troll on a bridge and you must", 50, 100);
			    g.drawString("answer the riddle to move ahead, the riddle goes", 50, 150);
				g.setFont((new Font("Comic Sans", Font.BOLD | Font.ITALIC, 48)));
			    g.drawString("You see a boat filled with people. It has not sunk ", 50, 200);
			    g.drawString("but when you look again you don’t see ", 50, 250);
			    g.drawString("a single person on the boat. Why?", 50, 300);
		    
			
		    }
			else if(Slide == evilSlide) {
		    	g.setColor(new Color(2, 2, 2,150));
				g.drawRect(50, 60, 950, 250);
				g.fillRect(50, 60, 950, 250);
		    	g.setColor(new Color(255, 255, 255));
				g.drawString("As you exit a forest of trees a dark wizard appears.", 50, 100);
			    g.drawString("The wizard invites you to leave your friends", 50, 150);
			    g.drawString("and join him in becoming a dark wizard.", 50, 200);
			    g.drawString("What do you do?", 50, 250);
			}
			else if(Slide == Slide1) {
		    	g.setColor(new Color(255, 255, 255));
				g.drawString("Congratulations!", 500, 100);
				g.drawString("You have followed the good path", 400, 150);
				g.drawString("You have unclocked a BONUS LEVEL", 400, 200);
				g.drawString("After your dreadful journey", 580, 350);
				g.drawString("and emotional trauma, Would you", 480, 400);
				g.drawString("take the potion for erasing memory", 450, 450);
			}
			//left
		    int leftBoxWidth = hoverLeft ? 350 : 300;  // Increase size if hovering
		    int leftBoxHeight = hoverLeft ? 270 : 250;  // Increase size if hovering
		    
		    g.setColor(new Color(first, second, third)); // Set color for left box
		    g.fillRoundRect(230, 550, leftBoxWidth, leftBoxHeight, 40, 40); // Draw the left box
		    
		    // Right box - Resize on hover
		    int rightBoxWidth = hoverRight ? 350 : 300;  // Increase size if hovering
		    int rightBoxHeight = hoverRight ? 270 : 250;  // Increase size if hovering
		    
		    g.setColor(new Color(first2, second2, third2)); // Set color for right box
		    g.fillRoundRect(730, 550, rightBoxWidth, rightBoxHeight, 40, 40); // Draw the right box

			//left
			g.setColor(new Color(first, second, third));

			g.fillRoundRect(slideButtonLeftX,slideButtonLeftY,slideButtonLeftXS,slideButtonLeftYS, 40, 40);
			
			if (!hoverLeft) {
				g.setColor(new Color (13,13,12));
				g.setFont((new Font("Comic Sans", Font.PLAIN,44)));
				g.drawString("Choice 1", 290, 685);
			}
			else {
				g.setColor(new Color (13,13,12));
				g.setFont((new Font("Comic Sans", Font.PLAIN,33)));
				if (Slide == CaveOfTruth) {
					g.drawString("Say \"I have been", 237, 590);
					g.drawString("and will try to be good\"", 237, 630);
					if (points<0) lie.paintIcon(this,  g,  237,  630);
					else truth.paintIcon(this,  g,  237,  670);
				}
				else if (Slide == Friends3) {
					g.drawString("Go down the light", 260, 590);
					g.drawString("path that appears", 260, 625);
					g.drawString("to be safe", 260, 660);
					LightPath.paintIcon(this, g, 280, 680);
				}
				else if (Slide == greed) {
				    g.drawString("Steal the magical", 260, 590);
				    g.drawString("backpack and leave", 260, 625);
				    g.drawString("the wizard behind", 260, 660);
				    evilSteal.paintIcon(this, g, 320, 665);
				}
				else if(Slide == dragon) {
					g.drawString("Fight the beast", 260, 590);
				    g.drawString("with your powers", 260, 625);
				    g.drawString("and weapons.", 260, 660);
				    fight.paintIcon(this, g, 295, 665);
				}else if (Slide == troll) {
					g.drawString("You answer: all ", 260, 590);
				    g.drawString("the people were", 260, 625);
				    g.drawString("married", 260, 660);
				    married.paintIcon(this, g, 295, 665);
				}
				else if (Slide == evilSlide) {
				    g.drawString("Join your friends", 260, 590);
				    g.drawString("and fight the wizard", 260, 625);
				    //g.drawString("", 260, 660);
				    Fistbump.paintIcon(this, g, 320, 665);
				}
				else if (Slide == CliffForest) {
				    g.drawString("Scavange for rare", 260, 590);
				    g.drawString("ingredients", 260, 625);
				    //g.drawString("", 260, 660);
				    glowMushroom.paintIcon(this, g, 320, 665);
				}
				else if (Slide == Slide1) {
					g.setColor(new Color(242, 246, 245));
					g.setFont((new Font("Comic Sans", Font.BOLD, 33)));
					g.drawString("Take the potion", 260, 590);
				    g.drawString("but risk forgetting", 260, 625);
				    g.drawString("important stuff", 260, 660);
				    potion.paintIcon(this, g, 295, 665);
				}
			}
			
			//right
			g.setColor(new Color(first2, second2, third2));

			g.fillRoundRect(slideButtonRightX,slideButtonRightY,slideButtonRightXS,slideButtonRightYS, 40, 40);
			
			if (!hoverRight) {
				g.setColor(new Color (13,13,12));
				g.setFont((new Font("Comic Sans", Font.PLAIN,44)));
				g.drawString("Choice 2", 785, 685);
			}
			else {
				g.setColor(new Color (13,13,12));
				g.setFont((new Font("Comic Sans", Font.PLAIN,33)));
				
				if (Slide == CaveOfTruth) {
					g.drawString("Say \"I have actually", 742, 590);
					g.drawString("been a bit bad\"", 742, 630);
				if (points<0) truth.paintIcon(this,  g,  742,  670);
				else lie.paintIcon(this,  g,  742,  630);
				}
				else if (Slide == Friends3) {
					g.drawString("Go down the dark,", 770, 590);
					g.drawString("gloomy path that ", 770, 625);
					g.drawString("looks dangerous", 770, 660);
					DarkPath.paintIcon(this, g, 780, 680);
				}
				else if (Slide == greed) {
					g.drawString("Help the wizard", 770, 590);
				    g.drawString("and gain their", 770, 625);
				    g.drawString("gratitude", 770, 660);
				    save.paintIcon(this, g, 800, 660);
				}
				else if(Slide == dragon) {
					g.setFont((new Font("Comic Sans", Font.PLAIN, 33)));
				    g.drawString("Leave your friends", 740, 590);
				    g.drawString("behind and run away.", 740, 625);
			    	running.paintIcon(this, g, 800, 640);
				}else if (Slide == troll) {
					g.setFont((new Font("Comic Sans", Font.PLAIN, 33)));
				    g.drawString("You answer: You're", 740, 590);
				    g.drawString("blind, so can't see.", 740, 625);
				    blind.paintIcon(this, g, 800, 625);
				}
				else if (Slide == evilSlide) {
				    g.drawString("Join the wizard", 770, 590);
				    g.drawString("and leave your", 770, 625);
				    g.drawString("friends", 770, 660);
				    evilHandShake.paintIcon(this, g, 870, 700);
				}
				else if (Slide == CliffForest) {
				    g.drawString("Use your last", 770, 590);
				    g.drawString("supplies for a", 770, 625);
				    g.drawString("quick spell", 770, 660);
				    //g.drawString("", 260, 660);
				    wizardSwirl.paintIcon(this, g, 800, 660);
				}
				else if (Slide == Slide1) {
					g.setColor(new Color(242, 246, 245));
					g.setFont((new Font("Comic Sans", Font.BOLD, 33)));
				    g.drawString("Reject the potion", 740, 590);
				    g.drawString("and learn from", 740, 625);
				    g.drawString("past experiences", 740, 660);
				    reject.paintIcon(this, g, 800, 625);
				}
			}
		}
		
		if (fadeBlack) {
			g.setColor(new Color(0,0,0,fdBlk));
			g.fillRect(0,0,1280,896);
			
		}
		
		if(option1) {
				slideScreen = false;
				slidePostScreen = true;
				option1 = false;
				storeOption = 1;
				if (Slide == CaveOfTruth) {
					if (points<0) points--;
					else points = points + 2;
				}
				else if (Slide == CliffForest || Slide == evilSlide || Slide == dragon || Slide == troll) {
					points = points + 2;
				} 
				else {
					points--;
				}
				currentLevel++;
		}
		else if(option2) {
				slideScreen = false;
				slidePostScreen = true;
				option2 = false;
				storeOption = 2;
				if (Slide == CaveOfTruth) {
					if (points<0) points = points + 2;
					else points--;
				}
				else if (Slide == Friends3 || Slide == greed || Slide == Slide1) {
					points = points + 2;
				} 
				else {
					points--;
				}
				currentLevel++;
		}
		
		pointsBar = points;
		if (points < 0) pointsBar = 0;           // Prevent negative points if undesired
		if (points > progressBar.getMaximum()) pointsBar = progressBar.getMaximum();
		progressBar.setValue(pointsBar);
		if (points >= 5) { // Good points
		    progressBar.setForeground(new Color(144, 238, 144)); // Dark green
		} else if (points > 3) { // Neutral
		    progressBar.setForeground(new Color(255, 255, 0)); // Light green
		} else { // Negative points
		    progressBar.setForeground(new Color(255, 0, 0)); // Red
		}
		
		//progress bar level
		
		if (currentLevel>totalLevels) {
			currentLevel=totalLevels;
		}
		progressBar2.setValue(currentLevel);
	}
}

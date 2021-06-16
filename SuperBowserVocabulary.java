package jrJava.superBowser;

/*
Aditya Phatak
4/9/18- 5/17/18
SuperBowserVocabulary.java
Description: SuperBowserVocabulary.java is an interactive game, in which the user gets to learn how to find the definition of several 9th grade
Literature Class vocabulary words from several 9th Grade Literature Class Books, such as "To Kill a Mockingbird", "Samurai's Garden", 
"Romeo and Juliet", and etc. 

- The game will contain a tutorial panel, in which the user- an eighth grade student will be taught how to find the defintion of a 
vocabulary word using in context clues from a specific 9th grade literature class book

- There will be an Instructions Panel(currently called the "Instructions panel") that will guide the user on how to play the game- this will be
the fun part of the game. I will probably be done with the instructions panel and game panel in a few weeks. 

- There will be a game panel, in which the user will play the game.
///////-----HOW THE GAME WORKS-----////////

 * The game will have two characters. On the left side, Mario will be standing and on the right side, Bowser will be standing. These will
 * be images. Using the paintComponent() method, I will draw a racket in Mario's hand. Bowser, the enemy will be throwing vocabulary
 * words from either of the 9th grade literature novels and will be aimed at Mario. Mario will have to hit the vocabulary word and if he misses,
 * he loses a life. Mario has a total of three lives. When Mario hits the vocabulary word, a panel will pop up with the corresponding 
 * vocabulary word used in its corresponding novel. If the user gets at least 9 correct out of the 10 vocabulary words aimed at them, they
 * will win the game and will be given a chance to play again.

 * NOTE: There may be some changes later- I may add more things to this game logic later, as I develop the code for my application.

Testing Plan:

When I am done with coding the game, I will go to Kennedy Middle School, Cupertino Middle School, etc. to test my game on their eighth graders.
I will make sure that the eighth grade users know how to use the game and that it is easy for them to follow along with the instructions I
have provided for them. I will make sure that when the press the left, right, up, or down arrow keys, Mario moves and that when the vocabulary 
word thrown by Bowser hits Mario's racket, a panel pops up with the correct vocabulary word in use in a sentence from the book that the vocab
word comes out of. This testing plan is subject to change.. There will be edits made to this later and this is not the final testing plan.
It is only one part of what I will be testing.
 */

////-----Here, we will import all the things that we will need for the game.------////
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

//
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class SuperBowserVocabulary
{
	private TutorialPanel tutorial; // declare all the panels we will need to add card layout to, in the holdLayout class, so that we can add several other panels onto that specific
	private HoldPanel hold;            // panel.
	private InstructionsPanel instructions;
	private StartPanel begin;
	private CardLayout cards;
	public static void main(String[] args)
	{
		SuperBowserVocabulary game = new SuperBowserVocabulary();
	}
	public SuperBowserVocabulary()
	{
		JFrame gameProject = new JFrame("Super Bowser Vocabulary Game"); //Here, we will set up the JFrame that will contain all the other panels.
		gameProject.setSize(800, 800);
		gameProject.setDefaultCloseOperation(gameProject.EXIT_ON_CLOSE); //Make sure that the application shuts down, once the user presses the 'x' in the corner of the JFrame.
		gameProject.setLocation(100, 80);
		gameProject.setResizable(false);// this will make sure the user is not able to change the size of the screen, so the components' layouts are maintained.

		hold = new HoldPanel(); // The constructor of the HoldPanel class will be called, so that we can add the start, tutorial, and instructions panels with card layout.
		gameProject.getContentPane().add(hold);
		gameProject.setVisible(true); //makes sure that the frame is visible on the screen.
	}

	class HoldPanel extends JPanel
	{
		public HoldPanel()
		{
			cards = new CardLayout(); //set the layout of all the panels that we will add onto this frame.
			setLayout(cards);

			HomePanel start = new HomePanel(); //call the constructor of each of the panel classes.
			tutorial = new TutorialPanel();
			instructions = new InstructionsPanel(); //call all the other constructors as well, so that we can show the content of the start, instructions, and tutorial panel.
			begin = new StartPanel();

			add(start, "home"); //add all the panels by using card layout.
			add(begin, "start");
			add(tutorial, "tutorial");
			add(instructions, "instructions");
		}
	}
	class HomePanel extends JPanel
	{
		private JPanel instructions, begin, buttonPanel, titlePanel; //declare all the sub- panels, which include the instructions, begin , as well as buttonPanel and titlePanels, which will be on
		public CardLayout cards;                             //the home panel.
		public HomePanel()
		{
			setBackground(Color.WHITE);
			setLayout(null); //this will allow us to position the panels, particularly where we want on the main home panel.
			buttonPanel = new ButtonPanel();
			titlePanel = new TitlePanel(); //call the constructors, so we can add the design of the
			titlePanel.setLocation(50, 50);
			titlePanel.setSize(700, 100);
			buttonPanel.setLocation(300, 350);
			buttonPanel.setSize(200, 100);
			add(buttonPanel);
			add(titlePanel);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		}
	}
	class TitlePanel extends JPanel //This is the panel containing the title of the game "Super Bowser Vocabulary"
	{
		public TitlePanel()
		{

		}
		public void paintComponent(Graphics g) //We will create a larger, bold font, for the title.
		{
			super.paintComponent(g);
			g.setFont(new Font("Serif", Font.BOLD,30)); //Set font of the text, so that it is visible.
			g.drawString("Super Bowser Vocabulary", 175, 50);
		}
	}
	class ButtonPanel extends JPanel implements ActionListener //so that we can change the panels in response to a button being clicked
	{
		private JButton startB, toInstructions, example;
		private JPanel instructions, begin, buttonPanel, titlePanel;
		private HomePanel home;
		public ButtonPanel()
		{
			setLayout(new GridLayout(3, 1, 15, 10)); //Add grid layout and space, so that the JButtons are clearly visible and are spaced.
			startB = new JButton("Start");
			toInstructions = new JButton("Instructions");
			example = new JButton("Tutorial");
			example.addActionListener(this); //Add actionListener, so the buttons respond to all of the actions that are performed to the buttons.
			toInstructions.addActionListener(this);
			startB.addActionListener(this);
			add(startB); //add the buttons to the panel.
			add(toInstructions);
			add(example);
		}
		public void paintComponent(Graphics g)
		{
			// setBackground(Color.RED);
			super.paintComponent(g);
		}
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==example) //if tutorial button is pressed, we will redirect to the tutorial panel
			{
				cards.show(hold, "tutorial");
			}

			else if(e.getSource()==toInstructions)
			{
				cards.show(hold, "instructions"); //if instructions button is pressed, we will redirect to the instructions panel
			}

			else if(e.getSource() == startB)
			{
				cards.show(hold, "start"); //if start button is pressed, we will redirect to the start panel
			}
		}
	}
	class TutorialPanel extends JPanel
	{

		public TutorialPanel()
		{
			setBackground(Color.PINK);

			setLayout(null);
			TutorialTitle title = new TutorialTitle(); /** ADD  **/
			TutorialBack back = new TutorialBack();
			TutorialExamples ex = new TutorialExamples();
			title.setSize(800, 100);
			title.setLocation(80, 0);
			back.setSize(80, 40);
			back.setLocation(0, 0);
			ex.setSize(400, 400);
			ex.setLocation(190, 200);
			add(title);
			add(back);
			add(ex);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		}
	}

	//This class needs to be edited- we need to center the title on the TutorialPanel.

	class TutorialTitle extends JPanel 
	{
		private JButton tutorialBack;

		public TutorialTitle()
		{

		}

		public void paintComponent(Graphics g)
		{
			setBackground(Color.WHITE); //set the background color of the TutorialTitle frame white
			super.paintComponent(g);

			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.drawString("Learn How to Play Super Mario Vocabulary", 80, 35); //set font andtext on additional JPanel that we will add onto the tutorial panel.
		}

	}

	class TutorialBack extends JPanel implements ActionListener
	{
		private JButton tutorialBack;

		public TutorialBack()
		{
			tutorialBack= new JButton("Back"); //we will initialize the JButton
			add(tutorialBack, new FlowLayout(FlowLayout.LEFT));

			add(tutorialBack); //add JButton

			tutorialBack.addActionListener(this); //make sure that we are redirectred to the home panel after we are done clicking the JButton.

		}

		public void actionPerformed(ActionEvent e)
		{

			if(e.getSource()== tutorialBack)
			{
				cards.show(hold, "home"); //check if the tutorial's back button is pressed and if so, redirect to main, home panel.
			}
		}
	}

	class TutorialExamples extends JPanel
	{
		private int check;
		
		public TutorialExamples()
		{
			//check = (int)(Math.random()*5+1);
			check = 1;
		}
		
		public void paintComponent(Graphics g)
		{
			setBackground(Color.GRAY);
			super.paintComponent(g);
			
			if(check==1)
			{
		
			g.drawString("The back of the Radley house was less inviting than ", 50, 100);
			
			g.drawString("the front: a", 50, 120);
			
			g.drawString("porch ran the width of ", 230, 120);
					
			g.drawString("the house there were two doors and two dark", 50, 140);
			
			g.drawString("windows between the doors.", 50, 160);
			
			g.setFont(new Font("Arial", Font.BOLD, 18));
			
			g.drawString("ramshackle", 125, 120);
			g.drawLine(125, 122, 225, 122);
			
			g.drawLine(230, 110, 500, 100);
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			
			g.drawString("Example 1", 15, 30);
			
			}
			
			/*
			else if(check==2)
				g.drawString("IT IS WORKING", 50,  50);
			
			else if(check==3)
				g.drawString("WOW", 50,  50);
			
			else if(check==4)
				g.drawString("okay", 50,  50);
			
			else if(check==5)
				g.drawString("haha", 50,  50);
				*/
			
		}
	}

	class InstructionsPanel extends JPanel implements ActionListener
	{
		private JButton instructionsBack; //declare the back button.

		public InstructionsPanel()
		{
			setBackground(Color.RED);

			instructionsBack= new JButton("Back"); //have back button that will redirect to the homepage.
			add(instructionsBack, new FlowLayout(FlowLayout.LEFT));

			instructionsBack.addActionListener(this); //make back button respond.
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); //will paint the background
		}

		public void actionPerformed(ActionEvent e)
		{

			if(e.getSource()== instructionsBack)
			{
				cards.show(hold, "home"); //if the instructions panel's back button is pressed, redirect to the home panel.


				//This will eventually be called the "InstruuctionsPanel".

				//Here, we will begin to call the constructor of "ExampleContext" Class

				//set the exact location of the ExampleContextPanel- around the center of InstructionsPanel.

				//Make the panel visible
			}
		}
	}

	class StartPanel extends JPanel implements ActionListener
	{
		private JButton back;
		private Image image;
		private String pictName;
		private boolean jump;

		public StartPanel()
		{
			setBackground(Color.GREEN);

			TutorialBack back = new TutorialBack();
			back.setSize(80, 40);
			back.setLocation(0, 0);
			
			jump = false;
			image = null;
			pictName = "aditiJava/UV Mario Standing Big.jpeg"; //this does not need to be a field var, but it can easily be changed here.

			setLayout(null);
			MarioPanel pane = new MarioPanel();
			pane.setLocation(0, 100);
			pane.setSize(800, 700);
			add(pane);
			add(back);	
			getMyImage();
		}

		public void getMyImage()
		{

			try
			{
				image = ImageIO.read(new File(pictName));

			}
			catch(IOException e)
			{
				System.err.println("\n\n" + pictName + " can't be found.\n\n");
				e.printStackTrace(); //This makes sure that the error is seen. Since there is no System.exit(1), the program will not stop.
			}


		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); //This will make sure that the background of the panel is painted with the appropriate color.


			//Start designing the game panel in which there will be Mario on the right side and Bowser on the left side.


			//Design the platforms Mario and Bowser will be standing on


			//Add a score system- draw the string here


		}

		//Add method that handles the scoring system- make sure to have variables that can be printed out as strings.

		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()== back)
			{
				cards.show(hold, "home"); //if the start panel's back button is pressed, redirect to the home panel.
			}
		}


		class MarioPanel extends JPanel implements KeyListener, MouseListener
		{

			private int posX, posY;
			private int x, y;

			public MarioPanel()
			{
				x=50;
				y=500;
				posX= 10;
				posY = 10;

				setBackground(Color.ORANGE);
				addKeyListener(this);
				addMouseListener(this);
				//addActionListener(this);
			}


			public void paintComponent ( Graphics g )
			{
				///////////////////////////////////////////////////
				// 2.Draw the Image onto the panel.  Examples will use all three methods.
				super.paintComponent(g);

				g.drawImage(image, x, y, 150, 150, this);
				g.setColor(Color.RED);
				g.drawLine(30, 623, 235, 623);

				for(int x=30; x<=230; x+=10)
				{
					g.fillRect(x, 623, 5, 5);
				}

				for(int x=35; x<=230; x+=10)
				{
					g.fillRect(x, 618, 5, 5);
				}

				for(int x=30; x<=230; x+=10)
				{
					g.fillRect(x, 473, 5, 5);
				}

				for(int x=35; x<=230; x+=10)
				{
					g.fillRect(x, 468, 5, 5);
				}

				g.drawLine(30, 473, 230, 473);


				g.drawLine(30, 323, 230, 323);

				for(int x=30; x<=230; x+=10)
				{
					g.fillRect(x, 323, 5, 5);
				}

				for(int x=35; x<=230; x+=10)
				{
					g.fillRect(x, 318, 5, 5);
				}
				g.drawLine(30, 173, 230, 173);

				for(int x=30; x<=230; x+=10)
				{
					g.fillRect(x, 173, 5, 5);
				}

				for(int x=35; x<=230; x+=10)
				{
					g.fillRect(x, 168, 5, 5);
				}


			}

			public void keyTyped(KeyEvent e) {
	
			}

			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();

				requestFocusInWindow();

				if(code==KeyEvent.VK_UP)
				{
					y -= 150;
					repaint();
				}

				else if(code==KeyEvent.VK_DOWN)
				{
					y += 150;
					repaint();
				}
				else if(code==KeyEvent.VK_LEFT)
				{
					x -= 15;
					repaint();
				}
				else if(code==KeyEvent.VK_RIGHT)
				{
					x += 15;
					repaint();
				}

			}


			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}


			@Override
			public void mouseClicked(MouseEvent e) {



			}


			@Override
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
				//jump=true;
			}


			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}


			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}


			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		}
	}

	//Here, I will make a BackButton Class, in which I will design the back button and from the StartPanel, TutorialPanel, and InstructionsPanel
	//Classes, I will call the constructor of this BackButton Class, so that I can show the "Back" button at the top left corner
	//of every one of these sub-panels.



	//Start designing ImageClass
	//   - This will get the image of the mushroom

	//	- We will start to display the background image


	// 	-Decide on how to add the hills image on the GamePanel.

	//End Image Class


	//Create the ExampleContext Class, in which we will use Math.random() and provide various examples on how to identify
	//the definition of a vocabulary word, based on context clues.


}
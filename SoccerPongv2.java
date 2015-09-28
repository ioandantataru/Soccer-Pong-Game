//Name: Ioan Tataru
//Bayview Class: ICS4U
//Teacher: Mr. Anandarajan
//Description: This is a game involving OOP

// The "Pongv1" class.
import java.awt.*;
import hsa.Console;
import java.awt.font.*;
import java.io.*;
public class SoccerPongv2
{
    static Console c;           // The output console
    static Paddle paddle1;
    static Paddle paddle2;
    static Square square1;
    static Ball ball1;
    static int moveX, moveY, squY;
    static int move1, move2;
    static int score = 0, multi = 0;
    static char cKey, dif, chara, again;
    static boolean pause = false;
    static boolean blackwhole = false;
    static File f;
    static String[] highname = new String [11];
    static int[] highscore = new int [11];
    static String name;
    static Font a;
    static int nplayer = 1;
    static int counter1 = 0;
    static File Save;
    static boolean loaded = false;
    static int scorep1 = 0, scorep2 = 0;
    static Font h;
    public static void main (String[] args) throws Exception
    {
	c = new Console ();

	do //main menu loop
	{
	    Men ();
	    do
	    {
		chara = c.getChar ();
		if (chara == 'p' || chara == 'P') //play
		{

		    Men1 ();
		    while (chara != 'm' && chara != 'M' && chara != '1' && chara != '2') //singleplayer, multiplayer or back to menu
		    {
			chara = c.getChar ();
		    }
		    if (chara == 'm' || chara == 'M')
		    {
			break;
		    }
		    else if (chara == '1')
		    {
			Men2 ();
			dif = c.getChar ();
			while (dif != 'e' && dif != 'E' && dif != 'm' && dif != 'M' && dif != 'h' && dif != 'H')
			{
			    dif = c.getChar ();
			}
			PlaySingle ();

		    }
		    else if (chara == '2')
		    {
			nplayer++;
			Men2 ();
			dif = c.getChar ();
			while (dif != 'e' && dif != 'E' && dif != 'm' && dif != 'M' && dif != 'h' && dif != 'H')
			{
			    dif = c.getChar ();
			}
			Play ();
		    }
		}

		else if (chara == 'i' || chara == 'I') //instructions
		{
		    Instructions ();
		    chara = c.getChar ();
		    while (chara != 'm' && chara != 'M')
		    {
			chara = c.getChar ();
		    }
		    if (chara == 'm' || chara == 'M')
		    {
			break;
		    }
		}
		else if (chara == 'q' || chara == 'Q') //quit
		{
		    c.close ();
		}
		else if (chara == 'h' || chara == 'H') //highscore
		{
		    HSDisplay ();
		    HighscoreGraphics ();
		    chara = c.getChar ();
		    while (chara != 'm' && chara != 'M')
		    {
			chara = c.getChar ();
		    }
		    if (chara == 'm' || chara == 'M')
		    {
			break;
		    }
		}
		else if (chara == 'l' || chara == 'L') //load
		{
		    c.setColor (Color.black);
		    c.fillRect (0, 0, 640, 500);
		    c.setColor (Color.yellow);
		    c.fillOval (95, 180, 420, 250);
		    c.setFont (new Font ("Times New Roman", Font.BOLD, 30));
		    c.setColor (Color.magenta);
		    c.drawString ("Enter the name of the file", 140, 260);
		    String savedfile = c.readLine ();
		    Save = new File (savedfile);
		    while (Save.exists () == false)
		    {
			c.drawString ("Invalid File Name", 190, 150);
			c.drawString ("Enter the name of the file", 140, 260);
			c.drawString ("OR", 280, 290);
			c.drawString ("Enter (m) to return to the Menu", 100, 320);
			savedfile = c.readLine ();
			if (savedfile.equals ("m") || savedfile.equals ("M"))
			{
			    break;
			}
			Save = new File (savedfile);
		    }
		    if (savedfile.equals ("m") || savedfile.equals ("M"))
		    {
			break;
		    }
		    LoadGame ();
		}
	    }
	    while (true);
	}
	while (true);

	// Place your program here.  'c' is the output console
    } // main method


    public static int HSlength () throws IOException //determine the length of the hs
    {
	int counter = 0;
	f = new File ("HighScore.txt");
	BufferedReader input = new BufferedReader (new FileReader (f));
	String line;
	line = input.readLine ();
	if (f.exists () == false)
	{
	    return 0;
	}
	else
	{
	    while (line != null)
	    {
		line = input.readLine ();
		line = input.readLine ();
		counter++;
	    }
	    return counter;
	}
    }


    public static void HS () throws IOException //helper method for the high score
    {
	f = new File ("HighScore.txt");
	if (f.exists ())
	{
	    BufferedReader input = new BufferedReader (new FileReader (f));
	    int counter = 0;
	    String line = input.readLine ();
	    while (counter < 11 && line != null)
	    {
		highname [counter] = line;
		line = input.readLine ();
		//c.println (line);
		if (line == null)
		{
		    break;
		}
		highscore [counter] = Integer.parseInt (line);
		counter++;
		line = input.readLine ();
	    }
	    InsertionSort (HSlength ());

	    PrintWriter Input = new PrintWriter (new FileWriter (f));
	    for (int i = 0 ; i < 10 ; i++)
	    {
		if (highname [i] != null && highscore [i] != 0)
		{
		    Input.println (highname [i]);
		    Input.println (highscore [i]);
		}
	    }
	    Input.close ();
	}
    }


    public static void HSStore () throws IOException //stores a new high score
    {
	HS ();
	if (f.exists () == false)
	{
	    String FileIn = "HighScore.txt";
	    PrintWriter Input = new PrintWriter (new FileWriter (FileIn));
	    Input.println (name);
	    Input.println (score);
	    Input.close ();
	}


	else
	{
	    PrintWriter Input = new PrintWriter (new FileWriter (f));
	    Input.println (name);
	    Input.println (score);
	    for (int i = 0 ; i < 10 ; i++)
	    {
		if (highname [i] != null && highscore [i] != 0)
		{
		    Input.println (highname [i]);
		    Input.println (highscore [i]);
		}
	    }
	    Input.close ();
	}
	HS ();
    }


    public static void HSDisplay () throws IOException //displayes the top 10 high scores
    {
	HS ();
	c.clear ();
	int x = 80, y = 100;
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);

	c.setColor (Color.magenta);
	c.setFont (a = new Font ("Times New Roman", Font.BOLD, 40));
	c.drawString ("High Score", 225, 40);
	for (int i = 0 ; i < 10 ; i++)
	{
	    if (highname [i] != null && highscore [i] != 0)
	    {
		c.setFont (a = new Font ("Comic Sans MS", Font.BOLD, 15));
		c.setColor (Color.orange);
		c.drawString ("Name: " + highname [i], 270, x);
		c.drawString ("Score: " + highscore [i], 270, y);
		x = x + 40;
		y = y + 40;
	    }
	}

	c.setColor (Color.yellow);
    }


    public static void InsertionSort (int hslength)  //sorts the high scores in descending order
    {
	for (int i = 1 ; i < hslength ; i++)
	{
	    int valueToSort = highscore [i];
	    String temp = highname [i];
	    int j = i;
	    while (j > 0 && highscore [j - 1] < valueToSort)
	    {
		highscore [j] = highscore [j - 1];
		highname [j] = highname [j - 1];
		j--;
	    }
	    highscore [j] = valueToSort;
	    highname [j] = temp;
	}
    }


    public static void Again ()  //graphics
    {
	c.setColor (Color.yellow);
	c.fillRect (160, 350, 350, 70);
	c.setColor (Color.magenta);
	c.drawString ("Play again? (y/n)", 170, 400);
    }


    public static void HighscoreGraphics ()  //graphics
    {
	c.setColor (Color.yellow);
	c.fillRect (475, 350, 150, 60);
	c.setColor (Color.magenta);
	Font button;
	c.setFont (button = new Font ("Comic Sans MS", Font.BOLD, 20));
	c.drawString ("Menu (m)", 510, 390);
    }


    public static void Instructions ()  //displayes instructions
    {
	Font h;
	char k;
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	c.setColor (Color.magenta);
	c.setFont (h = new Font ("Times New Roman", Font.BOLD, 40));
	c.drawString ("Instructions", 220, 40);
	c.setFont (h = new Font ("Comic Sans MS", Font.BOLD, 15));
	c.setColor (Color.white);
	c.drawString ("The goal of the game is to block the ball from escaping on your side of the screen.", 1, 80);
	c.drawString ("Commands for 1 player: (The first one to score 1 goal wins!!!)", 1, 100);
	c.drawString ("Use 'w' to go up with you paddle and 's' to go down", 20, 120);
	c.drawString ("Commands for 2 players: (The first one to score 3 goals wins!!!)", 1, 140);
	c.drawString ("First player must use 'w' to go up with you paddle and 's' to go down", 20, 160);
	c.drawString ("Second player must use letters 'o' to go up and 'l' to go down", 20, 180);
	c.drawString ("Levels of Difficulty for 1 player:", 1, 200);
	c.drawString ("Easy: Player's paddle height | AI paddle's height | AI paddle speed", 20, 220);
	c.drawString ("               150 px     |        100 px       |     200 px per sec ", 40, 240);
	c.drawString ("Medium: Player's paddle height | AI paddle's height | AI paddle speed", 20, 260);
	c.drawString ("                  100 px         |        100 px      |     300 px per sec ", 20, 280);
	c.drawString ("Hard: Player's paddle height | AI paddle's height | AI paddle speed", 20, 300);
	c.drawString ("               100 px      |        125 px      |     400 px per sec ", 40, 320);
	c.drawString ("Levels of Difficulty for 2 players:", 1, 340);
	c.drawString ("Easy: 150 pixels paddle", 20, 360);
	c.drawString ("Medium: 100 pixels paddle", 20, 380);
	c.drawString ("Hard: 50 pixels paddle", 20, 400);
	c.drawString ("Score: (easy X1) (medium X2) (hard X3)", 1, 420);
	c.drawString ("Score = level x (number of player's paddle hits + 25(if player wins))", 20, 440);
	c.drawString ("Other keyboard commands:", 1, 460);
	c.drawString ("(p) pause | (r) save", 1, 480);
	c.setColor (Color.yellow);
	c.drawString ("Watch out for the referee in multiplayer mode!!!", 250, 480);
	HighscoreGraphics ();
    }


    public static void Men ()  //menu grapghics
    {
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	c.setColor (Color.yellow);
	c.fillOval (180, 80, 280, 150);
	c.setFont (new Font ("Times New Roman", Font.BOLD, 45));
	c.setColor (Color.magenta);
	c.drawString ("Soccer Pong", 210, 170);
	Font button;
	c.setFont (button = new Font ("Comic Sans MS", Font.BOLD, 20));

	c.setColor (Color.yellow); //Play
	c.fillRect (50, 300, 150, 60);
	c.setColor (Color.magenta);
	c.drawString ("Play (p)", 75, 340);

	c.setColor (Color.yellow); //Instructions
	c.fillRect (250, 300, 150, 60);
	c.setColor (Color.magenta);
	c.drawString ("Instruction (i)", 260, 340);

	c.setColor (Color.yellow); //Quit
	c.fillRect (450, 300, 150, 60);
	c.setColor (Color.magenta);
	c.drawString ("Quit (q)", 475, 340);

	c.setColor (Color.yellow); //High Score
	c.fillRect (50, 400, 250, 60);
	c.setColor (Color.magenta);
	c.drawString ("High Score (h)", 103, 438);

	c.setColor (Color.yellow); //Load
	c.fillRect (350, 400, 250, 60);
	c.setColor (Color.magenta);
	c.drawString ("Load (l)", 435, 438);
    }


    public static void Men1 ()  //menu graphics
    {
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	c.setColor (Color.yellow);
	c.fillOval (180, 80, 280, 150);
	c.setFont (new Font ("Times New Roman", Font.BOLD, 45));
	c.setColor (Color.magenta);
	c.drawString ("Soccer Pong", 210, 170);
	Font button;
	c.setFont (button = new Font ("Comic Sans MS", Font.BOLD, 20));

	c.setColor (Color.yellow); //Play
	c.fillRect (40, 300, 170, 60);
	c.setColor (Color.magenta);
	c.drawString ("Single Player (1)", 50, 340);

	c.setColor (Color.yellow); //Help
	c.fillRect (240, 300, 170, 60);
	c.setColor (Color.magenta);
	c.drawString ("Multiplayer (2)", 260, 340);

	c.setColor (Color.yellow); //Quit
	c.fillRect (440, 300, 170, 60);
	c.setColor (Color.magenta);
	c.drawString ("Menu (m)", 475, 340);
    }


    public static void Men2 ()  //menu graphics
    {
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	c.setColor (Color.yellow);
	c.fillOval (180, 80, 280, 150);
	c.setFont (new Font ("Times New Roman", Font.BOLD, 45));
	c.setColor (Color.magenta);
	c.drawString ("Difficulty", 240, 170);
	Font button;
	c.setFont (button = new Font ("Comic Sans MS", Font.BOLD, 20));

	c.setColor (Color.yellow);
	c.fillRect (40, 300, 170, 60);
	c.setColor (Color.magenta);
	c.drawString ("Easy (e)", 50, 340);

	c.setColor (Color.yellow);
	c.fillRect (240, 300, 170, 60);
	c.setColor (Color.magenta);
	c.drawString ("Medium (m)", 260, 340);

	c.setColor (Color.yellow);
	c.fillRect (440, 300, 170, 60);
	c.setColor (Color.magenta);
	c.drawString ("Hard (h)", 475, 340);
    }



    public static void Play () throws Exception //multiplayer mode
    {
	nplayer = 2; //set number of players
	do
	{
	    if (loaded == false)
	    {
		scorep1 = 0;
		scorep2 = 0;
	    }
	    while (scorep1 < 3 && scorep2 < 3)
	    {
		if (loaded == false)
		{
		    counter1 = 0;
		    c.clear ();
		    move1 = (int) (Math.random () * 2); //generate initial random direction for ball
		    move2 = (int) (Math.random () * 2);
		    squY = 1;
		    if (move1 < 1)
		    {
			moveX = -1;
		    }
		    else
		    {
			moveX = 1;
		    }
		    if (move2 < 1)
		    {
			moveY = -1;
		    }
		    else
		    {
			moveY = 1;
		    }
		    paddle1 = new Paddle (); //initiate objects
		    paddle2 = new Paddle ();
		    square1 = new Square ();
		    paddle2.SetPone (false);
		    square1.SetY (0);
		    square1.SetX ((640 - square1.GetSize ()) / 2);
		    square1.Setspeed (2);
		    square1.Draw (c);
		    ball1 = new Ball ();
		    ball1.SetBackground (Color.green);
		    paddle1.SetBackground (Color.green);
		    paddle2.SetBackground (Color.green);
		    RanClrBall ();
		    int radi = 20;
		    if (dif == 'e' || dif == 'E' || multi == 1) //determine paddle height and score multiplyer based on level of difficulty
		    {
			paddle1.SetHeight (150);
			paddle2.SetHeight (150);
			multi = 1;
		    }
		    else if (dif == 'm' || dif == 'M' || multi == 2)
		    {
			paddle1.SetHeight (100);
			paddle2.SetHeight (100);
			multi = 2;
		    }
		    else
		    {
			paddle1.SetHeight (50);
			paddle2.SetHeight (50);
			multi = 3;
		    }
		    ball1.SetRadius (radi);
		    int ballx, bally;
		    ballx = (int) (Math.random () * 200) + 220;
		    ball1.SetX (ballx);
		    bally = (int) (Math.random () * 200) + 150;
		    ball1.SetY (bally);
		    ball1.Draw (c);
		    ball1.Setspeed (2);
		    paddle1.SetWidth (25);
		    paddle1.Setspeed (35);
		    paddle1.SetColour (Color.blue);
		    paddle1.SetY ((500 - paddle1.GetHeight ()) / 2);
		    paddle1.Draw (c);
		    paddle2.SetWidth (25);
		    paddle2.Setspeed (35);
		    paddle2.SetColour (Color.red);
		    paddle2.SetX (640 - paddle2.GetWidth ());
		    paddle2.SetY ((500 - paddle2.GetHeight ()) / 2);
		    paddle2.Draw (c);
		    //square1.SetX ();
		    square1.SetY (0);
		    square1.Draw (c);
		}
		while (scorep1 != 3 && scorep2 != 3) //initiate the game
		{
		    c.setColor (Color.green);
		    c.fillRect (0, 0, 640, 500);
		    loaded = false;
		    if (pause == false)
		    {

			ball1.Setspeed (2 + (int) (counter1 / 10));
			blackwhole = false;
			if (ball1.isTouchingDown (c, 500))
			{
			    moveY = -1;
			}
			else if (ball1.isTouchingUp (c, 0))
			{
			    moveY = 1;
			}
			else if (paddle2.isTouching (ball1.GetXRight (), ball1.GetY (), c))
			{
			    moveX = -1;
			    counter1++;

			}
			else if (paddle1.isTouching (ball1.GetXLeft (), ball1.GetY (), c))
			{
			    moveX = 1;
			    counter1++;
			}

			else if (square1.isTouching (ball1.GetXRight (), ball1.GetY (), c) || square1.isTouching (ball1.GetXLeft (), ball1.GetY (), c) || square1.isTouching (ball1.GetX (), ball1.GetYUp (), c) || square1.isTouching (ball1.GetX (), ball1.GetYDown (), c))
			{
			    ball1.Erase (c);
			    ball1.SetX ((square1.GetX () * 2 + square1.GetSize ()) / 2);
			    ball1.SetY ((square1.GetY () * 2 + square1.GetSize ()) / 2);
			    RanClrBall ();
			    blackwhole = true;
			}
			if (square1.GetY () + square1.GetSize () + square1.Getspeed () > 500)
			{
			    squY = -1;
			}
			else if (square1.GetY () - square1.Getspeed () < 0)
			{
			    squY = 1;
			}
			square1.Move (c, square1.GetX (), square1.GetY () + (square1.Getspeed () * squY));
			ball1.Move (c, ball1.GetX () + ball1.Getspeed () * moveX, ball1.GetY () + ball1.Getspeed () * moveY);
			paddle1.Draw (c);
			paddle2.Draw (c);
			Thread.sleep (10);
			if (blackwhole)
			{
			    ball1.Erase (c);
			    int rand = (int) (Math.random () * 4);
			    if (rand < 1)
			    {
				moveX = 1;
				moveY = -1;
				ball1.Move (c, square1.GetX () + square1.GetSize () + 1, square1.GetY () - 1);
			    }
			    else if (rand < 2)
			    {
				moveX = -1;
				moveY = -1;
				ball1.Move (c, square1.GetX () - 1, square1.GetY () - 1);
			    }
			    else if (rand < 3)
			    {
				moveX = -1;
				moveY = 1;
				ball1.Move (c, square1.GetX () - 1, square1.GetY () + square1.GetSize () + 1);
			    }
			    else
			    {
				moveX = 1;
				moveY = 1;
				ball1.Move (c, square1.GetX () + square1.GetSize () + 1, square1.GetY () + square1.GetSize () + 1);
			    }
			}
			if (c.isCharAvail () && moveX == 1)
			{
			    cKey = c.getChar ();
			    if (cKey == 'o')
			    {
				if (paddle2.GetY () - paddle2.Getspeed () >= 0)
				{
				    paddle2.Move (c, paddle2.GetX (), paddle2.GetY () - paddle2.Getspeed ());
				}
				else
				{
				    paddle2.Move (c, paddle2.GetX (), 0);
				}

			    }
			    else if (cKey == 'l')
			    {
				if (paddle2.GetY () + paddle2.Getspeed () <= (500 - paddle2.GetHeight ()))
				{
				    paddle2.Move (c, paddle2.GetX (), paddle2.GetY () + paddle2.Getspeed ());
				}
				else
				{
				    paddle2.Move (c, paddle2.GetX (), 500 - paddle2.GetHeight ());
				}
			    }
			    else if (cKey == 'p')
			    {
				pause = true;
			    }
			    else if (cKey == 'r')
			    {
				SaveGame ();
			    }
			}
			else if (c.isCharAvail () && moveX == -1)
			{
			    cKey = c.getChar ();
			    if (cKey == 'w')
			    {
				if (paddle1.GetY () - paddle1.Getspeed () >= 0)
				{
				    paddle1.Move (c, paddle1.GetX (), paddle1.GetY () - paddle1.Getspeed ());
				}
				else
				{
				    paddle1.Move (c, paddle1.GetX (), 0);
				}
			    }
			    else if (cKey == 's')
			    {
				if (paddle1.GetY () + paddle1.Getspeed () <= (500 - paddle1.GetHeight ()))
				{
				    paddle1.Move (c, paddle1.GetX (), paddle1.GetY () + paddle1.Getspeed ());
				}
				else
				{
				    paddle1.Move (c, paddle1.GetX (), 500 - paddle1.GetHeight ());
				}
			    }
			    else if (cKey == 'p')
			    {
				pause = true;
			    }
			    else if (cKey == 'r')
			    {
				SaveGame ();
			    }
			}

			if (ball1.GetXRight () < 0)
			{
			    scorep2++;
			    c.setColor (Color.red);
			    c.setFont (h = new Font ("Comic Sans MS", Font.BOLD, 45));
			    c.drawString ("GOAL!!!", 240, 200);
			    c.drawString (Integer.toString (scorep1) + " - " + Integer.toString (scorep2), 250, 300);
			    Thread.sleep (1500);
			    break;
			}
			else if (ball1.GetXLeft () > 640)
			{
			    scorep1++;
			    c.setColor (Color.blue);
			    c.setFont (h = new Font ("Comic Sans MS", Font.BOLD, 45));
			    c.drawString ("GOAL!!!", 240, 200);
			    c.drawString (Integer.toString (scorep1) + " - " + Integer.toString (scorep2), 250, 300);
			    Thread.sleep (1500);
			    break;
			}
		    }
		    else
		    {
			c.setColor (Color.black);
			c.drawString ("Paused", 100, 100);
			c.drawString ("Press (p) to resume", 75, 140);
			Thread.sleep (15);
			if (c.isCharAvail ())
			{
			    cKey = c.getChar ();
			    if (cKey == 'p')
			    {
				pause = false;
			    }
			}

		    }
		}
	    }
	    c.setColor (Color.green);
	    c.fillRect (0, 0, 640, 500);
	    paddle1.Draw (c);
	    paddle2.Draw (c);
	    c.setFont (new Font ("Times New Roman", Font.BOLD, 45));
	    if (scorep1 >= 3)
	    {
		c.setColor (Color.black);
		c.drawString ("GAME OVER", 190, 200);
		c.setColor (Color.blue);
		c.drawString ("Blue Wins", 225, 300);
	    }
	    else
	    {
		c.setColor (Color.black);
		c.drawString ("GAME OVER", 190, 200);
		c.setColor (Color.red);
		c.drawString ("Red Wins", 225, 300);
	    }
	    Again ();
	    loaded = false;
	    again = c.getChar ();
	    while (again != 'y' && again != 'n')
	    {
		again = c.getChar ();
	    }
	}
	while (again == 'y');
	c.close ();
    }


    public static void PlaySingle () throws Exception //singleplayer mode
    {
	c.clear ();
	if (loaded == false)
	{
	    c.setColor (Color.black);
	    c.fillRect (0, 0, 640, 500);

	    c.setColor (Color.yellow);
	    c.fillOval (140, 200, 350, 100);

	    Font f;
	    c.setColor (Color.magenta);
	    c.setFont (f = new Font ("Times New Roman", Font.BOLD, 40));
	    c.drawString ("Enter Your Name", 170, 260);
	    name = c.readLine ();
	}


	nplayer = 1;
	do
	{
	    if (loaded == false)
	    {
		score = 0;
		counter1 = 0;
		//will gradually increase the speed
		c.clear ();
		move1 = (int) (Math.random () * 2);
		move2 = (int) (Math.random () * 2);

		if (move1 < 1)
		{
		    moveX = -1;
		}
		else
		{
		    moveX = 1;
		}
		if (move2 < 1)
		{
		    moveY = -1;
		}
		else
		{
		    moveY = 1;
		}
		paddle1 = new Paddle ();
		paddle2 = new Paddle ();
		paddle2.SetPone (false);
		ball1 = new Ball ();
		ball1.SetBackground (Color.green);
		paddle1.SetBackground (Color.green);
		paddle2.SetBackground (Color.green);
		RanClrBall ();
		int radi = 20;
		if (dif == 'e' || dif == 'E' || multi == 1) //initiate paddle height and AI paddle speed based on level of difficulty
		{
		    paddle1.SetHeight (150);
		    paddle2.SetHeight (100);
		    paddle2.Setspeed (2);
		    multi = 1;
		}
		else if (dif == 'm' || dif == 'M' || multi == 2)
		{
		    paddle1.SetHeight (100);
		    paddle2.SetHeight (100);
		    paddle2.Setspeed (3);
		    multi = 2;
		}
		else
		{
		    paddle1.SetHeight (100);
		    paddle2.SetHeight (125);
		    paddle2.Setspeed (4);
		    multi = 3;
		}
		ball1.SetRadius (radi);
		int ballx, bally;
		ballx = (int) (Math.random () * 200) + 220;
		ball1.SetX (ballx);
		bally = (int) (Math.random () * 200) + 150;
		ball1.SetY (bally);
		ball1.Draw (c);
		ball1.Setspeed (2);
		paddle1.SetWidth (25);
		paddle1.Setspeed (35);
		paddle1.SetColour (Color.blue);
		paddle1.SetY ((500 - paddle1.GetHeight ()) / 2);
		paddle1.Draw (c);
		paddle2.SetWidth (25);
		paddle2.SetColour (Color.red);
		paddle2.SetX (640 - paddle2.GetWidth ());
		paddle2.SetY ((500 - paddle2.GetHeight ()) / 2);
		paddle2.Draw (c);
		//square1.Draw (c);
	    }
	    while (true)
	    {
		c.setColor (Color.green);
		c.fillRect (0, 0, 640, 500);
		if (pause == false)
		{
		    ball1.Setspeed (2 + (int) (counter1 / 9));
		    if (ball1.isTouchingDown (c, 500)) //
		    {
			moveY = -1;
		    }
		    else if (ball1.isTouchingUp (c, 0))
		    {
			moveY = 1;
		    }
		    else if (paddle2.isTouching (ball1.GetXRight (), ball1.GetY (), c))
		    {
			moveX = -1;
			counter1++;
			RanClrBall ();
		    }
		    else if (paddle1.isTouching (ball1.GetXLeft (), ball1.GetY (), c))
		    {
			moveX = 1;
			counter1++;
			RanClrBall ();
			score++;
		    }
		    ball1.Move (c, ball1.GetX () + ball1.Getspeed () * moveX, ball1.GetY () + ball1.Getspeed () * moveY);
		    paddle1.Draw (c);
		    paddle2.Draw (c);
		    Thread.sleep (10);
		    if (c.isCharAvail ())
		    {
			cKey = c.getChar ();
			if (cKey == 'w')
			{
			    if (paddle1.GetY () - paddle1.Getspeed () >= 0)
			    {
				paddle1.Move (c, paddle1.GetX (), paddle1.GetY () - paddle1.Getspeed ());
			    }
			    else
			    {
				paddle1.Move (c, paddle1.GetX (), 0);
			    }
			}
			else if (cKey == 's')
			{
			    if (paddle1.GetY () + paddle1.Getspeed () <= (500 - paddle1.GetHeight ()))
			    {
				paddle1.Move (c, paddle1.GetX (), paddle1.GetY () + paddle1.Getspeed ());
			    }
			    else
			    {
				paddle1.Move (c, paddle1.GetX (), 500 - paddle1.GetHeight ());
			    }
			}
			else if (cKey == 'p')
			{
			    pause = true;
			}
			else if (cKey == 'r')
			{
			    SaveGame ();
			}
		    }

		    if (ball1.GetY () < (paddle2.GetY () + ((paddle2.GetHeight () / 3 * 2)))) //AI paddle movement
		    {
			if (paddle2.GetY () - paddle2.Getspeed () >= 0)
			{
			    paddle2.Move (c, paddle2.GetX (), paddle2.GetY () - paddle2.Getspeed ());
			}
			else
			{
			    paddle2.Move (c, paddle2.GetX (), 0);
			}
		    }
		    else if (ball1.GetY () > (paddle2.GetY () + (paddle2.GetHeight () / 3 * 2))) //AI paddle movement
		    {
			if (paddle2.GetY () + paddle2.Getspeed () <= (500 - paddle2.GetHeight ()))
			{
			    paddle2.Move (c, paddle2.GetX (), paddle2.GetY () + paddle2.Getspeed ());
			}
			else
			{
			    paddle2.Move (c, paddle2.GetX (), 500 - paddle2.GetHeight ());
			}
		    }
		    if (ball1.GetXRight () < 0 || ball1.GetXLeft () > 640)
		    {
			break;
		    }
		}
		else
		{
		    c.setColor (Color.black);
		    c.drawString ("Paused", 100, 100);
		    c.drawString ("Press (p) to resume", 75, 140);
		    Thread.sleep (15);
		    if (c.isCharAvail ())
		    {
			cKey = c.getChar ();
			if (cKey == 'p')
			{
			    pause = false;
			}
		    }

		}
	    }
	    ball1.Erase (c);
	    paddle1.Draw (c);
	    paddle2.Draw (c);
	    c.setFont (new Font ("Times New Roman", Font.BOLD, 45));
	    if (ball1.GetX () > 320)
	    {
		c.setColor (Color.black);
		c.drawString ("GAME OVER", 190, 200);
		c.setColor (Color.blue);
		c.drawString ("You win", 225, 300);
		score += 25;
	    }
	    else
	    {
		c.setColor (Color.black);
		c.drawString ("GAME OVER", 190, 200);
		c.setColor (Color.red);
		c.drawString ("You lose", 225, 300);
	    }
	    score = score * multi;
	    HSStore ();
	    Again ();
	    c.setColor (Color.blue);
	    c.drawString ("Score: " + score, 225, 100);
	    again = c.getChar ();
	    loaded = false;
	    while (again != 'y' && again != 'n')
	    {
		again = c.getChar ();
	    }
	}
	while (again == 'y');
	c.close ();
    }


    public static void RanClrBall ()  //generate random color for the ball
    {
	int r, b, g;
	r = (int) (Math.random () * 255);
	b = (int) (Math.random () * 255);
	g = (int) (Math.random () * 255);
	while (g >= (r + b))
	{
	    r = (int) (Math.random () * 255);
	    b = (int) (Math.random () * 255);
	    g = (int) (Math.random () * 255);
	}


	Color clr = new Color (r, g, b);
	ball1.SetColour (clr);

    }


    public static void SaveGame () throws IOException //save a game in progress
    {
	c.println ("Enter the name of the file");
	String FileIn = c.readLine ();
	c.clear ();
	PrintWriter Input = new PrintWriter (new FileWriter (FileIn));
	if (nplayer == 1)
	{
	    Input.println (nplayer); //number of players
	    Input.println (name);
	    Input.println (multi); //level of difficulti and score multiplyer for single player
	    Input.println (score);
	    Input.println (counter1);
	    Input.println (moveX);
	    Input.println (moveY);
	    Input.println (ball1.GetX ());
	    Input.println (ball1.GetY ());
	    Input.println (ball1.GetRadius ());
	    Input.println (ball1.Getspeed ());

	    Input.println (ball1.GetColour ().getRed ());
	    Input.println (ball1.GetColour ().getGreen ());
	    Input.println (ball1.GetColour ().getBlue ());

	    Input.println (paddle1.GetX ());
	    Input.println (paddle1.GetY ());
	    Input.println (paddle1.GetHeight ());
	    Input.println (paddle1.GetWidth ());
	    Input.println (paddle1.Getspeed ());
	    Input.println (paddle2.GetX ());
	    Input.println (paddle2.GetY ());
	    Input.println (paddle2.GetHeight ());
	    Input.println (paddle2.GetWidth ());
	    Input.println (paddle2.Getspeed ());
	}


	else
	{
	    Input.println (nplayer); //number of players
	    Input.println (multi);
	    Input.println (counter1);
	    Input.println (moveX);
	    Input.println (moveY);
	    Input.println (ball1.GetX ());
	    Input.println (ball1.GetY ());
	    Input.println (ball1.GetRadius ());
	    Input.println (ball1.Getspeed ());

	    Input.println (ball1.GetColour ().getRed ());
	    Input.println (ball1.GetColour ().getGreen ());
	    Input.println (ball1.GetColour ().getBlue ());
	    Input.println (paddle1.GetX ());

	    Input.println (paddle1.GetY ());
	    Input.println (paddle1.GetHeight ());
	    Input.println (paddle1.GetWidth ());
	    Input.println (paddle1.Getspeed ());
	    Input.println (paddle2.GetX ());
	    Input.println (paddle2.GetY ());
	    Input.println (squY);
	    Input.println (square1.GetX ());
	    Input.println (square1.GetY ());
	    Input.println (square1.GetSize ());
	    Input.println (square1.Getspeed ());
	    Input.println (blackwhole);
	    Input.println (scorep1);
	    Input.println (scorep2);
	}


	Input.close ();
    }


    public static void LoadGame () throws Exception //load a game
    {
	loaded = true;
	BufferedReader input = new BufferedReader (new FileReader (Save));
	paddle1 = new Paddle ();
	paddle2 = new Paddle ();
	paddle2.SetPone (false);
	ball1 = new Ball ();
	String line;
	int r, g, b;
	line = input.readLine ();
	c.println (line);
	if (line.equals ("1"))
	{
	    line = input.readLine ();

	    name = line;
	    line = input.readLine ();

	    multi = Integer.parseInt (line);
	    line = input.readLine ();

	    score = Integer.parseInt (line);
	    line = input.readLine ();

	    counter1 = Integer.parseInt (line);
	    line = input.readLine ();

	    moveX = Integer.parseInt (line);
	    line = input.readLine ();

	    moveY = Integer.parseInt (line);
	    line = input.readLine ();

	    ball1.SetX (Integer.parseInt (line));
	    line = input.readLine ();

	    ball1.SetY (Integer.parseInt (line));
	    line = input.readLine ();

	    ball1.SetRadius (Integer.parseInt (line));
	    line = input.readLine ();

	    ball1.Setspeed (Integer.parseInt (line));

	    line = input.readLine ();

	    r = Integer.parseInt (line);
	    line = input.readLine ();

	    g = Integer.parseInt (line);
	    line = input.readLine ();

	    b = Integer.parseInt (line);
	    Color savedclr = new Color (r, g, b);
	    ball1.SetColour (savedclr);

	    line = input.readLine ();

	    paddle1.SetX (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle1.SetY (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle1.SetHeight (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle1.SetWidth (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle1.Setspeed (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle2.SetX (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle2.SetY (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle2.SetHeight (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle2.SetWidth (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle2.Setspeed (Integer.parseInt (line));

	    input.close ();
	    paddle1.SetColour (Color.blue);
	    paddle2.SetColour (Color.red);
	    PlaySingle ();

	}


	else
	{
	    c.clear ();
	    square1 = new Square ();
	    line = input.readLine ();
	    multi = Integer.parseInt (line);
	    line = input.readLine ();
	    counter1 = Integer.parseInt (line);
	    line = input.readLine ();
	    moveX = Integer.parseInt (line);
	    line = input.readLine ();
	    moveY = Integer.parseInt (line);
	    line = input.readLine ();
	    ball1.SetX (Integer.parseInt (line));
	    line = input.readLine ();
	    ball1.SetY (Integer.parseInt (line));
	    line = input.readLine ();
	    ball1.SetRadius (Integer.parseInt (line));
	    line = input.readLine ();
	    ball1.Setspeed (Integer.parseInt (line));
	    line = input.readLine ();
	    r = Integer.parseInt (line);
	    line = input.readLine ();
	    g = Integer.parseInt (line);
	    line = input.readLine ();
	    b = Integer.parseInt (line);
	    Color savedclr = new Color (r, g, b);
	    ball1.SetColour (savedclr);
	    line = input.readLine ();
	    paddle1.SetX (Integer.parseInt (line));
	    line = input.readLine ();
	    paddle1.SetY (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle1.SetHeight (Integer.parseInt (line));
	    paddle2.SetHeight (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle1.SetWidth (Integer.parseInt (line));
	    paddle2.SetWidth (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle1.Setspeed (Integer.parseInt (line));
	    paddle2.Setspeed (Integer.parseInt (line));

	    line = input.readLine ();

	    paddle2.SetX (Integer.parseInt (line));
	    line = input.readLine ();

	    paddle2.SetY (Integer.parseInt (line));
	    line = input.readLine ();
	    squY = Integer.parseInt (line);

	    line = input.readLine ();

	    square1.SetX (Integer.parseInt (line));
	    line = input.readLine ();
	    square1.SetY (Integer.parseInt (line));
	    line = input.readLine ();
	    square1.SetSize (Integer.parseInt (line));
	    line = input.readLine ();
	    square1.Setspeed (Integer.parseInt (line));
	    line = input.readLine ();
	    if (line.equals ("true"))
	    {
		blackwhole = true;
	    }
	    else
	    {
		blackwhole = false;
	    }
	    line = input.readLine ();
	    scorep1 = (Integer.parseInt (line));
	    line = input.readLine ();
	    scorep2 = (Integer.parseInt (line));

	    input.close ();
	    paddle1.SetColour (Color.blue);
	    paddle2.SetColour (Color.red);
	    Play (); //use the boolean loaded to eliminate useless code from the Play() method
	}



    }
}



// Paddle_Test class

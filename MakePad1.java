// The "Paddle_Test" class.
import java.awt.*;
import hsa.Console;

public class MakePad1
{
    static Console c;           // The output console
    static Paddle paddle1;
    public static void main (String[] args) throws Exception
    {
	c = new Console ();        
	paddle1 = new Paddle ();
	paddle1.SetHeight (50);
	paddle1.SetColour (Color.blue);
	paddle1.Draw (c);
	Thread.sleep (100);
	while (!paddle1.isTouching (640 - paddle1.GetWidth (), 0, c))
	{
	    paddle1.Move (c, paddle1.GetX () + 1, paddle1.GetY ());
	    Thread.sleep (3);
	}
	while (!paddle1.isTouching (640 - paddle1.GetWidth (), 500 - paddle1.GetHeight (), c))
	{
	    paddle1.Move (c, paddle1.GetX (), paddle1.GetY () + 1);
	    Thread.sleep (3);
	}
	while (!paddle1.isTouching (0, 500 - paddle1.GetHeight (), c))
	{
	    paddle1.Move (c, paddle1.GetX () - 1, paddle1.GetY ());
	    Thread.sleep (3);
	}
	while (!paddle1.isTouching (0, 0, c))
	{
	    paddle1.Move (c, paddle1.GetX (), paddle1.GetY () - 1);
	    Thread.sleep (3);
	}

	// Place your program here.  'c' is the output console
    } // main method
} // Paddle_Test class

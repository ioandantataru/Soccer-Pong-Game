// The "Paddle_Test" class.
import java.awt.*;
import hsa.Console;

public class MakePad3
{
    static Console c;           // The output console
    static Paddle paddle1;
    public static void main (String[] args) throws Exception
    {
	c = new Console ();
	char cKey;
	paddle1 = new Paddle ();
	paddle1.SetHeight (50);
	paddle1.Setspeed (5);
	paddle1.SetColour (Color.blue);
	paddle1.Draw (c);
	while (true)
	{
	    cKey = c.getChar ();
	    if (cKey == 'w')
	    {
		if (paddle1.GetY () - paddle1.Getspeed () < 0)
		{
		    paddle1.Erase (c);
		    paddle1.SetY (500);
		    paddle1.Draw (c);
		}
		else
		{
		    paddle1.Move (c, paddle1.GetX (), paddle1.GetY () - paddle1.Getspeed ());
		}
	    }
	    else if (cKey == 's')
	    {
		if (paddle1.GetY () + paddle1.Getspeed () > 500)
		{
		    paddle1.Erase (c);
		    paddle1.SetY (0);
		    paddle1.Draw (c);
		}
		else
		{
		    paddle1.Move (c, paddle1.GetX (), paddle1.GetY () + paddle1.Getspeed ());
		}
	    }

	    else if (cKey == 'a')
	    {
		if (paddle1.GetX () - paddle1.Getspeed () < 0)
		{
		    paddle1.Erase (c);
		    paddle1.SetX (640);
		    paddle1.Draw (c);
		}
		else
		{
		    paddle1.Move (c, paddle1.GetX () - paddle1.Getspeed (), paddle1.GetY ());
		}
	    }

	    else if (cKey == 'd')
	    {
		if (paddle1.GetX () + paddle1.Getspeed () > 640)
		{
		    paddle1.Erase (c);
		    paddle1.SetX (0);
		    paddle1.Draw (c);
		}
		else
		{
		    paddle1.Move (c, paddle1.GetX () + paddle1.Getspeed (), paddle1.GetY ());
		}
	    }
	}

    }


    // Place your program here.  'c' is the output console
} // main method
// Paddle_Test class

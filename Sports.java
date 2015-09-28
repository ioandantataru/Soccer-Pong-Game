// The "Sports" class.
import java.awt.*;
import hsa.Console;

public class Sports
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	Football football1 = new Football ();
	football1.SetColour (Color.green);
	football1.Draw (c);
	// Place your program here.  'c' is the output console
    } // main method
} // Sports class

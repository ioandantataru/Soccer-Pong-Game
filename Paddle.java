// The "Paddle" class.
import java.awt.*;
import hsa.Console;

public class Paddle
{
    protected int x, y, height, width, speed;
    protected Color clr, background;
    protected boolean Pone;
    static Console c;           // The output console
    public Paddle ()
    {
	x = 0;
	y = 0;
	clr = Color.black;
	background = Color.white;
	height = 100;
	width = 100;
	speed = 10;
	Pone = true;
    }


    public Paddle (int x1, int y1, Color clr1, int s1, int h1, int w1, boolean Pone1)
    {
	this.x = x1;
	this.y = y1;
	this.clr = clr1;
	this.speed = s1;
	this.height = h1;
	this.width = w1;
	this.Pone = Pone1;
	background = Color.green;
    }


    public boolean getPone ()
    {
	return Pone;
    }


    public void SetPone (boolean Pone1)
    {
	Pone = Pone1;
    }


    public int Getspeed ()
    {
	return speed;
    }



    public void Setspeed (int s1)
    {
	speed = s1;
    }


    public int GetHeight ()
    {
	return height;
    }



    public void SetHeight (int h1)
    {
	height = h1;
    }


    public int GetWidth ()
    {
	return width;
    }


    public void SetWidth (int w1)
    {
	width = w1;
    }


    public void SetX (int x1)  //Communicators - setting a value
    {
	x = x1;
    }


    public int GetX ()  // returns the characteristic
    {
	return x;
    } // Returns a characteristic value of x - It is a function method that returns a value


    public void SetY (int y1)  //Communicators - setting a value
    {
	y = y1;
    }


    public int GetY ()
    {
	return y;
    }


    public void SetColour (Color clr1)
    {
	clr = clr1;
    }


    public Color GetColour ()
    {
	return clr;
    }


    public void SetBackground (Color background1)
    {
	background = background1;
    }


    public Color GetBackground ()
    {
	return background;
    }


    public boolean isTouching (int x1, int y1, Console c1)
    {
	if (x1 >= x && x1 <= (x + width) && y1 >= y && y1 <= (y + height))
	{
	    return true;
	}
	return false;
    }


    public void Draw (Console c1)
    {
	c = c1;
	c.setColor (clr);
	int[] xlist = new int [3];
	int[] ylist = new int [3];

	if (Pone == true)
	{
	    xlist [0] = GetX () + GetWidth ();
	    xlist [1] = GetX ();
	    xlist [2] = GetX () + GetWidth ();
	    ylist [0] = GetY ();
	    ylist [1] = GetY () + GetHeight () / 2;
	    ylist [2] = GetY () + GetHeight ();
	    c.fillPolygon (xlist, ylist, 3);
	}
	else
	{
	    xlist [0] = GetX ();
	    xlist [1] = GetX () + GetWidth ();
	    xlist [2] = GetX ();
	    ylist [0] = GetY ();
	    ylist [1] = GetY () + GetHeight () / 2;
	    ylist [2] = GetY () + GetHeight ();
	    c.fillPolygon (xlist, ylist, 3);
	}
    }


    public void Erase (Console c1)
    {
	c = c1;
	c.setColour (background);
	c.fillRect (GetX (), GetY (), GetWidth (), GetHeight ());
    }


    public void Move (Console c1, int x1, int y1)
    {
	c = c1;
	Erase (c1);
	SetX (x1);
	SetY (y1);
	Draw (c1);
    }
} // Paddle class

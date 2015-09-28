// The "Paddle" class.
import java.awt.*;
import hsa.Console;

public class Square
{
    protected int x, y, size, speed;
    protected Color clr, background;
    static Console c;           // The output console
    public Square ()
    {
	x = 0;
	y = 0;
	clr = Color.black;
	background = Color.white;
	size = 50;
	speed = 10;
    }


    public Square (int x1, int y1, Color clr1, int s1, int z1)
    {
	this.x = x1;
	this.y = y1;
	this.clr = clr1;
	this.speed = s1;
	this.size = z1;
	background = Color.white;
    }


    public int Getspeed ()
    {
	return speed;
    }



    public void Setspeed (int s1)
    {
	speed = s1;
    }


    public int GetSize ()
    {
	return size;
    }



    public void SetSize (int z1)
    {
	size = z1;
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
	if (x1 >= x && x1 <= (x + size) && y1 >= y && y1 <= (y + size))
	{
	    return true;
	}
	return false;
    }


    public boolean isTouchingC1 (int x1, int y1, Console c1)
    {
	if (x1 >= x + size - 2 && x1 <= (x + size) && y1 >= y && y1 <= (y + 2))
	{
	    return true;
	}
	return false;
    }


    public boolean isTouchingC2 (int x1, int y1, Console c1)
    {
	if (x1 >= x && x1 <= (x + 2) && y1 >= y && y1 <= (y + 2))
	{
	    return true;
	}
	return false;
    }


    public boolean isTouchingC3 (int x1, int y1, Console c1)
    {
	if (x1 >= x && x1 <= (x + 2) && x1 <= (x + size) && y1 >= y + size - 2 && y1 <= (y + size))
	{
	    return true;
	}
	return false;
    }


    public boolean isTouchingC4 (int x1, int y1, Console c1)
    {
	if (x1 >= x + size - 2 && y1 >= y + size - 2 && y1 <= (y + size))
	{
	    return true;
	}
	return false;
    }


    public boolean isTouchingUp (int x1, int y1, Console c1)
    {
	if (x1 >= x && x1 <= (x + size) && y1 >= y && y1 <= (y + size) / 2)
	{
	    return true;
	}
	return false;
    }


    public boolean isTouchingDown (int x1, int y1, Console c1)
    {
	if (x1 >= x && x1 <= (x + size) && y1 >= (y + size) / 2 && y1 <= (y + size))
	{
	    return true;
	}
	return false;
    }


    public boolean isTouchingLeft (int x1, int y1, Console c1)
    {
	if (x1 >= x && x1 <= (x + size) / 2 && y1 >= y && y1 <= (y + size))
	{
	    return true;
	}
	return false;
    }


    public boolean isTouchingRight (int x1, int y1, Console c1)
    {
	if (x1 >= (x + size) / 2 && x1 <= (x + size) && y1 >= y && y1 <= (y + size))
	{
	    return true;
	}
	return false;
    }


    public void Draw (Console c1)
    {
	c = c1;
	c.setColor (clr);
	c.fillRect (GetX (), GetY (), GetSize (), GetSize ());
	c.setColor (Color.yellow);
	c.fillOval (GetX () + (GetSize () / 4), GetY () + (GetSize () / 4), 15, 15);
	c.fillRect (GetX () + (GetSize () / 4) + 7, GetY () + (GetSize () / 4), 15, 8);
	// c.setColor (Color.white);
	// c.drawString ("Referee", (x + (size) / 2) - 15, (y + (size) / 2) - 5);
    }


    public void Erase (Console c1)
    {
	c = c1;
	c.setColour (background);
	c.fillRect (GetX (), GetY (), GetSize (), GetSize ());
    }


    public void Move (Console c1, int x1, int y1)
    {
	c = c1;
	Erase (c1);
	SetX (x1);
	SetY (y1);
	Draw (c1);
    }


    // public boolean isTouching (int x1, int y1, Console c1)
    // {
    //     if (x1 >= x && x1 <= (x + width) && y1 >= y && y1 <= (y + height))
    //     {
    //         return true;
    //     }
    //     return false;
    // }
}


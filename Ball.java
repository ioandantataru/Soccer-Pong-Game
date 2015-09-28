// The "Ball" class.
import java.awt.*;
import hsa.Console;

public class Ball
{

    //Characteristics of the object

    protected int x, y, radius, score, speed; // Variables
    protected Color clr, background;
    protected Console c;

    public Ball ()  // Constructor method where values are being initialized
    {
	x = 320;
	y = 240;
	radius = 100;
	clr = Color.black;
	background = Color.white;
	score = 0;
	speed = 1;
    } // Constructor method


    public Ball (int x1, int y1, int rad1, Color clr1, int speed1)
	//Method overloading - 2 methods with the same name but has either
	// Different parameters or number of parameters
    {
	this.x = x1;
	this.y = y1;
	this.radius = rad1;
	this.clr = clr1;
	this.speed = speed1;
	background = Color.white;
    } // Another Constructor method - Method overloading


    //Communicators - to set the values and to get the values of characteristics

    public void SetX (int x1)  // Sets the characteristics - Methods
    {
	x = x1;
    }


    public int GetX ()  // returns the characteristic
    {
	return x;
    } // Returns a characteristic value of x - It is a function method that returns a value




    public int GetXRight ()
    {
	return x + radius;
    }


    public int GetXLeft ()
    {
	return x - radius;
    }


    public void SetY (int y1)  //Communicators - setting a value
    {
	y = y1;
    }


    public int GetY ()
    {
	return y;
    }





    public int GetYDown ()
    {
	return y + radius;
    }


    public int GetYUp ()
    {
	return y - radius;
    }


    public void SetRadius (int rad1)
    {
	radius = rad1;
    }


    public int GetRadius ()
    {
	return radius;
    }


    public void SetColour (Color clr1)
    {
	clr = clr1;
    }


    public Color GetColour ()
    {
	return clr;
    }


    public void Setspeed (int speed1)
    {
	speed = speed1;
    }


    public int Getspeed ()
    {
	return speed;
    }


    public void SetBackground (Color background1)
    {
	background = background1;
    }


    public Color GetBackground ()
    {
	return background;
    }


    //Action

    public void Draw (Console c1)  // Example of an action
    {
	c = c1;
	int x0 = x - radius;
	int y0 = y - radius;
	c.setColor (clr);
	c.fillOval (x0, y0, 2 * radius, 2 * radius);
	c.setColor (Color.black);
	c.drawOval (x0, y0, 2 * radius - 1, 2 * radius - 1);
	c.drawLine (x - 10, y - 15, x - 10, y + 15);
	c.drawLine (x, y - radius, x, y + radius - 1);
	c.drawLine (x + 10, y - 15, x + 10, y + 15);
	c.drawLine (x - radius, y, x + radius - 1, y);
	c.drawLine (x - 15, y - (radius / 2), x + 15, y - (radius / 2));
	c.drawLine (x - 15, y + (radius / 2), x + 15, y + (radius / 2));
    }


    public void Erase (Console c1)
    {
	c = c1;
	int x0 = x - radius;
	int y0 = y - radius;
	c.setColor (background);
	c.fillOval (x0, y0, 2 * radius, 2 * radius);
    }


    public void Move (Console c1, int x1, int y1)
    {
	c = c1;
	Erase (c1);
	SetX (x1);
	SetY (y1);
	// if (isTouchingRight (x1, y1, c1))
	// {
	//     SetX (1280 - x1);
	// }
	// else if (isTouchingLeft (x1, y1, c1))
	// {
	//     SetX (-x1);
	// }
	Draw (c1);
    }


    public boolean isTouchingDown (Console c1, int y1)
    {
	if (y1 <= GetY () + GetRadius ())
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public boolean isTouchingUp (Console c1, int y1)
    {
	if (y1 >= GetY () - GetRadius ())
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public boolean isTouchingRight (int x1, Console c1)
    {
	if (x1 <= GetX () + GetRadius ())
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public boolean isTouchingLeft (int x1, Console c1)
    {
	if (x1 >= GetX () - GetRadius ())
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }
} // Ball class


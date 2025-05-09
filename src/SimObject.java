import java.awt.Color;
import javax.swing.JLabel;

public class SimObject {
    private JLabel label;
    private int x;
    private int y;
    private int xScale;
    private int yScale;
    private Color color;
    private boolean isShowing = true;

    public void setLabel(JLabel _label)
    {
        this.label = _label;
    }

    public JLabel getLabel()
    {
        return this.label;
    }

    public void setX(int _x)
    {
        this.x = _x;
    }

    public int getX()
    {
        return this.x;
    }

    public void setY(int _y)
    {
        this.y = _y;
    }

    public int getY()
    {
        return this.y;
    }

    public void setXScale(int _xScale)
    {
        this.xScale = _xScale;
    }
    
    public int getXScale()
    {
        return this.xScale;
    }

    public void setYScale(int _yScale)
    {
        this.yScale = _yScale;
    }

    public int getYScale()
    {
        return this.yScale;
    }

    public void setColor(Color _color)
    {
        this.color = _color;
    }

    public Color getColor()
    {
        return this.color;
    }

    public void setIsShowing(boolean _isShowing)
    {
        this.isShowing = _isShowing;
    }

    public boolean getIsShowing()
    {
        return this.isShowing;
    }
    
    public void gameTick()
    {

    }
}

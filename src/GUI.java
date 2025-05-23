import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame{
    GUI(int _xSize, int _ySize)
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(_xSize, _ySize);
        this.setVisible(true);
        this.setTitle("Train Simulator");
        this.setLayout(null);
    }

    public JLabel createObject(int _x, int _y, int _xScale, int _yScale, Color _color)
    {
        // This is for the display, since it doesn't support negative width
        if (_xScale < 0)
        {
            _x += _xScale;
            _xScale = -_xScale;
        }
        if (_yScale < 0)
        {
            _y += _yScale;
            _yScale = -_yScale;
        }
        JLabel _label = new JLabel();
        System.out.println("Labels: x: " + _x + ", y: " + _y + ", width: " + _xScale + ", height: " + _yScale);
        _label.setBounds(_x, _y, _xScale, _yScale);
        _label.setOpaque(true);
        _label.setBackground(_color);
        _label.setVisible(true);
        this.add(_label);
        this.revalidate();
        this.repaint();
        return _label;
    }

    public JLabel updateComponent(JLabel _label, int _x, int _y, int _xScale, int _yScale, boolean _isShowing)
    {   
        _label.setBounds(_x, _y, _xScale, _yScale);
        _label.setVisible(_isShowing);
        this.revalidate();
        this.repaint();
        return _label;
    }

    public void hideComponent(JLabel _label)
    {
        _label.setVisible(false);
    }

    public void showComponent(JLabel _label)
    {
        _label.setVisible(true);
    }
}

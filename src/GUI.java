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
        // THIS DOESNT WORK FOR TRAINS AND STATIONS
        int _displayX = Math.min(_x, _x + _xScale);
        int _displayY = Math.min(_y, _y + _yScale);
        int _width = Math.abs(_xScale);
        int _height = Math.abs(_yScale);

        System.out.println("Labels: x: " + _displayX + ", y: " + _displayY + ", width: " + _width + ", height: " + _height);

        JLabel _label = new JLabel();
        _label.setBounds(_displayX, _displayY, _width, _height);
        _label.setOpaque(true);
        _label.setBackground(_color);
        _label.setVisible(true);
        this.add(_label);
        this.revalidate();
        this.repaint();
        return _label;
    }

    public JLabel updateComponent(JLabel _label, int _x, int _y, int _xScale, int _yScale)
    {
        int _displayX = Math.min(_x, _x + _xScale);
        int _displayY = Math.min(_y, _y + _yScale);
        int _width = Math.abs(_xScale);
        int _height = Math.abs(_yScale);
        
        _label.setBounds(_displayX, _displayY, _width, _height);
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

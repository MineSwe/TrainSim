import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
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

    public JComboBox createComboBox(int _x, int _y, int _xScale, int _yScale, int _min, int _max, ActionListener _actionListener)
    {
        int _counterSize = _max-_min;
        String[] _counter = new String[_counterSize];
        for(int i = 0; i < _counterSize; i++)
        {
            _counter[i] = String.valueOf(_min + i);
        }
        JComboBox _comboBox = new JComboBox(_counter);
        _comboBox.addActionListener(_actionListener);
        _comboBox.setBounds(_x, _y, _xScale, _yScale);
        _comboBox.setOpaque(true);
        _comboBox.setVisible(true);
        this.add(_comboBox);
        this.revalidate();
        this.repaint();
        return _comboBox;
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
        _label.setBounds(_x, _y, _xScale, _yScale);
        _label.setVisible(_isShowing);
        this.revalidate();
        this.repaint();
        return _label;
    }
}
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CustomisedTextPanel extends JTextPane
{
	private static final long serialVersionUID = 1L;
	StyledDocument doc = getStyledDocument();
    SimpleAttributeSet left = new SimpleAttributeSet();
    SimpleAttributeSet right = new SimpleAttributeSet();
    
    public CustomisedTextPanel()
    {
    	StyleConstants.setForeground(left, Color.BLUE);
	    StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
	    
	    StyleConstants.setForeground(right, Color.RED);
	    StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
	    setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
	    setMargin(new Insets(10, 20, 10, 20));
	    setEditable(false);
	    setOpaque(false);
	    setBackground(getBackground());
    }
    
    public void recievedMessage(String str) 
    {
	    try
	    {
	        doc.insertString(doc.getLength(), str, left );
	        doc.setParagraphAttributes(doc.getLength(), 1, left, false);
	    }
	    catch(Exception e) { }
    }
    
    public void myMessage(String str)
    {
    	try {
	        doc.insertString(doc.getLength(), str, right );
	        doc.setParagraphAttributes(doc.getLength(), 1, right, false);
    	}catch(Exception e) {}
    }

    
}

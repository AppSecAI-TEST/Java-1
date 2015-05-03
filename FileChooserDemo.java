import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


//--------------------------------------------------------------------------------------------//
@SuppressWarnings("serial")
public class ReadAndParseFile extends JPanel
                             implements ActionListener 
{
	ArrayList<String> entire = new ArrayList<String>();
	ArrayList<Segment> segments = new ArrayList<Segment>();
    static private final String newline = "\n";
    JButton openButton;
    JButton btnSearch;
    JFileChooser fc;
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".EXP Files", "exp", "exp");

    JEditorPane txtpnabc;
    private JTextField textField;
    private JLabel lblTag;
    private JTextField textField_1;
	private JScrollBar bar;
    private static File file;
    private String tag;
    private static final String seg = "<Segment>";
	private static final String src = "<Source>";
	private static final String  trg = "<Target>";
	Segment tmpSeg = new Segment();
	int segmentCount = 0;
    BufferedReader br;
    private final JScrollPane scrollPane = new JScrollPane();


// --------------------------------------------------------------------------------------------//
    public ReadAndParseFile()  // Constructor
    {
 
        //Create a file chooser
        fc = new JFileChooser();
        fc.setFileFilter(filter);
        setLayout(null);
 

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(12, 14, 773, 85);
        buttonPanel.setLayout(null);
 
        //Add the buttons and the log to this panel.
        add(buttonPanel);
        
        JLabel lblFile = new JLabel("File");
        lblFile.setBounds(22, 4, 36, 20);
        buttonPanel.add(lblFile);
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(81, 6, 512, 17);
        buttonPanel.add(textField);
        textField.setColumns(10);
	        
        openButton = new JButton("Open a File...");
        openButton.setBounds(622, 4, 109, 23);
		buttonPanel.add(openButton);
		 
		lblTag = new JLabel("Tag");
		lblTag.setBounds(22, 50, 36, 19);
		buttonPanel.add(lblTag);
		                       
		textField_1 = new JTextField();
		textField_1.setBounds(83, 49, 510, 20);
		buttonPanel.add(textField_1);
		textField_1.setColumns(10);
		                       
		btnSearch = new JButton("Search");
		btnSearch.setBounds(622, 49, 109, 23);
		buttonPanel.add(btnSearch);
		                       
		openButton.addActionListener(this);
		btnSearch.addActionListener(this);
		                       
		JPanel panel = new JPanel();
		panel.setBounds(12, 137, 1029, 278);
		add(panel);
		panel.setLayout(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 1029, 278);
		panel.add(scrollPane);
		
		txtpnabc = new JEditorPane();
		txtpnabc.setContentType("text/html");
		txtpnabc.setText("dd");
		scrollPane.setViewportView(txtpnabc);
		                       
		JLabel lblResult = new JLabel("Result :");
		lblResult.setBounds(22, 109, 57, 15);
	    add(lblResult);        
    }
 // --------------------------------------------------------------------------------------------//
    public void actionPerformed(ActionEvent e) 
    {
        /*StyledDocument doc = txtpnabc.getStyledDocument();
        
        Style style = txtpnabc.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.red);
        Style style_2 = txtpnabc.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style_2, Color.black);
        */
        //Handle open button action.
        if (e.getSource() == openButton)
        {
            int returnVal = fc.showOpenDialog(ReadAndParseFile.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                file = fc.getSelectedFile();         
                //This is where a real application would open the file.
                textField.setText(file.getName());                
                
                try
                {
                	
                	
            		br = new BufferedReader(new InputStreamReader
            				(new FileInputStream(file.getPath()), Charset.forName("UTF-16")));
            		 
                		String line = br.readLine();
                		while( line != null )
                		{
                			entire.add(line);
                			line = br.readLine();
                		}
                		findSegment(entire);
                }
                catch(Exception error) { error.printStackTrace(); } 
            } 
            else 
            {
            	textField.setText("File Choose Cancelled by user");
            }
        }
        else if(e.getSource() == btnSearch)
        {
        	try
        	{
        		int i = 0;
        		txtpnabc.setText("");
        		tag = textField_1.getText();
        		String _tag = "<" + tag;
        		
        		while(i < segments.size() )
        		{
        			if( segments.get(i).getSource().indexOf(_tag)!= -1 && segments.get(i).getTarget().indexOf(_tag) == -1)
        			{	
        				// tag in source, but not in target
        				appendString( segments.get(i).getSegment() + "\n");
        				appendString( "--" + "Source : "+ segments.get(i).getSource() + "\n");
        				appendString( "-" + "Target :" + segments.get(i).getTarget() + "\n\n");
        				
        			}
        			else if( segments.get(i).getTarget().indexOf(_tag)!= -1 && segments.get(i).getSource().indexOf(_tag) == -1)
        			{
        				// tag in target, but not in source
        				appendString( segments.get(i).getSegment() + "\n");
        				appendString( "-" + "Source : "+ segments.get(i).getSource() + "\n");
        				appendString( "--" + "Target :" + segments.get(i).getTarget() + "\n\n");
        			}
        			else if( segments.get(i).getSource().indexOf(_tag)!= -1 &&
        					segments.get(i).getTarget().indexOf(_tag)!= -1)
        			{
        				// tag in both source and target
        				appendString( segments.get(i).getSegment() + "\n");
        				appendString( "--" + "Source :" + segments.get(i).getSource() + "\n");
        				appendString( "--" + "Target :" + segments.get(i).getTarget() + "\n\n");
        			}
        			
        			i++;
        		}
        	}
        	catch( Exception error ) {error.printStackTrace();}
        }
    }
// --------------------------------------------------------------------------------------------//
    public void appendString(String str) throws Exception
    {
         StyledDocument document = (StyledDocument) txtpnabc.getDocument();
         document.insertString(document.getLength(), str, null);
     }
 // --------------------------------------------------------------------------------------------//
    private static void createAndShowGUI() {
        //Create and set up the window.'
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.getContentPane().add(new ReadAndParseFile());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
// --------------------------------------------------------------------------------------------//
void findSegment(ArrayList<String> entire) throws Exception 
	{
		Segment tempSeg = new Segment();
		StringBuilder sb = new StringBuilder();
		String temp;
		
		for (int i=0; i < entire.size(); i++)
		{
			if( entire.get(i).indexOf(seg) != -1 ) // a line with 'segment' found
			{
				tempSeg.setSegment( entire.get(i).substring(9));
				//find source and target
			}
			if( entire.get(i).indexOf(src) != -1 )
			{
				if( entire.get(i).indexOf("</Source>") != -1)
				{
					tempSeg.setSource( entire.get(i).substring(
							8, entire.get(i).indexOf("</Source>")));
				}
				else
				{
					int j = i;
					while(true)
					{
						if (entire.get(j).indexOf("</Source>") == -1)
						{
							sb.append(entire.get(j));
						}
						else
						{
							sb.append(entire.get(j));
							break;
						}
						j++;
					}
					temp = sb.toString();
					tempSeg.setSource( temp.substring(8, temp.indexOf("</Source>")));
				}
			}
			if( entire.get(i).indexOf(trg) != -1 )
			{
				if( entire.get(i).indexOf("</Target>") != -1)
				{
					tempSeg.setTarget( entire.get(i).substring(
							8, entire.get(i).indexOf("</Target>")));
				}
				else
				{
					int j = i;
					while(true)
					{
						if (entire.get(j).indexOf("</Target>") == -1)
						{
							sb.append(entire.get(j));
						}
						else
						{
							sb.append(entire.get(j));
							break;
						}
						j++;
					}
					temp = sb.toString();
					tempSeg.setTarget( temp.substring(8, temp.indexOf("</Target>")));
				}
				segments.add(tempSeg);
				tempSeg = new Segment();
				segments.get(segmentCount).printSegment();
				segmentCount++;
			}
			
		}
		
	}
// --------------------------------------------------------------------------------------------//
void findTag(String line, String tag)
{
	String left = "<" + tag;
	String right = "</" + tag;
	
	//left = Colouring( line.substring()  )
}
//--------------------------------------------------------------------------------------------//
String Colouring(String s)
{
	return "<html><font color='red'>" + s + "</font></html>";
}

//--------------------------------------------------------------------------------------------//
    public static void main(String[] args) 
    {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
            		//Windows UI
                    UIManager.setLookAndFeel(
            	            UIManager.getCrossPlatformLookAndFeelClassName());
                    createAndShowGUI();
            	} catch(Exception e) {
            		e.printStackTrace();
            	}
            }
        });
    }
// --------------------------------------------------------------------------------------------//
}
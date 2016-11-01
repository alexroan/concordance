import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
 * 
 * @author alr16
 * User interface enabling human interaction with concordance application
 */

@SuppressWarnings("serial")
public class UserInterface extends JFrame implements ActionListener{

	JFileChooser chooser;
	HashTable controller;
	
	JPanel row1 = new JPanel();
	JLabel bookLabel = new JLabel("Book:", JLabel.LEFT);
	JTextField enterBook = new JTextField(28);
	JButton confirmBook = new JButton("Find Book");
	JButton clearAll = new JButton("Clear All");
	
	JPanel row2 = new JPanel();
	JLabel indexLabel = new JLabel("Index:", JLabel.LEFT);
	JTextField enterIndex = new JTextField(28);
	JButton confirmIndex = new JButton("Find Index");
	JButton generateLineNumbers = new JButton("Generate");
	
	JPanel row4 = new JPanel();
	JTextArea output = new JTextArea(30,52);
	JScrollPane scroll = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	
	/**
	 * Creates a new user interface window and instanciates a new HashTable
	 */
	public UserInterface(){		
		super("Concordance!");
		this.setSize(620, 620);
		this.setResizable(false);
		this.setLocation(50, 50);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		this.setLayout(layout);
		
		controller = new HashTable();

		
		confirmBook.addActionListener(this);
		clearAll.addActionListener(this);
		row1.add(bookLabel);
		row1.add(enterBook);
		row1.add(confirmBook);
		row1.add(clearAll);
		clearAll.setVisible(false);
		this.add(row1);
		
		confirmIndex.addActionListener(this);
		row2.add(indexLabel);
		row2.add(enterIndex);
		row2.add(confirmIndex);
		confirmIndex.setVisible(false);
		row2.add(generateLineNumbers);
		generateLineNumbers.addActionListener(this);
		generateLineNumbers.setVisible(false);
		this.add(row2);
		
		
		
		row4.add(scroll);
		this.add(row4);
		
		this.setVisible(true);
	}
	
	
	/**
	 * recognises when buttons are clicked and reponds in appropriate way
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(false);		
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equals(this.generateLineNumbers.getActionCommand())){
			long init = System.currentTimeMillis();
			controller.readInIndexFile(this.enterIndex.getText());
			controller.setBook(this.enterBook.getText());
			controller.generateLineNumbers();
			
			this.output.setText(controller.toString());
			long ending = System.currentTimeMillis();
			float timeTaken = (float)(ending - init)/1000;
			this.output.setText(this.output.getText() + 
					"---------------------------------------------------------------------" +
					"\n time taken to calculate and display: " + timeTaken + "seconds");
			this.clearAll.setVisible(true);
			this.confirmBook.setEnabled(false);
			this.confirmIndex.setEnabled(false);
			this.generateLineNumbers.setEnabled(false);
		}
		else if (e.getActionCommand().equals(this.clearAll.getActionCommand())){
			controller.clearAll();
			this.enterBook.setText("");
			this.enterIndex.setText("");
			this.output.setText("");
			this.confirmIndex.setVisible(false);
			this.generateLineNumbers.setVisible(false);
			this.clearAll.setVisible(false);
			this.confirmBook.setEnabled(true);
			this.confirmIndex.setEnabled(true);
			this.generateLineNumbers.setEnabled(true);
		}
		else if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			String fileName = file.getAbsolutePath();
			if (e.getActionCommand().equals(this.confirmBook.getActionCommand())){ 
				this.enterBook.setText(fileName);
				this.confirmIndex.setVisible(true);
			}
			else if (e.getActionCommand().equals(this.confirmIndex.getActionCommand())){
				this.enterIndex.setText(fileName);
				this.generateLineNumbers.setVisible(true);
			}
		}
	}
}

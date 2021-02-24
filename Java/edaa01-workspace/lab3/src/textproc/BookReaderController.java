package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import javax.swing.*;

public class BookReaderController {
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 1200, 800));
	}
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(counter.getWordList());
		JList<Map.Entry<String, Integer>> list = new JList<>(listModel);
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		JPanel bottomPanel = new JPanel();
		
		JButton button1 = new JButton("Alphabetic");
		JButton button2 = new JButton("Frequency");
		button1.addActionListener(e -> listModel.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey())));		
		button2.addActionListener(e -> listModel.sort((e1, e2) -> e2.getValue() - e1.getValue()));
		
		JButton button3 = new JButton("Find");
		// frame.getRootPane().setDefaultButton(button3);
		
		JTextField textField1 = new JTextField("", 30);
		textField1.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent event) {
				if(event.getKeyCode() == 10) {
					button3.doClick();
				}
			}
			
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
		});
		
		button3.addActionListener(e -> {
			String searchQuery = textField1.getText().trim().strip().toLowerCase();
			int index = -1;
			
			for(int i = 0; i < listModel.getSize(); i++) {
				if(listModel.getElementAt(i).getKey().compareTo(searchQuery) == 0) {
					index = i;
					break;
				}
			}
			
			if(index >= 0) {
				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index);
			} else {
				JOptionPane.showMessageDialog(frame, "'" + textField1.getText() + "' could not be found.");
			}
		});
		
		bottomPanel.add(button1);
		bottomPanel.add(button2);
		bottomPanel.add(textField1);
		bottomPanel.add(button3);
		
		// pane är en behållarkomponent till vilken de övriga komponenterna
		pane.add(bottomPanel, BorderLayout.SOUTH);
		pane.add(scrollPane, BorderLayout.CENTER);
		
		frame.pack();
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}
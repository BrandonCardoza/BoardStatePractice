import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BoardStatePanel extends JPanel{
	
	private JComboBox<String> deckChoices;
	private JPanel infoPanel, deck1CardsPanel, deck2CardsPanel, dummyPanel, deck1Creatures, deck1ArtifactsAndEnchantments, deck1Planeswalkers,
	deck2Creatures, deck2ArtifactsAndEnchantments, deck2Planeswalkers, deckEntryPanel, deck3CardsPanel, deck4CardsPanel,
	deck3Creatures, deck3ArtifactsAndEnchantments, deck3Planeswalkers, deck4Creatures, deck4ArtifactsAndEnchantments, deck4Planeswalkers;
	private Deck[] listOfDecks;
	private Random rand;
	private JButton refreshButton, addDeckButton, addCardsButton;
	private ButtonListener buttonListener;
	private DeckChangeListener itemChangeListener;
	private Deck currentDeck;
	private JTextField deckTextField;
	private JScrollPane scroller1_1, scroller1_2, scroller1_3, scroller2_1, scroller2_2, scroller2_3, scroller3_1, scroller3_2, scroller3_3,
	scroller4_1, scroller4_2, scroller4_3;
	private JTextArea lifeTotals;
	private int yourLife, opponent1Life, opponent2Life, opponent3Life;
	
	
	public BoardStatePanel()
	{
		itemChangeListener = new DeckChangeListener();
		deckChoices = new JComboBox<String>();
		deckChoices.addActionListener(itemChangeListener);
		listOfDecks = new Deck[4];
		setUpBoard();
	}
	
	public void setUpBoard()
	{
		this.setPreferredSize(new Dimension(1450, 800));		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		
		rand = new Random();
		
		
		dummyPanel = new JPanel();
		dummyPanel.setLayout(new BoxLayout(dummyPanel, BoxLayout.Y_AXIS));
		deck1CardsPanel = new JPanel();
		deck1CardsPanel.setLayout(new BoxLayout(deck1CardsPanel, BoxLayout.Y_AXIS));
		deck2CardsPanel = new JPanel();
		deck2CardsPanel.setLayout(new BoxLayout(deck2CardsPanel, BoxLayout.Y_AXIS));
		deck3CardsPanel = new JPanel();
		deck3CardsPanel.setLayout(new BoxLayout(deck3CardsPanel, BoxLayout.Y_AXIS));
		deck4CardsPanel = new JPanel();
		deck4CardsPanel.setLayout(new BoxLayout(deck4CardsPanel, BoxLayout.Y_AXIS));
		
		
		String currentDeckName = (String) deckChoices.getSelectedItem();
		
		if(listOfDecks[0] != null)
		{
			for(Deck d: listOfDecks)
	        {
				if(d != null)
				{
					if(d.getDeckName().equals(currentDeckName))
			      	  {
			      		  currentDeck = d;
			      	  }
				}
	      	  
	        }
		}
		
		
		
		this.add(deck1CardsPanel, BorderLayout.CENTER);
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(deckChoices, BorderLayout.NORTH);
		this.add(infoPanel, BorderLayout.EAST);
		
		deckEntryPanel = new JPanel();
		deckEntryPanel.setLayout(new BoxLayout(deckEntryPanel, BoxLayout.Y_AXIS));
		deckEntryPanel.setBorder(BorderFactory.createEmptyBorder(300, 0, 300, 0));
		deckTextField = new JTextField();
		
		buttonListener = new ButtonListener();
		addDeckButton = new JButton("Add Deck");
		addDeckButton.addActionListener(buttonListener);
		addCardsButton = new JButton("Add Cards");
		addCardsButton.addActionListener(buttonListener);
		deckEntryPanel.add(addDeckButton);
		deckEntryPanel.add(deckTextField);
		deckEntryPanel.add(addCardsButton);
		
		infoPanel.add(deckEntryPanel,BorderLayout.CENTER);
		
		yourLife = rand.nextInt(40) + 1;
		opponent1Life = rand.nextInt(40) + 1;
		opponent2Life = rand.nextInt(40) + 1;
		opponent3Life = rand.nextInt(40) + 1;
		
		lifeTotals = new JTextArea(2, 40);
		Font font1 = new Font("SansSerif", Font.PLAIN, 24);
		lifeTotals.setEditable(false);
		lifeTotals.setFont(font1);
		lifeTotals.insert("            Your life: " + yourLife + "\tOpponent 1 Life: " + opponent1Life
				+ "\tOpponent 2 Life: " + opponent2Life + "\tOpponent 3 Life: " + opponent3Life, 0);
		
		this.add(lifeTotals, BorderLayout.NORTH);
		
		deck1Creatures = new JPanel();
		deck1Creatures.setLayout(new BoxLayout(deck1Creatures, BoxLayout.X_AXIS));
		scroller1_1 = new JScrollPane(deck1Creatures, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deck1ArtifactsAndEnchantments = new JPanel();
		deck1ArtifactsAndEnchantments.setLayout(new BoxLayout(deck1ArtifactsAndEnchantments, BoxLayout.X_AXIS));
		scroller1_2 = new JScrollPane(deck1ArtifactsAndEnchantments, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deck1Planeswalkers = new JPanel();
		deck1Planeswalkers.setLayout(new BoxLayout(deck1Planeswalkers, BoxLayout.X_AXIS));
		scroller1_3 = new JScrollPane(deck1Planeswalkers, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		deck2Creatures = new JPanel();
		deck2Creatures.setLayout(new BoxLayout(deck2Creatures, BoxLayout.X_AXIS));
		scroller2_1 = new JScrollPane(deck2Creatures, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deck2ArtifactsAndEnchantments = new JPanel();
		deck2ArtifactsAndEnchantments.setLayout(new BoxLayout(deck2ArtifactsAndEnchantments, BoxLayout.X_AXIS));
		scroller2_2 = new JScrollPane(deck2ArtifactsAndEnchantments, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deck2Planeswalkers = new JPanel();
		deck2Planeswalkers.setLayout(new BoxLayout(deck2Planeswalkers, BoxLayout.X_AXIS));
		scroller2_3 = new JScrollPane(deck2Planeswalkers, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		deck3Creatures = new JPanel();
		deck3Creatures.setLayout(new BoxLayout(deck3Creatures, BoxLayout.X_AXIS));
		scroller3_1 = new JScrollPane(deck3Creatures, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deck3ArtifactsAndEnchantments = new JPanel();
		deck3ArtifactsAndEnchantments.setLayout(new BoxLayout(deck3ArtifactsAndEnchantments, BoxLayout.X_AXIS));
		scroller3_2 = new JScrollPane(deck3ArtifactsAndEnchantments, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deck3Planeswalkers = new JPanel();
		deck3Planeswalkers.setLayout(new BoxLayout(deck3Planeswalkers, BoxLayout.X_AXIS));
		scroller3_3 = new JScrollPane(deck3Planeswalkers, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		deck4Creatures = new JPanel();
		deck4Creatures.setLayout(new BoxLayout(deck4Creatures, BoxLayout.X_AXIS));
		scroller4_1 = new JScrollPane(deck4Creatures, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deck4ArtifactsAndEnchantments = new JPanel();
		deck4ArtifactsAndEnchantments.setLayout(new BoxLayout(deck4ArtifactsAndEnchantments, BoxLayout.X_AXIS));
		scroller4_2 = new JScrollPane(deck4ArtifactsAndEnchantments, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deck4Planeswalkers = new JPanel();
		deck4Planeswalkers.setLayout(new BoxLayout(deck4Planeswalkers, BoxLayout.X_AXIS));
		scroller4_3 = new JScrollPane(deck4Planeswalkers, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		deck1CardsPanel.add(scroller1_3);
		deck1CardsPanel.add(scroller1_1);
		deck1CardsPanel.add(scroller1_2);
		
		deck2CardsPanel.add(scroller2_3);
		deck2CardsPanel.add(scroller2_1);
		deck2CardsPanel.add(scroller2_2);
		
		deck3CardsPanel.add(scroller3_3);
		deck3CardsPanel.add(scroller3_1);
		deck3CardsPanel.add(scroller3_2);
		
		deck4CardsPanel.add(scroller4_3);
		deck4CardsPanel.add(scroller4_1);
		deck4CardsPanel.add(scroller4_2);
		
		
		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(buttonListener);
		infoPanel.add(refreshButton, BorderLayout.SOUTH);
	}
	
	public void addCreatureCards(Deck deck)
	{
		for(int i = 0; i < deck.getNumCreatures(); i++)
		{
			int j = 1;
			while(listOfDecks[j-1] != deck)
			{
				j++;
			}
			switch(j)
			{
			case 1:
				deck1Creatures.add(deck.creatures[i]);
				break;
			case 2:
				deck2Creatures.add(deck.creatures[i]);
				break;
			case 3:
				deck3Creatures.add(deck.creatures[i]);
				break;
			case 4:
				deck4Creatures.add(deck.creatures[i]);
				break;
			}
		}
		
	}
	
	public void addPlaneswalkerCards(Deck deck)
	{
		for(int i = 0; i < deck.getNumPlaneswalkers(); i++)
		{
			int j = 1;
			while(listOfDecks[j-1] != deck)
			{
				j++;
			}
			switch(j)
			{
			case 1:
				deck1Planeswalkers.add(deck.planeswalkers[i]);
				break;
			case 2:
				deck2Planeswalkers.add(deck.planeswalkers[i]);
				break;
			case 3:
				deck3Planeswalkers.add(deck.planeswalkers[i]);
				break;
			case 4:
				deck4Planeswalkers.add(deck.planeswalkers[i]);
				break;
			}
		}
		
	}
	
	public void addArtifactAndEnchantmentCards(Deck deck)
	{
		for(int i = 0; i < deck.getNumArtifacts(); i++)
		{
			int j = 1;
			while(listOfDecks[j-1] != deck)
			{
				j++;
			}
			switch(j)
			{
			case 1:
				deck1ArtifactsAndEnchantments.add(deck.artifacts[i]);
				break;
			case 2:
				deck2ArtifactsAndEnchantments.add(deck.artifacts[i]);
				break;
			case 3:
				deck3ArtifactsAndEnchantments.add(deck.artifacts[i]);
				break;
			case 4:
				deck4ArtifactsAndEnchantments.add(deck.artifacts[i]);
				break;
			}
		}
		for(int i = 0; i < deck.getNumEnchantments(); i++)
		{
			int j = 1;
			while(listOfDecks[j-1] != deck)
			{
				j++;
			}
			switch(j)
			{
			case 1:
				deck1ArtifactsAndEnchantments.add(deck.enchantments[i]);
				break;
			case 2:
				deck2ArtifactsAndEnchantments.add(deck.enchantments[i]);
				break;
			case 3:
				deck3ArtifactsAndEnchantments.add(deck.enchantments[i]);
				break;
			case 4:
				deck4ArtifactsAndEnchantments.add(deck.enchantments[i]);
				break;
			}
		}
		
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == refreshButton)
			{			
				int j = 1;
				int i = 1;
				while(listOfDecks[j-1] != null && i < 5)
				{
					switch(j)
					{
					case 1:
						listOfDecks[j-1].removeCards();
						deck1Creatures.removeAll();
						deck1ArtifactsAndEnchantments.removeAll();
						deck1Planeswalkers.removeAll();
						listOfDecks[j-1].createCards();
						revalidate();
						repaint();
						i++;
						j++;
						break;
					case 2:
						listOfDecks[j-1].removeCards();
						deck2Creatures.removeAll();
						deck2ArtifactsAndEnchantments.removeAll();
						deck2Planeswalkers.removeAll();
						listOfDecks[j-1].createCards();
						revalidate();
						repaint();
						j++;
						i++;
						break;
					case 3:
						listOfDecks[j-1].removeCards();
						deck3Creatures.removeAll();
						deck3ArtifactsAndEnchantments.removeAll();
						deck3Planeswalkers.removeAll();
						listOfDecks[j-1].createCards();
						revalidate();
						repaint();
						j++;
						i++;
						break;
					case 4:
						listOfDecks[j-1].removeCards();
						deck4Creatures.removeAll();
						deck4ArtifactsAndEnchantments.removeAll();
						deck4Planeswalkers.removeAll();
						listOfDecks[j-1].createCards();
						revalidate();
						repaint();
						i++;
						break;
					}
				}
				
				String currentDeckName = (String) deckChoices.getSelectedItem();
				for(Deck d: listOfDecks)
		        {
					if(d != null)
					{
						if(d.getDeckName().equals(currentDeckName))
				      	  {
				      		  currentDeck = d;
				      	  }
					}
		      	  
		        }
				
				addCreatureCards(currentDeck);
				addArtifactAndEnchantmentCards(currentDeck);
				addPlaneswalkerCards(currentDeck);
				add(currentDeck.getAssignedPanel(),BorderLayout.CENTER);
				
				yourLife = rand.nextInt(40) + 1;
				opponent1Life = rand.nextInt(40) + 1;
				opponent2Life = rand.nextInt(40) + 1;
				opponent3Life = rand.nextInt(40) + 1;
				lifeTotals.replaceRange("            Your life: " + yourLife + "\tOpponent 1 Life: " + opponent1Life
						+ "\tOpponent 2 Life: " + opponent2Life + "\tOpponent 3 Life: " + opponent3Life, 0, lifeTotals.getText().length());
				
				revalidate();
				repaint();
			}
			
			if(e.getSource() == addDeckButton)
			{
				String deckToAddName = deckTextField.getText();
				Deck newDeck = null;
				int i = 1;
				while(listOfDecks[i-1] != null && i < 4)
				{
					i++;
				}
				switch(i)
				{
				case 1:
					newDeck = new Deck(deckToAddName, deck1CardsPanel);
					break;
				case 2:
					newDeck = new Deck(deckToAddName, deck2CardsPanel);
					break;
				case 3:
					newDeck = new Deck(deckToAddName, deck3CardsPanel);
					break;
				case 4:
					newDeck = new Deck(deckToAddName, deck4CardsPanel);
					break;
				}
				deckChoices.addItem(newDeck.getDeckName());
				deckTextField.setText("");
				listOfDecks[i-1] = newDeck;
				deckChoices.setSelectedItem(deckToAddName);
				revalidate();
				repaint();
				
			}
			if(e.getSource() == addCardsButton)
			{
				String deckToAddName = (String) deckChoices.getSelectedItem();
				Deck newDeck = null;

				for(Deck d: listOfDecks)
		        {
	    			if(d != null)
	    			{
	    				if(d.getDeckName().equals(deckToAddName))
			        	{
			        		  newDeck = d;
			        	}
	    			}
		        }

				newDeck.createCards();
				addCreatureCards(newDeck);
				addArtifactAndEnchantmentCards(newDeck);
				addPlaneswalkerCards(newDeck);
				String currentDeckName = (String) deckChoices.getSelectedItem();
				if(listOfDecks[1] != null)
				{
					for(Deck d: listOfDecks)
			        {
						if(d != null)
						{
							if(d.getDeckName().equals(currentDeckName))
					      	  {
					      		  currentDeck = d;
					      	  }
						}
			      	  
			        }
				}
				deckTextField.setText("");
				revalidate();
				repaint();
				
				
			}
		}
	}
	
	private class DeckChangeListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) 
	    {
	    	Deck newDeck = null;
	    	@SuppressWarnings({ "rawtypes", "unchecked" })
			JComboBox<String> cb = (JComboBox) e.getSource();
	    	String newDeckName = (String) cb.getSelectedItem();
	    	
	    	if(listOfDecks[0] != null)
	    	{
	    		for(Deck d: listOfDecks)
		          {
	    			if(d != null)
	    			{
	    				if(d.getDeckName().equals(newDeckName))
			        	  {
			        		  newDeck = d;
			        	  }
			        	  if(d == currentDeck)
			        	  {
			        		  remove(d.getAssignedPanel());
			        		  int j = 1;
			      			while(listOfDecks[j-1] != currentDeck)
			      			{
			      				j++;
			      			}
			      			switch(j)
			      			{
			      			case 1:
			      				deck1Creatures.removeAll();
				    	        deck1ArtifactsAndEnchantments.removeAll();
				    	        deck1Planeswalkers.removeAll();
				    	        revalidate();
				    	        repaint();
			      				break;
			      			case 2:
			      				deck2Creatures.removeAll();
				    	        deck2ArtifactsAndEnchantments.removeAll();
				    	        deck2Planeswalkers.removeAll();
				    	        revalidate();
				    	        repaint();
			      				break;
			      			case 3:
			      				deck3Creatures.removeAll();
				    	        deck3ArtifactsAndEnchantments.removeAll();
				    	        deck3Planeswalkers.removeAll();
				    	        revalidate();
				    	        repaint();
			      				break;
			      			case 4:
			      				deck4Creatures.removeAll();
				    	        deck4ArtifactsAndEnchantments.removeAll();
				    	        deck4Planeswalkers.removeAll();
				    	        revalidate();
				    	        repaint();
			      				break;
			      			}
			    	          
			        	  }
	    			}
		        	  
		          }
	    	
	          
	          add(newDeck.getAssignedPanel(), BorderLayout.CENTER);
	          addCreatureCards(newDeck);
	          addArtifactAndEnchantmentCards(newDeck);
	          addPlaneswalkerCards(newDeck);
	          currentDeck = newDeck;
	          revalidate();
	          repaint();
	    	}
	       }
	    }       
	}

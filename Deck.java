import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Deck {
	
	private String deckName;
	protected Card deckOfCards[];
	private int lastCardSpot;
	protected CardButton creatures[];
	protected CardButton instants[];
	protected CardButton sorceries[];
	protected CardButton artifacts[];
	protected CardButton enchantments[];
	protected CardButton planeswalkers[];
	private JPanel assignedPanel;
	
	public Deck(String name, JPanel panelName)
	{
		this.deckName = name;
		deckOfCards = new Card[100];
		creatures = new CardButton[100];
		instants = new CardButton[100];
		sorceries = new CardButton[100];
		artifacts = new CardButton[100];
		enchantments = new CardButton[100];
		planeswalkers = new CardButton[100];
		assignedPanel = panelName;
		lastCardSpot = 0;
	}
	
	public void addCard(Card cardToAdd)
	{
		Random rand = new Random();
		
		ImageIcon icon;
		if(rand.nextInt() % 4 == 0)
		{
			Color darkYellow = new Color(224, 192, 9);
			if(this.deckName.equals("Erza Scarlet Titania"))
			{
				icon = new ImageIcon(this.deckName + "/" + cardToAdd.getName() + ".jpeg");
			}
			else
			{
				icon = new ImageIcon(this.deckName + "/" + cardToAdd.getName() + ".jpg");
			}
			
			Image image = icon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(150, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			icon = new ImageIcon(newimg);  // transform it back
			
			CardButton cardButtonToAdd = new CardButton(cardToAdd, icon);
			cardButtonToAdd.setSize(10, 10);
			cardButtonToAdd.setOpaque(true);
			if(cardButtonToAdd.getCard().getIsCommander())
			{
				cardButtonToAdd.setBackground(darkYellow);
			}
			else
			{
				cardButtonToAdd.setBackground(Color.black);
			}
			deckOfCards[lastCardSpot] = cardToAdd;
			lastCardSpot++;
			
			if(cardToAdd.getCardType().equals(BoardStatePracticeGUI.cardTypes.Creature))
			{
				creatures[getNumCreatures()-1] = cardButtonToAdd;
			}
			else if(cardToAdd.getCardType().equals(BoardStatePracticeGUI.cardTypes.Instant))
			{
				instants[getNumInstants()-1] = cardButtonToAdd;
			}
			else if(cardToAdd.getCardType().equals(BoardStatePracticeGUI.cardTypes.Sorcery))
			{
				sorceries[getNumSorceries()-1] = cardButtonToAdd;
			}
			else if(cardToAdd.getCardType().equals(BoardStatePracticeGUI.cardTypes.Artifact))
			{
				artifacts[getNumArtifacts()-1] = cardButtonToAdd;
			}
			else if(cardToAdd.getCardType().equals(BoardStatePracticeGUI.cardTypes.Enchantment))
			{
				enchantments[getNumEnchantments()-1] = cardButtonToAdd;
			}
			else if(cardToAdd.getCardType().equals(BoardStatePracticeGUI.cardTypes.Planeswalker))
			{
				planeswalkers[getNumPlaneswalkers()-1] = cardButtonToAdd;
			}
		}
		
	}
	
	public JPanel getAssignedPanel()
	{
		return this.assignedPanel;
	}
	
	public String printDeck()
	{
		String deckDetails = deckName + "\n-------------------------\n";
		deckDetails += "Creatures: " + getNumCreatures() + "\nInstants: " + getNumInstants() +
				"\nSorceries: " + getNumSorceries() + "\nArtifacts: " + getNumArtifacts() +
				"\nEnchantments: " + getNumEnchantments() + "\nPlaneswalkers: " + getNumPlaneswalkers() + "\n\n";
		
		for(int i = 0; i < lastCardSpot; i++)
		{
			deckDetails += deckOfCards[i].toString();
			deckDetails += "\n";
		}
		
		return deckDetails;
	}
	
	public int getNumCreatures()
	{
		int numCreatures = 0;
		for(int i = 0; i < lastCardSpot; i++)
		{
			if(deckOfCards[i].getCardType().equals(BoardStatePracticeGUI.cardTypes.Creature))
			{
				numCreatures++;
			}
		}
		return numCreatures;
	}
	
	public int getNumInstants()
	{
		int numInstants = 0;
		for(int i = 0; i < lastCardSpot; i++)
		{
			if(deckOfCards[i].getCardType().equals(BoardStatePracticeGUI.cardTypes.Instant))
			{
				numInstants++;
			}
		}
		return numInstants;
	}
	
	public int getNumSorceries()
	{
		int numSorceries = 0;
		for(int i = 0; i < lastCardSpot; i++)
		{
			if(deckOfCards[i].getCardType().equals(BoardStatePracticeGUI.cardTypes.Sorcery))
			{
				numSorceries++;
			}
		}
		return numSorceries;
	}
	
	public int getNumArtifacts()
	{
		int numArtifacts = 0;
		for(int i = 0; i < lastCardSpot; i++)
		{
			if(deckOfCards[i].getCardType().equals(BoardStatePracticeGUI.cardTypes.Artifact))
			{
				numArtifacts++;
			}
		}
		return numArtifacts;
	}
	
	public int getNumEnchantments()
	{
		int numEnchantments = 0;
		for(int i = 0; i < lastCardSpot; i++)
		{
			if(deckOfCards[i].getCardType().equals(BoardStatePracticeGUI.cardTypes.Enchantment))
			{
				numEnchantments++;
			}
		}
		return numEnchantments;
	}
	
	public int getNumPlaneswalkers()
	{
		int numPlaneswalkers = 0;
		for(int i = 0; i < lastCardSpot; i++)
		{
			if(deckOfCards[i].getCardType().equals(BoardStatePracticeGUI.cardTypes.Planeswalker))
			{
				numPlaneswalkers++;
			}
		}
		return numPlaneswalkers;
	}
	
	public String printCreatures()
	{
		String output = "";
		for (int i = 0; i < getNumCreatures(); i++)
		{
			output += creatures[i];
		}
		return output;
	}
	
	public String getDeckName()
	{
		return this.deckName;
	}
	
	public void createCards()
	{
		File deck = new File(this.getDeckName() + "CardsInfo");
		if(deck.isFile() && deck.exists())
		{
			try
			{
				Scanner deckScan = new Scanner(deck);
				while(deckScan.hasNextLine())
				{
					String line = deckScan.nextLine();
					Scanner cardScan = new Scanner(line);
					cardScan.useDelimiter(",");
					while (cardScan.hasNext())
					{
						Card newCard = null;
						String name = cardScan.next();
						int manaCost = cardScan.nextInt();
						String type = cardScan.next();
						boolean commander = cardScan.nextBoolean();
						switch(type)
						{
						case "Creature":
							newCard = new Card(name, manaCost, BoardStatePracticeGUI.cardTypes.Creature, commander);
							break;
						
						case "Artifact":
							newCard = new Card(name, manaCost, BoardStatePracticeGUI.cardTypes.Artifact, commander);
							break;
						case "Enchantment":
							newCard = new Card(name, manaCost, BoardStatePracticeGUI.cardTypes.Enchantment, commander);
							break;
						case "Planeswalker":
							newCard = new Card(name, manaCost, BoardStatePracticeGUI.cardTypes.Planeswalker, commander);
							break;
						}
						addCard(newCard);
							
					}
					cardScan.close();
				}
				deckScan.close();
			}
			catch (FileNotFoundException e)
			{
			}
		}
	}
	
	public void removeCards()
	{
		for(int i = 0; i < getNumCreatures(); i++)
		{
			creatures[i] = null;
		}
		for(int i = 0; i < getNumArtifacts(); i++)
		{
			artifacts[i] = null;
		}
		for(int i = 0; i < getNumEnchantments(); i++)
		{
			enchantments[i] = null;
		}
		for(int i = 0; i < getNumPlaneswalkers(); i++)
		{
			planeswalkers[i] = null;
		}
		for(int i = 0; i < lastCardSpot; i++)
		{
			deckOfCards[i] = null;
		}
		lastCardSpot = 0;
		
	}

}

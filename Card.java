
public class Card {
	
	private String cardName;
	private int manaCost;
	private BoardStatePracticeGUI.cardTypes cardType;
	private boolean isCommander;
	
	public Card(String name, int cost, BoardStatePracticeGUI.cardTypes type, boolean isCommander)
	{
		this.cardName = name;
		this.manaCost = cost;
		this.cardType = type;
		this.isCommander = isCommander;
	}
	
	public String getName()
	{
		return this.cardName;
	}
	
	public int getManaCost()
	{
		return this.manaCost;
	}
	
	public BoardStatePracticeGUI.cardTypes getCardType()
	{
		return this.cardType;
	}
	
	public boolean getIsCommander()
	{
		return this.isCommander;
	}
	
	public String toString()
	{
		String commander;
		if(isCommander)
		{
			commander = "Yes";
		}
		else
		{
			commander = "No";
		}
		
		String cardDetails = "Card Name: " + this.cardName + "\nConverted Mana Cost: " + this.manaCost
				+ "\nCard Type: " + this.cardType + "\nCommander: " + commander + "\n";
		return cardDetails;
	}

}

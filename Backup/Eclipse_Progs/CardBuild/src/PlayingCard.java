public class PlayingCard
{

	private Suit suit;
	private Rank rank;

	private boolean visible;

	PlayingCard(Rank rank, Suit suit)
	{

		this(rank, suit, false);
	}

	PlayingCard(Rank rank, Suit suit, Boolean visibility)
	{
		this.rank = rank;
		this.suit = suit;
		this.visible = visibility;
	}

	public void setVisible(boolean visibility)
	{
		visible = visibility;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public boolean equals(PlayingCard otherCard)
	{
		return suit.equals(otherCard.suit) && rank.equals(otherCard.rank);
	}

	public Rank getRank()
	{
		return null;
	}

	public Suit getSuit()
	{
		return null;
	}

	public String toString()
	{
		return rank.getSymbol() + " of " + suit.getSymbol();
	}

}

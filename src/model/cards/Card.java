package model.cards;

public abstract class Card implements Cloneable {
	private String name;
	private int manaCost;
	private Rarity rarity;

	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		this.manaCost = manaCost;
		this.rarity = rarity;
	}

	public int getManaCost() {
		return manaCost;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
		if (this.manaCost > 10)
			this.manaCost = 10;
		if (this.manaCost < 0)
			this.manaCost = 0;
	}

	public Card clone() throws CloneNotSupportedException {
		return (Card) super.clone();
	}
	public String toString2(){
		String x = "";
		if (name.equals("Colossus of the Moon")){
			x = "Colossus of the" + "\n" + "Moon"+ "\n" ;
		}else {
		String s = name ;
		String[] arr = s.split(" ");
		for (int i = 0 ; i < arr.length ; i++ ){
			x = x + arr[i] +"\n" ;
		}}
		
		return (x + "Mana cost :" + manaCost + "\n"+rarity + "\n");
	}
	public String toString(){
		return (name + "\n" + "Mana cost :" + manaCost + "\n"+rarity + "\n");
	}
	public static void main (String [] a){
		//Card c = new Card("asda ", 9 , Rarity.BASIC);
		
		
	}

}

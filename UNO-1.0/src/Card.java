import java.io.*;
import java.util.*;

/*
 * Class for UNO cards
 * @param card_color --> The color of the card is represented by an integer
 * --> Red = 0
 * --> Blue = 1
 * --> Green = 2
 * --> Yellow = 3
 * @param card_val --> The number on the card; If the card value is a special card(Draw 2, reverse, Skip, Wild, Wild+4) It will have different values
 * --> 0-9 = 0-9
 * --> Skip Next = 10 
 * --> Reverse = 11
 * --> Draw 2 = 12
 * --> Wild Card = 13
 * --> Wild Card + 4 = 14
 */

public class Card {
	int card_color;
	int card_value;
	public Card() {};
	public Card(int color, int card_val) {
		card_color = color;
		card_value = card_val;
	};
	
	String color_to_string(Card card_to_convert) {
		int card_color_val = card_to_convert.card_color;
		String color_string = "";
		if(card_color_val == 0) {
			color_string = "Red";
		}else if(card_color_val == 1) {
			color_string =  "Blue";
		}else if(card_color_val == 2) {
			color_string = "Green";
		}else if(card_color_val == 3) {
			color_string = "Yellow";
		}
		return color_string;
	}
	
	String value_to_string(Card card_to_convert) {
		int card_value_int = card_to_convert.card_value;
		String value_string = "";
		if(card_value_int <= 9) {
			value_string = Integer.toString(card_value_int);
		}else if(card_value_int == 10) {
			value_string =  "Skip Card";
		}else if(card_value_int == 11) {
			value_string = "Reverse Card";
		}else if(card_value_int == 12) {
			value_string = "Draw 2 Card";
		}else if(card_value_int == 13) {
			value_string = "Wild Card";
		}else if(card_value_int == 14) {
			value_string = "Wild Draw 4 Card";
		}
		return value_string;
	}
}

class RegularCard extends Card{
	public RegularCard() {};
}

// Reverse the direction of play
class ReverseCard extends Card{
	public ReverseCard() {};
	void changeDirectionofPlay() {
		
	}
}

//The next person is skipped in turn
class SkipCard extends Card{
	public SkipCard() {};
	void skipNextPlayer() {
		
	}
}

//The next person draws 2 cards from the playable deck and misses turn
class DrawTwoCard extends Card{
	public DrawTwoCard() {};
	void drawTwo(){
	
	}
 }

//New color is chosen by the player. New color can be the same as old color
class WildCard extends Card{
	public WildCard() {};
	void changeColor() {
		
	}
}

//New color is chosen by the player who places it on discard pile. The next player must also draw 4 from playable deck and miss a turn
//Bonus point: +0.5 if implement the following rule:
//�Wild Draw Four� card can only be played if the player has no cards matching the current color
class WildDrawFourCard extends Card{
	public WildDrawFourCard() {};
	void changeColorPlusFour() {
		
	}
}


/*
 * There are two ways to make the cards for the deck. 
 * 1. Do all the cards by each color, so if red then make all the card types for red. if blue then make all the card types for blue and so on.
 * 2. Do all the cards by card type, so if wild card then make all the wild cards for each color, if reverse card then make all reverse cards for each color.
 * There needs to be 2 sets of 1-9 and 1 zero card for each color, which in total is 19 cards without special move cards
 * Each color also gets 2 reverse cards, 2 skip cards, 2 draw two cards, 2 wild card, 2 wild draw 4 card, which is an additional 8 cards.
 * This makes each color have a total of 27 cards
 */

class Deck extends Card {
	
	public Deck() {
		Stack<Card> deck = new Stack<Card>();
		//or
		
		createRegularCards(76); // 19 for each color * 4 for each color = 76 cards
		
		createReverseCards(8); // 2 for each color * 4 for each color = 8 cards
		
		createSkipCards(8); // 2 for each color * 4 for each color = 8 cards
		
		createDrawTwoCards(8);
		
		createWildCards(8); // 2 for each color * 4 for each color = 8 cards
		
		createWildDrawFourCards(8); // 2 for each color * 4 for each color = 8 cards
		
	}
	
	
	void createReverseCards(int count) {
		for (int colorofCard = 0; colorofCard < 4; colorofCard++) {
			for (int numReverseCards = 0; numReverseCards < count; numReverseCards++) {
				ReverseCard revCard = new ReverseCard();
				this.push(revCard);
			}
		}
	}
	
	void createSkipCards(int count) {
		for (int colorofCard = 0; colorofCard < 4; colorofCard++) {
			for (int numSkipCards = 0; numSkipCards < count; numSkipCards++) {
				SkipCard skipCard = new SkipCard();
				this.push(skipCard);
			}
		}
	}
	
	void createDrawTwoCards(int count) {
		for (int colorofCard = 1; colorofCard < 5; colorofCard++) {
			for (int numSkipCards = 0; numSkipCards < count; numSkipCards++) {
				DrawTwoCard skipCard = new DrawTwoCard();
				this.push(skipCard);
			}
		}
	}
	
	void createWildCards(int count) {
		for (int numWildCards = 0; numWildCards < count; numWildCards++ ) {
			WildCard wildCard = new WildCard();
			this.push(wildCard);
		}
	}
	
	void createWildDrawFourCards(int count) {
		for (int numWild4Cards = 0; numWild4Cards < count; numWild4Cards++) {
			WildDrawFourCard wild4Card = new WildDrawFourCard();
			this.push(wild4Card);
		}
	}
	
	void createRegularCards(int num_cards_needed) {
		
		for (int colorofCard = 1; colorofCard < 5; colorofCard++) {
			for (int numRegCards = 0; numRegCards <= 18; numRegCards++ ) {
				Card regCard = new Card(colorofCard, numRegCards % 9);
				this.push(regCard);
			}
		}

	}
	
	Card peek() {
		return this.peek();
	}
	
	Card pop() {
		return this.pop();
	}
	
	void push(Card card_to_push) {
		this.push(card_to_push);
	}
}


class Hand extends Card {
	Map<Integer, Card> hand = new HashMap<Integer, Card>();
	int total_cards;
	public Hand() {
		total_cards = hand.size();
	}
	
	//add a card to player hand
	void add_card_to_hand(Card newCard) {
		hand.put(total_cards, newCard);
		this.total_cards += 1;
	}
	
	void remove_card_from_hand(int cardIndex) {
		this.hand.remove(cardIndex);
		this.total_cards -= 1;
	}
	
	
	//show player hand
	Map<Integer, String> convert_to_readable_hand(Map<Integer, Card> hand){
		
		Map<Integer, String> hand_readable = new HashMap<Integer, String>();
		for (Map.Entry<Integer, Card> entry : hand.entrySet()) {
		    Integer cardIndex = entry.getKey();
		    Card cardValue = entry.getValue();
		    hand_readable.put(cardIndex, color_to_string(cardValue) + value_to_string(cardValue));
		}
		return hand_readable;
	}
	
	
}















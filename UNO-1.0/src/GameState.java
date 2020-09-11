/*
 * Class representing the state of the game. Inherits Player and Card class
 * 
 * @param totalPlayers
 * The total # of players in the game.
 * 
 * @param current_direction
 * Keeps track of the direction the game is flowing. This variable changes if a reverse card is ever used.
 * 1 means clockwise; -1 means counter clockwise
 * 
 * @param closest_wineer 
 * This is the player with the least amount of cards, closest to winning at any given time.
 * 
 * @param current_color
 * This is the color the next card should match. Any card played next much match this card, or the number, or a wild/wilddraw4 card must be played
 * 
 * @param current_number
 * This is the number the next card should match, if not, it must be the same color as the card or a wild/wilddraw4 card must be played
 * 
 * @param playableDeck
 * This is the deck that will be used to pull cards from if needed. This deck is the left over after distributing 7 cards to each player
 * 
 * @param discardDeck
 * This is the deck the players will put their cards into and later reused and shuffled to replace playableDeck if the playableDeck is empty
 * 
 * @param playableDeckCount
 * The count of how many cards are available in the playable deck
 * 
 * @param discardDeckCount
 * The count of how many cards are in the discard deck 
 *  
 */
public class GameState {
	int totalPlayers;
	int current_direction;
	String closest_winner;
	int current_color;
	int current_number;
	Deck playableDeck;
	Deck discardDeck;
	int playableDeckCount;
	int discardDeckCount;
	public GameState() {};
	public GameState(int total_players, int direction){
		totalPlayers = total_players;
		current_direction = direction;
		
	}
	//If a reverse card is used, it should alert players of new direction
	void alert_new_direction() {
		this.current_direction *= -1;
		//change direction
	}
	
	//If a wild card is played, it should alert players or new color
	void alert_new_color() {
		//change into new color
		System.out.println(this.current_color);
	}
	
	//When a card is played it should display the card to all other players
	void display_most_recent_card() {
		Card currentCard = this.discardDeck.peek();
		String curr_color = currentCard.color_to_string(currentCard);
		String curr_value = currentCard.value_to_string(currentCard);
		System.out.println("Most Recent Card:" + curr_color + " " + curr_value);
	}
	
	//GameState needs to update current color and number after each new played card
	void update_current_color_and_number() {
		this.current_color = this.discardDeck.peek().card_color;
		this.current_number = this.discardDeck.peek().card_value;
	}
	
	
	//playable deck should be updated after a card is drawn. card should be taken out. count should be decreased
	void pull_from_playable_deck() {
		Card current = playableDeck.pop();
		this.subtract_from_playable_deck();
		update_current_color_and_number();
		this.add_to_discard_deck_and_update_count(current);
	}
	
	//discard deck should be updated after a card is added. card should be added. count should be increased
	void add_to_discard_deck_and_update_count(Card card_to_add) {
		this.discardDeck.push(card_to_add);
		this.discardDeckCount += 1;
	}
	//Any time a card is taken from the deck, it need to be popped and into the players hand
	void subtract_from_playable_deck() {
		this.playableDeckCount -= 1;
		//check if the playableDeck is empty, if yes, then make discard deck new playable deck
		if(this.playableDeckCount == 0) {
			Deck newDeck = shuffle_discard(this.playableDeck);
			this.playableDeck = discard_to_playable(newDeck);
		}
	}
	
	//Shuffle the discard pile to be used as the playable deck when the playable deck runs out
	Deck shuffle_discard(Deck deck_to_shuffle) {
		//return Collections.shuffle(deck_to_shuffle);
		return null;
	}
	
	//Replace the playable deck with the shuffled discard pile
	Deck discard_to_playable(Deck previous_discard_deck) {
		Deck prev_playableDeck = this.playableDeck;
		this.playableDeck = previous_discard_deck;
		this.discardDeck = prev_playableDeck;
		return this.playableDeck;
	}
	
	
}
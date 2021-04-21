package ie.tudublin;

public class Note {
	//Inits the variables
	private char note;
	private int duration;

	//Takes the note and duration
	//and adds them to the variable
	public Note(char n, int dur) {
		note = n;
		duration = dur;
	}

	@Override
	public String toString() {
		//Returns the note value, duration and note name
		return note + " " + duration + (duration == 1 ? " Quaver" : " Crotchet");
	}

	//Returns the note
	public char getNote() {
		return note;
	}

	//Returns the duration
	public int getDuration() {
		return duration;
	}
}

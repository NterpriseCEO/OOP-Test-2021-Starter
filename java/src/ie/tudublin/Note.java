package ie.tudublin;

public class Note {
	private char note;
	private int duration;

	public Note(char n, int dur) {
		note = n;
		duration = dur;
	}

	@Override
	public String toString() {
		return "Note [duration=" + duration + ", note=" + note + "]";
	}

	public char getNote() {
		return note;
	}

	public int getDuration() {
		return duration;
	}
}

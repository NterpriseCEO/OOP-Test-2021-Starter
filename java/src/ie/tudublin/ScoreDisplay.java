package ie.tudublin;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class ScoreDisplay extends PApplet {

	//Intialises the variables
	ArrayList<Note> notes = new ArrayList<Note>();
	HashMap<Character, Integer> positions = new HashMap<Character, Integer>();

	//The notes to be played
	//String score = "DEFGABcd";
	//String score = "D2E2F2G2A2B2c2d2";
	String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";


	//Line variables
	float lineWidth = 0;
	float lineDistance = 0;
	float notesDistance = 0;

	//This function puts the
	//notes into an arraylist of Note objects
	void loadScore() {
		//Loops through the characters in the string
		for(int i = 0; i < score.length(); i++) {
			//Checks if not at the end of string yet
			if(i < score.length()) {
				//Checks if character is a letter
				if(Character.isLetter(score.charAt(i))) {
					boolean error = false;
					try {
						//Checks if next character is a digit
						if(Character.isDigit(score.charAt(i+1))) {
							//Adds the note and the duration to the notes arraylist
							notes.add(new Note(score.charAt(i),  Character.getNumericValue(score.charAt(i+1))));
						}else {
							//Adds the note and the duration 1 to the arraylist
							notes.add(new Note(score.charAt(i), 1));
						}
					}catch(Exception e) {
						println(e);
						//Error if index out of bounds
						error = true;
					}

					if(error) {
						//Error indicates that end of string has been reached
						//Adds the note and the duration 1 to the arraylist if the last character
						//was a letter and not a number
						notes.add(new Note(score.charAt(i), 1));
					}
				}
			}
		}
	}

	//Loops through each note and prints
	//the notes value and its duration and name
	public void printScores() {
		for(int i = 0; i < notes.size(); i++) {
			println(notes.get(i).toString());
		}
	}

	public void settings() {
		size(1000, 500);

		//Sets the line width to 80% of the window
		lineWidth = width*0.8f;
		//Sets the line distance to 50% * 15%
		lineDistance = (height/2)*0.15f;
		//Sets the notes distance to the line width / 18
		notesDistance = lineWidth/18;

		//Puts the notes positions into the hashmap
		//Allows the note to be printed at the position
		//specified here
		positions.put('D', 9);
		positions.put('E', 8);
		positions.put('F', 7);
		positions.put('G', 6);
		positions.put('A', 5);
		positions.put('B', 4);
		positions.put('c', 3);
		positions.put('d', 2);
	}

	public void setup() {
		//Loads the score and prints it
		loadScore();
		printScores();
	}

	public void draw() {
		background(255);
		//Draws the 5 lines
		drawStave();
		//Sets the line width
		strokeWeight(2);
		textSize(40);
		//Draws each note and the correct colour
		drawNotes();
	}

	//Draws the lines
	public void drawStave() {
		for(int i = 0; i < 5; i++) {
			//Starts line at 10% screen width, ends at 90%
			//Draws it at y: height * 25% + the line distance
			line(width*0.1f, (height*0.25f)+(lineDistance*i), width-(width)*0.1f, (height*0.25f)+lineDistance*i);
		}
	}

	void drawNotes() {
		//The mouse position
		float mx = mouseX;
		//Loops through the notes
		for(int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			//The position of each note
			int pos = positions.get(note.getNote());

			//The x and y positions of the lines
			//y = 25% of height + line distance / 2 (1/2 way between line)
			float x = (width*0.15f)+(notesDistance*i);
			float y = (height*0.25f)+((lineDistance/2)*pos);

			//Checks if the mouse pos is at the x pos of the note
			if(mx >= x && mx < x+25) {
				fill(255, 0, 0);
				stroke(255, 0, 0);
			}

			//Draws the circle
			ellipse(x, y, 25, 20);

			//Draws the line
			line(x+12.5f, y, x+12.5f, y-100);

			//Draws the text
			text(note.getNote(), x, 35);

			//Draws the diagonal line if
			//the note is a crotchet
			if(note.getDuration() == 2) {
				//Draws it at the top of the other line (x+12.5)
				//and down a diagonally
				line(x+12.5f, y-100, x+25, y-80);
			}
			//Changes the colour to black
			fill(0);
			stroke(0);
		}
	}
}

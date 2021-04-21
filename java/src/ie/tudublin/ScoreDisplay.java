package ie.tudublin;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class ScoreDisplay extends PApplet {

	ArrayList<Note> notes = new ArrayList<Note>();
	HashMap<Character, Integer> positions = new HashMap<Character, Integer>();

	//String score = "DEFGABcd";
	//String score = "D2E2F2G2A2B2c2d2";
	String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";

	float lineWidth = 0;
	float lineDistance = 0;
	float notesDistance = 0;

	void loadScore() {
		for(int i = 0; i < score.length(); i++) {
			if(i < score.length()) {
				if(Character.isLetter(score.charAt(i))) {
					boolean error = false;
					try {
						if(Character.isDigit(score.charAt(i+1))) {
							notes.add(new Note(score.charAt(i),  Character.getNumericValue(score.charAt(i+1))));
						}else {
							notes.add(new Note(score.charAt(i), 1));
						}
					}catch(Exception e) {
						println(e);
						error = true;
					}

					if(error) {
						notes.add(new Note(score.charAt(i), 1));
					}
				}
			}
		}
		println(notes.size());
		for(int i = 0; i < notes.size(); i++) {
			println(notes.get(i).getNote(), "- ",notes.get(i).getDuration());
		}
	}

	public void settings() {
		size(1000, 500);

		lineWidth = width*0.8f;
		lineDistance = (height/2)*0.15f;
		notesDistance = lineWidth/18;

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
		loadScore();
	}

	public void draw() {
		background(255);
		drawStave();
		drawNotes();
	}

	public void drawStave() {
		for(int i = 0; i < 5; i++) {
			line(width*0.1f, (height*0.15f)+(lineDistance*i), width-(width)*0.1f, (height*0.15f)+lineDistance*i);
		}
	}

	void drawNotes() {
		for(int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			int pos = positions.get(note.getNote());

			float x = (width*0.15f)+(notesDistance*i);
			float y = (height*0.15f)+((lineDistance/2)*pos);
			if(note.getDuration() == 2) {
				fill(0);
			}else {
				noFill();
			}
			ellipse(x, y, 25, 20);
			line(x+12.5f, y, x+12.5f, y-100);
		}
	}
}

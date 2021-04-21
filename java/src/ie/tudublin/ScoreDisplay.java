package ie.tudublin;

import java.util.ArrayList;
import java.util.HashMap;

import com.jogamp.graph.font.FontSet;

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
	}

	public void printScores() {
		for(int i = 0; i < notes.size(); i++) {
			print(notes.get(i).getNote(), "- ", notes.get(i).getDuration());
			if(notes.get(i).getDuration() == 1) {
				print(" Quaver\n");
			}else {
				print(" Crotchet\n");
			}
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
		printScores();
	}

	public void draw() {
		background(255);
		drawStave();
		fill(0);
		strokeWeight(2);
		textSize(40);
		drawNotes();
	}

	public void drawStave() {
		for(int i = 0; i < 5; i++) {
			line(width*0.1f, (height*0.25f)+(lineDistance*i), width-(width)*0.1f, (height*0.25f)+lineDistance*i);
		}
	}

	void drawNotes() {
		float mx = mouseX;
		for(int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			int pos = positions.get(note.getNote());

			float x = (width*0.15f)+(notesDistance*i);
			float y = (height*0.25f)+((lineDistance/2)*pos);

			if(mx >= x && mx < x+25) {
				fill(255, 0, 0);
				stroke(255, 0, 0);
			}

			ellipse(x, y, 25, 20);


			line(x+12.5f, y, x+12.5f, y-100);

			text(note.getNote(), x, 35);

			if(note.getDuration() == 2) {
				line(x+12.5f, y-100, x+25, y-80);
			}
			fill(0);
			stroke(0);
		}
	}
}

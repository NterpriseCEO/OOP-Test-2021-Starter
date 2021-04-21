package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class ScoreDisplay extends PApplet {

	ArrayList<Note> notes = new ArrayList<Note>();

	//String score = "DEFGABcd";
	//String score = "D2E2F2G2A2B2c2d2";
	String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";

	float lineWidth = 0;
	float lineDistance = 0;

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
		lineDistance = (height/2)*0.2f;
		
	}

	public void setup() {
		loadScore();
	}

	public void draw() {
		background(255);
		drawStave();
	}

	public void drawStave() {
		for(int i = 0; i < 5; i++) {
			line(width*0.1f, (width*0.15f)+(lineDistance*i), width-(width)*0.1f, (width*0.15f)+lineDistance*i);
		}
	}

	void drawNotes() {

	}
}

package com.epic.bobrunningpuzzle.view;

import com.epic.bobrunningpuzzle.model.Alley;
import com.epic.bobrunningpuzzle.model.Bob;
import com.epic.bobrunningpuzzle.model.Goal;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Road;
import com.epic.bobrunningpuzzle.model.Start;
import com.epic.bobrunningpuzzle.model.Surmountable;

public interface RendererVisitor {
	void draw(Surmountable el);
	void draw(Alley el);
	void draw(Goal el);
	void draw(Road el);
	void draw(Start el);
	void draw(Bob el);
	void draw(Level el);	
}

package com.epic.bobrunningpuzzle.view;

import com.epic.bobrunningpuzzle.model.Alley;
import com.epic.bobrunningpuzzle.model.BezierCurve;
import com.epic.bobrunningpuzzle.model.Bob;
import com.epic.bobrunningpuzzle.model.Gate;
import com.epic.bobrunningpuzzle.model.Goal;
import com.epic.bobrunningpuzzle.model.Junction;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Road;
import com.epic.bobrunningpuzzle.model.Start;
import com.epic.bobrunningpuzzle.model.StraightLine;
import com.epic.bobrunningpuzzle.model.Surmountable;
import com.epic.bobrunningpuzzle.model.Traveler;

public interface RendererVisitor {
	void draw(Alley el);
	void draw(BezierCurve el);
	void draw(Bob el);
	void draw(Gate el);
	void draw(Goal el);
	void draw(Junction el);
	void draw(Level el);	
	void draw(Road el);
	void draw(Start el);
	void draw(StraightLine el);
	void draw(Surmountable el);
	void draw(Traveler el);
}

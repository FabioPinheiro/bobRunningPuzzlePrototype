package com.epic.bobrunningpuzzle.model;

import com.epic.bobrunningpuzzle.view.RendererVisitor;

public class Road extends Surmountable {

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBob(float delta, Bob bob) {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptRendererVisitor(RendererVisitor rendererVisitor) {
		rendererVisitor.draw(this);
	}

}

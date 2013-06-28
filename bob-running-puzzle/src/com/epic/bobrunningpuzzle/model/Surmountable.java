package com.epic.bobrunningpuzzle.model;

import com.epic.bobrunningpuzzle.view.RendererVisitor;

public abstract class Surmountable implements ModelElement{

	public abstract void update(float delta);
	public abstract void updateBob(float delta,Bob bob);
	public abstract void acceptRendererVisitor(RendererVisitor rendererVisitor);
}

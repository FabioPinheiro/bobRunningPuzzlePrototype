package com.epic.bobrunningpuzzle.model;

import com.epic.bobrunningpuzzle.view.RendererVisitor;

public interface ModelElement {
	public void update(float delta);
	public void acceptRendererVisitor(RendererVisitor rendererVisitor);
}

package com.epic.bobrunningpuzzle.sceens;

import java.awt.Label;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Credits implements Screen{
	
	private Stage stage;
	private Skin skin;
	private Table table;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, false);
		
		//table.setTransform(true);
		table.setClip(true); //wrokarounf for TsetTransform
		table.setSize(width, height);
	}

	@Override
	public void show() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("ui/generalSkin.json"), new TextureAtlas("ui/atlas.pack"));
		
		Gdx.input.setInputProcessor(stage);
		
		table = new Table(skin);
		table.debug();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Window window = new Window("CREDITS", skin);
		window.pad(64);
		
		TextButton buttonBack = new TextButton("BACK", skin);
		buttonBack.pad(10);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				stage.addAction(Actions.sequence(Actions.moveTo(0, -stage.getHeight(), .25f), Actions.run(new Runnable() {
					 @Override
					 public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
					 }
				})));
			}
		});
		
		window.add("FABIO PIINHEIRO");
		window.pack();
		
		table.add(buttonBack);
		
		stage.addActor(table);
		stage.addActor(window);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}
	

}

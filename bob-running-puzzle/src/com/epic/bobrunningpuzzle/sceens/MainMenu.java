package com.epic.bobrunningpuzzle.sceens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.tween.ActorAccessor;

public class MainMenu implements Screen {

	private Stage stage; //done
	private TextureAtlas atlas; //done
	private Skin skin; //done
	private Table table; //done
	private TextButton buttonPlay, buttonExit, buttonHelp; //50%
	private BitmapFont whiteFont, blackFont, whiteFont_mistral, blackFont_mistral; // done
	private Label heading,heading_mistral;
	private TweenManager tweenManager;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Table.drawDebug(stage);
		
		tweenManager.update(delta);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		//table.setTransform(true); ERROR BUG =!?
		//table.setClip(true);// workaround because Table#setTransform(boolean transform) is not working
		table.invalidateHierarchy();
		table.setSize(width,height);
		
	}

	@Override
	public void show() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Creating Fonts
		whiteFont = new BitmapFont(Gdx.files.internal("font/whiteFont.fnt"),false);
		blackFont = new BitmapFont(Gdx.files.internal("font/blackFont.fnt"),false);
		whiteFont_mistral = new BitmapFont(Gdx.files.internal("font/whiteFont_mistral.fnt"),false);
		blackFont_mistral = new BitmapFont(Gdx.files.internal("font/blackFont_mistral.fnt"),false);
		
		//Creating Buttons
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = blackFont;
		
		buttonExit = new TextButton("EXIT", textButtonStyle);
		buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
		buttonExit.pad(15);
		
		buttonPlay = new TextButton("PLAY", textButtonStyle);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
		});
		buttonPlay.pad(15);
		
		buttonHelp = new TextButton("HELP", textButtonStyle);
		buttonHelp.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new HelpScreen());
			}
		});
		buttonHelp.pad(15);
		//Creating heading
		heading = new Label(BobRunningPuzzle.TITLE, new LabelStyle(whiteFont, Color.WHITE));
		heading_mistral = new Label(BobRunningPuzzle.TITLE, new LabelStyle(whiteFont_mistral, Color.WHITE));
		//heading.setFontScale(1.5f);
		
		//Puting stuff together
		table.add(heading);
		table.getCell(heading).spaceBottom(20);
		table.row();
		table.add(heading_mistral);
		table.getCell(heading_mistral).spaceBottom(20);
		table.row();
		table.add(buttonPlay);
		table.getCell(buttonPlay).spaceBottom(10);
		table.row();
		table.add(buttonExit);
		table.getCell(buttonExit).spaceBottom(10);
		table.row();
		table.add(buttonHelp);
		table.getCell(buttonHelp).spaceBottom(10);
		
		table.debug(); //TODO remove later
		stage.addActor(table);
		
		//Creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		//heading color animation
		Timeline.createSequence().beginSequence()
			.push(Tween.to(heading_mistral, ActorAccessor.RGB, .5f).target(0,0,1))
			.push(Tween.to(heading_mistral, ActorAccessor.RGB, .5f).target(0,1,1))
			.push(Tween.to(heading_mistral, ActorAccessor.RGB, .5f).target(1,0,0))
			.push(Tween.to(heading_mistral, ActorAccessor.RGB, .5f).target(1,1,0))
			.push(Tween.to(heading_mistral, ActorAccessor.RGB, .5f).target(1,0,1))
			.push(Tween.to(heading_mistral, ActorAccessor.RGB, .5f).target(1,1,1))
			.end().repeat(Tween.INFINITY, 0).start(tweenManager);
		
		//heading and button fade-in
		Timeline.createSequence().beginSequence()
			.push(Tween.set(heading, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(heading_mistral, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
			.push(Tween.to(heading, ActorAccessor.ALPHA, 0.25f).target(1))
			.push(Tween.to(heading_mistral, ActorAccessor.ALPHA, 0.25f).target(1))
			.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, 0.25f).target(1))
			.push(Tween.to(buttonExit, ActorAccessor.ALPHA, 0.25f).target(1))
			.end().start(tweenManager);
		
		//table fade.-in
		Tween.from(table, ActorAccessor.ALPHA, 0.5f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, 0.5f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);
		
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		whiteFont.dispose();
		blackFont.dispose();
	}

}

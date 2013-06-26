package com.epic.bobrunningpuzzle.sceens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.tween.ActorAccessor;

//import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenu implements Screen {

	private Stage stage; //done
	private TextureAtlas atlas; //done
	private Skin skin; //done
	private Table table; //done
	private TextButton buttonPlay, buttonExit, buttonHelp; //50%
	private Label heading;
	private TweenManager tweenManager;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Table.drawDebug(stage);
		
		stage.act(delta);
		stage.draw();
		
		tweenManager.update(delta);
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
		
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/generalSkin.json"),atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Creating Buttons
		buttonPlay = new TextButton("PLAY",skin);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				stage.addAction(Actions.sequence(Actions.moveTo(0, -stage.getHeight(), .25f), Actions.run(new Runnable() {
					 @Override
					 public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new LevelMenuScreen());
					 }
				})));
			}
		});
		buttonPlay.pad(5);
		
		buttonHelp = new TextButton("HELP", skin);
		buttonHelp.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new HelpScreen());
			}
		});
		buttonHelp.pad(5);
		
		buttonExit = new TextButton("EXIT",skin);
		buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
		buttonExit.pad(5);
		
		//Creating heading
		heading = new Label(BobRunningPuzzle.TITLE, skin);
		//heading.setFontScale(1.5f);
		
		//Puting stuff together
		table.add(heading);
		table.getCell(heading).spaceBottom(20);
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
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0,0,1))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0,1,1))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1,0,0))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1,1,0))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1,0,1))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1,1,1))
			.end().repeat(Tween.INFINITY, 0).start(tweenManager);
		
		//heading and button fade-in
		Timeline.createSequence().beginSequence()
			.push(Tween.set(heading, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
			.push(Tween.to(heading, ActorAccessor.ALPHA, 0.25f).target(1))
			.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, 0.25f).target(1))
			.push(Tween.to(buttonExit, ActorAccessor.ALPHA, 0.25f).target(1))
			.end().start(tweenManager);
		
		//table fade.-in
		stage.addAction(Actions.sequence(Actions.moveTo(0, stage.getHeight()), Actions.moveTo(0, 0, .25f))); // coming in from top animation
		//Tween.from(table, ActorAccessor.ALPHA, 0.5f).target(0).start(tweenManager);
		//Tween.from(table, ActorAccessor.Y, 0.5f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);
		
		tweenManager.update(Float.MIN_VALUE);
	}

	@Override
	public void hide() {
		dispose();
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
	}

}

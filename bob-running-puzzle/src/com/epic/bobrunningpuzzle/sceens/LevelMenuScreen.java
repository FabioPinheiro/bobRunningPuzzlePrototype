package com.epic.bobrunningpuzzle.sceens;

import sun.reflect.generics.tree.BottomSignature;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.StraightLine;

public class LevelMenuScreen implements Screen {
	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private ScrollPane scrollPane;
	private TextButton buttonPlay, buttonBack;


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Table.drawDebug(stage);


		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, false);
		//table.setClip(true); // workaround because Table#setTransform(boolean transform) is not working
		table.invalidateHierarchy();
		//table.setSize(width,height);
		//setupTable();
	}

	@Override
	public void show() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#show()");
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/generalSkin.json"),atlas);
		
		table = new Table(skin);
		table.setFillParent(true);
		
		table.debug();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		scrollPane = new ScrollPane(this.getLevelsList(), skin);
		scrollPane.setOverscroll(false, false);
		scrollPane.setScrollingDisabled(true, false);
		
		buttonPlay = new TextButton("PLAY",skin);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				stage.addAction(Actions.sequence(Actions.moveTo(0, -stage.getHeight(), .25f), Actions.run(new Runnable() {
					 @Override
					 public void run() {
						 ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
					 }
				})));
			}
		});
		buttonPlay.pad(10);
		
		buttonBack = new TextButton("BACK",skin);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				stage.addAction(Actions.sequence(Actions.moveTo(0, -stage.getHeight(), .25f), Actions.run(new Runnable() {
					 @Override
					 public void run() {
						 ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
					 }
				})));
			}
		});
		buttonBack.pad(10);
		
		setupTable();//Puting stuff together
		
		stage.addActor(table);
		
		stage.addAction(Actions.sequence(Actions.moveTo(0, stage.getHeight()), Actions.moveTo(0, 0, .25f))); // coming in from top animation
		

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
		atlas.dispose();
		skin.dispose();
	}
	
	// Adds everything in the table and resizes it. In an extra method for easier resizing because of table.getWidth() / 3
	private void setupTable() {
		table.clear(); // remove all children so we can add them again
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		table.add(new Label("SELECT LEVEL", skin/*, "big"*/)).colspan(3).expandX().spaceBottom(50).row();
		table.add(scrollPane).uniformX().expandY().top().left();
		table.add(buttonPlay).uniformX();
		table.add(buttonBack).uniformX().bottom().right();
	}
	
	private List getLevelsList() {
		String[] aux = new String[1];
		for(int i = 0; i < aux.length ; i++){
			aux[i] = new String("Level1");
		}
		return new List(aux, skin);
	}

}

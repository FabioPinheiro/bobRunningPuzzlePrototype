package com.epic.bobrunningpuzzle.sceens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;

public class HelpScreen implements Screen {
	private Stage stage; // done
	private TextureAtlas atlas; // done
	private Skin skin; // done
	private Table table; // done
	private TweenManager tweenManager;
	private TextButton buttonBack; // 50%
	private BitmapFont whiteFont, blackFont, whiteFont_mistral, blackFont_mistral; // done
	private Label heading, heading_mistral;

	private int width, height; // the width and height of the screen used by the
								// Android touch events.
	private ParticleEffect effect;
	private SpriteBatch batch;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Table.drawDebug(stage);

		batch.begin();
		effect.draw(batch, delta);
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		table.invalidateHierarchy();
	}

	@Override
	public void show() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#show()");
		stage = new Stage();
		batch = new SpriteBatch();

		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/generalSkin.json"),atlas);
		table = new Table(skin);
		table.setFillParent(true);
		//LIXO table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Creating Fonts
		whiteFont = new BitmapFont(Gdx.files.internal("font/whiteFont.fnt"), false);
		blackFont = new BitmapFont(Gdx.files.internal("font/blackFont.fnt"), false);
		whiteFont_mistral = new BitmapFont(Gdx.files.internal("font/whiteFont_mistral.fnt"), false);
		blackFont_mistral = new BitmapFont(Gdx.files.internal("font/blackFont_mistral.fnt"), false);

		// Creating Buttons
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = blackFont;

		buttonBack = new TextButton("BACK", textButtonStyle);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
			}
		});
		buttonBack.pad(15);
		buttonBack.setPosition(width-buttonBack.getHeight(), height-buttonBack.getHeight());
		
		
//		Window window = new Window("Help", skin);
//		window.pad(64);
//		window.add("Touch the screen \n to change your route...");
//		window.pack();
//		stage.addActor(window);
		
		table.add(new Label("HELP", skin/*, "big"*/)).colspan(3).expandX().spaceBottom(50).row();
		table.add().width(table.getWidth() / 3); // adding three empty cells just for looks
		table.add().width(table.getWidth() / 3);
		table.add().width(table.getWidth() / 3).row();
		table.add().expandY().top().left();
		table.add("Touch the screen \n to change your route...");
		table.add(buttonBack).bottom().right();
		
		table.debug(); //TODO remove later
		stage.addActor(table);
		
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("effects/test.p"), Gdx.files.internal("img"));
		effect.setPosition(Gdx.graphics.getWidth()/2, 2*Gdx.graphics.getHeight()/3);
		effect.start();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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

}

package com.epic.bobrunningpuzzle.view;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.Alley;
import com.epic.bobrunningpuzzle.model.Bob;
import com.epic.bobrunningpuzzle.model.Goal;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Road;
import com.epic.bobrunningpuzzle.model.Start;
import com.epic.bobrunningpuzzle.model.Surmountable;

public class WorldRenderer implements RendererVisitor{

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
	private static final float RUNNING_FRAME_DURATION = 0.06f;
	
	private Bob bob;
	private Level level;
	private OrthographicCamera cam;
	
	private SpriteBatch spriteBatch;
	private boolean debug;
	private int width;
	private int height;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis
	
	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	
	public WorldRenderer(Level level, boolean dedug) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+" <new>");
		this.level = level;
		this.debug = debug;		
		spriteBatch = new SpriteBatch();
		this.bob= new Bob(level.getStart());
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		loadTextures();
	}
	
	public void render() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#drawLevel");
		this.cam.update();
		spriteBatch.begin();
			level.acceptRendererVisitor(this);//drawSurmountable(level);
			bob.acceptRendererVisitor(this);//drawSurmountable(bob);
		spriteBatch.end();
	
		if (debug)
			drawDebug();
	}
	
	private void drawDebug() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawDebug");
	}
	
	public void draw(Bob bob) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawBob");
	}
	public void draw(Level level) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawLevel");
		Gdx.app.error("ERROR!!", "drawLevel- nunca devia chegar aqui!!!! devido ao visitor");//FIXME
	}
	
	public void draw(Surmountable el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "ERROR!!!! " + this.getClass().getName() +" #drawSurmountable");
		Gdx.app.error("ERROR!!", "drawSurmountable- nunca devia chegar aqui!!!!");
	}
	
	public void draw(Alley el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawAlley");
	}
	public void draw(Goal el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGoal");
	}
	public void draw(Road el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawRoad");
	}
	public void draw(Start el) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawStart");
	}
	
	public void loadTextures() {
		/*//bobTexture = new  Texture(Gdx.files.internal("data/textures/Mushroom_Block.png"));
		blockTexture = new Texture(Gdx.files.internal("data/textures/Brick_Block.png"));
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/textures/textures.pack"));
		bobIdleLeft = atlas.findRegion("bob-01");
		bobIdleRight = new TextureRegion(bobIdleLeft);
		bobIdleRight.flip(true, false);
		//blockTexture = atlas.findRegion("block");

		bobJumpLeft = atlas.findRegion("bob-up");
		bobJumpRight = new TextureRegion(bobJumpLeft);
		bobJumpRight.flip(true, false);
		bobFallLeft = atlas.findRegion("bob-down");
		bobFallRight = new TextureRegion(bobFallLeft);
		bobFallRight.flip(true, false);

		TextureRegion[] walkLeftFrames = new TextureRegion[5];
		for (int i = 0; i < 5; i++) {
			walkLeftFrames[i] = atlas.findRegion("bob-0" + (i + 2));
		}
		walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);

		TextureRegion[] walkRightFrames = new TextureRegion[5];

		for (int i = 0; i < 5; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);*/
	}
	
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
}

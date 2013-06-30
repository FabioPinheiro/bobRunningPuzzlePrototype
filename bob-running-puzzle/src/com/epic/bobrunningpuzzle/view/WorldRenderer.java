package com.epic.bobrunningpuzzle.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.Alley;
import com.epic.bobrunningpuzzle.model.Bob;
import com.epic.bobrunningpuzzle.model.Gate;
import com.epic.bobrunningpuzzle.model.Goal;
import com.epic.bobrunningpuzzle.model.Junction;
import com.epic.bobrunningpuzzle.model.Level;
import com.epic.bobrunningpuzzle.model.Road;
import com.epic.bobrunningpuzzle.model.Start;
import com.epic.bobrunningpuzzle.model.Surmountable;

public class WorldRenderer implements RendererVisitor{

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 10f;
	//private static final float RUNNING_FRAME_DURATION = 0.06f;
	
	private Level level;
	private OrthographicCamera cam;
	
	private SpriteBatch spriteBatch;
	private boolean debug;
	private float width;			//width = Gdx.graphics.getWidth();
	private float height;			//height = Gdx.graphics.getHeight();
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis
	
	private Texture bobTexture;
	
	//** for debug rendering **//
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	
	
	public WorldRenderer(Level level, boolean debug) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+" <new>");
		this.level = level;
		this.debug = debug;

		
		this.cam = new OrthographicCamera();//Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.setToOrtho(false, 30f, 30f);
		//this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();

		spriteBatch = new SpriteBatch();
		//spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.setProjectionMatrix(cam.projection);
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+" $$$$$$$$$\n"+ cam.combined.toString());
		
		loadTextures();
		
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, level.debugString());
	}
	
	
	public void render() {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#render");
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		updateCam();
		
		spriteBatch.setProjectionMatrix(cam.combined);
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+" #############\n"+ cam.projection.toString());
		spriteBatch.begin();
			level.acceptRendererVisitor(this);
		spriteBatch.end();
		
		if (debug) {
			debugRenderer.setProjectionMatrix(cam.combined);
			drawDebug();
		}
	}
	
	private void updateCam() {
		this.cam.position.set(level.getBob().getPosition().x, level.getBob().getPosition().y, 1);
		this.cam.lookAt(level.getBob().getPosition().x, level.getBob().getPosition().y, 0);
		this.cam.rotate(0.2f);
		this.cam.update();
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#updateCam: this.cam.direction:"+ this.cam.direction.toString());
	}


	public void dispose(){
		spriteBatch.dispose();
		bobTexture.dispose();
		debugRenderer.dispose();
	}
	
	public void draw(Bob bob) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawBob");
		spriteBatch.draw(bobTexture, bob.getPosition().x-Bob.SIZE/2, bob.getPosition().y-Bob.SIZE/2,Bob.SIZE,Bob.SIZE);
	}
	public void draw(Level level) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawLevel");
		Gdx.app.error("ERROR!!", "drawLevel- nunca devia chegar aqui!!!! devido ao visitor");//FIXME
	}
	
	public void draw(Surmountable el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, "ERROR!!!! " + this.getClass().getName() +" #drawSurmountable");
		Gdx.app.error("ERROR!!", "drawSurmountable- nunca devia chegar aqui!!!!");
	}
	
	public void draw(Alley el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawAlley");
	}
	@Override
	public void draw(Gate el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGate");
	}
	public void draw(Goal el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawGoal");
	}
	public void draw(Junction el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawJunction");
	}
	public void draw(Road el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawRoad");
	}
	public void draw(Start el) {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawStart");
	}
	
	public void loadTextures() {
		bobTexture = new Texture("img/bob.png");
		bobTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
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
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#setSize(): ppuX" + ppuX +"  ppuY"+ ppuY);
	}
	
	
	private void drawDebug() {
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#drawDebug");
		debugRenderer.begin(ShapeType.Line);
		float maxX=100, maxY=100;
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName());
		for(float x = 0; x <maxX ; x+=1){
			debugRenderer.line(x, 0, x, maxY, new Color(1, 0, 0, 1), new Color(1, 0, 0, 1));
		}
		for(float y = 0; y <maxY ; y+=1){
			debugRenderer.line(0, y, maxX, y, new Color(0, 1, 0, 1), new Color(0, 1, 0, 1));
		}
		
		/*for (Block block : world.getDrawableBlocks((int)CAMERA_WIDTH, (int)CAMERA_HEIGHT)) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render Bob
		Bob bob = world.getBob();
		Rectangle rect = bob.getBounds();
		float x1 = bob.getPosition().x + rect.x;
		float y1 = bob.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);*/
		debugRenderer.end();
	}
}

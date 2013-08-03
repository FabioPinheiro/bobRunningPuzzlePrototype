package com.epic.bobrunningpuzzle.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.epic.bobrunningpuzzle.BobRunningPuzzle;
import com.epic.bobrunningpuzzle.model.Level;

public class WorldRenderer{

	//private static final float CAMERA_WIDTH = 10f;
	//private static final float CAMERA_HEIGHT = 10f;
	//private static final float RUNNING_FRAME_DURATION = 0.06f;
	
	private Level level;
	private OrthographicCamera cam;
	private float camAngle = 0f;
	
	private float width;			//width = Gdx.graphics.getWidth();
	private float height;			//height = Gdx.graphics.getHeight();
	//private float ppuX; // pixels per unit on the X axis
	//private float ppuY; // pixels per unit on the Y axis
	
	private Texture bobTexture;
	
	
	private SpriteBatch spriteBatch;
	private RendererModels rendererModels;
	//** for debug rendering **//
	private boolean debug;
	private ShapeRenderer debugShapeRenderer = new ShapeRenderer();
	private RendererDebugModels rendererDebugModels;
	
	
	
	public WorldRenderer(Level level, boolean debug) {
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+" <new>");
		this.level = level;
		this.debug = debug;
		
		this.calculateSize();
		
		this.cam = new OrthographicCamera();
		this.zoomInTheTraveler(0);//show all map, not zoomed in the traveler
		this.cam.update();

		spriteBatch = new SpriteBatch();
		//spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.setProjectionMatrix(cam.projection);
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+" $$$$$$$$$\n"+ cam.combined.toString());
		
		loadTextures();
		
		this.rendererModels = new RendererModels(spriteBatch, bobTexture);
		this.rendererDebugModels = new RendererDebugModels(debugShapeRenderer);
		
		
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, level.debugString());
	}
	
	
	public void render() {
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#render");
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		updateCam();
		
		spriteBatch.setProjectionMatrix(cam.combined); //LIXO Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+" #############\n"+ cam.projection.toString());
		spriteBatch.begin();
			level.acceptRendererVisitor(rendererModels);
		spriteBatch.end();
		
		if (debug) {
			debugShapeRenderer.setProjectionMatrix(cam.combined);
			debugShapeRenderer.begin(ShapeType.Line);
			rendererDebugModels.drawGridLines();
			level.acceptRendererVisitor(rendererDebugModels);
			debugShapeRenderer.end();
		}
	}
	
	private void updateCam() {
		this.camPositionAndLookAt();
		//this.camZoom();
		this.camRotate();
		this.cam.update();
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#updateCam");
	}
	
	private void camPositionAndLookAt() {
		if(Level.getGameState() == Level.GameState.INGAME){
			this.cam.position.set(level.getBob().getPosition().x, level.getBob().getPosition().y, 1);
			this.cam.lookAt(level.getBob().getPosition().x, level.getBob().getPosition().y, 0);
		}
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#camPosition");
	}
	
	/**
	 * 0 show the all the map and the 1 show Traveler
	 * @param percentage varies between 0 and 1
	 */
	public void zoomInTheTraveler(float percentage) { //FIXME ENG percentage
		//this.cam.zoom = 1f*(1-percentage) + 0.3f*(percentage);
		//FIXME ZOOM and the comfiguração do ecram
		
		float auxW = 5f;
		float auxH = 5f * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
		
		//this.cam.setToOrtho(false, level.getWidth()*(1-percentage) + auxW*(percentage), level.getHeight()*(1-percentage) + auxH*(percentage));
		this.cam.setToOrtho(false, this.width*(1-percentage) + auxW*(percentage), this.height*(1-percentage) + auxH*(percentage));
		float x= level.getWidth()/2*(1-percentage) + level.getBob().getPosition().x * percentage;
		float y= level.getHeight()/2*(1-percentage) + level.getBob().getPosition().y * percentage;
		this.cam.position.set(x, y, 1);
		this.cam.lookAt(x, y, 0);
	}
	
	@SuppressWarnings("unused")//FIXME
	private void camZoom() {
		cam.zoom = 1f;
		Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#camZoom: this.cam.zoom:"+ this.cam.zoom);
	}
	private void camRotate() {
		float aux = level.getBob().getDirectionAngle()-camAngle;
		
		if(Math.abs(aux) > 180){
			if(aux > 180) aux -= 360f;
			if(aux < 180) aux += 360f;
			if(Math.abs(aux) > 45){ //DEGUB LIXO FIXME I (Fabio) don't like this!!
				Gdx.app.error("ERROR!!",  this.getClass().getName()+"#camRotate: \"Level too sharp\" aux=" + aux);
			}
		}
		
		
		if(Math.abs(aux) > 3f){
			aux *= .8f;
			if(aux >=0 ){	//positive
				if(aux < +3f) aux = +3;
				if(aux > +6f) aux = +6;
			}
			else {			//negative
				if(aux > -3f) aux = -3;
				if(aux < -6f) aux = -6;
			}
			
		}
		while (Math.abs(aux) > 2f) {
			aux *= 0.7;
		}
		
		
		
		this.cam.rotate(-aux);   //XXX it work but it makes sense?
		camAngle += aux;
		if (camAngle < 0) camAngle += 360;
		if (camAngle > 360) {
			Gdx.app.error("ERROR!!",  this.getClass().getName()+"#camRotate: (camAngle > 360)  this.camAngle=" + this.camAngle);
			camAngle -= 360;
		}
		//Gdx.app.log(BobRunningPuzzle.GAMELOG, this.getClass().getName()+"#camRotate: this.camAngle:"+ this.camAngle + "  aux=" + aux);
	}


	public void dispose(){
		spriteBatch.dispose();
		bobTexture.dispose();
		debugShapeRenderer.dispose();
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
	
	public void calculateSize () {
		//FIXME ENG racioEcran racioLevel
		float racioEcran = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		float racioLevel =  level.getWidth()/ level.getHeight();
		if(racioLevel <= racioEcran){//ENG limitado pele altura do level
			this.width = level.getHeight() * Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
			this.height = level.getHeight();
		}else {//ENG limitado pele Largura do level
			this.width = level.getWidth();
			this.height =level.getWidth() * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
		}
		Gdx.app.log(BobRunningPuzzle.GAMELOG_RENDER, this.getClass().getName()+"#calculateSize(): this.width" + this.width +"  this.height"+ this.height);
		
	}
	
}

package scenes;

import HUD.MainMenuButtons;
import HUD.UIHud;
import clouds.Cloud;
import clouds.CloudsController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import helpers.GameInfo;
import player.Player;

public class Gameplay implements Screen, ContactListener {

    private GameMain game;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private OrthographicCamera box2dCamera;
    private Box2DDebugRenderer debugRenderer;
    private World world;
    private Sprite[] bgs;
    private float lastYPosition;
    private CloudsController cloudsController;
    private Player player;

    private UIHud hud;

    private boolean moveCamera;

    public Gameplay(GameMain game){
        this.game = game;

        mainCamera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2, 0);
        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);
        box2dCamera = new OrthographicCamera();
        box2dCamera.setToOrtho(false, GameInfo.WIDTH/GameInfo.PPM, GameInfo.HEIGHT/GameInfo.PPM);
        box2dCamera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f,0);

        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -9.8f),true);
        cloudsController = new CloudsController(world);
        player = cloudsController.playerPos(player);
        hud = new UIHud(game);

        createBackgrounds();
    }

    void update(float dt){
        handleInput(dt);
        moveCamera();
        checkBackgroundPos();
        cloudsController.setCameraY(mainCamera.position.y);
        cloudsController.createAndArrangeNewCloud();
    }

    void moveCamera(){
        if(moveCamera) {
            mainCamera.position.y -= 0;
        }
    }

    void createBackgrounds(){
        bgs = new Sprite[3];

        for(int i = 0; i < bgs.length; i++) {
            bgs[i] = new Sprite(new Texture("Backgrounds/Game BG.png"));
            bgs[i].setPosition(0, -(bgs[i].getHeight() * i));
            lastYPosition = Math.abs(bgs[i].getY());
        }
    }

    void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.movePlayer(-2);
            moveCamera = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.movePlayer(2);
            moveCamera = true;
        }else{
            player.setWalking(false);
        }
    }

    void drawBackgrounds() {
        for (int i = 0; i < bgs.length; i++) {
            game.getBatch().draw(bgs[i], bgs[i].getX(), bgs[i].getY());
        }
    }

    void checkBackgroundPos() {

       for(int i = 0; i < bgs.length; i++){
           if(bgs[i].getY() - bgs[i].getHeight()/2f - 5> mainCamera.position.y){
               float newPosition = bgs[i].getHeight() + lastYPosition;
               bgs[i].setPosition(0, -newPosition);
               lastYPosition = Math.abs(newPosition);
           }
       }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);
        checkBackgroundPos();

        game.getBatch().begin();
        drawBackgrounds();
        cloudsController.drawClouds(game.getBatch());
        player.drawPlayerIdle(game.getBatch());
        player.drawPlayerAnimation(game.getBatch());
        game.getBatch().end();

        debugRenderer.render(world, box2dCamera.combined);
        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        cloudsController.drawCollectables(game.getBatch());

        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();

        player.UpdatePlayer();
        world.step(Gdx.graphics.getDeltaTime(),6,2);
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        for(int i = 0; i < bgs.length; i++){
            bgs[i].getTexture().dispose();
        }
        player.getTexture().dispose();
        debugRenderer.dispose();
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

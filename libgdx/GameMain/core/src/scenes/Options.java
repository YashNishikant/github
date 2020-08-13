package scenes;

import HUD.OptionsButtons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import helpers.GameInfo;

public class Options implements Screen {
    private GameMain game;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private Texture bgOptions;

    private OptionsButtons btns;

    public Options(GameMain game){
        this.game = game;

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDTH/2f, GameInfo.HEIGHT/2f,0);
        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);
        bgOptions = new Texture("Backgrounds/Options BG.png");
        btns = new OptionsButtons(game);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        game.getBatch().begin();

        game.getBatch().draw(bgOptions,0,0);

        game.getBatch().end();

        game.getBatch().setProjectionMatrix(btns.getStage().getCamera().combined);

        btns.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {

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
        bgOptions.dispose();
        btns.getStage().dispose();
    }
}

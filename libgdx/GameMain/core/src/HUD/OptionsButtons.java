package HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import helpers.GameInfo;
import scenes.MainMenu;

public class OptionsButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewPort;
    private ImageButton easy, medium, hard, backBtn;
    private Image sign;

    public OptionsButtons(GameMain game){
        this.game = game;

        gameViewPort = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewPort, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addListeners();

        stage.addActor(easy);
        stage.addActor(medium);
        stage.addActor(hard);
        stage.addActor(backBtn);
        stage.addActor(sign);

    }
    void createAndPositionButtons(){
        easy = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Easy.png"))));

        medium = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Medium.png"))));

        hard = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Hard.png"))));

        backBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Back.png"))));

        sign = new Image(new Texture("Buttons/Check Sign.png"));

        backBtn.setPosition(17,17, Align.bottomLeft);

        easy.setPosition(GameInfo.WIDTH/2, GameInfo.HEIGHT / 2 + 40, Align.center);
        medium.setPosition(GameInfo.WIDTH/2, GameInfo.HEIGHT / 2 - 40, Align.center);
        hard.setPosition(GameInfo.WIDTH/2, GameInfo.HEIGHT / 2 - 120, Align.center);

        sign.setPosition(GameInfo.WIDTH/2 + 76, medium.getY() + 13,
                Align.bottomLeft);
    }

    void addListeners() {

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });

        easy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(easy.getY() + 13);
            }
        });

        medium.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                sign.setY(medium.getY() + 13);
            }
        });

        hard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                sign.setY(hard.getY() + 13);
            }
        });
    }

    public Stage getStage(){
        return this.stage;
    }

}

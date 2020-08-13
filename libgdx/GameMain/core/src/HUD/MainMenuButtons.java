package HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import helpers.GameInfo;
import scenes.Gameplay;
import scenes.HighScore;
import scenes.Options;

public class MainMenuButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewPort;

    private ImageButton playbtn;
    private ImageButton highscorebtn;
    private ImageButton optionsbtn;
    private ImageButton quitbtn;
    private ImageButton musicbtn;

    public MainMenuButtons(GameMain game){
        this.game = game;
        gameViewPort = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage(gameViewPort, game.getBatch());
        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(playbtn);
        stage.addActor(highscorebtn);
        stage.addActor(optionsbtn);
        stage.addActor(quitbtn);
        stage.addActor(musicbtn);
    }

    void createAndPositionButtons(){
        playbtn = new ImageButton(new SpriteDrawable(new Sprite(new Sprite (
                new Texture("Buttons/Start Game.png")))));
        playbtn.setPosition(GameInfo.WIDTH/2f - 80, GameInfo.HEIGHT/2f + 50, Align.center);

        highscorebtn = new ImageButton(new SpriteDrawable(new Sprite(new Sprite (
                new Texture("Buttons/Highscore.png")))));
        highscorebtn.setPosition(GameInfo.WIDTH/2f - 60, GameInfo.HEIGHT/2f - 20, Align.center);

        optionsbtn= new ImageButton(new SpriteDrawable(new Sprite(new Sprite (
                new Texture("Buttons/Options.png")))));
        optionsbtn.setPosition(GameInfo.WIDTH/2f - 40, GameInfo.HEIGHT/2f - 90, Align.center);

        quitbtn = new ImageButton(new SpriteDrawable(new Sprite(new Sprite (
                new Texture("Buttons/Quit.png")))));
        quitbtn.setPosition(GameInfo.WIDTH/2f - 20, GameInfo.HEIGHT/2f - 160, Align.center);

        musicbtn = new ImageButton(new SpriteDrawable(new Sprite(new Sprite (
                new Texture("Buttons/Music On.png")))));
        musicbtn.setPosition(GameInfo.WIDTH/2f - 13, Align.bottomRight);


    }

    void addAllListeners(){
        playbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Gameplay(game));
            }
        });

        highscorebtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HighScore(game));
            }
        });

        optionsbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Options(game));
            }
        });

        quitbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        musicbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
    }

    public Stage getStage(){
        return this.stage;
    }
}

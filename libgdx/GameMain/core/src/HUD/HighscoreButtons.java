package HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import helpers.GameInfo;
import scenes.MainMenu;

public class HighscoreButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewPort;
    private ImageButton backbtn;
    private Label scoreLabel, coinLabel;

    public HighscoreButtons (GameMain game){
        this.game = game;

        gameViewPort = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());
        stage = new Stage(gameViewPort, game.getBatch());
        Gdx.input.setInputProcessor(stage);
        createAndPositionUIElements();

        stage.addActor(backbtn);
        stage.addActor(scoreLabel);
        stage.addActor(coinLabel);

    }

    void createAndPositionUIElements(){
        backbtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Back.png"))));

        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("Fonts/blow.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;

        BitmapFont scoreFont = generator.generateFont(parameter);
        BitmapFont coinFont = generator.generateFont(parameter);

        scoreLabel = new Label ("100" , new Label.LabelStyle(scoreFont, Color.WHITE));
        coinLabel = new Label ("100" , new Label.LabelStyle(scoreFont, Color.WHITE));

        backbtn.setPosition(67,17, Align.bottomRight);

        scoreLabel.setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT / 2 - 120);
        coinLabel.setPosition(GameInfo.WIDTH/2f, GameInfo.HEIGHT / 2 - 240);

        backbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
    }

    public Stage getStage(){
        return this.stage;
    }

}

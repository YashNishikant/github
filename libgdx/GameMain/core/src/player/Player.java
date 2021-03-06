package player;

import HUD.UIHud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import helpers.GameInfo;

public class Player extends Sprite {
    private World world;
    private Body body;

    private TextureAtlas playerAtlas;
    private Animation animation;
    private float elapsedTime;

    private boolean isWalking;

    public Player(World world, float x, float y){
        super(new Texture("Player/Player 1.png"));
        this.world = world;
        setPosition(x,y);
        createBody();
        playerAtlas = new TextureAtlas("Player Animation/Player_Animation.png.atlas");
    }

    void createBody(){
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.position.set(getX()/ GameInfo.PPM,getY()/GameInfo.PPM);

        body = world.createBody(bodydef);
        body.setFixedRotation(true);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2f - 20) / GameInfo.PPM, (getHeight()/2f)/GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 4f;
        fixtureDef.friction = 2f;
        fixtureDef.shape = shape;

        fixtureDef.filter.categoryBits = GameInfo.PLAYER;
        fixtureDef.filter.maskBits = GameInfo.DEFAULT | GameInfo.COLLECTABLE;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Player");

        shape.dispose();

    }

    public void movePlayer(float x){
        if (x < 0 && !this.isFlipX()) {
            this.flip(true, false);
        } else if (x > 0 && this.isFlipX()) {
            this.flip(true, false);
        }
        isWalking = true;
        body.setLinearVelocity(x, body.getLinearVelocity().y);

    }

    public void drawPlayerIdle(SpriteBatch batch){
        if(!isWalking) {
            batch.draw(this, getX() + getWidth() / 2f - 15, getY() - getHeight() / 2f);
        }
    }

    public void drawPlayerAnimation(SpriteBatch batch){
        if(isWalking){
            elapsedTime += Gdx.graphics.getDeltaTime();

            Array<TextureAtlas.AtlasRegion> frames = playerAtlas.getRegions();
            for(TextureRegion frame: frames){
                if(body.getLinearVelocity().x < 0 && !frame.isFlipX()){
                    frame.flip(true,false);
                }
                else if(body.getLinearVelocity().x > 0 && frame.isFlipX()){
                    frame.flip(true,false);
                }
            }

            animation = new Animation(1f/10f, playerAtlas.getRegions());

            batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), getX() + getWidth() / 2f - 15, getY() - getHeight() / 2f);
        }
    }
    public void UpdatePlayer(){
        if(body.getLinearVelocity().x > 0){
            setPosition(body.getPosition().x * GameInfo.PPM,
                    body.getPosition().y * GameInfo.PPM);
        }
        else if(body.getLinearVelocity().x < 0){
            setPosition((body.getPosition().x - 0.3f) * GameInfo.PPM,
                    body.getPosition().y * GameInfo.PPM);
        }
    }

    public void setWalking(boolean isWalking){
        this.isWalking = isWalking;
    }

}

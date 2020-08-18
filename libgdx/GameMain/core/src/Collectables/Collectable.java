package Collectables;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class Collectable extends Sprite {

    private World world;
    private Body body;
    private Fixture fixture;
    private String name;

    public Collectable(World world, String name){
        super(new Texture("Collectables/" + name +".png"));
        this.world = world;
        this.name = name;

    }

    void createCollectableBody(){
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.StaticBody;
        bodydef.position.set((getX() - getWidth()/2)/ GameInfo.PPM,
                (getY() + getWidth()/2)/ GameInfo.PPM);

        body = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2)/GameInfo.PPM,
                (getHeight()/2)/GameInfo.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameInfo.COLLECTABLE;
        fixtureDef.isSensor = true;

        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(name);

        shape.dispose();
    }

    public void setCollectablePosition(float x, float y){
        setPosition(x,y);
        createCollectableBody();
    }

    public void updateCollectable(){
        setPosition((body.getPosition().x - 0.0001f)* GameInfo.PPM,
                (body.getPosition().y - 0.2f)* GameInfo.PPM);
    }

    public void changeFilter(){
        Filter filter = new Filter();
        filter.categoryBits = GameInfo.DESTROYED;
        fixture.setFilterData(filter);
    }

    public Fixture getFixture(){
        return fixture;
    }

}

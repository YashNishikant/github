package clouds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import helpers.GameInfo;
import player.Player;

public class CloudsController {
    private World world;
    private final float DISTANCE_BETWEEN_CLOUDS = 250f;
    int index = 1;
    private Array<Cloud> clouds = new Array<Cloud>();
    private float lastCloudPositionY;
    private float cameraY;

    public CloudsController(World world){
        this.world = world;
        createClouds();
        positionClouds(true);
    }
    public void createClouds(){
        for(int i = 0; i < 2; i++){
            clouds.add(new Cloud(world, "Dark Cloud"));
        }
        for(int i = 0; i < 6; i++){
            clouds.add(new Cloud(world, "Cloud " + index));
            if(index == 4) {
                index++;
                index = 1;
            }
        }
        clouds.shuffle();
    }

    public void positionClouds(boolean firstTimeArranging){

        while(clouds.get(0).getCloudName() == "Dark Cloud"){
            clouds.shuffle();
        }

        float positionY = 0;
        if(firstTimeArranging){
            positionY = GameInfo.HEIGHT/2;
        }
        else{
            positionY = lastCloudPositionY;
        }

        float tempX = 0;
        float randomPlace = 0;
        float randomdisplacement = 0;

        for(Cloud c:clouds){

            if(c.getX() == 0 && c.getY() == 0){
                tempX = 0;
                randomdisplacement = (int)(Math.random()*70);

                if(randomPlace == 0){
                    randomPlace = 1;
                    tempX = 320;
                    c.setDrawLeft(false);
                }
                else  if (randomPlace == 1){
                    tempX = 80;
                    randomPlace = 0;
                    c.setDrawLeft(true);
                }
                c.setSpritePosition(tempX + randomdisplacement, positionY);
                positionY -= DISTANCE_BETWEEN_CLOUDS;
                lastCloudPositionY = positionY;
            }
        }
    }

    public void drawClouds(SpriteBatch batch) {
        for (Cloud c : clouds) {

            if(c.getDrawLeft()){
                batch.draw(c, c.getX()-c.getWidth()/2f - 20,
                        c.getY() - c.getHeight()/2f);
            }
            else{
                batch.draw(c, c.getX()-c.getWidth()/2f + 10,
                        c.getY() - c.getHeight()/2f);
            }
        }
    }
    public void createAndArrangeNewCloud(){
        for(int i = 0; i < clouds.size;i++){
            if((clouds.get(i).getY() - GameInfo.HEIGHT/2 - 15) > cameraY){
                clouds.get(i).getTexture().dispose();
                clouds.removeIndex(i);
            }
        }

        if(clouds.size == 4){
            createClouds();
            positionClouds(false);
        }
    }

    public Player playerPos(Player player){
        player = new Player(world, clouds.get(0).getX(), clouds.get(0).getY() + 100);
        return player;

    }

    public void setCameraY(float cameraY){
        this.cameraY = cameraY;
    }
}

package logic;

import de.acagamics.framework.geometry.Vec2f;
import de.acagamics.framework.ui.elements.Texture;
import de.acagamics.framework.ui.interfaces.GameObject;
import de.acagamics.framework.ui.interfaces.IDrawable;
import javafx.scene.canvas.GraphicsContext;

public class Player extends GameObject implements IDrawable {

    private IPlayerController controller;
    private Vec2f order;
    private Texture texture;

    private int points;

    private boolean lock;

    public Player(Vec2f position, IPlayerController controller) {
        super(position);
        this.controller = controller;
        this.texture = new Texture(new Vec2f(-0.05f, -0.05f), "Unit1.png", 0.1f, false);
    }

    IPlayerController getController(){
        return controller;
    }

    void update(Map map){
        lock = false;
        controller.think(map, this);
        lock = true;
        if(order != null){
            position = position.add(order.sub(position).clipLenght(0.01f));
        }
        position = position.clipLenght(1);
    }

    void addPoint() {
        points++;
    }

    public int getPoints(){
        return points;
    }

    public void setOrder(Vec2f position){
        if(!lock){
            order = position;
        }
    }

    @Override
    public String toString() {
        return controller.getClass().getSimpleName();
    }

    @Override
    public void draw(GraphicsContext context) {
        texture.draw(context, position);
    }
}

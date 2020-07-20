package logic;

import de.acagamics.framework.geometry.Vec2f;
import de.acagamics.framework.resources.DesignProperties;
import de.acagamics.framework.resources.ResourceManager;
import de.acagamics.framework.ui.interfaces.IDrawable;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map implements IDrawable {

    List<Flag> flags;
    List<Player> players;

    Map(List<Player> players, int n, long seed){
        this.players = players;
        flags = new ArrayList<>(n);
        Random random = new Random(seed);
        for(int i = 0; i < n; ++i){
            Vec2f position;
            do{
                position = new Vec2f(random.nextFloat() * 2 -1, random.nextFloat() * 2 -1);
            }while(position.length() > 1);
            flags.add(new Flag(position));
        }
    }

    void update(){
        for(Player player : players){
            player.update(this);
        }
        List<Flag> toBeRemoved = new ArrayList<>();
        for(Player player : players){
            for(Flag flag : flags){
                if(flag.getBounds().isInside(player.getPosition())){
                    toBeRemoved.add(flag);
                    player.addPoint();
                }
            }
        }
        flags.removeAll(toBeRemoved);
    }

    public List<Flag> getFlags(){
        return new ArrayList<>(flags);
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setStroke(ResourceManager.getInstance().loadProperties(DesignProperties.class).getForegroundColor());
        context.setLineWidth(0.02);
        context.strokeOval(-1, -1, 2,2);
        for(Player player : players){
            player.draw(context);
        }
        for(Flag flag : flags){
            flag.draw(context);
        }
    }
}

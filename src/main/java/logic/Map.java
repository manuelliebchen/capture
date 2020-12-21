package logic;

import de.acagamics.framework.geometry.Circle2f;
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
        List<Circle2f> flagPositions = new ArrayList<>(n);
        float radius = 1f;
        for(int i = 0; i < n; ++i){
            Circle2f position;
            do{
                position = new Circle2f(new Vec2f(random.nextFloat() * 2 * radius - radius, random.nextFloat() * 2 * radius - radius), 0.1f);
            } while(position.getPosition().length() > radius);
            flagPositions.add(position);
        }
        java.util.Map<Circle2f, Vec2f> posUpdate = Circle2f.relexCollisions(flagPositions);
        for( Circle2f pos : flagPositions){
            flags.add(new Flag(pos.getPosition().add(posUpdate.get(pos))));
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

package logic;

import de.acagamics.framework.simulation.GameStatistic;
import de.acagamics.framework.simulation.Simulatable;
import de.acagamics.framework.ui.interfaces.IDrawable;
import de.acagamics.framework.ui.interfaces.UIState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

import java.util.List;
import java.util.stream.Collectors;

public class Game implements Simulatable, IDrawable {
    private Map map;

    private boolean running = true;

    private GameStatistic gameStatistic;

    public Game(List<Player> players, int n){
        map = new Map(players, n, 0);
    }

    @Override
    public GameStatistic tick() {
        map.update();
        if(map.getFlags().isEmpty()){
            running = false;
            map.getPlayers().sort((p1,p2) -> p2.getPoints() - p1.getPoints());
            gameStatistic = new GameStatistic(false, map.getPlayers().stream().map(Player::getController).collect(Collectors.toList()));
            return gameStatistic;
        }
        return null;
    }

    @Override
    public boolean isAlive() {
        return running;
    }

    @Override
    public GameStatistic getStatistic() {
        return gameStatistic;
    }

    @Override
    public void draw(GraphicsContext context) {
        Affine transforamtion = UIState.calcultateTransformation(context.getCanvas(), 3);
        context.setTransform(transforamtion);
        map.draw(context);
    }
}

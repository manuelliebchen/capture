package states;

import de.acagamics.framework.geometry.Vec2f;
import de.acagamics.framework.resources.ResourceManager;
import de.acagamics.framework.simulation.GameStatistic;
import de.acagamics.framework.ui.StateManager;
import de.acagamics.framework.ui.elements.DynamicTextBox;
import de.acagamics.framework.ui.interfaces.ALIGNMENT;
import de.acagamics.framework.ui.interfaces.SelfUpdatingState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.Game;
import logic.IPlayerController;
import logic.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState extends SelfUpdatingState {
    private static final Logger LOG = LogManager.getLogger(GameState.class.getName());

    private Game game;

    public GameState(StateManager manager, GraphicsContext context, long seed) {
        super(manager, context);
        Random random = new Random(seed);
        List<Class<?>> controllers = ResourceManager.getInstance().loadContorller(IPlayerController.class);
        List<Player> players = new ArrayList<>(2);
        for(Class<?> controllerClass : controllers){
            try {
                IPlayerController controller;
                controller = (IPlayerController) controllerClass.getDeclaredConstructor().newInstance();
                Vec2f position;
                do{
                    position = new Vec2f(random.nextFloat() * 2 -1, random.nextFloat() * 2  -1 );
                } while (position.length()  > 1);
                players.add(new Player(position, controller));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("while constructing controllers: ", e);
            }
        }
        double width = context.getCanvas().getWidth();
        int i = 0;
        for(Player player : players){
            drawables.add(new DynamicTextBox(new Vec2f((float) (++i * width/(players.size() + 1) - width/2), 50), ()-> player.toString() + ": " + player.getPoints()).setVerticalAlignment(ALIGNMENT.CENTER));
        }
        game = new Game(players, 10);
    }

    @Override
    public void update() {
        GameStatistic gameStatistic = game.tick();
        if(gameStatistic != null) {
            manager.switchCurrentState(new StatisticState(manager,context,gameStatistic));
        }
        super.update();
    }

    @Override
    public void redraw() {
        super.redraw();
        context.save();
        game.draw(context);
        context.restore();
    }

    @Override
    public void handle(InputEvent event) {
        if (event instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                manager.pop();
            }
        }
    }
}

package states;

import de.acagamics.framework.geometry.Vec2f;
import de.acagamics.framework.resources.DesignProperties;
import de.acagamics.framework.resources.ResourceManager;
import de.acagamics.framework.simulation.GameStatistic;
import de.acagamics.framework.ui.StateManager;
import de.acagamics.framework.ui.elements.Button;
import de.acagamics.framework.ui.elements.TextBox;
import de.acagamics.framework.ui.interfaces.ALIGNMENT;
import de.acagamics.framework.ui.interfaces.UIState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class StatisticState extends UIState {

    public StatisticState(StateManager manager, GraphicsContext context, GameStatistic gameStatistic) {
        super(manager, context);
        int i = 1;
        drawables.add(new TextBox( new Vec2f(-50, 100),"Statistic").setFont(ResourceManager.getInstance().loadProperties(DesignProperties.class).getSubtitleFont()).setVerticalAlignment(ALIGNMENT.CENTER));
        for(Object player : gameStatistic.getOrderedControllers()){
            drawables.add(new TextBox(new Vec2f(-100, i * 50.0f + 150), i + ".").setVerticalAlignment(ALIGNMENT.CENTER));
            drawables.add(new TextBox(new Vec2f(100, i * 50.0f + 150), player.getClass().getSimpleName()).setVerticalAlignment(ALIGNMENT.CENTER));
            i++;
        }

        clickable.add(new Button(new Vec2f(-325, -120), Button.BUTTON_TYPE.NORMAL, "Back", manager::pop)
                .setKeyCode(KeyCode.ESCAPE).setVerticalAlignment(ALIGNMENT.RIGHT)
                .setHorizontalAlignment(ALIGNMENT.LOWER));
    }
}

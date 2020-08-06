package states;

import de.acagamics.framework.geometry.Vec2f;
import de.acagamics.framework.resources.DesignProperties;
import de.acagamics.framework.resources.ResourceManager;
import de.acagamics.framework.ui.StateManager;
import de.acagamics.framework.ui.elements.Button;
import de.acagamics.framework.ui.elements.TextBox;
import de.acagamics.framework.ui.interfaces.ALIGNMENT;
import de.acagamics.framework.ui.interfaces.UIState;
import javafx.scene.canvas.GraphicsContext;

public class MenuState extends UIState {
    public MenuState(StateManager manager, GraphicsContext context) {
        super(manager, context);
        drawables.add(new TextBox( new Vec2f(-50, 100),"Capture!").setFont(ResourceManager.getInstance().loadProperties(DesignProperties.class).getTitleFont()).setVerticalAlignment(ALIGNMENT.CENTER));
        clickable.add(new Button(new Vec2f(-35, 300), Button.BUTTON_TYPE.NORMAL, "Start", () -> manager.push(new GameState(manager,context, 0))).setVerticalAlignment(ALIGNMENT.CENTER));
        clickable.add(new Button(new Vec2f(-35, 400), Button.BUTTON_TYPE.NORMAL, "Inactive", () -> manager.push(new GameState(manager,context, 0))).setEnabled(false).setVerticalAlignment(ALIGNMENT.CENTER));
        clickable.add(new Button(new Vec2f(-35, 500), Button.BUTTON_TYPE.NORMAL, "Exit", manager::popAll).setVerticalAlignment(ALIGNMENT.CENTER));
    }
}

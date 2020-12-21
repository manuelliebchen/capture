package logic;

import de.acagamics.framework.geometry.Circle2f;
import de.acagamics.framework.geometry.Illustrator;
import de.acagamics.framework.geometry.Vec2f;
import de.acagamics.framework.ui.elements.Texture;
import de.acagamics.framework.ui.interfaces.GameObject;
import de.acagamics.framework.ui.interfaces.IDrawable;
import javafx.scene.canvas.GraphicsContext;

public class Flag extends GameObject implements IDrawable {

    private Texture texture;

    public Flag(Vec2f position) {
        super(position);
        this.texture = new Texture(new Vec2f(), "icon.png", 0.1f, false);
    }

    public Circle2f getBounds(){
            return new Circle2f(position, 0.1f);
    }

    @Override
    public void draw(GraphicsContext context) {
        texture.draw(context, position);
        Illustrator illustrator = new Illustrator(context);
        illustrator.draw(getBounds());
    }
}

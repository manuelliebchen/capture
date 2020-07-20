package controllers;

import de.acagamics.framework.interfaces.Student;
import logic.IPlayerController;
import logic.Map;
import logic.Player;

@Student(author = "Manuel", matrikelnummer = -1)
public class RunAround implements IPlayerController {

    @Override
    public void think(Map map, Player player) {
        player.setOrder(player.getPosition().add(player.getPosition().rotate(Math.PI/2).getNormalized().mult(0.01f)));
    }
}

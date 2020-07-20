package controllers;

import de.acagamics.framework.interfaces.Student;
import logic.Flag;
import logic.IPlayerController;
import logic.Map;
import logic.Player;

import java.util.List;

@Student(author = "Manuel", matrikelnummer = -1)
public class SearchIt implements IPlayerController {

    @Override
    public void think(Map map, Player player) {
        List<Flag> flags = map.getFlags();
        flags.sort(player.getPosition().sortDistanceTo());
        player.setOrder(flags.get(0).getPosition());
    }
}

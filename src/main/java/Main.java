import de.acagamics.framework.ui.CliArguments;
import de.acagamics.framework.ui.MainWindow;
import states.MenuState;

public class Main {
    public static void main(String[] args) {
        new CliArguments(args);
        MainWindow.launch(MenuState.class);
    }
}

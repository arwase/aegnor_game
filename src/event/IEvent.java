package event;

import area.map.GameCase;
import client.Player;

/**
 * Created by Locos on 02/10/2016.
 */
public interface IEvent {

    void prepare();

    void perform();

    void execute();

    void close();

    boolean onReceivePacket(EventManager manager, Player player, String packet) throws Exception;

    GameCase getEmptyCellForPlayer(Player player);
}

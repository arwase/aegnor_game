package guild;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import client.Player;
import database.Database;
import kernel.Constant;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Locos on 31/01/2017
 **/
public class GuildMember {

    private Player player;
    private Guild guild;
    private int rank = 0;
    private byte xpGive = 0;
    private long xpGave = 0;
    private int rights = 0;
    private String lastCo;
    private Map<Integer, Boolean> haveRights = new TreeMap<>();

    GuildMember(Player player, Guild guild, int rank, long xpGave, byte xpGive, int rights, String lastCo) {
        this.player = player;
        this.guild = guild;
        this.rank = rank;
        this.xpGave = xpGave;
        this.xpGive = xpGive;
        this.rights = rights;
        this.lastCo = lastCo;
        this.parseIntToRight(this.rights);
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerId() {
        return player.getId();
    }

    public String getName() {
        return player.getName();
    }

    public int getAlign() {
        return player.get_align();
    }

    public int getGfx() {
        return player.getGfxId();
    }

    public int getLvl() {
        return player.getLevel();
    }

    public Guild getGuild() {
        return guild;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public long getXpGave() {
        return xpGave;
    }

    public int getXpGive() {
        return xpGive;
    }

    public void giveXpToGuild(long xp) {
        this.xpGave += xp;
        this.guild.addXp(xp);
    }

    public String parseRights() {
        return Integer.toString(this.rights, 36);
    }

    public int getRights() {
        return rights;
    }

    public String getLastCo() {
        return lastCo;
    }

    public void setLastCo(String lastCo) {
        this.lastCo = lastCo;
    }

    int getHoursFromLastCo() {
        String[] split = this.lastCo.split("~");
        LocalDate localDate = new LocalDate(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        return Days.daysBetween(localDate, new LocalDate()).getDays() * 24;
    }

    public boolean canDo(int rightValue) {
        return this.rights == 1 ? true : haveRights.get(rightValue);
    }

    public void setAllRights(int rank, byte xp, int right, Player perso) {
        if (rank == -1) rank = this.rank;
        if (xp < 0) xp = this.xpGive;
        if (xp > 90) xp = 90;
        if (right == -1) right = this.rights;

        this.rank = rank;
        this.xpGive = xp;

        if (right != this.rights && right != 1) //V???rifie si les droits sont pareille ou si des droits de meneur; pour ne pas faire la conversion pour rien
            this.parseIntToRight(right);
        this.rights = right;

        Database.getDynamics().getGuildMemberData().update(perso);
    }


    private void initRights() {
        for (int right : Constant.G_RIGHTS) {
            this.haveRights.put(right, false);
        }
    }

    private void parseIntToRight(int total) {
        if (this.haveRights.isEmpty()) {
            this.initRights();
        }
        if (total != 1) {
            if (this.haveRights.size() > 0)//Si les droits contiennent quelque chose -> Vidage (M???me si le HashMap supprimerais les entr???es doublon lors de l'ajout)
                this.haveRights.clear();
            initRights();//Remplissage des droits

            Integer[] array = this.haveRights.keySet().toArray(new Integer[this.haveRights.size()]); //R???cup???re les clef de map dans un tableau d'Integer

            while (total > 0) {
                for (int i = this.haveRights.size() - 1; i < this.haveRights.size(); i--) {
                    if (array[i] <= total) {
                        total ^= array[i];
                        this.haveRights.put(array[i], true);
                        break;
                    }
                }
            }
        }
    }
}
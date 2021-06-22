package fight.traps;

import area.map.GameCase;
import common.PathFinding;
import common.SocketManager;
import fight.Fight;
import fight.Fighter;
import fight.spells.Spell.SortStats;
import kernel.Constant;

import java.util.ArrayList;

public class Trap {

    private Fighter caster;
    private GameCase cell;
    private byte size;
    private int spell;
    private SortStats trapSpell;
    private Fight fight;
    private int color;
    private boolean isUnHide = true;
    private int teamUnHide = -1;

    public Trap(Fight fight, Fighter caster, GameCase cell, byte size,
                SortStats trapSpell, int spell) {
        this.fight = fight;
        this.caster = caster;
        this.cell = cell;
        this.spell = spell;
        this.size = size;
        this.trapSpell = trapSpell;
        this.color = Constant.getTrapsColor(spell);
    }

    public GameCase getCell() {
        return this.cell;
    }

    public byte getSize() {
        return this.size;
    }

    public Fighter getCaster() {
        return this.caster;
    }

    public void setIsUnHide(Fighter f) {
        this.isUnHide = true;
        this.teamUnHide = f.getTeam();
    }

    public int getColor() {
        return this.color;
    }

    public void desappear() {
        StringBuilder str = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        StringBuilder str4 = new StringBuilder();

        int team = this.caster.getTeam() + 1;
        str.append("GDZ-").append(this.cell.getId()).append(";").append(this.size).append(";").append(this.color);
        SocketManager.GAME_SEND_GA_PACKET_TO_FIGHT(this.fight, team, 999, this.caster.getId()
                + "", str.toString(), 999);
        str2.append("GDC").append(this.cell.getId());
        SocketManager.GAME_SEND_GA_PACKET_TO_FIGHT(this.fight, team, 999, this.caster.getId()
                + "", str2.toString(), 999);
        if (this.isUnHide) {
            int team2 = this.teamUnHide + 1;
            str3.append("GDZ-").append(this.cell.getId()).append(";").append(this.size).append(";").append(this.color);
            SocketManager.GAME_SEND_GA_PACKET_TO_FIGHT(this.fight, team2, 999, this.caster.getId()
                    + "", str3.toString(), 999);
            str4.append("GDC").append(this.cell.getId());
            SocketManager.GAME_SEND_GA_PACKET_TO_FIGHT(this.fight, team2, 999, this.caster.getId()
                    + "", str4.toString(), 999);
        }
    }

    public void appear(Fighter f) {
        StringBuilder str = new StringBuilder();
        StringBuilder str2 = new StringBuilder();

        int team = f.getTeam() + 1;
        str.append("GDZ+").append(this.cell.getId()).append(";").append(this.size).append(";").append(this.color);
        SocketManager.GAME_SEND_GA_PACKET_TO_FIGHT(this.fight, team, 999, this.caster.getId()
                + "", str.toString(), 999);
        str2.append("GDC").append(this.cell.getId()).append(";Haaaaaaaaz3005;");
        SocketManager.GAME_SEND_GA_PACKET_TO_FIGHT(this.fight, team, 999, this.caster.getId()
                + "", str2.toString(), 999);
    }

    public void onTraped(Fighter target) {
        if (target.isDead())
            return;
        this.fight.getAllTraps().remove(this);//On efface le pieges
        desappear();//On d�clenche ses effets
        String str = this.spell + "," + this.cell.getId() + ",0,1,1," + this.caster.getId();
        SocketManager.GAME_SEND_GA_PACKET_TO_FIGHT(this.fight, 7, 307, target.getId() + "", str, 307);

        ArrayList<GameCase> cells = new ArrayList<GameCase>();
        cells.add(this.cell);
        //on ajoute les cases
        for (int a = 0; a < this.size; a++) {
            char[] dirs = {'b', 'd', 'f', 'h'};
            ArrayList<GameCase> cases2 = new ArrayList<GameCase>();//on �vite les modifications concurrentes
            cases2.addAll(cells);
            for (GameCase aCell : cases2) {
                if(aCell == null) continue;
                for (char d : dirs) {
                    GameCase cell = this.fight.getMap().getCase(PathFinding.GetCaseIDFromDirrection(aCell.getId(), d, this.fight.getMap(), true));
                    if (cell == null)
                        continue;
                    if (!cells.contains(cell))
                        cells.add(cell);
                }
            }
        }
        Fighter fakeCaster;
        if (this.caster.getPlayer() == null)
            fakeCaster = new Fighter(this.fight, this.caster.getMob());
        else
            fakeCaster = new Fighter(this.fight, this.caster.getPlayer());
        fakeCaster.setCell(this.cell);
        this.trapSpell.applySpellEffectToFight(this.fight, fakeCaster, target.getCell(), cells, false);
        this.fight.verifIfTeamAllDead();
    }
}
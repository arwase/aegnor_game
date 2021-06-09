package fight.ia.type;

import common.PathFinding;
import fight.Fight;
import fight.Fighter;
import fight.ia.AbstractIA;
import fight.ia.util.Function;

/**
 * Created by Locos on 04/10/2015.
 */
public class IA19 extends AbstractIA {

    private boolean tp = false;

    public IA19(Fight fight, Fighter fighter, byte count) {
        super(fight, fighter, count);
    }

    @Override
    public void apply() {
        if (!this.stop && this.fighter.canPlay() && this.count > 0) {
            Fighter friend = Function.getInstance().getNearestFriend(this.fight, this.fighter);
            Fighter ennemy = Function.getInstance().getNearestEnnemy(this.fight, this.fighter);

            int dist1 = PathFinding.getDirBetweenTwoCase(friend.getCell().getId(), ennemy.getCell().getId(), this.fight.getMap(), true);
            int dist2 = PathFinding.getDirBetweenTwoCase(this.fighter.getCell().getId(), ennemy.getCell().getId(), this.fight.getMap(), true);

            for (Fighter t : this.fight.getFighters(3)) {
                if (t != null && t.getTeam() == this.fighter.getTeam()) {
                    int tDist = PathFinding.getDistanceBetweenTwoCase(this.fight.getMap(), t.getCell(), ennemy.getCell());
                    if (dist2 > tDist && dist1 > tDist) {
                        dist1 = tDist;
                        friend = t;
                    }
                }
            }

            boolean needTp = dist2 > dist1;

            if (dist2 <= 3) {
                needTp = false;
                this.tp = true;
            }

            if (needTp && !this.tp && Function.getInstance().tpIfPossibleTynril(this.fight, this.fighter, friend) == 0) {
                this.tp = true;
            } else if (!needTp) {
                Function.getInstance().moveNearIfPossible(this.fight, this.fighter, ennemy);
                dist1 = -5;
            }

            if (this.fighter.getCurPm(this.fight) > 0 && dist1 != -5) {
                int dist = PathFinding.getDirBetweenTwoCase(this.fighter.getCell().getId(), ennemy.getCell().getId(), this.fight.getMap(), true);
                if (dist > 1) {
                    Function.getInstance().moveNearIfPossible(this.fight, this.fighter, ennemy);
                }
            }

            if (!Function.getInstance().HealIfPossiblefriend(fight, this.fighter, friend)) {
                Function.getInstance().attackIfPossibleTynril(this.fight, this.fighter, ennemy);
            }
            this.addNext(this::decrementCount, 1000);
        } else {
            this.stop = true;
        }
    }
}
package fight.ia.type;

import fight.Fight;
import fight.Fighter;
import fight.ia.AbstractNeedSpell;
import fight.ia.util.Function;
import fight.spells.Spell;

/**
 * Created by Locos on 04/10/2015.
 */
public class IA35 extends AbstractNeedSpell {

    public IA35(Fight fight, Fighter fighter, byte count) {
        super(fight, fighter, count);
    }

    @Override
    public void apply() {
        if (!this.stop && this.fighter.canPlay() && this.count > 0) {
            int time = 100, maxPo = 1;
            boolean action = false;
            Fighter nearestEnnemy = Function.getInstance().getNearestEnnemy(this.fight, this.fighter);

            for (Spell.SortStats S : this.highests)
                if (S.getMaxPO() > maxPo)
                    maxPo = S.getMaxPO();

            Fighter ennemy1 = Function.getInstance().getNearestEnnemynbrcasemax(this.fight, this.fighter, 1, maxPo + 1);// pomax +1;
            Fighter ennemy2 = Function.getInstance().getNearestEnnemynbrcasemax(this.fight, this.fighter, 0, 2);//2 = po min 1 + 1;

            if (maxPo == 1) ennemy1 = null;
            if (ennemy2 != null) if (ennemy2.isHide()) ennemy2 = null;
            if (ennemy1 != null) if (ennemy1.isHide()) ennemy1 = null;

            if (this.fighter.getCurPm(this.fight) > 0 && (ennemy1 == null || ennemy2 == null)) {
                if (Function.getInstance().moveNearIfPossible(this.fight, this.fighter, nearestEnnemy)) {
                    time = 1000;
                    action = true;
                    ennemy1 = Function.getInstance().getNearestEnnemynbrcasemax(this.fight, this.fighter, 1, maxPo + 1);// pomax +1;
                    ennemy2 = Function.getInstance().getNearestEnnemynbrcasemax(this.fight, this.fighter, 0, 2);//2 = po min 1 + 1;

                    if (maxPo == 1) ennemy1 = null;
                }
            }

            if (this.fighter.getCurPa(this.fight) > 0 && !action) {
                if (Function.getInstance().invocIfPossible(this.fight, this.fighter, this.invocations)) {
                    time = 1000;
                    action = true;
                }
            }
            if (this.fighter.getCurPa(this.fight) > 0 && !action) {
                if (Function.getInstance().buffIfPossible(this.fight, this.fighter, this.fighter, this.buffs)) {
                    time = 1200;
                    action = true;
                }
            }
            if (this.fighter.getCurPa(this.fight) > 0 && !action) {
                if (Function.getInstance().HealIfPossible(this.fight, this.fighter, true, 50) != 0) {
                    time = 1000;
                    action = true;
                }
            }

            if (this.fighter.getCurPa(this.fight) > 0 && ennemy1 != null && !action) {
                int value = Function.getInstance().attackIfPossible(this.fight, this.fighter, this.highests);
                if (value != 0) {
                    time = value;
                    action = true;
                }
            } else if (this.fighter.getCurPa(this.fight) > 0 && ennemy2 != null && !action) {
                int value = Function.getInstance().attackIfPossible(this.fight, this.fighter, this.cacs);
                if (value != 0) {
                    time = value;
                    action = true;
                }
            }

            if (this.fighter.getCurPm(this.fight) > 0 && !action) {
                int value = Function.getInstance().moveautourIfPossible(this.fight, this.fighter, nearestEnnemy);
                if (value != 0) time = value;
            }

            if (this.fighter.getCurPa(this.fight) == 0 && this.fighter.getCurPm(this.fight) == 0) this.stop = true;
            addNext(this::decrementCount, time);
        } else {
            this.stop = true;
        }
    }
}
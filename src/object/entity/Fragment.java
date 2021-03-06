package object.entity;

import game.world.World.Couple;
import object.GameObject;

import java.util.ArrayList;

public class Fragment extends GameObject {

    private ArrayList<Couple<Integer, Integer>> runes;

    public Fragment(int Guid, String runes) {
        super(Guid);
        this.runes = new ArrayList<>();

        if (!runes.isEmpty()) {
            for (String rune : runes.split(";")) {
                String[] split = rune.split(":");
                this.runes.add(new Couple<>(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }
        }
    }

    public ArrayList<Couple<Integer, Integer>> getRunes() {
        return runes;
    }

    public void addRune(int id) {
        Couple<Integer, Integer> rune = this.search(id);

        if (rune == null)
            this.runes.add(new Couple<>(id, 1));
        else
            rune.second += 1;
    }

    public Couple<Integer, Integer> search(int id) {
        for (Couple<Integer, Integer> couple : this.runes)
            if (couple.first == id)
                return couple;
        return null;
    }
}
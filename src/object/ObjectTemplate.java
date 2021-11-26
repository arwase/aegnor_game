package object;

import client.Player;
import client.other.Stats;
import common.ConditionParser;
import common.Formulas;
import common.SocketManager;
import database.Database;
import entity.pet.PetEntry;
import fight.spells.SpellEffect;
import game.world.World;
import kernel.Constant;
import object.entity.SoulStone;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import other.Dopeul;

import java.util.*;

public class ObjectTemplate {

    private int id;
    private String strTemplate;
    private String name;
    private int type;
    private int level;
    private int pod;
    private int price;
    private int panoId;
    private String conditions;
    private int PACost, POmin, POmax, tauxCC, tauxEC,
            bonusCC;
    private boolean isTwoHanded;
    private long sold;
    private int avgPrice;
    private int points, newPrice;
    private ArrayList<ObjectAction> onUseActions;
    private int boutique;

    public String toString() {
        return id + "";
    }

    public ObjectTemplate(int id, String strTemplate, String name, int type,
                          int level, int pod, int price, int panoId, String conditions,
                          String armesInfos, int sold, int avgPrice, int points, int newPrice, int boutique) {
        this.id = id;
        this.strTemplate = strTemplate;
        this.name = name;
        this.type = type;
        this.level = level;
        this.pod = pod;
        this.price = price;
        this.panoId = panoId;
        this.conditions = conditions;
        this.PACost = -1;
        this.POmin = 1;
        this.POmax = 1;
        this.tauxCC = 100;
        this.tauxEC = 2;
        this.bonusCC = 0;
        this.sold = sold;
        this.avgPrice = avgPrice;
        this.points = points;
        this.newPrice = newPrice;
        this.boutique = boutique;
        if(armesInfos.isEmpty()) return;
        try {
            String[] infos = armesInfos.split(";");
            PACost = Integer.parseInt(infos[0]);
            POmin = Integer.parseInt(infos[1]);
            POmax = Integer.parseInt(infos[2]);
            tauxCC = Integer.parseInt(infos[3]);
            tauxEC = Integer.parseInt(infos[4]);
            bonusCC = Integer.parseInt(infos[5]);
            isTwoHanded = infos[6].equals("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInfos(String strTemplate, String name, int type, int level, int pod, int price, int panoId, String conditions, String armesInfos, int sold, int avgPrice, int points, int newPrice, int boutique) {
        this.strTemplate = strTemplate;
        this.name = name;
        this.type = type;
        this.level = level;
        this.pod = pod;
        this.price = price;
        this.panoId = panoId;
        this.conditions = conditions;
        this.PACost = -1;
        this.POmin = 1;
        this.POmax = 1;
        this.tauxCC = 100;
        this.tauxEC = 2;
        this.bonusCC = 0;
        this.sold = sold;
        this.avgPrice = avgPrice;
        this.points = points;
        this.newPrice = newPrice;
        this.boutique = boutique;
        try {
            String[] infos = armesInfos.split(";");
            PACost = Integer.parseInt(infos[0]);
            POmin = Integer.parseInt(infos[1]);
            POmax = Integer.parseInt(infos[2]);
            tauxCC = Integer.parseInt(infos[3]);
            tauxEC = Integer.parseInt(infos[4]);
            bonusCC = Integer.parseInt(infos[5]);
            isTwoHanded = infos[6].equals("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrTemplate() {
        return strTemplate;
    }

    public void setStrTemplate(String strTemplate) {
        this.strTemplate = strTemplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPod() {
        return pod;
    }

    public void setPod(int pod) {
        this.pod = pod;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPanoId() {
        return panoId;
    }

    public void setPanoId(int panoId) {
        this.panoId = panoId;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public int getPACost() {
        return PACost;
    }

    public void setPACost(int pACost) {
        PACost = pACost;
    }

    public int getPOmin() {
        return POmin;
    }

    public void setPOmin(int pOmin) {
        POmin = pOmin;
    }

    public int getPOmax() {
        return POmax;
    }

    public void setPOmax(int pOmax) {
        POmax = pOmax;
    }

    public int getTauxCC() {
        return tauxCC;
    }

    public void setTauxCC(int tauxCC) {
        this.tauxCC = tauxCC;
    }

    public int getTauxEC() {
        return tauxEC;
    }

    public void setTauxEC(int tauxEC) {
        this.tauxEC = tauxEC;
    }

    public int getBonusCC() {
        return bonusCC;
    }

    public void setBonusCC(int bonusCC) {
        this.bonusCC = bonusCC;
    }

    public boolean isTwoHanded() {
        return isTwoHanded;
    }

    public void setTwoHanded(boolean isTwoHanded) {
        this.isTwoHanded = isTwoHanded;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public long getSold() {
        return this.sold;
    }

    public int getPoints() {
        return this.points;
    }

    public void addAction(ObjectAction A) {
        if(this.onUseActions == null)
            this.onUseActions = new ArrayList<>();
        this.onUseActions.add(A);
    }

    public ArrayList<ObjectAction> getOnUseActions() {
        return onUseActions == null ? new ArrayList<>() : onUseActions;
    }

    public GameObject createNewCertificat(GameObject obj) {
        int id = Database.getStatics().getObjectData().getNextId();
        GameObject item = null;
        if (getType() == Constant.ITEM_TYPE_CERTIFICAT_CHANIL) {
            PetEntry myPets = World.world.getPetsEntry(obj.getGuid());
            Map<Integer, String> txtStat = new HashMap<Integer, String>();
            Map<Integer, String> actualStat = obj.getTxtStat();
            if (actualStat.containsKey(Constant.STATS_PETS_PDV))
                txtStat.put(Constant.STATS_PETS_PDV, actualStat.get(Constant.STATS_PETS_PDV));
            if (actualStat.containsKey(Constant.STATS_PETS_DATE))
                txtStat.put(Constant.STATS_PETS_DATE, myPets.getLastEatDate()
                        + "");
            if (actualStat.containsKey(Constant.STATS_PETS_POIDS))
                txtStat.put(Constant.STATS_PETS_POIDS, actualStat.get(Constant.STATS_PETS_POIDS));
            if (actualStat.containsKey(Constant.STATS_PETS_EPO))
                txtStat.put(Constant.STATS_PETS_EPO, actualStat.get(Constant.STATS_PETS_EPO));
            if (actualStat.containsKey(Constant.STATS_PETS_REPAS))
                txtStat.put(Constant.STATS_PETS_REPAS, actualStat.get(Constant.STATS_PETS_REPAS));
            item = new GameObject(id, getId(), 1, Constant.ITEM_POS_NO_EQUIPED, obj.getStats(), new ArrayList<SpellEffect>(), new HashMap<Integer, Integer>(), txtStat, 0, 0, -1);
            World.world.removePetsEntry(obj.getGuid());
            Database.getStatics().getPetData().delete(obj.getGuid());
        }
        return item;
    }

    public GameObject createNewFamilier(GameObject obj) {
        int id = Database.getStatics().getObjectData().getNextId();
        Map<Integer, String> stats = new HashMap<>();
        stats.putAll(obj.getTxtStat());

        GameObject object = new GameObject(id, getId(), 1, Constant.ITEM_POS_NO_EQUIPED, obj.getStats(), new ArrayList<>(), new HashMap<>(), stats, 0, 0, -1);

        long time = System.currentTimeMillis();
        World.world.addPetsEntry(new PetEntry(id, getId(), time, 0, Integer.parseInt(stats.get(Constant.STATS_PETS_PDV), 16), Integer.parseInt(stats.get(Constant.STATS_PETS_POIDS), 16), !stats.containsKey(Constant.STATS_PETS_EPO)));
        Database.getStatics().getPetData().add(id, time, getId());
        return object;
    }

    public GameObject createNewBenediction(int turn) {
        int id = Database.getStatics().getObjectData().getNextId();
        GameObject item = null;
        Stats stats = generateNewStatsFromTemplate(getStrTemplate(), true, 0);
        stats.addOneStat(Constant.STATS_TURN, turn);
        item = new GameObject(id, getId(), 1, Constant.ITEM_POS_BENEDICTION, stats, new ArrayList<>(), new HashMap<>(), new HashMap<>(), 0, 0, -1);
        return item;
    }

    public GameObject createNewMalediction() {
        int id = Database.getStatics().getObjectData().getNextId();
        Stats stats = generateNewStatsFromTemplate(getStrTemplate(), true, 0);
        stats.addOneStat(Constant.STATS_TURN, 1);
        return new GameObject(id, getId(), 1, Constant.ITEM_POS_MALEDICTION, stats, new ArrayList<>(), new HashMap<>(), new HashMap<>(), 0, 0, -1);
    }

    public GameObject createNewRoleplayBuff() {
        int id = Database.getStatics().getObjectData().getNextId();
        Stats stats = generateNewStatsFromTemplate(getStrTemplate(), true, 0);
        stats.addOneStat(Constant.STATS_TURN, 1);
        return new GameObject(id, getId(), 1, Constant.ITEM_POS_ROLEPLAY_BUFF, stats, new ArrayList<>(), new HashMap<>(), new HashMap<>(), 0, 0, -1);
    }

    public GameObject createNewCandy(int turn) {
        int id = Database.getStatics().getObjectData().getNextId();
        GameObject item = null;
        Stats stats = generateNewStatsFromTemplate(getStrTemplate(), true, 0);
        stats.addOneStat(Constant.STATS_TURN, turn);
        item = new GameObject(id, getId(), 1, Constant.ITEM_POS_BONBON, stats, new ArrayList<SpellEffect>(), new HashMap<Integer, Integer>(), new HashMap<Integer, String>(), 0, 0, -1);
        return item;
    }

    public GameObject createNewFollowPnj(int turn) {
        int id = Database.getStatics().getObjectData().getNextId();
        GameObject item = null;
        Stats stats = generateNewStatsFromTemplate(getStrTemplate(), true, 0);
        stats.addOneStat(Constant.STATS_TURN, turn);
        stats.addOneStat(148, 0);
        item = new GameObject(id, getId(), 1, Constant.ITEM_POS_PNJ_SUIVEUR, stats, new ArrayList<SpellEffect>(), new HashMap<Integer, Integer>(), new HashMap<Integer, String>(), 0, 0, -1);
        return item;
    }
    private final Integer[] ItemsRarityAllowed = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,16,17,19,20,21,22,23,81,82};
    public GameObject createNewItem(int qua, boolean useMax, int rarity) {
        int id = Database.getStatics().getObjectData().getNextId();
        GameObject item;
        List<Integer> verif = new ArrayList<>(Arrays.asList(ItemsRarityAllowed));
        if(!verif.contains(getType()))
        {
            rarity = 0;
        }
        if (getType() == Constant.ITEM_TYPE_QUETES && (Constant.isCertificatDopeuls(getId()) || getId() == 6653)) {
            Map<Integer, String> txtStat = new HashMap<Integer, String>();
            txtStat.put(Constant.STATS_DATE, System.currentTimeMillis() + "");
            item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<SpellEffect>(), new HashMap<Integer, Integer>(), txtStat, 0,0, -1);
        } else if (this.getId() == 10207) {
            item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<SpellEffect>(), new HashMap<Integer, Integer>(), Dopeul.generateStatsTrousseau(), 0,0, -1);
        } else if (getType() == Constant.ITEM_TYPE_FAMILIER) {
            item = new GameObject(id, getId(), 1, Constant.ITEM_POS_NO_EQUIPED, (useMax ? generateNewStatsFromTemplate(World.world.getPets(this.getId()).getJet(), false, 0) : new Stats(false, null)), new ArrayList<>(), new HashMap<>(), World.world.getPets(getId()).generateNewtxtStatsForPets(), 0,0, -1);
            //Ajouter du Pets_data SQL et World
            long time = System.currentTimeMillis();
            World.world.addPetsEntry(new PetEntry(id, getId(), time, 0, 10, 0, false));
            System.out.println("id obj créé + "+ id + " itemasset + " + getId() + "sans duppli verif " );
            Database.getStatics().getPetData().add(id, time, getId());

        } else if(getType() == Constant.ITEM_TYPE_CERTIF_MONTURE) {
            item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(getStrTemplate(), useMax, 0), getEffectTemplate(getStrTemplate()), new HashMap<>(), new HashMap<>(), 0,0,-1);
        } else {
            if (getType() == Constant.ITEM_TYPE_OBJET_ELEVAGE) {
                item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), getStringResistance(getStrTemplate()), 0,0,-1);
            } else if (Constant.isIncarnationWeapon(getId())) {
                Map<Integer, Integer> Stats = new HashMap<>();
                Stats.put(Constant.ERR_STATS_XP, 0);
                Stats.put(Constant.STATS_NIVEAU, 1);
                item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(getStrTemplate(), useMax, 0), getEffectTemplate(getStrTemplate()), Stats, new HashMap<Integer, String>(), 0,0,-1);
            } else if(getType() == 113)
            {
                item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, getStrTemplate(), 0,0,-1);
            }
            else {
                Map<Integer, String> Stat = new HashMap<>();
                switch (getType()) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        String[] splitted = getStrTemplate().split(",");
                        for (String s : splitted) {
                            String[] stats = s.split("#");
                            int statID = Integer.parseInt(stats[0], 16);
                            if (statID == Constant.STATS_RESIST) {
                                String ResistanceIni = stats[1];
                                Stat.put(statID, ResistanceIni);
                            }
                        }
                        break;
                }
                int rarete = 0;

                if(rarity < 1) {
                    if(ArrayUtils.contains( Constant.ITEM_TYPE_WITH_RARITY, getType() ) ) {
                        rarete = GameObject.getAleaRarity();
                    }
                }
                else {
                    if(ArrayUtils.contains( Constant.ITEM_TYPE_WITH_RARITY, getType() ) )
                    {
                        rarete = rarity;
                    }
                }
                item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(getStrTemplate(), useMax, rarete), getEffectTemplate(getStrTemplate()), new HashMap<Integer, Integer>(), Stat, 0,rarete, -1);
                item.getSpellStats().addAll(this.getSpellStatsTemplate());
            }
        }
        return item;
    }
    public GameObject createNewItemWithoutDuplicationForJobs(Collection<GameObject> objects, int qua, boolean useMax,int chanceimpact) {

        int id = -1;
        GameObject item = null;
        try{

            if (this.getType() == Constant.ITEM_TYPE_QUETES && (Constant.isCertificatDopeuls(this.getId()) || this.getId() == 6653)) {
                Map<Integer, String> txtStat = new HashMap<>();
                txtStat.put(Constant.STATS_DATE, System.currentTimeMillis() + "");
                item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), txtStat, 0,0, -1);
            } else if (this.getId() == 10207) {
                item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), Dopeul.generateStatsTrousseau(), 0,0, -1);
            } else if (this.getType() == Constant.ITEM_TYPE_FAMILIER) {
                item = new GameObject(id, this.getId(), 1, Constant.ITEM_POS_NO_EQUIPED, (useMax ? generateNewStatsFromTemplate(World.world.getPets(this.getId()).getJet(), false,0) : new Stats(false, null)), new ArrayList<>(), new HashMap<>(), World.world.getPets(this.getId()).generateNewtxtStatsForPets(), 0,0, -1);
                //Ajouter du Pets_data SQL et World
                long time = System.currentTimeMillis();
                World.world.addPetsEntry(new PetEntry(id, getId(), time, 0, 10, 0, false));
                Database.getStatics().getPetData().add(id, time, this.getId());
            } else if(this.getType() == Constant.ITEM_TYPE_CERTIF_MONTURE) {
                item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(this.getStrTemplate(), useMax,0), getEffectTemplate(this.getStrTemplate()), new HashMap<>(), new HashMap<>(), 0,0,-1);
            } else {
                if (this.getType() == Constant.ITEM_TYPE_OBJET_ELEVAGE) {
                    item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), getStringResistance(getStrTemplate()), 0,0,-1);
                } else if (Constant.isIncarnationWeapon(this.getId())) {
                    Map<Integer, Integer> Stats = new HashMap<>();
                    Stats.put(Constant.ERR_STATS_XP, 0);
                    Stats.put(Constant.STATS_NIVEAU, 1);
                    item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(this.getStrTemplate(), useMax,0), getEffectTemplate(this.getStrTemplate()), Stats, new HashMap<>(), 0,0,-1);

                } else {
                    Map<Integer, String> Stat = new HashMap<>();
                    switch (this.getType()) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            if( this.getStrTemplate() !="" || !StringUtils.isEmpty(this.getStrTemplate()) || !StringUtils.isBlank(this.getStrTemplate()) ){
                                String[] splitted = getStrTemplate().split(",");
                                for (String s : splitted) {
                                    String[] stats = s.split("#");
                                    int statID = Integer.parseInt(stats[0], 16);
                                    if (statID == Constant.STATS_RESIST) {
                                        String ResistanceIni = stats[1];
                                        Stat.put(statID, ResistanceIni);
                                    }
                                }
                            }
                            break;
                    }
                    int rarete = 0;
                    if( ArrayUtils.contains( Constant.ITEM_TYPE_WITH_RARITY, getType() )  )
                    {
                        rarete = GameObject.getAleaRarity(chanceimpact);
                    }

                    item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(this.getStrTemplate(), useMax,rarete), getEffectTemplate(getStrTemplate()), new HashMap<Integer, Integer>(), Stat, 0,rarete, -1);

                }

            }

            for(GameObject object : objects)
                if(World.world.getConditionManager().stackIfSimilar(object, item, true))
                    return object;

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return item;

    }

    public GameObject createNewItemWithoutDuplication(Collection<GameObject> objects, int qua, boolean useMax) {
        int id = -1;
        GameObject item = null;
        try{

        List<Integer> verif = new ArrayList<>(Arrays.asList(ItemsRarityAllowed));
        if (getType() == Constant.ITEM_TYPE_QUETES && (Constant.isCertificatDopeuls(getId()) || getId() == 6653)) {
            Map<Integer, String> txtStat = new HashMap<>();
            txtStat.put(Constant.STATS_DATE, System.currentTimeMillis() + "");
            item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), txtStat, 0,0,-1);
        } else if (this.getId() == 10207) {
            item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), Dopeul.generateStatsTrousseau(), 0,0,-1);
        } else if (getType() == Constant.ITEM_TYPE_FAMILIER) {
            item = new GameObject(id, getId(), 1, Constant.ITEM_POS_NO_EQUIPED, (useMax ? generateNewStatsFromTemplate(World.world.getPets(this.getId()).getJet(), false, 0) : new Stats(false, null)), new ArrayList<>(), new HashMap<>(), World.world.getPets(getId()).generateNewtxtStatsForPets(), 0,0,-1);
            //Ajouter du Pets_data SQL et World
            long time = System.currentTimeMillis();
            World.world.addPetsEntry(new PetEntry(id, getId(), time, 0, 10, 0, false));
            System.out.println("id obj créé + "+ id + " itemasset + " + getId() + "without rarity " );
            Database.getStatics().getPetData().add(id, time, getId());
        } else if(getType() == Constant.ITEM_TYPE_CERTIF_MONTURE) {
            item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(getStrTemplate(), useMax, 0), getEffectTemplate(getStrTemplate()), new HashMap<>(), new HashMap<>(), 0,0,-1);
        } else {
            if (getType() == Constant.ITEM_TYPE_OBJET_ELEVAGE) {
                item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), getStringResistance(getStrTemplate()), 0,0,-1);
            } else if (Constant.isIncarnationWeapon(getId())) {
                Map<Integer, Integer> Stats = new HashMap<>();
                Stats.put(Constant.ERR_STATS_XP, 0);
                Stats.put(Constant.STATS_NIVEAU, 1);
                item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(getStrTemplate(), useMax, 0), getEffectTemplate(getStrTemplate()), Stats, new HashMap<>(), 0,0,-1);
            } else {
                Map<Integer, String> Stat = new HashMap<>();
                switch (getType()) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        if( this.getStrTemplate() !="" || !StringUtils.isEmpty(this.getStrTemplate()) || !StringUtils.isBlank(this.getStrTemplate()) ) {
                            String[] splitted = getStrTemplate().split(",");
                            for (String s : splitted) {
                                String[] stats = s.split("#");
                                int statID = 64;
                                try {
                                     statID = Integer.parseInt(stats[0], 16);
                                }
                                catch(Exception e) {
                                     statID = 64;
                                    e.printStackTrace();
                                }
                                if (statID == Constant.STATS_RESIST) {
                                    String ResistanceIni = stats[1];
                                    Stat.put(statID, ResistanceIni);
                                }
                            }
                        }
                        break;
                }

                int rarete = 0;
                if( ArrayUtils.contains( Constant.ITEM_TYPE_WITH_RARITY, getType() )  )
                {
                    rarete = GameObject.getAleaRarity();
                }

                item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(getStrTemplate(), useMax, rarete), getEffectTemplate(getStrTemplate()), new HashMap<Integer, Integer>(), Stat, 0, rarete,-1);
                item.getSpellStats().addAll(this.getSpellStatsTemplate());
            }
        }

        for(GameObject object : objects) {
            if (World.world.getConditionManager().stackIfSimilar2(object, item, true)) {
                return object;
            }
        }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    private Map<Integer, String> getStringResistance(String statsTemplate) {
        Map<Integer, String> Stat = new HashMap<Integer, String>();
        String[] splitted = statsTemplate.split(",");

        for (String s : splitted) {
            String[] stats = s.split("#");
            int statID = Integer.parseInt(stats[0], 16);
            String ResistanceIni = stats[1];
            Stat.put(statID, ResistanceIni);
        }
        return Stat;
    }

    public ArrayList<String> getSpellStatsTemplate() {
        final ArrayList<String> spellStats = new ArrayList<>();

        if(!this.getStrTemplate().isEmpty()) {
            for (String stats : this.getStrTemplate().split(",")) {
                String[] split = stats.split("#");
                int id = Integer.parseInt(split[0], 16);

                if (id >= 281 && id <= 294) {
                    spellStats.add(stats);
                }
            }
        }
        return spellStats;
    }

    public Stats generateNewStatsFromTemplate(String statsTemplate,
                                              boolean useMax, int rarete) {

        Stats itemStats = new Stats(false, null);
        //Si stats Vides
        if (statsTemplate.equals("") || statsTemplate == null)
            return itemStats;


        String[] splitted = statsTemplate.split(",");
        for (String s : splitted) {
            String[] stats = s.split("#");
            int statID = Integer.parseInt(stats[0], 16);
            boolean follow = true;

            for (int a : Constant.ARMES_EFFECT_IDS)
                //Si c'est un Effet Actif
                if (a == statID)
                    follow = false;
            if (!follow)//Si c'ï¿½tait un effet Actif d'arme
                continue;
            if (statID == Constant.STATS_RESIST)
                continue;
            boolean isStatsInvalid = false;
            switch (statID) {
                case 110:
                case 139:
                case 605:
                case 614:
                    isStatsInvalid = true;
                    break;
                case 615:
                    itemStats.addOneStat(statID, Integer.parseInt(stats[3], 16));
                    break;
            }
            if (isStatsInvalid)
                continue;
            String jet = "";
            int value = 1;
            try {
                switch (rarete) {
                    case 0:
                    case 1:{
                        if(stats.length > 4) {
                            jet = stats[4];
                            value = Formulas.getRandomJet(jet);
                            if (useMax) {
                                try {
                                    //on prend le jet max
                                    int min = Integer.parseInt(stats[1], 16);
                                    int max = Integer.parseInt(stats[2], 16);
                                    value = min;
                                    if (max != 0)
                                        value = max;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    value = Formulas.getRandomJet(jet);
                                }
                            }
                        }
                        break;
                    }
                    case 2 :{
                        if(stats.length > 4) {
                            jet = stats[4];
                            int min = Integer.parseInt(stats[1], 16);
                            int max = Integer.parseInt(stats[2], 16);
                            value = Formulas.getRandomJetWithRarity(min, max, rarete);
                            if (useMax) {
                                try {
                                    //on prend le jet max
                                    min = Integer.parseInt(stats[1], 16);
                                    max = Integer.parseInt(stats[2], 16);
                                    value = min;
                                    if (max != 0)
                                        value = max;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    value = Formulas.getRandomJet(jet);
                                }
                            }
                        }
                        break;
                    }
                    case 4 :
                    case 5 : {
                        if(stats.length > 4) {
                            jet = stats[4];
                            int min = Integer.parseInt(stats[1], 16);
                            int max = Integer.parseInt(stats[2], 16);
                            value = Formulas.getRandomJetWithRarity(min, max, rarete);
                            if (useMax) {
                                try {
                                    value = Formulas.getRandomJetWithRarity(min, max, 5);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    value = Formulas.getRandomJetWithRarity(min, max, rarete);
                                }
                            }
                        }
                        break;
                    }
                    case 3 :{
                        try {
                            //on prend le jet max
                            int min = Integer.parseInt(stats[1], 16);
                            int max = Integer.parseInt(stats[2], 16);
                            value = min;
                            if (max != 0)
                                value = max;
                        } catch (Exception e) {
                            e.printStackTrace();
                            value = Formulas.getRandomJet(jet);
                        }
                        break;
                    }
                }
                if(value > 0) {
                    itemStats.addOneStat(statID, value);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return itemStats;
    }

    private ArrayList<SpellEffect> getEffectTemplate(String statsTemplate) {
        ArrayList<SpellEffect> Effets = new ArrayList<SpellEffect>();
        if (statsTemplate.equals(""))
            return Effets;

        String[] splitted = statsTemplate.split(",");
        for (String s : splitted) {
            String[] stats = s.split("#");
            int statID = Integer.parseInt(stats[0], 16);
            for (int a : Constant.ARMES_EFFECT_IDS) {
                if (a == statID) {
                    int id = statID;
                    String min = stats[1];
                    String max = stats[2];
                    String jet = stats[4];
                    String args = min + ";" + max + ";-1;-1;0;" + jet;
                    Effets.add(new SpellEffect(id, args, 0, -1));
                }
            }
            switch (statID) {
                case 110:
                case 139:
                case 605:
                case 614:
                    String min = stats[1];
                    String max = stats[2];
                    String jet = stats[4];
                    String args = min + ";" + max + ";-1;-1;0;" + jet;
                    Effets.add(new SpellEffect(statID, args, 0, -1));
                    break;
            }
        }
        return Effets;
    }

    public String parseItemTemplateStats() {
        return getId() + ";" + getStrTemplate() + (this.newPrice > 0 ? ";" + this.newPrice : "");
    }

    public void applyAction(Player player, Player target, int objectId, short cellId) {
        if (World.world.getGameObject(objectId) == null) return;
        if (World.world.getGameObject(objectId).getTemplate().getType() == 85) {
            if (!SoulStone.isInArenaMap(player.getCurMap().getId()))
                return;

            SoulStone soulStone = (SoulStone) World.world.getGameObject(objectId);

            player.getCurMap().spawnNewGroup(true, player.getCurCell().getId(), soulStone.parseGroupData(), "MiS=" + player.getId());
            SocketManager.GAME_SEND_Im_PACKET(player, "022;" + 1 + "~" + World.world.getGameObject(objectId).getTemplate().getId());
            player.removeItem(objectId, 1, true, true);
        } else {
            for (ObjectAction action : this.getOnUseActions())
                action.apply(player, target, objectId, cellId);
        }
    }

    public synchronized void newSold(int amount, int price) {
        long oldSold = getSold();
        sold += amount;
        avgPrice = (int) ((getAvgPrice() * oldSold + price) / getSold());
    }

    public GameObject createNewItemWithoutDuplicationWithrarity(Collection<GameObject> objects, int qua, boolean useMax, int rarity) {

        int id = -1;
        GameObject item = null;
        try{

            if (this.getType() == Constant.ITEM_TYPE_QUETES && (Constant.isCertificatDopeuls(this.getId()) || this.getId() == 6653)) {
                Map<Integer, String> txtStat = new HashMap<>();
                txtStat.put(Constant.STATS_DATE, System.currentTimeMillis() + "");
                item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), txtStat, 0,0,-1);
            } else if (this.getId() == 10207) {
                item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), Dopeul.generateStatsTrousseau(), 0,0,-1);
            } else if (this.getType() == Constant.ITEM_TYPE_FAMILIER) {
                item = new GameObject(id, this.getId(), 1, Constant.ITEM_POS_NO_EQUIPED, (useMax ? generateNewStatsFromTemplate(World.world.getPets(this.getId()).getJet(), false,0) : new Stats(false, null)), new ArrayList<>(), new HashMap<>(), World.world.getPets(this.getId()).generateNewtxtStatsForPets(), 0,0,-1);
                //Ajouter du Pets_data SQL et World
                long time = System.currentTimeMillis();
                World.world.addPetsEntry(new PetEntry(id, getId(), time, 0, 10, 0, false));
                System.out.println("id obj créé + "+ id + " itemasset + " + getId() + "with rarity " );
                Database.getStatics().getPetData().add(id, time, this.getId());
            } else if(this.getType() == Constant.ITEM_TYPE_CERTIF_MONTURE) {
                item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(this.getStrTemplate(), useMax,0), getEffectTemplate(this.getStrTemplate()), new HashMap<>(), new HashMap<>(), 0,0,-1);
            } else {
                if (this.getType() == Constant.ITEM_TYPE_OBJET_ELEVAGE) {
                    item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, new Stats(false, null), new ArrayList<>(), new HashMap<>(), getStringResistance(getStrTemplate()), 0,0,-1);
                } else if (Constant.isIncarnationWeapon(this.getId())) {
                    Map<Integer, Integer> Stats = new HashMap<>();
                    Stats.put(Constant.ERR_STATS_XP, 0);
                    Stats.put(Constant.STATS_NIVEAU, 1);
                    item = new GameObject(id, this.getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(this.getStrTemplate(), useMax,0), getEffectTemplate(this.getStrTemplate()), Stats, new HashMap<>(), 0,0,-1);

                } else {
                    Map<Integer, String> Stat = new HashMap<>();
                    switch (this.getType()) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            if( this.getStrTemplate() !="" || !StringUtils.isEmpty(this.getStrTemplate()) || !StringUtils.isBlank(this.getStrTemplate()) ){
                                String[] splitted = getStrTemplate().split(",");
                                for (String s : splitted) {
                                    String[] stats = s.split("#");
                                    int statID = Integer.parseInt(stats[0], 16);
                                    if (statID == Constant.STATS_RESIST) {
                                        String ResistanceIni = stats[1];
                                        Stat.put(statID, ResistanceIni);
                                    }
                                }
                            }
                            break;
                    }
                    int rarete = rarity;

                    item = new GameObject(id, getId(), qua, Constant.ITEM_POS_NO_EQUIPED, generateNewStatsFromTemplate(this.getStrTemplate(), useMax,rarete), getEffectTemplate(getStrTemplate()), new HashMap<Integer, Integer>(), Stat, 0,rarete,-1);

                }

            }

            for(GameObject object : objects)
                if(World.world.getConditionManager().stackIfSimilar2(object, item, true))
                    return object;

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return item;

    }

    public int getBoutique() {
        return boutique;
    }
}
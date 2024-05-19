public abstract class FantasyCharacter implements Fighter {
    private String name;		//имя
    private int healthPoints;	//очки здоровья
    private int strength;		//сила
    private int dexterity;		//ловкость
    private int xp;				//опыт
    private int gold;			//золото

    public FantasyCharacter(String name, int healthPoints, int strength, int dexterity,
                            int xp, int gold) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.dexterity = dexterity;
        this.xp = xp;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    private int getRandomValue() {
        return (int)(Math.random() * 100);
    }

    @Override
    public int attack() {
        if (3 * dexterity > getRandomValue()) return strength;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, healthPoints);
    }
}

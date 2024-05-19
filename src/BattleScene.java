public class BattleScene {
    public void fight(FantasyCharacter hero, FantasyCharacter monster,
                      Main.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while(!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                if(turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                }
                else {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private boolean makeHit(FantasyCharacter defender, FantasyCharacter attacker,
                            Main.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHealthPoints() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!",
                    attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...",
                    defender.getName(), defenderHealth));
        }
        else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }

        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("Вы пали в бою");
            fightCallback.fightLost();
            return true;
        }
        else if (defenderHealth <= 0) {
            System.out.println(String.format("Победа! Вам %d опыта и %d золота",
                    defender.getXp(), defender.getGold()));
            attacker.setXp(defender.getXp() + attacker.getXp());
            attacker.setGold(defender.getGold() + attacker.getGold());
            fightCallback.fightWin();
            return true;
        }

        defender.setHealthPoints(defenderHealth);
        return false;
    }
}

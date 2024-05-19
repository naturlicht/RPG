import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    interface FightCallback {
        void fightLost();
        void fightWin();
    }

    private static BufferedReader br;
    private static FantasyCharacter player = null;
    private static BattleScene battleScene = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();

        System.out.println("Введите имя персонажа:");

        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String str) throws IOException {
        if (player == null) {
            player = new Hero(str, 100, 20, 20, 0, 0);
            System.out.println(String.format("%s спасет мир", player.getName()));
        }

        printNavigation();

        switch(str) {
            case "1":
                System.out.println("Торговец еще не приехал");
                command(br.readLine());
                break;
            case "2":
                commitFight();
                break;
            case "3":
                System.out.println("Конец игры");
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет":
                printNavigation();
                command(br.readLine());
        }

        command(br.readLine());
    }

    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь опыта: %d, золота: %d," +
                                " здоровья: %d", player.getName(), player.getXp(), player.getGold(),
                        player.getHealthPoints()));
                System.out.println("Продолжить или вернуться? (да/нет)");

                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static FantasyCharacter createMonster() {
        int random = (int)(Math.random() * 10);
        if (random % 2 == 0) {
            return new Goblin("Гоблин", 50, 10, 10, 100, 20);
        }
        else return new Skeleton("Скелет", 25, 20, 20, 100, 10);
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }
}
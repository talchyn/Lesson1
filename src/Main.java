import java.util.Random;

public class Main {
    public static int bossHealth = 300;
    public static int bossDamage = 50;
    public static int medicHealingPack = 100;
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int[] heroesHealth = {250, 220, 230, 500};
    public static int[] heroesDamage = {25, 20, 30, 0};
    public static int medic;
    public static String bossDefenceType = "";

    public static void main(String[] args) {
        printStatistics();


        while (!isGameFinish()) {
            round();
        }
    }

    public static void printStatistics() {
        System.out.println("________");
        System.out.println("Boss health- " + bossHealth + " Damage -" + " [" + bossDamage + "] ");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println("Heroes health " + heroesAttackType[i] +
                    " " + heroesHealth[i] +
                    " Damage - " + " [" + heroesDamage[i] + "]");
        }
        System.out.println("________");
    }

    public static boolean isGameFinish() {
        if (bossHealth <= 0) {
            System.out.println("HEROES WON");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("BOSS WON");
        }
        return allHeroesDead;
    }

    public static void round() {
        chooseBosseDefence();
        bossHits();
        heroesHits();
        healHero();
        printStatistics();
    }

    public static void healHero() {
        if(heroesHealth[medic] > 0) {
            for(int i =0; i < heroesAttackType.length-1; i++) {
                if(heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(100);
                    heroesHealth[i] += randomIndex;
                    System.out.println("Medic health - [" + heroesAttackType[i] + "] on [" + randomIndex + "] HH");
                    break;
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    bossHealth = bossHealth + heroesDamage[i];
                }
                if (bossHealth < heroesDamage[i]) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static void chooseBosseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }
}

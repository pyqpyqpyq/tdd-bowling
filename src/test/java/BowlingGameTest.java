import java.text.DecimalFormat;
import java.util.Scanner;

class Test01 {

    public static void main(String[] args) {
        DecimalFormat formatter = new DecimalFormat("00");
        Scanner sc = new Scanner(System.in);

        int firstHit = 0, secondHit = 0;
        BowlingGame game = new BowlingGame();
        for(int i = 0; i < BowlingGame.TOTAL_ROUND; i++) {
            System.out.println("=====第" + formatter.format(i + 1) + "回合=====");
            System.out.print("第一次击中: ");
            firstHit = sc.nextInt();
            game.throwTheBall(firstHit);
            if(firstHit < 10) { // 第一次没有全中就要打第二颗
                System.out.print("第二次击中: ");
                secondHit = sc.nextInt();
                game.throwTheBall(secondHit);
            }
            if(i == BowlingGame.TOTAL_ROUND - 1) {
                // 第十局如果全中或补中则要继续投掷球
                if(firstHit == 10 || firstHit + secondHit == 10) {
                    System.out.print("加一球: ");
                    int lastOne = sc.nextInt();
                    game.setLastOneHit(lastOne);
                    if(firstHit == 10) {    // 第一颗球全中则还要再投掷一颗球
                        System.out.print("加二球: ");
                        int lastTow = sc.nextInt();
                        game.setLastTwoHit(lastTow);
                    }
                }
            }
        }

        game.calcScore();   // 计算成绩

        // 打印每一局的累积得分
        for(int i = 0; i < BowlingGame.TOTAL_ROUND; i++) {
            System.out.print(game.getScoreByRound(i) + "\t");
        }

        System.out.println("\n总分: " + game.getTotalScore());    // 打印总分数

        sc.close();
    }
}

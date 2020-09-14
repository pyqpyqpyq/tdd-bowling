//reference
//https://www.jianshu.com/p/4c737ce5baac
 public class BowlingGame {
    public static final int TOTAL_ROUND = 10;   // 总局数

    private int[] scores;                       // 记录每局得分的数组
    private int[][] numberOfHits;               // 记录每局两球击倒瓶子个数的数组
    private int lastOneHit, lastTwoHit;         // 最后两次击球
    private int totalScore;                     // 总成绩

    private int currentRound;                   // 当前第几局
    private boolean firstHit;                   // 是否是每局的第一次击球

    public BowlingGame() {
        scores = new int[TOTAL_ROUND];
        numberOfHits = new int[TOTAL_ROUND][2];
        firstHit = true;
    }

    /**
     * 扔出一颗球
     * @param num 打倒瓶子的数量
     */
    public void throwTheBall(int num) {
        if(firstHit) {  // 第一颗  球
            numberOfHits[currentRound][0] = num;
            if(num != 10) {
                firstHit = false;   // 第一颗球没有全中则要打第二颗
            }
            else {
                currentRound += 1;  // 第一颗球全中直接进入下一局
            }
        }
        else {
            numberOfHits[currentRound][1] = num;
            currentRound += 1;      // 打完第二颗球进入下一局
            firstHit = true;        // 准备投掷下一局的第一颗球
        }
    }

    public void calcScore() {
        // from round 1 to round 9
        for(int i = 0; i < numberOfHits.length - 1; i++) {
            if(numberOfHits[i][0] == 10) {  // Strike(全中)
                scores[i] += 10;
                if(numberOfHits[i + 1][0] == 10) {
                    scores[i] += 10;
                    if(i < 8) {
                        scores[i] += numberOfHits[i + 2][0];
                    }
                    else {
                        scores[i] += lastOneHit;
                    }
                }
                else {
                    scores[i] += numberOfHits[i + 1][0] + numberOfHits[i + 1][1];
                }
            }
            else if(numberOfHits[i][0] + numberOfHits[i][1] == 10) { // Spare(补中)
                scores[i] += 10;
                scores[i] += numberOfHits[i + 1][0];
            }
            else {
                scores[i] += numberOfHits[i][0] + numberOfHits[i][1];
            }
        }
        // the last round
        if(numberOfHits[9][0] == 10) {
            scores[9] += 10;
            scores[9] += lastOneHit + lastTwoHit;
        }
        else if(numberOfHits[9][0] + numberOfHits[9][1] == 10) {
            scores[9] += 10;
            scores[9] += lastOneHit;
        }
        else {
            scores[9] += numberOfHits[9][0] + numberOfHits[9][1];
        }
    }

    /**
     * 设置最后第一球击倒瓶子数
     * @param num 击倒瓶子数
     */
    public void setLastOneHit(int num) {
        this.lastOneHit = num;
    }

    /**
     * 设置最后第二球击倒瓶子数
     * @param num 瓶子数
     */
    public void setLastTwoHit(int num) {
        this.lastTwoHit = num;
    }

    /**
     * 计算总分数
     * @return 总分数
     */
    public int getTotalScore() {
        for(int i = 0; i < scores.length; i++) {
            totalScore += scores[i];
        }
        return totalScore;
    }

    /**
     * 获得指定局数的累积得分
     * @param round 局数
     * @return 从第一局到指定局数的累积得分
     */
    public int getScoreByRound(int round) {
        int sum = 0;
        for(int i = 0; i <= round; i++) {
            sum += scores[i];
        }
        return sum;
    }
}

package TankGame.main;

import java.io.*;
import java.util.Vector;

/**
 * @author myry
 * @date 2024-55-29 21:55
 * <p>
 * 该类用于记录相关信息 和 文件交互
 */

@SuppressWarnings({"all"})
public class Recorder {
    //定义变量 记录我方击毁敌方坦克数量
    private static int allEnemyTankNum = 0;

    //定义向量 记录敌人坦克的坐标 通过MyPanel传入
    private static Vector<EnemyTank> enemyTanks = null;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //IO对象
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "/Users/myry/Documents/MyJavaProject/Games/src/TankGame/record/myRecrod.txt";


    //Node节点 保存信息
    private static Vector<Node> nodes = new Vector<>();

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克 击毁一辆 那么++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }

    //增加一个方法 把数量 保存在文件中
    public static void keepRecord() {

        try {

            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "");
            bw.newLine();

            //遍历敌人坦克 获取相应信息
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive) {
                    //保存
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
                    //写入
                    bw.write(record);
                    bw.newLine();
                }
            }

        } catch (IOException io) {

            io.printStackTrace();

        } finally {

            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    //增加一个方法 用于读取文件信息
    //该方法 在继续上句的时候 启动
    public static Vector<Node> getNodesAndEnemyTank() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            //读取第一行
            allEnemyTankNum = Integer.parseInt(br.readLine());

            //循环读取文件 生成向量
            String line = ""; //255 40 3
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" "); //根据空格进行分割
                Node node = new Node(Integer.parseInt(xyd[0]),
                        Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                nodes.add(node);//放入向量
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }
}

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame jf =new JFrame("贪吃蛇小游戏");
        jf.setBounds(20,20,920,720);
        jf.setResizable(false);
        jf.add(new GameJpanel());
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

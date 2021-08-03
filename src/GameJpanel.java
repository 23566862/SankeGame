import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJpanel extends JPanel implements KeyListener, ActionListener {
    int lenth;//小蛇的长度（包括蛇头）
    int score;
    int [] snakeX =new int[255]; //蛇的x位置
    int [] snakeY =new int[255];//蛇的y位置
    String fx; //蛇头的方向 R：右，L：左 U：上 D：下
    boolean isStart =false; //游戏是否停止
    boolean isfail =false; //游戏是否失败
    //食物定义
    int foodX;
    int foodY;
    Random dom =new Random();
    //定时器可以刷新频率让小蛇动起来
    Timer time =new Timer(100,this);
    public GameJpanel(){
        inint();
        //获取键盘监听事件
        this.setFocusable(true);//获取键盘焦点
        this.addKeyListener(this);
        time.start();
    }
    //初始化方法
    public void inint(){
        score=0;
        lenth =3;
        fx ="R";
        snakeX[0]=100;snakeY[0]=100; //头部位置
        snakeX[1]=75;snakeY[1]=100; //第一节位置
        snakeX[2]=50;snakeY[2]=100; //第二节位置
        //初始化食物
        foodX=25+25*dom.nextInt(34);
        foodY=75+25*dom.nextInt(24);
        //食物不和身体重合
        for (int i = 0; i <lenth ; i++) {
            if (foodX ==snakeX[i] && foodY==snakeY[i]){
                foodX=25+25*dom.nextInt(34);
                foodY=75+25*dom.nextInt(24);
            }
        }
    }
    //画板添加
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);
        //绘制标签
        //g.setColor(Color.RED);
        g.setFont(new Font("微软雅黑", Font.BOLD, 25));
        g.drawString("班级:19软件2",50,42);
        g.drawString("姓名：赖如发",400,42);
        g.drawString("得分："+score,700,42);
        g.fillRect(25, 75, 850, 600);
        //绘制游戏区域
        //绘制食物
        Data.foodImage.paintIcon(this,g,foodX,foodY);
        //绘制静态小蛇
        //根据键盘方向改变头的方向
        if (fx.equals("R")){
            Data.rightImage.paintIcon(this,g,snakeX[0],snakeY[0]);//
        }else if(fx.equals("L")){
            Data.leftImage.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("D")){
            Data.downImage.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("U")){
            Data.upImage.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        //根据蛇的长度添加身体
        for (int i = 1; i <lenth ; i++) {
            Data.bodyImage.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        /*Data.bodyImage.paintIcon(this,g,snakeX[1],snakeY[1]);//身体
        Data.bodyImage.paintIcon(this,g,snakeX[2],snakeY[2]);*/
        if (isStart==false){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,400);
        }
        //游戏失败
        if (isfail ==true){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏结束,得分为:"+score+",空格重新开始",230,400);
        }
    }




    //键盘监听
    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下，未释放
        //获取键盘按下的键进行操作
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (isfail == true){
                isfail =false;
                inint();//初始化游戏
            }
            isStart=!isStart;
            repaint();//刷新界面
        }

        //键盘控制方向,不能反方向走
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            fx=fx.equals("R")?"R":"L";
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            //fx="D";
            fx=fx.equals("U")?"U":"D";
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            //fx="U";
            fx=fx.equals("D")?"D":"U";
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            //fx="R";
            fx=fx.equals("L")?"L":"R";

        }

    }

    //定时器可以刷新频率让小蛇动起来 timer类
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏启动,并且游戏没有结束
        if (isStart ==true && isfail==false){
            for (int i =lenth-1; i >0 ; i--) {
                snakeX[i]=snakeX[i-1];
                snakeY[i]=snakeY[i-1];
            }

            if (fx.equals("R")){
                snakeX[0]=snakeX[0]+25; //头的位置往右移
                if (snakeX[0]>850){isfail=true; }//边界判断
            }else if (fx.equals("L")){
                snakeX[0]=snakeX[0]-25; //头的位置往左移
                if (snakeX[0]<25){
                    isfail=true;
                }
            }else if (fx.equals("U")){
                snakeY[0]=snakeY[0]-25; //头的位置往上移
                if(snakeY[0]<65){
                    isfail=true;
                }
            }else if (fx.equals("D")){
                snakeY[0]=snakeY[0]+25; //头的位置往下移
                if(snakeY[0]>650){
                    isfail=true;
                }
            }

            //小蛇吃食物判断，判断蛇头位置和食物位置
            if (foodX ==snakeX[0] && foodY ==snakeY[0]){
                lenth++;    //长度+1
                score =score+10;
                foodX=25+25*dom.nextInt(34);
                foodY=75+25*dom.nextInt(24);
                //食物不和身体重合
                for (int i = 0; i <lenth ; i++) {
                    if (foodX ==snakeX[i] && foodY==snakeY[i]){
                        foodX=25+25*dom.nextInt(34);
                        foodY=75+25*dom.nextInt(24);
                    }
                }
                repaint();
            }

            //蛇撞到身体判断
            for (int i = 1; i <lenth ; i++) {
                if (snakeX[0] ==snakeX[i] & snakeY[0] ==snakeY[i]){
                    isfail=true;
                }
            }

            repaint();
            time.start();
        }
    }





    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下
    }


}

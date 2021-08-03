import javax.swing.*;
import java.net.URL;

public class Data {
    //获取图片 URL：获取图片地址 ImageIcon：获取图片
    //身体
    public static URL bodyUrl=Data.class.getResource("/Statics/body.png");
    public static ImageIcon bodyImage=new ImageIcon(bodyUrl);
    //头：向下
    public static URL downUrl=Data.class.getResource("/Statics/down.png");
    public static ImageIcon downImage=new ImageIcon(downUrl);
    //头：向上
    public static URL upUrl=Data.class.getResource("/Statics/up.png");
    public static ImageIcon upImage=new ImageIcon(upUrl);
    //头：向左
    public static URL leftUrl=Data.class.getResource("/Statics/left.png");
    public static ImageIcon leftImage=new ImageIcon(leftUrl);
    //头：向右
    public static URL rightUrl=Data.class.getResource("/Statics/right.png");
    public static ImageIcon rightImage=new ImageIcon(rightUrl);
    //食物
    public static URL foodUrl=Data.class.getResource("/Statics/food.png");
    public static ImageIcon foodImage=new ImageIcon(foodUrl);

    //背景图
    public static URL background =Data.class.getResource("/Statics/background.png");
    public static ImageIcon back=new ImageIcon(background);

}

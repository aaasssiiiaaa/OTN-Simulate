/**
 * Created by asia on 2016/7/3.
 * 需求：生成随机数据，并按长度切割
 */
public class DataGenerate {
    int data[] = new int[6];
    public DataGenerate() {
    }
    public int[] daGenrate() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 30; i++) {
            str.append((int) (1 + Math.random() * 10));
        }
        //切割字符串，并转为int型
        int x = 0, y = 5;
        for (int j = 0; j < 6; j++) {
            String str1 = str.substring(x, y);
            int substr = Integer.parseInt(str1);
            data[j] = substr;
            x = x + 5;
            y = y + 5;
            System.out.println(substr);
        }return data;
    }
}


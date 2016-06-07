package onlyfun.caterpillar;

/**
 * Created by seasen on 2016/1/8.
 */
public class MusicBoxFactory {
    public static IMusicBox createMusicBox() {
        return new IMusicBox() {
            public void play() {
                System.out.println("playing music.....");
            }
        };
    }
}

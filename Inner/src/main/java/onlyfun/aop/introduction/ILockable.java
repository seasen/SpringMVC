package onlyfun.aop.introduction;

/**
 * Created by seasen on 2016/1/16.
 */
public interface ILockable {
    public void lock();
    public void unlock();
    public boolean isLocked();
}

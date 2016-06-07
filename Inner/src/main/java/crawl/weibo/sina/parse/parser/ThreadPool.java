package crawl.weibo.sina.parse.parser;

import java.util.LinkedList;


public class ThreadPool extends ThreadGroup{
	/**
	 * 线程是否活着
	 * */
	private boolean isAlive;
	/**
	 * 双向队列
	 * */
	private LinkedList<Runnable> taskQueue;
	/**
	 * 保存线程的ID
	 * */
	private int threadID;
	/**
	 * 表示线程池的ID
	 * */
	private static int threadPoolID;
	/**
	 * 在构造方法中创建线程池
	 * numThreads用来指定池中的线程数
	 * */
	public ThreadPool(int numThreads) {
		// TODO Auto-generated constructor stub
		super("线程池-"+(threadPoolID++));
		System.out.println(Thread.currentThread().getName());
		setDaemon(true);//守护线程
		isAlive = true;
		taskQueue = new LinkedList<Runnable>();//初始化任务队列
	}
	public void startThread(int numThreads){
		for (int i = 0; i < numThreads; i++) {
			new PooledThread().start();//启动池中线程
		}
	}
	public synchronized void runTask(Runnable task){
		//如果线程池的状态是isAlive == false
		if(!isAlive)
			throw new IllegalStateException();
		//如果任务不为null
		if(task!=null){
			//那么在任务把该加入到任务阶段
			taskQueue.add(task);
			//唤醒空闲的线程执行该任务
			notify();
		}
	}
	protected synchronized Runnable getTask() throws InterruptedException{
		//如果任务队列不是空
		while(taskQueue.size() == 0)
		{
			//如果线程池的状态isAlive == false
			if(!isAlive)
				return null;
			wait();//等待任务
		}
		return (Runnable)taskQueue.removeFirst();		
	}
	public synchronized void close(){
		//如果线程池是活的
		if(isAlive)
		{
			isAlive = false;
		}
		//清空任务队列
		taskQueue.clear();
		//最后终止线程池中所有线程的运行
		interrupt();
	}
	public void join(){
		//当ThreadPool不再活动时唤醒所有等待的线程
		synchronized (this) {
			isAlive = false;
			notifyAll();
		}
		//等待所有池中的线程对象执行完毕
		Thread[] threads = new Thread[activeCount()];
		//把此线程组及其子组中的所有活动线程复制到指定数组中
		int count = enumerate(threads);
		//按序让每个线程执行完毕
		for (int i = 0; i < count; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	/**
	 * 定义线程池中的线程，这些线程对象用来运行任务对象
	 * */
	private class PooledThread extends Thread{
		//把该线程分配指定的线程组对象中去
		public PooledThread(){
			super(ThreadPool.this,"池中线程ID号-"+(threadID++));
		}
		//重写run方法，执行任务对象
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			// TODO Auto-generated method stub
			while(!isInterrupted()){
				//那么从线程池中获取一个任务对象来执行
				Runnable task = null;
				try {
					task = getTask();
				} catch (InterruptedException e) {
				}
				//如果getTask()返回null或者被中断，那么使用return 关闭该线程
				if(task == null)
					return ;
				try {
					task.run();
				} catch (Throwable e) {
					// TODO: handle exception
					uncaughtException(this, e);
				}
			}	
		}
	}
}

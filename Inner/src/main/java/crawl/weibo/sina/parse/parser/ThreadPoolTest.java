package crawl.weibo.sina.parse.parser;

import crawl.weibo.sina.parse.queue.WeiboUrlQueue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {
	/**
	 * @param args
	 */
	public static volatile  int start = 0;
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		parserWeiBo parser = new parserWeiBo();
		parser.getWeiBo2();
		int numThreads = 6;
		//创建线程池对象
		ThreadPool tp = new ThreadPool(numThreads);
		while(!WeiboUrlQueue.isEmpty()) {
			tp.runTask(createTask(WeiboUrlQueue.outElement()));
		}
		//启动线程
		tp.startThread(numThreads);
		//关闭线程池以等待所有线程完毕
		tp.join();
	}
	private static Runnable createTask(final String weiId){
		return new Runnable() {
			public void run(){
				parserWeiBo parser = new parserWeiBo();
				parser.getAllWeiBo(weiId);

			}
		};
	}
}

package basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MSI on 2015/11/26.
 */
public class TDUtils {

    private static ExecutorService totalPool;

    static {
        totalPool = Executors.newCachedThreadPool(); // 线程�?
    }

    public static void execute(Runnable command) {
        totalPool.execute(command);
    }

}
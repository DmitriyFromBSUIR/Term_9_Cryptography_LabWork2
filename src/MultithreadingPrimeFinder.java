/**
 * Created by Dmitry on 20.10.2016.
 */

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

    public class MultithreadingPrimeFinder implements Runnable{

        private int [] _numbersBorders;
        private int _amdalsCoef = 2;
        private int _poolSize = 0;
        private ExecutorService _pool;
        private int _timeout = 120;

        public void taskBalancing() {

        }

        public MultithreadingPrimeFinder(){
            _poolSize = _amdalsCoef * 4;
            _pool = Executors.newFixedThreadPool(_poolSize);
        }

        private void shutdownAndAwaitTermination() {
            _pool.shutdown(); // Disable new tasks from being submitted
            try {
                // Wait a while for existing tasks to terminate
                if (!_pool.awaitTermination(_timeout, TimeUnit.SECONDS)) {
                    _pool.shutdownNow(); // Cancel currently executing tasks
                    // Wait a while for tasks to respond to being cancelled
                    if (!_pool.awaitTermination(_timeout, TimeUnit.SECONDS))
                        System.err.println("Pool did not terminate");
                }
            } catch (InterruptedException ie) {
                // (Re-)Cancel if current thread also interrupted
                _pool.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }

        public void run() { // run the service
            try {
                int intervalsCount = _numbersBorders.length;
                for (int i = 0; i < intervalsCount; i++) {
                    _pool.execute(new Handler( _numbersBorders[i], _numbersBorders[i+1] ));
                }
            } catch (Exception ex) {
                //_pool.shutdown();
                shutdownAndAwaitTermination();
            }
        }


    private class Handler implements Runnable {
        int _left = 0;
        int _right = 0;
        Handler(int leftBorder, int rightBorder) { _left = leftBorder; _right = rightBorder;}
        public void run() {
            //

        }
    }



}

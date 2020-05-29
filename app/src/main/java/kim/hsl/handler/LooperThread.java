package kim.hsl.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

/**
 * 将普通线程转为 Looper 线程
 *
 * 1. 定义 Handler 成员 ( 可以定义若干个 )
 * 2. Looper.prepare()
 * 3. 实例化 Handler 成员
 * 4. Looper.loop()
 *
 * @author hsl
 */
public class LooperThread extends Thread {
    /**
     * 1. 定义时不要实例化
     * Handler 实例化需要关联 Looper 对象
     * Looper 对象在 Looper
     */
    private Handler handler;

    @Override
    public void run() {
        super.run();

        //2. 将线程转为 Looper 线程
        //主要是创建 Looper 放入 ThreadLocal 对象中
        Looper.prepare();

        //3. 创建 Handler 必须在 Looper.prepare() 之后, 否则会崩溃
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                //TODO 处理 Handler 相关业务逻辑
            }
        };

        //4. Looper 开始轮询 MessageQueue, 将消息调度给 Handler 处理
        Looper.loop();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}

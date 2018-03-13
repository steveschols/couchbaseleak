package be.cegeka;

import com.couchbase.client.core.utils.UnicastAutoReleaseSubject;
import com.couchbase.client.deps.io.netty.util.ReferenceCounted;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class DakaWild {

    public static void main(String[] args) throws InterruptedException {
        UnicastAutoReleaseSubject<MyBuf> sub = UnicastAutoReleaseSubject.create(2000, TimeUnit.MILLISECONDS, Schedulers.io());

        sub.onBackpressureBuffer()
            .doOnNext(l -> System.out.println("Premap " + l.myInt))
            .map(l -> {
                System.out.println("Map " + l.myInt);
                l.release();
                return l;
            })
            .limit(10)
            //If you remove this subscribe -> Autorelease after 2 sec
            .subscribe(l -> System.out.println("Sub " + l.myInt));

        for (int j = 0; j < 20; j++) {
            System.out.println("Gen " + j);
            MyBuf myBuf = new MyBuf();
            myBuf.myInt = j;
            sub.onNext(myBuf);
            Thread.sleep(1);
        }

        Thread.sleep(5000);
    }

    private static class MyBuf implements ReferenceCounted {
        public int myInt;

        @Override
        public int refCnt() {
            return 0;
        }

        @Override
        public ReferenceCounted retain() {
            return null;
        }

        @Override
        public ReferenceCounted retain(int increment) {
            return null;
        }

        @Override
        public boolean release() {
            System.out.println("Released " + myInt);
            return true;
        }

        @Override
        public boolean release(int decrement) {
            System.out.println("Released decr " + myInt);
            return false;
        }
    }
}

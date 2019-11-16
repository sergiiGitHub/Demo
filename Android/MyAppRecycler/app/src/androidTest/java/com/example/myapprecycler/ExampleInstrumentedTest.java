package com.example.myapprecycler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.util.Pair;

import com.example.myapprecycler.group.TimeStampGroupHolder;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String TAG = ExampleInstrumentedTest.class.getSimpleName();

    TimeStampGroupHolder<MyData> group = new TimeStampGroupHolder<>();
    List<MyData> list = new ArrayList<>();
    DateFormat mDateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
    private static int increment = 0;
    private static long time = System.currentTimeMillis();

    @Test
    public void testAdd() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<MyData> arr = group.toList(appContext, mDateFormat);
        assertEquals(arr.size(), list.size());
        addOne(new MyData("text1", getTime()));
        addOne(new MyData("text2", getTime()));
        Log.d(TAG, "testAdd: " + arr);

        arr = group.toList(appContext, mDateFormat);
        assertEquals(list.size(), arr.size());
        Log.d(TAG, "testAdd: finish");

        addMultiple(3);
        arr = group.toList(appContext, mDateFormat);
        assertEquals(list.size(), arr.size());

        clear();
    }

    private void clear() {
        group.clear();
        list.clear();
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<MyData> arr = group.toList(appContext, mDateFormat);
        assertEquals(list.size(), arr.size());

    }

    @Test
    public void testTemt() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<MyData> arr = group.toList(appContext, mDateFormat);

        assertEquals(list.size(), arr.size());

        MyData data = new MyData("text1", getTime());
        addOne(data);
        arr = group.toList(appContext, mDateFormat);

        assertEquals(list.size(), arr.size());
    }

    @Test
    public void testCrash() {
        check();
        MyData data = new MyData("text1", getTime());

        addNew(data);
        check();

        remove(data);
        check();

        remove(data);
        check();

        addNew(data);
        check();
    }

    @Test
    public void testCrashRandom() {
        check();
        ArrayList<MyData> datas = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            boolean isAdd = random.nextBoolean() || datas.isEmpty();
            if (isAdd) {
                MyData e = new MyData("text" + i, getTime());
                datas.add(e);
                addNew(e);
                check();
            } else {
                int index = random.nextInt(datas.size());
                MyData data = datas.remove(index);
                remove(data);
                check();
            }
        }
        clear();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.myapprecycler", appContext.getPackageName());
    }

    @Test
    public void testAddConcurrent() {
        check();
        Handler h = new Handler(Looper.getMainLooper());
        //add multiple
        Runnable runnable = () -> {
            for (int i = 0; i < 100; ++i) {
                Log.d(TAG, "run: add multi: " + i + " " + Thread.currentThread().getName());
                h.post(() -> {
                    addMultiple(4);
                    check();
                });
                Log.d(TAG, "run: add multi: sleep ");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Log.d(TAG, "run: ", e);
                }
            }
        };

        ExecutorService exe = Executors.newSingleThreadExecutor();
        exe.execute(runnable);

        //one
        for (int i = 0; i < 100; ++i) {
            h.post(() -> {
                Log.d(TAG, "run: add one: " + Thread.currentThread().getName());
                //addOne(new MyData("text1", getTime()));
                addNew(new MyData("text1", getTime()));
                check();
            });

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Log.d(TAG, "run: ", e);
            }
        }

        try {
            exe.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clear();
    }

    private long getTime() {
        return time + (increment++);
    }

    private void addMultiple(int count) {
        List<MyData> arr = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            arr.add(new MyData("text" + i, getTime()));
        }

        list.clear();
        //group.clear();
        addAll(arr);
    }

    private void addAll(List<MyData> newArrays) {
        Context appContext = InstrumentationRegistry.getTargetContext();
        group.setData(newArrays);
        list.addAll(group.toList(appContext, mDateFormat));
    }

    private void addOne(MyData item) {
        int index = group.add(item);
        list.add(index, item);
    }

    private void check() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<MyData> arr = group.toList(appContext, mDateFormat);
        assertEquals(list.size(), arr.size());
    }

    private void addNew(MyData data) {
        Context context = InstrumentationRegistry.getTargetContext();
        Pair<Integer, List<MyData>> res = group.addNew(context, mDateFormat, data);
        list.addAll(res.first, res.second);
    }

    void remove(MyData item) {
        if (group.remove(item)) {
            int index = list.indexOf(item);
            list.remove(index - 1);
        }
        list.remove(item);
    }
}

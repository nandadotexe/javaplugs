/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Vladislav Zablotsky
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE. 
 */
package org.github.javaplugs.app;

import org.testng.annotations.Test;

/**
 * @author Vladislav Zablotsky
 */
public class CrossLockTest {

    @Test
    public void singleLockTest() {
        String lockId = "lock-id-example";
        CrossLock lockDouble = new CrossLock(lockId);

        assert CrossLock.get(lockId).lock() == true;
        assert lockDouble.lock() == false;

        CrossLock.get(lockId).release();

        assert lockDouble.lock() == true;
        assert CrossLock.get(lockId).lock() == false;

        lockDouble.clear();

        assert CrossLock.get(lockId).lock() == true;
        assert lockDouble.lock() == false;
        assert CrossLock.get(lockId).lock() == true;

        CrossLock.get(lockId).clear();
        lockDouble.clear();
    }

    @Test
    public void multiLockTest() {
        String lock1 = "lock-1-id";
        String lock2 = "lock-2-id";

        CrossLock lock1Obj = new CrossLock(lock1);
        CrossLock lock2Obj = new CrossLock(lock2);

        assert CrossLock.get(lock1).lock() == true;
        assert CrossLock.get(lock2).lock() == true;

        assert lock1Obj.lock() == false;
        assert lock2Obj.lock() == false;

        CrossLock.get(lock1).release();

        assert lock1Obj.lock() == true;
        assert lock2Obj.lock() == false;

        assert CrossLock.get(lock1).lock() == false;

        CrossLock.get(lock2).release();

        assert lock1Obj.lock() == true;
        assert lock2Obj.lock() == true;

        assert CrossLock.get(lock1).lock() == false;
        assert CrossLock.get(lock2).lock() == false;

        lock1Obj.clear();
        lock2Obj.clear();

        assert CrossLock.get(lock1).lock() == true;
        assert CrossLock.get(lock2).lock() == true;

        assert lock1Obj.lock() == false;
        assert lock2Obj.lock() == false;

        CrossLock.get(lock1).clear();
        CrossLock.get(lock2).clear();
        lock1Obj.clear();
        lock2Obj.clear();
    }
}

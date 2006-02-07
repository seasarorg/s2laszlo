/*
 * Copyright 2005-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.laszlo;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * @author u1hoshino
 * 
 */
public class S2LaszloAdapterTest extends S2TestCase {

    public S2LaszloAdapterTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(S2LaszloAdapterTest.class);
    }

    public void setUp() {
        include("s2laszlo-test.dicon");
    }

    public void testService() {

        ReturnObject ro1 = S2LaszloAdapter.service("org.seasar.laszlo.Hoge1",
                "sayHoge");
        assertTrue(ro1.isSuccess());
        assertEquals("hoge", ro1.getObject());
        assertNull(ro1.getException());
        assertNull(ro1.getExceptionMessage());

        List l = new ArrayList();
        l.add("hello");
        ReturnObject ro2 = S2LaszloAdapter.service("org.seasar.laszlo.Hoge1",
                "echo", l);
        assertTrue(ro2.isSuccess());
        assertEquals("hello", ro2.getObject());
        assertNull(ro2.getException());
        assertNull(ro2.getExceptionMessage());

        // throw Exception
        ReturnObject ro3 = S2LaszloAdapter.service("org.seasar.laszlo.Hoge1",
                "throwRuntimeException");
        assertFalse(ro3.isSuccess());
        assertNull(ro3.getObject());
        assertEquals(RuntimeException.class.getName(), ro3.getException());
        assertEquals("exception", ro3.getExceptionMessage());

        // not defined method
        ReturnObject ro4 = S2LaszloAdapter.service("org.seasar.laszlo.Hoge1",
                "notDefinedMethod");
        assertFalse(ro4.isSuccess());
        assertNull(ro4.getObject());
        assertNotNull(ro4.getException());
        assertNotNull(ro4.getExceptionMessage());

        // not defined metatag
        ReturnObject ro5 = S2LaszloAdapter.service("org.seasar.laszlo.Hoge2",
                "sayHoge");
        assertFalse(ro5.isSuccess());
        assertNull(ro5.getObject());
        assertEquals(S2LaszloNotAllowedException.class.getName(), ro5
                .getException());
        assertNotNull(ro5.getExceptionMessage());

        // not defined component
        ReturnObject ro6 = S2LaszloAdapter.service("org.seasar.laszlo.Hoge3",
                "sayHoge");
        assertFalse(ro6.isSuccess());
        assertNull(ro6.getObject());
        assertNotNull(ro5.getException());
        assertNotNull(ro6.getExceptionMessage());

    }
}

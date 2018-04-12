/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.diracnote.reflection.MutablePerson
 */
package sun.reflect;

import com.diracnote.reflection.MutablePerson;
import java.lang.reflect.InvocationTargetException;
import sun.reflect.MethodAccessorImpl;

public class GeneratedMethodAccessor1
extends MethodAccessorImpl {
    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public Object invoke(Object object, Object[] arrobject) throws InvocationTargetException {
        if (object == null) {
            throw new NullPointerException();
        }
        try {
            if (arrobject != null && arrobject.length != 0) {
                throw new IllegalArgumentException();
            }
            return ((MutablePerson)object).getName();
        }
        catch (ClassCastException | NullPointerException runtimeException) {
            throw new IllegalArgumentException(Object.super.toString());
        }
        catch (Throwable throwable) {
            throw new InvocationTargetException(throwable);
        }
    }
}

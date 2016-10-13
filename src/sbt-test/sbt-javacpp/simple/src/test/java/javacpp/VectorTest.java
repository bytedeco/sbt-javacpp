package javacpp;

import java.io.File;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.Builder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

@Platform(include="<vector>")
public class VectorTest {

    @BeforeClass public static void setUpClass() throws Exception {
        Class c = VectorTest.class;
        Builder builder = new Builder().classesOrPackages(c.getName());
        File[] outputFiles = builder.build();
        Loader.load(c);
    }

    @Name("std::vector<std::vector<void*> >")
    public static class PointerVectorVector extends Pointer {
        static { Loader.load(); }
        public PointerVectorVector()       { allocate();  }
        public PointerVectorVector(long n) { allocate(n); }
        public PointerVectorVector(Pointer p) { super(p); } // this = (vector<vector<void*> >*)p
        private native void allocate();                  // this = new vector<vector<void*> >()
        private native void allocate(long n);            // this = new vector<vector<void*> >(n)
        @Name("operator=")
        public native @ByRef PointerVectorVector put(@ByRef PointerVectorVector x);

        @Name("operator[]")
        public native @StdVector PointerPointer get(long n);
        public native @StdVector PointerPointer at(long n);

        public native long size();
        public native @Cast("bool") boolean empty();
        public native void resize(long n);
        public native @Index long size(long i);                   // return (*this)[i].size()
        public native @Index @Cast("bool") boolean empty(long i); // return (*this)[i].empty()
        public native @Index void resize(long i, long n);         // (*this)[i].resize(n)

        public native @Index Pointer get(long i, long j);  // return (*this)[i][j]
        public native void put(long i, long j, Pointer p); // (*this)[i][j] = p
    }

    @Test public void testPointerVectorVector() {
        PointerVectorVector v = new PointerVectorVector(13);
        v.resize(0, 42); // v[0].resize(42)
        Pointer p = new Pointer() { { address = 0xDEADBEEFL; } };
        v.put(0, 0, p);  // v[0][0] = p
        assertTrue(!v.empty());
    }
}
package javacpp;

import org.bytedeco.mkl.global.mkl_rt;
import org.junit.Test;
import static org.junit.Assert.*;

public class MklTest {
    @Test public void testGetMaxCpuFrequency() {
        assertTrue(mkl_rt.MKL_Get_Max_Cpu_Frequency() > 0);
    }
}

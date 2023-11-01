import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;

public class MemTest {
  	static String mb (long s) {
    	return String.format("%d (%.2f M)", s, (double)s / (1024 * 1024));
  }

  public static void main(String[] args) {
	// java vm이 사용할수 있는 총 메모리(bytes), -Xmx
	long maxMem = Runtime.getRuntime().maxMemory()/1024/1024;
	// java vm에 할당된 총 메모리
	long totalMem = Runtime.getRuntime().totalMemory()/1024/1024;
	// java vm이 추가로 할당 가능한 메모리
	long freeMem = Runtime.getRuntime().freeMemory()/1024/1024;
	// 현재 사용중인 메모리
	long usedMem = totalMem - freeMem;
	// 퍼센트
	double pct = usedMem * 100 / maxMem;
	String t = "heap.current \t heap.percent \t heap.max";
	String s = String.format("%s\r\n%10dmb \t %11.1f%% \t %6dmb", t, usedMem, pct, maxMem);
	System.out.println(s);


	System.out.println("Runtime max: " + mb(Runtime.getRuntime().maxMemory()));
    	MemoryMXBean m = ManagementFactory.getMemoryMXBean();

    	System.out.println("Non-heap: " + mb(m.getNonHeapMemoryUsage().getMax()));
    	System.out.println("Heap: " + mb(m.getHeapMemoryUsage().getMax()));

    	for (MemoryPoolMXBean mp : ManagementFactory.getMemoryPoolMXBeans()) {
      		System.out.println("Pool: " + mp.getName() + " (type " + mp.getType() + ")" + " = " + mb(mp.getUsage().getMax()));
    }
  }
}

package alma.STOPWATCH_MODULE.StopWatchImpl;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.scxml.SCXMLExecutor;

public class StartTimer extends Thread
{
    SCXMLExecutor exec;
    /** The events for the stop watch. */
    public static final String EVENT_START = "watch.start",
        VENT_STOP = "watch.stop", EVENT_SPLIT = "watch.split",
        EVENT_UNSPLIT = "watch.unsplit", EVENT_RESET = "watch.reset";

    public static StringBuffer stringBuffer;

    /** The fragments of the elapsed time. */
    private static int hr, min, sec, fract;
    /** The fragments of the display time. */
    private static int dhr, dmin, dsec, dfract;
    /** The stopwatch "split" (display freeze). */
    private static boolean split;
    /** The Timer to keep time. */
    private static Timer timer;
    /** The display decorations. */
    private static final String DELIM = ":", DOT = ".", EMPTY = "", ZERO = "0";

    public void run(){ 
        split = false;
        if (timer == null) {
            timer = new Timer(true);
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    increment();
                }
            }, 100, 100);
        }
    }
 
    public synchronized void stopping() {
        timer.cancel();
        timer = null;

    }

    public synchronized void paussing() {
        split = true;

    }

    private void increment() {
        if (fract < 9) {
            fract++;
        } else {
            fract = 0;
            if (sec < 59) {
                sec++;
            } else {
                sec = 0;
                if (min < 59) {
                    min++;
                } else {
                    min = 0;
                    if (hr < 99) {
                        hr++;
                    } else {
                        hr = 0; //wrap
                    }
                }
            }
        }
        if (!split) {
            dhr = hr;
            dmin = min;
            dsec = sec;
            dfract = fract;
        }
    }

    public void running() {
        split = false;
        if (timer == null) {
            timer = new Timer(true);
            timer.scheduleAtFixedRate(new TimerTask() {  public void run() { increment();} }, 100, 100);
        }
    }

    public static String getDisplay() {
        String padhr = dhr > 9 ? EMPTY : ZERO;
        String padmin = dmin > 9 ? EMPTY : ZERO;
        String padsec = dsec > 9 ? EMPTY : ZERO;
        stringBuffer = new StringBuffer();
        return stringBuffer.append(padhr).append(dhr).append(DELIM).
            append(padmin).append(dmin).append(DELIM).append(padsec).
            append(dsec).append(DOT).append(dfract).toString();
    }

    public static void reset() {
        hr = min = sec = fract = dhr = dmin = dsec = dfract = 0;
        split = false;
    }

}



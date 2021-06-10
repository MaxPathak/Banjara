package src.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TimedEvent implements Serializable {
    public ArrayList<Timer> timers;

    public TimedEvent() {
        timers = new ArrayList<Timer>();
    }

    public void add(TimerTask task, long delay, long period) {
        Timer temp = new Timer();
        temp.schedule(task, delay, period);
        timers.add(temp);
    }

    public void merge(TimedEvent t) {
        ArrayList<Timer> targets = t.timers;
        for (Timer target : targets) {
            if (!timers.contains(target))
                timers.add(target);
        }
    }

    public void separate(TimedEvent t) {
        ArrayList<Timer> targets = t.timers;
        timers.removeAll(targets);
    }

    public void stopAll() {
        for (Timer timer : timers) {
            timer.cancel();
            timer.purge();
        }
    }

    /*
     * public static void main(String[] args) { TimedEvent e = new TimedEvent();
     * e.add(new TimerTask() {
     * 
     * @Override public void run() { System.out.println("Hello"); } }, 0, 170); }
     */

}

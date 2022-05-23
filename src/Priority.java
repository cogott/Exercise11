public class Priority implements Runnable{
    int count;Thread thread; static boolean stop= false; static String currentName;

    Priority(String name){
        thread = new Thread(this,name);
        count= 0;
        currentName = name;
    }
    public void run() {
        System.out.println(thread.getName() + "starts to operate");
        do {
            count++;
            if (currentName.compareTo(thread.getName()) != 0) {
                currentName = thread.getName();
                System.out.println(currentName + "is executed");
            }
        } while (stop == false && count < 10_000_000);
        stop = true;
        System.out.println("\n" + thread.getName() + "finishes running");
    }
}

class PriorityDemo{
    public static void main(String[] args) {
        Priority p1 = new Priority("High priority thread");
        Priority p2 = new Priority("Thread with low priority");
        Priority p3 = new Priority("Thread #1 with normal priority");
        Priority p4 = new Priority("Thread #2 with normal priority");
        Priority p5 = new Priority("Thread #3 with normal priority");

        p1.thread.setPriority(Thread.MAX_PRIORITY);
        p2.thread.setPriority(Thread.MIN_PRIORITY);

        p1.thread.start();p2.thread.start();p3.thread.start();p4.thread.start();p5.thread.start();
        try{p1.thread.join();p2.thread.join();p2.thread.join();p3.thread.join();p5.thread.join();p5.thread.join();
        }catch (InterruptedException e){System.out.println("The main thread starts running");}
        System.out.println("High priority thread counted to:" + p1.count);
        System.out.println("Low priority thread counted to: " + p2.count);
        System.out.println("#1 Normal Priority thread added to: " + p3.count);
        System.out.println("#2 Normal Priority thread added to: " + p4.count);
        System.out.println("#3 Normal Priority thread added to: " + p5.count);

    }
}
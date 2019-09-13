import java.util.Scanner;

public class Main {
    public static void main(String [] args)
    {
        System.out.println("cos tma");
        TruckQueue queue = new TruckQueue();
        Scanner scanner = new Scanner(System.in);
        String whatToDo = scanner.next();
        while(!whatToDo.equals("q"))
        {
            if(whatToDo.equals("add"))
            {
                int weight = scanner.nextInt();
                System.out.println("ID: " + queue.arrive(weight));
            }
            else if(whatToDo.equals("status"))
            {
                queue.status();
            }
            else if(whatToDo.equals("step"))
            {
                queue.step();
            }
            else if(whatToDo.equals("time"))
            {
                int id = scanner.nextInt();
                System.out.println("Waiting time:" + queue.waitingTime(id));
            }
            whatToDo = scanner.next();
        }
    }
}

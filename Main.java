import java.util.Scanner;

public class Main {
    public static void main(String [] args)
    {
        TruckQueue queue = new TruckQueue();
        Scanner scanner = new Scanner(System.in);
        String whatToDo = scanner.next();
        while(!whatToDo.equals("q"))
        {
            switch (whatToDo)
            {
                case "add":
                    int weight = scanner.nextInt();
                    System.out.println("ID: " + queue.arrive(weight));
                    break;

                case "status":
                    queue.status();
                    break;

                case "step":
                    queue.step();
                    break;

                case "time":
                    int id = scanner.nextInt();
                    System.out.println("Waiting time:" + queue.waitingTime(id));
                    break;
            }
            whatToDo = scanner.next();
        }
    }
}

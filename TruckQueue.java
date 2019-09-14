public class TruckQueue {
    private Truck[][] queue = new Truck[2][5];
    private int currentID = 0;
    private double full_time_waiting = 0.0;
    private int how_many_trucks_leave = 0;
    private final int LAST_POSITION = 5;

    public int arrive(int weight)
    {
        this.currentID++;
        Truck new_truck = new Truck(weight, currentID);
        addTruckToQueue(new_truck);
        return this.currentID;
    }

    private void addTruckToQueue(Truck new_truck)
    {
        if(isBetterToAddToFirstQueue())
            tryToAddToOneQueue(0, new_truck);
        else
            tryToAddToOneQueue(1, new_truck);
    }

    private boolean isBetterToAddToFirstQueue()
    {
        return getWaitingTimeInQueue(0, 4) <= getWaitingTimeInQueue(1, 4);
    }

    private int getWaitingTimeInQueue(int which_queue, int truckPos)
    {
        int i = 0;
        int waitingTime = 0;
        while(i <= truckPos && queue[which_queue][i] != null)
        {
            waitingTime += queue[which_queue][i].getTime();
            i++;
        }

        return waitingTime;
    }

    private void tryToAddToOneQueue(int which_queue, Truck new_truck)
    {
        int position = getFreeSpaceInQueue(which_queue);
        if(position < LAST_POSITION)
        {
            queue[which_queue][position] = new_truck;
        }
        else
        {
            int otherQueue = (which_queue + 1)%2;
            position = getFreeSpaceInQueue(otherQueue);
            if(position < LAST_POSITION)
                queue[otherQueue][position] = new_truck;
            else
                System.out.println("Cannot add truck to queue. There is no space.");
        }
    }

    private int getFreeSpaceInQueue(int which_queue)
    {
        for(int i = 0; i < LAST_POSITION; i++)
            if(queue[which_queue][i] == null)
                return i;
        return LAST_POSITION;
    }

    public void status()
    {
        int i = 0;
        System.out.println("Average waiting time is equal: " + (full_time_waiting)/(double)(how_many_trucks_leave));
        System.out.println("First queue:");
        while(i < LAST_POSITION)
        {
            if(queue[0][i] != null)
                printOutData(0, i);
            i++;
        }
        System.out.println("Second queue:");
        i = 0;
        while (i < LAST_POSITION)
        {
            if(queue[1][i] != null)
                printOutData(1, i);
            i++;
        }
    }

    private void printOutData(int i, int j)
    {
        System.out.println("Truck with ID: " + queue[i][j].getID() + " will probably wait " +
                getWaitingTimeInQueue(i, j) + " more units of time");
    }

    public void step()
    {
        addOneUnitOfTime();
        stepInOneQueue(0);
        stepInOneQueue(1);
    }

    private void addOneUnitOfTime()
    {
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < LAST_POSITION; j++)
                if(queue[i][j] != null)
                    queue[i][j].addOneUnitOfTime();

    }

    private void stepInOneQueue(int which_queue)
    {
        if(queue[which_queue][0] != null)
        {
            queue[which_queue][0].stepInQueue();
            if(queue[which_queue][0].getTime() == 0)
            {
                how_many_trucks_leave++;
                full_time_waiting += queue[which_queue][0].getFull_waiting_time();
                queueForward(which_queue);
            }
        }
    }

    private void queueForward(int which_queue)
    {
        int i = 1;
        queue[which_queue][0] = null;
        while(i < LAST_POSITION && queue[which_queue][i] != null)
        {
            changeIfProfitableToSwapTrucks(i, which_queue);
            queue[which_queue][i - 1] = queue[which_queue][i];
            queue[which_queue][i] = null;
            i++;
        }
    }

    private void changeIfProfitableToSwapTrucks(int position, int mainQueue)
    {
        if(queue[0][position] != null && queue[1][position] != null)
            if(queue[mainQueue][position].getTime() > queue[(mainQueue + 1)%2][position].getTime())
            {
                Truck buf = queue[mainQueue][position];
                queue[mainQueue][position] = queue[(mainQueue + 1)%2][position];
                queue[(mainQueue + 1)%2][position] = buf;
            }
    }

    public int waitingTime(int truckId)
    {
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < LAST_POSITION; j++)
                if(queue[i][j] != null && queue[i][j].getID() == truckId)
                {
//                    System.out.println(queue[i][j].getID());
                    return getWaitingTimeInQueue(i, j);
                }
        System.out.println("There is no truck in queue with this ID");
        return -1;
    }

}

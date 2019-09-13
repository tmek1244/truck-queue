public class TruckQueue {
    private Truck[][] queue = new Truck[2][5];
    private int currentID = 0;

    private void addTruckToQueue(Truck new_truck)
    {
        if(getWaitingTimeInQueue(0, 4) <= getWaitingTimeInQueue(1, 4))
            tryToAddToOneQueue(0, new_truck);
        else
            tryToAddToOneQueue(1, new_truck);
    }

    private void tryToAddToOneQueue(int which, Truck new_truck)
    {
        int position = getFreeSpaceInQueue(which);
        if(position < 5)
        {
            queue[which][position] = new_truck;
        }
        else
        {
            int otherQueue = (which + 1)%2;
            position = getFreeSpaceInQueue(otherQueue);
            if(position < 5)
                queue[otherQueue][position] = new_truck;
            else
                System.out.println("Cannot add truck to queue. There is no space.");
        }
    }

    private int getFreeSpaceInQueue(int which)
    {
        for(int i = 0; i < 5; i++)
            if(queue[which][i] == null)
                return i;
        return 5;
    }


    private int getWaitingTimeInQueue(int which, int truckPos)
    {
        int i = 0;
        int waitingTime = 0;
        while(i <= truckPos && queue[which][i] != null)
        {
            waitingTime += queue[which][i].getTruckTime();
            i++;
        }

        return waitingTime;
    }

    private void queueForward(int which)
    {
        int i = 1;
        while(i < 5 && queue[which][i] != null)
        {
            queue[which][i - 1] = queue[which][i];
            queue[which][i] = null;
            i++;
        }
    }

    private void stepInOneQueue(int which)
    {
        if(queue[which][0] != null)
        {
            queue[which][0].stepInQueue();
            if(queue[which][0].getTruckTime() == 0)
            {
                queueForward(which);
            }
        }
    }

    private void printOutData(int i, int j)
    {
        System.out.println("Truck with ID: " + queue[i][j].getTruckID() + " will probably wait " +
                getWaitingTimeInQueue(i, j) + " more units of time");
    }

    public int arrive(int weight)
    {
        this.currentID++;
        Truck new_truck = new Truck(weight, currentID);
        addTruckToQueue(new_truck);
        return this.currentID;
    }

    public void status()
    {
        int i = 0;
        System.out.println("First queue:");
        while(i < 5)
        {
            if(queue[0][i] != null)
                printOutData(0, i);
            i++;
        }
        System.out.println("Second queue:");
        i = 0;
        while (i < 5)
        {
            if(queue[1][i] != null)
                printOutData(1, i);
            i++;
        }
    }

    public void step()
    {
        stepInOneQueue(0);
        stepInOneQueue(1);
    }

    public int waitingTime(int truckId)
    {
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 5; j++)
                if(queue[i][j].getTruckID() == truckId)
                    return getWaitingTimeInQueue(i, j);
        System.out.println("There is no truck in queue with this ID");
        return -1;
    }

}

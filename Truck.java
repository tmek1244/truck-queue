class Truck {
    private int time;
    private int ID;
    private int full_waiting_time;

    Truck(int time, int ID)
    {
        this.time = time;
        this.ID = ID;
        this.full_waiting_time = 0;
    }

    int getTime()
    {
        return this.time;
    }

    int getFullWaitingTime()
    {
        return this.full_waiting_time;
    }

    int getID()
    {
        return this.ID;
    }

    void stepInQueue()
    {
        this.time--;
    }

    void addOneUnitOfTime()
    {
        this.full_waiting_time++;
    }
}

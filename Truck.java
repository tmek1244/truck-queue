class Truck {
    private int truckTime;
    private int truckID;

    Truck(int truckTime, int truckID)
    {
        this.truckTime = truckTime;
        this.truckID = truckID;
    }

    int getTruckTime()
    {
        return this.truckTime;
    }

    void stepInQueue()
    {
        this.truckTime--;
    }

    int getTruckID()
    {
        return this.truckID;
    }
}

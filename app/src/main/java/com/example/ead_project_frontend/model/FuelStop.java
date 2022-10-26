package com.example.ead_project_frontend.model;

public class FuelStop {
    public String id;
    public String name;
    public String location;

    public String companyName;

    public double fuelPetrolCapacity;

    public double fuelDiselCapacity;

    public int carQueue;
    public int bikeQueue;
    public int busQueue;

    public int threeWheelerQueue;

    public FuelStop(String id, String name, String location, String companyName, double fuelPetrolCapacity, double fuelDiselCapacity, int carQueue, int bikeQueue, int busQueue, int threeWheelerQueue) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.companyName = companyName;
        this.fuelPetrolCapacity = fuelPetrolCapacity;
        this.fuelDiselCapacity = fuelDiselCapacity;
        this.carQueue = carQueue;
        this.bikeQueue = bikeQueue;
        this.busQueue = busQueue;
        this.threeWheelerQueue = threeWheelerQueue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getFuelPetrolCapacity() {
        return fuelPetrolCapacity;
    }

    public void setFuelPetrolCapacity(double fuelPetrolCapacity) {
        this.fuelPetrolCapacity = fuelPetrolCapacity;
    }

    public double getFuelDiselCapacity() {
        return fuelDiselCapacity;
    }

    public void setFuelDiselCapacity(double fuelDiselCapacity) {
        this.fuelDiselCapacity = fuelDiselCapacity;
    }

    public int getCarQueue() {
        return carQueue;
    }

    public void setCarQueue(int carQueue) {
        this.carQueue = carQueue;
    }

    public int getBikeQueue() {
        return bikeQueue;
    }

    public void setBikeQueue(int bikeQueue) {
        this.bikeQueue = bikeQueue;
    }

    public int getBusQueue() {
        return busQueue;
    }

    public void setBusQueue(int busQueue) {
        this.busQueue = busQueue;
    }

    public int getThreeWheelerQueue() {
        return threeWheelerQueue;
    }

    public void setThreeWheelerQueue(int threeWheelerQueue) {
        this.threeWheelerQueue = threeWheelerQueue;
    }
}



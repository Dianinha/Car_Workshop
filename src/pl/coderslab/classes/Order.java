package pl.coderslab.classes;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Order {

	private int id;
	private LocalDateTime acceptance;
	private LocalDateTime repairStartTime;
	private LocalDateTime repairEndTime;
	private Employee assignedWorker;
	private String problemDescription;
	private String repairDescription;
	private Status status;
	private Vehicle vehicle;
	private double repairCost;
	private double partsCost;
	private double costPerHour;
	private double repairTime;

	public Order() {
		super();
	}

	public Order(LocalDateTime acceptance, LocalDateTime repairStartTime, Employee assignedWorker,
			String problemDescription, String repairDescription, Status status, Vehicle vehicle, double repairCost,
			double partsCost, double repairTime, LocalDateTime repairEndTime) {
		super();
		this.id = 0;
		this.acceptance = acceptance;
		this.repairStartTime = repairStartTime;
		this.assignedWorker = assignedWorker;
		this.problemDescription = problemDescription;
		this.repairDescription = repairDescription;
		this.status = status;
		this.vehicle = vehicle;
//		this.repairCost = repairCost;
		this.partsCost = partsCost;
		this.costPerHour = this.assignedWorker.getCostPerHour();
		this.repairTime = repairTime;
		this.repairEndTime=repairEndTime;
		this.repairCost = this.partsCost+ this.repairTime*this.costPerHour;
	}

	public int getId() {
		return id;
	}

	public Order setId(int id) {
		this.id = id;
		return this;
	}

	public LocalDateTime getAcceptance() {
		return acceptance;
	}

	public Order setAcceptance(LocalDateTime acceptance) {
		this.acceptance = acceptance;
		return this;
	}

	public LocalDateTime getRepairStartTime() {
		return repairStartTime;
	}

	public Order setRepairStartTime(LocalDateTime repairStartTime) {
		this.repairStartTime = repairStartTime;
		return this;
	}

	public Employee getAssignedWorker() {
		return assignedWorker;
	}

	public Order setAssignedWorker(Employee assignedWorker) {
		this.assignedWorker = assignedWorker;
		return this;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public Order setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
		return this;
	}

	public String getRepairDescription() {
		return repairDescription;
	}

	public Order setRepairDescription(String repairDescription) {
		this.repairDescription = repairDescription;
		return this;
	}

	public int getStatusIinInt() {
		Status[] vals = Status.values();
		int value = Arrays.binarySearch(vals, status) + 1;
		return value;
	}
	
	public Status getStatus() {
		return status;
	}

	public Order setStatus(Status status) {
		this.status = status;
		return this;
	}

	public Order setStatus(int status) {
		if (status <= 5) {
			Status[] vals = Status.values();
			this.status = vals[status - 1];

		}
		return this;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Order setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	public double getRepairCost() {
		return repairCost;
	}

	public Order setRepairCost(double repairCost) {
		this.repairCost = repairCost;
		return this;
	}
	public Order setRepairCost() {
		this.repairCost = this.partsCost+ this.repairTime*this.costPerHour;
		return this;
	}

	public double getPartsCost() {
		return partsCost;
	}

	public Order setPartsCost(double partsCost) {
		this.partsCost = partsCost;
		return this;
	}

	public double getCostPerHour() {
		return costPerHour;
	}

	public Order setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
		return this;
	}
	
	public Order setCostPerHour() {
		this.costPerHour = getAssignedWorker().getCostPerHour();
		return this;
	}

	public double getRepairTime() {
		return repairTime;
	}

	public Order setRepairTime(double repairTime) {
		this.repairTime = repairTime;
		return this;
	}
	
	
	
	public LocalDateTime getRepairEndTime() {
		return repairEndTime;
	}

	public void setRepairEndTime(LocalDateTime repairEndTime) {
		this.repairEndTime = repairEndTime;
	}

	public String showStatus(){
		String result="";
		
		switch (this.status) {
		case ACCEPTED: 
			result= "NIEZAAKCEPTOWANE";
			break;
		case APPROVED: 
			result= "ZAAKCEPTOWANE";
			break;
		case IN_REPAIR:
			result= "W REALIZACJI";
			break;
		case READY_TO_RETURN:
			result= "GOTOWE DO ODBIORU";
			break;
		case CANCELLED:
			result= "ANULOWANE";
			break;
		default:
			break;
			
		}
		return result;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", acceptance=" + acceptance + ", repairStartTime=" + repairStartTime
				+ ", assignedWorker=" + assignedWorker + ", problemDescription=" + problemDescription
				+ ", repairDescription=" + repairDescription + ", status=" + status + ", vehicle=" + vehicle
				+ ", repairCost=" + repairCost + ", partsCost=" + partsCost + ", costPerHour=" + costPerHour
				+ ", repairTime=" + repairTime + "]";
	}

}

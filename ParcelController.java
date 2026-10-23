ParcelController (Main Class)
package com.courier.controller;

import com.courier.service.ParcelService;

import java.util.Scanner;

public class ParcelController {
	public static void main(String[] args) {
		
		ParcelService service = new ParcelService();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("1. Add Parcel");
			System.out.println("2. Upadte status");
			System.out.println("3. Track Parcel");
			System.out.println("4. Exit");
			
			int choice = sc.nextInt();
			
			if(choice == 1) {
				System.out.println("Enter Tracking ID: ");
				String id = sc.next();
				System.out.println("Sender: ");
				String sender = sc.next();
				System.out.println("Receiver: ");
				String receiver = sc.next();
				
				service.addParcel(id, sender, receiver);
				
			}
			else if(choice == 2) {
				System.out.println("Enter Tracking Id: ");
				String updated = sc.next();
				System.out.println("Enter New Status: ");
				String status = sc.next();
				
				service.updateStatus(updated, status);
	
			}
			else if(choice == 3) {
				System.out.println("Enter Tracking ID: ");
				String trackId = sc.next();
				
				service.trackParcel(trackId);
				
			}
			else if(choice == 4) {
				System.exit(0);
				
			}
			else {
				System.out.println("Invalid Choice!");
			}
		}
	}
}





Parcel
package com.courier.model;

public class Parcel {
	private String trackingId;
	private String sender;
	private String receiver;
	private String status;
	
	public Parcel(String trackingId, String sender, String receiver, String status) {
		this.trackingId = trackingId;
		this.sender = sender;
		this.receiver = receiver;
		this.status = status;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}





ParcelRepository
package com.courier.repository;

import com.courier.model.Parcel;

import java.util.ArrayList;

public class ParcelRepository {
	private ArrayList<Parcel> parcelList = new ArrayList<>();
	
	public void save(Parcel parcel) {
		parcelList.add(parcel);
	}
	public Parcel findById(String trackingId) {
		for(Parcel p : parcelList) {
			if(p.getTrackingId().equals(trackingId)) {
				return p;
			}
		}
		return null;
	}
	public ArrayList<Parcel> getAllParcels(){
		return parcelList;
	}
}







ParcelService
package com.courier.service;

import java.util.ArrayList;

import com.courier.model.Parcel;

import com.courier.repository.ParcelRepository;

public class ParcelService {
	private ParcelRepository repository = new ParcelRepository();
	public void addParcel(String id,String sender,String receiver) {
		Parcel parcel = new Parcel(id, sender, receiver, "Registered");
		repository.save(parcel);
		System.out.println("Parcel Added Successfully!");
	}
	public void updateStatus(String id, String status) {
		ArrayList<Parcel> list = repository.getAllParcels();
		
		boolean found = false;
		
		for(Parcel p : repository.getAllParcels()) {
			if(p.getTrackingId().equals(id)) {
				p.setStatus(status);
				System.out.println("Status Updated!");
				found = true;
				break;
			}
		}
		if(!found) {
			System.out.println("Parcel Not Founded");
		}
	}
	public void trackParcel(String id) {
		
		ArrayList<Parcel> list = repository.getAllParcels();
		boolean found = false;
		
		for(Parcel p : list) {
			if(p.getTrackingId().equals(id)) {
				System.out.println("Tracking ID: " + p.getTrackingId());
				System.out.println("Sender: " + p.getSender());
				System.out.println("Receiver: " + p.getReceiver());
				System.out.println("Status: " + p.getStatus());
				found = true;
				break;
			}
		}
		if(!found) {
			System.out.println("Parcel Not Founded");
		}
	}
}

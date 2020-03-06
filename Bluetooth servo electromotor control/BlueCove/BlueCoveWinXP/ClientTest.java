/*
    Copyright 2004 Intel Corporation

    This file is part of Blue Cove.

    Blue Cove is free software; you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation; either version 2.1 of the License, or
    (at your option) any later version.

    Blue Cove is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Blue Cove; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

import javax.bluetooth.*;

import javax.microedition.io.*;



import java.io.*;

import java.util.*;



public class ClientTest implements DiscoveryListener

{
	class Cancel extends Thread
	{
		ClientTest client;

		Cancel(ClientTest client) 
		{
			this.client = client;
		}

		public void run()
		{
			try {
				Thread.sleep(4000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("cancelling");

			LocalDevice.getLocalDevice().getDiscoveryAgent().cancelInquiry(client);

			System.out.println("cancelled");
		}
	}


	public static final UUID uuid =	new UUID("27012f0c68af4fbf8dbe6bbaf7ab651b", false);



	Vector devices;

	Vector records;



	public ClientTest(String message)

	{

		devices = new Vector();


		(new Cancel(this)).start();

		synchronized(this) {

			try {

				LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, this);



				try {

					wait();

				} catch(InterruptedException e) {

					e.printStackTrace();

				}

			} catch(BluetoothStateException e) {

				e.printStackTrace();

			}

		}



		for(Enumeration enum_d = devices.elements(); enum_d.hasMoreElements(); ) {

			RemoteDevice d = (RemoteDevice)enum_d.nextElement();



			try {

				System.out.println(d.getFriendlyName(false));

			} catch(IOException e) {

				e.printStackTrace();

			}



			System.out.println(d.getBluetoothAddress());

			

			synchronized(this) {

				records = new Vector();

			

				try {

					LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(new int[]{0x0100, 0x0101}, new UUID[]{uuid}, d, this);

					

					try {

						wait();

					} catch(InterruptedException e) {

						e.printStackTrace();

					}

				} catch(BluetoothStateException e) {

					e.printStackTrace();

				}

			}



			/*

				BUGBUG: need to give the system time to sort itself out after doing a service attribute request

			*/



			try {

				Thread.sleep(100);

			} catch(InterruptedException e) {

				e.printStackTrace();

			}

			

			for(Enumeration enum_r = records.elements(); enum_r.hasMoreElements(); ) {

				ServiceRecord r = (ServiceRecord)enum_r.nextElement();



				System.out.println(r.getAttributeValue(0x0100));



				try {

					StreamConnection conn = (StreamConnection)Connector.open(r.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false));



					DataOutputStream dos = new DataOutputStream(conn.openOutputStream());

					

					dos.writeUTF(message);

					

					dos.close();



					conn.close();

				} catch(IOException e) {

					e.printStackTrace();

				}

			}

		}

	}



	public static void main(String[] args)

	{

		if (args.length == 1)

			new ClientTest(args[0]);

		else

			System.out.println("syntax: ClientTest <message>");	

	}



	public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod)

	{

		devices.addElement(btDevice);

		System.out.println(cod);

	}



	public synchronized void inquiryCompleted(int discType)

	{

		System.out.println("inquiry completed: discType = " + discType);



		notifyAll();

	}



	public void servicesDiscovered(int transID, ServiceRecord[] servRecord)

	{

		for(int i = 0; i < servRecord.length; i++)

			records.addElement(servRecord[i]);

	}



	public synchronized void serviceSearchCompleted(int transID, int respCode)

	{

		System.out.println("service search completed: respCode = " + respCode);



		notifyAll();

	}

}
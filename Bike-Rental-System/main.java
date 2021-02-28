import java.io.*;
import java.util.*;
import java.lang.*;

abstract class Bike {

    static int[][] BikesPrice = { { 300, 320, 350, 400 }, { 200, 220, 240, 250 }, { 700, 750, 800, 900 },
            { 400, 420, 450, 480 } };

    static int[][] BikesQuantity = { { 3, 3, 3, 3 }, { 3, 3, 3, 3 }, { 3, 3, 3, 3 }, { 3, 3, 3, 3 } };

    int[][] BikesPenalty = { { 25, 30, 35, 35 }, { 20, 25, 30, 30 }, { 110, 130, 150, 180 }, { 30, 40, 45, 50 } };

    static String[] Types = { "Regular", "NonGear", "Sports", "Battery" };
    static String[] Colors = { "White", "Black", "Red", "Blue" };
    long time1, time2, DueTime = 60;
    static int ChoosenType = 0, ChoosenColor = 0;

    // 2D Vector "Status" for showing current status
    // static Vector<Vector<Integer>> Status = new Vector<Vector<Integer>>();
    // Vector<Vector<Integer>> status = new Vector<Vector<Integer>>(100);
    static int[][] status = new int[100][3];

    Scanner sc = new Scanner(System.in);

    void BikeDetails() {
        for (int i = 0; i < 4; i++) {
            System.out.println("\n  ##### " + Types[i] + " Bikes  #####");
            try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
            System.out.println("\nColors  " + "Price   " + "Penalty    " + "Quantity");

            for (int j = 0; j < 4; j++) {
                // System.out.println(Colors[j] + " " + BikesPrice[i][j] + " " +
                // BikesPenalty[i][j]
                // + " " + BikesQuantity[i][j]);
                System.out.print(Colors[j]);
                if (Colors[j] == "Red")
                    System.out.print("  ");
                if (Colors[j] == "Blue")
                    System.out.print(" ");

                System.out.print("    " + BikesPrice[i][j] + "     " + BikesPenalty[i][j] + "        "
                        + BikesQuantity[i][j] + "\n");
                
                try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                
              
            }
            try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

    void ShowDetails() {
        System.out.println("   -->Enter your Choice for Types of Bike: ");
        System.out.println("     1. Regular");
        System.out.println("     2. NonGear");
        System.out.println("     3. Sports");
        System.out.println("     4. Battery");

        ChoosenType = sc.nextInt();

        System.out.println("   -->Enter your Choice For colors : ");
        System.out.println("     1. White");
        System.out.println("     2. Black");
        System.out.println("     3. Red");
        System.out.println("     4. Blue");

        ChoosenColor = sc.nextInt();
    }

    synchronized void TakeBike(int I1) {

        ShowDetails();

        // status.add(I - 1, new Vector<Integer>(
        // Arrays.asList(ChoosenType, ChoosenColor, BikesPrice[ChoosenType -
        // 1][ChoosenColor - 1])));

        if (BikesQuantity[ChoosenType - 1][ChoosenColor - 1] >= 1) {
            status[I1 - 1][0] = ChoosenType;
            status[I1 - 1][1] = ChoosenColor;
            status[I1 - 1][2] = BikesPrice[ChoosenType - 1][ChoosenColor - 1];

            BikesQuantity[ChoosenType - 1][ChoosenColor - 1]--;

            time1 = System.currentTimeMillis() / 60000;

            try {

                Date date = new Date();
                String s = "";

                s = Integer.toString(I1).concat(" ").concat(Integer.toString(ChoosenType)).concat(" ")
                        .concat(Integer.toString(ChoosenColor)).concat(" ").concat(date.toString()).concat(" ")
                        .concat("1");

                FileWriter fw = new FileWriter("data.txt", true);

                for (int i = 0; i < s.length(); i++) {
                    fw.write(s.charAt(i));
                }
                fw.write("\n");
                fw.close();

            } catch (Exception e) {
                System.out.println("Exception in File Handling");
            }

        } else {
            System.out.println("----Sorry, Bike is not Available----");
        }
    }

    void ReturnBike(int I1) {

        ShowDetails();

        if (status[I1 - 1][2] != 0) {

            if (ChoosenType == status[I1 - 1][0] && ChoosenColor == status[I1 - 1][1]) {
                BikesQuantity[ChoosenType - 1][ChoosenColor - 1]++;
                time2 = System.currentTimeMillis() / 60000;
                status[I1 - 1][0] = 0;
                status[I1 - 1][1] = 0;
                status[I1 - 1][2] = 0;

                try {

                    Date date = new Date();
                    String s = "";

                    s = Integer.toString(I1).concat(" ").concat(Integer.toString(ChoosenType)).concat(" ")
                            .concat(Integer.toString(ChoosenColor)).concat(" ").concat(date.toString()).concat(" ")
                            .concat("0");

                    FileWriter fw = new FileWriter("data.txt", true);

                    for (int i = 0; i < s.length(); i++) {
                        fw.write(s.charAt(i));
                    }
                    fw.write("\n");
                    fw.close();
                    
                    System.out.println("You have returned your bike...");

                } catch (Exception e) {
                    System.out.println("Exception in File Handling");
                }

            } else {
                System.out.println("\n\nYou Have not choosen a proper value...");
            }

        } else {
            System.out.println("\n\nYou Have not choosen this Bike.");
        }

        // if (Colors[Status.get(I - 1).get(1) - 1] == Colors[ChoosenColor - 1]
        // && Types[Status.get(I - 1).get(0) - 1] == Types[ChoosenType - 1])
        // ans = true;

        // if (ans) {
        // BikesQuantity[ChoosenType - 1][ChoosenColor - 1]++;
        // System.out.println("We Hope You Enjoyed!!");
        // time2 = System.currentTimeMillis() / 60000;
        // } else {
        // System.out.println("\n\nYou Have not choosen this Bike.");
        // }
    }

    // prints current status of bike taken
    void CurrentStatus(int I1) {

        if (status[I1 - 1][2] != 0) {

            System.out.println("\n  Current Bike Taken: ");

            // Printing status
            System.out.println("\n  -->Bike Type : " + Types[status[I1 - 1][0] - 1]);
            System.out.println("  -->Bike Color : " + Colors[status[I1 - 1][1] - 1]);
            System.out.println("  -->Bike Price : " + status[I1 - 1][2]);

        } else {
            System.out.println("\n\nCurrently you do not have any Bikes...");
        }

    }

}

class Rent extends Bike implements Runnable {
    long Time = time2 - time1;

    int I11;

    Rent(int I1) {
        I11 = I1;
    }

    public void run() {
        try {
            Rent takeB = new Rent(I11);
            takeB.TakeBike(I11);

            System.out.println("   -->How much time will you choose to get bike ? ");
            System.out.println("     1. On Hourly Basis");
            System.out.println("     2. On Daily Basis");
            System.out.println("     3. On Weekly Basis");

            int x, rent;
            x = sc.nextInt();

            if (x == 1) {
                rent = takeB.GetHourRent();
            } else if (x == 2) {
                rent = takeB.GetDayRent();
            } else {
                rent = takeB.GetWeekRent();
            }

            System.out.println("  -->Your rent of this bike is " + rent + " Rs.");
            System.out.println("  -->if you don't return bike on time then there is "
                    + takeB.BikesPenalty[takeB.ChoosenType - 1][takeB.ChoosenColor - 1] + "Rs penalty");
            System.out.println("\n  ------Enjoy your Ride!!------");
            
            try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }

    int GetHourRent() {
        int Amount = BikesPrice[ChoosenType - 1][ChoosenColor - 1];
        Amount *= 1;

        // penalty case:
        if (Time > DueTime) {
            Amount += BikesPenalty[ChoosenType - 1][ChoosenColor - 1];
        }
        return Amount;
    }

    int GetDayRent() {
        int Amount = BikesPrice[ChoosenType - 1][ChoosenColor - 1];

        Amount *= (0.98 * 24);

        // penalty case:
        if (Time > DueTime) {
            Amount += BikesPenalty[ChoosenType - 1][ChoosenColor - 1];
        }
        return Amount;
    }

    int GetWeekRent() {
        int Amount = BikesPrice[ChoosenType - 1][ChoosenColor - 1];

        Amount *= (0.89 * 24 * 7);

        // penalty case:
        if (Time > DueTime) {
            Amount += BikesPenalty[ChoosenType - 1][ChoosenColor - 1];
        }
        return Amount;
    }
}

class All_User {

    static int I = 1, Index = 0;

    static String user, pass1, name, phone, email, pass2;
    static String User, pass, Vs1 = null, Vs2 = null;
    static int i;

    static Vector<Vector<String>> detail = new Vector<Vector<String>>();

    static Vector<Vector<String>> V = new Vector<Vector<String>>();

    public static void CreateUser() {

        System.out.println("\n----------------------------------");
        System.out.println("--> Enter Your Details Carefully :");
        System.out.println("----------------------------------");
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter your name : ");
        name = sc.next();

        System.out.println("\nEnter your Email id : ");
        email = sc.next();

        System.out.println("\nEnter your Username : ");
        user = sc.next();

        System.out.println("\nEnter your Password : ");
        pass1 = sc.next();

        System.out.println("\nConfirm Password : ");
        pass2 = sc.next();

        System.out.println("\n#########################");

        if (pass1.equals(pass2)) {
        	
        	// adding user -> Ith Row, 0th Column ; pass1 -> Ith Row, 1st Column;
            detail.add(new Vector<String>(Arrays.asList(user, pass1)));

            // adding name -> Ith Row, 0th Column ; email -> Ith Row, 1st Column;
            V.add(new Vector<String>(Arrays.asList(name, email)));

            Index = I;
            I++;

            System.out.println(" Succesfully Registered!");
            System.out.println("#########################");
        } else {
            System.out.println("Password Doesn't Match");
        }

    }

    public static boolean CheckUser() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter your Username : ");
        User = sc.nextLine();

        System.out.println("\nEnter your Password : ");
        pass = sc.nextLine();

        for (i = 0; i < (I - 1); i++) {
            if (detail.get(i).get(0).equals(user) && detail.get(i).get(1).equals(pass)) {
                Index = i + 1;
                return true;
            }
        }
        return false;
    }

    public static void Profile(int I1) {

        System.out.println("  PROFILE :-");
        System.out.println("\n --> User Name : " + detail.get(I1 - 1).get(0));
        System.out.println("  --> Name :      " + V.get(I1 - 1).get(0));
        System.out.println("  --> Email Id :  " + V.get(I1 - 1).get(1));
    }

    public int GetIndex() {

        return Index;
    }
}

class main {
    public static void main(String[] args) {
        System.out.println("-------------------------------------");
        System.out.println("####### Welcome to Online BRS #######");
        System.out.println("-------------------------------------");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nSELECT CHOICE :\n ");
            System.out.println(" 1. Register");
            System.out.println(" 2. Log-in");
            System.out.println(" 3. Exit");

            int choice, authenticated = 0;
            choice = sc.nextInt();

            if (choice == 1) {
                All_User.CreateUser();
                authenticated = 1;
            }

            else if (choice == 2) {
                boolean check = All_User.CheckUser();

                if (check)
                    authenticated = 1;
                else {
                    System.out.println("Invalid username or password");
                    continue;
                }
            }

            else if (choice == 3) {
                break;
            }

            if (authenticated == 1) {
            	
            	try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                System.out.println("\n\n########__WELCOME__########\n");
                try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                while (true) {
                    int select = 0;
                    
                    try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    System.out.println("\nSelect Choice :\n ");
                    System.out.println("  1. Show Avaiable Bikes");
                    System.out.println("  2. Profile");
                    System.out.println("  3. Take Bike");
                    System.out.println("  4. Return Bike");
                    System.out.println("  5. Current Status");
                    System.out.println("  6. Log-out");

                    select = sc.nextInt();
                    All_User y = new All_User();
                    Rent TakeAndReturnBike = new Rent(y.GetIndex());

                    if (select == 1) {
                        Rent Details = new Rent(y.GetIndex());
                        Details.BikeDetails();
                    }
                    else if (select == 2) {

                        All_User.Profile(y.GetIndex());
                    } else if (select == 3) {

                        Thread obj = new Thread(new Rent(y.GetIndex()));
                        obj.start();

                        try {
                            obj.join();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }

                    } else if (select == 4) {
                        All_User x = new All_User();
                        TakeAndReturnBike.ReturnBike(x.GetIndex());
                    } else if (select == 5) {

                        All_User x = new All_User();
                        TakeAndReturnBike.CurrentStatus(x.GetIndex());
                    } else if (select == 6) {
                        break;
                    }
                }
            } else {
                System.out.println("\nInvalid Usename or password");
            }
        }
    }
}

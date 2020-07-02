import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class fileProject {
	static int total_sum = 0;
	static int users = 0;
	static int checkouts = 0;
	static int ans = 0;
	static String option1 = "";
	static String option2 = "";
	static String uName = "";
	static String pass = "";
	static String str = "";
	static String merege_login = "";
	static String merege_bill = "";
	static String hashtext = "";
	static String item = "";
	static String quantity = "";
	static String price = "";
	static String date = "";
	static String title_align = "";
	static String content_align = "";
	static String border = "+------------------------";
	static String title_part1 = "STUDENT EXPENSE SYSTEM";
	static String title_part2 = "Welcome: " + uName;
	static File bills = new File("src\\Files\\Bills.txt");
	static FileReader bills_reader;
	static BufferedReader bills_bfr;
	static File info = new File("src\\Files\\Info.txt");
	static FileReader info_reader;
	static BufferedReader info_bfr;
	static File checkout = new File("src\\Files\\checkout.txt");
	static FileReader checkout_reader;
	static BufferedReader checkout_bfr;
	static ArrayList<String> uName_list = new ArrayList<String>();
	static ArrayList<String> uName_sum_List = new ArrayList<String>();
	static ArrayList<String> uName_sum_pay_recieve_list = new ArrayList<String>();

	public static void main(String[] args) {
		run_part1();
	}


	public static void run_part1() {
		System.out.println();
		System.out.print("****************************************\n");
		System.out.print("**       STUDENT EXPENSE SYSTEM       **\n");
		System.out.print("****************************************\n");
		System.out.printf("** %-34s **\n", "        [1] Sign UP");
		System.out.printf("** %-34s **\n", "        [2] Log In");
		System.out.printf("** %-34s **\n", "        [3] Checkout");
		System.out.printf("** %-34s **\n", "        [4] Exit");
		System.out.print("****************************************\n");
		System.out.print("****************************************\n");
		options_part1();
	}
	public static void options_part1() {
		create_files();
		try {
			option1 = JOptionPane.showInputDialog("Write down an Option:");
			while(!option1.equals("1") || !option1.equals("2")  || !option1.equals("3")
					|| !option1.equals("4")) {
				if(option1.equals("1")) {
					sign_up();
					break;
				}else if(option1.equals("2")) {
					log_in();
					break;
				}else if(option1.equals("3")) {
					checkout();
					break;
				}else if(option1.equals("4")) {
					exit();
					break;
				}else {
					option1 = JOptionPane.showInputDialog("INVALID request! Options from 1-4:");
				}
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Problem found in options1, Try agin later!");
		}
	}
	public static void create_files(){
		if (!bills.exists() || !info.exists() || !checkout.exists()) {
			try {
				bills.createNewFile();
				info.createNewFile();
				checkout.createNewFile();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problem in creating files!");
			}
		}
	}


	public static void sign_up() {
		try {
			uName = JOptionPane.showInputDialog("User Name:");
			while(invalid_username_taken(uName) || uName.trim().length() < 8 || uName.trim().length() > 22) {
				if(invalid_username_taken(uName)){
					uName_taken();
				}else if(uName.trim().length() < 8) {
					uName_short();
				}else if(uName.trim().length() > 22) {
					uName_long();
				}
			}
			pass = JOptionPane.showInputDialog("Password:");
			while(pass.trim().length()<8) {
				pass_short();
			}
			write_info();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Problem in sign up Found! Try agin.");
		}
	}
	public static void uName_taken() {
		JOptionPane.showMessageDialog(null, "Username Taken! Try another.");
		uName = JOptionPane.showInputDialog("Username:");
	}
	public static void uName_short() {
		JOptionPane.showMessageDialog(null, "User name must be more than 8 characters!");
		uName = JOptionPane.showInputDialog("User Name:");
	}
	public static void uName_long() {
		JOptionPane.showMessageDialog(null, "User name must be less than 23 characters!");
		uName = JOptionPane.showInputDialog("User Name:");
	}
	public static void pass_short() {
		JOptionPane.showMessageDialog(null, "Password must be more than 8 characters!");
		pass = JOptionPane.showInputDialog("Password:");
	}
	public static boolean invalid_username_taken(String username){
		try {
			info_reader = new FileReader(info);
			info_bfr = new BufferedReader(info_reader);
			while ((str = info_bfr.readLine()) != null){
				if (str.split(",")[0].equals(username)){
					return true;
				}
			}
			info_reader.close();
			info_bfr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem occurred, Try againfdisj! ");
		}
		return false;
	}
	public static void write_info() {
		try {
			FileWriter info_writer = new FileWriter(info, true);
			info_writer.write(uName);
			info_writer.write(",");
			info_writer.write(get_Md5(pass));
			info_writer.write("\n");
			info_writer.close();
			create_new_user_file(uName);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem occurred! Try agin later.");
		}
	}
	//code y net :)
	public static String get_Md5(String input) { 
		try {
			MessageDigest m_d = MessageDigest.getInstance("MD5"); 
			byte[] messageDigest = m_d.digest(input.getBytes()); 
			BigInteger sig = new BigInteger(1, messageDigest); 
			hashtext = sig.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "" + hashtext; 
			} 
			return hashtext; 
		}  
		catch (NoSuchAlgorithmException e) { 
			JOptionPane.showMessageDialog(null, "Problem on Encryption! Try Agin!");
		} 
		return "";
	} 
	public static void create_new_user_file(String username) {
		File user = new File("src\\Files\\" + uName + ".txt");
		try {
			if(!user.exists())
				user.createNewFile();
			JOptionPane.showMessageDialog(null, "Account created!");
			popUp_chekout_msg();
			run_part2();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem happened in creating new user File!");
		}
	}


	public static void log_in() throws IOException {
		uName = JOptionPane.showInputDialog("Username:").trim();
		pass = JOptionPane.showInputDialog("Password:").trim();
		merege_login = uName + "," + get_Md5(pass);
		if(login_validaity()) {
			if(option1.equals("3"))
				checkout();
			popUp_chekout_msg();
			run_part2();
		}
		while(!login_validaity()) {
			JOptionPane.showMessageDialog(null, "Check Username or Password!");
			log_in();//Recursion xD :)
		}
	}
	public static boolean login_validaity(){
		try {
			info_reader = new FileReader(info);
			info_bfr= new BufferedReader(info_reader);
			while((str = info_bfr.readLine()) != null){
				if(str.equals(merege_login)) {
					return true;
				}
			}
			info_reader.close();
			info_bfr.close();		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem in log in Validaity!");
		}
		return false;
	}


	public static void checkout() throws IOException {
		if(uName.isBlank()) {
			JOptionPane.showMessageDialog(null, "Please log in first!");
			log_in();
		}else {
			confirm_checkout_msg();
		}
	}
	public static void confirm_checkout_msg() throws IOException {
		if(is_uName_accepted_before()) {
			JOptionPane.showMessageDialog(null, "You accepted checkout before! Don't repeat it.");
			JOptionPane.showMessageDialog(null, "Press OK to view other options.");
			run_part2();
		}else {
			ans = JOptionPane.showConfirmDialog(null, "Are you sure you wanna checkout?");
			if(ans == 0) {
				add_uName_checkout();
				JOptionPane.showMessageDialog(null, "We sent a message for other users, after accepting it by all users, the proccess starts.");
				run_part2();
			}
			else {
				JOptionPane.showMessageDialog(null, "Press OK to see options menu.");
				run_part2();
			}
		}
	}
	public static void add_uName_checkout() {
		try {
			FileWriter checkout_writer = new FileWriter(checkout, true);
			checkout_writer.write(uName + "\n");
			checkout_writer.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Problem found in adding username to checkout! Try again later.");
		}
		clear_everything();
	}
	public static boolean is_uName_accepted_before() {
		try {
			checkout_reader = new FileReader(checkout);
			checkout_bfr = new BufferedReader(checkout_reader);
			while( (str = checkout_bfr.readLine()) != null){
				if (str.equals(uName)){
					checkout_bfr.close();
					return true;
				}
			}
			checkout_reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void clear_everything(){
		try {
			info_reader = new FileReader(info);
			info_bfr = new BufferedReader(info_reader);
			checkout_reader = new FileReader(checkout);
			checkout_bfr = new BufferedReader (checkout_reader);
			users = 0;
			checkouts = 0;
			while(info_bfr.readLine() != null){
				users++;
			}
			while(checkout_bfr.readLine() != null){
				checkouts++;
			}
			info_reader.close();
			info_bfr.close();
			checkout_reader.close();
			checkout_bfr.close();
			if (users == checkouts){
				clear_users_file();
				FileWriter bills_writer = new FileWriter(bills);
				BufferedWriter bills_bfr_writer = new BufferedWriter(bills_writer);
				bills_bfr_writer.write("");
				bills_bfr_writer.close();
				bills_writer.close();
				FileWriter checkout_writer = new FileWriter(checkout);
				BufferedWriter checkout_bfr_writer = new BufferedWriter(checkout_writer);
				checkout_bfr_writer.write("");
				checkout_bfr_writer.close();
				checkout_writer.close();
				JOptionPane.showMessageDialog(null, "Everey thing deleted!");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void clear_users_file() {
		try {
			info_reader = new FileReader(info);
			info_bfr = new BufferedReader(info_reader);
			while((str = info_bfr.readLine()) != null){
				String file_name = str.split(",")[0];
				File user = new File("src\\Files\\" + file_name + ".txt");
				FileWriter user_writer = new FileWriter (user);
				BufferedWriter user_bfr_writer = new BufferedWriter(user_writer);
				user_bfr_writer.write("");
				user_bfr_writer.close();
				user_writer.close();
			}
			info_reader.close();
			info_bfr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void popUp_chekout_msg() {
		if(is_it_necessaty_to_popUp()) {
			ans = JOptionPane.showConfirmDialog(null, "Do u also wanna checkout like your friends?");
			if(ans == 0) {
				add_uName_checkout();
				JOptionPane.showMessageDialog(null, "Request confirmed.");
				JOptionPane.showMessageDialog(null, "Click OK to see options menu.");
			}
		}
	}
	public static boolean is_it_necessaty_to_popUp() {
		try {
			checkout_reader = new FileReader(checkout);
			checkout_bfr = new BufferedReader (checkout_reader);
			while((str = checkout_bfr.readLine()) != null){
				if (str.equals(uName)){
					return false;
				}
			}if(checkout.length() == 0){
				return false;
			}
			checkout_reader.close();
			checkout_bfr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	//deleted every thing

	public static void exit() {
		System.exit(0);
	}


	public static void run_part2() throws IOException {
		System.out.print("\n****************************************\n");
		System.out.printf("** %-34s **\n","    Welcome: " + uName );
		System.out.print("****************************************\n");
		System.out.printf("** %-34s **\n", "    [1] Add item");
		System.out.printf("** %-34s **\n", "    [2] Delete item");
		System.out.printf("** %-34s **\n", "    [3] View my bill");
		System.out.printf("** %-34s **\n", "    [4] View all bills");
		System.out.printf("** %-34s **\n", "    [5] View checkout");
		System.out.printf("** %-34s **\n", "    [6] Return");
		System.out.printf("** %-34s **\n", "    [7] checkout list");
		System.out.print("****************************************\n");
		System.out.print("****************************************\n");
		option_part2();
	}
	public static void option_part2() throws IOException {
		option2 = JOptionPane.showInputDialog("Write down an Option:");
		while(!option2.equals("1") || !option2.equals("2")  || !option2.equals("3") || 
				!option2.equals("4") || !option2.equals("5")  || !option2.equals("6") || !option2.equals("7") ) {
			if(option2.equals("1")) {
				add_item();
				break;
			}else if(option2.equals("2")) {
				delete_item();
				break;
			}else if(option2.equals("3")) {
				view_my_bill();
				break;
			}else if(option2.equals("4")) {
				view_all_bills();
				break;
			}else if(option2.equals("5")) {
				view_checkout();
				break;
			}else if(option2.equals("6")) {
				return_();
				break;
			}else if(option2.equals("7")) {
				checkout_list();
				break;
			}else {
				option2 = JOptionPane.showInputDialog("INVALID request! Choose Options from 1-7:");
			}
		}
	}


	public static void add_item() {
		enter_bill();
		File user = new File("src\\Files\\" + uName + ".txt");
		try {
			FileWriter user_writer = new FileWriter(user, true);
			FileWriter bills_writer = new FileWriter(bills, true);
			user_writer.write(merege_bill+ "\n");
			user_writer.close();
			bills_writer.write(uName + ",");
			bills_writer.write(merege_bill + "\n");
			bills_writer.close();
			JOptionPane.showMessageDialog(null, "Item Successfully added! :)");
			option_part2();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Happened in adding item!");
		}
	}
	public static void enter_bill() {
		merege_bill = "";
		item = JOptionPane.showInputDialog("Item:");
		while(item.isBlank()) {
			JOptionPane.showMessageDialog(null, "Please Write the item!");
			item = JOptionPane.showInputDialog("Item:");
		}
		price = JOptionPane.showInputDialog("Price:");
		while(price.isBlank() || !price_validaity()) {
			JOptionPane.showMessageDialog(null, "Please Write the Price!");
			price = JOptionPane.showInputDialog("Price:");
		}
		quantity = JOptionPane.showInputDialog("Quantity:");
		while(quantity.isBlank()) {
			JOptionPane.showMessageDialog(null, "Please Write the Quantity!");
			quantity = JOptionPane.showInputDialog("Quantity:");
		}
		date = JOptionPane.showInputDialog("Date:");
		while(date.isBlank()) {
			JOptionPane.showMessageDialog(null, "Please Write the Date!");
			date = JOptionPane.showInputDialog("Date:");
		}
		merege_bill = item.trim()+ "," + price.trim() + "," + quantity.trim() + "," + date.trim(); 
	}
	public static boolean price_validaity() {
		try {
			int copy = Integer.parseInt(price);
			if(copy > 0)
				return true;
		}catch(Exception e) {
		}
		return false;
	}


	public static void delete_item() throws IOException {
		enter_bill();
		delete_match_user();
		delete_match_bill();
		option_part2();
	} 
	//Stack over flow temporary file
	public static void delete_match_user()  {
		File user = new File("src\\Files\\" + uName + ".txt");
		File temp_file = new File ("src\\Files\\tempFile.txt");
		try {
			if(!temp_file.exists())
				temp_file.createNewFile();
			FileReader user_reader = new FileReader(user);
			BufferedReader user_bfr = new BufferedReader(user_reader);
			FileWriter temp_writer = new FileWriter(temp_file, true);
			while((str = user_bfr.readLine()) != null){
				if (str.trim().equals(merege_bill.trim())){
					continue;
				}
				temp_writer.write(str + "\n");
			}
			temp_writer.close();
			user_reader.close();
			user_bfr.close();
			user.delete();
			temp_file.renameTo(new File("src\\Files\\" + uName + ".txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public static void delete_match_bill() {
		merege_bill = uName + "," + merege_bill;
		File temporary_file = new File ("src\\Files\\temoporaryFile.txt");
		try {
			if(!temporary_file.exists())
				temporary_file.createNewFile();
			FileWriter temp_writer = new FileWriter(temporary_file);
			bills_reader = new FileReader(bills);
			bills_bfr = new BufferedReader(bills_reader);
			temp_writer.write("");
			while((str = bills_bfr.readLine()) != null){
				if (str.trim().equals(merege_bill)){
					JOptionPane.showMessageDialog(null, "Item Successfully deleted :)");
					continue;
				}
				temp_writer .write(str+ "\n");
			}
			temp_writer.close();
			bills_reader.close();
			bills_bfr.close();
			bills.delete();
			boolean successful = temporary_file.renameTo(new File("src\\Files\\Bills.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	//Stack over flow table
	public static void view_my_bill() {
		System.out.print(border + border + border + border + "+\n");
		System.out.printf("| %-22s | %-22s | %-22s | %-22s | \n","Item","Price","Quantity","Date");
		System.out.print(border + border + border + border + "+\n");
		try {
			File user = new File("src\\Files\\" + uName + ".txt");
			FileReader user_reader = new FileReader(user);
			BufferedReader user_bfr = new BufferedReader(user_reader);
			while( (str = user_bfr.readLine()) != null) {
				String index0 = str.split(",")[0];
				String index1 = str.split(",")[1];
				String index2 = str.split(",")[2];
				String index3 = str.split(",")[3];
				System.out.printf("| %-22s | %-22s | %-22s | %-22s | \n",index0, index1, index2, index3);
			}
			user_reader.close();
			user_bfr.close();
			System.out.print(border + border + border + border + "+\n");
			JOptionPane.showMessageDialog(null, "Click OK to go back to options list.");
			run_part2();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem in viewing your bill! Try again :(");
		}
	}


	public static void view_all_bills() {
		sort_bills();
		System.out.print(border + border + border + border + border + "+\n");
		System.out.printf("| %-22s | %-22s | %-22s | %-22s | %-22s |\n", "Username", "Item", "Price", "Quantity", "Date");
		System.out.print(border + border + border + border + border + "+\n");
		try {
			bills_reader = new FileReader(bills);
			bills_bfr = new BufferedReader(bills_reader);
			while( (str = bills_bfr.readLine()) != null) {
				String index0 = str.split(",")[0];
				String index1 = str.split(",")[1];
				String index2 = str.split(",")[2];
				String index3 = str.split(",")[3];
				String index4 = str.split(",")[4];
				System.out.printf("| %-22s | %-22s | %-22s | %-22s | %-22s |\n",index0, index1, index2, index3, index4);
			}
			bills_reader.close();
			bills_bfr.close();
			System.out.print(border + border + border + border + border + "+\n");
			JOptionPane.showMessageDialog(null, "Click OK to go back to options list.");
			run_part2();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem happened in viewing all bills! Try again :(");
		}
	}
	public static void sort_bills() {
		try {
			bills_reader = new FileReader(bills);
			bills_bfr = new BufferedReader(bills_reader);
			ArrayList<String> lines = new ArrayList<String>();
			while((str = bills_bfr.readLine()) != null){
				lines.add(str);
			}
			Collections.sort(lines);      
			FileWriter bills_writer = new FileWriter(bills);
			bills_writer.write("");
			for (String line : lines){
				bills_writer.write(line);
				bills_writer.write("\n");
			}
			bills_writer.close();
			bills_reader.close();
			bills_bfr.close();
			lines.clear();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem in sorting the bills! Try again:(");
		}
	}


	public static void view_checkout() throws IOException {
		unames_list();
		uName_sum_list();
		total_sum();
		name_sum_pay_recieve_list();
		view_checkout_table();
		System.out.println("Total money of the month: " + total_sum);
		JOptionPane.showMessageDialog(null, "Click OK to go back to options list.");
		run_part2();
	}
	public static void unames_list() {
		try {
			info_reader = new FileReader(info);
			info_bfr = new BufferedReader(info_reader);
			uName_list.clear();
			while((str = info_bfr.readLine()) != null){
				uName_list.add(str.split(",")[0]);
			}
			info_reader.close();
			info_bfr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem in usernames list! :(");
		}
	}
	public static void uName_sum_list() {
		uName_sum_List.clear();
		for (int i = 0; i<uName_list.size(); i++){
			String path = "src\\Files\\" + uName_list.get(i) + ".txt";
			File user = new File(path);
			try {
				FileReader user_reader = new FileReader(user);
				BufferedReader user_bfr = new BufferedReader(user_reader);
				int sum = 0;
				while ((str = user_bfr.readLine()) != null){
					sum += Integer.parseInt(str.split(",")[1]);
				}
				uName_sum_List.add(uName_list.get(i));
				uName_sum_List.add(sum+"");
				user_reader.close();
				user_bfr.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problem in usernames and sum list! :(");
			}
		}
	}
	public static void total_sum() {
		try {
			bills_reader = new FileReader(bills);
			bills_bfr = new BufferedReader(bills_reader);
			total_sum = 0;
			while( (str=bills_bfr.readLine()) != null){
				total_sum +=Integer.parseInt(str.split(",")[2]);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem occured in total sum! :(");
		}
	}
	public static void name_sum_pay_recieve_list() {
		int n = uName_list.size();
		int avg = total_sum / n;
		int pay = 0;
		int recieve = 0;
		uName_sum_pay_recieve_list.clear();
		//[sakar hama , 9000 , shna shna , 4000]
		for (int i = 1; i<uName_sum_List.size(); i+=2){
			pay = avg - Integer.parseInt(uName_sum_List.get(i));
			if (pay < 0){
				pay = 0;}
			recieve = Integer.parseInt(uName_sum_List.get(i)) - avg;
			if (recieve < 0){
				recieve = 0;}
			uName_sum_pay_recieve_list.add(uName_sum_List.get(i-1));
			uName_sum_pay_recieve_list.add(uName_sum_List.get(i));
			uName_sum_pay_recieve_list.add(pay + "");
			uName_sum_pay_recieve_list.add(recieve + "");
		}
	}
	public static void view_checkout_table() {
		System.out.print(border + border + border + border + "+\n");
		System.out.printf("| %-22s | %-22s | %-22s | %-22s |\n", "Username", "Sum", "Pay", "Recieve");
		System.out.print(border + border + border + border + "+\n");
		for (int i = 0; i<uName_sum_pay_recieve_list.size(); i+=4){
			String index0 = uName_sum_pay_recieve_list.get(i);
			String index1 = uName_sum_pay_recieve_list.get(i+1);
			String index2 = uName_sum_pay_recieve_list.get(i+2);
			String index3 = uName_sum_pay_recieve_list.get(i+3);
			System.out.printf("| %-22s | %-22s | %-22s | %-22s |\n", index0, index1, index2, index3);
		}
		System.out.print(border + border + border + border + "+\n");
	}


	public static void return_() {
		run_part1();
	}


	public static void checkout_list() {
		System.out.print(border + "+-------------------+\n");
		System.out.printf("| %-22s | %-17s |\n","Usernames","Accepted Checkout");
		System.out.print(border + "+-------------------+\n");
		try {
			info_reader = new FileReader(info);
			info_bfr = new BufferedReader(info_reader);
			String str1 = "";
			String str2 = "";
			String mark = "";
			while( (str1 = info_bfr.readLine()) != null){
				String user = str1.split(",")[0];
				System.out.printf("| %-22s ", user);
				checkout_reader = new FileReader(checkout);
				checkout_bfr = new BufferedReader(checkout_reader);
				while( (str2 = checkout_bfr.readLine()) != null){
					if(user.equals(str2)) {
						mark = "\u2713";
						break;
					}else {
						mark = "\u2715";
					}
				}
				if(checkout.length()==0)
					mark = "\u2715";
				System.out.printf("| %-42s |\n", mark);
				checkout_reader.close();
				checkout_bfr.close();
			}
			System.out.print(border + "+-------------------+\n");
			info_reader.close();
			info_bfr.close();
			JOptionPane.showMessageDialog(null, "Press OK to view other options");
			run_part2();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

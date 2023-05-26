# Student Expense Management System

The given program is a comprehensive console-based expense management system designed specifically for students residing in a dormitory. The purpose of the system is to help students track, manage, and split expenses in a clear, streamlined manner. The data is stored in text files to simplify the learning process, serving as a practical introduction to data handling before advancing to more complex database systems.

**Note: This program, is an assignment of the programming module at Koya University's Software Engineering course (2020), tha is a student-friendly system designed to simplify the tracking and splitting of dormitory expenses. Everything is done according to the assignment requirements.**

## Program Start: Sign Up, Log In, Checkout, or Exit:

- Sign Up allows new users to create an account with a unique username and password. The username should be between 8 and 22 characters long and the password should be at least 8 characters long. The information is stored securely, with the password being hashed using the MD5 algorithm.
- Log In lets existing users access their account using their username and password.
- Checkout initiates the expense splitting process. This option can only be used after logging in.
- Exit allows users to safely close the program.


## After logging in or signing up, users can do the following:

- Add Item: Add a new expense to their account.
- Delete Item: Remove an existing expense from their account.
- View My Bill: See their individual expenses.
- View All Bills: Access all expense information, providing a complete overview.
- View Checkout: See the final split of expenses.
- Return: Go back to the main menu.
- Checkout List: Review the list of users who have accepted the checkout.
**The Checkout function is particularly important. When a user opts to checkout, a request is sent to all users in the system. Once all users confirm the checkout, the expenses are finalized, and the total is evenly distributed amongst the users. This allows for easy splitting of shared expenses. The expense information is stored in text files, providing a clear audit trail.**

All data manipulations (adding, deleting, viewing items) and user interactions (sign up, log in, and checkout) are handled by dedicated functions to ensure modularity, readability, and ease of debugging. Furthermore, the program takes special care to handle exceptions, ensuring the stability and robustness of the system.

In essence, this is an effective tool for students living together to manage shared expenses. The intuitive interface and automated calculations simplify the often-complicated process of expense splitting, allowing users to focus more on their studies and less on financial logistics.


# Some Visual representation:

<ImageGroup>
<img alt="First view of the app" src="https://raw.githubusercontent.com/SakarDev/StudentExpenseManagementSystem/master/1.png" />
<img alt="view all bills" src="https://raw.githubusercontent.com/SakarDev/StudentExpenseManagementSystem/master/viewAllBills.png" />
<img alt="view My Bill" src="https://raw.githubusercontent.com/SakarDev/StudentExpenseManagementSystem/master/viewMyBill.png" />
<img alt="view Checkout" src="https://raw.githubusercontent.com/SakarDev/StudentExpenseManagementSystem/master/viewCheckout.png" />
</ImageGroup>


COM1321_2024_PROJECT

This application helps users calculate their monthly loan repayments for homes and vehicles and track their monthly expenses. The program provides notifications if total expenses exceed 75% of the user's gross income. It also allows users to choose between renting or buying a property, and if they are purchasing a vehicle, it calculates the monthly loan payment along with the insurance premium.

FEATURES:
Loan Calculations: Calculate monthly repayments for home and vehicle loans based on user input.
Expense Tracking: Record monthly expenses such as groceries, utilities, travel, and more.
Threshold Notifications: Warn the user if their total expenses exceed 75% of their gross income.
Sorted Expenses: Display all expenses in descending order for easy review.

HOW TO COMPILE?

Ensure you have Java or any Java-compatible compiler installed on your system.

HOW TO RUN THE CODE?
After compiling, run the program using the following command:

java Expense2

OR 

Alternatively, you can run the code using an IDE like NetBeans or any other Java compiler.

Follow the on-screen instructions to input your income, expenses, and loan details. The program will calculate your loan repayments and provide an overview of your monthly expenses.

EXPLANATION OF KEY COMPONETS:

Loan Class (Abstract): Defines the structure of a basic loan, including properties such as purchase price, deposit, interest rate, and loan duration. Subclasses must implement the getAmount() method, which calculates the monthly loan repayment.

HomeLoan Class: Extends the Loan class and implements the getAmount() method to calculate the home loan repayment. The loan term is fixed at 30 years (360 months).

VehicleLoan Class: Extends the Loan class with a fixed loan term of 5 years (60 months). The getAmount() method calculates the vehicle loan repayment.

Main Class (Expense2): Handles user input, records expenses, and calculates total expenses. It also checks if expenses exceed 75% of gross income and displays expenses in descending order.

USAGE DETAILS:

Property Purchase: If the user chooses to buy a property, they provide the purchase price, deposit, and interest rate. The monthly home loan repayment is calculated and added to the expenses list.

Vehicle Purchase: If the user is buying a vehicle, they enter the purchase price, deposit, interest rate, and insurance premium. The program calculates the total monthly cost (loan repayment + insurance) and adds it to the expenses.

Expense Threshold Warning: The program warns the user if their total monthly expenses exceed 75% of their gross income.

Sorting Expenses: Expenses are displayed in descending order based on their value.

SAMPLE OR EXAMPLE OF INPUT/OUTPUT
EXAMPLE INPUT:

Enter your Gross monthly income: 5000
Enter your Monthly tax deductions: 500
Enter your Groceries: 300
Enter your Water and lights: 100
Enter your Travel costs: 150
Enter your Phone expenses: 50
Enter your Other expenses: 200
Are you renting or buying a property? (rent/buy): buy
Enter the purchase price of the property: 200000
Enter the total deposit: 20000
Enter the interest rate (%): 5
Are you buying a vehicle? (yes/no): yes
Enter the purchase price of the vehicle: 25000
Enter the total deposit for the vehicle: 2000
Enter the interest rate for the vehicle (%): 6
Enter the estimated insurance premium: 100

EXAMPLE  OUTPUT:

Monthly home loan repayment: 966.28
Warning: Home loan repayment is more than a third of your gross income.
Total monthly cost of buying the vehicle (loan + insurance): 510.85
Warning: Total expenses exceed 75% of your gross income.
Available monthly money after deductions: 1223.87
Expenses in descending order:
966.28
510.85
300.0
200.0
150.0
100.0
50.0


This application serves as a valuable tool for users looking to manage their finances by calculating loan repayments and tracking expenses. By providing real-time notifications about financial thresholds, users can make informed decisions regarding their financial commitments.


# codingAssignmentJE
1. To run the program I’ve included a dist folder containing a .jar file that can be run from command prompt (you need Java installed on your computer), so there is no need to compile the code.

	To run the application from cmd enter:

	java -jar “yourfilepath\dist\JustEats.jar”

	Where yourfilepath is the path to the dist folder.

2. Assumptions making this solution:

	-It wasn’t specified how to display the address/what fields it should include, so I decided to not include the coordinates, as it’s not something I can see in apps/solutions often.

	-Some cuisines types in the API were not ‘technically’ cuisines (for example ‘deals’), but I included all the data returned anyways.

4. Improvements I could make include:
   
	-Rather than printing the data, storing each restaurant as a restaurant object (I have an example constructor) and creating an Array/List would enable to filter or sort the restaurant data. For example, sorting the list from highest to lowest rated or by restaurant name alphabetically. However, this is something outside the scope of assignment criteria, so I opted for printing the data. 

	-The application takes a few seconds to return the restaurant list, so it would be worth looking into how to optimize the program to improve performance. As a quick solution for a program where specifically ten restaurants need to be displayed, I could have not scanned the whole API response but stopped after a certain amount of lines. Otherwise, I would need to look more into handling APIs and JSON files in java to see how I can improve the performance. 

	-Adding int parameter to printResults() to customize how many restaurants need to be returned

	-Allow user to enter the postcode they want to see the restaurant data for

	-Create a nice interface

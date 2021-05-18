import java.util.ArrayList;
import java.util.Scanner;

public class ProjectPlan {

	public static void main (String[] args) {

		Scanner scanner = new Scanner(System.in);
		ArrayList<Task> tasks = new ArrayList<Task>();
		int taskNum = 1;
		boolean hasTask = true;
		
		System.out.println("Project Plan\n");
		
		// while there is task
		while(hasTask) {
			
			String name = "";
			int duration = 0;
			int start = 0;
			int end = 0;
			String dependencies = "0";
			
			// display task number
			System.out.println("# Task " + taskNum);
			
			// input name
			System.out.print("- Name: ");
			name = scanner.nextLine();
			
			// input duration
			System.out.print("- duration: ");
			duration = Integer.parseInt(scanner.nextLine());
			if(taskNum == 1) {
				System.out.println("- dependencies(none available): 0");
			} else {
				System.out.print("- dependencies: (");
				 for(Task t: tasks) { 
					 System.out.print(t.id + ", ");
				 }
				System.out.print("0 if none): ");
				dependencies = scanner.nextLine();
			}
			
			//calculate start and end days
			if(dependencies.equals("0")) {
				start = 0;
				end = duration;
			} else {
				String[] dependencyList = dependencies.split(" ");
				for (String id: dependencyList) {
					int dependencyEnd = tasks.get(Integer.parseInt(id)-1).end;
					if(dependencyEnd > start)
						start = dependencyEnd;
				}
				end = duration + start;
			}
			
			// add new task
			tasks.add(new Task(taskNum, name, duration, start, end, dependencies));
			
			System.out.println("Done adding Task " + taskNum);
			
			// continue adding task?
			System.out.print("\nAdd new Task? (Y/N) ");
			String cont = scanner.nextLine();
			hasTask = (cont.equalsIgnoreCase("Y"));
			
			// new line
			System.out.println();
			
			// increment task number
			taskNum ++;
		}
		
		String empty = "    ";
		String BarLine = "++++";
		
		int maxName = 0;
		int maxDays = 0;

		ArrayList<String> taskName = new ArrayList<String>();
		ArrayList<String> barGraph = new ArrayList<String>();
		
		for(Task t: tasks) {
			String tn = t.id + ". " + t.name;
			if (tn.length() > maxName)
				maxName = tn.length();
			
			if (t.end > maxDays)
				maxDays = t.end;
			
			String bar = "";
			for (int i=0; i < t.end; i++) {
				if(i < t.start)
					bar += empty;
				else 
					bar += BarLine;
			}
			
			taskName.add(tn);
			barGraph.add(bar);
		}
		
		System.out.println("Done!\n");
		
		System.out.println("Schedule\n");
		for (int i=0; i<taskName.size(); i++) {
			System.out.println(padRight(taskName.get(i), maxName)  + " |" + padLeft(barGraph.get(i), 4));
		}
		System.out.println(padRight("", maxName) + " +" + generateLines(maxDays));
		System.out.println(padRight("", maxName) + "  " + generateDays(maxDays));

		scanner.close();
	}
	

	public static String padRight (String s, int n) {
		return String.format("%-" + n + "s", s);  
	}
	
	public static String padLeft (String s, int n) {
		return String.format("%" + n + "s", s);  
	}
	
	public static String generateDays (int n) {
		String days = "";
		for (int i=0; i<n; i++) {
			days += padLeft(Integer.toString(i+1), 4);
		}
		return days;
	}
	
	public static String generateLines (int n) {
		String lines = "";
		for (int i=0; i<n; i++) {
			lines += "----";
		}
		return lines;
	}
}
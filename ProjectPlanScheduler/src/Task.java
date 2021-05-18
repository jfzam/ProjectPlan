public class Task {
	
	int id;
	String name;
	int duration;
	int start;
	int end;
	String dependencies;
	
	public Task(int id, String name, int duration, int start, int end, String dependencies) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.start = start;
		this.end = end;
		this.dependencies = dependencies;
	}
	
}
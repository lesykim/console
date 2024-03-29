package console;

public interface CRUD {
	public void createData(User user);
	public void readData(User user);
	public void updateData(User user, int number);
	public void deleteData(User user);
}

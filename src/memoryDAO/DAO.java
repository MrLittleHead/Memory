package memoryDAO;

import java.util.ArrayList;

import memoryBo.ParticipationBo;

public abstract class DAO<T> {

	public abstract boolean create(T obj);

	public abstract boolean delete(T obj);

	public abstract boolean update(T obj);
	
	public abstract T read(int id);
}

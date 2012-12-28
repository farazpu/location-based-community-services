package net.fast.lbcs.data;

public class DataSourceFactory {

	public static DataSource getDataSource() {
		return new InMemoryDataSource();
	}
}

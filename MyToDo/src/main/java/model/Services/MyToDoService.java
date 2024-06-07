package model.Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.beans.MyToDoBean;
import model.beans.UsersBean;
import util.DbUtil;

public class MyToDoService {
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static String sql = "";
	static String deadlineOrder = "asc";
	static String priorityOrder = "asc";
	static String markd = "▲";
	static String markp = "  ";
	static int judge = 0;

	//期限ソート
	public static void deadlineOrder() {
		judge = 0;
		markd = "▲";
		markp = "  ";
		if (deadlineOrder.equals("asc")) {
			markd = "▼";
			deadlineOrder = "desc";
		} else {
			markd = "▲";
			deadlineOrder = "asc";
		}
	}

	//優先度ソート
	public static void priorityOrder() {
		judge = 1;
		markd = "  ";
		markp = "▲";
		if (priorityOrder.equals("asc")) {
			markp = "▼";
			priorityOrder = "desc";
		} else {
			markp = "▲";
			priorityOrder = "asc";
		}

	}

	//getter mark
	public static String getMarkd() {
		return markd;
	}

	public static String getMarkp() {
		return markp;
	}

	//taskListにテーブルデータを格納
	public static ArrayList<MyToDoBean> addAll() {
		final ArrayList<MyToDoBean> taskList = new ArrayList<>();
		try {
			conn = DbUtil.open();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int priority = rs.getInt("priority");
				Date sqlDate = rs.getDate("deadline");
				LocalDate deadline = sqlDate.toLocalDate();
				String content = rs.getString("content");
				int completed = rs.getInt("completed");
				int user_id = rs.getInt("user_id");

				taskList.add(new MyToDoBean(id, title, priority, deadline, content, completed, user_id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return taskList;
	}

	//UsersBeanにデータを格納
	public static UsersBean addAllUser() {
		UsersBean ub = null;
		try {
			conn = DbUtil.open();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String name = rs.getString("name");
				String e_mail = rs.getString("e_mail");
				String password = rs.getString("password");

				ub = new UsersBean(user_id, name, e_mail, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ub;
	}

	//タスクを全件表示
	public static ArrayList<MyToDoBean> selectAll(int user_id) {
		ArrayList<MyToDoBean> taskList = new ArrayList<>();
		if (judge == 0) {
			sql = "select * ";
			sql += "from task ";
			sql += "inner join users ";
			sql += "on task.user_id = users.user_id ";
			sql += "where users.user_id = " + user_id;
			sql += " order by deadline " + deadlineOrder;
		} else {
			sql = "select * ";
			sql += "from task ";
			sql += "inner join users ";
			sql += "on task.user_id = users.user_id ";
			sql += "where users.user_id = " + user_id;
			sql += " order by priority " + priorityOrder;
		}

		taskList = addAll();
		return taskList;
	}

	//タスクを追加
	public ArrayList<MyToDoBean> insert(String title, int priority, LocalDate deadline, String content, int user_id) {
		ArrayList<MyToDoBean> taskList = new ArrayList<>();
		sql = "insert into task (title, priority, deadline, content, user_id) ";
		sql += "values (?, ?, ?, ?, ?)";

		try {
			conn = DbUtil.open();
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setInt(2, priority);
			ps.setObject(3, deadline);
			ps.setString(4, content);
			ps.setInt(5, user_id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		taskList = addAll();
		return taskList;
	}

	//completedを0or1にする
	public ArrayList<MyToDoBean> completed(int id, int completed) {

		ArrayList<MyToDoBean> taskList = new ArrayList<>();
		if (completed == 0) {
			sql = "update task ";
			sql += "set completed = 1 ";
			sql += "where id = " + id;
		} else if (completed == 1) {
			sql = "update task ";
			sql += "set completed = 0 ";
			sql += "where id = " + id;
		}

		try {
			conn = DbUtil.open();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		sql = "select * ";
		sql += "from task ";

		taskList = addAll();
		return taskList;

	}

	//完了済みタスクを全件表示
	public ArrayList<MyToDoBean> completedTask(int user_id) {
		ArrayList<MyToDoBean> taskList = new ArrayList<>();

		if (judge == 0) {
			sql = "select * ";
			sql += "from task ";
			sql += "inner join users ";
			sql += "on task.user_id = users.user_id ";
			sql += "where users.user_id = " + user_id;
			sql += " and completed = 1 ";
			sql += "order by deadline " + deadlineOrder;
		} else {
			sql = "select * ";
			sql += "from task ";
			sql += "inner join users ";
			sql += "on task.user_id = users.user_id ";
			sql += "where users.user_id = " + user_id;
			sql += " and completed = 1 ";
			sql += "order by deadline " + priorityOrder;
		}

		taskList = addAll();
		return taskList;
	}

	//遅延
	public ArrayList<MyToDoBean> delay(int user_id) {
		ArrayList<MyToDoBean> taskList = new ArrayList<>();
		LocalDate today = LocalDate.now();
		conn = DbUtil.open();

		try {
			if (judge == 0) {
				sql = "select * ";
				sql += "from task ";
				sql += "inner join users ";
				sql += "on task.user_id = users.user_id ";
				sql += "where users.user_id = " + user_id;
				sql += " and deadline < ? and completed = 0 ";
				sql += "order by deadline " + deadlineOrder;
			} else {
				sql = "select * ";
				sql += "from task ";
				sql += "inner join users ";
				sql += "on task.user_id = users.user_id ";
				sql += "where users.user_id = " + user_id;
				sql += " and deadline < ? and completed = 0 ";
				sql += "order by deadline " + priorityOrder;
			}

			ps = conn.prepareStatement(sql);
			ps.setDate(1, Date.valueOf(today));

			try {
				rs = ps.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					int priority = rs.getInt("priority");
					Date sqlDate = rs.getDate("deadline");
					LocalDate deadline = sqlDate.toLocalDate();
					String content = rs.getString("content");
					int completed = rs.getInt("completed");
					user_id = rs.getInt("user_id");

					taskList.add(new MyToDoBean(id, title, priority, deadline, content, completed, user_id));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return taskList;
	}

	//削除
	public ArrayList<MyToDoBean> delete(int id) {
		ArrayList<MyToDoBean> taskList = new ArrayList<>();
		sql = "delete from task ";
		sql += "where id = " + id;

		try {
			conn = DbUtil.open();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		taskList = addAll();
		return taskList;
	}

	//編集するタスクを表示
	public MyToDoBean editShow(int id) {
		MyToDoBean mtdb = null;

		sql = "select * ";
		sql += "from task ";
		sql += "where id = " + id;

		try {
			conn = DbUtil.open();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt("id");
				String title = rs.getString("title");
				int priority = rs.getInt("priority");
				Date sqlDate = rs.getDate("deadline");
				LocalDate deadline = sqlDate.toLocalDate();
				String content = rs.getString("content");
				int completed = rs.getInt("completed");
				int user_id = rs.getInt("user_id");

				mtdb = new MyToDoBean(id, title, priority, deadline, content, completed, user_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return mtdb;
	}

	//編集
	public ArrayList<MyToDoBean> edit(int id, String title, int priority, LocalDate deadline, String content) {
		ArrayList<MyToDoBean> taskList = new ArrayList<>();
		sql = "update task ";
		sql += "set title = ?, ";
		sql += "priority = ?, ";
		sql += "deadline = ?, ";
		sql += "content = ? ";
		sql += "where id = " + id;

		try {
			conn = DbUtil.open();
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setInt(2, priority);
			ps.setObject(3, deadline);
			ps.setString(4, content);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		taskList = addAll();
		return taskList;
	}

	//進行中
	public ArrayList<MyToDoBean> doing(int user_id) {
		ArrayList<MyToDoBean> taskList = new ArrayList<>();
		LocalDate today = LocalDate.now();
		conn = DbUtil.open();

		try {
			if (judge == 0) {
				sql = "select * ";
				sql += "from task ";
				sql += "inner join users ";
				sql += "on task.user_id = users.user_id ";
				sql += "where users.user_id = " + user_id;
				sql += " and deadline > ? and completed = 0 ";
				sql += "order by deadline " + deadlineOrder;
			} else {
				sql = "select * ";
				sql += "from task ";
				sql += "inner join users ";
				sql += "on task.user_id = users.user_id ";
				sql += "where users.user_id = " + user_id;
				sql += " and deadline > ? and completed = 0 ";
				sql += "order by deadline " + priorityOrder;
			}

			ps = conn.prepareStatement(sql);
			ps.setDate(1, Date.valueOf(today));

			try {
				rs = ps.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					int priority = rs.getInt("priority");
					Date sqlDate = rs.getDate("deadline");
					LocalDate deadline = sqlDate.toLocalDate();
					String content = rs.getString("content");
					int completed = rs.getInt("completed");
					user_id = rs.getInt("user_id");

					taskList.add(new MyToDoBean(id, title, priority, deadline, content, completed, user_id));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}

	//ログイン
	public UsersBean login(String e_mail, String password) {
		UsersBean ub = null;

		sql = "select * from users ";
		sql += "where e_mail = ? and password = ?";

		try {
			conn = DbUtil.open();
			ps = conn.prepareStatement(sql);
			ps.setString(1, e_mail);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				int user_id = rs.getInt("user_id");
				String name = rs.getString("name");
				e_mail = rs.getString("e_mail");
				password = rs.getString("password");

				ub = new UsersBean(user_id, name, e_mail, password);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ub;
	}

	//アカウント作成
	public void accountInsert(String name, String e_mail, String password) {
		sql = "insert into users (name, e_mail, password) ";
		sql += "values (?, ?, ?)";

		try {
			conn = DbUtil.open();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, e_mail);
			ps.setString(3, password);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//ログインのログ
	public void logniLog(int user_id, String name, String e_mail, String password) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(
					new FileWriter("C:\\pleiades\\2022-12\\workspace\\MyToDo\\src\\main\\webapp\\loginLog.txt", true));

			user_id = rs.getInt("user_id");
			name = rs.getString("name");
			e_mail = rs.getString("e_mail");
			password = rs.getString("password");
			//今の時刻を取得
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = now.format(formatter);

			String logMessage = "Time:" + formattedDateTime + " ";
			logMessage += "ID:" + user_id + " ";
			logMessage += "Name:" + name + " ";
			logMessage += "E-Mail:" + e_mail + " ";
			logMessage += "PassWord:" + password + " ";

			writer.write(logMessage);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Error writing to log file: " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// ファイルを閉じる
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				System.out.println("Error closing log file: " + e.getMessage());
			}
		}

	}
	
	//アカウント編集のログ
	public void editAccountLog(int user_id, String name, String e_mail, String password) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(
					new FileWriter("C:\\pleiades\\2022-12\\workspace\\MyToDo\\src\\main\\webapp\\editAccountLog.txt", true));

			user_id = rs.getInt("user_id");
			name = rs.getString("name");
			e_mail = rs.getString("e_mail");
			password = rs.getString("password");
			//今の時刻を取得
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = now.format(formatter);

			String logMessage = "Time:" + formattedDateTime + " ";
			logMessage += "ID:" + user_id + " ";
			logMessage += "Name:" + name + " ";
			logMessage += "E-Mail:" + e_mail + " ";
			logMessage += "PassWord:" + password + " ";

			writer.write(logMessage);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Error writing to log file: " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				System.out.println("Error closing log file: " + e.getMessage());
			}
		}

	}
	
	//アカウント編集
	public void editAccount(int user_id,String name, String e_mail, String password) {
		
		sql = "update users ";
		sql += "set name = ?, ";
		sql += "e_mail = ?, ";
		sql += "password = ? ";
		sql += "where user_id = ?";

		try {
			conn = DbUtil.open();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, e_mail);
			ps.setObject(3, password);
			ps.setInt(4, user_id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//アカウント削除
	public void deleteAccount(int user_id) {
		sql = "delete from users ";
		sql += "where user_id = " + user_id;

		try {
			conn = DbUtil.open();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
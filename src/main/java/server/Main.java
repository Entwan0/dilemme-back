package server;
/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
public class Main {

	private int playerTurn = 1;
	private Round round = new Round();
	private String choiceJ1;
	private String choiceJ2;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	ArrayList<String> players = new ArrayList<String>();

	@RequestMapping(value = "/player", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ResponseBody
	public int players() {
		int result = 1;
		if (players.size() == 0) {
			players.add("J1");
			result = 1;
		} else if (players.contains("J2") && !players.contains("J1")) {
			players.add("J1");
			result = 1;
		} else if (!players.contains("J2") && players.contains("J1")) {
			players.add("J2");
			result = 2;
		} else {
			result = 2;
		}
		return result;
	}

	@RequestMapping(value = "/waitOtherPLayer", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ResponseBody
	public boolean waitOtherPLayer(@RequestBody int player) {
		if (this.playerTurn == player)
			return true;
		else
			return false;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/play", method = RequestMethod.POST)
	@ResponseBody
	public void play(@RequestBody String moove) {
		System.out.println(moove);

		if (this.playerTurn == 1) {
			this.choiceJ1 = moove;
			this.playerTurn = 2;
		} else {
			this.choiceJ2 = moove;
			this.round.treat_choices(this.choiceJ1,this.choiceJ2);
			System.out.println("point J1 = " + this.round.getGame().getPointJ1());
			System.out.println("point J2 = " + this.round.getGame().getPointJ2());
			this.playerTurn = 1;
		}
	}

	@RequestMapping(value = "/leave", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ResponseBody
	public String leave(@RequestBody int player) {
		System.out.println(player);
		if (player == 1) {
			this.players.remove("J1");
		} else {
			this.players.remove("J2");
		}
		System.out.println(this.players);
		return "leave !";
	}
	
	@RequestMapping(value = "/updatePoints", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ResponseBody
	public String updatePoints(@RequestBody String t) {
		String result = this.round.getGame().getPointJ1() + "," + this.round.getGame().getPointJ2();
		return result;
	}

	@RequestMapping("/")
	String index() {
		return "index";
	}

	@RequestMapping("/db")
	String db(Map<String, Object> model) {
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
			stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
			ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getTimestamp("tick"));
			}

			model.put("records", output);
			return "db";
		} catch (Exception e) {
			model.put("message", e.getMessage());
			return "error";
		}
	}

	@Bean
	public DataSource dataSource() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

}

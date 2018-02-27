package com.staryea.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Test4 {

	public static void main(String[] args) {
		String line = null;
		List<Map> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File("F://mytest.txt")))) {

			while ((line = br.readLine()) != null) {
				list.add(JSON.parseObject(line, Map.class));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, List<Map>> classify = new HashMap<>();

		list.stream().filter(m -> m.containsKey("transactionId")).forEach(m -> {
			String transactionId = m.get("transactionId").toString();

			if (!classify.containsKey(transactionId))
				classify.put(m.get("transactionId").toString(), new ArrayList<>());

			classify.get(transactionId).add(m);
		});

		classify.entrySet().stream().forEach(e -> {
			boolean goon = e.getValue().size() > 10
					&& e.getValue().stream().anyMatch(m -> m.get("parentId").toString().equals(""));

			if (goon)
				new Test4(e.getValue(), e.getKey()).makeTree("").close();
		});
	}

	private List<Map>	list;
	private PrintWriter	pw	= null;

	public Test4(List<Map> list, String fileName) {
		this.list = list;

		System.out.println("filename->" + fileName + " || size->" + list.size());

		try {
			pw = new PrintWriter(new FileWriter(new File("F://download/TestWS/test/" + fileName + ".txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Test4 makeTree(String parent) {
		list.stream().filter(m -> m.get("parentId").equals(parent)).forEach(m -> {
			pw.println(JSON.toJSONString(m));

			makeTree(m.get("id").toString());
		});

		return this;
	}

	public void close() {
		pw.close();
	}

}

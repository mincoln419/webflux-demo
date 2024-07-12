package com.ideatec.springwebfluxdemo.coding;

import java.sql.Array;
import java.util.*;

public class OilerPj {

	public static void main(String[] args) {

		System.out.println(new OilerPj().solution());
	}


	public Long solution(){
		List<Integer> resultSet = new ArrayList<>();
		long cnt = 0;
		long sum = 0;
		for(int i = 1 ; i<= 9999 ; i++){
			Set<Long> check = new HashSet<>();
			long result = getHappyNumber(i, check);
			if(result == 1){
				cnt++;
				sum += i;
				resultSet.add(i);
			}
		}
		System.out.println("cnt: " + cnt);
		System.out.println("sum: " + sum);
		System.out.println("resultSet: " + resultSet);

		return sum * cnt;
	}

	private long getHappyNumber(long i, Set<Long> check) {

		if(check.contains(i) || i == 1)return i;
		check.add(i);
		String iString = String.valueOf(i);
		long sum = 0;
		System.out.println(iString);
		for(int j = 0 ; j < iString.length(); j++){
			long a = Long.parseLong(String.valueOf(iString.charAt(j)));

			sum += a * a;
		}

		return getHappyNumber(sum, check);
	}
}

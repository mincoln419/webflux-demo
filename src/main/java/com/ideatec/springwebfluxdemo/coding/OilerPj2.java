package com.ideatec.springwebfluxdemo.coding;

public class OilerPj2 {

	public static void main(String[] args) {
		System.out.println(new OilerPj2().solution());
	}

	private int solution() {

//		100,000,000,000,000원

		long maxNum = 100_000_000_000_000L;

		for(int i = 4 ; i >= 0 ; i--){
			double unit = Math.pow(10000, i);
			System.out.println(maxNum/unit);
		}




		return 0;
	}
}

package com.ideatec.springwebfluxdemo.coding;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class OilerPj2 {

	public static void main(String[] args) {
		System.out.println(new OilerPj2().solution());
	}

	Map<String, String> numberDic() {
		return Map.of(
				"0", ""
				, "1", "일"
				, "2", "이"
				, "3", "삼"
				, "4", "사"
				, "5", "오"
				, "6", "육"
				, "7", "칠"
				, "8", "팔"
				, "9", "구"
		);
	}

	Map<Integer, String> decDic() {
		return Map.of(

				4, "만"
				, 8, "억"
				, 12, "조"
		);
	}


	String[] samples() {

		return new String[]{
				"1원",
				"4원",
				"8원",
				"9원",
				"10원",
				"17원",
				"79원",
				"80원",
				"95원",
				"205원",
				"809원",
				"851원",
				"878원",
				"2,000원",
				"2,800원",
				"7,008원",
				"8,174원",
				"9,718원",
				"45,150원",
				"50,000원",
				"69,700원",
				"382,915원",
				"431,409원",
				"921,500원",
				"5,003,052원",
				"5,039,670원",
				"6,835,623원",
				"8,000,000원",
				"10,000,003원",
				"35,100,000원",
				"39,997,777원",
				"90,021,015원",
				"93,275,690원",
				"403,197,000원",
				"459,176,461원",
				"730,080,000원",
				"999,999,000원",
				"6,887,000,000원",
				"7,000,020,000원",
				"7,700,000,500원",
				"7,848,761,270원",
				"38,048,620,625원",
				"57,000,000,000원",
				"74,778,562,249원",
				"97,417,165,814원",
				"101,000,120,000원",
				"343,000,000,000원",
				"458,807,907,862원",
				"872,818,015,000원",
				"6,278,000,015,000원",
				"7,991,000,844,000원",
				"9,000,400,000,675원",
				"22,018,914,675,100원",
				"78,196,000,000,000원",
				"85,000,904,224,858원",
				"95,000,000,404,918원"
		};

	}

	String[] samples2() {
		return new String[]{
				"1원",
				"80,270원",
				"111,111원",
				"1,234,567,890원",
				"100,000,000,000,000원"
		};
	}

	static int sum = 0;
	static int statcCnt = 0;

	private int solution() {

//		100,000,000,000,000원

//		long maxNum = 100_000_000_000_000L;
		Map<Integer, String> decDic = decDic();
		Map<String, String> numberDic = numberDic();
		Stack<String> stringStack = new Stack<>();
		Arrays.stream(samples()).forEach(target -> {
			try {

				for (int i = target.length() - 1, cnt = 0; i >= 0; i--) {

					String next = String.valueOf(target.charAt(i));
					if (next.equals("원") || next.equals(",")) continue;


					StringBuilder sb = new StringBuilder();
					if (!next.equals("1")) {
						sb.append(numberDic.get(next));
					} else if (cnt % 4 == 0) {
						sb.append(numberDic.get(next));
					}
					if (!numberDic.get(next).isEmpty()) {
						switch (cnt % 4) {
							case 1:
								sb.append("십");
								break;
							case 2:
								sb.append("백");
								break;
							case 3:
								sb.append("천");
								break;
							default:
								break;

						}
					}
					if (decDic.containsKey(cnt)) {

						sb.append(decDic.get(cnt));
					}
					if (!sb.isEmpty()) stringStack.push(sb.toString());
					cnt++;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			StringBuilder rsb = new StringBuilder();
			String tmp = "";
			while (!stringStack.isEmpty()) {
				String pop = stringStack.pop();
				if (pop.contains("조") || pop.equals("억") || pop.equals("만")) {
					if (!tmp.contains("조") && !tmp.contains("억")) {
						rsb.append(pop);
						rsb.append(" ");
					}
				} else if (pop.equals("일만")) {
					if (tmp.contains("조") || tmp.contains("억")) {
						rsb.append("만");

					}else{
						rsb.append("일만");
					}
					rsb.append(" ");
				} else {
					rsb.append(pop);
					if (pop.contains("조") || pop.contains("억") || pop.contains("만")) {
						rsb.append(" ");
					}
				}
				tmp = pop;
			}
			System.out.println(rsb.toString().trim() + "원");
			String result = rsb.toString().trim() + "원";
			String[] resultSplit = result.split(" ");
			int len = resultSplit.length;
			int wordsCnt = result.replaceAll(" ", "").length();
			System.out.println(len + " * " + wordsCnt + " = " + len * wordsCnt);
			sum += len * wordsCnt;
			statcCnt++;
		});


		System.out.println(statcCnt);
		return sum;
	}
}

package cn.com.mwsn.mpm.common;

import java.util.ArrayList;
import java.util.List;

import cn.com.mwsn.mpm.entity.RFIDCardPerson;

public class WardRounds {

	private static List<WardRound> wardRounds = new ArrayList<WardRound>();

	static class WardRound {
		private Long comeinTime;
		private Long endTime;
		private String name;
		private String empNO;
		private String role;
		private String roomCode;

		public WardRound(Long comeinTime, Long endTime, String name,
				String empNO, String role, String roomCode) {
			super();
			this.comeinTime = comeinTime;
			this.endTime = endTime;
			this.name = name;
			this.empNO = empNO;
			this.role = role;
			this.roomCode = roomCode;
		}
	}

	public static boolean isWardRound(RFIDCardPerson person, Long endTime,
			String roomCode) {
		for (int i = 0; i < wardRounds.size(); i++) {
			if (wardRounds.get(i).empNO.equals(person.getEmpNO())
					&& wardRounds.get(i).roomCode.equals(roomCode)) {
				if (wardRounds.get(i).comeinTime < endTime
						&& Math.abs(endTime - wardRounds.get(i).comeinTime) >= Constant.minCheckWardTime) {
					// 一次查房
					wardRounds.remove(i);
					return true;
				}
				return false;
			}
		}
		return false;
	}

	public static void insert(RFIDCardPerson person, Long cominTime, String roomCode) {
		boolean flag = false;
		for (int i = 0; i < wardRounds.size(); i++) {
			if (wardRounds.get(i).empNO.equals(person.getEmpNO())) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			wardRounds.add(new WardRound(cominTime, -1L, person.getName(),
					person.getEmpNO(), person.getRole(), roomCode));
			System.out.println(wardRounds.size());
		}

	}

	public static List<WardRound> getWardRounds() {
		if (wardRounds == null) {
			wardRounds = new ArrayList<WardRound>();
		}
		return wardRounds;
	}
}

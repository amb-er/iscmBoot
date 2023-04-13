package com.armitage.server.activity.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ActivityConditionUtil {
	
	private Object resolveTarget(Object target,String type){
		switch (type) {
		case "Integer":
			if(target instanceof Integer){
				return target;
			}else if(target instanceof Long){
				return ((Long) target).intValue();
			}else if(target instanceof Double){
				DecimalFormat df = new DecimalFormat("#");
				String str = df.format(((Double) target).doubleValue());
				return Integer.parseInt(str);
			}
			break;
		case "Long":
			if(target instanceof Long){
				return target;
			}else if(target instanceof Integer){
				return ((Integer) target).longValue();
			}else if(target instanceof Double){
				DecimalFormat df = new DecimalFormat("#");
				String str = df.format(((Double) target).doubleValue());
				return Long.parseLong(str);
			}
			break;
		case "BigDecimal":
			if(target instanceof Integer){
				BigDecimal b = new BigDecimal(((Integer) target).intValue());
				return b;
			}else if(target instanceof Long){
				BigDecimal b = new BigDecimal(((Long) target).longValue());
				return b;
			}else if(target instanceof Double){
				DecimalFormat df = new DecimalFormat("#.00");
				String str = df.format(((Double) target).doubleValue());
				BigDecimal b = new BigDecimal(str);
				return b;
			}
			break;
		case "String":
			if(target instanceof String){
				return target;
			}else if(target instanceof Integer || target instanceof Long || target instanceof Double){
				return String.valueOf(target);
			}
			break;
		default:
			break;
		}
		return target;
	}

	/*值等于*/
	public boolean valueEq(Object source, Object target) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			if(value1 == value2){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			String s2 = (String) target;
			if(s1.equals(s2)){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			long l2 = ((Long) target).longValue();
			if(l1 == l2){
				return true;
			}
		} else if (source instanceof Boolean) {
			boolean b1 = ((Boolean) source).booleanValue();
			boolean b2 = ((Boolean) target).booleanValue();
			if(b1 == b2){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			if(d1.compareTo(d2) == 0){
				return true;
			}
		}
		return false;
	}
	
	/*值不等于*/
	public boolean valueNe(Object source, Object target) {
		if(source == null){
			return true;
		}
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			if(value1 != value2){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			String s2 = (String) target;
			if(!s1.equals(s2)){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			long l2 = ((Long) target).longValue();
			if(l1 != l2){
				return true;
			}
		} else if (source instanceof Boolean) {
			boolean b1 = ((Boolean) source).booleanValue();
			boolean b2 = ((Boolean) target).booleanValue();
			if(b1 != b2){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			if(d1.compareTo(d2) != 0){
				return true;
			}
		}
		return false;
	}
	
	/*值相似*/
	public boolean valueLike(Object source, Object target) {
		if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			String s2 = (String) target;
			if(s1.contains(s2)){
				return true;
			}
		}
		return false;
	}
	
	/*值大于等于*/
	public boolean valueGe(Object source, Object target) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			if(value1 >= value2){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			String s2 = (String) target;
			if(s1.compareTo(s2) >= 0){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			long l2 = ((Long) target).longValue();
			if(l1 >= l2){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			if(d1.compareTo(d2) >= 0){
				return true;
			}
		}
		return false;
	}
	
	/*值大于*/
	public boolean valueGt(Object source, Object target) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			if(value1 > value2){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			String s2 = (String) target;
			if(s1.compareTo(s2) > 0){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			long l2 = ((Long) target).longValue();
			if(l1 > l2){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			if(d1.compareTo(d2) > 0){
				return true;
			}
		}
		return false;
	}
	
	/*值小于等于*/
	public boolean valueLe(Object source, Object target) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			if(value1 <= value2){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			String s2 = (String) target;
			if(s1.compareTo(s2) <= 0){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			long l2 = ((Long) target).longValue();
			if(l1 <= l2){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			if(d1.compareTo(d2) <= 0){
				return true;
			}
		}
		return false;
	}
	
	/*值小于*/
	public boolean valueLt(Object source, Object target) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			if(value1 < value2){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			String s2 = (String) target;
			if(s1.compareTo(s2) < 0){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			long l2 = ((Long) target).longValue();
			if(l1 < l2){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			if(d1.compareTo(d2) < 0){
				return true;
			}
		}
		return false;
	}
	
	/*值介于两者之间（包含边界值）*/
	public boolean valueBetween(Object source, Object target, Object target2) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			if(!(target2 instanceof Integer)){
				target2 = resolveTarget(target2,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			int value3 = ((Integer) target2).intValue();
			if(value2 <= value1 && value1 <= value3){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			if(!(target2 instanceof String)){
				target2 = resolveTarget(target2,"String");
			}
			String s2 = (String) target;
			String s3 = (String) target2;
			if(s2.compareTo(s1) <= 0 && s1.compareTo(s3) <= 0){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			if(!(target2 instanceof Long)){
				target2 = resolveTarget(target2,"Long");
			}
			long l2 = ((Long) target).longValue();
			long l3 = ((Long) target2).longValue();
			if(l2 <= l1 && l1 <= l3){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			if(!(target2 instanceof BigDecimal)){
				target2 = resolveTarget(target2,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			BigDecimal d3 = (BigDecimal) target2;
			if(d2.compareTo(d1) <= 0 && d1.compareTo(d3) <= 0){
				return true;
			}
		}
		return false;
	}
	
	/*值介于两者之间（不包含边界值）*/
	public boolean valueBetween2(Object source, Object target, Object target2) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			if(!(target2 instanceof Integer)){
				target2 = resolveTarget(target2,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			int value3 = ((Integer) target2).intValue();
			if(value2 < value1 && value1 < value3){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			if(!(target2 instanceof String)){
				target2 = resolveTarget(target2,"String");
			}
			String s2 = (String) target;
			String s3 = (String) target2;
			if(s2.compareTo(s1) < 0 && s1.compareTo(s3) < 0){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			if(!(target2 instanceof Long)){
				target2 = resolveTarget(target2,"Long");
			}
			long l2 = ((Long) target).longValue();
			long l3 = ((Long) target2).longValue();
			if(l2 < l1 && l1 < l3){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			if(!(target2 instanceof BigDecimal)){
				target2 = resolveTarget(target2,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			BigDecimal d3 = (BigDecimal) target2;
			if(d2.compareTo(d1) < 0 && d1.compareTo(d3) < 0){
				return true;
			}
		}
		return false;
	}
	
	/*值介于两者之间（包含左边界值）*/
	public boolean valueBetween3(Object source, Object target, Object target2) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			if(!(target2 instanceof Integer)){
				target2 = resolveTarget(target2,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			int value3 = ((Integer) target2).intValue();
			if(value2 <= value1 && value1 < value3){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			if(!(target2 instanceof String)){
				target2 = resolveTarget(target2,"String");
			}
			String s2 = (String) target;
			String s3 = (String) target2;
			if(s2.compareTo(s1) <= 0 && s1.compareTo(s3) < 0){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			if(!(target2 instanceof Long)){
				target2 = resolveTarget(target2,"Long");
			}
			long l2 = ((Long) target).longValue();
			long l3 = ((Long) target2).longValue();
			if(l2 <= l1 && l1 < l3){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			if(!(target2 instanceof BigDecimal)){
				target2 = resolveTarget(target2,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			BigDecimal d3 = (BigDecimal) target2;
			if(d2.compareTo(d1) <= 0 && d1.compareTo(d3) < 0){
				return true;
			}
		}
		return false;
	}
	
	/*值介于两者之间（包含右边界值）*/
	public boolean valueBetween4(Object source, Object target, Object target2) {
		if (source instanceof Integer) {
			int value1 = ((Integer) source).intValue();
			if(!(target instanceof Integer)){
				target = resolveTarget(target,"Integer");
			}
			if(!(target2 instanceof Integer)){
				target2 = resolveTarget(target2,"Integer");
			}
			int value2 = ((Integer) target).intValue();
			int value3 = ((Integer) target2).intValue();
			if(value2 < value1 && value1 <= value3){
				return true;
			}
		} else if (source instanceof String) {
			String s1 = (String) source;
			if(!(target instanceof String)){
				target = resolveTarget(target,"String");
			}
			if(!(target2 instanceof String)){
				target2 = resolveTarget(target2,"String");
			}
			String s2 = (String) target;
			String s3 = (String) target2;
			if(s2.compareTo(s1) < 0 && s1.compareTo(s3) <= 0){
				return true;
			}
		} else if (source instanceof Long) {
			long l1 = ((Long) source).longValue();
			if(!(target instanceof Long)){
				target = resolveTarget(target,"Long");
			}
			if(!(target2 instanceof Long)){
				target2 = resolveTarget(target2,"Long");
			}
			long l2 = ((Long) target).longValue();
			long l3 = ((Long) target2).longValue();
			if(l2 < l1 && l1 <= l3){
				return true;
			}
		} else if (source instanceof BigDecimal) {
			BigDecimal d1 = (BigDecimal) source;
			if(!(target instanceof BigDecimal)){
				target = resolveTarget(target,"BigDecimal");
			}
			if(!(target2 instanceof BigDecimal)){
				target2 = resolveTarget(target2,"BigDecimal");
			}
			BigDecimal d2 = (BigDecimal) target;
			BigDecimal d3 = (BigDecimal) target2;
			if(d2.compareTo(d1) < 0 && d1.compareTo(d3) <= 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean valueListEq(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueEq(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListNe(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueNe(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListLike(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueLike(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListGe(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueGe(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListGt(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueGt(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListLe(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueLe(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListLt(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueLt(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListBetween(List list, Object target, Object target2) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueBetween(list.get(i),target,target2)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListBetween2(List list, Object target, Object target2) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueBetween2(list.get(i),target,target2)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListBetween3(List list, Object target, Object target2) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueBetween3(list.get(i),target,target2)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListBetween4(List list, Object target, Object target2) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueBetween4(list.get(i),target,target2)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean approvalOpinion(String opinion){
		if("agree".equalsIgnoreCase(opinion)){
			return true;
		}else if("refuse".equalsIgnoreCase(opinion)){
			return false;
		}
		return true;
	}
	
	public boolean agree(Object target){
		if(!(target instanceof String)){
			target = resolveTarget(target,"String");
		}
		String opinion = (String) target;
		return approvalOpinion(opinion);
	}
	
	public boolean refuse(Object target){
		if(!(target instanceof String)){
			target = resolveTarget(target,"String");
		}
		String opinion = (String) target;
		return approvalOpinion(opinion);
	}
	
	public boolean valueListAllEq(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueEq(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllNe(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueNe(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllLike(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueLike(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllGe(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueGe(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllGt(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueGt(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllLe(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueLe(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllLt(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueLt(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllBetween(List list, Object target, Object target2) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueBetween(list.get(i),target,target2)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllBetween2(List list, Object target, Object target2) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueBetween2(list.get(i),target,target2)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllBetween3(List list, Object target, Object target2) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueBetween3(list.get(i),target,target2)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllBetween4(List list, Object target, Object target2) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueBetween4(list.get(i),target,target2)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/*值in*/
	public boolean valueIn(Object source, Object target) {
		String range = String.valueOf(target);
		String[] rangeList = range.split(",");
		if(rangeList != null && rangeList.length > 0){
			for(String rangeValue : rangeList){
				if(StringUtils.isNotBlank(rangeValue)){
					if (source instanceof Integer) {
						int value1 = ((Integer) source).intValue();
						int value2 = Integer.parseInt(rangeValue);
						if(value1 == value2){
							return true;
						}
					} else if (source instanceof String) {
						String s1 = (String) source;
						if(s1.equals(rangeValue)){
							return true;
						}
					} else if (source instanceof Long) {
						long l1 = ((Long) source).longValue();
						long l2 = Long.parseLong(rangeValue);
						if(l1 == l2){
							return true;
						}
					} else if (source instanceof BigDecimal) {
						BigDecimal d1 = (BigDecimal) source;
						BigDecimal d2 = new BigDecimal(rangeValue);
						if(d1.compareTo(d2) == 0){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/*值notin*/
	public boolean valueNotIn(Object source, Object target) {
		if(source == null){
			return true;
		}
		String range = String.valueOf(target);
		String[] rangeList = range.split(",");
		if(rangeList != null && rangeList.length > 0){
			for(String rangeValue : rangeList){
				if(StringUtils.isNotBlank(rangeValue)){
					if (source instanceof Integer) {
						int value1 = ((Integer) source).intValue();
						int value2 = Integer.parseInt(rangeValue);
						if(value1 == value2){
							return false;
						}
					} else if (source instanceof String) {
						String s1 = (String) source;
						if(s1.equals(rangeValue)){
							return false;
						}
					} else if (source instanceof Long) {
						long l1 = ((Long) source).longValue();
						long l2 = Long.parseLong(rangeValue);
						if(l1 == l2){
							return false;
						}
					} else if (source instanceof BigDecimal) {
						BigDecimal d1 = (BigDecimal) source;
						BigDecimal d2 = new BigDecimal(rangeValue);
						if(d1.compareTo(d2) == 0){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean valueListIn(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueIn(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListNotIn(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(valueNotIn(list.get(i),target)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean valueListAllIn(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueIn(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean valueListAllNotIn(List list, Object target) {
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(!valueNotIn(list.get(i),target)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
}

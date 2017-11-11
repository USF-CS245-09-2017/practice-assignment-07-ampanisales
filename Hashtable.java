/**
 * @author Anthony Panisales
 */

public class Hashtable {
	
	class HashNode {
		
		String key;
		String value;
		HashNode next;
		
		public HashNode(String newK, String newV) {
			key = newK;
			value = newV;
		}
	}
	
	private HashNode[] arr;
	private int size;
	
	public Hashtable() {
		arr = new HashNode[314526];
		size = 0;
	}
	
	//Mid-squaring
	public int hashCode(String key) {
		return (int) Math.pow(Character.getNumericValue(key.charAt(key.length()/2)), 2);
	}
	
	public void put(String key, String value) {
		int index = hashCode(key) % arr.length;
		HashNode newNode = new HashNode(key, value);
		newNode.next = arr[index];
		arr[index] = newNode;
		if (!containsKey(key))
			size++;
	}
	
	public boolean containsKey(String key) {
		int index = hashCode(key) % arr.length;
		HashNode start = arr[index];
		
		while (start != null) {
			if (start.key.equals(key))
				return true;
			start = start.next;
		}
		return false;
	}
	
	public String get(String key) {
		if (!containsKey(key))
			return null;

		int index = hashCode(key) % arr.length;
		HashNode start = arr[index];
		
		while (start != null) {
			if (start.key.equals(key))
				return start.value;
			start = start.next;
		}
		return null;
	}
	
	public String remove(String key) throws Exception {
		if (!containsKey(key))
			throw new Exception();

		int index = hashCode(key) % arr.length;
		HashNode start = arr[index];
		
		if (start == null) {
			return null;
		} else {
			String removed = null;
			int count = 0;

			if (start.key.equals(key)) {
				removed = start.value;
				arr[index] = start.next;
				size--;
				start = arr[index];
				count++;
			}

			if (containsKey(key)) {
				while (start.next != null) {
					while (start.next != null && start.next.key.equals(key)) {
						count++;
						if (count == 1) {
							removed = start.next.value;
							size--;
						}
						start.next = start.next.next;
					}
					start = start.next;
				}
			}
			return removed;
		}
	}
}

[1mdiff --git a/Algorithm/DataStruct/HashSet/MyHashMap/src/MyHashMap.java b/Algorithm/DataStruct/HashSet/MyHashMap/src/MyHashMap.java[m
[1mindex 0a83a91..1e86b93 100644[m
[1m--- a/Algorithm/DataStruct/HashSet/MyHashMap/src/MyHashMap.java[m
[1m+++ b/Algorithm/DataStruct/HashSet/MyHashMap/src/MyHashMap.java[m
[36m@@ -3,7 +3,7 @@[m [mimport java.util.LinkedList;[m
 import java.util.List;[m
 [m
 public class MyHashMap {[m
[31m-	public static final int BUCKET_SIZE = 29;[m
[32m+[m	[32mprivate static final int BUCKET_SIZE = 29;[m
 [m
 	private final List<User>[] buckets;[m
 [m
[36m@@ -29,16 +29,18 @@[m [mpublic class MyHashMap {[m
 [m
 	public void put(User user) {[m
 		// System.out.println(user);[m
[31m-		List<User> ll = buckets[user.hashCode()];[m
[32m+[m		[32mfinal int index = user.hashCode() % BUCKET_SIZE;[m
[32m+[m		[32mList<User> ll = buckets[index];[m
 		if (ll == null) {[m
 			ll = new LinkedList<User>();[m
[31m-			buckets[user.hashCode()] = ll;[m
[32m+[m			[32mbuckets[index] = ll;[m
 		}[m
 		ll.add(user);[m
 	}[m
 [m
 	public User get(User user) {[m
[31m-		List<User> ll = buckets[user.hashCode()];[m
[32m+[m		[32mfinal int index = user.hashCode() % BUCKET_SIZE;[m
[32m+[m		[32mList<User> ll = buckets[index];[m
 		if (ll == null) {[m
 			return null;[m
 		}[m
[36m@@ -71,13 +73,14 @@[m [mpublic class MyHashMap {[m
 	}[m
 [m
 	public void remove(User user) {[m
[31m-		List<User> ll = buckets[user.hashCode()];[m
[32m+[m		[32mfinal int index = user.hashCode() % BUCKET_SIZE;[m
[32m+[m		[32mfinal List<User> ll = buckets[index];[m
 		if (ll == null) {[m
 			return;[m
 		}[m
[31m-		Iterator<User> users = ll.iterator();[m
[32m+[m		[32mfinal Iterator<User> users = ll.iterator();[m
 		while (users.hasNext()) {[m
[31m-			User u = users.next();[m
[32m+[m			[32mfinal User u = users.next();[m
 			if (u.equals(user)) {[m
 				users.remove();[m
 				break;[m
[1mdiff --git a/Algorithm/DataStruct/HashSet/MyHashMap/src/User.java b/Algorithm/DataStruct/HashSet/MyHashMap/src/User.java[m
[1mindex 7aae18e..6698b83 100644[m
[1m--- a/Algorithm/DataStruct/HashSet/MyHashMap/src/User.java[m
[1m+++ b/Algorithm/DataStruct/HashSet/MyHashMap/src/User.java[m
[36m@@ -7,7 +7,7 @@[m [mpublic class User {[m
 		int calc = 0;[m
 		for (int i = 0; i < name.length(); ++i) {[m
 			calc += (name.charAt(i) - 'a');[m
[31m-			calc %= MyHashMap.BUCKET_SIZE;[m
[32m+[m			[32mcalc %= 29;[m
 		}[m
 		hashCode = calc;[m
 	}[m
